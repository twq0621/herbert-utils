package net.snake.gamemodel.task.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TaskRewardDAO {
	
	private SqlMapClient sqlMapClient;
	public TaskRewardDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList(	"t_task_reward.queryall");
		return list;
	}

	
}
