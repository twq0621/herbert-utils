package net.snake.gamemodel.achieve.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AchieveDAO {
	private SqlMapClient sqlMapClient;

	public AchieveDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_achieve.queryall");
		return list;
	}

}
