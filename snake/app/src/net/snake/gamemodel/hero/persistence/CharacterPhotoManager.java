package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.CharacterPhoto;
import net.snake.ibatis.SystemFactory;

/**
 * 
 * 角色的图片
 * 
 * @author serv_dev
 * @version 1.0
 * @created 2011-4-1 下午03:35:29
 */
public class CharacterPhotoManager {
	private static final Logger logger = Logger.getLogger(CharacterPhotoManager.class);
	private static CharacterPhotoDAO dao = new CharacterPhotoDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterPhotoManager instance;

	private CharacterPhotoManager() {
	}

	public static CharacterPhotoManager getInstance() {
		if (instance == null) {
			instance = new CharacterPhotoManager();
		}
		return instance;
	}

	// 单例实现=====================================结束
	public CharacterPhoto getCharacterPhoto(int characterId) {
		CharacterPhoto characterPhoto = null;
		try {
			characterPhoto = dao.selectByPrimaryKey(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return characterPhoto;
	}

	public void deleteCharacterPhoto(final int characterId) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.deleteByPrimaryKey(characterId);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void updateCharacterPhoto(final CharacterPhoto characterPhoto) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updateByPrimaryKeySelective(characterPhoto);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void addCharacterPhoto(final CharacterPhoto characterPhoto) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insertSelective(characterPhoto);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}
}
