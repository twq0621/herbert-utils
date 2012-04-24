package net.snake.gamemodel.panel.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RoleUpgradeDescDAO {
	private SqlMapClient sqlMapClient;

	public RoleUpgradeDescDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_role_upgrade_desc.queryall");
		return list;
	}

}
