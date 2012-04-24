package net.snake.gamemodel.config.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.sql.SQLException;

import net.snake.commons.BeanUtils;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.config.bean.ConfigParam;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class ConfigParamManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(ConfigParamManager.class);
	ConfigParamDAO configParamDAO;
	ConfigParam configparam = new ConfigParam();

	public ConfigParamManager() {
		configParamDAO = new ConfigParamDAO(SystemFactory.getGamedataSqlMapClient());
	}

	public ConfigParam getConfigParam() {
		return configparam;
	}

	@Override
	public void reload() {
		try {
			ConfigParam configparamnow = configParamDAO.selectByPrimaryKey(0);
			BeanUtils.copyProperties(configparamnow, configparam);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "configparam";
	}
}
