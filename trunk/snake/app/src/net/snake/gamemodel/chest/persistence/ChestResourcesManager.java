package net.snake.gamemodel.chest.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.chest.bean.ChestResources;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;


/**
 *  宝箱数据
 * 
 * 
 */

public class ChestResourcesManager {

	private static final Logger logger = Logger.getLogger(ChestResourcesManager.class);
	
	private static ChestResourcesDAO dao = new ChestResourcesDAO(SystemFactory.getGamedataSqlMapClient());
	//单例实现=====================================
	private static ChestResourcesManager instance;
	private ChestResourcesManager() {		
	
	}
	
	public static ChestResourcesManager getInstance() {
		if (instance == null) {
			instance=new ChestResourcesManager();
		}
		return instance;
	}
	//单例实现========================================
	
	@SuppressWarnings("unchecked")
	public List<ChestResources> getChestResources(){
		List<ChestResources> list =null;
		try {
			list =  dao.select();
		} catch (SQLException e1) {
			logger.error(e1.getMessage(),e1);
		}
		return list;
	}
	
}
