package net.snake.gamemodel.onhoor.response;

import net.snake.netio.ServerResponse;

/**
 * 
 * @author serv_dev
 * 
 */
public class UpdateAfkTimeResponse50590 extends ServerResponse {

	/**
	 * 
	 * @param type
	 *            0其他设置中的挂机剩余时间1真气剩余时间2经验剩余时间3坐骑经验剩余时间(byte)
	 * @param time
	 *            毫秒
	 */
	public UpdateAfkTimeResponse50590(int type, long time) {
		setMsgCode(50590);
		writeByte(type);
		long nowtime = time / 60000;
		// 小于1分钟取得上限1分钟
		writeInt((int) (nowtime == 0 ? (time > 0 ? 1 : 0) : nowtime));

	}
}
