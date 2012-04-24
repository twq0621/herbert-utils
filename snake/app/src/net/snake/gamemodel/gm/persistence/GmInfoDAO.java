package net.snake.gamemodel.gm.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GmInfoDAO {

	private SqlMapClient sqlMapClient;

	public GmInfoDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select(int state) throws SQLException {
		List list = sqlMapClient.queryForList("t_gm_info.queryall",state);
		return list;
	}

}
