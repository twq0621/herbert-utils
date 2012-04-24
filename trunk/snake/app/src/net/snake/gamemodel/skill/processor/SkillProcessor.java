package net.snake.gamemodel.skill.processor;

import org.apache.log4j.Logger;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.CharacterFightController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.SkillShowResponse;
import net.snake.ai.formula.AttackFormula;
import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.SkillId;
import net.snake.consts.SkillStatus;
import net.snake.consts.SkillTarget;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.exception.DataException;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 攻击消息处理 10083
 * 
 * 追击判断
 * 
 */
@MsgCodeAnn(msgcode = 10083)
public class SkillProcessor extends CharacterMsgProcessor {

	private static final Logger logger = Logger.getLogger(SkillProcessor.class);

	@Override
	public void process(Hero character, RequestMsg request)// 是怪物或者马骑的话就不走刀起的操作了
			throws Exception {
		/*
		 * 客户端保证所有移动都停止后,才开始的攻击,也就是说,攻击时的点,一定等于路径中的最后一点,因此遇到攻击消息时,服务器判定路径差距, 如果不是太大 就直接停止移动,更新为路径的最后点
		 */
		character.getMoveController().checkToStopTailPoint();
		character.getCatchHorseActionController().breakCatch();// 使用技能会打断攻击
		character.getCatchXuanyuanJianActionController().breakCatch();
		if (character.getObjectState() == VisibleObjectState.Dazuo) {// 如果在打坐会自动切换状态
			character.setObjectState(VisibleObjectState.Idle);
		}
		int skillId = request.getInt();
		byte type = request.getByte();// 1攻击的是人物, 2攻击的是怪物
		int bgjId = request.getInt();// 被攻击的对象id
		byte force = request.getByte();// 是否按住了shift键
		skillFight(skillId, type, bgjId, character, force == 1);

	}

