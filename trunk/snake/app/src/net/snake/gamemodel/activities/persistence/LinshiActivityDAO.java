package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LinshiActivityDAO {
	private SqlMapClient sqlMapClient;
	public LinshiActivityDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings("rawtypes")
	public List selectAll() throws SQLException {
		return sqlMapClient.queryForList("t_linshi_activity.queryall");
	}
}
