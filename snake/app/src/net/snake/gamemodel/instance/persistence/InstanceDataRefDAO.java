package net.snake.gamemodel.instance.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class InstanceDataRefDAO {

	private SqlMapClient sqlMapClient;

	public InstanceDataRefDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_instance.queryall");
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List selectByEnable(int enable) throws SQLException {
		List list = sqlMapClient.queryForList("t_instance.querybyenable", enable);
		return list;
	}
}
