package net.snake.gamemodel.guide.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.guide.bean.CharacterWeekTime;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterWeekTimeDAO {

	private SqlMapClient sqlMapClient;

	public CharacterWeekTimeDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByWeek(CharacterWeekTime query) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_week.selectByWeek", query);
		return list;
	}

	public Integer insert(CharacterWeekTime record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_week.insert", record);
		return (Integer) newKey;
	}

	public int updateByPrimaryKey(CharacterWeekTime record) throws SQLException {
		int rows = sqlMapClient.update("t_character_week.updateByPrimaryKey", record);
		return rows;
	}

	public int deleteByPrimaryKey(Integer fId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_week.deleteByPrimaryKey", fId);
		return rows;
	}
}
