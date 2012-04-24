package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.activities.bean.DoubActivity;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DoubActivityDAO {

	private SqlMapClient sqlMapClient;

	public DoubActivityDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public DoubActivity selectByPrimaryKey(Integer sid) throws SQLException {
		DoubActivity record = (DoubActivity) sqlMapClient.queryForObject("t_doub_activity.selectByPrimaryKey", sid);
		return record;
	}
}
