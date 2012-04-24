package net.snake.gamemodel.monster.logic;

/**
 * 
 * 怪物马管理
 * @author serv_dev
 */
import java.util.Set;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.GameMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.catchpet.CatchHorseOK50046;
import net.snake.gamemodel.pet.logic.HorseCreator;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;

import org.apache.mina.util.ConcurrentHashSet;

public class WhoCatchMeManager {
	private final SceneMonster scenemonster;
	private Set<Hero> charactersList = new ConcurrentHashSet<Hero>();

	public WhoCatchMeManager(SceneMonster scenemonster) {
		this.scenemonster = scenemonster;
	}

	public Set<Hero> getCharactersList() {
		return charactersList;
	}

	/** 增加捕获者 **/
	public void addCatcher(Hero character) {
		charactersList.add(character);
	}

	/** 移除捕获者 **/
	public void removeCatcher(Hero character) {
		charactersList.remove(character);
	}

	public void removeAllCatcher() {
		charactersList.clear();
	}

	/** 我的捕获时间到了,激发最后的捕获判断 如果成功则为角色创建坐骑,并从场景中移除此怪物,注,但不从 ***/
	public void catchTimeOK(Hero character) {
		if (charactersList.contains(character)) {
			// 捕获成功添加数据库，还差一个角色马的更新
			Scene scene = scenemonster.getSceneRef();
			scene.leaveScene(scenemonster);
			if (scenemonster.getRelive() == 1) {
				((GameMap) scene).getRefreshMonsterController().addSceneMonster(scenemonster);
			}
			HorseCreator.createCharacterHorse(character, scenemonster.getMonsterModel().getHorseModelId());
			character.sendMsg(new CatchHorseOK50046(scenemonster.getId()));
			HorseModel horseModel = HorseModelDataProvider.getInstance().getHorseModelByID(scenemonster.getMonsterModel().getHorseModelId());
			character.getMyCharacterAchieveCountManger().getHorseCount().catchHorse(horseModel);
			character.getDayInCome().dealGethorse(1);
		}
		// 一个人成功之后通知别人失败
		for (Hero c : charactersList) {
			removeCatcher(c);
			if (c != character) {
				c.getCatchHorseActionController().breakCatch();
			}
		}
	}

	public void destory() {
		if (charactersList != null) {
			charactersList.clear();
		}

	}
}
