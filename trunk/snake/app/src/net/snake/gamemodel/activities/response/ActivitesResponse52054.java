package net.snake.gamemodel.activities.response;

/**
 * 
 * 
 * @author serv_dev
 */
import net.snake.gamemodel.activities.bean.XianshiActivityReward;
import net.snake.netio.ServerResponse;

public class ActivitesResponse52054 extends ServerResponse {

	public ActivitesResponse52054(byte type) {
		setMsgCode(52054);
		writeByte(type);
		writeByte(1);
	}

	/**
	 * @param xar
	 * @param goodId
	 */
	public ActivitesResponse52054(XianshiActivityReward xar, int goodId) {
		setMsgCode(52054);
		writeInt(xar.getXianshiActivityId());
		writeInt(xar.getId());
		writeInt(goodId);
	}
}
