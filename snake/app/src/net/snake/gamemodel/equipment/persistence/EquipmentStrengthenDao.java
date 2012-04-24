package net.snake.gamemodel.equipment.persistence;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.gamemodel.equipment.bean.EquipmentStrengthen;
import net.snake.ibatis.SystemFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 装备强化属性表数据库操作类
 * 
 * @author benchild
 */
public class EquipmentStrengthenDao {

	private static final Logger logger = Logger.getLogger(EquipmentStrengthenDao.class);

	public SqlMapClient getSqlMapClient() {
		return SystemFactory.getGamedataSqlMapClient();
	}

	@SuppressWarnings("unchecked")
	public List<EquipmentStrengthen> getEuipmentStrengthenList() {
		List<EquipmentStrengthen> euipmentStrengthenList = null;
		try {
			euipmentStrengthenList = getSqlMapClient().queryForList("equipmentStrengthenGetAll");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return euipmentStrengthenList;
	}
}
