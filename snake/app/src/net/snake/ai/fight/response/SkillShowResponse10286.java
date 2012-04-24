package net.snake.ai.fight.response;

import java.util.Iterator;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.netio.ServerResponse;

/**
 * 点技能返回消息
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class SkillShowResponse10286 extends ServerResponse {
	private static final int MSGCODE = 10286;

	/**
	 * 
	 * @param ref
	 *            关联技能标识 0普通1关联
	 * @param skillId
	 *            功方技能ID
	 * @param gjType
	 *            功方roleType
	 * @param gjId
	 *            攻方ID
	 * @param bjType
	 *            被功方roleType
	 * @param bjId
	 *            被攻方ID
	 */
	public SkillShowResponse10286(int skillId, FighterVO fighterVO, int num, int bjType, int bjId) {
		int gjType = fighterVO.getSponsor().getSceneObjType();
		int gjId = fighterVO.getSponsor().getId();
		short x = fighterVO.getX();
		short y = fighterVO.getY();
		setMsgCode(MSGCODE);
		writeInt(skillId);
		writeByte(gjType);
		writeInt(gjId);
		writeShort(x);
		writeShort(y);
		writeByte(num);
		writeByte(bjType);
		writeInt(bjId);
	}

	/**
	 * 
	 * @param ref
	 *            关联技能标识 0普通1关联
	 * @param skillId
	 *            功方技能ID
	 * @param gjType
	 *            功方roleType
	 * @param gjId
	 *            攻方ID
	 * @param bjType
	 *            被功方roleType
	 * @param bjId
	 *            被攻方ID
	 */
	public SkillShowResponse10286(FighterVO fighterVO, byte num, Iterator<VisibleObject> iterator) {
		int skillId = fighterVO.getSkill().getId();
		int gjType = fighterVO.getSponsor().getSceneObjType();
		int gjId = fighterVO.getSponsor().getId();
		short x = fighterVO.getX();
		short y = fighterVO.getY();
		setMsgCode(MSGCODE);
		writeInt(skillId);
		writeByte(gjType);
		writeInt(gjId);
		writeShort(x);
		writeShort(y);
		writeByte(num);

		while (iterator.hasNext()) {
			VisibleObject vo = (VisibleObject) iterator.next();
			writeByte(isCharacter(vo));
			writeInt(vo.getId());
		}
	}

	private static byte isCharacter(VisibleObject vo) {
		return vo.getSceneObjType() == SceneObj.SceneObjType_Hero ? CommonUseNumber.byte1 : (byte) 2;
	}

}
