package net.snake.ai.formula;

import net.snake.commons.script.SCharacterSkill;
import net.snake.commons.script.SVO;
import net.snake.commons.script.EvtSkillFormula;

public class SkillFormula implements EvtSkillFormula {

	@Override
	public float fengHujuPercent(int skillGrade, SVO attacker, SVO affecter) {
		float percent = (skillGrade - affecter.getGrade() + 25) / 100f;
		if (percent < 0f) {
			percent = 0f;
		}

		if (percent > 0.6f) {
			percent = 0.6f;
		}
		return percent;
	}

	@Override
	public int fengHujuTime(int skillGrade, SVO attacker, SVO affecter) {
		return (((skillGrade - affecter.getUnHujuGrade()) * 2 / 10) + 8) * 1000;
	}

	@Override
	public int fengHujuTriggerlv(int skillGrade, SVO attacker, SVO affecter) {
		int lv = (int) ((skillGrade - affecter.getUnHujuGrade()) * 1.5f);
		if (lv < 0)
			return 0;
		if (lv > 100)
			return 100;
		return lv;
	}

	@Override
	public int fengMpTime(int skillGrade, SVO attacker, SVO affecter) {
		return (((skillGrade - affecter.getUnFengMpGrade()) * 2 / 10) + 5) * 1000;
	}

	@Override
	public int fengMpTriggerlv(int skillGrade, SVO attacker, SVO affecter) {
		int lv = (int) ((skillGrade - affecter.getUnFengMpGrade()) * 1.5f);
		if (lv < 0)
			return 0;
		if (lv > 100)
			return 100;
		return lv;
	}

	@Override
	public int fengMpValue(int skillGrade, SVO attacker, SVO affecter) {
		float percent = (skillGrade - affecter.getGrade() + 25) / 100f;
		if (percent < 0f) {
			percent = 0f;
		}

		if (percent > 0.6f) {
			percent = 0.6f;
		}
		return (int) (affecter.getPropertyAdditionController().getExtraMaxMp() * percent);
	}

	@Override
	public int fengSpTime(int skillGrade, SVO attacker, SVO affecter) {
		return (((skillGrade - affecter.getUnFengSpGrade()) * 2 / 10) + 5) * 1000;
	}

	@Override
	public int fengSpTriggerlv(int skillGrade, SVO attacker, SVO affecter) {
		int lv = (int) ((skillGrade - affecter.getUnFengSpGrade()) * 1.5f);
		if (lv < 0)
			return 0;
		if (lv > 100)
			return 100;
		return lv;
	}

	@Override
	public int fengSpValue(int skillGrade, SVO attacker, SVO affecter) {
		float percent = (skillGrade - affecter.getGrade() + 25) / 50f;
		if (percent < 0f) {
			percent = 0f;
		}

		if (percent > 1f) {
			percent = 1f;
		}
		return (int) (affecter.getPropertyAdditionController().getExtraMaxSp() * percent);
	}

	@Override
	public float fengWuqiPercent(int skillGrade, SVO attacker, SVO affecter) {
		float percent = (skillGrade - affecter.getGrade() + 25) / 100f;
		if (percent < 0f) {
			percent = 0f;
		}

		if (percent > 0.6f) {
			percent = 0.6f;
		}
		return percent;
	}

	@Override
	public int fengWuqiTime(int skillGrade, SVO attacker, SVO affecter) {
		return (((skillGrade - affecter.getUnHujuGrade()) * 2 / 10) + 8) * 1000;
	}

	@Override
	public int fengWuqiTriggerlv(int skillGrade, SVO attacker, SVO affecter) {
		int lv = (int) ((skillGrade - affecter.getUnWuqiGrade()) * 1.5f);
		if (lv < 0)
			return 0;
		if (lv > 100)
			return 100;
		return lv;
	}

	@Override
	public int getDantian_MaxHp(SVO vo, int skillgrade) {
		return skillgrade * 12;
	}

	@Override
	public int getDiling_dodge(SVO vo, int skillgrade) {
		return skillgrade * 4;
	}

	@Override
	public int getGeKongDuQiHp(int skillGrade, SVO attacker) {
		// return Math
		// .round((float) ((attacker.getPropertyAdditionController().getExtraMaxHp() * ((skillGrade + 10) * 0.001))));
		// 每秒回血量 = (技能等级+10)*20 2011.3.7
		return (skillGrade + 10) * 20;
	}

	@Override
	public int getJinli_MaxSp(SVO vo, int skillgrade) {
		return skillgrade * 1;
	}

	@Override
	public int getPuDuCiHangHp(int skillGrade, SVO attacker) {
		// 每秒回血量 = (技能等级+10)*17 2011.3.7
		// return Math.round((float) (attacker.getPropertyAdditionController().getExtraMaxHp()
		// * ((skillGrade + 10) * 0.001) * 0.3));
		return (skillGrade + 10) * 17;
	}

	@Override
	public int getSkillTotalGrade(int totalGrade) {
		if (totalGrade >= 100) {
			return 1;
		} else if (totalGrade >= 250) {
			return 2;
		} else if (totalGrade >= 450) {
			return 3;
		} else if (totalGrade >= 700) {
			return 4;
		} else if (totalGrade >= 1000) {
			return 5;
		} else if (totalGrade >= 1400) {
			return 6;
		} else if (totalGrade >= 1900) {
			return 7;
		} else {
			return 0;
		}
	}

