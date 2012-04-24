package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

/**
 * 获得灵宠孵化信息
 * 
 * @author jack
 * 
 */
public class GetFuhuaInfoResponse extends ServerResponse {

	/**
	 * (byte) 0没有正在孵化中的灵宠
	 * 
	 * @param character
	 */
	public GetFuhuaInfoResponse() {

		setMsgCode(60024);
		writeByte(0);
	}

	/**
	 * 3孵化冷却中{剩余时间(int),总时间(int)}
	 * 
	 * @param totalTime
	 * @param time
	 */
	public GetFuhuaInfoResponse(int b, int time, int totalTime) {
		setMsgCode(60024);
		writeByte(b);
		writeInt(time);
		writeInt(totalTime);
	}

	/**
	 *  1有正在孵化的灵宠{内丹ID(int),孵化成品ID(int),剩余时间(int),总时间(int)}
	 * @param neidanId
	 * @param horseId
	 * @param time
	 * @param totalTime
	 */
	public GetFuhuaInfoResponse(int neidanId, int horseId, int time, int totalTime) {
		setMsgCode(60024);
		writeByte(1);
		writeInt(neidanId);
		writeInt(horseId);
		writeInt(time);
		writeInt(totalTime);
	}
}
