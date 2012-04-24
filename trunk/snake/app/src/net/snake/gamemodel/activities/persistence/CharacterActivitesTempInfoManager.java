/**
 * 
 */
package net.snake.gamemodel.activities.persistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.gamemodel.activities.bean.Activities;

/**
 * 角色 领取活动奖励临时缓存 不做数据哭操作（目的是防止操过快而出现重复领取） Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class CharacterActivitesTempInfoManager {
	private Map<Integer, Activities> map = new ConcurrentHashMap<Integer, Activities>();

	public void destory() {
		if (map != null) {
			map.clear();
			map = null;
		}
	}

	/**
	 * @param character2
	 */
	public CharacterActivitesTempInfoManager() {
	}

	public void addActivities(Activities activities) {
		Activities temp = map.get(activities.getType());
		if (temp == null) {
			temp = activities;
			map.put(activities.getType(), activities);
		} else {
			temp.setCount(activities.getCount());
		}
	}

	public int getCountByType(int type) {
		Activities temp = map.get(type);
		if (temp == null) {
			return 0;
		}
		return temp.getCount();
	}
}
