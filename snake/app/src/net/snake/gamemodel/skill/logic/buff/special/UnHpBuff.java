package net.snake.gamemodel.skill.logic.buff.special;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.formula.AttackFormula;
import net.snake.consts.GameConstant;
import net.snake.consts.SkillId;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController;
import net.snake.gamemodel.skill.bean.EffectInfo;

public class UnHpBuff extends ChiHuan {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UnHpBuff.class);

	public UnHpBuff(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		return true;
	}

	public boolean isAblePk(VisibleObject vo, Hero character) {
		Hero cvo = (Hero) vo;
		if (character.getSceneRef().isPkGradeProtect()) {
			if (vo.getGrade() < GameConstant.PK_GRADE) {
				return false;
			}

			if (character.getGrade() < GameConstant.PK_GRADE) {
				return false;
			}
		}

		if (character.getSceneRef().isPkGradeProtect() && !cvo.getMyCharacterVipManger().isFeibaohuState()) {// 被攻击者不是"非保护和平状态"
			if (Math.abs(vo.getGrade() - character.getGrade()) >= GameConstant.PK_GRADE_CHA) {// 如果被攻击者处于非保护和平状态或者地图不是pk保护的地图，则跳过12等级差的判断
				return false;
			}
		}

		if (vo.getEffectController().isIspkProtect()) {
			return false;
		}

		CharacterOnHoorController characterOnHoorController = cvo.getCharacterOnHoorController();
		if (characterOnHoorController.isAutoOnHoor()) {
			if (characterOnHoorController.isPkProtectTime() && characterOnHoorController.isProtect()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean leaveCondition(long now) {
		VisibleObject attacker = effect.getAttacker();
		if (attacker == null) {
			return true;
		}
		Hero character = null;
		if (attacker.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			character = (Hero) attacker;
			if (character.getObjectState() == VisibleObjectState.Walk && effect.getEffect().getId() != SkillId.DI_HUO_SKILL_ID) {
				return true;
			}
		}
		long beginTime = effect.getBufBeginTime();
		int count = effect.getCount();
		long gotime = (count - 1) * effect.getPreDuration();
		if (now - beginTime > effect.getDuration()) {
			return true;
		}
		if ((now - (beginTime + gotime) > effect.getPreDuration())) {
			count++;
			effect.setCount(count);
			int i = 0;
			boolean forceAttack = effect.getFighterVO().isForceAttack();
			short centerX = character.getX();
			short centerY = character.getY();
			if (effect.getFighterVO().getEffect().getAoeType() == 2) {
				centerX = effect.getFighterVO().getX();
				centerY = effect.getFighterVO().getY();
			}
			Map<Integer, VisibleObject> vosmap = new HashMap<Integer, VisibleObject>();
			if (character.getPkModel() != 0) {
				Collection<Hero> cs = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_Hero);
				for (Hero vo : cs) {
					if (vo.getObjectState() == VisibleObjectState.Die)
						continue;
					if (vo.getObjectState() == VisibleObjectState.Shock) {
						if (!forceAttack) {
							continue;
						}
					}
					if (vo.getSceneRef().getPvpModel() == 0) {
						if (effect.getTypePn() == 0) {
							break;
						}
					}
					if (!isAblePk(vo, character)) {
						continue;
					}
					if (character.getMyTeamManager().isInSameTeamWith(vo.getId())) {
						continue;
					}
					if (character.getMyFactionManager().isInSameFactionWith((Hero) vo)) {
						continue;
					}
					if (AttackFormula.atkIsEnable2(centerX, centerY, vo.getX(), vo.getY(), effect.getScopeRadius())) {// 在效果的范围内
						if (i >= effect.getUseLimit()) {
							break;
						}
						if (vo.getId() == character.getId()) {
							continue;
						}

						effect.setBufValue(0);
						effect.setAffecter(vo);
						zhongdu(effect);
						vosmap.put(vo.getId(), vo);
						i++;
					}
				}
			}
			int j = 0;
			Collection<SceneMonster> ms = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_MonsterScene);
			for (SceneMonster vo : ms) {
				if (AttackFormula.atkIsEnable2(centerX, centerY, vo.getX(), vo.getY(), effect.getScopeRadius())) {// 在效果的范围内
					if (j >= effect.getUseLimit()) {
						break;
					}
					if (vo.getId() == character.getId()) {
						continue;
					}
					if (vo.getObjectState() == VisibleObjectState.Shock) {
						if (!forceAttack) {
							continue;
						}
					}
					effect.setBufValue(0);
					effect.setAffecter(vo);
					zhongdu(effect);
					vosmap.put(vo.getId(), vo);
					j++;
				}
			}
			this.chihuanBuff(vosmap);
		}
		return false;
	}

	public void chihuanBuff(Map<Integer, VisibleObject> vosmap) {
	}

	private void zhongdu(EffectInfo effect) {
		VisibleObject vo = effect.getAffecter();
		try {
			effect.getAttacker().getFightController().getEffectHandler().attackHurtValue(effect.getFighterVO(), vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