	@Override
	public int getSkillUpgradeNeedZhenqi(SCharacterSkill skill) {
		int grade = 1;
		return (100 + grade * grade - grade) * skill.getRevise();
	}

	@Override
	public int getWuShenLinTiTime(int skillGrade) {
		return (int) ((skillGrade + 10) * 6 * 1000);
	}

	@Override
	public int getWuShenLinTiValue(int skillGrade, int affecterDefence) {
		int value = (int) ((skillGrade * 2 + 10) * 0.001 * affecterDefence);
		if (value == 0)
			value = 1;
		return value;
	}

	@Override
	public int getXiaowu_defence(SVO vo, int skillgrade) {
		return Math.round(skillgrade * 2.8f);
	}

	@Override
	public int getXuanmen_MaxMp(SVO vo, int skillgrade) {
		return skillgrade * 6;
	}

	@Override
	public int getXuanyuan_attack(SVO vo, int skillgrade) {
		return skillgrade * 4;
	}

	@Override
	public int getZhanYiLinTiTime(int skillGrade) {
		return (int) ((skillGrade + 10) * 6 * 1000);
	}

	// @Override
	// public int getZhanYiLinTiValue(int skillGrade, SVO affecter) {
	// int value = (int) ((skillGrade * 2 + 10) * 0.001 * affecter.getPropertyAdditionController().getExtraAttack());
	// if (value == 0)
	// value = 1;
	// return value;
	// }
	@Override
	public int getZhanYiLinTiValue(int skillGrade, int affecterDefence) {
		int value = (int) ((skillGrade * 2 + 10) * 0.001 * affecterDefence);
		if (value == 0)
			value = 1;
		return value;
	}

	@Override
	public int getZhanqi_crt(SVO vo, int skillgrade) {
		return Math.round(skillgrade * 1.2f);
	}

	@Override
	public int hitvitalpointTime(int skillGrade, SVO attacker, SVO affecter) {
		return (((skillGrade - affecter.getUnhitvitalpointGrade()) * 2 / 10) + 3) * 1000;
	}

	@Override
	public int hitvitalpointTriggerlv(int skillGrade, SVO attacker, SVO affecter) {
		int lv = (int) ((skillGrade - affecter.getUnhitvitalpointGrade()) / 150f * 100);
		if (lv < 0)
			return 0;
		if (lv > 50)
			return 50;
		return lv;
	}

	@Override
	public int knockbackDistance(int skillGrade, SVO attacker, SVO affecter) {
		return (((skillGrade - affecter.getUnKnockbackGrade()) * 2 / 20) + 5);
	}

	@Override
	public int knockbackTriggerlv(int skillGrade, SVO attacker, SVO affecter) {
		int lv = (skillGrade - affecter.getUnKnockbackGrade()) * 2;
		if (lv < 0)
			return 0;
		if (lv > 100)
			return 100;
		return lv;
	}

	@Override
	public int poisoningHp(SVO attacker, SVO affecter) {
		float reduceHp = (affecter.getNowHp() * .05f);
		reduceHp = reduceHp > 5000 ? 5000 : reduceHp;
		return Math.round(reduceHp);
	}

	@Override
	public int poisoningTime(int skillGrade, SVO attacker, SVO affecter) {
		return (((skillGrade - affecter.getUnpoisoningGrade()) * 2 / 10) + 3) * 1000;
	}

	@Override
	public int poisoningTriggerlv(int skillGrade, SVO attacker, SVO affecter) {
		int lv = (skillGrade - affecter.getUnpoisoningGrade()) * 2;
		if (lv < 0)
			return 0;
		if (lv > 100)
			return 100;
		return lv;
	}

	@Override
	public int xixuePercent(int skillGrade) {
		int value = (skillGrade + 10) / 2;
		if (value < 0)
			return value = 0;
		if (value > 50)
			return value = 50;
		return value;
	}

	@Override
	public int xixueTriggerlv(int skillGrade, SVO attacker, SVO affecter) {
		int lv = (int) ((skillGrade - affecter.getUnXiXueGrade()) * 1.5f);
		if (lv < 0)
			return 0;
		if (lv > 100)
			return 100;
		return lv;
	}

	@Override
	public int getReduceDodgePrecent_QianHuanZhang() {
		return 5000;
	}

	@Override
	public int getReduceCrtPrecent_QiShaZhang() {
		return 5000;
	}

	@Override
	public int getReduceMoveSpeed_SiHouGong() {
		return 80;
	}

	@Override
	public int getReduceAttackSpeed_CuoGuFenJin() {
		return 500;
	}

	@Override
	public int bangpaiSkillTribLv(int skillGrade, int enemySkillGrade) {
		// (自己心法等级-对方抗性心法等级+5)*1.5/100，最小为0，最大为100%
		int lv = (int) ((skillGrade - enemySkillGrade + 5) * 1.5f);
		if (lv < 0)
			return 0;
		if (lv > 100)
			return 100;
		return lv;
	}

	@Override
	public int bangpaiSkillDuration(int skillGrade, int enemySkillGrade) {
		// INT((自己心法等级-对方抗性心法等级)*3/10)+8
		int duration = ((skillGrade - enemySkillGrade) * 3 / 10 + 8) * 1000;
		return duration;
	}
}
