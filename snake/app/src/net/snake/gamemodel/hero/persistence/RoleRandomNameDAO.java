package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RoleRandomNameDAO {
	private SqlMapClient sqlMapClient;

	public RoleRandomNameDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_role_random_name.queryall");
		return list;
	}
}
