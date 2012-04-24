package net.snake.gamemodel.cache.logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.snake.gamemodel.cache.bean.CacheEntry;
import net.snake.gamemodel.cache.persistence.CacheDao;
import org.apache.log4j.Logger;

class CheckDbUpdate implements Runnable {
	private static Logger logger = Logger.getLogger(CheckDbUpdate.class);
	HashSet<CacheListenerWrapper> cachelistenerWrapperSet;
	CacheDao cacheDao;

	public CheckDbUpdate(HashSet<CacheListenerWrapper> cachelistenerWrapperSet, CacheDao cacheDao) {
		this.cachelistenerWrapperSet = cachelistenerWrapperSet;
		this.cacheDao = cacheDao;
	}

	private HashSet<String> getMonitorAppnameList() {
		HashSet<String> appnameset = new HashSet<String>();
		for (Iterator<CacheListenerWrapper> iter = cachelistenerWrapperSet.iterator(); iter.hasNext();) {
			CacheListenerWrapper item = iter.next();
			appnameset.add(item.cacheupdatelistener.getAppname());
		}
		return appnameset;
	}

	public HashMap<String, CacheEntry> getNewCacheEntry() {
		HashSet<String> appnameset = getMonitorAppnameList();
		if (appnameset == null || appnameset.size() == 0) {
			return null;
		}
		HashMap<String, CacheEntry> tempHashmap = new HashMap<String, CacheEntry>();
		try {
			for (String appname : appnameset) {
				List<CacheEntry> list = cacheDao.getCacheEntryList(appname);
				if (list == null) {
					continue;
				}
				for (CacheEntry elem : list) {
					tempHashmap.put(elem.getKey(), elem);
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return tempHashmap;
	}

	public void loadData() {
		ConcurrentLinkedQueue<CacheListenerWrapper> targets = new ConcurrentLinkedQueue<CacheListenerWrapper>();
		targets.addAll(cachelistenerWrapperSet);
	}

	public void run() {
		HashMap<String, CacheEntry> tempHashmap = this.getNewCacheEntry();
		for (CacheListenerWrapper cacheListenerWrapper : cachelistenerWrapperSet) {
			CacheEntry oldCacheEntry = cacheListenerWrapper.initCacheEntry;
			CacheEntry currentCacheEntry = tempHashmap.get(oldCacheEntry.getKey());
			if (currentCacheEntry == null) {
				continue;
			}
			if (currentCacheEntry.getVersion() <= oldCacheEntry.getVersion()) {
				continue;
			}
			cacheListenerWrapper.initCacheEntry = currentCacheEntry;
			try {
				cacheListenerWrapper.cacheupdatelistener.reload();
				logger.info(currentCacheEntry.getCachename() + "更新。。。为" + currentCacheEntry.getVersion() + "版本");
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
