package net.snake.ai.fight.response.dantian;

import java.util.Iterator;
import java.util.List;

import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.netio.ServerResponse;

/**
 * 
 * 功方技能ID(int),攻方ID(int),被攻击方数量(byte){被功方roleType（byte），被攻方ID(int)*}
 * 
 * @author serv_dev
 * 
 */
public class DanTian10408 extends ServerResponse {
	public DanTian10408(int skillId, int charId, int bgjType, int bgjId) {
		setMsgCode(10408);
		writeInt(skillId);
		writeInt(charId);
		writeByte(1);
		writeByte(bgjType);
		writeInt(bgjId);
	}

	public DanTian10408(int skillId, int charId, List<VisibleObject> list) {
		setMsgCode(10408);
		writeInt(skillId);
		writeInt(charId);
		writeByte(list.size());
		for (Iterator<VisibleObject> iterator = list.iterator(); iterator
				.hasNext();) {
			VisibleObject visibleObject = iterator.next();
			writeByte(visibleObject.getSceneObjType());
			writeInt(visibleObject.getId());
		}
	}
}
