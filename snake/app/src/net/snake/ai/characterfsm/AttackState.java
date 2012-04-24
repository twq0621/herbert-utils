package net.snake.ai.characterfsm;

import java.util.Collection;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.AttackFormula;
import net.snake.consts.SkillStatus;
import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.gamemodel.onhoor.response.AFKCharacterMoveDriverResponse;
import net.snake.gamemodel.onhoor.response.ChangeTargetMsg50594;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.logic.CharacterSkill;

//import org.apache.log4j.Logger;

/**
 * 攻击状态(挂机使用)
 * 
 * @author serv_dev
 * 
 */
public class AttackState extends AfkState {

	private static final int intervalTime = 300;// 选择技能的间隔时间
	private long attackBeginTime = 0l;
	private long skillTime = 0l;
	private long horseSkillTime = 0l;
	private CharacterOnHoorConfig characterOnHoorConfig = null;
	private Horse horse;

	// private static final Logger logger = Logger.getLogger(AttackState.class);

	public AttackState(CharacterFSM fsm, Hero character) {
		super(fsm, character);
	}

	@Override
	public void onBegin() {
		attackBeginTime = System.currentTimeMillis();
		if (characterOnHoorConfig == null) {
			characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
		}
		if (horse == null) {
			horse = character.getCharacterHorseController().getShowHorse();
		}
	}

	@Override
	public void onExit() {
		characterOnHoorConfig = null;
		horse = null;
	}

	@Override
	public void onUpdate(long now) {

		if (character.getObjectState() == VisibleObjectState.Walk || character.isZeroHp()) {
			return;
		}
		if (now - attackBeginTime > intervalTime) {
			// 人物使用技能
			_charUseSkill(now);
			_horseUseSkill(now);
			attackBeginTime = now;
		}

	}

	/**
	 * 坐骑使用技能
	 * 
	 * @param curTime
	 */
	private void _horseUseSkill(long curTime) {
		if (horse == null) {
			return;
		}
		int horseSkillTime1 = 1000;
		if (curTime - horseSkillTime > horseSkillTime1) {
			Collection<CharacterSkill> zhudongSkills = horse.getSkillManager().getZhudongSkills();
			for (CharacterSkill characterSkill : zhudongSkills) {
				if (characterSkill != null) {
					boolean tf = horse.getSkillManager().useSkill(characterSkill);
					if (tf) {
						horseSkillTime = curTime;
						return;
					}
				}
			}
//			if (horse.getSkillManager().useSkill(horse.getSkillManager().getPingkanSkill())) {
//				skillTime = curTime;
//			}
		}
	}

	/**
	 * 人物使用技能
	 * 
	 * @param curTime
	 */
	private void _charUseSkill(long curTime) {
		int _charSkillTime1 = 1000;
		if (curTime - skillTime > _charSkillTime1) {
			int _charSkillId = characterOnHoorConfig.getSkillOne();

			if (_charSkillId > 0) {
				int sta = characterAfkUseSkill(character.getSkillManager().getCharacterSkillById(_charSkillId));
				if (sta == 1) {
					return;
				}
				if (sta == 3) {
					skillTime = curTime;
					return;
				}
			}
			_charSkillId = characterOnHoorConfig.getSkillTwo();
			if (_charSkillId > 0) {
				int sta = characterAfkUseSkill(character.getSkillManager().getCharacterSkillById(_charSkillId));
				if (sta == 1) {
					return;
				}
				if (sta == 3) {
					skillTime = curTime;
					return;
				}
			}
			_charSkillId = characterOnHoorConfig.getSkillThree();
			if (_charSkillId > 0) {
				int sta = characterAfkUseSkill(character.getSkillManager().getCharacterSkillById(_charSkillId));
				if (sta == 1) {
					return;
				}
				if (sta == 3) {
					skillTime = curTime;
					return;
				}
			}
			int ret = characterAfkUseSkill(character.getFightController().getCommonskill());
			if (ret == 3) {
				skillTime = curTime;
				return;
			} 
//			else {
//				// System.err.println("4");
//			}
		}
	}

	/**
	 * 人物挂机使用技能
	 * 
	 * @param characterSkill
	 * @return 1怪物死亡，退出战斗，2技能无法使用 3成功完成战斗
	 */
	private int characterAfkUseSkill(CharacterSkill characterSkill) {
		Skill _skill = characterSkill.getSkill();
		VisibleObject voObject = character.getTarget();

		if (voObject == null || voObject.isZeroHp()) {
			character.setTarget(null);
			character.setObjectState(VisibleObjectState.Idle);
			return 1;
		}

		if (voObject instanceof SceneBangqiMonster) {
			character.setTarget(null);
			character.setObjectState(VisibleObjectState.Idle);
			return 1;
		}

		if (voObject.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			character.setTarget(null);
			character.setObjectState(VisibleObjectState.Idle);
			return 1;
		}

		if (voObject.isJiTui()) {
			return 1;
		}

		if (voObject.getScene() != character.getScene()) {
			character.setTarget(null);
			character.setObjectState(VisibleObjectState.Idle);
			return 1;
		}
		SkillStatus skillStus = characterSkill.ableUse();
		if (skillStus != SkillStatus.ok) {
			if (skillStus == SkillStatus.no_enough_hp || skillStus == SkillStatus.no_enough_mp || skillStus == SkillStatus.no_enough_sp) {
				FightMsgSender.sendSkillReleaseFailMsg(character, skillStus);
			}
			return 2;
		}

		boolean ableAttack = AttackFormula.atkIsEnable2(character.getX(), character.getY(), voObject.getX(), voObject.getY(), (short) (_skill.getDistance()));
		if (!ableAttack) {
			int configX = character.getCharacterOnHoorController().getX();
			int configY = character.getCharacterOnHoorController().getY();
			short[] point = character.getSceneRef().findWay((short) configX, (short) configY, voObject.getX(), voObject.getY());
			if (point == null) {
				character.setTarget(null);
				fsm.switchState(VisibleObjectState.Idle);
				return 1;
			}

			short[] _ppoint = voObject.getPursuitPointManager().getPursuitPoint(character, _skill.getDistance());
			if (_ppoint == null) {
				character.setTarget(null);
				fsm.switchState(VisibleObjectState.Idle);
				return 1;
			}

			AFKCharacterMoveDriverResponse response = new AFKCharacterMoveDriverResponse(voObject.getId(), _ppoint[0], _ppoint[1]);
			character.sendMsg(response);
			return 1;
		} else {
			SkillEffect skillEffect = _skill.getEffect();
			if (skillEffect == null) {
				return 1;
			}

			VisibleObject targetObject = character.getTarget();
			if (skillEffect.getTypePn() == 1) {
				targetObject = character;
			}
			character.sendMsg(new ChangeTargetMsg50594(targetObject.getId()));
			FighterVO fighterVO = new FighterVO(character, character, targetObject, characterSkill);
			fighterVO.setX(targetObject.getX());
			fighterVO.setY(targetObject.getY());
			if (character.getFightController().fight(fighterVO)) {
				return 3;
			} else {
				return 2;
			}
		}
	}

	@Override
	public void reset() {
	}

}
