package net.snake.gamemodel.goods.response;


import net.snake.netio.ServerResponse;


public class GoodScripToBadMsg53052 extends ServerResponse {
public GoodScripToBadMsg53052(int goodId){
	this.setMsgCode(53052);
		this.writeInt(goodId);
}
}
