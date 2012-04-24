package net.snake.gamemodel.account.bean;

import java.util.Date;

import net.snake.gamemodel.account.persistence.AccountMonneyManager;
import net.snake.ibatis.IbatisEntity;

/**
 * 账号
 * 
 * @author serv_dev
 */

public class Account implements IbatisEntity {

	/**
	 * 主键 游戏帐号 t_account.f_id
	 */
	private Integer id;
	/**
	 * 用户的原始ID t_account.f_account_initially_id
	 */
	private Integer accountInitiallyId;
	/**
	 * 运营帐号id t_account.f_yunying_id
	 */
	private String yunyingId;
	/**
	 * 登录账号 t_account.f_loginname
	 */
	private String loginname;
	/**
	 * 登录密码 t_account.f_password
	 */
	private String password;
	/**
	 * 状态 1可用 0不可用 t_account.f_status
	 */
	private Byte status;
	/**
	 * 创建日期 t_account.f_create_date
	 */
	private Date createDate;
	/**
	 * 创建ip t_account.f_create_ip
	 */
	private String createIp;
	/**
	 * 是否受防沉迷系统限制，1限制，0不限制 t_account.f_is_limit
	 */
	private Byte isLimit;
	/**
	 * 上次登录ip t_account.f_lastlogin_ip
	 */
	private String lastloginIp;
	/**
	 * 上次登录时间 t_account.f_lastlogin_date
	 */
	private Date lastloginDate;
	/**
	 * 登陆次数 t_account.f_login_time
	 */
	private Integer loginTime;
	/**
	 * 上一次登陆的游戏服务器ID t_account.f_server
	 */
	private String server;
	/**
	 * 是否是gm用户 1是/0不是 t_account.f_gm
	 */
	private Integer gm;
	/**
	 * 当前元宝数量 t_account.f_yuanbao
	 */
	private Integer yuanbao;
	/**
	 * 总计从游戏消费元宝数（不计录玩家间的交易) t_account.f_consume_yuanbao
	 */
	private Integer consumeYuanbao;
	/**
	 * 总计充值元宝数量 t_account.f_recharge_yuanbao
	 */
	private Integer rechargeYuanbao;
	/**
	 * 最后一次充值时间 t_account.f_lastrechage_time
	 */
	private Date lastrechageTime;
	/**
	 * 从其他玩家身上赚到的元宝数 t_account.f_gain_yuanbao
	 */
	private Integer gainYuanbao;
	/**
	 * 购买其他玩家的物品失去的元宝数 t_account.f_lost_yuanbao
	 */
	private Integer lostYuanbao;
	/**
	 * 0　不在线 1在线 t_account.f_online
	 */
	private Integer online;
	/**
	 * 封号原因 t_account.f_remarks
	 */
	private String remarks;

	/**
	 * 主键 游戏帐号 t_account.f_id
	 * 
	 * @return the value of t_account.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键 游戏帐号 t_account.f_id
	 * 
	 * @param id
	 *            the value for t_account.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 用户的原始ID t_account.f_account_initially_id
	 * 
	 * @return the value of t_account.f_account_initially_id
	 */
	public Integer getAccountInitiallyId() {
		return accountInitiallyId;
	}

	/**
	 * 用户的原始ID t_account.f_account_initially_id
	 * 
	 * @param accountInitiallyId
	 *            the value for t_account.f_account_initially_id
	 */
	public void setAccountInitiallyId(Integer accountInitiallyId) {
		this.accountInitiallyId = accountInitiallyId;
	}

	/**
	 * 运营帐号id t_account.f_yunying_id
	 * 
	 * @return the value of t_account.f_yunying_id
	 */
	public String getYunyingId() {
		return yunyingId;
	}

	/**
	 * 运营帐号id t_account.f_yunying_id
	 * 
	 * @param yunyingId
	 *            the value for t_account.f_yunying_id
	 */
	public void setYunyingId(String yunyingId) {
		this.yunyingId = yunyingId;
	}

	/**
	 * 登录账号 t_account.f_loginname
	 * 
	 * @return the value of t_account.f_loginname
	 */
	public String getLoginname() {
		return loginname;
	}

