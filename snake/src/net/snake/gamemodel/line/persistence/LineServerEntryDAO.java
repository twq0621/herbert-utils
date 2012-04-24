package net.snake.gamemodel.line.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LineServerEntryDAO {

	private SqlMapClient sqlMapClient;

	public LineServerEntryDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByServerId(int serverId) throws SQLException {
		List list = sqlMapClient.queryForList("t_lineserver.selectByServerId",
				serverId);
		return list;
	}

}
