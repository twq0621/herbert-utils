package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.ibatis.SystemFactory;


/**
 * 帮会数据库表管理
 * 
 */

public class FactionFlagManager {
	private static final Logger logger = Logger.getLogger(FactionFlagManager.class);
	private FactionFlagDAO dao = new FactionFlagDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer,FactionFlag> gradeMap=new ConcurrentHashMap<Integer, FactionFlag>();
	private Map<Integer,FactionFlag> idMap=new ConcurrentHashMap<Integer, FactionFlag>();
	// 单例实现=====================================
	private static FactionFlagManager instance;

	private FactionFlagManager() {
		initDate();
	}
	public static FactionFlagManager getInstance() {
		if (instance == null) {
			instance = new FactionFlagManager();
		}
		return instance;
	}
	public void initDate() {
		List<FactionFlag> list=selectAllFactionflag();
		if(list==null||list.size()==0){
			return;
		}
		try {
			BeanTool.addOrUpdate(gradeMap,list, "fGrade");
			BeanTool.addOrUpdate(idMap,list, "id");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<FactionFlag> selectAllFactionflag(){
		try {
			return dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}
	public FactionFlag getFactionFlagByGrade(int grade){
		return gradeMap.get(grade);
	}
	public FactionFlag getFactionFlagById(int id){
		return idMap.get(id);
	}
}
