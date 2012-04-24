package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ActivityDataDAO {
	private SqlMapClient sqlMapClient;

	public ActivityDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select()
			throws SQLException {
		List list = sqlMapClient.queryForList("t_activity.queryall");
		return list;
	}

}
