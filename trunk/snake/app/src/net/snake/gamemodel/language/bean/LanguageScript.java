package net.snake.gamemodel.language.bean;

import net.snake.ibatis.IbatisEntity;

public class LanguageScript implements IbatisEntity {
	/**
	 * t_language_script.f_id
	 * 
	 */
	private Integer id;

	/**
	 * 脚本提示属性键 t_language_script.f_key
	 * 
	 */
	private String key;

	/**
	 * 脚本提示属性值 t_language_script.f_value
	 * 
	 */
	private String value;

	/**
	 * 脚本提示属性值国际化 t_language_script.f_value_i18n
	 * 
	 */
	private String valueI18n;

	/**
	 * t_language_script.f_id
	 * 
	 * @return the value of t_language_script.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_language_script.f_id
	 * 
	 * @param id
	 *            the value for t_language_script.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 脚本提示属性键 t_language_script.f_key
	 * 
	 * @return the value of t_language_script.f_key
	 * 
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 脚本提示属性键 t_language_script.f_key
	 * 
	 * @param key
	 *            the value for t_language_script.f_key
	 * 
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 脚本提示属性值 t_language_script.f_value
	 * 
	 * @return the value of t_language_script.f_value
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 脚本提示属性值 t_language_script.f_value
	 * 
	 * @param value
	 *            the value for t_language_script.f_value
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 脚本提示属性值国际化 t_language_script.f_value_i18n
	 * 
	 * @return the value of t_language_script.f_value_i18n
	 * 
	 */
	public String getValueI18n() {
		return valueI18n;
	}

	/**
	 * 脚本提示属性值国际化 t_language_script.f_value_i18n
	 * 
	 * @param valueI18n
	 *            the value for t_language_script.f_value_i18n
	 * 
	 */
	public void setValueI18n(String valueI18n) {
		this.valueI18n = valueI18n;
	}
}