	/**
	 * 登录账号 t_account.f_loginname
	 * 
	 * @param loginname
	 *            the value for t_account.f_loginname
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	/**
	 * 登录密码 t_account.f_password
	 * 
	 * @return the value of t_account.f_password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 登录密码 t_account.f_password
	 * 
	 * @param password
	 *            the value for t_account.f_password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 状态 1可用 0不可用 t_account.f_status
	 * 
	 * @return the value of t_account.f_status
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 状态 1可用 0不可用 t_account.f_status
	 * 
	 * @param status
	 *            the value for t_account.f_status
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * 创建日期 t_account.f_create_date
	 * 
	 * @return the value of t_account.f_create_date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 创建日期 t_account.f_create_date
	 * 
	 * @param createDate
	 *            the value for t_account.f_create_date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 创建ip t_account.f_create_ip
	 * 
	 * @return the value of t_account.f_create_ip
	 */
	public String getCreateIp() {
		return createIp;
	}

	/**
	 * 创建ip t_account.f_create_ip
	 * 
	 * @param createIp
	 *            the value for t_account.f_create_ip
	 */
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	/**
	 * 是否受防沉迷系统限制，1限制，0不限制 t_account.f_is_limit
	 * 
	 * @return the value of t_account.f_is_limit
	 */
	public Byte getIsLimit() {
		return isLimit;
	}

	/**
	 * 是否受防沉迷系统限制，1限制，0不限制 t_account.f_is_limit
	 * 
	 * @param isLimit
	 *            the value for t_account.f_is_limit
	 */
	public void setIsLimit(Byte isLimit) {
		this.isLimit = isLimit;
	}

	/**
	 * 上次登录ip t_account.f_lastlogin_ip
	 * 
	 * @return the value of t_account.f_lastlogin_ip
	 */
	public String getLastloginIp() {
		return lastloginIp;
	}

	/**
	 * 上次登录ip t_account.f_lastlogin_ip
	 * 
	 * @param lastloginIp
	 *            the value for t_account.f_lastlogin_ip
	 */
	public void setLastloginIp(String lastloginIp) {
		this.lastloginIp = lastloginIp;
	}

	/**
	 * 上次登录时间 t_account.f_lastlogin_date
	 * 
	 * @return the value of t_account.f_lastlogin_date
	 */
	public Date getLastloginDate() {
		return lastloginDate;
	}

	/**
	 * 上次登录时间 t_account.f_lastlogin_date
	 * 
	 * @param lastloginDate
	 *            the value for t_account.f_lastlogin_date
	 */
	public void setLastloginDate(Date lastloginDate) {
		this.lastloginDate = lastloginDate;
	}

	/**
	 * 登陆次数 t_account.f_login_time
	 * 
	 * @return the value of t_account.f_login_time
	 */
	public Integer getLoginTime() {
		return loginTime;
	}

