package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

/**
 * s-->c 60034 相应 灵宠ID(int),当前活力(int),最大活力(int)
 * 
 * @author jack
 * 
 */
public class HorseLivingnessRestorationResponse extends ServerResponse {

	public HorseLivingnessRestorationResponse(int horseId, int livingness, int maxLivingness) {
		setMsgCode(60034);
		writeInt(horseId);
		writeInt(livingness);
		writeInt(maxLivingness);
	}
}
