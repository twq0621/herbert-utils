package net.snake.gamemodel.goods.response;

import net.snake.netio.ServerResponse;


public class GoodDetailFailMsg50132 extends ServerResponse {

	public GoodDetailFailMsg50132(int characterId,int position,int goodModelId,int horseId) {
		setMsgCode(50132);
		try {
			writeInt(characterId);
			writeShort(position);
			writeInt(goodModelId);
			writeInt(horseId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
}
