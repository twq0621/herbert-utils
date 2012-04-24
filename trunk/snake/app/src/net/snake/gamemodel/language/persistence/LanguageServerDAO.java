package net.snake.gamemodel.language.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LanguageServerDAO {
	private SqlMapClient sqlMapClient;

	public LanguageServerDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectLessThanId(int id) throws SQLException {
		List list = sqlMapClient.queryForList("t_language_server.queryall",id);
		return list;
	}
}
