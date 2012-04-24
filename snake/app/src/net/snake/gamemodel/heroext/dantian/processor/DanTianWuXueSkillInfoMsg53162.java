package net.snake.gamemodel.heroext.dantian.processor;

import java.io.IOException;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.logic.BaseSkillManager;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.ServerResponse;



public class DanTianWuXueSkillInfoMsg53162 extends ServerResponse{
	
	/**
	 * @param cdtid 角色丹田ID
	 * 
	 * @param skillmanager 玩家技能管理器
	 * @param skillitem	丹田可学的武学
	 * @throws IOException 
	 */
	
	public DanTianWuXueSkillInfoMsg53162(int cdtid,BaseSkillManager<Hero> skillmanager,String[] skillitem) throws IOException{
		setMsgCode(53162);
//		角色丹田ID(byte),请求的丹田ID(byte),增加攻击(int),增加防御(int),增加暴击(int)，增加闪避(int)，
//		增加生命上限(int)，增加内力上限(int)，增加体力上限(int)，增加攻击速度(int)，增加移动速度(int)，
//		攻击增强比例(int)，忽视防御比例(int),所有门派绝学加成(int),所有江湖绝学加成(int),所有其它绝学加成(int),
//		技能数(byte){技能ID(int),是否己学(byte 1是 0不是)}
		writeByte(cdtid);
		writeByte(skillitem==null?0:skillitem.length);
		for (String string : skillitem) {
			int parseInt = Integer.parseInt(string);
			writeInt(parseInt);
			CharacterSkill charskill = skillmanager.getCharacterSkillById(parseInt);
			writeByte(charskill==null?0:1);
		}
	}
}