	/**
	 * 判断玩家是否可以使用该技能
	 * 
	 * @param character
	 * @param skill
	 * @return
	 */
	public boolean isUseSkill(Hero character, Skill skill) {
		// ---判断是否允许使用技能
		if (null == skill) {
			logger.info("不存在该技能,id");
			return false;
		}
		if (skill.getSkilltype() == 1) {
			return false;
		}
		// 判断技能是否为主动攻击，如果是被动攻击，则返回
		if (skill.isPassive()) {
			logger.info("被动技能不能直接使用,id={}" + skill.getId());
			return false;
		}

		if (Options.IsCrossServ && SkillTarget.getTarget(skill.getTarget()) == SkillTarget.ScopeHostilityTarge) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20152));// 论剑台内禁止使用群体攻击
			return false;
		}

		// 判断技能是否允许使用
		if (character.isZeroHp()) {// 血量等于0就不允许攻击了。也就是玩家已经死掉了
			return false;
		}
		if (character.getEffectController().isImmb()) {// 被定身
			return false;
		}
		if (character.getEffectController().isSleep()) {// 被沉睡
			return false;
		}
		return true;
	}

	/**
	 * 得到攻击目标
	 * 
	 * @param character
	 * @param skill
	 * @param type
	 * @param bgjId
	 * @return
	 */
	public VisibleObject getTargetObject(Hero character, Skill skill, byte type, int bgjId) {
		Scene scene = character.getSceneRef();
		int skillId = skill.getId();
		VisibleObject targetObject = null;
		if (1 == type) {// 攻击的是人物
			Hero _affecter = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero, bgjId);
			if (_affecter == null || _affecter.getObjectState() == VisibleObjectState.Die) {
				return null;
			}
			targetObject = _affecter;
			SkillEffect skillEffect = skill.getEffect();
			if (skillEffect == null) {
				return null;
			}
			if (skillEffect.getTypePn() == 0) {// 伤害型技能
				if (bgjId != character.getId()) {
					if (!_affecter.getMyCharacterVipManger().isFeibaohuState()) {// 被攻击者处于非保护状态下
						if (scene.getPvpModel() == 0) {
							character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 862));// 本地图禁止PK
							return null;
						}
					}
					if (!character.isAblePk(targetObject)) {
						return null;
					}
				}
			}

		} else if (2 == type) {// 攻击的是怪物
			targetObject = scene.getSceneObj(SceneObj.SceneObjType_MonsterScene, bgjId);
			if (targetObject == null) {
				targetObject = scene.getSceneObj(SceneObj.SceneObjType_MonsterBangqi, bgjId);
			}
			if (targetObject == null) {
				return null;
			}

			if (!character.isAblePk(targetObject)) {
				return null;
			}

			SceneMonster monster = (SceneMonster) targetObject;
			if (targetObject.getObjectState() == VisibleObjectState.Die && !monster.isFallDown()) {// 死亡还没倒下
				// logger.info("kill monster boss in not down-------");
				targetObject.getLastAttackInfo().setAttackInfo(character, skillId);
//				boolean isdrop =
						monster.getDropGoodManager().dropBossGoodToScene(character);// boss掉落包裹
//				if (isdrop) {
//					monster.getDropGoodManager().dropGoodsToScen(null);
//				}
				CharacterSkill characterSkill = character.getCharacterSkillManager().getCharacterSkillById(skillId);
				monster.getEyeShotManager().sendMsg(
						new SkillShowResponse(skillId, characterSkill.getGrade(), character.getSpriteType(), character.getId(), monster.getSpriteType(), monster.getId()));// 直接视野内广播技能
				character.getFightController().autoAttack(false);
				return null;
			}
			// 坐骑不可攻击
			if (monster.isHorse()) {
				return null;
			}

			if (monster.getObjectState() == VisibleObjectState.Dispose || monster.getObjectState() == VisibleObjectState.Die
					|| monster.getObjectState() == VisibleObjectState.Reset || monster.getObjectState() == VisibleObjectState.IsReset
					|| monster.getObjectState() == VisibleObjectState.Jitui) {// 不可攻击的状态
				FightMsgSender.sendSkillReleaseFailMsg(character, SkillStatus.invalid);
				return null;
			}
		}
		return targetObject;
	}

	private void skillFight(int skillId, byte type, int bgjId, Hero character, boolean forceAttack) throws DataException, Exception {
		CharacterFightController fightController = character.getFightController();
		Skill skill = SkillManager.getInstance().getSkillById(skillId);

		if (!isUseSkill(character, skill)) {
			return;
		}
		// 默认技能设置自动攻击
		// boolean defaultSkill = fightController.getCommonskill().getSkillId() == skillId;
		fightController.autoAttack(true);
		fightController.updateAttackMondel(System.currentTimeMillis());

		VisibleObject targetObject = getTargetObject(character, skill, type, bgjId);
		if (null == targetObject) {
			return;
		}
		character.setTarget(targetObject);
		CharacterSkill characterSkill = null;
		characterSkill = character.getCharacterSkillManager().getCharacterSkillById(skillId);
		if (characterSkill == null) {
			return;
		}
		// 是否可以使用该技能 验证冷却时间、公共冷却时间、消耗hp,mp,sp
		boolean ableUseSkill = characterSkill.able2Use(targetObject);
		if (!ableUseSkill) {
			return;
		}
		if (skillId == SkillId.SHE_MO_SKILL_ID) {// 蛇魔技能的特殊处理
			short[] xy = AttackFormula.getLineAttackPoint(character.getX(), character.getY(), targetObject.getX(), targetObject.getY(), character);
			if (xy == null) {
				SkillShowResponse showResponse = new SkillShowResponse(1);
				character.sendMsg(showResponse);
				return;
			}
		}
		// 2.判断攻击范围是否有效
		// boolean tf = AttackFormula.atkIsEnable2(character.getX(), character.getY(), targetObject.getX(), targetObject.getY(), skill.getDistance());
		if ((int) DistanceFormula.getDistance2(character.getX(), character.getY(), targetObject.getX(), targetObject.getY()) <= skill.getDistance()) {
			// if (tf) {
			FighterVO fighterVO = new FighterVO(characterSkill.getSponsor(), character, targetObject, characterSkill);
			fighterVO.forceAttack(forceAttack);
			fightController.fight(fighterVO);// ---------------------------
			character.getFightController().setMsg10906Cnt(0);
		}
	}
}
