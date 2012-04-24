/**
 * 
 */
package net.snake.gamemodel.activities.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.activities.bean.ActivityData;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 *@author serv_dev
 */
/**
 * @author Administrator
 * 
 */
public class ActivityDataManager implements CacheUpdateListener {
	private static Logger log = Logger.getLogger(ActivityDataManager.class);
	private static ActivityDataManager instance;
	private ActivityDataDAO dao = new ActivityDataDAO(SystemFactory.getGamedataSqlMapClient());
	private List<ActivityData> allActivity;
	private Map<Integer, ActivityData> map = new HashMap<Integer, ActivityData>();

	public static ActivityDataManager getInstance() {
		if (instance == null) {
			instance = new ActivityDataManager();
		}
		return instance;
	}

	private ActivityDataManager() {
	}

	public List<ActivityData> getAllActivity() {
		return allActivity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			allActivity = dao.select();
			for (ActivityData ac : allActivity) {
				map.put(ac.getId(), ac);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "activity";
	}

	public ActivityData getActivityDataById(int id) {
		return map.get(id);
	}
}
