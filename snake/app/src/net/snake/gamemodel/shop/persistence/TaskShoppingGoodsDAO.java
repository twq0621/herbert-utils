package net.snake.gamemodel.shop.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.shop.bean.TaskShoppingGoods;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TaskShoppingGoodsDAO {
  

	private SqlMapClient sqlMapClient;

	public TaskShoppingGoodsDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
    @SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
        List list = sqlMapClient.queryForList("t_character_task_shopping.selectByCharacterId", characterId);
        return list;
    }
    
    public int deleteByCharacterId(int characterId) throws SQLException {
        int rows = sqlMapClient.delete("t_character_task_shopping.deleteByCharacterId", characterId);
        return rows;
    }
    
    public int updateByPrimaryKey(TaskShoppingGoods record) throws SQLException {
        int rows = sqlMapClient.update("t_character_task_shopping.updateByPrimaryKey", record);
        return rows;
    }
    
    public void insert(TaskShoppingGoods record) throws SQLException {
        sqlMapClient.insert("t_character_task_shopping.insert", record);
    }
}
