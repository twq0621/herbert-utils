package net.snake.gamemodel.config.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.config.bean.ConfigParam;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ConfigParamDAO {

	private SqlMapClient sqlMapClient;

	public ConfigParamDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public ConfigParam selectByPrimaryKey(Integer id) throws SQLException {
		ConfigParam record = (ConfigParam) sqlMapClient.queryForObject("t_config_param.selectById", id);
		return record;
	}

}
