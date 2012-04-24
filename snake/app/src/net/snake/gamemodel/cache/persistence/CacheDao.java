package net.snake.gamemodel.cache.persistence;

import com.ibatis.sqlmap.client.SqlMapClient;
import java.util.List;
import java.sql.SQLException;

import net.snake.gamemodel.cache.bean.CacheEntry;

public class CacheDao {
	private SqlMapClient sqlmapclient;

	public CacheDao() {
	}

	public CacheDao(SqlMapClient sqlmapclient) {
		setSqlmapclient(sqlmapclient);
	}

	public void setSqlmapclient(SqlMapClient sqlmapclient) {
		this.sqlmapclient = sqlmapclient;
	}

	public CacheEntry getCacheEntry(String appname, String cachename) throws SQLException {
		CacheEntry cacheentry = new CacheEntry();
		cacheentry.setAppname(appname);
		cacheentry.setCachename(cachename);
		return (CacheEntry) sqlmapclient.queryForObject("getCacheEntry", cacheentry);
	}

	@SuppressWarnings("unchecked")
	public List<CacheEntry> getCacheEntryList(String appname) throws SQLException {
		return sqlmapclient.queryForList("getCacheEntryList", appname);
	}

}
