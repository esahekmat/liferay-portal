/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchLayoutSetPrototypeException;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.impl.LayoutSetPrototypeImpl;
import com.liferay.portal.model.impl.LayoutSetPrototypeModelImpl;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the layout set prototype service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetPrototypePersistence
 * @see LayoutSetPrototypeUtil
 * @generated
 */
public class LayoutSetPrototypePersistenceImpl extends BasePersistenceImpl<LayoutSetPrototype>
	implements LayoutSetPrototypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LayoutSetPrototypeUtil} to access the layout set prototype persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LayoutSetPrototypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_UUID = new FinderPath(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_C_A = new FinderPath(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findByC_A",
			new String[] {
				Long.class.getName(), Boolean.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_C_A = new FinderPath(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countByC_A",
			new String[] { Long.class.getName(), Boolean.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeModelImpl.FINDER_CACHE_ENABLED,
			FINDER_CLASS_NAME_LIST, "countAll", new String[0]);

	/**
	 * Caches the layout set prototype in the entity cache if it is enabled.
	 *
	 * @param layoutSetPrototype the layout set prototype to cache
	 */
	public void cacheResult(LayoutSetPrototype layoutSetPrototype) {
		EntityCacheUtil.putResult(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeImpl.class, layoutSetPrototype.getPrimaryKey(),
			layoutSetPrototype);

		layoutSetPrototype.resetOriginalValues();
	}

	/**
	 * Caches the layout set prototypes in the entity cache if it is enabled.
	 *
	 * @param layoutSetPrototypes the layout set prototypes to cache
	 */
	public void cacheResult(List<LayoutSetPrototype> layoutSetPrototypes) {
		for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
			if (EntityCacheUtil.getResult(
						LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
						LayoutSetPrototypeImpl.class,
						layoutSetPrototype.getPrimaryKey(), this) == null) {
				cacheResult(layoutSetPrototype);
			}
		}
	}

	/**
	 * Clears the cache for all layout set prototypes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(LayoutSetPrototypeImpl.class.getName());
		}

		EntityCacheUtil.clearCache(LayoutSetPrototypeImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	/**
	 * Clears the cache for the layout set prototype.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	public void clearCache(LayoutSetPrototype layoutSetPrototype) {
		EntityCacheUtil.removeResult(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeImpl.class, layoutSetPrototype.getPrimaryKey());
	}

	/**
	 * Creates a new layout set prototype with the primary key. Does not add the layout set prototype to the database.
	 *
	 * @param layoutSetPrototypeId the primary key for the new layout set prototype
	 * @return the new layout set prototype
	 */
	public LayoutSetPrototype create(long layoutSetPrototypeId) {
		LayoutSetPrototype layoutSetPrototype = new LayoutSetPrototypeImpl();

		layoutSetPrototype.setNew(true);
		layoutSetPrototype.setPrimaryKey(layoutSetPrototypeId);

		String uuid = PortalUUIDUtil.generate();

		layoutSetPrototype.setUuid(uuid);

		return layoutSetPrototype;
	}

	/**
	 * Removes the layout set prototype with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the layout set prototype to remove
	 * @return the layout set prototype that was removed
	 * @throws com.liferay.portal.NoSuchModelException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype remove(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return remove(((Long)primaryKey).longValue());
	}

	/**
	 * Removes the layout set prototype with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutSetPrototypeId the primary key of the layout set prototype to remove
	 * @return the layout set prototype that was removed
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype remove(long layoutSetPrototypeId)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		Session session = null;

		try {
			session = openSession();

			LayoutSetPrototype layoutSetPrototype = (LayoutSetPrototype)session.get(LayoutSetPrototypeImpl.class,
					Long.valueOf(layoutSetPrototypeId));

			if (layoutSetPrototype == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
						layoutSetPrototypeId);
				}

				throw new NoSuchLayoutSetPrototypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					layoutSetPrototypeId);
			}

			return layoutSetPrototypePersistence.remove(layoutSetPrototype);
		}
		catch (NoSuchLayoutSetPrototypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Removes the layout set prototype from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layoutSetPrototype the layout set prototype to remove
	 * @return the layout set prototype that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype remove(LayoutSetPrototype layoutSetPrototype)
		throws SystemException {
		return super.remove(layoutSetPrototype);
	}

	protected LayoutSetPrototype removeImpl(
		LayoutSetPrototype layoutSetPrototype) throws SystemException {
		layoutSetPrototype = toUnwrappedModel(layoutSetPrototype);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, layoutSetPrototype);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeImpl.class, layoutSetPrototype.getPrimaryKey());

		return layoutSetPrototype;
	}

	public LayoutSetPrototype updateImpl(
		com.liferay.portal.model.LayoutSetPrototype layoutSetPrototype,
		boolean merge) throws SystemException {
		layoutSetPrototype = toUnwrappedModel(layoutSetPrototype);

		if (Validator.isNull(layoutSetPrototype.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			layoutSetPrototype.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, layoutSetPrototype, merge);

			layoutSetPrototype.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
			LayoutSetPrototypeImpl.class, layoutSetPrototype.getPrimaryKey(),
			layoutSetPrototype);

		return layoutSetPrototype;
	}

	protected LayoutSetPrototype toUnwrappedModel(
		LayoutSetPrototype layoutSetPrototype) {
		if (layoutSetPrototype instanceof LayoutSetPrototypeImpl) {
			return layoutSetPrototype;
		}

		LayoutSetPrototypeImpl layoutSetPrototypeImpl = new LayoutSetPrototypeImpl();

		layoutSetPrototypeImpl.setNew(layoutSetPrototype.isNew());
		layoutSetPrototypeImpl.setPrimaryKey(layoutSetPrototype.getPrimaryKey());

		layoutSetPrototypeImpl.setUuid(layoutSetPrototype.getUuid());
		layoutSetPrototypeImpl.setLayoutSetPrototypeId(layoutSetPrototype.getLayoutSetPrototypeId());
		layoutSetPrototypeImpl.setCompanyId(layoutSetPrototype.getCompanyId());
		layoutSetPrototypeImpl.setName(layoutSetPrototype.getName());
		layoutSetPrototypeImpl.setDescription(layoutSetPrototype.getDescription());
		layoutSetPrototypeImpl.setSettings(layoutSetPrototype.getSettings());
		layoutSetPrototypeImpl.setActive(layoutSetPrototype.isActive());

		return layoutSetPrototypeImpl;
	}

	/**
	 * Finds the layout set prototype with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout set prototype to find
	 * @return the layout set prototype
	 * @throws com.liferay.portal.NoSuchModelException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the layout set prototype with the primary key or throws a {@link com.liferay.portal.NoSuchLayoutSetPrototypeException} if it could not be found.
	 *
	 * @param layoutSetPrototypeId the primary key of the layout set prototype to find
	 * @return the layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype findByPrimaryKey(long layoutSetPrototypeId)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		LayoutSetPrototype layoutSetPrototype = fetchByPrimaryKey(layoutSetPrototypeId);

		if (layoutSetPrototype == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					layoutSetPrototypeId);
			}

			throw new NoSuchLayoutSetPrototypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				layoutSetPrototypeId);
		}

		return layoutSetPrototype;
	}

	/**
	 * Finds the layout set prototype with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the layout set prototype to find
	 * @return the layout set prototype, or <code>null</code> if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Finds the layout set prototype with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param layoutSetPrototypeId the primary key of the layout set prototype to find
	 * @return the layout set prototype, or <code>null</code> if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype fetchByPrimaryKey(long layoutSetPrototypeId)
		throws SystemException {
		LayoutSetPrototype layoutSetPrototype = (LayoutSetPrototype)EntityCacheUtil.getResult(LayoutSetPrototypeModelImpl.ENTITY_CACHE_ENABLED,
				LayoutSetPrototypeImpl.class, layoutSetPrototypeId, this);

		if (layoutSetPrototype == null) {
			Session session = null;

			try {
				session = openSession();

				layoutSetPrototype = (LayoutSetPrototype)session.get(LayoutSetPrototypeImpl.class,
						Long.valueOf(layoutSetPrototypeId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (layoutSetPrototype != null) {
					cacheResult(layoutSetPrototype);
				}

				closeSession(session);
			}
		}

		return layoutSetPrototype;
	}

	/**
	 * Finds all the layout set prototypes where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findByUuid(String uuid)
		throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the layout set prototypes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @return the range of matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Finds an ordered range of all the layout set prototypes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				uuid,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<LayoutSetPrototype> list = (List<LayoutSetPrototype>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_UUID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<LayoutSetPrototype>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_UUID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first layout set prototype in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		List<LayoutSetPrototype> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLayoutSetPrototypeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last layout set prototype in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		int count = countByUuid(uuid);

		List<LayoutSetPrototype> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLayoutSetPrototypeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the layout set prototypes before and after the current layout set prototype in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param layoutSetPrototypeId the primary key of the current layout set prototype
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype[] findByUuid_PrevAndNext(
		long layoutSetPrototypeId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		LayoutSetPrototype layoutSetPrototype = findByPrimaryKey(layoutSetPrototypeId);

		Session session = null;

		try {
			session = openSession();

			LayoutSetPrototype[] array = new LayoutSetPrototypeImpl[3];

			array[0] = getByUuid_PrevAndNext(session, layoutSetPrototype, uuid,
					orderByComparator, true);

			array[1] = layoutSetPrototype;

			array[2] = getByUuid_PrevAndNext(session, layoutSetPrototype, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSetPrototype getByUuid_PrevAndNext(Session session,
		LayoutSetPrototype layoutSetPrototype, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(layoutSetPrototype);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutSetPrototype> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the layout set prototypes where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> filterFindByUuid(String uuid)
		throws SystemException {
		return filterFindByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the layout set prototypes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @return the range of matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> filterFindByUuid(String uuid, int start,
		int end) throws SystemException {
		return filterFindByUuid(uuid, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the layout set prototypes where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> filterFindByUuid(String uuid, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid(uuid, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(2);
		}

		query.append(_FILTER_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		appendGroupByComparator(query, _FILTER_COLUMN_PK);

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				LayoutSetPrototype.class.getName(), _FILTER_COLUMN_PK);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, LayoutSetPrototypeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, LayoutSetPrototypeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			return (List<LayoutSetPrototype>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the layout set prototypes before and after the current layout set prototype in the ordered set where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param layoutSetPrototypeId the primary key of the current layout set prototype
	 * @param uuid the uuid to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype[] filterFindByUuid_PrevAndNext(
		long layoutSetPrototypeId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUuid_PrevAndNext(layoutSetPrototypeId, uuid,
				orderByComparator);
		}

		LayoutSetPrototype layoutSetPrototype = findByPrimaryKey(layoutSetPrototypeId);

		Session session = null;

		try {
			session = openSession();

			LayoutSetPrototype[] array = new LayoutSetPrototypeImpl[3];

			array[0] = filterGetByUuid_PrevAndNext(session, layoutSetPrototype,
					uuid, orderByComparator, true);

			array[1] = layoutSetPrototype;

			array[2] = filterGetByUuid_PrevAndNext(session, layoutSetPrototype,
					uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSetPrototype filterGetByUuid_PrevAndNext(Session session,
		LayoutSetPrototype layoutSetPrototype, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_FILTER_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		appendGroupByComparator(query, _FILTER_COLUMN_PK);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				LayoutSetPrototype.class.getName(), _FILTER_COLUMN_PK);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, LayoutSetPrototypeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, LayoutSetPrototypeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(layoutSetPrototype);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutSetPrototype> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the layout set prototypes where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Finds a range of all the layout set prototypes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @return the range of matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findByCompanyId(long companyId, int start,
		int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Finds an ordered range of all the layout set prototypes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findByCompanyId(long companyId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<LayoutSetPrototype> list = (List<LayoutSetPrototype>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<LayoutSetPrototype>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first layout set prototype in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		List<LayoutSetPrototype> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLayoutSetPrototypeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last layout set prototype in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		int count = countByCompanyId(companyId);

		List<LayoutSetPrototype> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLayoutSetPrototypeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the layout set prototypes before and after the current layout set prototype in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param layoutSetPrototypeId the primary key of the current layout set prototype
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype[] findByCompanyId_PrevAndNext(
		long layoutSetPrototypeId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		LayoutSetPrototype layoutSetPrototype = findByPrimaryKey(layoutSetPrototypeId);

		Session session = null;

		try {
			session = openSession();

			LayoutSetPrototype[] array = new LayoutSetPrototypeImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, layoutSetPrototype,
					companyId, orderByComparator, true);

			array[1] = layoutSetPrototype;

			array[2] = getByCompanyId_PrevAndNext(session, layoutSetPrototype,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSetPrototype getByCompanyId_PrevAndNext(Session session,
		LayoutSetPrototype layoutSetPrototype, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(layoutSetPrototype);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutSetPrototype> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the layout set prototypes where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> filterFindByCompanyId(long companyId)
		throws SystemException {
		return filterFindByCompanyId(companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the layout set prototypes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @return the range of matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> filterFindByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return filterFindByCompanyId(companyId, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the layout set prototypes where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> filterFindByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId(companyId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(2);
		}

		query.append(_FILTER_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		appendGroupByComparator(query, _FILTER_COLUMN_PK);

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				LayoutSetPrototype.class.getName(), _FILTER_COLUMN_PK);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, LayoutSetPrototypeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, LayoutSetPrototypeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			return (List<LayoutSetPrototype>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the layout set prototypes before and after the current layout set prototype in the ordered set where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param layoutSetPrototypeId the primary key of the current layout set prototype
	 * @param companyId the company ID to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype[] filterFindByCompanyId_PrevAndNext(
		long layoutSetPrototypeId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByCompanyId_PrevAndNext(layoutSetPrototypeId, companyId,
				orderByComparator);
		}

		LayoutSetPrototype layoutSetPrototype = findByPrimaryKey(layoutSetPrototypeId);

		Session session = null;

		try {
			session = openSession();

			LayoutSetPrototype[] array = new LayoutSetPrototypeImpl[3];

			array[0] = filterGetByCompanyId_PrevAndNext(session,
					layoutSetPrototype, companyId, orderByComparator, true);

			array[1] = layoutSetPrototype;

			array[2] = filterGetByCompanyId_PrevAndNext(session,
					layoutSetPrototype, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSetPrototype filterGetByCompanyId_PrevAndNext(
		Session session, LayoutSetPrototype layoutSetPrototype, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_FILTER_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		appendGroupByComparator(query, _FILTER_COLUMN_PK);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				LayoutSetPrototype.class.getName(), _FILTER_COLUMN_PK);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, LayoutSetPrototypeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, LayoutSetPrototypeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(layoutSetPrototype);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutSetPrototype> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the layout set prototypes where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @return the matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findByC_A(long companyId, boolean active)
		throws SystemException {
		return findByC_A(companyId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the layout set prototypes where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @return the range of matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findByC_A(long companyId, boolean active,
		int start, int end) throws SystemException {
		return findByC_A(companyId, active, start, end, null);
	}

	/**
	 * Finds an ordered range of all the layout set prototypes where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findByC_A(long companyId, boolean active,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, active,
				
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<LayoutSetPrototype> list = (List<LayoutSetPrototype>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_C_A,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

			query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(active);

				list = (List<LayoutSetPrototype>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_BY_C_A,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_C_A,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Finds the first layout set prototype in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype findByC_A_First(long companyId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		List<LayoutSetPrototype> list = findByC_A(companyId, active, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLayoutSetPrototypeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the last layout set prototype in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a matching layout set prototype could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype findByC_A_Last(long companyId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		int count = countByC_A(companyId, active);

		List<LayoutSetPrototype> list = findByC_A(companyId, active, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", active=");
			msg.append(active);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchLayoutSetPrototypeException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Finds the layout set prototypes before and after the current layout set prototype in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param layoutSetPrototypeId the primary key of the current layout set prototype
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype[] findByC_A_PrevAndNext(
		long layoutSetPrototypeId, long companyId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		LayoutSetPrototype layoutSetPrototype = findByPrimaryKey(layoutSetPrototypeId);

		Session session = null;

		try {
			session = openSession();

			LayoutSetPrototype[] array = new LayoutSetPrototypeImpl[3];

			array[0] = getByC_A_PrevAndNext(session, layoutSetPrototype,
					companyId, active, orderByComparator, true);

			array[1] = layoutSetPrototype;

			array[2] = getByC_A_PrevAndNext(session, layoutSetPrototype,
					companyId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSetPrototype getByC_A_PrevAndNext(Session session,
		LayoutSetPrototype layoutSetPrototype, long companyId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

		query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(layoutSetPrototype);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutSetPrototype> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Filters by the user's permissions and finds all the layout set prototypes where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @return the matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> filterFindByC_A(long companyId,
		boolean active) throws SystemException {
		return filterFindByC_A(companyId, active, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Filters by the user's permissions and finds a range of all the layout set prototypes where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @return the range of matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> filterFindByC_A(long companyId,
		boolean active, int start, int end) throws SystemException {
		return filterFindByC_A(companyId, active, start, end, null);
	}

	/**
	 * Filters by the user's permissions and finds an ordered range of all the layout set prototypes where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> filterFindByC_A(long companyId,
		boolean active, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_A(companyId, active, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_FILTER_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

		query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

		appendGroupByComparator(query, _FILTER_COLUMN_PK);

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				LayoutSetPrototype.class.getName(), _FILTER_COLUMN_PK);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, LayoutSetPrototypeImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, LayoutSetPrototypeImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(active);

			return (List<LayoutSetPrototype>)QueryUtil.list(q, getDialect(),
				start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Filters the layout set prototypes before and after the current layout set prototype in the ordered set where companyId = &#63; and active = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param layoutSetPrototypeId the primary key of the current layout set prototype
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next layout set prototype
	 * @throws com.liferay.portal.NoSuchLayoutSetPrototypeException if a layout set prototype with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LayoutSetPrototype[] filterFindByC_A_PrevAndNext(
		long layoutSetPrototypeId, long companyId, boolean active,
		OrderByComparator orderByComparator)
		throws NoSuchLayoutSetPrototypeException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByC_A_PrevAndNext(layoutSetPrototypeId, companyId,
				active, orderByComparator);
		}

		LayoutSetPrototype layoutSetPrototype = findByPrimaryKey(layoutSetPrototypeId);

		Session session = null;

		try {
			session = openSession();

			LayoutSetPrototype[] array = new LayoutSetPrototypeImpl[3];

			array[0] = filterGetByC_A_PrevAndNext(session, layoutSetPrototype,
					companyId, active, orderByComparator, true);

			array[1] = layoutSetPrototype;

			array[2] = filterGetByC_A_PrevAndNext(session, layoutSetPrototype,
					companyId, active, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected LayoutSetPrototype filterGetByC_A_PrevAndNext(Session session,
		LayoutSetPrototype layoutSetPrototype, long companyId, boolean active,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_FILTER_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE);

		query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

		appendGroupByComparator(query, _FILTER_COLUMN_PK);

		if (orderByComparator != null) {
			String[] orderByFields = orderByComparator.getOrderByFields();

			if (orderByFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				LayoutSetPrototype.class.getName(), _FILTER_COLUMN_PK);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, LayoutSetPrototypeImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, LayoutSetPrototypeImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(active);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByValues(layoutSetPrototype);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<LayoutSetPrototype> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Finds all the layout set prototypes.
	 *
	 * @return the layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Finds a range of all the layout set prototypes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @return the range of layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Finds an ordered range of all the layout set prototypes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of layout set prototypes to return
	 * @param end the upper bound of the range of layout set prototypes to return (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public List<LayoutSetPrototype> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end),
				String.valueOf(orderByComparator)
			};

		List<LayoutSetPrototype> list = (List<LayoutSetPrototype>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LAYOUTSETPROTOTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LAYOUTSETPROTOTYPE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<LayoutSetPrototype>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<LayoutSetPrototype>)QueryUtil.list(q,
							getDialect(), start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FIND_ALL,
						finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs,
						list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the layout set prototypes where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (LayoutSetPrototype layoutSetPrototype : findByUuid(uuid)) {
			layoutSetPrototypePersistence.remove(layoutSetPrototype);
		}
	}

	/**
	 * Removes all the layout set prototypes where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (LayoutSetPrototype layoutSetPrototype : findByCompanyId(companyId)) {
			layoutSetPrototypePersistence.remove(layoutSetPrototype);
		}
	}

	/**
	 * Removes all the layout set prototypes where companyId = &#63; and active = &#63; from the database.
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_A(long companyId, boolean active)
		throws SystemException {
		for (LayoutSetPrototype layoutSetPrototype : findByC_A(companyId, active)) {
			layoutSetPrototypePersistence.remove(layoutSetPrototype);
		}
	}

	/**
	 * Removes all the layout set prototypes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (LayoutSetPrototype layoutSetPrototype : findAll()) {
			layoutSetPrototypePersistence.remove(layoutSetPrototype);
		}
	}

	/**
	 * Counts all the layout set prototypes where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the number of matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTSETPROTOTYPE_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the layout set prototypes where uuid = &#63;.
	 *
	 * @param uuid the uuid to search with
	 * @return the number of matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByUuid(String uuid) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUuid(uuid);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_LAYOUTSETPROTOTYPE_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				LayoutSetPrototype.class.getName(), _FILTER_COLUMN_PK);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			if (uuid != null) {
				qPos.add(uuid);
			}

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the layout set prototypes where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the number of matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LAYOUTSETPROTOTYPE_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the layout set prototypes where companyId = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @return the number of matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByCompanyId(long companyId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByCompanyId(companyId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_LAYOUTSETPROTOTYPE_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				LayoutSetPrototype.class.getName(), _FILTER_COLUMN_PK);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the layout set prototypes where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @return the number of matching layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_A(long companyId, boolean active)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, active };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_C_A,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LAYOUTSETPROTOTYPE_WHERE);

			query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(active);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_A, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Filters by the user's permissions and counts all the layout set prototypes where companyId = &#63; and active = &#63;.
	 *
	 * @param companyId the company ID to search with
	 * @param active the active to search with
	 * @return the number of matching layout set prototypes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByC_A(long companyId, boolean active)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByC_A(companyId, active);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_LAYOUTSETPROTOTYPE_WHERE);

		query.append(_FINDER_COLUMN_C_A_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_A_ACTIVE_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				LayoutSetPrototype.class.getName(), _FILTER_COLUMN_PK);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			qPos.add(active);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Counts all the layout set prototypes.
	 *
	 * @return the number of layout set prototypes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LAYOUTSETPROTOTYPE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the layout set prototype persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.portal.util.PropsUtil.get(
						"value.object.listener.com.liferay.portal.model.LayoutSetPrototype")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<LayoutSetPrototype>> listenersList = new ArrayList<ModelListener<LayoutSetPrototype>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<LayoutSetPrototype>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(LayoutSetPrototypeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST);
	}

	@BeanReference(type = AccountPersistence.class)
	protected AccountPersistence accountPersistence;
	@BeanReference(type = AddressPersistence.class)
	protected AddressPersistence addressPersistence;
	@BeanReference(type = BrowserTrackerPersistence.class)
	protected BrowserTrackerPersistence browserTrackerPersistence;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = ClusterGroupPersistence.class)
	protected ClusterGroupPersistence clusterGroupPersistence;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = ContactPersistence.class)
	protected ContactPersistence contactPersistence;
	@BeanReference(type = CountryPersistence.class)
	protected CountryPersistence countryPersistence;
	@BeanReference(type = EmailAddressPersistence.class)
	protected EmailAddressPersistence emailAddressPersistence;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;
	@BeanReference(type = LayoutPrototypePersistence.class)
	protected LayoutPrototypePersistence layoutPrototypePersistence;
	@BeanReference(type = LayoutRevisionPersistence.class)
	protected LayoutRevisionPersistence layoutRevisionPersistence;
	@BeanReference(type = LayoutSetPersistence.class)
	protected LayoutSetPersistence layoutSetPersistence;
	@BeanReference(type = LayoutSetBranchPersistence.class)
	protected LayoutSetBranchPersistence layoutSetBranchPersistence;
	@BeanReference(type = LayoutSetPrototypePersistence.class)
	protected LayoutSetPrototypePersistence layoutSetPrototypePersistence;
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = LockPersistence.class)
	protected LockPersistence lockPersistence;
	@BeanReference(type = MembershipRequestPersistence.class)
	protected MembershipRequestPersistence membershipRequestPersistence;
	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;
	@BeanReference(type = OrgGroupPermissionPersistence.class)
	protected OrgGroupPermissionPersistence orgGroupPermissionPersistence;
	@BeanReference(type = OrgGroupRolePersistence.class)
	protected OrgGroupRolePersistence orgGroupRolePersistence;
	@BeanReference(type = OrgLaborPersistence.class)
	protected OrgLaborPersistence orgLaborPersistence;
	@BeanReference(type = PasswordPolicyPersistence.class)
	protected PasswordPolicyPersistence passwordPolicyPersistence;
	@BeanReference(type = PasswordPolicyRelPersistence.class)
	protected PasswordPolicyRelPersistence passwordPolicyRelPersistence;
	@BeanReference(type = PasswordTrackerPersistence.class)
	protected PasswordTrackerPersistence passwordTrackerPersistence;
	@BeanReference(type = PermissionPersistence.class)
	protected PermissionPersistence permissionPersistence;
	@BeanReference(type = PhonePersistence.class)
	protected PhonePersistence phonePersistence;
	@BeanReference(type = PluginSettingPersistence.class)
	protected PluginSettingPersistence pluginSettingPersistence;
	@BeanReference(type = PortletPersistence.class)
	protected PortletPersistence portletPersistence;
	@BeanReference(type = PortletItemPersistence.class)
	protected PortletItemPersistence portletItemPersistence;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
	@BeanReference(type = RegionPersistence.class)
	protected RegionPersistence regionPersistence;
	@BeanReference(type = ReleasePersistence.class)
	protected ReleasePersistence releasePersistence;
	@BeanReference(type = RepositoryPersistence.class)
	protected RepositoryPersistence repositoryPersistence;
	@BeanReference(type = RepositoryEntryPersistence.class)
	protected RepositoryEntryPersistence repositoryEntryPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = ResourceActionPersistence.class)
	protected ResourceActionPersistence resourceActionPersistence;
	@BeanReference(type = ResourceCodePersistence.class)
	protected ResourceCodePersistence resourceCodePersistence;
	@BeanReference(type = ResourcePermissionPersistence.class)
	protected ResourcePermissionPersistence resourcePermissionPersistence;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = ServiceComponentPersistence.class)
	protected ServiceComponentPersistence serviceComponentPersistence;
	@BeanReference(type = ShardPersistence.class)
	protected ShardPersistence shardPersistence;
	@BeanReference(type = SubscriptionPersistence.class)
	protected SubscriptionPersistence subscriptionPersistence;
	@BeanReference(type = TeamPersistence.class)
	protected TeamPersistence teamPersistence;
	@BeanReference(type = TicketPersistence.class)
	protected TicketPersistence ticketPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;
	@BeanReference(type = UserGroupGroupRolePersistence.class)
	protected UserGroupGroupRolePersistence userGroupGroupRolePersistence;
	@BeanReference(type = UserGroupRolePersistence.class)
	protected UserGroupRolePersistence userGroupRolePersistence;
	@BeanReference(type = UserIdMapperPersistence.class)
	protected UserIdMapperPersistence userIdMapperPersistence;
	@BeanReference(type = UserNotificationEventPersistence.class)
	protected UserNotificationEventPersistence userNotificationEventPersistence;
	@BeanReference(type = UserTrackerPersistence.class)
	protected UserTrackerPersistence userTrackerPersistence;
	@BeanReference(type = UserTrackerPathPersistence.class)
	protected UserTrackerPathPersistence userTrackerPathPersistence;
	@BeanReference(type = VirtualHostPersistence.class)
	protected VirtualHostPersistence virtualHostPersistence;
	@BeanReference(type = WebDAVPropsPersistence.class)
	protected WebDAVPropsPersistence webDAVPropsPersistence;
	@BeanReference(type = WebsitePersistence.class)
	protected WebsitePersistence websitePersistence;
	@BeanReference(type = WorkflowDefinitionLinkPersistence.class)
	protected WorkflowDefinitionLinkPersistence workflowDefinitionLinkPersistence;
	@BeanReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
	private static final String _SQL_SELECT_LAYOUTSETPROTOTYPE = "SELECT layoutSetPrototype FROM LayoutSetPrototype layoutSetPrototype";
	private static final String _SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE = "SELECT layoutSetPrototype FROM LayoutSetPrototype layoutSetPrototype WHERE ";
	private static final String _SQL_COUNT_LAYOUTSETPROTOTYPE = "SELECT COUNT(layoutSetPrototype) FROM LayoutSetPrototype layoutSetPrototype";
	private static final String _SQL_COUNT_LAYOUTSETPROTOTYPE_WHERE = "SELECT COUNT(layoutSetPrototype) FROM LayoutSetPrototype layoutSetPrototype WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "layoutSetPrototype.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "layoutSetPrototype.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(layoutSetPrototype.uuid IS NULL OR layoutSetPrototype.uuid = ?)";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "layoutSetPrototype.companyId = ?";
	private static final String _FINDER_COLUMN_C_A_COMPANYID_2 = "layoutSetPrototype.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_A_ACTIVE_2 = "layoutSetPrototype.active = ?";
	private static final String _FILTER_SQL_SELECT_LAYOUTSETPROTOTYPE_WHERE = "SELECT {layoutSetPrototype.*} FROM LayoutSetPrototype layoutSetPrototype WHERE ";
	private static final String _FILTER_SQL_COUNT_LAYOUTSETPROTOTYPE_WHERE = "SELECT COUNT(DISTINCT layoutSetPrototype.layoutSetPrototypeId) AS COUNT_VALUE FROM LayoutSetPrototype layoutSetPrototype WHERE ";
	private static final String _FILTER_COLUMN_PK = "layoutSetPrototype.layoutSetPrototypeId";
	private static final String _FILTER_ENTITY_ALIAS = "layoutSetPrototype";
	private static final String _FILTER_ENTITY_TABLE = "LayoutSetPrototype";
	private static final String _ORDER_BY_ENTITY_ALIAS = "layoutSetPrototype.";
	private static final String _ORDER_BY_ENTITY_TABLE = "LayoutSetPrototype.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LayoutSetPrototype exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LayoutSetPrototype exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = com.liferay.portal.util.PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE;
	private static Log _log = LogFactoryUtil.getLog(LayoutSetPrototypePersistenceImpl.class);
}