package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

public class GetNeidanInfoResponse extends ServerResponse {

	/**
	 * 灵宠ID(int)，状态（1展示,2收起）,生产内丹(id),剩余时间秒为单位(int),经验(int),下一级经验(int)
	 * 
	 * @param horse
	 */
	public GetNeidanInfoResponse(int horseid, int status, int neidanId, int time, int exp, int nextexp) {
		setMsgCode(60026);
		writeInt(horseid);
		writeByte(status);
		writeInt(neidanId);
		writeInt(time);
		writeInt(exp);
		writeInt(nextexp);
	}
}
