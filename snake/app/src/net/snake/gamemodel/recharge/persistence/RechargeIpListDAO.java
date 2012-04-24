package net.snake.gamemodel.recharge.persistence;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class RechargeIpListDAO {
	private SqlMapClient sqlMapClient;

	public RechargeIpListDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws Exception {
		List list = sqlMapClient.queryForList("t_recharge_ip_list.queryall");
		return list;
	}
}
