package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HiddenWeaponsDAO {

	private SqlMapClient sqlMapClient;

	public HiddenWeaponsDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_hidden_weapons.queryall");
		return list;
	}

}
