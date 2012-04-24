package net.snake.gamemodel.trade.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * S-A（申请交易者）: B（接受交易申请者）是否接受交易申请
 *
 */
public class TradeMsg10826 extends ServerResponse{
public TradeMsg10826(byte type, Hero hisCharacter ){
	this.setMsgCode(10826);
	try {
		this.writeByte(type);
		this.writeInt(hisCharacter.getId());
		this.writeUTF(hisCharacter.getViewName());
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}

}
