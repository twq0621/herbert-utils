package net.snake.gamemodel.map.response.monster;

import java.util.List;

import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.netio.ServerResponse;


/**
 * simpleMonster(角色ID(int),模型ID(int),等级(short).当前HP(int),logicPoint)
 * 
 * 状态byte(0死亡,1活着)，如果死亡{可以捡物品的角色数量(byte), 可以捡物品的角色id(int)*,(是否有包裹(0没有，1有)}
 * 是否有可显示的技能id(byte)(0无1有), 身上主效果技能id(String)
 * 
 */
public class MonsterEnterEyeShot10032 extends ServerResponse {
	public MonsterEnterEyeShot10032(SceneMonster sceneMonster) {
		setMsgCode(10032);
		try {
			ServerResponse out = this;
			MonsterModel monsterModel = sceneMonster.getMonsterModel();
			out.writeInt(sceneMonster.getId());
			out.writeInt(monsterModel.getId());
			out.writeShort(sceneMonster.getGrade()==0?monsterModel.getGrade():sceneMonster.getGrade());
			String name=sceneMonster.geReplacetName();
			if(name!=null&&name.length()!=0)
			{
				out.writeByte(1);
				out.writeUTF(name);
			}else {
				out.writeByte(0);
			}
			out.writeShort(sceneMonster.getPropertyAdditionController().getExtraMoveSpeed());
			out.writeInt(sceneMonster.getNowHp());
			out.writeInt(sceneMonster.getPropertyAdditionController().getExtraMaxHp());
			out.writeShort(sceneMonster.getX());
			out.writeShort(sceneMonster.getY());
			if (sceneMonster.getNowHp() <= 0) {
				out.writeByte(0);
				// out.writeBoolean(sceneMonster.isHaveDropGoods());
			} else {
				out.writeByte(1);
			}
			out.writeBoolean(sceneMonster.isAttackcolor());
			out.writeBoolean(sceneMonster.isDefencecolor());
			out.writeBoolean(sceneMonster.isExposecolor());
			out.writeBoolean(sceneMonster.isDodgecolor());
			out.writeBoolean(sceneMonster.isHpcolor());
			out.writeBoolean(sceneMonster.isMoneycolor());
			out.writeInt(sceneMonster.getMonsterModel().getExper() > 0 ? (sceneMonster.getExp() / sceneMonster.getMonsterModel().getExper()) : 0);

			List<EffectInfo> t = sceneMonster.getEffectController()
					.getBuffList();
			out.writeByte(t.size());
			for (EffectInfo bse : t) {
				out.writeInt(bse.getId());
			}
			out.writeByte(monsterModel.getAbnormal());
			// sceneMonster.getEffectController().sendAllOnBodyBuffMsg();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}

}
