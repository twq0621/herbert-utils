package net.snake.gamemodel.guide.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.guide.bean.CharacterNewguide;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterNewguideManager {
	private static final Logger logger = Logger.getLogger(CharacterNewguideManager.class);
	private static CharacterNewguideDAO dao = new CharacterNewguideDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterNewguideManager instance;

	private CharacterNewguideManager() {
	}

	public static CharacterNewguideManager getInstance() {
		if (instance == null) {
			instance = new CharacterNewguideManager();
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
	public List<CharacterNewguide> selecteListByCharacterId(int characterId) {
		try {
			return dao.selectByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<CharacterNewguide>();
		}
	}

	/**
	 * 插入CharacterNewguide
	 * 
	 * @param cf
	 */
	public void insert(CharacterNewguide cf) {
		try {
			dao.insert(cf);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 异步插入CharacterNewguide
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynInsertCharacterNewguide(Hero character, final CharacterNewguide cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(cf);
			}
		});
	}

	public void update(CharacterNewguide cf) {
		try {
			dao.updateByPrimaryKey(cf);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * CharacterNewguide异步更新
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynUpdateCharacterNewguide(Hero character, final CharacterNewguide cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				update(cf);
			}
		});
	}
}
