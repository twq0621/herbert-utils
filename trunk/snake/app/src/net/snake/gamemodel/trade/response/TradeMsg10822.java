package net.snake.gamemodel.trade.response;


import net.snake.netio.ServerResponse;


/**
 * 申请交易是否失败
 *
 */
public class TradeMsg10822 extends ServerResponse{
public TradeMsg10822(int characterId){
	this.setMsgCode(10822);
	try {
		this.writeByte(1);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
public TradeMsg10822(int characterId,String str){
	this.setMsgCode(10822);
	try {
		this.writeByte(1);
		this.writeUTF(str);
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}
}
