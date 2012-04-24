package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

public class HorseLivingchange extends ServerResponse {
	public HorseLivingchange(int horseid, int livingness,int livingnessmax) {
		setMsgCode(60042);
		writeInt(horseid);
		writeInt(livingness);
		writeInt(livingnessmax);
	}
}
