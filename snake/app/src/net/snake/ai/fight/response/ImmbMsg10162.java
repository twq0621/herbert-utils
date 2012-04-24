package net.snake.ai.fight.response;

import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.netio.ServerResponse;

/**
 * 被定身
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class ImmbMsg10162 extends ServerResponse {
	public static final int Effect_Dingshen = 1;
	public static final int Effect_Miangu = 2;
	public static final int Effect_Yinfeng = 3;
	public static final int Effect_Chenshui = 4;
	public static final int Effect_Shiming = 5;

	public ImmbMsg10162(int effectType, VisibleObject vo) {
		setMsgCode(10162);
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
