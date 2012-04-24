package net.snake.commons.program;

public class IntId {
	private int currentid = 0;
	private int fromid = 0;

	public IntId() {
	}

	/**
	 * 从fromid开始计数
	 * 
	 * @param fromid
	 */
	public IntId(int fromid) {
		this.fromid = fromid;
		this.currentid = fromid;
	}

	public synchronized int getNextId() {
		if (currentid == Integer.MAX_VALUE) {
			currentid = fromid;
		}
		return currentid++;
	}
}
