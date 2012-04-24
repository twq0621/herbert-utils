package net.snake.commons.script;

public interface PropertiesSupport {
	/**
	 * 设置一个属性
	 * @param key
	 * @param value
	 */
	public abstract void setAttribute(String key, Object value);
	/**
	 * 获得属性
	 * @param key
	 * @return
	 */
	public abstract Object getAttribute(String key);
	/**
	 * 移除属性
	 * @param key
	 * @return
	 */
	public abstract Object removeAttribute(String key);
	/**
	 * 移除所有属性
	 */
	public abstract void removeAllAttribute();

}
