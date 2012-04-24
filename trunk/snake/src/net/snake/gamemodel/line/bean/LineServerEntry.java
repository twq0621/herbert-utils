package net.snake.gamemodel.line.bean;

import net.snake.ibatis.IbatisEntity;

public class LineServerEntry extends LineServerEntryKey implements IbatisEntity {

	/**
	 * 线路名 t_lineserver.name
	 */
	private String name;
	/**
	 * 内网IP t_lineserver.ip_adr
	 */
	// private String ipAdr;
	/**
	 * 端口号 t_lineserver.port
	 */
	// private String port;
	/**
	 * 客户端请求的IP或域名 t_lineserver.out_ip_adr
	 */
	// private String outIpAdr;
	/**
	 * 服务器状态 1正常 0无法连接监控 t_lineserver.server_status
	 */
	// private Integer serverStatus;
	/**
	 * t_lineserver.free_memory
	 */
	// private Integer freeMemory;
	/**
	 * t_lineserver.total_memory
	 */
	// private Integer totalMemory;
	/**
	 * 在线人数 t_lineserver.online_num
	 */
	// private Integer onlineNum;
	/**
	 * 在线数统计的更新时间 t_lineserver.online_num_update_time
	 */
	// private Date onlineNumUpdateTime;
	/**
	 * 该分线服务器是否启用 1启用 0禁用 t_lineserver.enable
	 */
	private Integer enable;

	/**
	 * 线路名 t_lineserver.name
	 * 
	 * @return the value of t_lineserver.name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 线路名 t_lineserver.name
	 * 
	 * @param name
	 *            the value for t_lineserver.name
	 */
	public void setName(String name) {
		this.name = name;
	}

	// /**
	// * 内网IP t_lineserver.ip_adr
	// * @return the value of t_lineserver.ip_adr
	//
	// */
	// public String getIpAdr() {
	// return ipAdr;
	// }

	/**
	 * 内网IP t_lineserver.ip_adr
	 * 
	 * @param ipAdr
	 *            the value for t_lineserver.ip_adr
	 */
	// public void setIpAdr(String ipAdr) {
	// this.ipAdr = ipAdr;
	// }

	// /**
	// * 端口号 t_lineserver.port
	// * @return the value of t_lineserver.port
	//
	// */
	// public String getPort() {
	// return port;
	// }
	//
	// /**
	// * 端口号 t_lineserver.port
	// * @param port the value for t_lineserver.port
	//
	// */
	// public void setPort(String port) {
	// this.port = port;
	// }

	/**
	 * 客户端请求的IP或域名 t_lineserver.out_ip_adr
	 * 
	 * @return the value of t_lineserver.out_ip_adr
	 */
	// public String getOutIpAdr() {
	// return outIpAdr;
	// }

	/**
	 * 客户端请求的IP或域名 t_lineserver.out_ip_adr
	 * 
	 * @param outIpAdr
	 *            the value for t_lineserver.out_ip_adr
	 */
	// public void setOutIpAdr(String outIpAdr) {
	// this.outIpAdr = outIpAdr;
	// }

	/**
	 * 服务器状态 1正常 0无法连接监控 t_lineserver.server_status
	 * 
	 * @return the value of t_lineserver.server_status
	 */
	// public Integer getServerStatus() {
	// return serverStatus;
	// }

	/**
	 * 服务器状态 1正常 0无法连接监控 t_lineserver.server_status
	 * 
	 * @param serverStatus
	 *            the value for t_lineserver.server_status
	 */
	// public void setServerStatus(Integer serverStatus) {
	// this.serverStatus = serverStatus;
	// }

	/**
	 * t_lineserver.free_memory
	 * 
	 * @return the value of t_lineserver.free_memory
	 */
	// public Integer getFreeMemory() {
	// return freeMemory;
	// }

	/**
	 * t_lineserver.free_memory
	 * 
	 * @param freeMemory
	 *            the value for t_lineserver.free_memory
	 */
	// public void setFreeMemory(Integer freeMemory) {
	// this.freeMemory = freeMemory;
	// }

	/**
	 * t_lineserver.total_memory
	 * 
	 * @return the value of t_lineserver.total_memory
	 */
	// public Integer getTotalMemory() {
	// return totalMemory;
	// }

	/**
	 * t_lineserver.total_memory
	 * 
	 * @param totalMemory
	 *            the value for t_lineserver.total_memory
	 */
	// public void setTotalMemory(Integer totalMemory) {
	// this.totalMemory = totalMemory;
	// }

	/**
	 * 在线人数 t_lineserver.online_num
	 * 
	 * @return the value of t_lineserver.online_num
	 */
	// public Integer getOnlineNum() {
	// return onlineNum;
	// }

	/**
	 * 在线人数 t_lineserver.online_num
	 * 
	 * @param onlineNum
	 *            the value for t_lineserver.online_num
	 */
	// public void setOnlineNum(Integer onlineNum) {
	// this.onlineNum = onlineNum;
	// }

	/**
	 * 在线数统计的更新时间 t_lineserver.online_num_update_time
	 * 
	 * @return the value of t_lineserver.online_num_update_time
	 */
	// public Date getOnlineNumUpdateTime() {
	// return onlineNumUpdateTime;
	// }

	/**
	 * 在线数统计的更新时间 t_lineserver.online_num_update_time
	 * 
	 * @param onlineNumUpdateTime
	 *            the value for t_lineserver.online_num_update_time
	 */
	// public void setOnlineNumUpdateTime(Date onlineNumUpdateTime) {
	// this.onlineNumUpdateTime = onlineNumUpdateTime;
	// }

	/**
	 * 该分线服务器是否启用 1启用 0禁用 t_lineserver.enable
	 * 
	 * @return the value of t_lineserver.enable
	 */
	public Integer getEnable() {
		return enable;
	}

	/**
	 * 该分线服务器是否启用 1启用 0禁用 t_lineserver.enable
	 * 
	 * @param enable
	 *            the value for t_lineserver.enable
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public boolean isConnecting = false;

	public boolean isConnecting() {
		return isConnecting;
	}

	public void setConnecting(boolean isConnecting) {
		this.isConnecting = isConnecting;
	}

}
