package net.snake.gamemodel.gift.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterGiftpacksManager {
	private static Logger logger = Logger.getLogger(CharacterGiftpacksManager.class);
	private static CharacterGiftpacksDAO dao = new CharacterGiftpacksDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterGiftpacksManager instance;

	private CharacterGiftpacksManager() {
	}

	public static CharacterGiftpacksManager getInstance() {
		if (instance == null) {
			instance = new CharacterGiftpacksManager();
		}
		return instance;
	}

	/**
	 * 更具角色id 进行的新手引导
	 * 
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterGiftpacks> selecteListByCharacterId(int characterId) {
		try {
			return dao.selectByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<CharacterGiftpacks>();
		}
	}

	/**
	 * 插入CharacterNewguide
	 * 
	 * @param cf
	 */
	public void insert(CharacterGiftpacks cf) {
		try {
			dao.insert(cf);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 异步插入CharacterGiftpacks
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynInsertCharacterGiftpacks(Hero character, final CharacterGiftpacks cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(cf);
			}
		});
	}

	public void update(CharacterGiftpacks cf) {
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

	public void asynUpdateCharacterGiftpacks(Hero character, final CharacterGiftpacks cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				update(cf);
			}
		});
	}

	public void delete(CharacterGiftpacks cgp) {
		try {
			dao.deleteByPrimaryKey(cgp.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void asynDeleteCharacterGiftpacks(Hero character, final CharacterGiftpacks cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				delete(cf);
			}
		});
	}
}
