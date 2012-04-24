package net.snake.gamemodel.dujie.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.dujie.bean.Dujie;
import net.snake.gamemodel.dujie.logic.DujieCtrlor;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 进入渡劫副本开始准备护法倒计时
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode = 60303)
public class DujieRequestProc extends MsgProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = session.getCurrentCharacter(Hero.class);
		boolean in = hero.getSceneRef().isInstanceScene();
		if (in) {
			return ;
		}
		
		DujieCtrlor dujieCtrlor = hero.getDujieCtrlor();
		int current = dujieCtrlor.currentTianjieNum();
		if (current > 13) {
			return;
			// 没有可用
		}

		int stat = dujieCtrlor.currentTianjieStat();
		if (stat != DujieCtrlor.Lvl_Unlock) {
			return;
		}
		Dujie dujie = DujieDataTbl.getInstance().getDujie(current);
		Integer sceneId = dujie.getScene();
		Scene scene = hero.getVlineserver().getSceneManager().getInstanceScene(sceneId);
		if (scene == null) {
			return;
		}
		hero.getMyCharacterInstanceManager().openInstance(scene, true);

	}

}