	/**
	 * 登陆次数 t_account.f_login_time
	 * 
	 * @param loginTime
	 *            the value for t_account.f_login_time
	 */
	public void setLoginTime(Integer loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * 上一次登陆的游戏服务器ID t_account.f_server
	 * 
	 * @return the value of t_account.f_server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * 上一次登陆的游戏服务器ID t_account.f_server
	 * 
	 * @param server
	 *            the value for t_account.f_server
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * 是否是gm用户 1是/0不是 t_account.f_gm
	 * 
	 * @return the value of t_account.f_gm
	 */
	public Integer getGm() {
		return gm;
	}

	/**
	 * 是否是gm用户 1是/0不是 t_account.f_gm
	 * 
	 * @param gm
	 *            the value for t_account.f_gm
	 */
	public void setGm(Integer gm) {
		this.gm = gm;
	}

	/**
	 * 当前元宝数量 t_account.f_yuanbao
	 * 
	 * @return the value of t_account.f_yuanbao
	 */
	public Integer getYuanbao() {
		return yuanbao;
	}

	/**
	 * 当前元宝数量 t_account.f_yuanbao
	 * 
	 * @param yuanbao
	 *            the value for t_account.f_yuanbao
	 */
	public void setYuanbao(Integer yuanbao) {
		this.yuanbao = yuanbao;
	}

	/**
	 * 总计从游戏消费元宝数（不计录玩家间的交易) t_account.f_consume_yuanbao
	 * 
	 * @return the value of t_account.f_consume_yuanbao
	 */
	public Integer getConsumeYuanbao() {
		return consumeYuanbao;
	}

	/**
	 * 总计从游戏消费元宝数（不计录玩家间的交易) t_account.f_consume_yuanbao
	 * 
	 * @param consumeYuanbao
	 *            the value for t_account.f_consume_yuanbao
	 */
	public void setConsumeYuanbao(Integer consumeYuanbao) {
		this.consumeYuanbao = consumeYuanbao;
	}

	/**
	 * 总计充值元宝数量 t_account.f_recharge_yuanbao
	 * 
	 * @return the value of t_account.f_recharge_yuanbao
	 */
	public Integer getRechargeYuanbao() {
		return rechargeYuanbao;
	}

	/**
	 * 总计充值元宝数量 t_account.f_recharge_yuanbao
	 * 
	 * @param rechargeYuanbao
	 *            the value for t_account.f_recharge_yuanbao
	 */
	public void setRechargeYuanbao(Integer rechargeYuanbao) {
		this.rechargeYuanbao = rechargeYuanbao;
	}

	/**
	 * 最后一次充值时间 t_account.f_lastrechage_time
	 * 
	 * @return the value of t_account.f_lastrechage_time
	 */
	public Date getLastrechageTime() {
		return lastrechageTime;
	}

	/**
	 * 最后一次充值时间 t_account.f_lastrechage_time
	 * 
	 * @param lastrechageTime
	 *            the value for t_account.f_lastrechage_time
	 */
	public void setLastrechageTime(Date lastrechageTime) {
		this.lastrechageTime = lastrechageTime;
	}

	/**
	 * 从其他玩家身上赚到的元宝数 t_account.f_gain_yuanbao
	 * 
	 * @return the value of t_account.f_gain_yuanbao
	 */
	public Integer getGainYuanbao() {
		return gainYuanbao;
	}

	/**
	 * 从其他玩家身上赚到的元宝数 t_account.f_gain_yuanbao
	 * 
	 * @param gainYuanbao
	 *            the value for t_account.f_gain_yuanbao
	 */
	public void setGainYuanbao(Integer gainYuanbao) {
		this.gainYuanbao = gainYuanbao;
	}

	/**
	 * 购买其他玩家的物品失去的元宝数 t_account.f_lost_yuanbao
	 * 
	 * @return the value of t_account.f_lost_yuanbao
	 */
	public Integer getLostYuanbao() {
		return lostYuanbao;
	}

	/**
	 * 购买其他玩家的物品失去的元宝数 t_account.f_lost_yuanbao
	 * 
	 * @param lostYuanbao
	 *            the value for t_account.f_lost_yuanbao
	 */
	public void setLostYuanbao(Integer lostYuanbao) {
		this.lostYuanbao = lostYuanbao;
	}

	/**
	 * 0　不在线 1在线 t_account.f_online
	 * 
	 * @return the value of t_account.f_online
	 */
	public Integer getOnline() {
		return online;
	}

	/**
	 * 0不在线， 1在线 t_account.f_online
	 * 
	 * @param online
	 *            the value for t_account.f_online
	 */
	public void setOnline(Integer online) {
		this.online = online;
	}

	/**
	 * 封号原因 t_account.f_remarks
	 * 
	 * @return the value of t_account.f_remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 封号原因 t_account.f_remarks
	 * 
	 * @param remarks
	 *            the value for t_account.f_remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	private final AccountMonneyManager AccountMonneyManager = new AccountMonneyManager(this);

	public AccountMonneyManager getAccountMonneyManager() {
		return AccountMonneyManager;
	}

	public boolean isAbleAccount() {
		if (this.status == null || this.status == 1) {
			return true;
		}
		return false;
	}

	public boolean isAccountLimit() {
		if (isLimit == null || isLimit == 1) {
			return true;
		}
		return false;
	}
}
