package net.snake.across.faction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.hero.bean.Hero;


/**
 * 跨服帮会管理 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */

public class AcrossFactionManager {

	public Map<String, AcrossFactionContoller> map = new ConcurrentHashMap<String, AcrossFactionContoller>();

	public synchronized void addCharacterToFaction(Hero character) {
		AcrossEtc ae = character.getCharacterAcrossServerManager().getAce();
		if (ae == null) {
			return;
		}
		String key = ae.getOldGangAreaId() + "_" + ae.getOldGangId();
		AcrossFactionContoller afc = map.get(key);
		if (afc == null) {
			afc = new AcrossFactionContoller(key, ae);
			map.put(key, afc);
		}
		afc.addAcrossFaction(character);

	}

	public synchronized void removeFaction(String key) {
		map.remove(key);
	}

	public AcrossFactionContoller getAcrossFactionContollerByKEY(String key) {
		return map.get(key);
	}

	public void clearFaction() {
		for (AcrossFactionContoller afc : map.values()) {
			if (afc != null) {
				afc.clear();
			}
		}
		map.clear();
	}
}
