/**
 * 
 */
package net.snake.script.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

/**
 * 时间工具
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */

public class DateUtile {
	private static final Logger logger = Logger.getLogger(DateUtile.class);
	/**
	 * 时间转化
	 * 
	 * @return
	 */
	public static Calendar dateToCalendar(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}
	public static Date stringToDate(String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		return null;
	}
}
