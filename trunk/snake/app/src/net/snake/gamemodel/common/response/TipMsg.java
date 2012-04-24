package net.snake.gamemodel.common.response;

import net.snake.netio.ServerResponse;

public class TipMsg extends ServerResponse {
	/** 左聊天框中 */
	public static final int MSGPOSITION_LEFTCHAT = 1;//
	/** 系统提示(右侧) */
	public static final int MSGPOSITION_RIGHT = 2;//
	/** 弹出ALERT消息 */
	public static final int MSGPOSITION_ALERT = 3;//
	/** 系统公告(滚动) */
	public static final int MSGPOSITION_SYSBROADCAST = 4;//
	/** 中间明显提示区(角色脚下) */
	public static final int MSGPOSITION_ERRORTIP = 5;//
	/** 系统公告(切出) */
	public static final int QICHU_MSG = 6;//
	/** 中间明显提示区(BOSS喊话等) */
	public static final int MSGPOSITION_JAVASCRIP = 7;//

	public TipMsg(int MSGPOSITON, String msg) {
		setMsgCode(22222);
		try {
			writeUTF(String.valueOf(MSGPOSITON));
			writeUTF(msg);
			if (MSGPOSITON == 4) {
				writeUTF(String.valueOf(System.currentTimeMillis()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
