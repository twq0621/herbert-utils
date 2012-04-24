package net.snake.gamemodel.skill.logic.buff.spouse;

import net.snake.GameServer;
import net.snake.ai.astar.Point;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.util.BrokenLine;
import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 
 * 比翼双飞buff
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class BiYiShuangFei extends Buff {

	public BiYiShuangFei(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		/*
		 * 将使用者立即传送到其配偶身后一格。
			如果是其身后一格是阻挡格，则以其配偶为圆点寻找离其最近的一个非阻挡格进行传送。
			传送时在使用者身上出现传送开始光效，传送到达后在使用者身上出现传送到达光效。
		 */
		VisibleObject vo = effect.getAttacker();
		if (vo.getSceneObjType()==SceneObj.SceneObjType_Hero) {
			Hero character = (Hero)vo;
			
			RoleWedingManager roleWedingManager = character.getMyFriendManager().getRoleWedingManager();
			Hero wedder = GameServer.vlineServerManager.getCharacterById(roleWedingManager.getWedderId());
//			if (wedder == null) {
//				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20116));
//				return false;
//			}
//			long now = System.currentTimeMillis();
//			if (character.inFighting(now)) {
//				long shenyuTime = (ClientConfig.FightingTime - (now - character.getFightController().getAttackModelBeginTime())) / 1000;
//				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,830,shenyuTime == 0 ? 1 : shenyuTime));
//				return false;
//			}
//			
//			if (wedder.getSceneRef().isInstanceScene()) {
//				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20117));
//				return false;
//			}
			
			Point p = randomTranPoint(wedder);
			if (p == null) {
				return false;
			}
			ExchangeMapTrans.transWithChangeLine(character, wedder.getVlineserver(), wedder.getSceneRef(),p.getX(),p.getY());
		} else {
			return false;
		}
		return false;
	}

	@Override
	public boolean leave(EffectController controller) {
		return true;
	}
	
	private Point randomTranPoint(Hero character){
		Scene scene = character.getSceneRef();
		for (int i = 1; i < 10; i++) {
			Point p = BrokenLine.getAroundPoint(character.getX(), character.getY(), i, scene.getBlockTiles(), null);
			if (p != null) {
				return p;
			}
		}
		return null;
	}

}
