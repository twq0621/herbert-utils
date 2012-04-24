package net.snake.gamemodel.heroext.channel.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ChannelDAO {
	
	private SqlMapClient sqlMapClient;

	public ChannelDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_channel.queryall");
		return list;
	}

}
