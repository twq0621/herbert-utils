package net.snake.gamemodel.task.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.task.bean.TaskDataEntry;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TaskDataEntryDAO {

	private SqlMapClient sqlMapClient;

	public TaskDataEntryDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByEndtimeIsNull(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_character_task.selectByEndtimeIsNull", characterId);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List selectByEndtimeIsNotNull(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_character_task.selectByEndtimeIsNotNull", characterId);
		return list;
	}

	public void insertSelective(TaskDataEntry record) throws SQLException {
		sqlMapClient.insert("t_character_task.insertSelective", record);
	}

	public int updateByPrimaryKey(TaskDataEntry record) throws SQLException {
		int rows = sqlMapClient.update("t_character_task.updateByPrimaryKey",
				record);
		return rows;
	}

	public int deleteByCharacterId(int characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_task.deleteByCharacterId",
				characterId);
		return rows;
	}

	public int deleteByPrimaryKey(String id) throws SQLException {
		int rows = sqlMapClient.delete("t_character_task.deleteByPrimaryKey",
				id);
		return rows;
	}

}
