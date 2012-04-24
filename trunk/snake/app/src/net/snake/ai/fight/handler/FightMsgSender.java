package net.snake.ai.fight.handler;

import java.io.IOException;
import java.util.Iterator;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.response.CancelSustainEffectResponse;
import net.snake.ai.fight.response.DirectEffectBroadResponse;
import net.snake.ai.fight.response.HiddenWeaponSkillMsg52000;
import net.snake.ai.fight.response.SkillShowResponse;
import net.snake.ai.fight.response.SkillShowResponse10286;
import net.snake.ai.fight.response.SkillShowResponse20186;
import net.snake.ai.fight.response.SkillShowResponse20190;
import net.snake.ai.fight.response.SkillShowResponse20192;
import net.snake.ai.fight.response.SustainEffectResponse;
import net.snake.ai.fight.response.TribBeiDongSkillMsg10102;
import net.snake.ai.fight.response.ZuHeiSkillMsg10658;
import net.snake.ai.fight.response.arrow.Skill10400;
import net.snake.ai.fight.response.arrow.Skill10402;
import net.snake.ai.fight.response.arrow.Skill10404;
import net.snake.ai.formula.AttackFormula;
import net.snake.consts.EffectType;
import net.snake.consts.SkillId;
import net.snake.consts.SkillStatus;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.netio.message.ResponseMsg;

public class FightMsgSender {

	/**
	 * 被动技能触发消息
	 * 
	 * @param vo
	 * @param skill
	 */
	public static void sendBeiDongSkillMsg(VisibleObject vo, Skill skill) {
		if (skill != null && !skill.isZhudong()) {
			vo.getEyeShotManager().sendMsg(new TribBeiDongSkillMsg10102(vo.getSceneObjType(), vo.getId(), skill.getId()));
		}
	}

	/**
	 * 发送取消持续效果id
	 * 
	 * @param character
	 * @param effect
	 * @throws Exception
	 */
	public static void sendCancelSustainEffectMsg(VisibleObject vo, EffectInfo effect) throws Exception {
		EffectController voEffectController = vo.getEffectController();

		voEffectController.setMainShowEffect(0);
		voEffectController.setMainShowSkillId(0);
		// byte isCharacter = isCharacter(vo);
		byte isCharacter = (byte) vo.getSceneObjType();
		CancelSustainEffectResponse cer = new CancelSustainEffectResponse(isCharacter, vo.getId(), effect.getId());
		if (isCharacter == SceneObj.SceneObjType_Hero) {
			if (effect.isFacade())
				((Hero) vo).sendMsg(cer);
			else
				vo.getEyeShotManager().sendMsg(cer);
		} else {
			if (!effect.isFacade()) {
				vo.getEyeShotManager().sendMsg(cer);
			}
		}
	}

	/**
	 * 技能释放失败
	 * 
	 * @param character
	 * @param failFalg
	 * @param id
	 */
	public static void sendSkillReleaseFailMsg(VisibleObject vo, SkillStatus skillStus) {
		SkillShowResponse response = new SkillShowResponse(skillStus.getStatus());
		vo.sendMsg(response);
	}

	/**
	 * 单一的显示效果
	 * 
	 * @param fighterVO
	 * @throws Exception
	 */
	public static void sendEffectResponse_only(FighterVO fighterVO) {
		Skill skill = fighterVO.getSkill();
		if (skill == null) {
			return;
		}
		int skillId = skill.getId();
		ResponseMsg response = null;
		if (skillId == SkillId.SHE_MO_SKILL_ID) {
			short[] xy = AttackFormula.getLineAttackPoint(fighterVO.getSponsor().getX(), fighterVO.getSponsor().getY(), fighterVO.getRecipient().getX(), fighterVO.getRecipient()
					.getY(), fighterVO.getSponsor());
			if (xy == null) {
				return;
			}
			response = new SkillShowResponse20186(skillId, (byte) fighterVO.getSponsor().getSceneObjType(), fighterVO.getSponsor().getId(), (byte) fighterVO.getRecipient()
					.getSceneObjType(), fighterVO.getRecipient().getId(), xy[0], xy[1]);
			fighterVO.getSponsor().setX(xy[0]);
			fighterVO.getSponsor().setY(xy[1]);
		} else {
			response = new SkillShowResponse(fighterVO);
		}
		fighterVO.getFighter().getEyeShotManager().sendMsg(response);
	}

	/**
	 * 坐骑组合技能
	 * 
	 * @param skillId
	 * @param owner
	 *            主人
	 */
	public static void sendZuHeSkill(int skillId, VisibleObject owner) {
		owner.getEyeShotManager().sendMsg(new ZuHeiSkillMsg10658(skillId, owner.getId()));
	}

