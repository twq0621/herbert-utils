package net.snake.gamemodel.wedding.response;


import net.snake.netio.ServerResponse;


/**
 * 技能附加等级
 * @author serv_dev
 *
 */
public class WeddingMsg52374 extends ServerResponse {
	
	public WeddingMsg52374(int... skill) {
		setMsgCode(52374);
		try {
			writeShort(skill.length / 2);
			for (int i = 0; i < skill.length; i = i + 2) {
				writeInt(skill[i]);
				writeInt(skill[i + 1]);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}
}
