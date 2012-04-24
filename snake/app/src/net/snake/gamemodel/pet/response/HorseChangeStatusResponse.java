package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

public class HorseChangeStatusResponse extends ServerResponse {
	/**
	 * 灵宠IDint)，响应类型(1展示,2收起,3放生)（byte）,tag(byte)0失败{国际化失败原因（str）}/1成功
	 * 之后将场景中添加的灵宠广播给所有玩家
	 * 
	 * @param horseid
	 */
	public HorseChangeStatusResponse(int characterId,int horseModelId,int horseid, int b) {
		setMsgCode(60008);
		writeInt(characterId);
		writeInt(horseModelId);
		writeInt(horseid);
		writeByte(b);
		writeByte(1);
	}
}
