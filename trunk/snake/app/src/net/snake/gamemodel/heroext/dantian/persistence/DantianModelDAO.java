package net.snake.gamemodel.heroext.dantian.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DantianModelDAO {
	private SqlMapClient sqlMapClient;

	public DantianModelDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_dantian.queryall");
		return list;
	}

}
