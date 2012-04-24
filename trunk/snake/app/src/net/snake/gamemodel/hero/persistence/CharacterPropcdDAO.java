package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterPropcd;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterPropcdDAO {

	private SqlMapClient sqlMapClient;

	public CharacterPropcdDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_character_propcd.selectByCharacterId", characterId);
		return list;
	}

	public int deleteByCharacterId(int characterId) throws SQLException {
		int rows = sqlMapClient.delete(
				"t_character_propcd.deleteByCharacterId", characterId);
		return rows;
	}

	public Integer insert(CharacterPropcd record) throws SQLException {
		Object newKey = sqlMapClient	.insert("t_character_propcd.insert", record);
		return (Integer) newKey;
	}

	public int updateByPrimaryKey(CharacterPropcd record) throws SQLException {
		int rows = sqlMapClient.update("t_character_propcd.updateByPrimaryKey",
				record);
		return rows;
	}
}
