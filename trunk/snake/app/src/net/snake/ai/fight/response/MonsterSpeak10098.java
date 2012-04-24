package net.snake.ai.fight.response;

import java.io.IOException;

import org.apache.log4j.Logger;

import net.snake.netio.ServerResponse;


/**
 * 怪物说话内容发送 
 * @author dev
 *
 */
public class MonsterSpeak10098 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(MonsterSpeak10098.class);
	public MonsterSpeak10098(int monsterid,byte index){
		setMsgCode(10098);
		try {
			sendSpeakBody(monsterid, index);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		
	}
	/**
	 * 怪物说话内容发送
	 * @param body
	 * @throws IOException
	 */
	private void sendSpeakBody(int monsterid,byte index) throws IOException
	{
		writeInt(monsterid);
		writeByte((byte)index);
	}
}
