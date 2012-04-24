package net.snake.gamemodel.skill.processor;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.CharacterFightController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.AttackFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.SkillStatus;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.netio.message.RequestMsg;

import org.apache.log4j.Logger;

/**
 * 点技能释放,群攻技能。
 * 
 */
@MsgCodeAnn(msgcode = 10283)
public class SkillProcesser10283 extends CharacterMsgProcessor {

	private static final Logger logger = Logger.getLogger(SkillProcesser10283.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		int skillId = request.getInt();
		short x = request.getShort();
		short y = request.getShort();
		byte force = request.getByte();// 是否按住了shift键

		if (character.getObjectState() == VisibleObjectState.Dazuo) {
			character.setObjectState(VisibleObjectState.Idle);
		}
		// 判断技能是否允许使用
		if (character.isZeroHp()) {// 血量等于0就不允许攻击了。也就是玩家已经死掉了
			return;
		}
		if (character.getEffectController().isImmb()) {// 被定身
			return;
		}
		if (character.getEffectController().isSleep()) {// 被沉睡
			return;
		}

		//boolean defaultSkill = character.getFightController().getCommonskill().getSkillId() == skillId;
		character.getFightController().autoAttack(true);
		character.getFightController().updateAttackMondel(System.currentTimeMillis());

		Skill skill = SkillManager.getInstance().getSkillById(skillId);
		if (null == skill) {
			logger.warn("no this skill,id=" + skillId);
			return;
		}

		if (skill.isPassive()) {
			return;
		}

		if (!AttackFormula.atkIsEnable2(x, y, character.getX(), character.getY(), skill.getDistance())) {
			// 超出了施法距离了
			short[] enablePoint = AttackFormula.getStraightLinePoint(character.getX(), character.getY(), x, y, skill.getDistance());
			x = enablePoint[0];
			y = enablePoint[1];
		}

		CharacterSkill characterSkill = character.getCharacterSkillManager().getCharacterSkillById(skillId);

		if (characterSkill != null) {
			SkillStatus skillStus = characterSkill.ableUse();
			if (skillStus == SkillStatus.ok) {
				FighterVO fighterVO = new FighterVO(characterSkill.getSponsor(), character, x, y, characterSkill);
				fighterVO.forceAttack(force == 1);
				CharacterFightController fightController = character.getFightController();
				fightController.fight(fighterVO);
			} else {
				FightMsgSender.sendSkillReleaseFailMsg(character, skillStus);
			}
		}
	}

}
