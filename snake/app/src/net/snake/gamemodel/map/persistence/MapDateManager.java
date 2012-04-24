package net.snake.gamemodel.map.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.map.bean.MapDate;
import net.snake.gamemodel.map.logic.DbMapLoader;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.cacheupdate.GameServerCacheUpdateListener;




public class MapDateManager  implements CacheUpdateListener{
	private MapDateDAO mapDateDAO = new MapDateDAO(SystemFactory.getGamedataSqlMapClient());
	//单例实现=====================================
	private static MapDateManager instance;
	private MapDateManager() {	
	}
	public static MapDateManager getInstance() {
		if (instance == null) {
			instance=new MapDateManager();
		}
		return instance;
	}
	@SuppressWarnings("unchecked")
	public List<MapDate> selectAllMapDateList() throws SQLException{
		return mapDateDAO.selectWithBLOBs();
	}
	@Override
	public void reload() {
		GameServerCacheUpdateListener.getInstance().reload();
		TransportDateManager.getInstance().initTransportDate();
		DbMapLoader.loadSceneManager();
	}
	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}
	@Override
	public String getCachename() {
		return "map";
	}
}
