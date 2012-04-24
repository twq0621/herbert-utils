package net.snake.gamemodel.skill.processor;

import net.snake.ai.fight.upgrade.response.QuerySkillPoInfoMsg10292;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.CharacterSkillManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 查询突破明细
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10291, accessLimit = 500)
public class QuerySkillPoInfoProcessor10291 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int skillid = request.getInt();
		CharacterSkillManager csm = character.getCharacterSkillManager();
		CharacterSkill characterskill = csm.getCharacterSkillById(skillid);
		if (characterskill != null) {
			character.sendMsg(new QuerySkillPoInfoMsg10292(characterskill));
		}
	}

}
