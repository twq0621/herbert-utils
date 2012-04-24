package net.snake.gamemodel.pet.response;

import net.snake.netio.ServerResponse;
/**
 * 	s-->c	60036	相应	灵宠ID(int),位置（byte）,灵宠技能等级(int),是否满级(byte)(0 非顶级 1 顶级), 0 非顶级{当前熟练度(int),下一级熟练度(int),需要灵宠等级(int),消耗真元(int),消耗铜币(int)}
 * @author jack
 *
 */
public class HorseSkillUpgradeResponse  extends ServerResponse{
	
	/**
	 * 灵宠ID(int),灵宠技能ID(int),灵宠技能等级(int),满级1(byte)
	 */
	public HorseSkillUpgradeResponse(int horseId,int skillPos,int grade){
		setMsgCode(60036);
		writeInt(horseId);
		writeByte(skillPos);
		writeInt(grade);
		writeByte(1);
	}
	/**
	 * 灵宠ID(int),灵宠技能ID(int),灵宠技能等级(int),0 非顶级(byte),当前熟练度(int),下一级熟练度(int),消耗真元(int),消耗铜币(int)
	 */
	public HorseSkillUpgradeResponse(int horseId,int skillPos,int grade,int s,int nexts,int horseGrade,int zhenqi,int copper){
		setMsgCode(60036);
		writeInt(horseId);
		writeByte(skillPos);
		writeInt(grade);
		writeByte(0);
		writeInt(s);
		writeInt(nexts);
		writeInt(horseGrade);
		writeInt(zhenqi);
		writeInt(copper);
	}
	

}
