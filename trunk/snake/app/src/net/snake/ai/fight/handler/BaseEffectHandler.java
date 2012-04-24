package net.snake.ai.fight.handler;

import java.util.Collection;
import java.util.List;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.formula.AttackFormula;
import net.snake.ai.formula.DistanceFormula;
import net.snake.commons.GenerateProbability;
import net.snake.commons.script.EvtFightFormula;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.logic.CharacterPropertyAdditionControllerImp;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.SkillManager;

import org.apache.log4j.Logger;

public abstract class BaseEffectHandler {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaseEffectHandler.class);

	private VisibleObject vo;

	protected BaseEffectHandler(VisibleObject vo) {
		this.vo = vo;
	}

	/**
	 * 效果处理
	 * 
	 * @param fighterVO
	 *            战斗vo
	 * @return 受用对象们
	 */
	public List<VisibleObject> getFighterVo(FighterVO fighterVO) {
		try {
			int userScope = fighterVO.getEffect().getUserScope();
			switch (userScope) {
			case 0:
			case 1:// 单体
				return monomerEffectTarget(fighterVO);
			case 2:// 群体
				return populationEffectTarget(fighterVO);
			default:
				if (logger.isInfoEnabled()) {
					logger.info("效果属性userScope:" + userScope);
				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 以自身为中心寻找周围的玩家
	 * 
	 * @param distance
	 * @param expId
	 *            排除角色id
	 * @return
	 */
	public VisibleObject findCharacterInScene(int distance, int expId) {
		short voX = vo.getX();
		short voY = vo.getY();
		Collection<Hero> cs = vo.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		for (Hero character : cs) {
			if (character.getId() == expId)
				continue;
			if (character.getId() == vo.getId())
				continue;
			if (!character.isZeroHp() && AttackFormula.atkIsEnable2(voX, voY, character.getX(), character.getY(), distance)) {// 在效果的范围内
				return character;
			}
		}
		return null;
	}

	/**
	 * 
	 * 找到范围内的目标
	 * 
	 * @param centerObject
	 *            以自身为中心或者以目标为中心
	 * @param distance
	 *            施法距离
	 * @param voInRegion
	 *            临时存放目标对象列表
	 * 
	 */

	protected void findCharacterInScene(short centerX, short centerY, SkillEffect effect, List<VisibleObject> voInRegion, int r) {
		int i = 0;
		int effectCnt = effect.getUseLimit();
		Collection<Hero> cs = vo.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
		for (Hero character : cs) {
			if (!character.isZeroHp() && AttackFormula.atkIsEnable2(centerX, centerY, character.getX(), character.getY(), r)) {// 在效果的范围内
				voInRegion.add(character);
				i++;
				if (i >= effectCnt) {
					break;
				}
			}
		}
	}

	/**
	 * 找到范围内的目标
	 * 
	 * @param centerObject
	 *            以自身为中心或者以目标为中心
	 * @param distance
	 *            施法距离
	 * @param voInRegion
	 *            临时存放目标对象列表
	 */

	protected void findSceneMonsterInScene(VisibleObject centerObject, SkillEffect skillEffect, List<VisibleObject> voInRegion) {
		int i = 0;
		int effectRadus = skillEffect.getScopeRadius();
		int effectCnt = skillEffect.getUseLimit();
		short centerX = centerObject.getX();
		short centerY = centerObject.getY();

		Collection<SceneMonster> ms = centerObject.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
		for (SceneMonster monster : ms) {
			if (monster.isHorse())
				continue;
			if (!monster.isDie() && AttackFormula.atkIsEnable2(centerX, centerY, monster.getX(), monster.getY(), effectRadus)) {// 在效果的范围内
				voInRegion.add(monster);
				i++;
				if (i >= effectCnt)
					break;
			}
		}
	}

	/**
	 * 单体效果目标（自己1、自己所在小队2、范围内友好目标3、范围内敌对目标4,其他技能5，当前目标6）
	 * 
	 * @param effect
	 * @param self
	 * @param target
	 * @param skill
	 * @throws Exception
	 */
	protected abstract List<VisibleObject> monomerEffectTarget(FighterVO fighterVO) throws Exception;

	/**
	 * 群体效果目标（自己1、自己所在小队2、范围内友好目标3、范围内敌对目标4,其他技能5，当前目标6）
	 * 
	 * @param effect
	 * @param self
	 * @param target
	 * @param skill
	 * @throws Exception
	 */
	protected abstract List<VisibleObject> populationEffectTarget(FighterVO fighterVO) throws Exception;

	/**
	 * 
	 * 伤害方式判定(计算效果值、持续效果判定、特效处理)
	 * 
	 * @param fighterVO
	 *            谁发起
	 * @param who
	 *            作用于谁
	 * @throws Exception
	 * 
	 */
	public void judgeHurtWay(final FighterVO fighterVO, final VisibleObject who) {
		relevanceSkill(fighterVO, who);
		if (fighterVO.getCharacterSkill() == null) {
			return;
		}
		Skill _skill = fighterVO.getSkill();
		float dis = 0;
		if (fighterVO.getSponsor().getSceneObjType() == SceneObj.SceneObjType_Horse) {
			Horse horse = (Horse) fighterVO.getSponsor();
			if (horse == horse.getCharacter().getCharacterHorseController().getCurrentRideHorse()) {// 如果是骑着的马，计算人和目标的距离
				dis = DistanceFormula.getDistance2(horse.getCharacter().getX(), horse.getCharacter().getY(), who.getX(), who.getY());
			} else {// 如果不是骑着的马，计算马和目标的距离
				dis = DistanceFormula.getDistance2(fighterVO.getSponsor().getX(), fighterVO.getSponsor().getY(), who.getX(), who.getY());
			}
		} else {
			dis = DistanceFormula.getDistance2(fighterVO.getSponsor().getX(), fighterVO.getSponsor().getY(), who.getX(), who.getY());
		}
		int delayTime = (int) (_skill.getPlayDelay() + (dis * 25) / _skill.getFlySpeed() * 1000);
		fighterVO.getCharacterSkill().setActionTime(delayTime);
		if (who.getObjectState() == VisibleObjectState.Die) {
			return;
		}
		//被攻击之后的下一步操作
		who.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				final EffectInfo effectInfo = new EffectInfo(fighterVO.getEffect());
				try {
					int value = getEffectValue(effectInfo, fighterVO);
					EffectController targetEffectController = who.getEffectController();
					effectInfo.setAttacker(vo);
					effectInfo.setAffecter(who);
					effectInfo.setBufValue(value);
					effectInfo.setRelevanceSkillId(fighterVO.getSkill().getId());
					effectInfo.setFighterVO(fighterVO);
					// 战斗
					if (effectInfo.getTypePn() == 0 && vo.getId() != who.getId()) {
						who.getLastAttackInfo().setAttackInfo(vo, fighterVO.getSkill().getId());
						if (who.getObjectState() != VisibleObjectState.Die) {
							attackHurtValue(fighterVO, who);
						}
					}
					//技能产生的buff等效果处理
					targetEffectController.addEffect(effectInfo);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}, delayTime);
		fighterVO.setRecipient(who);
	}

	private void relevanceSkill(FighterVO fighterVO, VisibleObject who) {
		int skillRelevance = fighterVO.getSkill().getSkillRelevance();
		if (skillRelevance > 0) {// 关联技能触发
			Skill skill2 = SkillManager.getInstance().getCacheSkillMap().get(skillRelevance);
			if (skill2 != null) {
				CharacterSkill characterSkill2 = fighterVO.getFighter().getSkillManager().getCharacterSkillById(skillRelevance);
				FighterVO fighterVO2;
				if (characterSkill2 == null) {
					fighterVO2 = new FighterVO(fighterVO.getSponsor(), fighterVO.getFighter(), fighterVO.getRecipient(), SkillManager.getInstance().getSkillById(skillRelevance));
				} else {
					fighterVO2 = new FighterVO(fighterVO.getSponsor(), fighterVO.getFighter(), fighterVO.getRecipient(), characterSkill2);
				}
				fighterVO2.forceAttack(fighterVO.isForceAttack());
				judgeHurtWay(fighterVO2, who);
			}
		}
	}

	/**
	 * 战斗
	 * 
	 * @param fighterVO
	 *            攻击方
	 * @param affecter
	 *            被攻击方
	 * @throws Exception
	 */
	public void attackHurtValue(FighterVO fighterVO, VisibleObject affecter) throws Exception {
		// 3.计算命中率，判定是否命中
		boolean ishit = AttackFormula.hit(vo, affecter);
		if (ishit) {
			int hurtValue = calculateHurtValue(fighterVO, vo, affecter);
			hurtValue = (int) (hurtValue * (1 + fighterVO.getHurt_decay_coefficient()));
			fighterVO.setHurtValue(hurtValue);
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				CharacterSkill charskill = fighterVO.getCharacterSkill();
				if (charskill != null) {
					hurtValue = charskill.getAppendSkillValue(hurtValue);
				}
			}
		} else {
			fighterVO.setHurtValue(0);
		}
		affecter.onBeAttack(vo, fighterVO);
	}

	/**
	 * 对被攻击方的伤害
	 * 
	 * @param fighterVO
	 * @param attacker
	 * @param affecter
	 * @return
	 * @throws Exception
	 */
	protected int calculateHurtValue(FighterVO fighterVO, VisibleObject attacker, VisibleObject affecter) throws Exception {
		AppEventCtlor eventCtlor = AppEventCtlor.getInstance();
		EvtFightFormula evtFightFormula = eventCtlor.getEvtFightFormula();

		int bgfHP = evtFightFormula.getHurtValue(attacker, affecter, fighterVO);
		if (bgfHP < 0)
			bgfHP = 0;
		byte baoji = 0;
		// 会心一击
		int xishu = 0;
		boolean isbaoji = AttackFormula.probabilityEnable(evtFightFormula.getCrt(attacker, affecter));
		if (affecter.getEffectController().isJiTuiBaoJi() || isbaoji) {
			xishu = 1;
			baoji = 1;
			affecter.getEffectController().setJiTuiBaoJi(false);
		}
		if (attacker.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero character = (Hero) (attacker);
			if (character.getMyFactionManager().isHaveYoulongBuffer()) {// 王威：战斗时有额外5%的几率打出3倍爆击伤害
				if (GenerateProbability.defaultIsGenerate(500)) {
					// bgfHP = bgfHP * 3;
					xishu += 3;
					baoji = 1;
				}
			}
		}
		/*
		 * 降低伤害比例是指：爆击产生伤害后，在伤害上 * （1-XX%） 本比例最大为100%，以防公式出现负数
		 * 
		 * 降低爆击伤害值合并计算： 各门派爆击伤害减免比例 + 对伤害施加者门派的爆击伤害减免比例
		 */

		float p = 1;
		if (affecter.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero character = (Hero) (affecter);
			CharacterPropertyAdditionControllerImp characterPropertyAdditionControllerImp = character.getCharacterPropertyAdditionController();
			float dugureducecrt = 0f;
			if (attacker.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				Hero character2 = (Hero) (attacker);
				dugureducecrt = characterPropertyAdditionControllerImp.getDugujiujian().getReduceCrtMenPai(character2.getPopsinger()) / 10000f;
			}
			p = p - (characterPropertyAdditionControllerImp.getDugujiujian().getAll_crt() / 10000f + dugureducecrt);
			if (p < 0) {
				p = 0;
			} else if (p > 1) {
				p = 1;
			}
		}
		bgfHP = (int) (bgfHP + xishu * bgfHP * p);
		if (bgfHP == 0) {
			return 0;
		}
		fighterVO.setBaoji(baoji);
		return bgfHP;
	}

	/**
	 * 技能效果处理
	 * 
	 * @param effect
	 * @param fighterVO
	 * @return
	 * @throws Exception
	 */

	protected int getEffectValue(EffectInfo effect, FighterVO fighterVO) throws Exception {
		int preValue = 0;
		int value = effect.getHurtValue();
		// logger.info("-----------------------------hurtValue=---------------------------------"+value);
		int hurtConsiderationWay = effect.getHurtConsiderationWay();
		switch (hurtConsiderationWay) {
		case 1:// 单一目标伤害，所有怪物的伤害结果一样
			preValue = value;
			break;
		case 2:// 全部目标伤害，所有怪物平分伤害结果
			preValue = value / fighterVO.getMaxHurtNum();
			break;
		case 3:
			preValue = value;
			break;
		default:
			preValue = value;
			break;
		}
		return preValue;
	}
}
