package net.snake.ai.fight.response;

import net.snake.netio.ServerResponse;

/**
 * 持续效果
 * 
 * @author serv_dev
 * 
 */
public class SustainEffectResponse extends ServerResponse {
	private static final int MSGCODE = 10150;

	public SustainEffectResponse(byte type, int id, int effectId, int time, int totalTime, int chuliang, int extraTime, int tishi) {
		// logger.info("SustainEffectResponse,byte type="+type+",int id="+id+",int effectId="+effectId+",int time="+time+",int totalTime="+totalTime+",int chuliang="+chuliang+" ,int extraTime="+extraTime+",int tishi="+tishi);
		setMsgCode(MSGCODE);
		writeByte(type);
		writeInt(id);
		writeInt(effectId);
		writeInt(time);
		writeInt(totalTime);
		writeInt(chuliang);
		writeInt(extraTime);
		writeByte(tishi);
	}
}
