package net.snake.gamemodel.activities.bean;

import net.snake.ibatis.IbatisEntity;

public class XianshiActivity implements IbatisEntity {

	/**
	 * t_xianshi_activity.f_id
	 */
	private Integer id;
	/**
	 * 活动名字 t_xianshi_activity.f_name
	 */
	private String name;
	/**
	 * 活动描述 t_xianshi_activity.f_desc
	 */
	private String desc;
	/**
	 * 活动开启时间段 t_xianshi_activity.f_time_limit
	 */
	private String timeLimit;
	/**
	 * 活动按钮显示 t_xianshi_activity.f_menu_desc
	 */
	private String menuDesc;
	/**
	 * 面板最底部描述 t_xianshi_activity.f_ui_bottom_desc
	 */
	private String uiBottomDesc;
	/**
	 * 活动显示顺序 t_xianshi_activity.f_order
	 */
	private Integer order;
	/**
	 * 查询用备注，不要重名 t_xianshi_activity.f_remark
	 */
	private String remark;
	/**
	 * 活动名字国际化 t_xianshi_activity.f_name_i18n
	 */
	private String nameI18n;
	/**
	 * 活动描述国际化 t_xianshi_activity.f_desc_i18n
	 */
	private String descI18n;
	/**
	 * 活动按钮显示国际化 t_xianshi_activity.f_menu_desc_i18n
	 */
	private String menuDescI18n;
	/**
	 * 面板最底部描述国际化 t_xianshi_activity.f_ui_bottom_desc_i18n
	 */
	private String uiBottomDescI18n;
	/**
	 * 查询用备注，不要重名国际化 t_xianshi_activity.f_remark_i18n
	 */
	private String remarkI18n;

	/**
	 * t_xianshi_activity.f_id
	 * 
	 * @return the value of t_xianshi_activity.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_xianshi_activity.f_id
	 * 
	 * @param id
	 *            the value for t_xianshi_activity.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 活动名字 t_xianshi_activity.f_name
	 * 
	 * @return the value of t_xianshi_activity.f_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 活动名字 t_xianshi_activity.f_name
	 * 
	 * @param name
	 *            the value for t_xianshi_activity.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 活动描述 t_xianshi_activity.f_desc
	 * 
	 * @return the value of t_xianshi_activity.f_desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 活动描述 t_xianshi_activity.f_desc
	 * 
	 * @param desc
	 *            the value for t_xianshi_activity.f_desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 活动开启时间段 t_xianshi_activity.f_time_limit
	 * 
	 * @return the value of t_xianshi_activity.f_time_limit
	 */
	public String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 活动开启时间段 t_xianshi_activity.f_time_limit
	 * 
	 * @param timeLimit
	 *            the value for t_xianshi_activity.f_time_limit
	 */
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 活动按钮显示 t_xianshi_activity.f_menu_desc
	 * 
	 * @return the value of t_xianshi_activity.f_menu_desc
	 */
	public String getMenuDesc() {
		return menuDesc;
	}

	/**
	 * 活动按钮显示 t_xianshi_activity.f_menu_desc
	 * 
	 * @param menuDesc
	 *            the value for t_xianshi_activity.f_menu_desc
	 */
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	/**
	 * 面板最底部描述 t_xianshi_activity.f_ui_bottom_desc
	 * 
	 * @return the value of t_xianshi_activity.f_ui_bottom_desc
	 */
	public String getUiBottomDesc() {
		return uiBottomDesc;
	}

	/**
	 * 面板最底部描述 t_xianshi_activity.f_ui_bottom_desc
	 * 
	 * @param uiBottomDesc
	 *            the value for t_xianshi_activity.f_ui_bottom_desc
	 */
	public void setUiBottomDesc(String uiBottomDesc) {
		this.uiBottomDesc = uiBottomDesc;
	}

	/**
	 * 活动显示顺序 t_xianshi_activity.f_order
	 * 
	 * @return the value of t_xianshi_activity.f_order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * 活动显示顺序 t_xianshi_activity.f_order
	 * 
	 * @param order
	 *            the value for t_xianshi_activity.f_order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * 查询用备注，不要重名 t_xianshi_activity.f_remark
	 * 
	 * @return the value of t_xianshi_activity.f_remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 查询用备注，不要重名 t_xianshi_activity.f_remark
	 * 
	 * @param remark
	 *            the value for t_xianshi_activity.f_remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 活动名字国际化 t_xianshi_activity.f_name_i18n
	 * 
	 * @return the value of t_xianshi_activity.f_name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 活动名字国际化 t_xianshi_activity.f_name_i18n
	 * 
	 * @param nameI18n
	 *            the value for t_xianshi_activity.f_name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 活动描述国际化 t_xianshi_activity.f_desc_i18n
	 * 
	 * @return the value of t_xianshi_activity.f_desc_i18n
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * 活动描述国际化 t_xianshi_activity.f_desc_i18n
	 * 
	 * @param descI18n
	 *            the value for t_xianshi_activity.f_desc_i18n
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	/**
	 * 活动按钮显示国际化 t_xianshi_activity.f_menu_desc_i18n
	 * 
	 * @return the value of t_xianshi_activity.f_menu_desc_i18n
	 */
	public String getMenuDescI18n() {
		return menuDescI18n;
	}

	/**
	 * 活动按钮显示国际化 t_xianshi_activity.f_menu_desc_i18n
	 * 
	 * @param menuDescI18n
	 *            the value for t_xianshi_activity.f_menu_desc_i18n
	 */
	public void setMenuDescI18n(String menuDescI18n) {
		this.menuDescI18n = menuDescI18n;
	}

	/**
	 * 面板最底部描述国际化 t_xianshi_activity.f_ui_bottom_desc_i18n
	 * 
	 * @return the value of t_xianshi_activity.f_ui_bottom_desc_i18n
	 */
	public String getUiBottomDescI18n() {
		return uiBottomDescI18n;
	}

	/**
	 * 面板最底部描述国际化 t_xianshi_activity.f_ui_bottom_desc_i18n
	 * 
	 * @param uiBottomDescI18n
	 *            the value for t_xianshi_activity.f_ui_bottom_desc_i18n
	 */
	public void setUiBottomDescI18n(String uiBottomDescI18n) {
		this.uiBottomDescI18n = uiBottomDescI18n;
	}

	/**
	 * 查询用备注，不要重名国际化 t_xianshi_activity.f_remark_i18n
	 * 
	 * @return the value of t_xianshi_activity.f_remark_i18n
	 */
	public String getRemarkI18n() {
		return remarkI18n;
	}

	/**
	 * 查询用备注，不要重名国际化 t_xianshi_activity.f_remark_i18n
	 * 
	 * @param remarkI18n
	 *            the value for t_xianshi_activity.f_remark_i18n
	 */
	public void setRemarkI18n(String remarkI18n) {
		this.remarkI18n = remarkI18n;
	}
}
