package net.snake.ai.formula;

import net.snake.commons.GenerateProbability;
import net.snake.commons.script.EvtFightFormula;
import net.snake.commons.script.SCharacterSkill;
import net.snake.commons.script.SEnmity;
import net.snake.commons.script.SFighterVO;
import net.snake.commons.script.SHorse;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SPropertyAdditionController;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SVO;

public class FightFormula implements EvtFightFormula {

	@Override
	public int getCrt(SVO attacker, SVO affecter) {
		int attackerGrade = attacker.getGrade();
		int affecterGrade = affecter.getGrade();
		int attackCrt = attacker.getPropertyAdditionController().getExtraCrt();
		int affecterCrt = affecter.getPropertyAdditionController().getExtraCrt();
		// 暴击率= a*(攻击者的暴击值/（攻击者的暴击值+防御方的暴击) +b*(攻击方角色等级-防御方角色等级)/100
		// 其中：a=0.2 b=0.2
		// 暴击率的取值区间为： [0.01，0.4]
		float a = 0.2f;
		float b = 0.2f;
		float _crtValue = a * ((float) attackCrt / (attackCrt + affecterCrt)) + b * (float) (attackerGrade - affecterGrade) / 100;
		_crtValue = _crtValue * 100;
		if (_crtValue < getMinCrtPrecent()) {
			_crtValue = getMinCrtPrecent();
		} else if (_crtValue > getMaxCrtPrecent()) {
			_crtValue = getMaxCrtPrecent();
		}
		return (int) _crtValue;
	}

	@Override
	public int getDodge(SVO attacker, SVO affecter) {
		int attackerGrade = attacker.getGrade();
		int affecterGrade = affecter.getGrade();
		float attackerAttack = attacker.getPropertyAdditionController().getExtraAttack();
		float affecterDodge = affecter.getPropertyAdditionController().getExtraDodge();
		float dodgePercent = (float) ((1 - 0.5 * (affecterDodge / (attackerAttack + affecterDodge)) + (0.25 * (attackerGrade - affecterGrade) * 0.01)) * 100);
		if (dodgePercent < getMinDodgePrecent())
			dodgePercent = getMinDodgePrecent();
		if (dodgePercent > getMaxDodgePrecent())
			dodgePercent = getMaxDodgePrecent();
		return (int) dodgePercent;
	}

	@Override
	public int getExp(SRole character, SMonster monster) {
		int cg = character.getGrade();
		int mg = monster.getGrade();
		int monserExp = monster.getExp();

		int _grade = Math.abs(mg - cg);
		/**
		 * 怪=玩家 　 100% 怪>玩家 大于或等于3 110% 　 超过3 115% 怪<玩家 小于或等于3 100% 　 超过3 80%
		 */
		float gradeE = 1f; // 等级衰减系数
		if (monster.getExpUnlimit() == 0) {
			// if (_grade == 0) {
			// gradeE = 1f;
			// } else if (_grade > 0 && _grade <= 3) {
			// gradeE = 1.1f;
			// } else if (_grade >= 3) {
			// gradeE = 1.5f;
			// } else if (_grade < 0 && _grade >= -3) {
			// gradeE = 1f;
			// } else if (_grade < -3) {
			// gradeE = 0.8f;
			// }
			if (_grade > 10) {
				gradeE = 0.01f;
			} else if (_grade > 5 && _grade <= 10) {
				gradeE = 0.8f;
			}
		}
		int doubExp = 1;// 多倍经验系数
		doubExp = doubExp + character.getDoubExpNum();
		doubExp = doubExp + character.getDaguaiExp();
		// 连斩系数
		float lianzhenExp = 0f;
		if (character.isContinuumKill()) {
			lianzhenExp = character.getContinuumExp();
		}
		int teamNum = character.getTeamNum();
		// 杀怪血量百分比
		SEnmity enmity = monster.getEnmityManager().getSEnmity(character);
		if (enmity == null) {
			return 0;
		}
		float enmityValue = enmity.getHurt();
		float percentMonsterHp = enmityValue / monster.getPropertyAdditionController().getExtraMaxHp();

		float _precentTeam = 0;
		if (percentMonsterHp < .01) {
			_precentTeam = 0f;
		} else if (.01 < percentMonsterHp && percentMonsterHp <= 0.1) {
			_precentTeam = .3f;
		} else if (0.1 < percentMonsterHp && percentMonsterHp <= 0.2) {
			_precentTeam = .4f;
		} else if (0.2 < percentMonsterHp && percentMonsterHp <= 0.3) {
			_precentTeam = .5f;
		} else if (0.3 < percentMonsterHp && percentMonsterHp <= 0.6) {
			_precentTeam = .65f;
		} else if (0.6 < percentMonsterHp && percentMonsterHp <= 0.75) {
			_precentTeam = .8f;
		} else {
			_precentTeam = 1f;
		}

		float allBornLv = 0f;
		if (character.isAllBornEquip()) {
			allBornLv = 0.1f;
		}
		double returnExp = 0;
		if (!monster.isPT()) {
			returnExp = monserExp * (1 + (teamNum - 1) * .05) * _precentTeam * gradeE;
		} else {
			double fengYuTongJiAdd = character.hasFengYuTongJiBuff() ? .3 : 0;
			returnExp = monserExp * (1 + (teamNum - 1) * .05) * _precentTeam * gradeE
					* (doubExp + allBornLv + fengYuTongJiAdd + lianzhenExp + (character.getBanghuiJiacheng() + character.xiangyangFactionJiacheng()) / 10000f);
		}

		returnExp = returnExp * character.getAntiAddictedSystemPlusScale();
		return Math.round((float) returnExp);
	}

