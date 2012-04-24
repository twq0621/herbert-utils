package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.wedding.bean.FeastJoin;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FeastJoinDAO {

	private SqlMapClient sqlMapClient;

	public FeastJoinDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void insertSelective(FeastJoin record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_feast_join.insert", record);
		record.setId((Integer) newKey);
	}

	public int deleteByApplyerAndMateid(FeastJoin feastJoin) throws SQLException {
		int rows = sqlMapClient.delete("t_feast_join.deleteByApplyerAndMateid", feastJoin);
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public List select(FeastJoin feastJoin) throws SQLException {
		List list = sqlMapClient.queryForList("t_feast_join.queryall", feastJoin);
		return list;
	}
}
