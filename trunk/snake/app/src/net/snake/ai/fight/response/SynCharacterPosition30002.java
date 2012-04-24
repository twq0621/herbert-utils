package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class SynCharacterPosition30002 extends ServerResponse {
	public SynCharacterPosition30002(int charId,int x,int y) {
		setMsgCode(30002);
		writeInt(charId);
		writeShort(x);
		writeShort(y);
	}
}
