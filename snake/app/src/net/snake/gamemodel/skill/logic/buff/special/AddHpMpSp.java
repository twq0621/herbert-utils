package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.EffectType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;

import org.apache.log4j.Logger;

/**
 * 加血
 * 
 * @author serv_dev
 * 
 */
public class AddHpMpSp extends Buff {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AddHpMpSp.class);

	public AddHpMpSp(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {

		VisibleObject affecter = effect.getAffecter();
		int hurtValue = effect.getHurtValue();
		int percent = effect.getPercent();
		if (effect.getDuration() == 0) {
			// 为一次性的加血
			switch (effect.getType()) {
			case EffectType.hp:
				// int addHP = (effect.getHurtValue() + (int) (affecter.getPropertyAdditionController().getExtraMaxHp() * percent * 0.0001));
				int grade = effect.getFighterVO().getCharacterSkill().getGrade();
				int addHP = effect.getHurtValue() + grade * grade + 3 * grade;
				addHP = affecter.changeNowHp(addHP);
				if (addHP > 0) {
					FightMsgSender.directHurtBroadCase(effect.getAttacker(), affecter, 0, CommonUseNumber.byte0);
				}
				break;
			case EffectType.mp:
				affecter.changeNowMp(hurtValue + (int) (affecter.getPropertyAdditionController().getExtraMaxMp() * percent * 0.0001));
				affecter.sendMsg(new CharacterOneAttributeMsg49990(affecter, EffectType.mp, affecter.getNowMp()));
				break;
			case EffectType.sp:
				affecter.changeNowSp(hurtValue + (int) (affecter.getPropertyAdditionController().getExtraMaxSp() * percent * 0.0001));
				affecter.sendMsg(new CharacterOneAttributeMsg49990(affecter, EffectType.sp, affecter.getNowSp()));
				break;
			default:
				break;
			}
			return false;
		} else {
			add();
			FighterVO fighterVO = effect.getFighterVO();
			if (fighterVO != null) {
				VisibleObject sponsor = fighterVO.getSponsor();
				if (sponsor != null) {
					CharacterSkill _characterSkill = sponsor.getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
					if (_characterSkill != null) {
						FightMsgSender.sendBeiDongSkillMsg(sponsor, _characterSkill.getSkill());
					}
				}
			}
			return true;
		}
	}

	@Override
	public boolean leave(EffectController controller) {
		return true;
	}

	@Override
	public boolean leaveCondition(long now) {

		long gotime = effect.getCount() * effect.getPreDuration();
		if (now - effect.getBufBeginTime() > effect.getDuration()) {
			return true;
		}

		if (now - (effect.getBufBeginTime() + gotime) > effect.getPreDuration()) {
			effect.setCount(effect.getCount() + 1);
			add();
		}
		return false;
	}

	private void add() {
		int value = effect.getBufValue();
		// logger.info("effect.getBufValue()="+effect.getBufValue());
		VisibleObject affecter = effect.getAffecter();
		if (affecter.isZeroHp())
			return;
		switch (effect.getType()) {
		case EffectType.hp:
			int changeHp = value * effect.getFighterVO().getCharacterSkill().getGrade();
			// + (int) (affecter.getPropertyAdditionController().getExtraMaxHp() * effect.getPercent() * 0.0001);
			value = affecter.changeNowHp(changeHp);
			if (value > 0) {
				FightMsgSender.directHurtBroadCase(effect.getRelevanceSkillId(), effect.getAttacker(), affecter, 0, CommonUseNumber.byte0);
			}
			break;
		case EffectType.mp:
			if (value > 0) {
				if (affecter.getEffectController().isMpOver()) {
					return;
				}
			}
			affecter.changeNowMp(value * effect.getFighterVO().getCharacterSkill().getGrade());
			// + (int) (affecter.getPropertyAdditionController().getExtraMaxMp() * effect.getPercent() * 0.0001)
			affecter.sendMsg(new CharacterOneAttributeMsg49990(affecter, EffectType.mp, affecter.getNowMp()));
			break;
		case EffectType.sp:
			if (value > 0) {
				if (affecter.getEffectController().isSpOver()) {
					return;
				}
			}
			affecter.changeNowSp(value * effect.getFighterVO().getCharacterSkill().getGrade());
			// + (int) (affecter.getPropertyAdditionController().getExtraMaxSp() * effect.getPercent() * 0.0001)
			affecter.sendMsg(new CharacterOneAttributeMsg49990(affecter, EffectType.sp, affecter.getNowSp()));
			break;
		default:
			break;
		}
	}

	@Override
	public boolean init(EffectController controller) {
		VisibleObject affecter = effect.getAffecter();
		int hurtValue = effect.getHurtValue();
		int percent = effect.getPercent();
		int buffValue = effect.getBufValue();
		if (effect.getDuration() == 0) {
		} else {

			VisibleObject attacker = effect.getAttacker();

			CharacterSkill characterSkill = attacker == null ? null : attacker.getSkillManager().getCharacterSkillById(effect.getRelevanceSkillId());
			switch (effect.getType()) {
			case EffectType.hp:
				int addHP = 0;
				if (hurtValue == 0 && percent == 0 && buffValue == 0) {
					if (attacker == null && effect.getBufValue() > 0) {
						return true;
					}
					if (characterSkill == null) {
						logger.warn("enter(EffectController) - no CharacterSkill:"+effect.getRelevanceSkillId()); //$NON-NLS-1$
						return false;
					}
					if (effect.getUserScope() == 1) {
						addHP = AppEventCtlor.getInstance().getEvtSkillFormula().getGeKongDuQiHp(characterSkill.getRealGrade(), attacker);// 隔空渡气
					} else if (effect.getUserScope() == 2) {
						addHP = AppEventCtlor.getInstance().getEvtSkillFormula().getPuDuCiHangHp(characterSkill.getRealGrade(), attacker);// 普渡慈航
					}
					effect.setBufValue(addHP);
				} else {
					if (buffValue == 0) {
						addHP = (effect.getHurtValue() + (int) (affecter.getPropertyAdditionController().getExtraMaxHp() * percent * 0.0001));
						effect.setBufValue(addHP);
					}
				}

				break;
			case EffectType.mp:
				effect.setBufValue(effect.getHurtValue() + (int) (affecter.getPropertyAdditionController().getExtraMaxMp() * percent * 0.0001));
				break;
			case EffectType.sp:
				effect.setBufValue(effect.getHurtValue() + (int) (affecter.getPropertyAdditionController().getExtraMaxSp() * percent * 0.0001));
				break;
			}
		}
		return true;
	}
}
