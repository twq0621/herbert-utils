package net.snake.gamemodel.cache.bean;

import net.snake.ibatis.IbatisEntity;

public class CacheEntry implements IbatisEntity {
	private String appname;
	private String cachename;
	private int version;

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getCachename() {
		return cachename;
	}

	public void setCachename(String cachename) {
		this.cachename = cachename;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getVersion() {
		return version;
	}

	public String getKey() {
		return getAppname() + "_|_" + getCachename();
	}

}