	@Override
	public int getHurtValue(SVO attacker, SVO affecter, SFighterVO fighterVO) {
		SPropertyAdditionController attProperty = attacker.getPropertyAdditionController();
		SPropertyAdditionController affProperty = affecter.getPropertyAdditionController();
		float attAttack = attProperty.getFightAttack();

		// 攻击力增强：在战斗公式中计算自己全部攻击力后再乘以（1+本值）
		attAttack = attAttack * (1 + attProperty.getExtraGjl() / 100f);

		// boss和精英怪伤害加成
		if (affecter instanceof SMonster) {
			SMonster sceneMonster = (SMonster) affecter;
			if (sceneMonster.getType() == 2 || sceneMonster.getType() == 3) {
				if (attacker instanceof SRole) {
					SRole _attacker = (SRole) attacker;
					int continumKillHate = _attacker.getContinuumKillHate();
					attAttack = attAttack * continumKillHate;
				}
			}
		}

		float affDef = affProperty.getFightDefence();

		// 忽视防御：在战斗公式中，减去对方防御时乘以（1-本值）
		affDef = affDef * (1 - attProperty.getExtraHsfy() / 100f);

		float returnValue = (attAttack - affDef);

		// 最终伤害=（原伤害+攻击方人物等级）*伤害随机区间
		// 伤害随机区间为：
		// [85%,115%]
		if (returnValue < getMinHurt())
			returnValue = getMinHurt();
		if (returnValue > getMaxHurt())
			returnValue = getMaxHurt();

		/*
		 * 主人对该怪物能够造出的伤害 * ((坐骑等级+50)/200) * 坐骑攻击伤害修正系数
		 */
		if (fighterVO.getSponsor() instanceof SHorse) {
			SHorse horse = (SHorse) fighterVO.getSponsor();
			// if (horse.getCurrentState() == HorseBasicState)
			if (horse.getStatus() == 2) {
				returnValue = (returnValue * ((horse.getGrade() + 50) * 0.005f) * horse.getAttackHurtCorrect());
			}
		}

		returnValue = ((returnValue + attacker.getGrade()) * GenerateProbability.randomFloatValue(getMinHurtPrecent(), getMaxHurtPrecent()));

		int hurtAdd = 0;
		int realgrade = 0;
		float hurtrevise = 1;
		SCharacterSkill cSkill = fighterVO.getCharacterSkill();
		if (cSkill != null) {
			hurtAdd = cSkill.getHurtAdds();
			realgrade = cSkill.getRealGrade();
			hurtrevise = cSkill.getHurtRevise();
		}

		returnValue = returnValue * ((1 + realgrade / 150f) * hurtrevise) + hurtAdd;
		return (int) returnValue;
	}

	@Override
	public float getMaxCrtPrecent() {
		return 40;
	}

	@Override
	public float getMaxDodgePrecent() {
		return 100;
	}

	@Override
	public int getMaxHurt() {
		return 1200000000;
	}

	@Override
	public float getMaxHurtPrecent() {
		return 1.15f;
	}

	@Override
	public float getMinCrtPrecent() {
		return 1;
	}

	@Override
	public float getMinDodgePrecent() {
		return 10;
	}

	@Override
	public int getMinHurt() {
		return 1;
	}

	@Override
	public float getMinHurtPrecent() {
		return 0.85f;
	}

	@Override
	public float getAddAttack(int attackAdd) {
		return attackAdd * 1.6f;
	}

	@Override
	public float getAddDefence(int defenceAdd) {
		return defenceAdd * 2f;
	}

	@Override
	public float getAddCrt(int qingshenAdd) {
		return qingshenAdd * 0.8f;
	}

	@Override
	public float getAddDodge(int qingshenAdd) {
		return qingshenAdd * 2f;
	}

	@Override
	public float getAddMaxHp(int jiantiAdd) {
		return jiantiAdd * 8f;
	}

	@Override
	public float getAddMaxMp(int jiantiAdd) {
		return jiantiAdd * 4f;
	}

	@Override
	public float getAddMaxSp(int jiantiAdd) {
		return jiantiAdd * 1f;
	}

	@Override
	public int getHiddenHurtValue(SVO attacker, SVO affecter, SFighterVO fighterVO) {
		if (fighterVO.getCharacterHiddenWeapon() == null) {
			return 0;
		}
		return (int) (getHurtValue(attacker, affecter, fighterVO) * fighterVO.getCharacterHiddenWeapon().getAttackpercent() / 10000f);
	}

	@Override
	public int getHit(SVO attacker, SVO affecter) {
		// 命中率= a* A的命中/(A的命中+0.5*B的ID显示的闪避) +b*(攻击方角色等级-防御方角色等级)/100
		// a=1 b=0.2
		int a = 1;
		float b = 0.2f;
		int attackerGrade = attacker.getGrade();
		int affecterGrade = affecter.getGrade();
		float attackerHit = attacker.getPropertyAdditionController().getExtraHit();
		float affecterDodge = affecter.getPropertyAdditionController().getExtraDodge();
		float hit = a * attackerHit / (attackerHit + 0.5f * affecterDodge) + b * (attackerGrade - affecterGrade) / 100;
		int intHit = (int) (hit * 100);
		if (intHit < 10) {
			intHit = this.getMinHit();
		} else if (intHit > 100) {
			intHit = this.getMaxHit();
		}
		return intHit;
	}

	@Override
	public int getMinHit() {
		return 10;
	}

	@Override
	public int getMaxHit() {
		return 100;
	}
}
