package net.snake.gamemodel.equipment.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class EquipmentPlayconfigDAO {
	
	private SqlMapClient sqlMapClient;
	public EquipmentPlayconfigDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings("rawtypes")
	public List select()
			throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_equipment_playconfig.queryall");
		return list;
	}
	
}
