package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.faction.bean.Faction;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FactionDAO {

	private SqlMapClient sqlMapClient;

	public FactionDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_faction.queryall");
		return list;
	}

	public void insert(Faction record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_faction.insert", record);
		record.setId( (Integer) newKey);
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_faction.deleteById", id);
		return rows;
	}

	public int updateByPrimaryKeySelective(Faction record) throws SQLException {
		int rows = sqlMapClient.update("t_faction.updateByIdSelective", record);
		return rows;
	}

}
