package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.wedding.bean.Couples;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CouplesDAO {

	private SqlMapClient sqlMapClient;

	public CouplesDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_couples.queryall");
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List selectByMale(int male) throws SQLException {
		List list = sqlMapClient.queryForList("t_couples.selectByMale", male);
		return list;
	}

	public void insert(Couples record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_couples.insert", record);
		record.setId((Integer) newKey);
	}

	public int deleteByPrimaryKey(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_couples.deleteById", id);
		return rows;
	}

	public int updateByPrimaryKeySelective(Couples record) throws SQLException {
		int rows = sqlMapClient
				.update("t_couples.updateByPrimaryKeySelective",
						record);
		return rows;
	}
}
