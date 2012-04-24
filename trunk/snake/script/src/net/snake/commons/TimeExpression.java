package net.snake.commons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 时间表达式
 * 
 * @author serv_dev
 * 
 */
public class TimeExpression {
	Expression[] expressions;


	private static final String help = "时间表达式格式 为  [年][月][日][时间段];[年][月][日][时间段];....\r\n用\";\"隔开并列的表达式, 每个\"[]\"里都可以用\"*\"表示不对该日期段进行限制,用\",\"表示并列,用\"-\"表示从哪儿到哪儿\r\n	例 [*][5,6,8-11][1,3,5,7][1-2:30,3:00-5:00]\r\n表示每5月,6月,8月至11月,每月的1号3号5号,7号,在这些限定日期内的每日的1:00-2:30,3:00-5:00\r\n例[*][*][1,3,5,7][*];[*][*][2,4,6,8][10-20:30]\r\n表示1号3号5号7号的任意时间和2号4号6号8号的10:00-20:30\r\n";

	static public String getHelp() {
		return help;

	}

	public TimeExpression(String experssions) {

		experssions = experssions.trim();
		if (experssions.endsWith(";")) {
			experssions = experssions.substring(0, experssions.length() - 1);
		}
		String[] exarray = experssions.split(";");
		Expression[] expressionArray = new Expression[exarray.length];
		for (int i = 0; i < exarray.length; i++) {
			expressionArray[i] = new Expression(exarray[i]);
		}
		expressions = expressionArray;
	}

	public boolean isExpressionTime(long time) {
		for (Expression item : expressions) {
			if (item.isValidateTime(time)) {
				return true;
			}
		}
		return false;
	}

