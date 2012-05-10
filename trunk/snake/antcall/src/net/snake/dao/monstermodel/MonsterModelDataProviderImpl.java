package net.snake.dao.monstermodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.common.BeanTool;

import org.springframework.beans.factory.InitializingBean;



public class MonsterModelDataProviderImpl implements MonsterModelDataProvider,InitializingBean {
	
	private Map<Integer, MonsterModel> map =new HashMap<Integer, MonsterModel>();
	private List<MonsterModel> list = null;
	public List<MonsterModel> getList() {
		return list;
	}

	public void setList(List<MonsterModel> list) {
		this.list = list;
	}

	private MonsterModelDAO monsterModelDAO;
	public MonsterModelDAO getMonsterModelDAO() {
		return monsterModelDAO;
	}

	public void setMonsterModelDAO(MonsterModelDAO monsterModelDAO) {
		this.monsterModelDAO = monsterModelDAO;
	}

	@Override
	public MonsterModel getMonsterModelByID(int id) {
		return map.get(id);
	}

	@Override
	public List<MonsterModel> getMonsterModelList() {
		return list;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		MonsterModelExample example =new MonsterModelExample();
		example.setOrderByClause("f_id");
		list=monsterModelDAO.selectByExample(example);
		map=BeanTool.listToMap(list, "id");
		System.out.println("MonsterModel--------"+list.size());
		
	}

}