	/**
	 * 单一的显示效果
	 * 
	 * @param fighterVO
	 * @throws Exception
	 */
	public static void sendEffectResponse_only2(FighterVO fighterVO) {
		if (fighterVO.getSkill() == null) {
			return;
		}
		VisibleObject vo = fighterVO.getRecipient();
		int num = 0;
		int recipientId = 0;
		int recipientType = 0;
		if (vo != null) {
			num = 1;
			recipientId = vo.getId();
			recipientType = vo.getSceneObjType();
		}
		ResponseMsg response = null;
		Skill skill = fighterVO.getSkill();
		int skillId = skill.getId();
		if (skill.getArrow_way() == 1) {
			response = new SkillShowResponse20190(fighterVO, skill);
		} else if (skill.getArrow_way() == 2) {
			response = new SkillShowResponse20192(fighterVO, skillId);
		} else {
			response = new SkillShowResponse10286(skillId, fighterVO, num, recipientType, recipientId);
		}
		fighterVO.getFighter().getEyeShotManager().sendMsg(response);
	}

	/**
	 * 群体的显示效果
	 * 
	 * @param num
	 *            群体数量
	 * @param effect
	 *            效果
	 * @param attacker
	 *            攻击者
	 * @param iterator
	 *            群体的迭代器
	 * @param scene
	 *            场景
	 * @throws Exception
	 */
	public static void sendEffectResponse_Mul(int num, FighterVO fighterVO, Iterator<VisibleObject> iterator) {

		if (fighterVO.getSkill() != null) {
			SkillShowResponse response = new SkillShowResponse(fighterVO, (byte) num, iterator);
			fighterVO.getFighter().getEyeShotManager().sendMsg(response);
		}
	}

	/**
	 * 暗器多体攻击消息
	 * 
	 * @param hwid
	 * @param num
	 * @param fighterVO
	 * @param iterator
	 */
	public static void sendHiddenWeapon_Mul(int hwid, int num, FighterVO fighterVO, Iterator<VisibleObject> iterator) {
		if (hwid != 0) {
			HiddenWeaponSkillMsg52000 hwsm = new HiddenWeaponSkillMsg52000(hwid, (byte) fighterVO.getSponsor().getSceneObjType(), fighterVO.getSponsor().getId(), (byte) num,
					iterator);
			fighterVO.getFighter().getEyeShotManager().sendMsg(hwsm);
		}
	}

	/**
	 * 暗器单体攻击消息
	 * 
	 * @param hwid
	 * @param fighterVO
	 */
	public static void sendHiddenWeapon_only(int hwid, FighterVO fighterVO) {
		if (hwid != 0) {
			HiddenWeaponSkillMsg52000 response = new HiddenWeaponSkillMsg52000(hwid, (byte) fighterVO.getSponsor().getSceneObjType(), fighterVO.getSponsor().getId(),
					(byte) fighterVO.getRecipient().getSceneObjType(), fighterVO.getRecipient().getId());
			fighterVO.getFighter().getEyeShotManager().sendMsg(response);
		}
	}

	/**
	 * 群体的显示效果
	 * 
	 * @param num
	 *            群体数量
	 * @param effect
	 *            效果
	 * @param attacker
	 *            攻击者
	 * @param iterator
	 *            群体的迭代器
	 * @param scene
	 *            场景
	 * @throws Exception
	 */
	public static void sendEffectResponse_Mul2(int num, FighterVO fighterVO, Iterator<VisibleObject> iterator) {
		if (fighterVO.getSkill() != null) {
			ResponseMsg response = null;
			Skill skill = fighterVO.getSkill();
			int skillId = skill.getId();
			if (skill.getArrow_way() == 1) {
				response = new SkillShowResponse20190(fighterVO, skill);
			} else if (skill.getArrow_way() == 2) {
				response = new SkillShowResponse20192(fighterVO, skillId);
			} else {
				response = new SkillShowResponse10286(fighterVO, (byte) num, iterator);
			}
			fighterVO.getFighter().getEyeShotManager().sendMsg(response);
		}
	}

