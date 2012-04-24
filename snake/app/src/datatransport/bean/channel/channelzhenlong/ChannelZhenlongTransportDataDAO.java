package datatransport.bean.channel.channelzhenlong;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ChannelZhenlongTransportDataDAO {
    
	private SqlMapClient sqlMapClient;
    public ChannelZhenlongTransportDataDAO(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }
    
    public ChannelZhenlongTransportData selectByPrimaryKey(Integer characterId) throws SQLException {
        ChannelZhenlongTransportData record = (ChannelZhenlongTransportData) sqlMapClient.queryForObject("t_channel_zhenlong.selectByPrimaryKey", characterId);
        return record;
    }
    
    public int deleteByPrimaryKey(Integer characterId) throws SQLException {
        int rows = sqlMapClient.delete("t_channel_zhenlong.deleteByPrimaryKey", characterId);
        return rows;
    }
    
    public void insert(ChannelZhenlongTransportData record) throws SQLException {
        sqlMapClient.insert("t_channel_zhenlong.insert", record);
    }
}
