package net.snake.gamemodel.panel.bean;

import net.snake.ibatis.IbatisEntity;

public class RoleUpgradeDesc implements IbatisEntity {

	/**
	 * t_role_upgrade_desc.f_id
	 */
	private Integer id;
	/**
	 * 角色等级 t_role_upgrade_desc.f_role_grade
	 */
	private Integer roleGrade;
	/**
	 * 角色描述 t_role_upgrade_desc.f_grade_desc
	 */
	private String gradeDesc;
	/**
	 * 角色描述 t_role_upgrade_desc.f_grade_desc_i18n
	 */
	private String gradeDescI18n;
	/**
	 * 当前经验 t_role_upgrade_desc.f_now_exp
	 */
	private Long nowExp;

	/**
	 * t_role_upgrade_desc.f_id
	 * 
	 * @return the value of t_role_upgrade_desc.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_role_upgrade_desc.f_id
	 * 
	 * @param id
	 *            the value for t_role_upgrade_desc.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 角色等级 t_role_upgrade_desc.f_role_grade
	 * 
	 * @return the value of t_role_upgrade_desc.f_role_grade
	 */
	public Integer getRoleGrade() {
		return roleGrade;
	}

	/**
	 * 角色等级 t_role_upgrade_desc.f_role_grade
	 * 
	 * @param roleGrade
	 *            the value for t_role_upgrade_desc.f_role_grade
	 */
	public void setRoleGrade(Integer roleGrade) {
		this.roleGrade = roleGrade;
	}

	/**
	 * 角色描述 t_role_upgrade_desc.f_grade_desc
	 * 
	 * @return the value of t_role_upgrade_desc.f_grade_desc
	 */
	public String getGradeDesc() {
		return gradeDesc;
	}

	/**
	 * 角色描述 t_role_upgrade_desc.f_grade_desc
	 * 
	 * @param gradeDesc
	 *            the value for t_role_upgrade_desc.f_grade_desc
	 */
	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}

	/**
	 * 角色描述 t_role_upgrade_desc.f_grade_desc_i18n
	 * 
	 * @return the value of t_role_upgrade_desc.f_grade_desc_i18n
	 */
	public String getGradeDescI18n() {
		return gradeDescI18n;
	}

	/**
	 * 角色描述 t_role_upgrade_desc.f_grade_desc_i18n
	 * 
	 * @param gradeDescI18n
	 *            the value for t_role_upgrade_desc.f_grade_desc_i18n
	 */
	public void setGradeDescI18n(String gradeDescI18n) {
		this.gradeDescI18n = gradeDescI18n;
	}

	/**
	 * 当前经验 t_role_upgrade_desc.f_now_exp
	 * 
	 * @return the value of t_role_upgrade_desc.f_now_exp
	 */
	public Long getNowExp() {
		return nowExp;
	}

	/**
	 * 当前经验 t_role_upgrade_desc.f_now_exp
	 * 
	 * @param nowExp
	 *            the value for t_role_upgrade_desc.f_now_exp
	 */
	public void setNowExp(Long nowExp) {
		this.nowExp = nowExp;
	}
}
