package net.snake.dao.map;

import java.util.HashMap;
import java.util.List;

import net.snake.common.BeanTool;

import org.springframework.beans.factory.InitializingBean;





public class MapDataProviderImpl implements MapDataProvider ,InitializingBean{

	private List<Map> mapslist = null;
	private MapDAO mapDAO;
	private java.util.Map<Integer, Map> getMap = new HashMap<Integer, Map>();
	public java.util.Map<Integer, Map> getGetMap() {
		return getMap;
	}

	@Override
	public List<Map> getMapList() {
		return mapslist;
	}

	public MapDAO getMapDAO() {
		return mapDAO;
	}

	public void setMapDAO(MapDAO mapDAO) {
		this.mapDAO = mapDAO;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		MapExample example = new MapExample();
		example.setOrderByClause("f_map_id");
		mapslist = mapDAO.selectByExample(example);
System.out.println("mapslist======="+mapslist.size());
		getMap=BeanTool.listToMap(mapslist, "mapId");
		
	}

	@Override
	public java.util.Map<Integer, Map> getMap() {
		return getMap;
	}



}
