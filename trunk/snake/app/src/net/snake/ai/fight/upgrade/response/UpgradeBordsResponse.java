package net.snake.ai.fight.upgrade.response;

import net.snake.netio.ServerResponse;



/**
 * 升级广播
 * @author serv_dev
 *
 */
public class UpgradeBordsResponse extends ServerResponse{
	public UpgradeBordsResponse(int characterId) {
		setMsgCode(10502);
			writeInt(characterId);
	}
}
