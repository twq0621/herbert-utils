package net.snake.dao.horsemodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.common.BeanTool;

import org.springframework.beans.factory.InitializingBean;

public class HorseModelProviderImpl implements HorseModelProvider ,InitializingBean{

	private List<HorseModel> listHorseModels ;
	private Map<Integer, HorseModel> maphorsemodel = new HashMap<Integer, HorseModel>();
	private HorseModelDAO horseModelDAO;
	public HorseModelDAO getHorseModelDAO() {
		return horseModelDAO;
	}
	public void setHorseModelDAO(HorseModelDAO horseModelDAO) {
		this.horseModelDAO = horseModelDAO;
	}
	@Override
	public List<HorseModel> getHorseModels() {
		
		return listHorseModels;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		HorseModelExample example = new HorseModelExample();
		example.setOrderByClause("f_id");
		listHorseModels = horseModelDAO.selectByExample(example);
		maphorsemodel = BeanTool.listToMap(listHorseModels,"id");
		System.out.println("horseModel--------"+listHorseModels.size());
	}
	@Override
	public HorseModel getHorseModelById(int id) {
		return maphorsemodel.get(id);
	}

}
