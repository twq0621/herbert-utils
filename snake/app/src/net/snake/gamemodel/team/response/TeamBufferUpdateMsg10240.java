package net.snake.gamemodel.team.response;

import net.snake.netio.ServerResponse;
/**
 * 组队广播玩家持续效果列表 
 * @author serv_dev
 *
 */
public class TeamBufferUpdateMsg10240 extends ServerResponse {
	public TeamBufferUpdateMsg10240(int characterId,byte num,int effectId,int time){
		setMsgCode(10240);
		try {
			ServerResponse out = this;
			out.writeInt(characterId);
			out.writeByte(num);
//			out.writeUTF(effectId);
			out.writeInt(time);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
