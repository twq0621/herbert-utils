package net.snake.gamemodel.instance.listener;

import net.snake.api.IAttackListener;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 室内副本拦截npc的攻击
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class DazhaishineiAttackListener implements IAttackListener {
	@Override
	public boolean beforeAttack(VisibleObject killer, VisibleObject behurted) {
		Scene scene = killer.getSceneRef();
		int instanceId = scene.getInstanceModelId();
		if (instanceId != 14&&instanceId != 20) {
			return true;
		}
		if (killer.getSceneObjType() !=SceneObj.SceneObjType_MonsterScene) {
			return true;
		}
		InstanceController  controller = scene.getInstanceController();
		SceneMonster monster = (SceneMonster)killer;
		SceneMonster npc = (SceneMonster)controller.getAttribute("protected");
		
		if (npc.getId().intValue() != monster.getId().intValue()) {
			return true;
		}
		return false;
	}

	@Override
	public void oneAttacked(VisibleObject killer, VisibleObject behurted) {
	}
}
