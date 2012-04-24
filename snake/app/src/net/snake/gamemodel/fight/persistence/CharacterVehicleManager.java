package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.fight.bean.CharacterVehicle;
import net.snake.ibatis.SystemFactory;
import org.apache.log4j.Logger;


/**
 * 攻城
 * 
 * @author jack
 * 
 */
public class CharacterVehicleManager {
	private static Logger logger = Logger.getLogger(CharacterVehicleManager.class);
	private static CharacterVehicleDAO dao = new CharacterVehicleDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterVehicleManager instance;

	private CharacterVehicleManager() {
	}

	public static CharacterVehicleManager getInstance() {
		if (instance == null) {
			instance = new CharacterVehicleManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<CharacterVehicle> selectVechicleListByCharacterId(int id) {
		try {
			return dao.selectByCharacterId(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void deleteCharacterVehicle(final CharacterVehicle cv) {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				try {
					dao.deleteByPrimaryKey(cv.getId());
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}

			}
		});

	}

	public void updateCharacterVehicle(final CharacterVehicle cv) {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				try {
					dao.updateByPrimaryKey(cv);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}

			}
		});

	}

	public void insertCharacterVehicle(final CharacterVehicle cv) {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				try {
					dao.insert(cv);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}

			}
		});
	}

}
