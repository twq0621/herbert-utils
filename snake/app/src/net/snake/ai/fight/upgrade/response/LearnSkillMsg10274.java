package net.snake.ai.fight.upgrade.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.ServerResponse;

/**
 * 学习技能
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class LearnSkillMsg10274 extends ServerResponse {
	public LearnSkillMsg10274(CharacterSkill characterSkill, Hero character) {
		setMsgCode(10274);
		writeInt(characterSkill.getSkillId());
		writeShort(character.getWuxueJingjie());
		writeInt(characterSkill.getGrade());
		SkillUpgradeExp skillUpgradeExp = characterSkill.upgradeNeedZhenqi();
		int zhenqi = 0;
		int cash = 0;
		if (skillUpgradeExp != null) {
			zhenqi = skillUpgradeExp.getExpZhengqi();
			cash = skillUpgradeExp.getExpCash();
		}
		writeInt(zhenqi);
		writeInt(cash);
		writeInt(characterSkill.getGrade() + 1);
		// /*是否为瓶颈技能(byte)(0非),如果为瓶颈的技能＋{当前技能是否突破了瓶颈(byte)(0非),突破瓶颈所需物品(str)格式:({goodmodelid,goodnum;}*),突破所需真气(int),突破所需铜币(int),突破后冷却时间(int)(单位小时),突破概率(int)(单位1/10000)}
		// *
		// * */

	}
}
