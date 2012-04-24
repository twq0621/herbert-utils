package net.snake.across.character.msg;

import net.snake.gamemodel.monster.logic.SceneXuanyuanMonster;
import net.snake.netio.ServerResponse;


/**
 * 轩辕剑帮会归属 
 */

public class XuanyuanjianBelongToMsg51152 extends ServerResponse {
	public XuanyuanjianBelongToMsg51152(SceneXuanyuanMonster[] sceneXuanyuanMonsters) {
		this.setMsgCode(51152);
		for (int i = 0; i < sceneXuanyuanMonsters.length; i++) {
			this.writeInt(sceneXuanyuanMonsters[i]
								.getBelongToFaction());
		}
	}
}
