package net.snake.gamemodel.panel.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PanelDateDAO {

	private SqlMapClient sqlMapClient;

	public PanelDateDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_panel.queryall");
		return list;
	}
}
