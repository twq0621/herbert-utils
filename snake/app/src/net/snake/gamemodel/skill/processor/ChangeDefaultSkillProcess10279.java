package net.snake.gamemodel.skill.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

import org.apache.log4j.Logger;

/**
 * 改变人物默认技能
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10279, accessLimit = 200)
public class ChangeDefaultSkillProcess10279 extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(ChangeDefaultSkillProcess10279.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		boolean tf = true;
		if (tf) {
			logger.info("改变人物默认技能 废除--");
			return;
		}
		// int skillId = request.getInt();
		// if (skillId > 0) {
		// CharacterSkill characterSkill;
		// Skill skill = SkillManager.getInstance().getSkillById(skillId);
		// if (skill.getType() == 1) {
		// return;// 不可设置为默认的技能
		// }
		// if (skill.getSkilltype() == 2) {
		// characterSkill = character.getCharacterSkillManager().getCharacterSkillById(skillId);
		// if (skillId != character.getSkillid()) {
		// character.setSkillid(skillId);
		// character.getFightController().setDefaultSkill(characterSkill);
		// }
		// character.sendMsg(new ChangeSkillMsg10280(character.getSkillid()));
		// CharacterOnHoorController characterOnHoorController = character.getCharacterOnHoorController();
		// if (!characterOnHoorController.isAutoOnHoor() && skillId != characterOnHoorController.getCharacterOnHoorConfig().getCharSkillId()) {
		// characterOnHoorController.getCharacterOnHoorConfig().setCharSkillId(skillId);
		// characterOnHoorController.saveOnHoorConfig();
		// character.sendMsg(new AfkConfigMsg50592(characterOnHoorController.getCharacterOnHoorConfig(), character.getCharacterOnHoorController()));
		// }
		// } else {
		// logger.warn("技能:{}id:{} 不能人物技能{}", new Object[] { skillId });
		// }
		// }
	}
}
