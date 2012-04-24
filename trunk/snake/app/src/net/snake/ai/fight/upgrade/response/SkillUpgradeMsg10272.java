package net.snake.ai.fight.upgrade.response;

import org.apache.log4j.Logger;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.ServerResponse;


/**
 * 技能升级 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class SkillUpgradeMsg10272 extends ServerResponse {
	private static Logger logger = Logger.getLogger(SkillUpgradeMsg10272.class);

	public SkillUpgradeMsg10272(CharacterSkill characterskill, Hero character) {
		setMsgCode(10272);
		writeInt(characterskill.getSkillId());
		writeShort(character.getWuxueJingjie());
		writeInt(characterskill.getGrade());
		SkillUpgradeExp skillUpgradeExp = characterskill.upgradeNeedZhenqi();
		int zhenqi = 0;
		int cash = 0;
		if (skillUpgradeExp != null) {
			zhenqi = skillUpgradeExp.getExpZhengqi();
			cash = skillUpgradeExp.getExpCash();
		} else {
			logger.warn(characterskill.getSkillId() + " can not find skillUpgradeExp");
			return;
		}
		writeInt(zhenqi);
		writeInt(cash);
		writeInt(skillUpgradeExp.getLimitGrade());
		writeBoolean(skillUpgradeExp.getPinjin() == 1 ? true : false);

	}
}
