package net.snake.gamemodel.goods.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.goods.bean.CharacterGoodsDC;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterGoodsDCManager {
	private static Logger logger = Logger.getLogger(CharacterGoodsDCManager.class);
	private static CharacterGoodsDCDAO dao = new CharacterGoodsDCDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterGoodsDCManager instance;

	private CharacterGoodsDCManager() {
	}

	public static CharacterGoodsDCManager getInstance() {
		if (instance == null) {
			instance = new CharacterGoodsDCManager();
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
	public List<CharacterGoodsDC> selecteListByCharacterId(int characterId) {
		try {
			return dao.selectByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<CharacterGoodsDC>();
		}
	}

	public void asynUpdateCharacterGoodsDC(Hero character, final CharacterGoodsDC dc) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updateByPrimaryKey(dc);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void asynInsertCharacterGoodsDC(Hero character, final CharacterGoodsDC dc) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insert(dc);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}
}
