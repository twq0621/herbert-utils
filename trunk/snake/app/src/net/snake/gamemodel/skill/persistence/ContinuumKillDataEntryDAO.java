package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ContinuumKillDataEntryDAO {

	private SqlMapClient sqlMapClient;

	public ContinuumKillDataEntryDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_continuum_kill.queryall");
		return list;
	}
}
