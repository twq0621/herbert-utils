package net.snake.gamemodel.language.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LanguageScriptDAO {
	private SqlMapClient sqlMapClient;

	public LanguageScriptDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_language_script.queryall");
		return list;
	}
}
