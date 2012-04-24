package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class RoleRandomName implements IbatisEntity {
	/**
	 * t_role_random_name.f_id
	 * 
	 */
	private Integer fId;

	/**
	 * 类别 1姓 ，2男名，3女名 t_role_random_name.f_type
	 * 
	 */
	private Integer fType;

	/**
	 * 类别对应值 t_role_random_name.f_value
	 * 
	 */
	private String fValue;

	/**
	 * t_role_random_name.f_id
	 * 
	 * @return the value of t_role_random_name.f_id
	 * 
	 */
	public Integer getfId() {
		return fId;
	}

	/**
	 * t_role_random_name.f_id
	 * 
	 * @param fId
	 *            the value for t_role_random_name.f_id
	 * 
	 */
	public void setfId(Integer fId) {
		this.fId = fId;
	}

	/**
	 * 类别 1姓 ，2男名，3女名 t_role_random_name.f_type
	 * 
	 * @return the value of t_role_random_name.f_type
	 * 
	 */
	public Integer getfType() {
		return fType;
	}

	/**
	 * 类别 1姓 ，2男名，3女名 t_role_random_name.f_type
	 * 
	 * @param fType
	 *            the value for t_role_random_name.f_type
	 * 
	 */
	public void setfType(Integer fType) {
		this.fType = fType;
	}

	/**
	 * 类别对应值 t_role_random_name.f_value
	 * 
	 * @return the value of t_role_random_name.f_value
	 * 
	 */
	public String getfValue() {
		return fValue;
	}

	/**
	 * 类别对应值 t_role_random_name.f_value
	 * 
	 * @param fValue
	 *            the value for t_role_random_name.f_value
	 * 
	 */
	public void setfValue(String fValue) {
		this.fValue = fValue;
	}

	public static int xingType = 1;
	public static int maleType = 2;
	public static int femaleType = 3;
}
