package net.snake.commons;

import java.util.Calendar;

public class TimeExpII {
	private Exp[] exps = null;

	public TimeExpII(String value) {
		String[] expArray = value.split(";");
		exps = new Exp[expArray.length];
		for (int i = 0; i < expArray.length; i++) {
			exps[i] = new Exp(expArray[i]);
		}
	}

	public boolean isOK(long timestamp) {
		Calendar time = Calendar.getInstance();

		for (int i = 0; i < exps.length; i++) {
			boolean flag = exps[i].inYears(time);
			if (!flag) {
				continue;
			}
			flag = exps[i].inMonthes(time);
			if (!flag) {
				continue;
			}
			flag = exps[i].inDays(time);
			if (!flag) {
				continue;
			}
			flag = exps[i].inMinutes(time);
			if (!flag) {
				continue;
			}
			return true;
		}
		return false;
	}

	public String nextTime(long timestamp) {

		return null;
	}

//	public static void main(String[] args) {
//		String value = "[2012][*][2,3,13-23][12:00-13:00]";
//		System.err.println(new TimeExpII(value).isOK(System.currentTimeMillis()));
//	}

	private static class Exp {
		private int[] years = null;
		private int[] monthes = null;
		private int[] days = null;
		private int[] times = null;

		Exp(String exp) {
			String[] fields = new String[4];

			int field_index = 0;
			char[] chars = exp.toCharArray();
			int begin = 1;
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == ']') {
					fields[field_index] = new String(chars, begin, i - begin);
					field_index++;
					begin = i + 2;
				}
			}

			if (!fields[0].equals("*")) {
				String[] y = fields[0].split(",");
				years = new int[y.length * 2];
				for (int i = 0; i < y.length; i++) {
					if (y[i].indexOf("-") == -1) {
						years[i * 2] = Integer.parseInt(y[i]);
						years[i * 2 + 1] = Integer.parseInt(y[i]);
					} else {
						String[] between = y[i].split("-");
						years[i * 2] = Integer.parseInt(between[0]);
						years[i * 2 + 1] = Integer.parseInt(between[1]);
					}
				}
			}

			if (!fields[1].equals("*")) {
				String[] m = fields[1].split(",");
				monthes = new int[m.length * 2];
				for (int i = 0; i < m.length; i++) {
					if (m[i].indexOf("-") == -1) {
						monthes[i * 2] = Integer.parseInt(m[i]);
						monthes[i * 2 + 1] = Integer.parseInt(m[i]);
					} else {
						String[] between = m[i].split("-");
						monthes[i * 2] = Integer.parseInt(between[0]);
						monthes[i * 2 + 1] = Integer.parseInt(between[1]);
					}
				}
			}

			if (!fields[2].equals("*")) {
				String[] d = fields[2].split(",");
				days = new int[d.length * 2];
				for (int i = 0; i < d.length; i++) {
					if (d[i].indexOf("-") == -1) {
						days[i * 2] = Integer.parseInt(d[i]);
						days[i * 2 + 1] = Integer.parseInt(d[i]);
					} else {
						String[] between = d[i].split("-");
						days[i * 2] = Integer.parseInt(between[0]);
						days[i * 2 + 1] = Integer.parseInt(between[1]);
					}
				}
			}

			if (!fields[3].equals("*")) {
				String[] mint = fields[3].split(",");
				times = new int[mint.length * 2];
				for (int i = 0; i < mint.length; i++) {
					if (mint[i].indexOf("-") == -1) {
						String[] hm = mint[i].split(":");
						int hour = Integer.parseInt(hm[0]);
						int mm = Integer.parseInt(hm[1]);

						int mms = hour * 60 + mm;
						times[i * 2] = mms;
						times[i * 2 + 1] = mms;
					} else {
						String[] unitStr = mint[i].split("-");

						String[] hm = unitStr[0].split(":");
						int hour = Integer.parseInt(hm[0]);
						int mm = Integer.parseInt(hm[1]);
						int mms = hour * 60 + mm;
						times[i * 2] = mms;

						hm = unitStr[1].split(":");
						hour = Integer.parseInt(hm[0]);
						mm = Integer.parseInt(hm[1]);

						mms = hour * 60 + mm;
						times[i * 2 + 1] = mms;
					}
				}
			}

		}

		private boolean inYears(Calendar date) {
			if (years == null) {
				return true;
			}
			int year = date.get(Calendar.YEAR);
			for (int i = 0; i < years.length; i = i + 2) {
				if (year < years[i] || year > years[i + 1]) {
					continue;
				}
				return true;
			}
			return false;
		}

		private boolean inMonthes(Calendar date) {
			if (monthes == null) {
				return true;
			}
			int month = date.get(Calendar.MONTH) + 1;
			for (int i = 0; i < monthes.length; i = i + 2) {
				if (month < monthes[i] || month > monthes[i + 1]) {
					continue;
				}
				return true;
			}
			return false;
		}

		private boolean inDays(Calendar date) {
			if (days == null) {
				return true;
			}
			int day = date.get(Calendar.DAY_OF_MONTH);
			for (int i = 0; i < days.length; i = i + 2) {
				if (day < days[i] || day > days[i + 1]) {
					continue;
				}
				return true;
			}
			return false;
		}

		private boolean inMinutes(Calendar date) {
			if (times == null) {
				return true;
			}
			int hour = date.get(Calendar.HOUR_OF_DAY);
			int minute = date.get(Calendar.MINUTE);
			int minutes = hour * 60 + minute;
			for (int i = 0; i < times.length; i = i + 2) {
				if (minutes < times[i] || minutes > times[i + 1]) {
					continue;
				}
				return true;
			}
			return false;
		}

		@SuppressWarnings("unused")
		private int nextMinute(Calendar date) {
			if (times == null) {
				return -1;
			}

			int hour = date.get(Calendar.HOUR_OF_DAY);
			int minute = date.get(Calendar.MINUTE);
			int minutes = hour * 60 + minute;
			for (int i = 0; i < times.length; i = i + 2) {
				if (minutes < times[i]) {
					return times[i];
				}
				continue;
			}
			return times[0];
		}

		@SuppressWarnings("unused")
		private int nextDay(Calendar date, boolean next) {
			int day = date.get(Calendar.DAY_OF_MONTH);
			if (days == null) {
				return day;
			}

			if (next) {
				day++;
			}
			for (int i = 0; i < days.length; i = i + 2) {
				if (day < days[i]) {
					return days[i];
				} else if (day < days[i + 1]) {
					return day;
				}
				continue;
			}
			return days[0];
		}

		@SuppressWarnings("unused")
		private int nextMonth(Calendar date, boolean next) {
			int month = date.get(Calendar.MONTH) + 1;
			if (monthes == null) {
				return month;
			}

			if (next) {
				month++;
			}
			for (int i = 0; i < monthes.length; i = i + 2) {
				if (month < monthes[i]) {
					return monthes[i];
				} else if (month < monthes[i + 1]) {
					return month;
				}
				continue;
			}
			return monthes[0];
		}

		@SuppressWarnings("unused")
		private int nextyear(Calendar date, boolean next) {
			int year = date.get(Calendar.YEAR);
			if (years == null) {
				return year;
			}
			if (next) {
				year++;
			}

			for (int i = 0; i < years.length; i = i + 2) {
				if (year < years[i]) {
					return years[i];
				} else if (year < years[i + 1]) {
					return year;
				}
				continue;
			}
			return years[0];
		}

	}

	public boolean isAllYear() {
		for (int i = 0; i < exps.length; i++) {
			if (exps[i].years == null) {
				return true;
			}
		}
		return false;
	}
}
