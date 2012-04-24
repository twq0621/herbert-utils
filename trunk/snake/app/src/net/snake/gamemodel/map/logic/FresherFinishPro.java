package net.snake.gamemodel.map.logic;

import java.util.Collection;
import java.util.Iterator;

import net.snake.ai.formula.CharacterFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.consts.GoodsContainerType;
import net.snake.consts.Popsinger;
import net.snake.consts.Position;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.task.logic.action.TaskActionFactory;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode=50693)
public class FresherFinishPro extends MsgProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = session.getCurrentCharacter(Hero.class);
		Scene scene = hero.getSceneRef();
		if(scene.getInstanceModelId()!=16){
			return ;
		}
		
		Scene newScene = hero.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
		short[] point = newScene.getRandomPoint(newScene.getEnterX(), newScene.getEnterY(), 7);
		ExchangeMapTrans.trans(newScene, point[0],  point[1], hero);
		
		hero.getEffectController().clearAllEffectListAndRemoveBuffOnBody();
		
		// clear bag
		Collection<CharacterGoods> goods=hero.getCharacterGoodController().getBagGoodsContiner().getGoodsList();
		for (Iterator<CharacterGoods> iterator=goods.iterator();iterator.hasNext();) {
			CharacterGoods good=iterator.next();
			hero.getCharacterGoodController().deleteCharacterGoods(good);
		}
		
		goods=hero.getCharacterGoodController().getBodyGoodsList();
		if (goods.size()>0) {
			// move it to bag
			for (Iterator<CharacterGoods> iterator=goods.iterator();iterator.hasNext();) {
				CharacterGoods good=iterator.next();
				hero.getCharacterGoodController().movePosition(good.getPosition(),0,GoodsContainerType.onBag,(short)0,0);
			}
			// clear bag
			goods=hero.getCharacterGoodController().getBagGoodsContiner().getGoodsList();
			for (Iterator<CharacterGoods> iterator=goods.iterator();iterator.hasNext();) {
				CharacterGoods good=iterator.next();
				hero.getCharacterGoodController().deleteCharacterGoods(good);
			}
		}
		
		
		CharacterGoods cg =null;
		int pops=hero.getPopsinger();
		if (pops==Popsinger.futu.getPopsinger()) {
			cg =CharacterGoods.createCharacterGoods(1, 90120006, 0);
		}else if (pops==Popsinger.guming.getPopsinger()) {
			cg =CharacterGoods.createCharacterGoods(1, 90110001, 0);
		}else if (pops == Popsinger.miaoyu.getPopsinger()) {
			cg =CharacterGoods.createCharacterGoods(1, 90140016, 0);
		}else {
			cg =CharacterGoods.createCharacterGoods(1, 90130011, 0);
		}
		hero.getCharacterGoodController().addGoodsToBag(cg);
		
		goods=hero.getCharacterGoodController().getBagGoodsContiner().getGoodsList();
		for (Iterator<CharacterGoods> iterator=goods.iterator();iterator.hasNext();) {
			CharacterGoods good=iterator.next();
			if (good.getGoodModel().getPosition()==Position.POSTION_WUQI) {
				hero.getCharacterGoodController().movePosition(good.getPosition(),0,GoodsContainerType.onBody,good.getGoodModel().getPosition(),0);
				break;
			}
		}
		
//		cg = CharacterGoods.createCharacterGoods(1, 89100001, 0);
//		hero.getCharacterGoodController().addGoodsToBag(cg);
		
		cg = CharacterGoods.createCharacterGoods(1, 81000126, 0);
		hero.getCharacterGoodController().addGoodsToBag(cg);
		
		
		CharacterFormula.experienceProcess(hero, 1610);
		
		hero.setJiaozi(0);
		CharacterPropertyManager.changeLijin(hero, 0);
		
		hero.getMyNewcomeManager().addOrUpdateNewGuide((byte)1, (byte)1,(byte) 1,(byte) 1);
		
		CharacterTaskController taskController = hero.getTaskController();
		Task task = TaskManager.getInstance().getCacheTaskMap().get(89903);
		TaskActionFactory.getInstance().createAction(task, taskController).acceptForTest();
		

	}
}