	/**
	 * 直接伤害广播
	 * 
	 * @param vo1
	 *            施加者
	 * @param vo
	 *            被施加者
	 * @param type
	 *            影响类型(血量、魔法、怒气)
	 * @param baoji
	 *            是否为暴击
	 * @throws Exception
	 */
	public static void directHurtBroadCase(VisibleObject vo1, VisibleObject vo, int dodge, int baoji) {
		DirectEffectBroadResponse msg = new DirectEffectBroadResponse();
		byte character1_type = 0;
		if (vo1 != null) {
			character1_type = (byte) vo1.getSceneObjType();
		}
		byte character_type = (byte) vo.getSceneObjType();
		/*
		 * 攻击者影响角色id(int),攻击者角色类型(byte),影响角色id(int), 角色类型(byte),是否被闪避(byte
		 * 2跳闪1闪避0不是)0{血量当前值（int）, 血量最大值(int),是否未暴击(byte 1是0不是)}
		 */
		msg.setMsgCode(10148);
		msg.sendSkillId(0);
		msg.sendVisibleObjectId(vo1 == null ? 0 : vo1.getId());
		msg.sendVisibleObjectType(character1_type);
		msg.sendVisibleObjectId(vo.getId());
		msg.sendVisibleObjectType(character_type);
		msg.sendDodgeFlag(dodge);
		if (dodge == 0) {
			int maxHp = vo.getPropertyAdditionController().getExtraMaxHp();
			int nowHp = vo.getNowHp();
			msg.sendEffect(nowHp, maxHp);
			msg.sendBaojiFlag(baoji);
		}
		if (vo instanceof SceneBangqiMonster) {
			SceneBangqiMonster monster = (SceneBangqiMonster) vo;
			monster.sendBangqiEyeShotMsg(msg);
		} else {
			vo.getEyeShotManager().sendMsg(msg);
		}
	}

	/**
	 * 直接伤害广播
	 * 
	 * @param skillId
	 *            技能id
	 * @param vo1
	 *            施加者
	 * @param vo
	 *            被施加者
	 * @param dodge
	 *            是否被闪避(byte,2跳闪,1闪避,0不是)
	 * @param baoji
	 *            是否为暴击
	 */

	public static void directHurtBroadCase(int skillId, VisibleObject vo1, VisibleObject vo, int dodge, int baoji) {
		/*
		 * 技能ID（int,如不需要客户端用此消息展现击中效果则此ID发0），
		 * 攻击者角色id(int),攻击者角色类型(byte),影响角色id(int),角色类型(byte), 是否被闪避(byte
		 * 2跳闪1闪避0不是)0{血量当前值（int）,血量最大值(int),是否未暴击(byte 1是0不是)}
		 */
		DirectEffectBroadResponse msg = new DirectEffectBroadResponse();
		byte character1_type = 0;
		if (vo1 != null) {
			character1_type = (byte) vo1.getSceneObjType();
		}
		byte character_type = (byte) vo.getSceneObjType();
		/*
		 * 攻击者影响角色id(int),攻击者角色类型(byte),影响角色id(int), 角色类型(byte),是否被闪避(byte
		 * 2跳闪1闪避0不是)0{血量当前值（int）, 血量最大值(int),是否未暴击(byte 1是0不是)}
		 */
		Skill skill = SkillManager.getInstance().getSkillById(skillId);
		if (skill != null && skill.getEffect().getType() == EffectType.jueji) {
			skillId = 0;
		}
		msg.setMsgCode(10148);
		msg.sendSkillId(skillId);
		msg.sendVisibleObjectId(vo1 == null ? 0 : vo1.getId());
		msg.sendVisibleObjectType(character1_type);
		msg.sendVisibleObjectId(vo.getId());
		msg.sendVisibleObjectType(character_type);
		msg.sendDodgeFlag(dodge);
		if (dodge == 0) {
			int maxHp = vo.getPropertyAdditionController().getExtraMaxHp();
			int nowHp = vo.getNowHp();
			msg.sendEffect(nowHp, maxHp);
			msg.sendBaojiFlag(baoji);
		}
		if (vo instanceof SceneBangqiMonster) {
			SceneBangqiMonster monster = (SceneBangqiMonster) vo;
			monster.sendBangqiEyeShotMsg(msg);
		} else {
			vo.getEyeShotManager().sendMsg(msg);
		}
	}

