package net.snake.gamemodel.team.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.team.bean.CharacterTeamfighting;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterTeamfightingDAO {

	private SqlMapClient sqlMapClient;

	public CharacterTeamfightingDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_character_teamfighting.selectByCharacterId", characterId);
		return list;
	}

	public void insert(CharacterTeamfighting record) throws SQLException {
		sqlMapClient.insert("t_character_teamfighting.insert",
				record);
	}

}
