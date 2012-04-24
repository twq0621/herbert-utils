package net.snake.gamemodel.line.bean;

import net.snake.ibatis.IbatisEntity;

public class ServerEntry  implements IbatisEntity {

	/**
	 * 登陆服务器id server_list.loginServerId
	 *
	 */
	private Integer loginserverid;
	/**
	 * 最大允许的玩家人数 server_list.maxPermitPlayerCount
	 *
	 */
	private Integer maxpermitplayercount;
	/**
	 * 登陆服务器名称 server_list.loginServerName
	 *
	 */
	private String loginservername;
	/**
	 * 外部IP或域名 server_list.loginServerIp
	 *
	 */
	private String loginserverip;
	/**
	 * 听监端口 server_list.loginServerPort
	 *
	 */
	private String loginserverport;
	/**
	 * 聊天IP server_list.chatServerIp
	 *
	 */
	private String chatserverip;
	/**
	 * 聊天端口 server_list.chatServerPort
	 *
	 */
	private String chatserverport;
	/**
	 * 静态资源地址 server_list.httpResURL
	 *
	 */
	private String httpresurl;
	/**
	 * 充值接口 server_list.recharge
	 *
	 */
	private String recharge;
	/**
	 * server_list.flashver
	 *
	 */
	private String flashver;
	/**
	 * server_list.homepage
	 *
	 */
	private String homepage;
	/**
	 * server_list.idcardurl
	 *
	 */
	private String idcardurl;

	/**
	 * 登陆服务器id server_list.loginServerId
	 * @return  the value of server_list.loginServerId
	 *
	 */
	public Integer getLoginserverid() {
		return loginserverid;
	}

	/**
	 * 登陆服务器id server_list.loginServerId
	 * @param loginserverid  the value for server_list.loginServerId
	 *
	 */
	public void setLoginserverid(Integer loginserverid) {
		this.loginserverid = loginserverid;
	}

	/**
	 * 最大允许的玩家人数 server_list.maxPermitPlayerCount
	 * @return  the value of server_list.maxPermitPlayerCount
	 *
	 */
	public Integer getMaxpermitplayercount() {
		return maxpermitplayercount;
	}

	/**
	 * 最大允许的玩家人数 server_list.maxPermitPlayerCount
	 * @param maxpermitplayercount  the value for server_list.maxPermitPlayerCount
	 *
	 */
	public void setMaxpermitplayercount(Integer maxpermitplayercount) {
		this.maxpermitplayercount = maxpermitplayercount;
	}

	/**
	 * 登陆服务器名称 server_list.loginServerName
	 * @return  the value of server_list.loginServerName
	 *
	 */
	public String getLoginservername() {
		return loginservername;
	}

	/**
	 * 登陆服务器名称 server_list.loginServerName
	 * @param loginservername  the value for server_list.loginServerName
	 *
	 */
	public void setLoginservername(String loginservername) {
		this.loginservername = loginservername;
	}

	/**
	 * 外部IP或域名 server_list.loginServerIp
	 * @return  the value of server_list.loginServerIp
	 *
	 */
	public String getLoginserverip() {
		return loginserverip;
	}

	/**
	 * 外部IP或域名 server_list.loginServerIp
	 * @param loginserverip  the value for server_list.loginServerIp
	 *
	 */
	public void setLoginserverip(String loginserverip) {
		this.loginserverip = loginserverip;
	}

	/**
	 * 听监端口 server_list.loginServerPort
	 * @return  the value of server_list.loginServerPort
	 *
	 */
	public String getLoginserverport() {
		return loginserverport;
	}

	/**
	 * 听监端口 server_list.loginServerPort
	 * @param loginserverport  the value for server_list.loginServerPort
	 *
	 */
	public void setLoginserverport(String loginserverport) {
		this.loginserverport = loginserverport;
	}

	/**
	 * 聊天IP server_list.chatServerIp
	 * @return  the value of server_list.chatServerIp
	 *
	 */
	public String getChatserverip() {
		return chatserverip;
	}

	/**
	 * 聊天IP server_list.chatServerIp
	 * @param chatserverip  the value for server_list.chatServerIp
	 *
	 */
	public void setChatserverip(String chatserverip) {
		this.chatserverip = chatserverip;
	}

	/**
	 * 聊天端口 server_list.chatServerPort
	 * @return  the value of server_list.chatServerPort
	 *
	 */
	public String getChatserverport() {
		return chatserverport;
	}

	/**
	 * 聊天端口 server_list.chatServerPort
	 * @param chatserverport  the value for server_list.chatServerPort
	 *
	 */
	public void setChatserverport(String chatserverport) {
		this.chatserverport = chatserverport;
	}

	/**
	 * 静态资源地址 server_list.httpResURL
	 * @return  the value of server_list.httpResURL
	 *
	 */
	public String getHttpresurl() {
		return httpresurl;
	}

	/**
	 * 静态资源地址 server_list.httpResURL
	 * @param httpresurl  the value for server_list.httpResURL
	 *
	 */
	public void setHttpresurl(String httpresurl) {
		this.httpresurl = httpresurl;
	}

	/**
	 * 充值接口 server_list.recharge
	 * @return  the value of server_list.recharge
	 *
	 */
	public String getRecharge() {
		return recharge;
	}

	/**
	 * 充值接口 server_list.recharge
	 * @param recharge  the value for server_list.recharge
	 *
	 */
	public void setRecharge(String recharge) {
		this.recharge = recharge;
	}

	/**
	 * server_list.flashver
	 * @return  the value of server_list.flashver
	 *
	 */
	public String getFlashver() {
		return flashver;
	}

	/**
	 * server_list.flashver
	 * @param flashver  the value for server_list.flashver
	 *
	 */
	public void setFlashver(String flashver) {
		this.flashver = flashver;
	}

	/**
	 * server_list.homepage
	 * @return  the value of server_list.homepage
	 *
	 */
	public String getHomepage() {
		return homepage;
	}

	/**
	 * server_list.homepage
	 * @param homepage  the value for server_list.homepage
	 *
	 */
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	/**
	 * server_list.idcardurl
	 * @return  the value of server_list.idcardurl
	 *
	 */
	public String getIdcardurl() {
		return idcardurl;
	}

	/**
	 * server_list.idcardurl
	 * @param idcardurl  the value for server_list.idcardurl
	 *
	 */
	public void setIdcardurl(String idcardurl) {
		this.idcardurl = idcardurl;
	}
}
