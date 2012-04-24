package net.snake.consts;

public enum TradeStatus {
    idle(0), //空闲
//    initiate(1),//发起
    perTrade(3), //握手后（确认双方进行交易）
    checking(5), //确认 (即锁定)
    trading(10); //交易   
    private int priority;   
    TradeStatus(int priority) {
    	this.priority = priority;
    }
	public int getPriority() {
		return priority;
	}
}
