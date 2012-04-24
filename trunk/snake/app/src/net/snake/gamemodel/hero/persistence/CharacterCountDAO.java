package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterCount;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterCountDAO {
	private SqlMapClient sqlMapClient;

	public CharacterCountDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public int update(CharacterCount record) throws SQLException {
		int rows = sqlMapClient.update("t_character_count.update", record);
		return rows;
	}

	public void insert(CharacterCount record) throws SQLException {
		Integer id = (Integer) sqlMapClient.insert("t_character_count.insert", record);
		record.setId(id);
	}

	@SuppressWarnings("rawtypes")
	public List select(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_count.queryall", characterId);
		return list;
	}
}
