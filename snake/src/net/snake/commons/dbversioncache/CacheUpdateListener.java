package net.snake.commons.dbversioncache;

public interface CacheUpdateListener {
	public void reload();

	public String getAppname();

	public String getCachename();

}
