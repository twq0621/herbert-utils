package net.snake.gamemodel.guide.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.guide.bean.CharacterNewguide;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterNewguideDAO {

	private SqlMapClient sqlMapClient;

	public CharacterNewguideDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_newguide.selectByCharacterId", characterId);
		return list;
	}

	public void insert(CharacterNewguide record) throws SQLException {
		sqlMapClient.insert("t_character_newguide.insert", record);
	}

	public int updateByPrimaryKey(CharacterNewguide record) throws SQLException {
		int rows = sqlMapClient.update("t_character_newguide.updateByPrimaryKey", record);
		return rows;
	}

}
