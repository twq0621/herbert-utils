package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.hero.bean.Worshipcontempt;

import com.ibatis.sqlmap.client.SqlMapClient;

public class WorshipcontemptDAO {
    private SqlMapClient sqlMapClient;
    
    public WorshipcontemptDAO(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }
    
    public Worshipcontempt selectByPrimaryKey(Integer characterId) throws SQLException {
        Worshipcontempt record = (Worshipcontempt) sqlMapClient.queryForObject("t_worship_contempt.selectByCharacterId", characterId);
        return record;
    }
    
    public int deleteByPrimaryKey(Integer characterId) throws SQLException {
        int rows = sqlMapClient.delete("t_worship_contempt.deleteByCharacterId", characterId);
        return rows;
    }
    
    public int updateByPrimaryKeySelective(Worshipcontempt record) throws SQLException {
        int rows = sqlMapClient.update("t_worship_contempt.update", record);
        return rows;
    }
    
    public void insert(Worshipcontempt record) throws SQLException {
        sqlMapClient.insert("t_worship_contempt.insert", record);
    }
}
