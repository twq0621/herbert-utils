package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BreakthroughDAO {

	private SqlMapClient sqlMapClient;

	public BreakthroughDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_breakthrough.queryall");
		return list;
	}
}
