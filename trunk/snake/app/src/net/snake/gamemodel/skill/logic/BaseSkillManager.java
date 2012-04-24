package net.snake.gamemodel.skill.logic;

import java.util.Collection;
import java.util.Iterator;

import net.snake.ai.fight.upgrade.response.SkillUpgradeMsg10272;
import net.snake.consts.CopperAction;
import net.snake.consts.EffectType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.gamemodel.skill.persistence.CharacterSkillDataProvider2;

import org.apache.log4j.Logger;

public abstract class BaseSkillManager<T extends VisibleObject> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaseSkillManager.class);

	protected T voObject;

	public BaseSkillManager(T vo) {
		voObject = vo;
	}

	public T getVoObject() {
		return voObject;
	}

	public abstract void destroy();

	/**
	 * character加技能，等级为1 怪物不同
	 * 
	 * @param skillId
	 * @param skillGrade
	 */
	public abstract CharacterSkill addSkill(int skillId, int skillGrade);

	public abstract CharacterSkill getCharacterSkillById(int skillId);

	public abstract Collection<CharacterSkill> getAllCharacterSkill();

	public abstract boolean useSkill(CharacterSkill characterSkill);

	public CharacterSkill getPingkanSkill() {
		return null;
	}

	public void setPingkanSkill(CharacterSkill pingkanSkill) {

	}
	
	/**
	 * 下线保存
	 */
	public void save() {
	}

	/**
	 * 1.被动技能加成 被动技能等级加成
	 * 
	 * @param characterSkill
	 */
	public void initPassiveSkillsAddition() {
		for (Iterator<CharacterSkill> iterator = getAllCharacterSkill().iterator(); iterator.hasNext();) {
			CharacterSkill characterSkill = iterator.next();
			_passiveSkillUpgrade(characterSkill);
		}
	}

	/**
	 * 被动技能加成（第一次创建和升级时使用）
	 * 
	 * @param grade
	 *            增量等级 如果是第一次创建则为1,升级1级为1 多级为n 初始化技能为当前技能等级n
	 * @param characterSkill
	 */
	protected void _passiveSkillUpgrade(int grade, CharacterSkill characterSkill) {
		Skill _skill = characterSkill.getSkill();
		if (_skill == null) {
			return;
		}
		// 被动技能
		if (_skill.isPassive()) {
			addUnPassiveGrade(grade, characterSkill);
		}
	}

	/**
	 * 被动技能加成（第一次创建和升级时使用）
	 * 
	 * @param grade
	 *            增量等级 如果是第一次创建则为1,升级1级为1 多级为n 初始化技能为当前技能等级n
	 * @param characterSkill
	 */
	protected void _passiveSkillUpgrade(CharacterSkill characterSkill) {
		Skill _skill = characterSkill.getSkill();
		if (_skill == null) {
			logger.warn("_passiveSkillUpgrade(int, CharacterSkill) - init hero "+ voObject.getId()+"'s passive skill fail,skill id:"+ characterSkill.getSkillId()); //$NON-NLS-1$
			return;
		}
		if (_skill.isZhudong()) {
			return;
		}
		// 被动技能
		SkillEffect skillEffect = _skill.getEffect();
		if (skillEffect == null)
			return;
		int type = skillEffect.getType();
		switch (type) {
		case EffectType.undu:
			voObject.setUnpoisoningGrade(characterSkill.getRealGrade());
			break;
		case EffectType.unjitui:
			voObject.setUnKnockbackGrade(characterSkill.getRealGrade());
			break;
		case EffectType.undianxue:
			voObject.setUnhitvitalpointGrade(characterSkill.getRealGrade());
			break;
		case EffectType.unfengMp:
			voObject.setUnFengMpGrade(characterSkill.getRealGrade());
			break;
		case EffectType.unfengSp:
			voObject.setUnFengSpGrade(characterSkill.getRealGrade());
			break;
		case EffectType.unxixue:
			voObject.setUnXiXueGrade(characterSkill.getRealGrade());
			break;
		case EffectType.unfengwuqi:
			voObject.setUnWuqiGrade(characterSkill.getRealGrade());
			break;
		case EffectType.unfengfanju:
			voObject.setUnHujuGrade(characterSkill.getRealGrade());
			break;
		case EffectType.deReduceAttackSpeed:
			voObject.setUnDeAttackSpeedGrade(characterSkill.getRealGrade());
			break;
		case EffectType.deReduceMoveSpeed:
			voObject.setUnDeMoveSpeedGrade(characterSkill.getRealGrade());
			break;
		case EffectType.deReduceDodge:
			voObject.setUnDodgeGrade(characterSkill.getRealGrade());
			break;
		case EffectType.deReduveCrt:
			voObject.setUnCrtGrade(characterSkill.getRealGrade());
			break;
		default:
			voObject.getPropertyAdditionController().replaceListener(characterSkill, characterSkill);
			break;
		}
	}

	public void addUnPassiveGrade(int grade, CharacterSkill characterSkill) {
		Skill _skill = characterSkill.getSkill();
		SkillEffect skillEffect = _skill.getEffect();
		if (skillEffect == null)
			return;
		int type = skillEffect.getType();
		switch (type) {
		case EffectType.undu:
			voObject.setUnpoisoningGrade(voObject.getUnKnockbackGrade() + grade);
			break;
		case EffectType.unjitui:
			voObject.setUnKnockbackGrade(voObject.getUnKnockbackGrade() + grade);
			break;
		case EffectType.undianxue:
			voObject.setUnhitvitalpointGrade(voObject.getUnhitvitalpointGrade() + grade);
			break;
		case EffectType.unfengMp:
			voObject.setUnFengMpGrade(voObject.getUnFengMpGrade() + grade);
			break;
		case EffectType.unfengSp:
			voObject.setUnFengSpGrade(voObject.getUnFengSpGrade() + grade);
			break;
		case EffectType.unxixue:
			voObject.setUnXiXueGrade(voObject.getUnXiXueGrade() + grade);
			break;
		case EffectType.unfengwuqi:
			voObject.setUnWuqiGrade(voObject.getUnWuqiGrade() + grade);
			break;
		case EffectType.unfengfanju:
			voObject.setUnHujuGrade(voObject.getUnHujuGrade() + grade);
			break;
		case EffectType.deReduceAttackSpeed:
			voObject.setUnDeAttackSpeedGrade(voObject.getUnDeAttackSpeedGrade() + grade);
			break;
		case EffectType.deReduceMoveSpeed:
			voObject.setUnDeMoveSpeedGrade(voObject.getUnDeMoveSpeedGrade() + grade);
			break;
		case EffectType.deReduceDodge:
			voObject.setUnDodgeGrade(voObject.getUnDodgeGrade() + grade);
			break;
		case EffectType.deReduveCrt:
			voObject.setUnCrtGrade((voObject.getUnCrtGrade() + grade));
			break;
		default:
			voObject.getPropertyAdditionController().replaceListener(characterSkill, characterSkill);
			break;
		}
	}

	public boolean skillUpgrade(CharacterSkill characterSkill, int grade) {
		VisibleObject vo = getVoObject();
		if (vo instanceof SceneMonster) {
			return false;
		}
		if (characterSkill == null){
			return false;
		}
		if (grade <= characterSkill.getGrade()) {
			return false;
		}
		Hero character = null;
		if (vo instanceof Hero) {
			character = (Hero) vo;
		} else {
			Horse horse = (Horse) vo;
			character = horse.getCharacter();
		}
		SkillUpgradeExp exp = characterSkill.upgradeNeedZhenqi();// 当前的
		if (exp == null) {
			return false;
		}
		if (vo.getGrade() < exp.getLimitGrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 593));
			return false;
		}

		if (grade > characterSkill.getSkill().getGradeLimit()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 594));
			return false;
		}
		
		Integer expZhengqi = exp.getExpZhengqi() - characterSkill.getMastery();
		Integer expCash = exp.getExpCash();
		boolean pinjin = exp.getPinjin() == 1 ? true : false;
		if (pinjin && !characterSkill.getPo()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 595));
			return false;
		}
		if (character.getZhenqi() < expZhengqi) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 596));
			return false;
		}
		if (character.getCopper() < expCash) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 597));
			return false;
		}
		CharacterPropertyManager.changeZhenqi(character, -expZhengqi);
		CharacterPropertyManager.changeCopper(character, -expCash, CopperAction.CONSUME);
		characterSkill.setGrade(grade);
		characterSkill.setMastery(0);
		characterSkill.setPo(false);
		updateCharacterSkill(character,characterSkill);
		character.sendMsg(new SkillUpgradeMsg10272(characterSkill, character));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 598, expCash + "", expZhengqi + "", characterSkill.getSkill().getNameI18n()));
		return true;
	}
	

	/**
	 * 更新 人物技能关系
	 * 
	 * @param characterSkill
	 */
	public void updateCharacterSkill(Hero character,CharacterSkill characterSkill) {
		CharacterSkillDataProvider2.getInstance().asynUpdataCharacterSkill(character, characterSkill);
	}
}
