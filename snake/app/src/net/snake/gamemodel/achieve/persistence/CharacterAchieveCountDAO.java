package net.snake.gamemodel.achieve.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.fight.bean.CharacterAchieveCount;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterAchieveCountDAO {

	private SqlMapClient sqlMapClient;

	public CharacterAchieveCountDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_character_achieve_count.queryall", characterId);
		return list;
	}

	public void insert(CharacterAchieveCount record) throws SQLException {
		Object newKey = sqlMapClient.insert(
				"t_character_achieve_count.insert", record);
		record.setId((Integer) newKey);
	}

	public int updateById(CharacterAchieveCount record)
			throws SQLException {
		int rows = sqlMapClient.update("t_character_achieve_count.updateById",
				record);
		return rows;
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_character_achieve_count.deleteById",
				id);
		return rows;
	}

}
