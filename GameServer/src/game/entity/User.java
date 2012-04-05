package game.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  用户信息
 * @Title  webGame
 * @Description User.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
public class User implements Serializable{

	//用户ID
	private Long id;
	
	//用户名
	private String userName;
	
	//密码
	private String password;
	
	//邮箱
	private String email;
	
	//帐户余额
	private Integer accountBalance;
	
	//创建日期
	private Date createDate;
	
	//登录次数
	private Integer loginCount;
	
	//帐号状态0正常
	private Integer flag;
	
	//用户IP
	private String loginIp;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(Integer accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
}
