package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class XuanyuanBufferJiachengDAO {
	private SqlMapClient sqlMapClient;

	public XuanyuanBufferJiachengDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient
				.queryForList("t_xuanyuan_buffer_jiacheng.queryall");
		return list;
	}

}
