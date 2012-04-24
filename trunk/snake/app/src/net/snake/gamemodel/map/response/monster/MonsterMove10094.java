package net.snake.gamemodel.map.response.monster;

import java.io.IOException;

import net.snake.ai.astar.PathConvert;
import net.snake.netio.ServerResponse;


public class MonsterMove10094 extends ServerResponse {
	public MonsterMove10094(int monsterid, short[] path,int index) {
		setMsgCode(10094);
		ServerResponse out = this;
		try {
			out.writeInt(monsterid);
			PathConvert.writeToPathArray(path,index,out);			
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	public MonsterMove10094(int monsterid, short[] path) {
		this(monsterid, path, 0);
	}
	
	
	
}
