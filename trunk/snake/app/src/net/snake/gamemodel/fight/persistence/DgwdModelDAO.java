package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DgwdModelDAO {

	private SqlMapClient sqlMapClient;

	public DgwdModelDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_dgwd.queryall");
		return list;
	}
}
