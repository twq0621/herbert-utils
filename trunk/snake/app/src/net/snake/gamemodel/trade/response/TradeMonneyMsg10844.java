package net.snake.gamemodel.trade.response;


import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.netio.ServerResponse;


/**
 *金钱存放交易栏中是否成功	金钱类别（byte）1铜币/2元宝,金钱数（int）

 * 
 */
public class TradeMonneyMsg10844 extends ServerResponse {
	public TradeMonneyMsg10844(IMyTradeController mtc) {
		this.setMsgCode(10844);
			this.writeInt(mtc.getCharacter().getId());
			this.writeInt(mtc.getCopper());
			this.writeInt(mtc.getYuanbao());
	}
}
