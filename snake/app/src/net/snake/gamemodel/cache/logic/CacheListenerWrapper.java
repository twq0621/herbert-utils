package net.snake.gamemodel.cache.logic;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.cache.bean.CacheEntry;

class CacheListenerWrapper {
	long previousCheckTime = System.currentTimeMillis();
	CacheUpdateListener cacheupdatelistener;
	CacheEntry initCacheEntry;

	public CacheListenerWrapper(CacheUpdateListener cacheupdatelistener, CacheEntry initCacheEntry) {
		this.cacheupdatelistener = cacheupdatelistener;
		this.initCacheEntry = initCacheEntry;
	}

	@Override
	public boolean equals(Object o) {
		CacheUpdateListener clo = ((CacheListenerWrapper) o).cacheupdatelistener;
		return cacheupdatelistener.equals(clo);
	}

	@Override
	public int hashCode() {
		return cacheupdatelistener.hashCode();
	}
}
