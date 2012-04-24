package net.snake.gamemodel.friend.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.friend.bean.CharacterFriend;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterFriendDAO {

	private SqlMapClient sqlMapClient;

	public CharacterFriendDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public int deleteByCharacterId(int characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_friend.deleteByCharacterId", characterId);
		return rows;
	}

	public int deleteByFriendId(int friendId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_friend.deleteByFriendId", friendId);
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public List selecteListByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_friend.selecteListByCharacterId", characterId);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List selecteListByFriendId(int friendId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_friend.selecteListByFriendId", friendId);
		return list;
	}

	public Integer insert(CharacterFriend record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_friend.insert", record);
		return (Integer) newKey;
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_character_friend.deleteById", id);
		return rows;
	}

	public int updateById(CharacterFriend record) throws SQLException {
		int rows = sqlMapClient.update("t_character_friend.updateById", record);
		return rows;
	}
}
