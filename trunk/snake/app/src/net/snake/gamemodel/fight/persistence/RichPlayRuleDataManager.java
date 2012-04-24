package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.commons.program.IReload;
import net.snake.gamemodel.fight.bean.RichPlayRuleData;
import net.snake.ibatis.SystemFactory;

/**
 * 丰富玩法管理类
 * 
 * @author serv_dev
 */
public class RichPlayRuleDataManager implements IReload {
	private static final Logger logger = Logger.getLogger(RichPlayRuleDataManager.class);
	public static RichPlayRuleDataManager instance;
	private RichPlayRuleDataDAO dao = new RichPlayRuleDataDAO(SystemFactory.getGamedataSqlMapClient());
	private List<RichPlayRuleData> selectByExample;

	public static RichPlayRuleDataManager getInstance() {
		if (instance == null) {
			instance = new RichPlayRuleDataManager();
		}
		return instance;
	}

	private RichPlayRuleDataManager() {
		reload();
	}

	public List<RichPlayRuleData> getAllRichPlayRuleData() {
		return selectByExample;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			selectByExample = dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
