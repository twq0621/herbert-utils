package net.snake.ai.fight.response;

import org.apache.log4j.Logger;

import net.snake.netio.ServerResponse;


/**
 * 怪物说话内容发送 
 * @author dev
 *
 */
public class MonsterSpeak10298 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(MonsterSpeak10298.class);
	public MonsterSpeak10298(int monsterid,String content){
		setMsgCode(10298);
		try {
			writeInt(monsterid);
			writeUTF(content);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
}
