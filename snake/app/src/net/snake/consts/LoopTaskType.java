package net.snake.consts;


public enum LoopTaskType {
	day(0),
	week(1),
	upgrade(2);
	
	public int getType(){
		return type;
	}
	private int type;
	private LoopTaskType(int type) {
		this.type = type;
	}
}

