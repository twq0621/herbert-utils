package net.snake.commons.dbversioncache;

import java.net.URL;

import net.snake.consts.GameConstant;

import org.apache.log4j.xml.DOMConfigurator;

/**
 * 重新加载log4j配置文件
 * 
 * @author jack
 * 
 */
public class Log4jUpdateListener implements CacheUpdateListener {
	private static Log4jUpdateListener instance;

	public static Log4jUpdateListener getInstance() {
		if (instance == null) {
			instance = new Log4jUpdateListener();
		}
		return instance;
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "log4j";
	}

	@Override
	public void reload() {
		URL url = this.getClass().getResource("/log4j.xml");
		if (url == null) {
			return;
		}
		try {
			DOMConfigurator.configure(url);
		} catch (Exception e) {
		}
	}
}
