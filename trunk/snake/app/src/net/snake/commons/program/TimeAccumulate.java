package net.snake.commons.program;

//时间累加器
public class TimeAccumulate {
	private long startTime;
	private long accumulateTime;// 已经累加出的毫秒
	private long maxAccumulateValue;// 已经累加出的毫秒

	public long getAccumulateTime() {
		return accumulateTime;
	}

	public TimeAccumulate(long maxAccumulateValue) {
		this.maxAccumulateValue = maxAccumulateValue;
		this.startTime = System.currentTimeMillis();

	}

	public TimeAccumulate(long maxAccumulateValue, long now, long currentAccumulateTime) {
		this.maxAccumulateValue = maxAccumulateValue;
		this.startTime = now;
		this.accumulateTime = currentAccumulateTime;

	}

	public void setStartTime(long startime) {
		this.startTime = startime;
	}

	public boolean isAccumulateTimeOK(long curTime) {
		accumulateTime = accumulateTime + curTime - startTime;
		startTime = curTime;
		if (accumulateTime >= maxAccumulateValue) {
			accumulateTime = maxAccumulateValue - accumulateTime;
			return true;
		}
		return false;
	}

}
