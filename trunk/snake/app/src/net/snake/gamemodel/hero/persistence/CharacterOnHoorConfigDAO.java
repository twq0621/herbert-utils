package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterOnHoorConfigDAO {

	private SqlMapClient sqlMapClient;

	public CharacterOnHoorConfigDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_onhoor_config.selectByCharacterId", characterId);
		return list;
	}

	public int updateById(CharacterOnHoorConfig record) throws SQLException {
		int rows = sqlMapClient.update("t_character_onhoor_config.updateById", record);
		return rows;
	}

	public void insert(CharacterOnHoorConfig record) throws SQLException {
		sqlMapClient.insert("t_character_onhoor_config.insert", record);
	}
}
