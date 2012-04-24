package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class WeddingRingDAO {
	private SqlMapClient sqlMapClient;

	public WeddingRingDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_wedding_ring.queryall");
		return list;
	}
}
