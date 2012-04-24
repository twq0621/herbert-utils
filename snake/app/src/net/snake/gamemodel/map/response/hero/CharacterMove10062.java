package net.snake.gamemodel.map.response.hero;

import java.io.IOException;

import net.snake.ai.astar.PathConvert;
import net.snake.netio.ServerResponse;

public class CharacterMove10062 extends ServerResponse {
	public CharacterMove10062(int roleid, short[] path) {
		this(roleid, path, 0);
	}

	public CharacterMove10062(int roleid, short[] path, int index) {
		setMsgCode(10062);
		try {
			writeInt(roleid);
			PathConvert.writeToPathArray(path, index, this);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
