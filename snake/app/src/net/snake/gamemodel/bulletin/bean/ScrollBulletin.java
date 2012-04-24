package net.snake.gamemodel.bulletin.bean;

import net.snake.ibatis.IbatisEntity;

public class ScrollBulletin implements IbatisEntity {
	/**
	 * 动滚公告 t_scroll_bulletin.f_id
	 */
	private Integer id;
	/**
	 * 公告内容 t_scroll_bulletin.f_content
	 */
	private String content;
	/**
	 * 时间表达式 t_scroll_bulletin.f_timeexp
	 */
	private String timeexp;
	/**
	 * 0 开启 1关闭 t_scroll_bulletin.f_state
	 */
	private Short state;
	/**
	 * 逗号分割 1:左聊天框中 2:系统提示(右侧) 3:弹出ALERT消息 4:系统公告(滚动) 5:中间明显提示区(角色脚下) 6:系统公告(切出) 7:中间明显提示区(BOSS喊话等) t_scroll_bulletin.f_type
	 */
	private String type;
	/**
	 * 公告内容国际化 t_scroll_bulletin.f_content_i18n
	 */
	private String contentI18n;

	/**
	 * 动滚公告 t_scroll_bulletin.f_id
	 * 
	 * @return the value of t_scroll_bulletin.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 动滚公告 t_scroll_bulletin.f_id
	 * 
	 * @param id
	 *            the value for t_scroll_bulletin.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 公告内容 t_scroll_bulletin.f_content
	 * 
	 * @return the value of t_scroll_bulletin.f_content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 公告内容 t_scroll_bulletin.f_content
	 * 
	 * @param content
	 *            the value for t_scroll_bulletin.f_content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 时间表达式 t_scroll_bulletin.f_timeexp
	 * 
	 * @return the value of t_scroll_bulletin.f_timeexp
	 */
	public String getTimeexp() {
		return timeexp;
	}

	/**
	 * 时间表达式 t_scroll_bulletin.f_timeexp
	 * 
	 * @param timeexp
	 *            the value for t_scroll_bulletin.f_timeexp
	 */
	public void setTimeexp(String timeexp) {
		this.timeexp = timeexp;
	}

	/**
	 * 0 开启 1关闭 t_scroll_bulletin.f_state
	 * 
	 * @return the value of t_scroll_bulletin.f_state
	 */
	public Short getState() {
		return state;
	}

	/**
	 * 0 开启 1关闭 t_scroll_bulletin.f_state
	 * 
	 * @param state
	 *            the value for t_scroll_bulletin.f_state
	 */
	public void setState(Short state) {
		this.state = state;
	}

	/**
	 * 逗号分割 1:左聊天框中 2:系统提示(右侧) 3:弹出ALERT消息 4:系统公告(滚动) 5:中间明显提示区(角色脚下) 6:系统公告(切出) 7:中间明显提示区(BOSS喊话等) t_scroll_bulletin.f_type
	 * 
	 * @return the value of t_scroll_bulletin.f_type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 逗号分割 1:左聊天框中 2:系统提示(右侧) 3:弹出ALERT消息 4:系统公告(滚动) 5:中间明显提示区(角色脚下) 6:系统公告(切出) 7:中间明显提示区(BOSS喊话等) t_scroll_bulletin.f_type
	 * 
	 * @param type
	 *            the value for t_scroll_bulletin.f_type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 公告内容国际化 t_scroll_bulletin.f_content_i18n
	 * 
	 * @return the value of t_scroll_bulletin.f_content_i18n
	 */
	public String getContentI18n() {
		return contentI18n;
	}

	/**
	 * 公告内容国际化 t_scroll_bulletin.f_content_i18n
	 * 
	 * @param contentI18n
	 *            the value for t_scroll_bulletin.f_content_i18n
	 */
	public void setContentI18n(String contentI18n) {
		this.contentI18n = contentI18n;
	}

	private boolean send = false;

	public boolean isSend() {
		return send;
	}

	public void setSend(boolean send) {
		this.send = send;
	}

	public boolean isEnable() {
		return getState() == 0;
	}
}
