package net.snake.gamemodel.recharge.bean;

import net.snake.ibatis.IbatisEntity;

public class RechargeIpList implements IbatisEntity {

	/**
	 * 主键ID t_recharge_ip_list.f_id
	 */
	private Byte id;
	/**
	 * 充值允许的IP t_recharge_ip_list.f_recharge_ip
	 */
	private String rechargeIp;

	/**
	 * 主键ID t_recharge_ip_list.f_id
	 * 
	 * @return the value of t_recharge_ip_list.f_id
	 */
	public Byte getId() {
		return id;
	}

	/**
	 * 主键ID t_recharge_ip_list.f_id
	 * 
	 * @param id
	 *            the value for t_recharge_ip_list.f_id
	 */
	public void setId(Byte id) {
		this.id = id;
	}

	/**
	 * 充值允许的IP t_recharge_ip_list.f_recharge_ip
	 * 
	 * @return the value of t_recharge_ip_list.f_recharge_ip
	 */
	public String getRechargeIp() {
		return rechargeIp;
	}

	/**
	 * 充值允许的IP t_recharge_ip_list.f_recharge_ip
	 * 
	 * @param rechargeIp
	 *            the value for t_recharge_ip_list.f_recharge_ip
	 */
	public void setRechargeIp(String rechargeIp) {
		this.rechargeIp = rechargeIp;
	}
}
