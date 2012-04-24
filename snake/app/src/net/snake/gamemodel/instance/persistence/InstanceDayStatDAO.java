package net.snake.gamemodel.instance.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.instance.bean.InstanceDayStat;

import com.ibatis.sqlmap.client.SqlMapClient;

public class InstanceDayStatDAO {

	private SqlMapClient sqlMapClient;

	public InstanceDayStatDAO(SqlMapClient sqlMapClient) {

		this.sqlMapClient = sqlMapClient;
	}

	public int deleteByStatdate(InstanceDayStat instanceDayStat) throws SQLException {
		int rows = sqlMapClient.delete("t_instance_daystat.deleteByStatdate", instanceDayStat);
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public List select(InstanceDayStat instanceDayStat) throws SQLException {
		List list = sqlMapClient.queryForList("t_instance_daystat.queryByCharacteridAndStatdate", instanceDayStat);
		return list;
	}

	public void insert(InstanceDayStat record) throws SQLException {
		sqlMapClient.insert("t_instance_daystat.insert", record);
	}

	public int updateByPrimaryKey(InstanceDayStat record) throws SQLException {
		int rows = sqlMapClient.update("t_instance_daystat.updateByPrimaryKey", record);
		return rows;
	}

}
