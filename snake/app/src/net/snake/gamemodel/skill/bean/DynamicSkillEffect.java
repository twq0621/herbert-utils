package net.snake.gamemodel.skill.bean;


public class DynamicSkillEffect extends SkillEffect {

	public void setType(short type) {
		this.type = type;
	}

	public void setIsDieClean(short isDieClean) {
		this.isDieClean = isDieClean;
	}

	public void setBuffFlag(short buffFlag) {
		this.buffFlag = buffFlag;
	}

	public void setEffectRepeatOption(Short effectRepeatOption) {
		this.effectRepeatOption = effectRepeatOption;
	}

	public void setHurtValue(int hurtValue) {
		this.hurtValue = hurtValue;
	}
	
}
