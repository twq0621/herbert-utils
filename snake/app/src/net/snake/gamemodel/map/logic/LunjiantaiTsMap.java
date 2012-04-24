package net.snake.gamemodel.map.logic;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import net.snake.consts.ClientConfig;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.monster.bean.SceneMonster;

/**
 * 论剑台地图
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class LunjiantaiTsMap extends GameMap {
	private static final Logger logger = Logger.getLogger(LunjiantaiTsMap.class);
	public static boolean isOpentTime = false; // false 表示没有处于攻城时间内 true表示处于攻城状态
	public static int startHours = 18;
	public static int endHours = 19;
	public static int LunjianTai_line = 3;
	public static int lunjiantaiSceneId = 20196;
	public static int activityType = 56;

	@Override
	public void enterScene(Hero player) {
		if (!isOpentTime || LunjianTai_line != player.getVlineserver().getLineid() || !LinshiActivityManager.getInstance().isTimeByLinshiActivityID(activityType)) {
			Scene scene = player.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
			ExchangeMapTrans.trans(scene, scene.getReliveX(), scene.getReliveY(), player);
			return;
		}
		super.enterScene(player);
	}

	@Override
	public void leaveScene(Hero character, Scene newScene) {
		super.leaveScene(character, null);
	}

	@Override
	public void update(long now) {
		try {
			if (this.getLineId() != LunjianTai_line) {
				return;
			}
			super.update(now);
			int hours = getTodayHourse();
			if (hours >= startHours && hours < endHours) {
				if (!isOpentTime) {
					isOpentTime = true;
					sendLunjiantaiMsgToAllRole();
				}
			}
			if (!isOpentTime) {
				return;
			}
			if (hours >= endHours) {
				isOpentTime = false;
				clearLunjiantaiDateWhenEndActivity();
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private void clearLunjiantaiDateWhenEndActivity() {
		Collection<SceneMonster> collectionM = this.getAllCurrentSceneMonster();
		for (SceneMonster monster : collectionM) {
			monster.setNowHp(0);
			monster.setObjectState(VisibleObjectState.Die);
		}
		Collection<Hero> collection = getAllCharacters();
		if (collection.size() == 0) {
			return;
		}
		for (Hero character : collection) {
			exchargeToXiangyang(character);
		}
	}

	/**
	 * 论剑台开启触发
	 */
	public void sendLunjiantaiMsgToAllRole() {
		// GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun()
		// {
		// @Override
		// public void run(Character character) {
		// character.getMyNewcomeManager().sendLunjianTaiActivityMsg();
		// }
		// });
	}

	public int getTodayHourse() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		return hours;
	}

	/**
	 * 传出到襄阳城
	 * 
	 * @param character
	 */
	public void exchargeToXiangyang(Hero character) {
		Scene newScene = character.getVlineserver().getSceneManager().getScene(ClientConfig.Scene_Xianjing);
		short[] point = newScene.getRandomPoint(newScene.getReliveX(), newScene.getReliveY(), 7);
		ExchangeMapTrans.trans(newScene, point[0], point[1], character);
	}
}
