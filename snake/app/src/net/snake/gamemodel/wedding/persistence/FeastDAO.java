package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.wedding.bean.Feast;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FeastDAO {

	private SqlMapClient sqlMapClient;

	public FeastDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_feast.queryall");
		return list;
	}

	public void insert(Feast record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_feast.insert", record);
		record.setId((Integer) newKey);
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_feast.deleteById", id);
		return rows;
	}

	public int updateByIdSelective(Feast record) throws SQLException {
		int rows = sqlMapClient.update("t_feast.updateByIdSelective", record);
		return rows;
	}
}
