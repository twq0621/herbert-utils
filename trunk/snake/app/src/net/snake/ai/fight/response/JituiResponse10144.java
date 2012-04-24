package net.snake.ai.fight.response;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.netio.ServerResponse;


/**
 * 击退消息发送
 * @author serv_dev
 *
 */
public class JituiResponse10144 extends ServerResponse {
	public JituiResponse10144(VisibleObject vo) {
		setMsgCode(10144);
		ServerResponse out = this;
		out.writeByte(isCharacter(vo));
		out.writeInt(vo.getId());
		out.writeShort(vo.getX());
		out.writeShort(vo.getY());
	}
	private static byte isCharacter(VisibleObject vo) {
		if (vo == null) return 0;
		return vo.getSceneObjType()==SceneObj.SceneObjType_Hero ? CommonUseNumber.byte1 : (byte) 2;
	}
}
