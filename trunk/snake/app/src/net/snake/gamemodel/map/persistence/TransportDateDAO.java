package net.snake.gamemodel.map.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TransportDateDAO {
	private SqlMapClient sqlMapClient;

	public TransportDateDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_transport2.queryall");
		return list;
	}
}
