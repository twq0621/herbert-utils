package net.snake.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class DateFormatUtil {
	private static Logger logger = Logger.getLogger(DateFormatUtil.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String getStringDate(Date date) {
		return sdf.format(date);
	}

	public static String getNowStringDate() {
		return sdf.format(new Date());
	}

	public static Date getDateByString(String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			//logger.error(e.getMessage(), e);
			logger.error(date+"dateformater err. yyyy-MM-dd HH:mm:ss");
			return null;
		}
	}
}
