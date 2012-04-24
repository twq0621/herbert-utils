package net.snake.gamemodel.heroext.lianti.response;


import net.snake.netio.ServerResponse;
import net.snake.netio.message.ResponseMsg;


public class TupoLianTiResultOK53006 extends ServerResponse implements
		ResponseMsg {
	public TupoLianTiResultOK53006(int liantiJingjieId) {
		setMsgCode(53006);
		try {
			writeByte(liantiJingjieId);
			writeByte(1);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}

}
