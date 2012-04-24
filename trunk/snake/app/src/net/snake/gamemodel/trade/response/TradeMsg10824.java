package net.snake.gamemodel.trade.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * 通知B有人向其申请交易 发送申请交易玩家的id 名字
 *
 */
public class TradeMsg10824 extends ServerResponse{
public TradeMsg10824(Hero appChararacter){
	this.setMsgCode(10824);
	try {
		this.writeInt(appChararacter.getId());
		this.writeUTF(appChararacter.getViewName());
	} catch (Exception e) {
		logger.error(e.getMessage(),e);
	}
}

}
