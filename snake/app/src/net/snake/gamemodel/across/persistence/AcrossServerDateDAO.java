package net.snake.gamemodel.across.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AcrossServerDateDAO {
	private SqlMapClient sqlMapClient;

	public AcrossServerDateDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_across_server.queryall");
		return list;
	}
}
