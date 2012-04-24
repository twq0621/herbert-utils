package net.snake.gamemodel.team.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TeamFightingDAO {

	private SqlMapClient sqlMapClient;

	public TeamFightingDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_teamfighting.queryall");
		return list;
	}

}
