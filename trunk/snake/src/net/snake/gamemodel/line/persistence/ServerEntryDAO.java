package net.snake.gamemodel.line.persistence;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
public class ServerEntryDAO {
	private SqlMapClient sqlMapClient;

	public ServerEntryDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	@SuppressWarnings("rawtypes")
	public List select() throws Exception {
		List list = sqlMapClient.queryForList(	"server_list.queryall");
		return list;
	}
}
