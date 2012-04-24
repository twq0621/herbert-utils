package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterPropcd;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterPropcdManager {
	private static final Logger logger = Logger.getLogger(CharacterPropcdManager.class);
	private static CharacterPropcdDAO dao = new CharacterPropcdDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterPropcdManager instance;

	private CharacterPropcdManager() {
	}

	public static CharacterPropcdManager getInstance() {
		if (instance == null) {
			instance = new CharacterPropcdManager();
		}
		return instance;
	}

	/**
	 * 跟据角色id 查询冷却cd
	 * 
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterPropcd> selecteListByCharacterId(int characterId) {
		try {
			return dao.selectByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<CharacterPropcd>();
		}
	}

	public void deleteByCharacterId(int characterId) {
		try {

			dao.deleteByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 插入CharacterNewguide
	 * 
	 * @param cf
	 */
	public void insert(CharacterPropcd cf) {
		try {
			dao.insert(cf);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 异步插入CharacterGiftpacks
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynInsertCharacterGiftpacks(Hero character, final CharacterPropcd cp) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(cp);
			}
		});
	}

	public void update(CharacterPropcd cf) {
		try {
			dao.updateByPrimaryKey(cf);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * CharacterGiftpacks异步更新
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynDeleteByCharacterId(final Hero character) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				deleteByCharacterId(character.getId());
			}
		});
	}
}
