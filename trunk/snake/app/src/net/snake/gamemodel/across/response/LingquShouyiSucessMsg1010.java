package net.snake.gamemodel.across.response;

import java.io.IOException;

import org.apache.log4j.Logger;

import net.snake.gmtool.net.ByteArrayWriter;
import net.snake.gmtool.net.Msg;

public class LingquShouyiSucessMsg1010 extends Msg {
	private static final Logger logger = Logger.getLogger(LingquShouyiSucessMsg1010.class);

	public LingquShouyiSucessMsg1010(int serverId, int oldInitiallycharacterId) {
		this.setFunction(1010);
		ByteArrayWriter out = this.getContentWriter();
		try {
			out.writeInt(serverId);
			out.writeInt(oldInitiallycharacterId);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
