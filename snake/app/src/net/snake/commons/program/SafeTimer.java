package net.snake.commons.program;

public class SafeTimer {
	private long startTime;
	/** 间隔时间，单位为毫秒 */
	private long interval;//
	boolean isfirst = true;

	public SafeTimer() {
	}

	public SafeTimer(long dur) {
		start(dur);
	}

	public void changeInterval(long dur) {
		this.interval = dur;
	}

	public void start(long dur) {
		this.interval = dur;
		startTime = System.currentTimeMillis();
		isfirst = true;
	}

	/**
	 * 
	 * @param dur
	 *            间隔时间
	 * @param elapse
	 *            时间偏差
	 * @param now
	 *            当前时间
	 */
	private void restart(long dur, long elapse, long now) {
		this.interval = dur;
		startTime = now - elapse;
	}

	public boolean isOK() {
		long curTime = System.currentTimeMillis();
		if (curTime - startTime >= interval) {
			return true;
		}
		return false;
	}

	public boolean isFirstOK(long now) {
		if (isfirst && now - startTime >= interval) {
			isfirst = false;
			return true;
		}
		return false;
	}

	/**
	 * 是否到了固定的间隔
	 * 
	 * @param now
	 * @return
	 */
	public boolean isIntervalOK(long now) {
		if (now - startTime >= interval) {
			restart(interval, now - startTime - interval, now);
			return true;
		}
		return false;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

}
