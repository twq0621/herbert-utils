package net.snake.commons.program;

/**
 * 用于实现多少桢判断一次,以减少服务器刷桢负载
 * 
 * @author serv_dev
 * 
 */
public class FrameIgnoreCount {
	private int ignoreframecount = 0;
	private int current = 0;

	public FrameIgnoreCount(int ignoreframecount) {
		this.ignoreframecount = ignoreframecount;
	}

	/**
	 * 是否需要直接返回
	 * 
	 * @return
	 */
	public boolean isNeedReturn() {
		current++;
		if (current > ignoreframecount) {
			current = 0;
			return false;
		}
		return true;
	}

}
