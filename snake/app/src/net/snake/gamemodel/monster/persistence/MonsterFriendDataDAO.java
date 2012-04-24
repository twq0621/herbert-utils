package net.snake.gamemodel.monster.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.monster.bean.MonsterFriendData;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MonsterFriendDataDAO {

	private SqlMapClient sqlMapClient;

	public MonsterFriendDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_monster_friend.selectByCharacterId", characterId);
		return list;
	}

	public void insert(MonsterFriendData record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_monster_friend.insert", record);
		record.setId((Integer) newKey);
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_monster_friend.deleteById", id);
		return rows;
	}

	public int updateStateById(MonsterFriendData record) throws SQLException {
		int rows = sqlMapClient.update("t_monster_friend.updateStateById", record);
		return rows;
	}

}
