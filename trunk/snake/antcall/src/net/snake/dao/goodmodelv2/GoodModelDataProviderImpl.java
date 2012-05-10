package net.snake.dao.goodmodelv2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.common.BeanTool;

import org.springframework.beans.factory.InitializingBean;




public class GoodModelDataProviderImpl implements GoodModelDataProvider,InitializingBean {

	private Map<Integer, GoodModelV2> map =new HashMap<Integer, GoodModelV2>();
	private List<GoodModelV2>list =null;
	
	private GoodModelV2DAO goodModelV2DAO;
	
	public GoodModelV2DAO getGoodModelV2DAO() {
		return goodModelV2DAO;
	}

	public void setGoodModelV2DAO(GoodModelV2DAO goodModelV2DAO) {
		this.goodModelV2DAO = goodModelV2DAO;
	}

	@Override
	public GoodModelV2 getGoodModelByID(int id) {
		
		init();
		return map.get(id);
	}

	@Override
	public List<GoodModelV2> getGoodModelList() {
		init();
		return list;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
//		GoodModelV2Example example = new GoodModelV2Example();
//		example.setOrderByClause("f_id");
//		list=goodModelV2DAO.selectByExample(example);
//		map=BeanTool.listToMap(list, "id");
//		System.out.println("goodmodel--------------"+list.size());
		
	}
	public void init(){
		GoodModelV2Example example = new GoodModelV2Example();
		example.setOrderByClause("f_id");
		list=goodModelV2DAO.selectByExample(example);
		map=BeanTool.listToMap(list, "id");
	}

}
