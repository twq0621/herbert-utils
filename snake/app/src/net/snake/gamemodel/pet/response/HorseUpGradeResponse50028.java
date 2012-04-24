package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;

/**
 * 返回请求马的属性升级成功还是失败
 * 
 */
public class HorseUpGradeResponse50028 extends ServerResponse {
	public static byte FAIL = 0;
	public static byte SUCCESS = 1;

	public HorseUpGradeResponse50028(int hourseId, byte type, byte isSucess, int msgKey, String... vars) {
		setMsgCode(50028);
		try {
			this.writeInt(hourseId);
			this.writeByte(type);// 界面ID（type）｛
									// 0恢复活力界面/1提升坐骑品质界面/2提升坐骑悟性界面/3提升攻击潜力/4提升防御潜力/5提升轻身潜力/6提升健体潜力
									// /7炼骨进阶
			this.writeByte(isSucess);
			this.writeInterUTF(msgKey, vars);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
