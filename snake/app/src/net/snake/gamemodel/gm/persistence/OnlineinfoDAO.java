package net.snake.gamemodel.gm.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.gm.bean.Onlineinfo;

import com.ibatis.sqlmap.client.SqlMapClient;

public class OnlineinfoDAO {
	private SqlMapClient sqlMapClient;

	public OnlineinfoDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void insert(Onlineinfo record) throws SQLException {
		sqlMapClient.insert("t_onlineinfo.insert", record);
	}

}
