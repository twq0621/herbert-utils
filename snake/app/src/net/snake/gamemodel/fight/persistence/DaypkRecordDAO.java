package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DaypkRecordDAO {
	private SqlMapClient sqlMapClient;

	public DaypkRecordDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select(int character) throws SQLException {
		List list = sqlMapClient.queryForList("t_daypk_record.queryall", character);
		return list;
	}

}
