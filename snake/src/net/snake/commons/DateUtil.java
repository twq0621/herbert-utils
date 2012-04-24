/**
 * 
 */
package net.snake.commons;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间工具 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class DateUtil {
	/**
	 * 时间获取前一天的Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToPriDayCalendar(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
		return calendar;
	}

	/**
	 * 获取当前时间的下周一0点
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToNextMonDay(Date date) {
		Calendar c = dateToPriDayCalendar(date);
		c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) + 1);
		c.set(Calendar.DAY_OF_WEEK, 2);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c;
	}

	/**
	 * DATE 转换Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 获取当前时间的上以周一0点
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar dateToPriMonDay(Date date) {
		Calendar c = dateToPriDayCalendar(date);
		c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) - 1);
		c.set(Calendar.DAY_OF_WEEK, 2);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c;
	}

	/**
	 * @param giftDate
	 * @param date
	 */
	public static boolean isSameDay(Date one, Date two) {
		Calendar calendarOne = dateToCalendar(one);
		Calendar calendarTwo = dateToCalendar(two);
		int yearOne = calendarOne.get(Calendar.YEAR);
		int dayOne = calendarOne.get(Calendar.DAY_OF_YEAR);
		int yeartwo = calendarTwo.get(Calendar.YEAR);
		int daytwo = calendarTwo.get(Calendar.DAY_OF_YEAR);
		if (dayOne != daytwo) {
			return false;
		}
		if (yearOne != yeartwo) {
			return false;
		}
		return true;
	}

	/**
	 * @param date
	 */
	public static Date dateToNextDay(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		Calendar calendarUpdate = new GregorianCalendar();
		calendarUpdate.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1, 0, 0, 0);
		return calendarUpdate.getTime();

	}

	public static int daysInMonth(GregorianCalendar c) {
		int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		daysInMonths[1] += c.isLeapYear(c.get(GregorianCalendar.YEAR)) ? 1 : 0;
		return daysInMonths[c.get(GregorianCalendar.MONTH)];
	}

	/**
	 * @param date
	 * @return
	 */
	public static int shenyuDaysInMonth(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int mDays = daysInMonth(calendar);
		return mDays - day;
	}
}
