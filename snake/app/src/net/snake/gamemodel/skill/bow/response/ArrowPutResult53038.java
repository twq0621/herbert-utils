package net.snake.gamemodel.skill.bow.response;

import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 */
public class ArrowPutResult53038 extends ServerResponse {

	public ArrowPutResult53038(byte b, int modelid1, int count1, byte isbind1, int modelid2, int count2, byte isbind2) {
		setMsgCode(53038);
		writeByte(b);
		writeInt(modelid1);
		writeInt(count1);
		writeByte(isbind1);
		writeInt(modelid2);
		writeInt(count2);
		writeByte(isbind2);
	}
}
