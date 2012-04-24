package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LiantiDAO {
	private SqlMapClient sqlMapClient;

	public LiantiDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_lianti.queryall");
		return list;
	}
}
