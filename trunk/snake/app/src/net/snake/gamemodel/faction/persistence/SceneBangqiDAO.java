package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.faction.bean.SceneBangqi;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SceneBangqiDAO {

	private SqlMapClient sqlMapClient;

	public SceneBangqiDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public int deleteByFactionId(int factionId) throws SQLException {
		int rows = sqlMapClient.delete("t_scene_bangqi.deleteByFactionId",
				factionId);
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_scene_bangqi.queryall");
		return list;
	}

	public Integer insert(SceneBangqi record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_scene_bangqi.insert", record);
		return (Integer) newKey;
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_scene_bangqi.deleteById", id);
		return rows;
	}

	public int updateHpMpById(SceneBangqi record) throws SQLException {
		int rows = sqlMapClient.update("t_scene_bangqi.updateHpMpById", record);
		return rows;
	}

}
