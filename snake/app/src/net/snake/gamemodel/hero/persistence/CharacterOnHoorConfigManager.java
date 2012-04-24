package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 管理
 * 
 */

public class CharacterOnHoorConfigManager {

	private static final Logger logger = Logger.getLogger(CharacterOnHoorConfigManager.class);
	private static CharacterOnHoorConfigDAO dao = new CharacterOnHoorConfigDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterOnHoorConfigManager instance;

	private CharacterOnHoorConfigManager() {
	}

	public static CharacterOnHoorConfigManager getInstance() {
		if (instance == null) {
			instance = new CharacterOnHoorConfigManager();
		}
		return instance;
	}

	// 单例实现========================================
	protected CharacterOnHoorConfigDAO getEntityDao() {
		return dao;
	}

	@SuppressWarnings("rawtypes")
	public CharacterOnHoorConfig getCharacterOnHoorConfigList(Integer characterId) {
		try {
			List list = dao.selectByCharacterId(characterId);
			if (list.isEmpty())
				return null;
			return (CharacterOnHoorConfig) (list.get(0));
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void asynUpdataCharacterOnHoorConfig(Hero character, final CharacterOnHoorConfig characterOnHoorConfig) {
		if (!Options.IsCrossServ) {
			character.addToBatchUpdateTask(new Runnable() {
				@Override
				public void run() {
					try {
						dao.updateById(characterOnHoorConfig);
					} catch (SQLException e) {
						logger.error(e.getMessage(), e);
					}
				}
			});
		}
	}

	public void insertCharacterOnHoorConfig(CharacterOnHoorConfig characterOnHoorConfig) {
		try {
			if (!Options.IsCrossServ) {
				dao.insert(characterOnHoorConfig);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
