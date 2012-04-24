package net.snake.ai.fight.response;

import java.util.Iterator;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.netio.ServerResponse;

/**
 * 攻击结果发送 10086
 * 
 * @author serv_dev
 * 
 */
public class SkillShowResponse extends ServerResponse {
	private static final int MSGCODE = 10086;

	public SkillShowResponse(int skillId, int skillgrade, int gjType, int gjId, int bjType, int bjId) {
		setMsgCode(MSGCODE);
		writeByte(1);
		writeInt(skillId);
		writeShort(skillgrade);
		writeByte(gjType);
		writeInt(gjId);
		writeByte(1);
		writeByte(bjType);
		writeInt(bjId);
	}

	/**
	 * 技能释放失败的原因
	 * 
	 * @param failFlag
	 *            1.攻击无效2.冷却时间3.蓝不足4.未命中5血不足6体力不足7公共时间冷却8跳闪
	 */
	public SkillShowResponse(int failFlag) {
		setMsgCode(MSGCODE);
		writeByte(0);
		writeByte(failFlag);
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
	public SkillShowResponse(FighterVO fighterVO) {
		int skillId = fighterVO.getSkill().getId();
		int skillgrade = fighterVO.getCharacterSkill().getGrade();
		int gjType = fighterVO.getSponsor().getSceneObjType();
		int gjId = fighterVO.getSponsor().getId();
		int bjType = fighterVO.getRecipient().getSceneObjType();
		int bjId = fighterVO.getRecipient().getId();
		setMsgCode(MSGCODE);
		writeByte(1);
		writeInt(skillId);
		writeShort(skillgrade);
		writeByte(gjType);
		writeInt(gjId);
		writeByte(1);
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
	public SkillShowResponse(FighterVO fighterVO, byte num, Iterator<VisibleObject> iterator) {
		int skillId = fighterVO.getSkill().getId();
		int skillgrade = fighterVO.getCharacterSkill().getGrade();
		int gjType = fighterVO.getSponsor().getSceneObjType();
		int gjId = fighterVO.getSponsor().getId();
		setMsgCode(MSGCODE);
		writeByte(1);
		writeInt(skillId);
		writeShort(skillgrade);
		writeByte(gjType);
		writeInt(gjId);
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
