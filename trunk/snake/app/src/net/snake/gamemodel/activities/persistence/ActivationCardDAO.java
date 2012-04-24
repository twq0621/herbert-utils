package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ActivationCardDAO {
	private SqlMapClient sqlMapClient;

	public ActivationCardDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_activation_card.queryall");
		return list;
	}
}
