package net.snake.gamemodel.friend.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterFriendManager {
	private static final Logger logger = Logger.getLogger(CharacterFriendManager.class);
	private static CharacterFriendDAO dao = new CharacterFriendDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterFriendManager instance;

	private CharacterFriendManager() {
	}

	public static CharacterFriendManager getInstance() {
		if (instance == null) {
			instance = new CharacterFriendManager();
		}
		return instance;
	}

	/**
	 * 更具角色id 删除其是谁的好友
	 * 
	 * @param characterId
	 * @return
	 */
	public void deleteByFriendId(int friendId) {
		try {
			dao.deleteByFriendId(friendId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 更具角色id 删除其是好友是谁
	 * 
	 * @param characterId
	 * @return
	 */
	public void delteByCharacterId(int characterId) {
		try {
			dao.deleteByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 更具角色id 查询其是谁的好友
	 * 
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterFriend> selecteListByFriendId(int friendId) {
		try {
			return dao.selecteListByFriendId(friendId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<CharacterFriend>();
		}
	}

	/**
	 * 根据角色id 查询其是好友是谁
	 * 
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterFriend> selecteListByCharacterId(int characterId) {
		try {
			return dao.selecteListByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<CharacterFriend>();
		}
	}

	/**
	 * 插入好友
	 * 
	 * @param cf
	 */
	private void insert(CharacterFriend cf) {
		try {
			dao.insert(cf);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 异步插入好友
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynInsertCharacterFriend(Hero character, final CharacterFriend cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(cf);
			}
		});
	}

	private void delete(CharacterFriend cf) {
		try {
			dao.deleteById(cf.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 好友异步删除
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynDeleteCharacterFriend(Hero character, final CharacterFriend cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				delete(cf);
			}
		});
	}

	private void update(CharacterFriend cf) {
		try {
			dao.updateById(cf);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 好友异步删除
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynUpdateCharacterFriend(Hero character, final CharacterFriend cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				update(cf);
			}
		});
	}
}
