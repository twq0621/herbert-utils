package net.snake.gamemodel.map.response.monster;


import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.netio.ServerResponse;



/**
 * simpleMonster(角色ID(int),模型ID(int),等级(short).当前HP(int),logicPoint)
 * 
 * 状态byte(0死亡,1活着)，如果死亡{可以捡物品的角色数量(byte), 可以捡物品的角色id(int)*,(是否有包裹(0没有，1有)}
 * 是否有可显示的技能id(byte)(0无1有), 身上主效果技能id(String)
 * 
 */
public class MonsterBangqiEnterEyeShot10036 extends ServerResponse {
	public MonsterBangqiEnterEyeShot10036(SceneBangqiMonster sceneMonster) {
		setMsgCode(10036);
		try {
			ServerResponse out = this;
			MonsterModel monsterModel = sceneMonster.getMonsterModel();
			out.writeInt(sceneMonster.getId());
			out.writeInt(monsterModel.getId());
			out.writeShort(monsterModel.getGrade());
			//out.writeShort(monsterModel.getMovespeed());
			out.writeInt(sceneMonster.getNowHp());
			out.writeInt(sceneMonster.getMaxHp());
			out.writeShort(sceneMonster.getX());
			out.writeShort(sceneMonster.getY());
//			if (sceneMonster.getNowHp() <= 0) {
//				out.writeByte(0);
//				// out.writeBoolean(sceneMonster.isHaveDropGoods());
//			} else {
//				out.writeByte(1);
//			}
//			out.writeBoolean(sceneMonster.isAttackcolor());
//			out.writeBoolean(sceneMonster.isDefencecolor());
//			out.writeBoolean(sceneMonster.isExposecolor());
//			out.writeBoolean(sceneMonster.isDodgecolor());
//			out.writeBoolean(sceneMonster.isHpcolor());
//			out.writeBoolean(sceneMonster.isMoneycolor());

//			List<EffectInfo> t = sceneMonster.getEffectController()
//					.getBuffList();
//			out.writeByte(t.size());
//			for (EffectInfo bse : t) {
//				out.writeInt(bse.getId());
//			}
		//	out.writeByte(monsterModel.getAbnormal());
					Faction faction = sceneMonster.getSceneBangqi()
							.getFactionController().getFaction();
					out.writeInt(faction.getId());
					out.writeUTF(faction.getViewName());
					out.writeUTF(faction.getBangqiName());
					FactionFlag flag=faction.getFactionFlag();
					out.writeByte(flag.getfGrade());
					out.writeInt(flag.getExpBufferId());
					out.writeInt(flag.getfJiachengExp());
					out.writeInt(flag.getfJiachengZheqi()/2);
			// sceneMonster.getEffectController().sendAllOnBodyBuffMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}

}
