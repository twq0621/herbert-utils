package net.snake.gamemodel.heroext.dantian.response;

import net.snake.netio.ServerResponse;



public class InfoMsg53152 extends ServerResponse{
//	
//	/**
//	 * @param cdtid 角色丹田ID
//	 * 
//	 * @param rdtid 请求丹田ID
//	 * @param addattack 增加攻击(int)
//	 * @param adddefence ,增加防御(int),
//	 * @param addcrt 增加暴击(int)
//	 * @param adddodge ，增加闪避(int)
//	 * @param addmaxhp ，增加生命上限(int)，
//	 * @param mengpaijuexue 所有门派绝学加成(int), 2
//	 * @param janghujuexue 所有江湖绝学加成(int),
//	 * @param qitajuexue 所有其它绝学加成(int)
//	 * @param skillmanager 玩家技能管理器
//	 * @param skillitem	丹田可学的武学
//	 * @throws IOException 
//	 */
//	
//	public InfoMsg53152(int rdtid,int cdtid,int mymp,int isperforation,int podtid,int pomp,int poisperforation,int addattack,int adddefence,int addcrt,int adddodge,int addmaxhp,
//			int mengpaijuexue,String mpskillitem,int janghujuexue,String jhskillitem,int qitajuexue,String qtskillitem,BaseSkillManager<Hero> skillmanager,String[] skillitem) throws Exception{
//		setMsgCode(53152);
////		角色丹田ID(byte),请求的丹田ID(byte),增加攻击(int),增加防御(int),增加暴击(int)，增加闪避(int)，
////		增加生命上限(int)，增加内力上限(int)，增加体力上限(int)，增加攻击速度(int)，增加移动速度(int)，
////		攻击增强比例(int)，忽视防御比例(int),所有门派绝学加成(int),所有江湖绝学加成(int),所有其它绝学加成(int),
////		技能数(byte){技能ID(int),是否己学(byte 1是 0不是)}
//		ServerResponse out = this;
//		out.writeByte(rdtid);
//		out.writeByte(cdtid);
//		out.writeByte(mymp);
//		out.writeByte(isperforation);
//		out.writeByte(podtid);
//		out.writeByte(pomp);
//		out.writeByte(poisperforation);
//		out.writeInt(addattack);
//		out.writeInt(adddefence);
//		out.writeInt(addcrt);
//		out.writeInt(adddodge);
//		out.writeInt(addmaxhp);
//		out.writeInt(mengpaijuexue);
//		out.writeUTF(mpskillitem);
//		out.writeInt(janghujuexue);
//		out.writeUTF(jhskillitem);
//		out.writeInt(qitajuexue);
//		out.writeUTF(qtskillitem);
//		out.writeByte(skillitem==null?0:skillitem.length);
//		for (String string : skillitem) {
//			int parseInt = Integer.parseInt(string);
//			out.writeInt(parseInt);
//			CharacterSkill charskill = skillmanager.getCharacterSkillById(parseInt);
//			out.writeByte(charskill==null?0:1);
//		}
//	}
//	
//	/**
//	 * 错误
//	 */
//	public InfoMsg53152(){
//		setMsgCode(53152);
////		out.writeByte(-1);
//	}
}
