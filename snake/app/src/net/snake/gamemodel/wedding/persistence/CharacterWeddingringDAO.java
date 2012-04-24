package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.wedding.bean.CharacterWeddingring;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterWeddingringDAO {

	private SqlMapClient sqlMapClient;

	public CharacterWeddingringDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_character_weddingring.selectByCharacterId", characterId);
		return list;
	}

	public Integer insert(CharacterWeddingring record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_weddingring.insert",
				record);
		return (Integer) newKey;
	}

	public int updateByPrimaryKey(CharacterWeddingring record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"t_character_weddingring.updateByPrimaryKey", record);
		return rows;
	}

	public int updateByCharacterId(CharacterWeddingring record) throws SQLException {
		int rows = sqlMapClient
				.update("t_character_weddingring.update", record);
		return rows;
	}

	public int deleteByPrimaryKey(Integer id) throws SQLException {
		int rows = sqlMapClient.delete(
				"t_character_weddingring.deleteByPrimaryKey", id);
		return rows;
	}

}
