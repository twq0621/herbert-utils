package net.snake.gamemodel.goods.response;

import net.snake.netio.ServerResponse;


/**
 * 古董收集 领取奖励功能
 * 
 * 
 */
public class GetAwardGoodsDCMsg50906 extends ServerResponse {
	/**
	 * @param flag
	 *            标识 这次操作时成功还是失败(失败0,成功1)
	 * @param attribute
	 *            属性{攻击,防御,轻身,健体}
	 * @param msgkey
	 *            国际化key
	 * @param vars
	 *            国际化消息参数
	 */
	public GetAwardGoodsDCMsg50906(int flag,int goodsDcId,int[] attribute, int msgkey,
			String... vars) {
		setMsgCode(50906);
		try {
			writeByte(flag);
			if (flag == 0) {
				writeInterUTF(msgkey,vars);
			}else {
				writeInt(goodsDcId);
				writeShort(attribute[0]);
				writeShort(attribute[1]);
				writeShort(attribute[2]);
				writeShort(attribute[3]);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
}
