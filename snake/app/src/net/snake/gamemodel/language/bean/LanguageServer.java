package net.snake.gamemodel.language.bean;

import net.snake.ibatis.IbatisEntity;

public class LanguageServer implements IbatisEntity {

	/**
	 * t_language_server.id
	 * 
	 */
	private Integer id;
	/**
	 * t_language_server.value
	 * 
	 */
	private String value;
	/**
	 * t_language_server.value_i18n
	 * 
	 */
	private String valueI18n;

	/**
	 * t_language_server.id
	 * 
	 * @return the value of t_language_server.id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_language_server.id
	 * 
	 * @param id
	 *            the value for t_language_server.id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * t_language_server.value
	 * 
	 * @return the value of t_language_server.value
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * t_language_server.value
	 * 
	 * @param value
	 *            the value for t_language_server.value
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * t_language_server.value_i18n
	 * 
	 * @return the value of t_language_server.value_i18n
	 * 
	 */
	public String getValueI18n() {
		return valueI18n;
	}

	/**
	 * t_language_server.value_i18n
	 * 
	 * @param valueI18n
	 *            the value for t_language_server.value_i18n
	 * 
	 */
	public void setValueI18n(String valueI18n) {
		this.valueI18n = valueI18n;
	}
}
