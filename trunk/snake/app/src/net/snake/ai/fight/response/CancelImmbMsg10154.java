package net.snake.ai.fight.response;

import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.netio.ServerResponse;

/**
 * 取消定身消息
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class CancelImmbMsg10154 extends ServerResponse {

	public CancelImmbMsg10154(int effectType, VisibleObject vo) {
		setMsgCode(10154);
		int visibaleType = 1;
		if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			visibaleType = 1;
		} else if (vo.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
			visibaleType = 2;
		} else if (vo.getSceneObjType() == SceneObj.SceneObjType_Horse) {
			visibaleType = 3;
		}

		writeByte(effectType);
		writeByte(visibaleType);
		writeInt(vo.getId());
	}
}
