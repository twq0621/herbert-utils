package net.snake.gamemodel.auth.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.auth.bean.AuthConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AuthConfigDAO {
	private SqlMapClient sqlMapClient;

	public AuthConfigDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public AuthConfig selectByPrimaryKey(Integer fid) throws SQLException {
		AuthConfig record = (AuthConfig) sqlMapClient.queryForObject("auth_config.selectByPrimaryKey", fid);
		return record;
	}

}
