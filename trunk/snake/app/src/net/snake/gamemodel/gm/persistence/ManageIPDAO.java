package net.snake.gamemodel.gm.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ManageIPDAO {

	private SqlMapClient sqlMapClient;

	public ManageIPDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_manageip.queryall");
		return list;
	}
}
