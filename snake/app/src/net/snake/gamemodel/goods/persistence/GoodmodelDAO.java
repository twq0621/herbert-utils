package net.snake.gamemodel.goods.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GoodmodelDAO {

	private SqlMapClient sqlMapClient;

	public GoodmodelDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_goodmodel.queryall");
		return list;
	}

	
}
