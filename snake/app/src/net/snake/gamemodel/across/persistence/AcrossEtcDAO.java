package net.snake.gamemodel.across.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.across.bean.AcrossEtc;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AcrossEtcDAO {

	private SqlMapClient sqlMapClient;

	public AcrossEtcDAO(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_across_etc.queryall");
		return list;
	}

	public void insert(AcrossEtc record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_across_etc.insert", record);
		record.setId((Integer) newKey);
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_across_etc.deleteById", id);
		return rows;
	}

	public int updateById(AcrossEtc record) throws SQLException {
		int rows = sqlMapClient.update("t_across_etc.updateById", record);
		return rows;
	}

}
