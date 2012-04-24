package net.snake.gamemodel.map.response.monster;

import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.netio.ServerResponse;

/**
 * simpleMonster(角色ID(int),模型ID(int),等级(short).当前HP(int),logicPoint)
 * 
 * 状态byte(0死亡,1活着)，如果死亡{可以捡物品的角色数量(byte), 可以捡物品的角色id(int)*,(是否有包裹(0没有，1有)}
 * 是否有可显示的技能id(byte)(0无1有), 身上主效果技能id(String)
 * 
 */
public class MonsterFactionCtEnterEyeShot10038 extends ServerResponse {
	public MonsterFactionCtEnterEyeShot10038(SceneMonster sceneMonster) {
		setMsgCode(10038);
		ServerResponse out = this;
		MonsterModel monsterModel = sceneMonster.getMonsterModel();
		out.writeInt(sceneMonster.getId());
		out.writeInt(monsterModel.getId());
		out.writeShort(sceneMonster.getX());
		out.writeShort(sceneMonster.getY());

	}

}
