package net.snake.ai.util;

import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 工具类
 * @author serv_dev
 *
 */
public class ArithmeticUtils {
private static Logger logger = Logger.getLogger(ArithmeticUtils.class);

	public static int shortToInt(short x, short y){
		int result = x;
		result = result << 16;
		result += y;
		return result;
	}
	
	public static short[] intToShort(int bl) {
		short y = (short)(bl & 0xffff);
		short x = (short)(bl >> 16);
		short[] result = new short[]{x,y};
		return result;
	}
	public static Date stringToDate(String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		return null;
	}
	public static String DateToString(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String str=df.format(date);
			return str;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		return null;
	}

}
