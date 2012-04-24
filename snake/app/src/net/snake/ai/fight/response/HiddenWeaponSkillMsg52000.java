package net.snake.ai.fight.response;
import java.util.Iterator;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.netio.ServerResponse;


/**
 * 暗器技能
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class HiddenWeaponSkillMsg52000 extends ServerResponse {

	public HiddenWeaponSkillMsg52000(int hwId, byte gjType, int gjId, byte num,
			Iterator<VisibleObject> iterator) {
		setMsgCode(52000);
		writeInt(hwId);
		writeByte(gjType);
		writeInt(gjId);
		writeByte(num);
		while (iterator.hasNext()) {
			VisibleObject vo = (VisibleObject) iterator.next();
			writeByte(isCharacter(vo));
			writeInt(vo.getId());
		}
	}
	
	public HiddenWeaponSkillMsg52000(int hwid,byte gjType,int gjId,byte bjType,int bjId){
		setMsgCode(52000);
		writeInt(hwid);
		writeByte(gjType);
		writeInt(gjId);
		writeByte(1);
		writeByte(bjType);
		writeInt(bjId);
	}
	
	private static byte isCharacter(VisibleObject vo) {
		return vo.getSceneObjType()==SceneObj.SceneObjType_Hero ?  CommonUseNumber.byte1 : (byte) 2;
	}
}
