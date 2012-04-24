package net.snake.gamemodel.heroext.lianti.response;


import net.snake.netio.ServerResponse;
import net.snake.netio.message.ResponseMsg;


public class TupoLianTiResultFail53006 extends ServerResponse implements
		ResponseMsg {

	public TupoLianTiResultFail53006(int liantiJingjieId, int msgkey,String... vars) {
		setMsgCode(53006);
		try {
			writeByte(liantiJingjieId);
			writeByte(0);
			writeInterUTF(msgkey,vars);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
