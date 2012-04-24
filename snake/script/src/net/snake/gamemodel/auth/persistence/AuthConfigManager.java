package net.snake.gamemodel.auth.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.sql.SQLException;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.auth.bean.AuthConfig;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class AuthConfigManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(AuthConfigManager.class);
	private static AuthConfigManager instance;
	private AuthConfig authconfig;
	private AuthConfigDAO authconfigdao = new AuthConfigDAO(SystemFactory.getGamedataSqlMapClient());
	private AuthConfig acrossConfig;

	private AuthConfigManager() {
	}

	public static AuthConfigManager getInstance() {
		if (instance == null) {
			instance = new AuthConfigManager();
		}
		return instance;
	}

	public String getAcrossMd5Key() {
		return acrossConfig.getMd5key();
	}

	public String getMd5Key() {
		return authconfig.getMd5key();
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "auth_config";
	}

	@Override
	public void reload() {
		try {
			authconfig = authconfigdao.selectByPrimaryKey(1);
			acrossConfig = authconfigdao.selectByPrimaryKey(3);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
