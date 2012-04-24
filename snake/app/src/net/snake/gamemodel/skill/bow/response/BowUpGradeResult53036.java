package net.snake.gamemodel.skill.bow.response;

import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 */
public class BowUpGradeResult53036 extends ServerResponse {

	public BowUpGradeResult53036(int i, int now, int luck, int totalluck, int zhufuluck) {
		setMsgCode(53036);
		writeByte(i);
		writeByte(now);
		writeByte(luck);
		writeInt(totalluck);
		writeInt(zhufuluck);
	}
}
