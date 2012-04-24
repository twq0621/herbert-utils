package net.snake.gamemodel.goods.response;


import net.snake.netio.ServerResponse;


public class CharacterGetGoods11212 extends ServerResponse {
	public CharacterGetGoods11212(int modelid,int count) {
		setMsgCode(11212);
//		try {
			writeInt(modelid);
			writeInt(count);
//		} catch (IOException e) {
//			logger.error(e.getMessage(),e);
//		}
	}
}
