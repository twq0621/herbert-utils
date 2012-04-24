package net.snake.gamemodel.skill.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.CharacterSkillManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 技能突破
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10275, accessLimit = 200)
public class SkillPoProcessor10275 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int skillid = request.getInt();
		CharacterSkillManager csm = character.getCharacterSkillManager();
		CharacterSkill characterskill = csm.getCharacterSkillById(skillid);
		if (characterskill != null) {
			csm.characterSkillPo(skillid);
		}
	}
}
