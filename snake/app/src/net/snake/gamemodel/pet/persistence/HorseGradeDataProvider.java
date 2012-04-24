package net.snake.gamemodel.pet.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.pet.bean.HorseGrade;
import net.snake.ibatis.SystemFactory;

public class HorseGradeDataProvider implements CacheUpdateListener {

	private static final Logger logger = Logger.getLogger(HorseGradeDataProvider.class);
	private static HorseGradeDataProvider instance;
	private HorseGradeDAO horseGradeDAO;
	private Map<Integer, HorseGrade> horsegradeMap = new HashMap<Integer, HorseGrade>();

	public static HorseGradeDataProvider getInstance() {
		if (instance == null) {
			instance = new HorseGradeDataProvider();
		}
		return instance;
	}

	private HorseGradeDataProvider() {
		horseGradeDAO = new HorseGradeDAO(SystemFactory.getGamedataSqlMapClient());
		reload();
	}

	public HorseGrade getHorseGradeById(int id) {
		return horsegradeMap.get(id);
	}

	@Override
	public void reload() {
		try {
			BeanTool.addOrUpdate(horsegradeMap, horseGradeDAO.select(), "levelId");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "horsegrade";
	}
}
