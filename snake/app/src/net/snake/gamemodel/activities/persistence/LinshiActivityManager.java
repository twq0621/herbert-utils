package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.commons.TimeExpression;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.activities.bean.LinshiActivity;
import net.snake.ibatis.SystemFactory;



/**
 *@author serv_dev
 */
public class LinshiActivityManager implements CacheUpdateListener {
	private static Logger logger = Logger
			.getLogger(LinshiActivityManager.class);
	
	private static LinshiActivityManager Instance;
	private HashMap<Integer,String> map=new HashMap<Integer, String>(); 
	private LinshiActivityDAO dao=new LinshiActivityDAO(SystemFactory.getGamedataSqlMapClient()); 
	public static LinshiActivityManager getInstance() {
		if(Instance==null){
			Instance=new LinshiActivityManager();
		}
		return Instance;
	}
	private LinshiActivityManager(){
	}
	
	/**
	 * 根据临时活动ID判断是否到时间
	 * @return
	 */
	public boolean isTimeByLinshiActivityID(int id){
		if(map.size()<=0){
			return false;
		}
		String exp= map.get(id);
		if(exp==null){
			return false;
		}
		TimeExpression te=new TimeExpression(exp);
		return te.isExpressionTime(System.currentTimeMillis());
	}
	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			List<LinshiActivity> selectByExample =dao.selectAll(); 
			if(selectByExample!=null&&selectByExample.size()>0)
			for (LinshiActivity linshiActivity : selectByExample) {
				map.put(linshiActivity.getId(),linshiActivity.getTimeExp());
			}
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
		}
		
	}
	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}
	@Override
	public String getCachename() {
		return "linshiactivity";
	}
	
	
	

}
