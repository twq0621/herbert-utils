package net.snake.gamemodel.gm.bean;

import net.snake.ibatis.IbatisEntity;

public class ManageIP implements IbatisEntity {

	/**
	 * 允许的IP t_manageip.ip
	 */
	private String ip;
	/**
	 * 描述 t_manageip.fdesc
	 */
	private String fdesc;

	/**
	 * 允许的IP t_manageip.ip
	 * 
	 * @return the value of t_manageip.ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 允许的IP t_manageip.ip
	 * 
	 * @param ip
	 *            the value for t_manageip.ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 描述 t_manageip.fdesc
	 * 
	 * @return the value of t_manageip.fdesc
	 */
	public String getFdesc() {
		return fdesc;
	}

	/**
	 * 描述 t_manageip.fdesc
	 * 
	 * @param fdesc
	 *            the value for t_manageip.fdesc
	 */
	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}
}
