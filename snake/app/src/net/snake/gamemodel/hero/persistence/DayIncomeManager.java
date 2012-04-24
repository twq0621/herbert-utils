package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.CharDayInComeData;
import net.snake.ibatis.SystemFactory;
import org.apache.log4j.Logger;


/**
 * @author serv_dev
 */
public class DayIncomeManager {
	public static DayIncomeManager instance;
	private static Logger log = Logger.getLogger(DayIncomeManager.class);
	private CharDayInComeDataDAO dao = new CharDayInComeDataDAO(SystemFactory.getCharacterSqlMapClient());

	public static DayIncomeManager getInstance() {
		if (instance == null) {
			instance = new DayIncomeManager();
		}
		return instance;
	}

	private DayIncomeManager() {
		try {
			init();
			if (log.isDebugEnabled()) {
				log.debug("当日统计初始化完成");
			}

		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void init() throws SQLException {
		dao.insertInit();
	}

	public CharDayInComeData getInComeByCharID(int characterid) throws SQLException {
		return dao.selectByPrimaryKey(characterid);
	}

	public void update(CharDayInComeData data) throws SQLException {
		dao.updateByPrimaryKey(data);
	}

	@SuppressWarnings("static-access")
	public void syncInsert(final CharDayInComeData data) {
		GameServer.getInstance().executorServiceForDB.submit(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insert(data);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		});
	}

}
