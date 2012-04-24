package net.snake.gamemodel.skill.bow.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.skill.bow.bean.BowDataEntry;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BowDataEntryDAO {

	
	private SqlMapClient sqlMapClient;

	public BowDataEntryDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public void insertSelective(BowDataEntry record) throws SQLException {
		sqlMapClient.insert("t_character_bow.insertSelective",
				record);
	}
	
	public int updateByPrimaryKeySelective(BowDataEntry record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"t_character_bow.updateByPrimaryKeySelective",
				record);
		return rows;
	}
	
	public BowDataEntry selectByPrimaryKey(Integer characterid)
			throws SQLException {
		BowDataEntry record = (BowDataEntry) sqlMapClient.queryForObject(
				"t_character_bow.selectByPrimaryKey", characterid);
		return record;
	}
	
}
