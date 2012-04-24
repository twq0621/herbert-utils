package net.snake.gamemodel.skill.bow.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BowModelDAO {
	private SqlMapClient sqlMapClient;

	public BowModelDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_bowmodel.queryall");
		return list;
	}

}