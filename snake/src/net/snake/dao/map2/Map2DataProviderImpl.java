package net.snake.dao.map2;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;





public class Map2DataProviderImpl implements Map2DataProvider ,InitializingBean{

	private List<Map2> mapslist = null;
	private Map2DAO map2dao;
	public Map2DAO getMap2dao() {
		return map2dao;
	}


	public void setMap2dao(Map2DAO map2dao) {
		this.map2dao = map2dao;
	}


	@Override
	public List<Map2> getMapList() {
		Map2Example example = new Map2Example();
		example.setOrderByClause("f_map_id");
		mapslist = map2dao.selectByExample(example);
		return mapslist;
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
//		Map2Example example = new Map2Example();
//		example.setOrderByClause("f_map_id");
//		mapslist = map2dao.selectByExample(example);
//System.out.println("map2list======="+mapslist.size());
		
	}


	@Override
	public List<Map2> getMap2byId(int id) {
		Map2Example example = new Map2Example();
		example.createCriteria().andMapIdEqualTo(id);
		mapslist = map2dao.selectByExample(example);
		return mapslist;
	}



}
