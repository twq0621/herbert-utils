package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.activities.bean.Activities;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ActivitiesDAO {

	private SqlMapClient sqlMapClient;

	public ActivitiesDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public Integer insert(Activities record) throws SQLException {
		if(record.getCountLimit()==null){
			record.setCountLimit(0);
		}
		Object newKey = sqlMapClient.insert("t_activities.insert", record);
		return (Integer) newKey;
	}

	@SuppressWarnings("rawtypes")
	public List selectByAccountAndType(int accountId,int type) throws SQLException {
		Activities activities = new Activities();
		activities.setAccountId(accountId);
		activities.setType(type);
		List list = sqlMapClient.queryForList("t_activities.selectByAccountAndType", activities);
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List  selectByaIdcId(int accountId, int characterId)  throws SQLException {
		Activities activities = new Activities();
		activities.setAccountId(accountId);
		activities.setCountLimit(characterId);
		List list = sqlMapClient.queryForList("t_activities.selectByaIdcId", activities);
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List  selectByaId(int accountId)  throws SQLException {
		Activities activities = new Activities();
		activities.setAccountId(accountId);
		List list = sqlMapClient.queryForList("t_activities.selectByaId", activities);
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List  selectByaIdcIdType(int accountId, int characterId,int type)  throws SQLException {
		Activities activities = new Activities();
		activities.setAccountId(accountId);
		activities.setCountLimit(characterId);
		activities.setType(type);
		List list = sqlMapClient.queryForList("t_activities.selectByaIdcIdType", activities);
		return list;
	}
	
	public int updateCount(Activities record) throws SQLException {
		int rows = sqlMapClient.update("t_activities.updateCount", record);
		return rows;
	}

}
