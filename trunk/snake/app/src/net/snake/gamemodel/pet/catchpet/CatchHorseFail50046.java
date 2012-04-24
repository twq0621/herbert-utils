package net.snake.gamemodel.pet.catchpet;

import net.snake.netio.ServerResponse;


public class CatchHorseFail50046 extends ServerResponse {
//	public CatchHorseFail50046(int horseid,String resean) {
//		setMsgCode(50046);
//		try {
//			writeInt(horseid);
//			writeByte(0);
//			writeUTF(resean);
//		} catch (IOException e) {
//			logger.error(e.getMessage(),e);
//		}
//	}
	public CatchHorseFail50046(int horseid,int msgKey) {
		setMsgCode(50046);
		try {
			writeInt(horseid);
			writeByte(0);
			writeInterUTF(msgKey);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
