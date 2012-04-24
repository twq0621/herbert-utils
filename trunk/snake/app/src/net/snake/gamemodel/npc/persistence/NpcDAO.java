package net.snake.gamemodel.npc.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class NpcDAO {
	private SqlMapClient sqlMapClient;

	public NpcDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_npc.queryall");
		return list;
	}
}
