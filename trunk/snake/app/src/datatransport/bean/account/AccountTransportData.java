package datatransport.bean.account;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class AccountTransportData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.id == null ? Integer.MIN_VALUE : this.id);
		out.writeInt(this.accountInitiallyId == null ? Integer.MIN_VALUE : this.accountInitiallyId);
		out.writeUTF(this.yunyingId == null ? "NaN" : this.yunyingId);
		out.writeUTF(this.loginname == null ? "NaN" : this.loginname);
		out.writeUTF(this.password == null ? "NaN" : this.password);
		out.writeByte(this.status);
		out.writeObject(this.createDate);
		out.writeUTF(this.createIp == null ? "NaN" : this.createIp);
		out.writeByte(this.isLimit);
		out.writeUTF(this.lastloginIp == null ? "NaN" : this.lastloginIp);
		out.writeObject(this.lastloginDate);
		out.writeInt(this.loginTime == null ? Integer.MIN_VALUE : this.loginTime);
		out.writeUTF(this.server == null ? "NaN" : this.server);
		out.writeInt(this.gm == null ? Integer.MIN_VALUE : this.gm);
		out.writeInt(this.yuanbao == null ? Integer.MIN_VALUE : this.yuanbao);
		out.writeInt(this.consumeYuanbao == null ? Integer.MIN_VALUE : this.consumeYuanbao);
		out.writeInt(this.rechargeYuanbao == null ? Integer.MIN_VALUE : this.rechargeYuanbao);
		out.writeObject(this.lastrechageTime);
		out.writeInt(this.gainYuanbao == null ? Integer.MIN_VALUE : this.gainYuanbao);
		out.writeInt(this.lostYuanbao == null ? Integer.MIN_VALUE : this.lostYuanbao);
		out.writeInt(this.online == null ? Integer.MIN_VALUE : this.online);
		out.writeUTF(this.remarks == null ? "NaN" : this.remarks);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.id = in.readInt();
		this.id = this.id == Integer.MIN_VALUE ? null : this.id;
		this.accountInitiallyId = in.readInt();
		this.accountInitiallyId = this.accountInitiallyId == Integer.MIN_VALUE ? null : this.accountInitiallyId;
		this.yunyingId = in.readUTF();
		this.yunyingId = this.yunyingId.equals("NaN") ? null : this.yunyingId;
		this.loginname = in.readUTF();
		this.loginname = this.loginname.equals("NaN") ? null : this.loginname;
		this.password = in.readUTF();
		this.password = this.password.equals("NaN") ? null : this.password;
		this.status = in.readByte();
		this.createDate = (Date) in.readObject();
		this.createIp = in.readUTF();
		this.createIp = this.createIp.equals("NaN") ? null : this.createIp;
		this.isLimit = in.readByte();
		this.lastloginIp = in.readUTF();
		this.lastloginIp = this.lastloginIp.equals("NaN") ? null : this.lastloginIp;
		this.lastloginDate = (Date) in.readObject();
		this.loginTime = in.readInt();
		this.loginTime = this.loginTime == Integer.MIN_VALUE ? null : this.loginTime;
		this.server = in.readUTF();
		this.server = this.server.equals("NaN") ? null : this.server;
		this.gm = in.readInt();
		this.gm = this.gm == Integer.MIN_VALUE ? null : this.gm;
		this.yuanbao = in.readInt();
		this.yuanbao = this.yuanbao == Integer.MIN_VALUE ? null : this.yuanbao;
		this.consumeYuanbao = in.readInt();
		this.consumeYuanbao = this.consumeYuanbao == Integer.MIN_VALUE ? null : this.consumeYuanbao;
		this.rechargeYuanbao = in.readInt();
		this.rechargeYuanbao = this.rechargeYuanbao == Integer.MIN_VALUE ? null : this.rechargeYuanbao;
		this.lastrechageTime = (Date) in.readObject();
		this.gainYuanbao = in.readInt();
		this.gainYuanbao = this.gainYuanbao == Integer.MIN_VALUE ? null : this.gainYuanbao;
		this.lostYuanbao = in.readInt();
		this.lostYuanbao = this.lostYuanbao == Integer.MIN_VALUE ? null : this.lostYuanbao;
		this.online = in.readInt();
		this.online = this.online == Integer.MIN_VALUE ? null : this.online;
		this.remarks = in.readUTF();
		this.remarks = this.remarks.equals("NaN") ? null : this.remarks;
	}

	/**
	 * 主键 游戏帐号 t_account.f_id
	 * 
	 */
	private Integer id;

	/**
	 * 用户的原始ID t_account.f_account_initially_id
	 * 
	 */
	private Integer accountInitiallyId;

	/**
	 * 运营帐号id t_account.f_yunying_id
	 * 
	 */
	private String yunyingId;

	/**
	 * 登录账号 t_account.f_loginname
	 * 
	 */
	private String loginname;

	/**
	 * 登录密码 t_account.f_password
	 * 
	 */
	private String password;

	/**
	 * 状态 1可用 0不可用 t_account.f_status
	 * 
	 */
	private Byte status;

	/**
	 * 创建日期 t_account.f_create_date
	 * 
	 */
	private Date createDate;

	/**
	 * 创建ip t_account.f_create_ip
	 * 
	 */
	private String createIp;

	/**
	 * 是否受防沉迷系统限制，1限制，0不限制 t_account.f_is_limit
	 * 
	 */
	private Byte isLimit;

	/**
	 * 上次登录ip t_account.f_lastlogin_ip
	 * 
	 */
	private String lastloginIp;

	/**
	 * 上次登录时间 t_account.f_lastlogin_date
	 * 
	 */
	private Date lastloginDate;

	/**
	 * 登陆次数 t_account.f_login_time
	 * 
	 */
	private Integer loginTime;

	/**
	 * 上一次登陆的游戏服务器ID t_account.f_server
	 * 
	 */
	private String server;

	/**
	 * 是否是gm用户 1是/0不是 t_account.f_gm
	 * 
	 */
	private Integer gm;

	/**
	 * 当前元宝数量 t_account.f_yuanbao
	 * 
	 */
	private Integer yuanbao;

	/**
	 * 总计从游戏消费元宝数（不计录玩家间的交易) t_account.f_consume_yuanbao
	 * 
	 */
	private Integer consumeYuanbao;

	/**
	 * 总计充值元宝数量 t_account.f_recharge_yuanbao
	 * 
	 */
	private Integer rechargeYuanbao;

	/**
	 * 最后一次充值时间 t_account.f_lastrechage_time
	 * 
	 */
	private Date lastrechageTime;

	/**
	 * 从其他玩家身上赚到的元宝数 t_account.f_gain_yuanbao
	 * 
	 */
	private Integer gainYuanbao;

	/**
	 * 购买其他玩家的物品失去的元宝数 t_account.f_lost_yuanbao
	 * 
	 */
	private Integer lostYuanbao;

	/**
	 * 0　不在线 1在线 t_account.f_online
	 * 
	 */
	private Integer online;

	/**
	 * 封号原因 t_account.f_remarks
	 * 
	 */
	private String remarks;

	/**
	 * 主键 游戏帐号 t_account.f_id
	 * 
	 * @return the value of t_account.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键 游戏帐号 t_account.f_id
	 * 
	 * @param id
	 *            the value for t_account.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 用户的原始ID t_account.f_account_initially_id
	 * 
	 * @return the value of t_account.f_account_initially_id
	 * 
	 */
	public Integer getAccountInitiallyId() {
		return accountInitiallyId;
	}

	/**
	 * 用户的原始ID t_account.f_account_initially_id
	 * 
	 * @param accountInitiallyId
	 *            the value for t_account.f_account_initially_id
	 * 
	 */
	public void setAccountInitiallyId(Integer accountInitiallyId) {
		this.accountInitiallyId = accountInitiallyId;
	}

	/**
	 * 运营帐号id t_account.f_yunying_id
	 * 
	 * @return the value of t_account.f_yunying_id
	 * 
	 */
	public String getYunyingId() {
		return yunyingId;
	}

	/**
	 * 运营帐号id t_account.f_yunying_id
	 * 
	 * @param yunyingId
	 *            the value for t_account.f_yunying_id
	 * 
	 */
	public void setYunyingId(String yunyingId) {
		this.yunyingId = yunyingId;
	}

	/**
	 * 登录账号 t_account.f_loginname
	 * 
	 * @return the value of t_account.f_loginname
	 * 
	 */
	public String getLoginname() {
		return loginname;
	}

	/**
	 * 登录账号 t_account.f_loginname
	 * 
	 * @param loginname
	 *            the value for t_account.f_loginname
	 * 
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	/**
	 * 登录密码 t_account.f_password
	 * 
	 * @return the value of t_account.f_password
	 * 
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 登录密码 t_account.f_password
	 * 
	 * @param password
	 *            the value for t_account.f_password
	 * 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 状态 1可用 0不可用 t_account.f_status
	 * 
	 * @return the value of t_account.f_status
	 * 
	 */
	public Byte getStatus() {
		return status;
	}

	/**
	 * 状态 1可用 0不可用 t_account.f_status
	 * 
	 * @param status
	 *            the value for t_account.f_status
	 * 
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}

	/**
	 * 创建日期 t_account.f_create_date
	 * 
	 * @return the value of t_account.f_create_date
	 * 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 创建日期 t_account.f_create_date
	 * 
	 * @param createDate
	 *            the value for t_account.f_create_date
	 * 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 创建ip t_account.f_create_ip
	 * 
	 * @return the value of t_account.f_create_ip
	 * 
	 */
	public String getCreateIp() {
		return createIp;
	}

	/**
	 * 创建ip t_account.f_create_ip
	 * 
	 * @param createIp
	 *            the value for t_account.f_create_ip
	 * 
	 */
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	/**
	 * 是否受防沉迷系统限制，1限制，0不限制 t_account.f_is_limit
	 * 
	 * @return the value of t_account.f_is_limit
	 * 
	 */
	public Byte getIsLimit() {
		return isLimit;
	}

	/**
	 * 是否受防沉迷系统限制，1限制，0不限制 t_account.f_is_limit
	 * 
	 * @param isLimit
	 *            the value for t_account.f_is_limit
	 * 
	 */
	public void setIsLimit(Byte isLimit) {
		this.isLimit = isLimit;
	}

	/**
	 * 上次登录ip t_account.f_lastlogin_ip
	 * 
	 * @return the value of t_account.f_lastlogin_ip
	 * 
	 */
	public String getLastloginIp() {
		return lastloginIp;
	}

	/**
	 * 上次登录ip t_account.f_lastlogin_ip
	 * 
	 * @param lastloginIp
	 *            the value for t_account.f_lastlogin_ip
	 * 
	 */
	public void setLastloginIp(String lastloginIp) {
		this.lastloginIp = lastloginIp;
	}

	/**
	 * 上次登录时间 t_account.f_lastlogin_date
	 * 
	 * @return the value of t_account.f_lastlogin_date
	 * 
	 */
	public Date getLastloginDate() {
		return lastloginDate;
	}

	/**
	 * 上次登录时间 t_account.f_lastlogin_date
	 * 
	 * @param lastloginDate
	 *            the value for t_account.f_lastlogin_date
	 * 
	 */
	public void setLastloginDate(Date lastloginDate) {
		this.lastloginDate = lastloginDate;
	}

	/**
	 * 登陆次数 t_account.f_login_time
	 * 
	 * @return the value of t_account.f_login_time
	 * 
	 */
	public Integer getLoginTime() {
		return loginTime;
	}

	/**
	 * 登陆次数 t_account.f_login_time
	 * 
	 * @param loginTime
	 *            the value for t_account.f_login_time
	 * 
	 */
	public void setLoginTime(Integer loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * 上一次登陆的游戏服务器ID t_account.f_server
	 * 
	 * @return the value of t_account.f_server
	 * 
	 */
	public String getServer() {
		return server;
	}

	/**
	 * 上一次登陆的游戏服务器ID t_account.f_server
	 * 
	 * @param server
	 *            the value for t_account.f_server
	 * 
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * 是否是gm用户 1是/0不是 t_account.f_gm
	 * 
	 * @return the value of t_account.f_gm
	 * 
	 */
	public Integer getGm() {
		return gm;
	}

	/**
	 * 是否是gm用户 1是/0不是 t_account.f_gm
	 * 
	 * @param gm
	 *            the value for t_account.f_gm
	 * 
	 */
	public void setGm(Integer gm) {
		this.gm = gm;
	}

	/**
	 * 当前元宝数量 t_account.f_yuanbao
	 * 
	 * @return the value of t_account.f_yuanbao
	 * 
	 */
	public Integer getYuanbao() {
		return yuanbao;
	}

	/**
	 * 当前元宝数量 t_account.f_yuanbao
	 * 
	 * @param yuanbao
	 *            the value for t_account.f_yuanbao
	 * 
	 */
	public void setYuanbao(Integer yuanbao) {
		this.yuanbao = yuanbao;
	}

	/**
	 * 总计从游戏消费元宝数（不计录玩家间的交易) t_account.f_consume_yuanbao
	 * 
	 * @return the value of t_account.f_consume_yuanbao
	 * 
	 */
	public Integer getConsumeYuanbao() {
		return consumeYuanbao;
	}

	/**
	 * 总计从游戏消费元宝数（不计录玩家间的交易) t_account.f_consume_yuanbao
	 * 
	 * @param consumeYuanbao
	 *            the value for t_account.f_consume_yuanbao
	 * 
	 */
	public void setConsumeYuanbao(Integer consumeYuanbao) {
		this.consumeYuanbao = consumeYuanbao;
	}

	/**
	 * 总计充值元宝数量 t_account.f_recharge_yuanbao
	 * 
	 * @return the value of t_account.f_recharge_yuanbao
	 * 
	 */
	public Integer getRechargeYuanbao() {
		return rechargeYuanbao;
	}

	/**
	 * 总计充值元宝数量 t_account.f_recharge_yuanbao
	 * 
	 * @param rechargeYuanbao
	 *            the value for t_account.f_recharge_yuanbao
	 * 
	 */
	public void setRechargeYuanbao(Integer rechargeYuanbao) {
		this.rechargeYuanbao = rechargeYuanbao;
	}

	/**
	 * 最后一次充值时间 t_account.f_lastrechage_time
	 * 
	 * @return the value of t_account.f_lastrechage_time
	 * 
	 */
	public Date getLastrechageTime() {
		return lastrechageTime;
	}

	/**
	 * 最后一次充值时间 t_account.f_lastrechage_time
	 * 
	 * @param lastrechageTime
	 *            the value for t_account.f_lastrechage_time
	 * 
	 */
	public void setLastrechageTime(Date lastrechageTime) {
		this.lastrechageTime = lastrechageTime;
	}

	/**
	 * 从其他玩家身上赚到的元宝数 t_account.f_gain_yuanbao
	 * 
	 * @return the value of t_account.f_gain_yuanbao
	 * 
	 */
	public Integer getGainYuanbao() {
		return gainYuanbao;
	}

	/**
	 * 从其他玩家身上赚到的元宝数 t_account.f_gain_yuanbao
	 * 
	 * @param gainYuanbao
	 *            the value for t_account.f_gain_yuanbao
	 * 
	 */
	public void setGainYuanbao(Integer gainYuanbao) {
		this.gainYuanbao = gainYuanbao;
	}

	/**
	 * 购买其他玩家的物品失去的元宝数 t_account.f_lost_yuanbao
	 * 
	 * @return the value of t_account.f_lost_yuanbao
	 * 
	 */
	public Integer getLostYuanbao() {
		return lostYuanbao;
	}

	/**
	 * 购买其他玩家的物品失去的元宝数 t_account.f_lost_yuanbao
	 * 
	 * @param lostYuanbao
	 *            the value for t_account.f_lost_yuanbao
	 * 
	 */
	public void setLostYuanbao(Integer lostYuanbao) {
		this.lostYuanbao = lostYuanbao;
	}

	/**
	 * 0　不在线 1在线 t_account.f_online
	 * 
	 * @return the value of t_account.f_online
	 * 
	 */
	public Integer getOnline() {
		return online;
	}

	/**
	 * 0　不在线 1在线 t_account.f_online
	 * 
	 * @param online
	 *            the value for t_account.f_online
	 * 
	 */
	public void setOnline(Integer online) {
		this.online = online;
	}

	/**
	 * 封号原因 t_account.f_remarks
	 * 
	 * @return the value of t_account.f_remarks
	 * 
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 封号原因 t_account.f_remarks
	 * 
	 * @param remarks
	 *            the value for t_account.f_remarks
	 * 
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
