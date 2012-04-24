package net.snake.gamemodel.skill.persistence;

import java.util.List;

import net.snake.gamemodel.skill.bean.BuffPersisData;

/**
 * 
 * 
 * @author wutao
 */

public class CharacterBuffManager {
	private static CharacterBuffDao dao = new CharacterBuffDao();

	private CharacterBuffManager() {
	}

	private static CharacterBuffManager instance = null;

	public static CharacterBuffManager getInstance() {
		if (instance == null) {
			instance = new CharacterBuffManager();
		}
		return instance;
	}

	public List<BuffPersisData> getListByCharacterId(int characterId) {
		return dao.getListByCharacterId(characterId);
	}

}
