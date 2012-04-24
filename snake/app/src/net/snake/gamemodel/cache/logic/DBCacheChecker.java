package net.snake.gamemodel.cache.logic;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.cache.bean.CacheEntry;
import net.snake.gamemodel.cache.persistence.CacheDao;
import net.snake.ibatis.SystemFactory;

/**
 * 
 * @author serv_dev
 * 
 */
public class DBCacheChecker {
	private HashSet<CacheListenerWrapper> cachelistenerWrapperSet = new HashSet<CacheListenerWrapper>();
	private ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
	private CacheDao cacheDao;

	public DBCacheChecker() {
		this.cacheDao = new CacheDao(SystemFactory.getGamedataSqlMapClient());
	}

	public void init() {
		Runnable checkDbUpdate = new CheckDbUpdate(cachelistenerWrapperSet, cacheDao);
		long delay = 10 * 1000;
		ses.scheduleWithFixedDelay(checkDbUpdate, delay, delay, TimeUnit.MILLISECONDS);
	}

	public void destory() {
		ses.shutdown();
	}

	public void addCacheUpdateListener(CacheUpdateListener cacheupdatelistener) throws SQLException {
		CacheEntry cacheentry = cacheDao.getCacheEntry(cacheupdatelistener.getAppname(), cacheupdatelistener.getCachename());
		if (cacheentry == null) {
			return;
		}
		CacheListenerWrapper cachelistenerWrapper = new CacheListenerWrapper(cacheupdatelistener, cacheentry);
		synchronized (cachelistenerWrapperSet) {
			cachelistenerWrapperSet.add(cachelistenerWrapper);
		}
	}

	public void removeCacheUpdateListener(CacheUpdateListener cacheupdatelistener) {
		synchronized (cachelistenerWrapperSet) {
			cachelistenerWrapperSet.remove(new CacheListenerWrapper(cacheupdatelistener, null));
		}
	}

}