	/**
	 * 直接伤害广播
	 * 
	 * @param vo1
	 *            施加者
	 * @param vo
	 *            被施加者
	 * @param dodge
	 *            是否被闪避(byte,2跳闪,1闪避,0不是)
	 * @param baoji
	 *            是否为暴击
	 * 
	 * @throws Exception
	 */
	public static void hiddeweaponDirectHurtBroadCase(VisibleObject vo1, VisibleObject vo, int dodge, int baoji) {
		DirectEffectBroadResponse msg = new DirectEffectBroadResponse();
		byte character1_type = (byte) vo1.getSceneObjType();
		byte character_type = (byte) vo.getSceneObjType();
		/**
		 * 攻击者影响角色id(int),攻击者角色类型(byte),影响角色id(int), 角色类型(byte),是否被闪避(byte
		 * 2跳闪1闪避0不是)0{血量当前值（int）, 血量最大值(int),是否未暴击(byte 1是0不是)}
		 */
		msg.setMsgCode(52002);
		msg.sendVisibleObjectId(vo1 == null ? 0 : vo1.getId());
		msg.sendVisibleObjectType(character1_type);
		msg.sendVisibleObjectId(vo.getId());
		msg.sendVisibleObjectType(character_type);
		msg.sendDodgeFlag(dodge);
		if (dodge == 0) {
			int maxHp = vo.getPropertyAdditionController().getExtraMaxHp();
			int nowHp = vo.getNowHp();
			msg.sendEffect(nowHp, maxHp);
			msg.sendBaojiFlag(baoji);
		}
		vo.getEyeShotManager().sendMsg(msg);

	}

	/**
	 * 持续效果处理
	 * 
	 * @param effect
	 * @param self
	 * @param who
	 * @param target
	 * @throws IOException
	 */
	public static void broastSustainEffect(EffectInfo effect, VisibleObject vo) {
		if (effect.getType() == EffectType.unhpbuff || effect.getType() == EffectType.jueji) {
			return;
		}
		byte isCharacter = (byte) vo.getSceneObjType();
		int totalTime = effect.getEffect().getDuration();
		SustainEffectResponse cer = new SustainEffectResponse(isCharacter, vo.getId(), effect.getId(), totalTime - effect.getDuration(), totalTime, effect.getRemainPoint(),
				effect.getDuration2(), 0);
		if (isCharacter == SceneObj.SceneObjType_Hero) {
			if (effect.isFacade())
				vo.sendMsg(cer);
			else
				vo.getEyeShotManager().sendMsg(cer);
		} else {
			if (!effect.isFacade()) {
				vo.getEyeShotManager().sendMsg(cer);
			}
		}
	}

	/**
	 * 
	 * 持续效果处理
	 * 
	 * @param effect
	 * @param self
	 * @param who
	 * @param target
	 * @throws IOException
	 */
	public static void broastSustainEffect(EffectInfo effect, int duration, VisibleObject vo) {
		byte isCharacter = (byte) vo.getSceneObjType();
		int totalTime = effect.getEffect().getDuration();
		SustainEffectResponse cer = new SustainEffectResponse(isCharacter, vo.getId(), effect.getId(), totalTime - effect.getDuration(), totalTime, effect.getRemainPoint(),
				effect.getDuration2(), 1);
		if (isCharacter == SceneObj.SceneObjType_Hero) {
			if (effect.isFacade())
				vo.sendMsg(cer);
			else
				vo.getEyeShotManager().sendMsg(cer);
		} else {
			if (!effect.isFacade()) {
				vo.getEyeShotManager().sendMsg(cer);
			}
		}
	}

	/**
	 * 获得经验奖励
	 * 
	 * @param character
	 */
	public static void sendGainExperienceMsg(Hero character) {
		character.sendMsg(new CharacterOneAttributeMsg49990(character, EffectType.exp, character.getNowExperience()));
	}

	public static void sendSkill10402Msg(VisibleObject attacker, int specialEfficiencyId, int skillId, int tarX, int tarY) {
		attacker.getEyeShotManager().sendMsg(new Skill10402(specialEfficiencyId, skillId, attacker.getId(), tarX, tarY));
	}

	/**
	 * 10400技能 发出扇状得技能特效
	 * 
	 * @param attacker
	 *            攻击者
	 * @param specialEfficiencyId
	 *            特效id
	 * @param skillId
	 *            技能id
	 * @param tarX
	 *            目标点x
	 * @param tarY
	 *            目标点y
	 * @param angle
	 *            角度
	 * @param arrowNum
	 *            箭支数量
	 */
	public static void sendSkill10400Msg(VisibleObject attacker, int specialEfficiencyId, int skillId, int tarX, int tarY, int angle, int arrowNum) {
		attacker.getEyeShotManager().sendMsg(new Skill10400(specialEfficiencyId, skillId, attacker.getId(), tarX, tarY, angle, arrowNum));
	}

	public static void sendSkill10404Msg(Hero attacker, int specialEfficiencyId, int skillId, VisibleObject affecter) {
		attacker.getEyeShotManager().sendMsg(new Skill10404(specialEfficiencyId, skillId, attacker.getId(), (byte) affecter.getSceneObjType(), affecter.getId()));

	}

}
