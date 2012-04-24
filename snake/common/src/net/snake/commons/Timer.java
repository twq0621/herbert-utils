package net.snake.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * 
 * 
 * @author serv_dev
 */
public class Timer {
	private static Logger logger = Logger.getLogger(Timer.class);

	public static int getHour(long oldTime, long newTime) {
		long time = (newTime - oldTime) / 1000;
		return (int) (time / 3600);
	}

	public static long getNow() {
		return System.currentTimeMillis();
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	public static long getMinute(long oldTime, long newTime) {// 将毫秒数换算成x天x时x分x秒x毫秒
		long l = newTime - oldTime;
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);

		return min;
	}

	/**
	 * @param strDate
	 *            传入日期返回年龄（yyyy-MM-dd）
	 * 
	 */

	public static String getYear(String strDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (strDate.length() > 0) {
			try {
				Date d = bartDateFormat.parse(strDate);
				cal.setTime(d);
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
			int year2 = cal.get(Calendar.YEAR);
			int month2 = cal.get(Calendar.MONTH);
			int day2 = cal.get(Calendar.DAY_OF_MONTH);
			int y_c = year - year2;
			int m_c = month - month2;
			int d_c = day - day2;

			if (d_c < 0) {
				m_c -= 1;
			}
			if (m_c < 0) {
				y_c -= 1;
			}

			return "" + y_c;
		} else {
			return "";
		}
	}

	/**
	 * //获取当天时间
	 * 
	 * @param dateformat
	 * @return
	 */
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		return hehe;
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	public static String timeJiSuan(String ksdate, String jsdate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now = df.parse(jsdate);
		java.util.Date date = df.parse(ksdate);
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		// System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		// "上次操作公用"+day+"天"+hour+"小时"+min+"分"+s+"秒"
		return "上次操作公用:" + hour + "小时" + min + "分" + s + "秒";
	}

	public static String timeJiSuanShowFenMiao(String ksdate, String jsdate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now = df.parse(jsdate);
		java.util.Date date = df.parse(ksdate);
		long l = now.getTime() - date.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		// System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		// "上次操作公用"+day+"天"+hour+"小时"+min+"分"+s+"秒"
		return "公用:" + min + "分" + s + "秒";
	}
}
