package net.snake.gamemodel.team.response;

import net.snake.netio.ServerResponse;
/**
 * 组队广播取消buffer效果影响
 * @author serv_dev
 *
 */
public class TeamCancelBufferMsg10242 extends ServerResponse {
	public TeamCancelBufferMsg10242(int characterId, Integer effectId) {
		setMsgCode(10242);
		try {
			ServerResponse out = this;
			out.writeInt(characterId);
//			out.writeUTF(effectId);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
