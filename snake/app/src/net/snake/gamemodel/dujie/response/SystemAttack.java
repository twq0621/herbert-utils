package net.snake.gamemodel.dujie.response;


import net.snake.commons.script.SRole;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.netio.ServerResponse;

public class SystemAttack extends ServerResponse {
	public SystemAttack() {
		super();
		setMsgCode(10386);
	}

	/**
	 * 技能ID(int),X(short),Y(short),被攻击方数量(byte){被功方roleType（byte），被攻方ID(int)*
	 */
	public void attackInfo(int skill,int x,int y,SRole attacked) {
		writeInt(skill);
		writeShort(x);
		writeShort(y);
		writeByte(1);
		
		writeByte(SceneObj.SceneObjType_Hero);
		writeInt(attacked.getId());
	}
}
