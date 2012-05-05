package net.snake.gamemodel.bulletin.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ScrollBulletinDAO {

	private SqlMapClient sqlMapClient;

	public ScrollBulletinDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_scroll_bulletin.queryall");
		return list;
	}
}