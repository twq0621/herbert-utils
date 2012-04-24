package net.snake.gamemodel.monster.logic;

import java.util.List;

import org.apache.log4j.Logger;

import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.guide.response.NewGuideDropGoodMsg50672;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.lostgoods.Lostgoods;
import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;

/**
 * 怪物物品掉落管理器(此管理器与场景中怪物绑定 怪物死亡通过此管理器完成掉落逻辑)
 * 
 * @author serv_dev
 * 
 */
public class SceneMonsterDropGoodManager {
	private final static Logger logger = Logger.getLogger(SceneMonsterDropGoodManager.class);
	private SceneMonster sceneMonster;
	private long dropGoodTime;
	private int count = 0;
	private Hero character;// 仇恨值最高的玩家

	public SceneMonsterDropGoodManager(SceneMonster sceneMonster) {
		this.sceneMonster = sceneMonster;
		this.count = 0;
	}

	/**
	 * 怪物死亡物品掉落
	 */
	public void dropGoodsToScen(Hero character) {
		this.dropGoodTime = System.currentTimeMillis();
		this.count = 0;
		this.character = character;
		List<CharacterGoods> dropGoods = sceneMonster.getMonsterModel().dropGoods(character, sceneMonster.getDropGoodJiacheng());
		SceneMonster dropmonster = sceneMonster.getMonsterModel().dropMonsters(character);
		monneyMonsterDropMoneyToScene();

		if (dropGoods.size() != 0) {
			for (CharacterGoods cg : dropGoods) {
				SceneDropGood sceneGoods = new SceneDropGood(cg, character, sceneMonster);
				// logger.info("cg=="+cg.getGoodModel()==null?"":cg.getGoodModel().getName()+"_"+cg.getGoodModel().getId());
				sceneMonster.getSceneRef().enterScene(sceneGoods);
			}
			if (character != null) {
				if (character.getDropGood() == null || character.getDropGood() <= 0) {
					character.setDropGood(1);
					character.sendMsg(new NewGuideDropGoodMsg50672());
				}
			}
		}
		if (dropmonster == null) {
			return;
		}
		Scene scene = sceneMonster.getSceneRef();
		short[] dropPoint = scene.getRandomPoint(sceneMonster.getX(), sceneMonster.getY(), 7);
		SceneMonsterHelper.createMonsterToScene(dropmonster.getModel(), dropPoint[0], dropPoint[1], false, scene);
	}

	/**
	 * 金钱怪物品额外物品掉落
	 * 
	 * @param character
	 */
	private void monneyMonsterDropMoneyToScene() {
		if (!sceneMonster.isMoneycolor()) {
			return;
		}
		int abnormal = GenerateProbability.randomIntValue(4, 6);
		if (abnormal < 1) {
			return;
		}
		for (int i = 0; i < abnormal; i++) {
			int rand = sceneMonster.getGrade() * 10;
			CharacterGoods cg = new CharacterGoods();
			cg.setGoodmodelId(-1);
			cg.setCount(rand);
			SceneDropGood sceneGoods = new SceneDropGood(cg, character, sceneMonster);
			sceneMonster.getSceneRef().enterScene(sceneGoods);
		}
	}

	/**
	 * 进入到计时 boss掉落物品
	 * 
	 * @param character
	 */
	public boolean dropBossGoodToScene(Hero character) {
		Lostgoods lostGoods = sceneMonster.getMonsterModel().getBossDropGoods();
		if (lostGoods == null) {
			logger.info("lost goods is null");
			return false;
		}
		long now = System.currentTimeMillis();
		if (count > 9) {
			logger.info("count is > 9 ,count is =" + count);
			return false;
		}
		logger.info("drop good time is " + (now - dropGoodTime));
		if (now - dropGoodTime < 1000) {
			logger.info("now - dropGoodTime < 1000");
			return false;
		}
		CharacterGoods cg = lostGoods.dropLostGoods(character, sceneMonster.getMonsterModel(), sceneMonster.getDropGoodJiacheng());
		if (cg == null) {
			return false;
		}
		count++;
		dropGoodTime = System.currentTimeMillis();
		SceneDropGood sceneGoods = new SceneDropGood(cg, this.character, sceneMonster);
		sceneMonster.getSceneRef().enterScene(sceneGoods);
		return true;
	}

	public void resetItemIndex() {
		Lostgoods lostGoods = sceneMonster.getMonsterModel().getBossDropGoods();
		if (lostGoods == null) {
			logger.info("lost goods is null");
			return;
		}
		lostGoods.resetItemIndex();
	}

	public void setCharacter(Hero character) {
		this.character = character;
	}

}
