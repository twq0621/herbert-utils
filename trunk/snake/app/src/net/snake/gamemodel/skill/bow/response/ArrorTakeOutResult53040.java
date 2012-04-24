package net.snake.gamemodel.skill.bow.response;

import net.snake.netio.ServerResponse;

/**
 * 箭支取出结果
 * 
 * @author serv_dev
 */
public class ArrorTakeOutResult53040 extends ServerResponse {

	public ArrorTakeOutResult53040(byte b, Integer integer, Integer integer2, byte c, Integer integer3, Integer integer4, byte d) {
		setMsgCode(53040);
		writeByte(b);
		writeInt(integer);
		writeInt(integer2);
		writeByte(c);
		writeInt(integer3);
		writeInt(integer4);
		writeByte(d);
	}

}
