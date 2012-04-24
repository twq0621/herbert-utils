package net.snake.gamemodel.instance.listener;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.api.IHurtListener;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 监牢
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class JianlaoHurtListener implements IHurtListener {
	@Override
	public boolean beforeHurt(VisibleObject killer, VisibleObject behurted, FighterVO vo, int hurtValue, boolean nohurt) {
		if (behurted.getSceneObjType() != VisibleObject.SceneObjType_MonsterScene) {
			return true;
		}

		Scene scene = behurted.getSceneRef();
		if (scene.getInstanceModelId() != 11&&scene.getInstanceModelId() != 18) {
			return true;
		}
		int behurtedId = behurted.getId();
		SceneMonster npc = (SceneMonster) scene.getInstance().getAttribute("protected");
		if (npc.getId().intValue() == behurtedId && killer.getSceneObjType() == VisibleObject.SceneObjType_Hero) {
			return false;
		}
		return true;
	}

	@Override
	public void onBehurted(VisibleObject killer, VisibleObject behurted, FighterVO vo, int hurtValue) {

	}
}
