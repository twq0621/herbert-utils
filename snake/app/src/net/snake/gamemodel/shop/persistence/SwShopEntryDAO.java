package net.snake.gamemodel.shop.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SwShopEntryDAO {
	
	private SqlMapClient sqlMapClient;
	public SwShopEntryDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_shop_shengwang.queryall");
		return list;
	}
}
