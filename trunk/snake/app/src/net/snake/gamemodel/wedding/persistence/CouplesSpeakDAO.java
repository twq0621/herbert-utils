package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.wedding.bean.CouplesSpeak;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CouplesSpeakDAO {

	private SqlMapClient sqlMapClient;

	public CouplesSpeakDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByMale(int male) throws SQLException {
		List list = sqlMapClient.queryForList("t_couples_speak.selectByMale",
				male);
		return list;
	}

	public Integer insert(CouplesSpeak record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_couples_speak.insert", record);
		return (Integer) newKey;
	}

	public int deleteByPrimaryKey(Integer id) throws SQLException {
		CouplesSpeak key = new CouplesSpeak();
		key.setId(id);
		int rows = sqlMapClient.delete("t_couples_speak.deleteById", key);
		return rows;
	}

	public int updateIsNoticeBySpeakId(Integer speakId) throws SQLException {
		int rows = sqlMapClient.update("t_couples_speak.updateIsNoticeBySpeakId",speakId);
		return rows;
	}

	public int deleteByMaleId(int maleId) throws SQLException {
		int rows = sqlMapClient.delete("t_couples_speak.deleteByMaleId", maleId);
		return rows;
	}
}
