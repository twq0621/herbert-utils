package net.snake.gamemodel.map.response.hero;

import net.snake.netio.ServerResponse;

public class CharacterLeaveEyeShot10080 extends ServerResponse {
	public CharacterLeaveEyeShot10080(int id,int horseId) {
		setMsgCode(10080);
		writeInt(id);
		writeInt(horseId);
	}
}
