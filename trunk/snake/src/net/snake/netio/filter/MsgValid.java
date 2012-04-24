package net.snake.netio.filter;
/**
 * 
 * @author serv_dev
 *
 */
public class MsgValid {
	/**单位秒**/
	public final static int feifaCountMax = 100; // 
	private int msgTime;
	private long receivedMsgTime;
	/**用户非法请求次数*/
	private int count = 0; // 
	public int countMax;

	public MsgValid(int msgTime) {
		this.receivedMsgTime = System.currentTimeMillis();
		this.msgTime = msgTime;
		this.count = 0;
		this.countMax = feifaCountMax * msgTime / 1000;
		if (this.countMax < 50) {
			countMax = 50;
		}
	}

	/**
	 * 返回消息合法性 0合法，1正常非法 ，2异常非法
	 * 
	 * @return
	 */
	public int isValidMsg() {
		if (msgTime < 1) {
			return 0;
		}
		long now = System.currentTimeMillis();
		if (now - receivedMsgTime > msgTime) {
			receivedMsgTime = now;
			this.count = 0;
			return 0;
		}
		count++;
		if (countMax <= count) {
			return 2;
		}
		return 1;
	}
}
