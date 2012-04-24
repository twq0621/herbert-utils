package net.snake.gamemodel.task.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TaskConditionDAO {
	private SqlMapClient sqlMapClient;
	public TaskConditionDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	@SuppressWarnings("rawtypes")
	public List select()
			throws SQLException {
		List list = sqlMapClient.queryForList(	"t_task_condition.queryall");
		return list;
	}
	
}
