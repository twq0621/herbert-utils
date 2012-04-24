package net.snake.gamemodel.pet.logic;

import java.util.Collection;

import net.snake.ai.horsefsm.HorseBasicState;
import net.snake.commons.VisibleObjectState;
import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.skill.logic.CharacterSkill;

public class HorseAutoAttackController implements Updatable {
	Horse horse;
	boolean attackBegin = false;
	SafeTimer attacktimer;
	private CharacterSkill defaultSkill;
	private long attackBeginTime = 0l;

	public HorseAutoAttackController(Horse horse) {
		this.horse = horse;
	}

	public int getAllObjInHeap() throws Exception {
		int size = 0;
		size = size + (defaultSkill != null ? 1 : 0);
		return size;
	}

	public void initData() {
		Integer defaultSkillId = horse.getCharacterHorse().getDefaultSkillId();
		defaultSkill = horse.getSkillManager().getCharacterSkillById(defaultSkillId);
		if (defaultSkill != null) {
			if (defaultSkill.getSkill().getHurtedTrib() == 0) {
				defaultSkillId = null;
			}
		}
		if (defaultSkillId == null || defaultSkillId == 0) {
			defaultSkillId = horse.getSkillManager().getPingkanSkill().getSkillId();
			horse.getCharacterHorse().setDefaultSkillId(defaultSkillId);
		}
	}

	public void resetDefaultSkill(int skillid) {
		CharacterSkill defaultSkill = horse.getSkillManager().getCharacterSkillById(skillid);
		if (defaultSkill != null) {
			this.defaultSkill = defaultSkill;
		}
	}

	public void startAttackIfNotStart(VisibleObject obj) {
		horse.setTarget(obj);
		if (!attackBegin) {
			attacktimer = new SafeTimer(horse.getHorseModel().getAttackSpeed());
			attackBegin = true;
		}
		horse.getCharacter().getUpdateObjManager().addFrameUpdateObject(this);
	}

	public boolean isStartAttack() {
		return attackBegin;
	}

	public void stopAttack() {
		attackBegin = false;
		horse.setTarget(null);
		horse.getCharacter().getUpdateObjManager().removeFrameUpdateObject(this);
	}

	public void setDefaultSkill(CharacterSkill defaultSkill) {
		this.defaultSkill = defaultSkill;
	}

	@Override
	public void update(long now) {
		if (now - attackBeginTime <= 1000) {
			return;
		}
		HorseBasicState horstState = horse.getCurrentState();
		if (horstState == null || horse.getStatus() == HorseStateConsts.REST) {
			stopAttack();
			return;
		}
		if (horse.getCharacter().getObjectState() == VisibleObjectState.Shock || horse.getCharacter().getTarget() == null) {
			stopAttack();
			return;
		}
		// 坐骑的普通攻击：
		attackBeginTime = now;
		if (horse == null) {
			return;
		}
		Collection<CharacterSkill> zhudongSkills = horse.getSkillManager().getZhudongSkills();
		for (CharacterSkill characterSkill : zhudongSkills) {
			if (characterSkill != null) {
				boolean tf = horse.getSkillManager().useSkill(characterSkill);
				if (tf) {
					return;
				}
			}
		}
		// horse.getSkillManager().useSkill(horse.getSkillManager().getPingkanSkill());
	}

}
