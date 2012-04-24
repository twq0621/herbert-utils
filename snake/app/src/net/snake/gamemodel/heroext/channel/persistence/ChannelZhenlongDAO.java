package net.snake.gamemodel.heroext.channel.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.heroext.channel.bean.ChannelZhenlong;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ChannelZhenlongDAO {

	private SqlMapClient sqlMapClient;

	public ChannelZhenlongDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
	public ChannelZhenlong selectByPrimaryKey(Integer characterId) throws SQLException {
		ChannelZhenlong record = (ChannelZhenlong) sqlMapClient.queryForObject(
				"t_channel_zhenlong.selectByPrimaryKey", characterId);
		return record;
	}
	
	public void insertSelective(ChannelZhenlong record) throws SQLException {
		sqlMapClient.insert("t_channel_zhenlong.insertSelective", record);
	}
	
	public int updateByPrimaryKeySelective(ChannelZhenlong record) throws SQLException {
		int rows = sqlMapClient.update("t_channel_zhenlong.updateByPrimaryKeySelective",
				record);
		return rows;
	}

}
