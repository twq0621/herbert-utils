package net.snake.gamemodel.gm.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class Onlineinfo implements IbatisEntity {

	/**
	 * t_onlineinfo.f_id
	 */
	private Integer id;
	/**
	 * t_onlineinfo.f_create_date
	 */
	private Date createDate;
	/**
	 * t_onlineinfo.f_count
	 */
	private Integer count;
	/**
	 * t_onlineinfo.f_server_id
	 */
	private Integer serverId;
	/**
	 * t_onlineinfo.f_server_lineid
	 */
	private Integer serverLineid;

	/**
	 * t_onlineinfo.f_id
	 * 
	 * @return the value of t_onlineinfo.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_onlineinfo.f_id
	 * 
	 * @param id
	 *            the value for t_onlineinfo.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * t_onlineinfo.f_create_date
	 * 
	 * @return the value of t_onlineinfo.f_create_date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * t_onlineinfo.f_create_date
	 * 
	 * @param createDate
	 *            the value for t_onlineinfo.f_create_date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * t_onlineinfo.f_count
	 * 
	 * @return the value of t_onlineinfo.f_count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * t_onlineinfo.f_count
	 * 
	 * @param count
	 *            the value for t_onlineinfo.f_count
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * t_onlineinfo.f_server_id
	 * 
	 * @return the value of t_onlineinfo.f_server_id
	 */
	public Integer getServerId() {
		return serverId;
	}

	/**
	 * t_onlineinfo.f_server_id
	 * 
	 * @param serverId
	 *            the value for t_onlineinfo.f_server_id
	 */
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	/**
	 * t_onlineinfo.f_server_lineid
	 * 
	 * @return the value of t_onlineinfo.f_server_lineid
	 */
	public Integer getServerLineid() {
		return serverLineid;
	}

	/**
	 * t_onlineinfo.f_server_lineid
	 * 
	 * @param serverLineid
	 *            the value for t_onlineinfo.f_server_lineid
	 */
	public void setServerLineid(Integer serverLineid) {
		this.serverLineid = serverLineid;
	}
}
