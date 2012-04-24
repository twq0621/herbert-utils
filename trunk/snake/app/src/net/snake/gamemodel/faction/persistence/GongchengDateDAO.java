package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import net.snake.gamemodel.fight.bean.GongchengDate;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GongchengDateDAO {

	private SqlMapClient sqlMapClient;

	public GongchengDateDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_gongcheng_date.queryall");
		return list;
	}

	public int deleteByDate(Date date) throws SQLException {
		int rows = sqlMapClient.delete("t_gongcheng_date.deleteByDate", date);
		return rows;
	}

	public void insert(GongchengDate record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_gongcheng_date.insert", record);
		record.setId((Integer) newKey);
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_gongcheng_date.deleteById", id);
		return rows;
	}
}
