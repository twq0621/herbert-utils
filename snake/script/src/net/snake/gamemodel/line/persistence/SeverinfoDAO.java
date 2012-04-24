package net.snake.gamemodel.line.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.line.bean.Severinfo;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SeverinfoDAO {

	private SqlMapClient sqlMapClient;

	public SeverinfoDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public Severinfo selectByPrimaryKey(Integer id) throws SQLException {
		Severinfo record = (Severinfo) sqlMapClient.queryForObject("t_server_info.queryById", id);
		return record;
	}

	public void insert(Severinfo record) throws SQLException {
		sqlMapClient.insert("t_server_info.insert", record);
	}
}
