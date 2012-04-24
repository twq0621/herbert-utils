package net.snake.gamemodel.gift.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GrandPrixDataDAO {

	private SqlMapClient sqlMapClient;

	public GrandPrixDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_grandprix.queryall");
		return list;
	}
}
