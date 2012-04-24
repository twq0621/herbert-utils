package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.fight.bean.CharacterVehicle;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterVehicleDAO {
    

	private SqlMapClient sqlMapClient;

	public CharacterVehicleDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
    @SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
        List list = sqlMapClient.queryForList("t_character_vehicle.selectByCharacterId", characterId);
        return list;
    }
    
    public int deleteByPrimaryKey(Integer id) throws SQLException {
        int rows = sqlMapClient.delete("t_character_vehicle.deleteByPrimaryKey", id);
        return rows;
    }
    
    public int updateByPrimaryKey(CharacterVehicle record) throws SQLException {
        int rows = sqlMapClient.update("t_character_vehicle.updateByPrimaryKey", record);
        return rows;
    }
    
    public Integer insert(CharacterVehicle record) throws SQLException {
        Object newKey = sqlMapClient.insert("t_character_vehicle.insert", record);
        return (Integer) newKey;
    }
}
