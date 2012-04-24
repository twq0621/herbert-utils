package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class XuanyuanjianConfigDAO {
	private SqlMapClient sqlMapClient;

	public XuanyuanjianConfigDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_xuanyuanjian_config.queryall");
		return list;
	}

}
