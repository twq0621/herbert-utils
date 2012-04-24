package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

/**
 * s-->c 60030 响应
 * 是否可以孵化标示(byte)(0不可以1可以)，1可以孵化{灵宠模型id（int），消耗铜币(int),消耗真元(int),成功几率
 * (int),幸运值(int)}
 * 
 * @author jack
 * 
 */
public class FuhuaCaiLiaoInfoResponse extends ServerResponse {

	/**
	 * 不可以孵化
	 */
	public FuhuaCaiLiaoInfoResponse() {
		setMsgCode(60030);
		writeByte(0);
	}

	/**
	 * 1可以孵化{灵宠模型id（int），消耗铜币(int),消耗真元(int),成功几率(int),幸运值(int)}
	 */
	public FuhuaCaiLiaoInfoResponse(int horseModelId, int copper, int zhenqi, int gailv) {
		setMsgCode(60030);
		writeByte(1);
		writeInt(horseModelId);
		writeInt(copper);
		writeInt(zhenqi);
		writeInt(gailv);
	}

}
