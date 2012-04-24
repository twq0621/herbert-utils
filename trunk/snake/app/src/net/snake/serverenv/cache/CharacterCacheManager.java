package net.snake.serverenv.cache;

import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;

import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;

/**
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class CharacterCacheManager {
	private Map<Integer, CharacterCacheEntry> characterMap;
	private Map<String, CharacterCacheEntry> nameCharacterMap;

	private static CharacterCacheManager instance = new CharacterCacheManager();

	private CharacterCacheManager() {
	}

	public static CharacterCacheManager getInstance() {
		return instance;
	}

	public void init() {
		characterMap = new ConcurrentHashMap<Integer, CharacterCacheEntry>(CharacterManager.getInstance().getCharacterMap());
		nameCharacterMap = new ConcurrentHashMap<String, CharacterCacheEntry>();
		for (CharacterCacheEntry cce : characterMap.values()) {
			nameCharacterMap.put(cce.getName(), cce);
		}
	}

	public Map<Integer, CharacterCacheEntry> getCharacterMap() {
		return characterMap;
	}

	public CharacterCacheEntry getCharacterCacheEntryById(int characterId) {
		return characterMap.get(characterId);
	}

	public void addCharacterCacheEntry(CharacterCacheEntry character) {
		characterMap.put(character.getId(), character);
		nameCharacterMap.put(character.getName(), character);
	}

	public void removeById(int id) {
		CharacterCacheEntry cce = characterMap.remove(id);
		if (cce != null) {
			nameCharacterMap.remove(cce.getName());
		}
	}

	public void updateCharacterCacheEntry(Hero character) {
		CharacterCacheEntry cce = getCharacterCacheEntryById(character.getId());
		if (cce == null) {
			cce = new CharacterCacheEntry(character);
			addCharacterCacheEntry(cce);
		} else {
			cce.setGrade(character.getGrade());
			cce.setBossKill(character.getBossKill());
			cce.setChannelXuewei(character.getChannelXuewei());
			cce.setChengjiuPoint(character.getChengjiuPoint());
			cce.setChenzhanshengwang(character.getChengzhanShengWang());
			cce.setWuxueJingjie(character.getWuxueJingjie());
			cce.setHeadimg(character.getHeadimg());
			cce.setDantiangrade(character.getDantiangrade());
		}
	}

	public Map<String, CharacterCacheEntry> getNameCharacterMap() {
		return nameCharacterMap;
	}

	public CharacterCacheEntry getCharacterCacheEntryByName(String name) {
		return nameCharacterMap.get(name);
	}
}
