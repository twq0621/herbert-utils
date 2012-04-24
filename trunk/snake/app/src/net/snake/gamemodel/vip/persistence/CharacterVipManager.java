package net.snake.gamemodel.vip.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.vip.bean.CharacterVip;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterVipManager {
	private static final Logger logger = Logger.getLogger(CharacterVipManager.class);
	private static CharacterVipDAO dao = new CharacterVipDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterVipManager instance;

	private CharacterVipManager() {

	}

	public static CharacterVipManager getInstance() {
		if (instance == null) {
			instance = new CharacterVipManager();
		}
		return instance;
	}

	/**
	 * 根据角色id 进行的新手引导
	 * 
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterVip> selecteListByCharacterId(int characterId) {
		try {
			return dao.selectByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<CharacterVip>();
		}
	}

	/**
	 * 插入CharacterNewguide
	 * 
	 * @param cf
	 */
	public void insert(CharacterVip cf) {
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

	public void asynInsertCharacterGiftpacks(Hero character, final CharacterVip cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(cf);
			}
		});
	}

	public void update(CharacterVip cf) {
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

	public void asynUpdateCharacterGiftpacks(Hero character, final CharacterVip cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				update(cf);
			}
		});
	}

	public void delete(CharacterVip cgp) {
		try {
			dao.deleteByPrimaryKey(cgp.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void asynDeleteCharacterGiftpacks(Hero character, final CharacterVip cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				delete(cf);
			}
		});
	}
}
