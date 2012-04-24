package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

/**
 *  s-->c	60032	响应	灵宠ID(int),灵宠当前经验(int),下一级经验(int)
 * @author jack
 *
 */
public class HorseFeedResponse extends ServerResponse {

	public HorseFeedResponse(int horseId,int exp,int nextExp){
		setMsgCode(60032);
		writeInt(horseId);
		writeInt(exp);
		writeInt(nextExp);
	}
}