	private boolean isExpressionDate(long time) {
		for (Expression item : expressions) {
			if (item.isValidateDate(time)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAllYear() {
		for (Expression item : expressions) {
			if (item.isAllYear()) {
				return true;
			}
		}
		return false;
	}

	// 获取刷新时间点
	public String getStart(long time) {
		// String s="";
		List<String> l = new ArrayList<String>();
		for (Expression item : expressions) {
			List<String> horseExp = item.getHourExp(time);
			if (horseExp.contains("*")) {
				return "即时";
			}
			for (String string : horseExp) {
				if (!l.contains(string)) {
					l.add(string);
				}
			}
		}
		Collections.sort(l);
		if (l.size() > 0) {
			return l.get(0);
		} else {
			return "当日不刷新";
		}
	}

	private static class Expression {
		String[] years;
		String[] months;
		String[] days;
		String[] hours;

		Expression(String experssion) {
			experssion = experssion.substring(1, experssion.length() - 1);
			String[] t = experssion.split("\\]\\[");
			if (t.length != 4) {
				throw new IllegalStateException();
			}
			years = t[0].split(",");
			months = t[1].split(",");
			days = t[2].split(",");
			hours = t[3].split(",");
			
			if (years.length == 0 || months.length == 0 || days.length == 0 || hours.length == 0) {
				throw new IllegalStateException();
			}
		}

		public boolean isValidateTime(long time) {
			if (isValidateDate(time)) {
				Calendar ca = new GregorianCalendar();
				ca.setTimeInMillis(time);
				int hour = ca.get(Calendar.HOUR_OF_DAY);
				int minute = ca.get(Calendar.MINUTE);
				int secend = ca.get(Calendar.SECOND);
				int millsecend = ca.get(Calendar.MILLISECOND);
				int hourtime = hour * 60 * 60 * 1000 + minute * 60 * 1000 + secend * 1000 + millsecend;
				if (!isinhours(hours, hourtime)) {
					return false;
				}
				return true;
			} else {
				return false;
			}
		}

		private boolean isinarray(String[] array, int value) {
			for (String item : array) {
				if (item.equals("*")) {
					return true;
				}
				if (item.indexOf("-") != -1) {// 数字段的
					String[] seg = item.split("-");
					if (value >= Integer.parseInt(seg[0]) && value <= Integer.parseInt(seg[1])) {
						return true;
					}
				} else if (item.equals(value + "")) {
					return true;
				}
			}
			return false;
		}

		private boolean isweek(String[] array, int value) {
			for (String string : array) {
				if (string.startsWith("w") || string.startsWith("W")) {
					string = string.substring(1, string.length());
				}
				if (string.equals("*")) {
					return true;
				}
				if (string.indexOf("-") != -1) {// 数字段的
					String[] seg = string.split("-");
					if (value >= Integer.parseInt(seg[0]) && value <= Integer.parseInt(seg[1])) {
						return true;
					}
				} else if (string.equals(value + "")) {
					return true;
				}
			}
			return false;
		}

		// 获取当前日表达式执行的时间点
		public List<String> getHourExp(long time) {
			List<String> l = new ArrayList<String>();
			if (isValidateDate(time)) {
				GregorianCalendar ca = new GregorianCalendar();
				int hour = ca.get(Calendar.HOUR_OF_DAY);
				int minute = ca.get(Calendar.MINUTE);
				int secend = ca.get(Calendar.SECOND);
				int millsecend = ca.get(Calendar.MILLISECOND);
				int hourtime = hour * 60 * 60 * 1000 + minute * 60 * 1000 + secend * 1000 + millsecend;
				for (String item : hours) {
					if (item.equals("*")) {
						l.clear();
						l.add("*");
						return l;
					}
					if (item.indexOf("-") != -1) {// 数字段的
						String[] seg = item.split("-");
						String[] start = seg[0].split(":");
						String[] end = seg[1].split(":");
						// String exp=start[0];
						int starthour = Integer.parseInt(start[0]) * 60 * 60 * 1000;
						if (start.length > 1) {
							starthour = starthour + Integer.parseInt(start[1]) * 60 * 1000;
						}
						int endhour = Integer.parseInt(end[0]) * 60 * 60 * 1000;
						if (end.length > 1) {
							endhour = endhour + Integer.parseInt(end[1]) * 60 * 1000;
						}
						if (hourtime <= starthour) {
							l.add(seg[0]);
						}
					}
				}
			}
			return l;
		}

		// 当前日期内是否执行
		public boolean isValidateDate(long time) {
			Calendar ca = new GregorianCalendar();
			ca.setTimeInMillis(time);
			
			int year = ca.get(Calendar.YEAR);
			if (!isinarray(years, year)) {
				return false;
			}
			int month = ca.get(Calendar.MONTH) + 1;
			if (!isinarray(months, month)) {
				return false;
			}
			if (days[0].startsWith("w") || days[0].startsWith("W")) {
				int week = ca.get(Calendar.DAY_OF_WEEK) - 1;
				if (week == 0) {
					week = 7;
				}
				if (!isweek(days, week)) {
					return false;
				}
			} else {
				int day = ca.get(Calendar.DAY_OF_MONTH);
				if (!isinarray(days, day)) {
					return false;
				}
			}
			return true;
		}

		private boolean isinhours(String[] array, int checkhourtime) {
			for (String item : array) {
				if (item.equals("*")) {
					return true;
				}
				// float hourtime=hours+minutes/(float)60;
				if (item.indexOf("-") != -1) {// 数字段的
					String[] seg = item.split("-");
					String[] start = seg[0].split(":");
					String[] end = seg[1].split(":");
					int starthour = Integer.parseInt(start[0]) * 60 * 60 * 1000;
					if (start.length > 1) {
						starthour = starthour + Integer.parseInt(start[1]) * 60 * 1000;
					}
					int endhour = Integer.parseInt(end[0]) * 60 * 60 * 1000;
					if (end.length > 1) {
						endhour = endhour + Integer.parseInt(end[1]) * 60 * 1000;
					}
					if (checkhourtime >= starthour && checkhourtime <= endhour) {
						return true;
					}
				}
			}
			return false;
		}

		private boolean isAllYear() {
			for (String year : years) {
				if ("*".equals(year)) {
					return true;
				}
			}
			return false;
		}
	}


	public static Date getNextDay(TimeExpression expression, long time) {
		if (!expression.isAllYear()) {
			return null;
		}
		Calendar recordCalendar = Calendar.getInstance();
		recordCalendar.setTimeInMillis(time);
		recordCalendar.set(Calendar.HOUR, 0);
		recordCalendar.set(Calendar.MINUTE, 0);
		recordCalendar.set(Calendar.SECOND, 0);
		recordCalendar.add(Calendar.DATE, 1);
		int i = 0;
		while (i <= 365) {
			if (expression.isExpressionDate(recordCalendar.getTimeInMillis())) {
				return recordCalendar.getTime();
			} else {
				recordCalendar.add(Calendar.DATE, 1);
				i++;
			}
		}
		return null;
	}

	/**
	 * 今天是否符合
	 * @param theDay
	 * @return
	 */
	public static boolean isToday(Date theDay) {
		Calendar recordCalendar = new GregorianCalendar();// 当前记录的日期
		Calendar completeCalendar = new GregorianCalendar();// 比较的日期
		recordCalendar.setTime(theDay);
		completeCalendar.setTime(new Date());
		if (recordCalendar.get(Calendar.YEAR) != completeCalendar.get(Calendar.YEAR) 
				|| recordCalendar.get(Calendar.MONTH) != completeCalendar.get(Calendar.MONTH)
				|| recordCalendar.get(Calendar.DATE) != completeCalendar.get(Calendar.DATE)) {
			return false;
		} else {
			return true;
		}
	}

}
