package net.snake.gamemodel.trade.response;


import net.snake.netio.ServerResponse;


/**
 *通知玩家交易状态改变	玩家id（int），type（byte）0无交易无锁定状态/1锁定状态，2交易状态	

 * 
 * 
 */
public class TradeSuodingMsg10838 extends ServerResponse {
	public TradeSuodingMsg10838(int charactterId, byte statu) {
		this.setMsgCode(10838);
			this.writeInt(charactterId);
			this.writeByte(statu);
	}
}
