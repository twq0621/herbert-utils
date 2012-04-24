package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TotemDAO {
	private SqlMapClient sqlMapClient;

	public TotemDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_totem.queryall");
		return list;
	}
}
