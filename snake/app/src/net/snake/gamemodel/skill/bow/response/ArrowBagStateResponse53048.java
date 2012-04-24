package net.snake.gamemodel.skill.bow.response;

import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 */
public class ArrowBagStateResponse53048 extends ServerResponse {
	public ArrowBagStateResponse53048(int bag1type, int bag1count, byte bag1bind, int bag2type, int bag2count, byte bag2bind) {
		setMsgCode(53048);
		writeInt(bag1type);
		writeInt(bag1count);
		writeByte(bag1bind);
		writeInt(bag2type);
		writeInt(bag2count);
		writeByte(bag2bind);
	}
}
