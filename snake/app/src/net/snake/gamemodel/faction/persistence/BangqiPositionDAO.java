package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BangqiPositionDAO {
	private SqlMapClient sqlMapClient;

	public BangqiPositionDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_bangqi_position.queryall");
		return list;
	}
}
