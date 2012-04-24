package net.snake.gamemodel.team.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.bean.CharacterTeamfighting;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterTeamfightingManager {
	private static final Logger logger = Logger.getLogger(CharacterTeamfightingManager.class);
	private CharacterTeamfightingDAO characterTeamfightingDAO = new CharacterTeamfightingDAO(SystemFactory.getCharacterSqlMapClient());

	// 单例实现=====================================
	private static CharacterTeamfightingManager instance;

	private CharacterTeamfightingManager() {

	}

	public static CharacterTeamfightingManager getInstance() {
		if (instance == null) {
			instance = new CharacterTeamfightingManager();
		}
		return instance;
	}

	/**
	 * 根据角色id 获取角色阵法
	 * 
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterTeamfighting> selectCharacterFightingByCharacterId(int characterId) {
		try {
			return characterTeamfightingDAO.selectByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void insert(CharacterTeamfighting ctf) {
		try {
			characterTeamfightingDAO.insert(ctf);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 异步步插入
	 * 
	 * @param ctf
	 * @param character
	 */
	public void asynchronousInsert(final CharacterTeamfighting ctf, Hero character) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(ctf);
			}
		});
	}

}
