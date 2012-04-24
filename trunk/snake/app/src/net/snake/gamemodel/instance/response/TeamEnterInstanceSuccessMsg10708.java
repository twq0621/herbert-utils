package net.snake.gamemodel.instance.response;

import net.snake.netio.ServerResponse;

public class TeamEnterInstanceSuccessMsg10708 extends ServerResponse {

	public TeamEnterInstanceSuccessMsg10708(int sceneId) {
		this.setMsgCode(10708);
		this.writeInt(sceneId);
	}

}
