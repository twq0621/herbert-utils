package net.snake.gamemodel.goods.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GoodsDCDAO {

	private SqlMapClient sqlMapClient;

	public GoodsDCDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_goods_dc.queryall");
		return list;
	}
}
