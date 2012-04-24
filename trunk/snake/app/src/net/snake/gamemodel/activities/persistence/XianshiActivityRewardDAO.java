package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class XianshiActivityRewardDAO {

	private SqlMapClient sqlMapClient;

	public XianshiActivityRewardDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectWithBLOBs() throws SQLException {
		List list = sqlMapClient
				.queryForList("t_xianshi_activity_reward.selectByExampleWithBLOBs");
		return list;
	}

}
