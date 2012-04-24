package net.snake.gamemodel.fight.bean;

import net.snake.ibatis.IbatisEntity;

public class RichPlayRuleData implements IbatisEntity {

	/**
	 * t_richplay_rule.id
	 */
	private Integer id;
	/**
	 * 丰富玩法名称 t_richplay_rule.f_name
	 */
	private String fName;
	/**
	 * 玩法简介 t_richplay_rule.f_ruledesc
	 */
	private String fRuledesc;
	/**
	 * 玩法等级 t_richplay_rule.f_grade
	 */
	private Integer fGrade;
	/**
	 * 丰富玩法名称国际化 t_richplay_rule.f_name_i18n
	 */
	private String fNameI18n;
	/**
	 * 玩法简介国际化 t_richplay_rule.f_ruledesc_i18n
	 */
	private String fRuledescI18n;

	/**
	 * t_richplay_rule.id
	 * 
	 * @return the value of t_richplay_rule.id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_richplay_rule.id
	 * 
	 * @param id
	 *            the value for t_richplay_rule.id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 丰富玩法名称 t_richplay_rule.f_name
	 * 
	 * @return the value of t_richplay_rule.f_name
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * 丰富玩法名称 t_richplay_rule.f_name
	 * 
	 * @param fName
	 *            the value for t_richplay_rule.f_name
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * 玩法简介 t_richplay_rule.f_ruledesc
	 * 
	 * @return the value of t_richplay_rule.f_ruledesc
	 */
	public String getfRuledesc() {
		return fRuledesc;
	}

	/**
	 * 玩法简介 t_richplay_rule.f_ruledesc
	 * 
	 * @param fRuledesc
	 *            the value for t_richplay_rule.f_ruledesc
	 */
	public void setfRuledesc(String fRuledesc) {
		this.fRuledesc = fRuledesc;
	}

	/**
	 * 玩法等级 t_richplay_rule.f_grade
	 * 
	 * @return the value of t_richplay_rule.f_grade
	 */
	public Integer getfGrade() {
		return fGrade;
	}

	/**
	 * 玩法等级 t_richplay_rule.f_grade
	 * 
	 * @param fGrade
	 *            the value for t_richplay_rule.f_grade
	 */
	public void setfGrade(Integer fGrade) {
		this.fGrade = fGrade;
	}

	/**
	 * 丰富玩法名称国际化 t_richplay_rule.f_name_i18n
	 * 
	 * @return the value of t_richplay_rule.f_name_i18n
	 */
	public String getfNameI18n() {
		return fNameI18n;
	}

	/**
	 * 丰富玩法名称国际化 t_richplay_rule.f_name_i18n
	 * 
	 * @param fNameI18n
	 *            the value for t_richplay_rule.f_name_i18n
	 */
	public void setfNameI18n(String fNameI18n) {
		this.fNameI18n = fNameI18n;
	}

	/**
	 * 玩法简介国际化 t_richplay_rule.f_ruledesc_i18n
	 * 
	 * @return the value of t_richplay_rule.f_ruledesc_i18n
	 */
	public String getfRuledescI18n() {
		return fRuledescI18n;
	}

	/**
	 * 玩法简介国际化 t_richplay_rule.f_ruledesc_i18n
	 * 
	 * @param fRuledescI18n
	 *            the value for t_richplay_rule.f_ruledesc_i18n
	 */
	public void setfRuledescI18n(String fRuledescI18n) {
		this.fRuledescI18n = fRuledescI18n;
	}
}
