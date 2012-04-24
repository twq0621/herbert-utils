package net.snake.gamemodel.gift.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GiftPacksDAO {

	private SqlMapClient sqlMapClient;

	public GiftPacksDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_gift_packs.queryall");
		return list;
	}

}
