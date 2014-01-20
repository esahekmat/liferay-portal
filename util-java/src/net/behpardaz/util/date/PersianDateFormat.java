/**
 * 
 */
package net.behpardaz.util.date;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.time.FastDateFormat;

import com.liferay.portal.kernel.util.StringPool;
import com.samanpr.jalalicalendar.JalaliCalendar;
import com.samanpr.jalalicalendar.JalaliCalendar.YearMonthDate;

/**
 * @author Esa Hekmatizadeh
 * 
 */
public class PersianDateFormat extends Format {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8799645183289628253L;
	private static PersianDateFormat _fomatter;
	private FastDateFormat _gregorian_formatter;

	protected PersianDateFormat(int dateStyle, int timeStyle,
			TimeZone timeZone, Locale locale) {
		_gregorian_formatter = FastDateFormat.getDateTimeInstance(
				FastDateFormat.LONG, timeStyle, timeZone, locale);
	}

	public String format(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		YearMonthDate jalali = JalaliCalendar
				.gregorianToJalali(new JalaliCalendar.YearMonthDate(cal
						.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
						.get(Calendar.DATE)));
		return jalali.getYear() + StringPool.SLASH + jalali.getMonth()
				+ StringPool.SLASH + jalali.getDate();
	}

	public String format(Calendar calendar) {
		YearMonthDate jalali = JalaliCalendar
				.gregorianToJalali(new JalaliCalendar.YearMonthDate(calendar
						.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DATE)));
		return jalali.getYear() + StringPool.SLASH + jalali.getMonth()
				+ StringPool.SLASH + jalali.getDate();
	}

	public static synchronized PersianDateFormat getDateTimeInstance(
			int dateStyle, int timeStyle, TimeZone timeZone, Locale locale) {
		if (_fomatter != null)
			return _fomatter;
		_fomatter = new PersianDateFormat(dateStyle, timeStyle, timeZone,
				locale);
		return _fomatter;
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo,
			FieldPosition pos) {
		if (obj instanceof Date) {
			return new StringBuffer(this.format((Date) obj));
		} else if (obj instanceof Calendar) {
			return new StringBuffer(this.format((Calendar) obj));
		}
		return _gregorian_formatter.format(obj, toAppendTo, pos);
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		return _gregorian_formatter.parseObject(source, pos);
	}

}