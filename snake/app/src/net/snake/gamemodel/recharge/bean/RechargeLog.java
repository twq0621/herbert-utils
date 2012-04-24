package net.snake.gamemodel.recharge.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class RechargeLog implements IbatisEntity {

	/**
	 * t_recharge_log.f_id
	 */
	private Integer id;
	/**
	 * 服务器ID t_recharge_log.f_sid
	 */
	private Integer sid;
	/**
	 * 运营帐号ID t_recharge_log.f_yunying_id
	 */
	private String yunyingId;
	/**
	 * 对应的帐号ID t_recharge_log.f_account_id
	 */
	private Integer accountId;
	/**
	 * 用户的原始ID t_recharge_log.f_account_initially_id
	 */
	private Integer accountInitiallyId;
	/**
	 * 订单号 t_recharge_log.f_oid
	 */
	private String oid;
	/**
	 * 充值人民币 t_recharge_log.f_rechage_money
	 */
	private Float rechageMoney;
	/**
	 * 充值之前的元宝数 t_recharge_log.f_original_yuanbao
	 */
	private Integer originalYuanbao;
	/**
	 * 本次要充值的元宝数 t_recharge_log.f_rechage_yuanbao
	 */
	private Integer rechageYuanbao;
	/**
	 * 玩家IP t_recharge_log.f_ip
	 */
	private String ip;
	/**
	 * 运营发来的时间戳 t_recharge_log.f_time
	 */
	private Long time;
	/**
	 * 充值时间 t_recharge_log.f_recharge_time
	 */
	private Date rechargeTime;
	/**
	 * 0为人民币充值 t_recharge_log.f_type
	 */
	private Integer type;
	/**
	 * 备注 t_recharge_log.f_remark
	 */
	private String remark;
	/**
	 * 充值时用户角色中最高等级的角色ID t_recharge_log.f_character_id
	 */
	private Integer characterId;
	/**
	 * 原始玩家角色ID t_recharge_log.f_character_initially_id
	 */
	private Integer characterInitiallyId;
	/**
	 * 充值当时角色等级 t_recharge_log.f_character_grade
	 */
	private Short characterGrade;
	/**
	 * 发出充值请求的服务器IP t_recharge_log.f_from_server_ip
	 */
	private String fromServerIp;

	/**
	 * t_recharge_log.f_id
	 * 
	 * @return the value of t_recharge_log.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_recharge_log.f_id
	 * 
	 * @param id
	 *            the value for t_recharge_log.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 服务器ID t_recharge_log.f_sid
	 * 
	 * @return the value of t_recharge_log.f_sid
	 */
	public Integer getSid() {
		return sid;
	}

	/**
	 * 服务器ID t_recharge_log.f_sid
	 * 
	 * @param sid
	 *            the value for t_recharge_log.f_sid
	 */
	public void setSid(Integer sid) {
		this.sid = sid;
	}

	/**
	 * 运营帐号ID t_recharge_log.f_yunying_id
	 * 
	 * @return the value of t_recharge_log.f_yunying_id
	 */
	public String getYunyingId() {
		return yunyingId;
	}

	/**
	 * 运营帐号ID t_recharge_log.f_yunying_id
	 * 
	 * @param yunyingId
	 *            the value for t_recharge_log.f_yunying_id
	 */
	public void setYunyingId(String yunyingId) {
		this.yunyingId = yunyingId;
	}

	/**
	 * 对应的帐号ID t_recharge_log.f_account_id
	 * 
	 * @return the value of t_recharge_log.f_account_id
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * 对应的帐号ID t_recharge_log.f_account_id
	 * 
	 * @param accountId
	 *            the value for t_recharge_log.f_account_id
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * 用户的原始ID t_recharge_log.f_account_initially_id
	 * 
	 * @return the value of t_recharge_log.f_account_initially_id
	 */
	public Integer getAccountInitiallyId() {
		return accountInitiallyId;
	}

	/**
	 * 用户的原始ID t_recharge_log.f_account_initially_id
	 * 
	 * @param accountInitiallyId
	 *            the value for t_recharge_log.f_account_initially_id
	 */
	public void setAccountInitiallyId(Integer accountInitiallyId) {
		this.accountInitiallyId = accountInitiallyId;
	}

	/**
	 * 订单号 t_recharge_log.f_oid
	 * 
	 * @return the value of t_recharge_log.f_oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * 订单号 t_recharge_log.f_oid
	 * 
	 * @param oid
	 *            the value for t_recharge_log.f_oid
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * 充值人民币 t_recharge_log.f_rechage_money
	 * 
	 * @return the value of t_recharge_log.f_rechage_money
	 */
	public Float getRechageMoney() {
		return rechageMoney;
	}

	/**
	 * 充值人民币 t_recharge_log.f_rechage_money
	 * 
	 * @param rechageMoney
	 *            the value for t_recharge_log.f_rechage_money
	 */
	public void setRechageMoney(Float rechageMoney) {
		this.rechageMoney = rechageMoney;
	}

	/**
	 * 充值之前的元宝数 t_recharge_log.f_original_yuanbao
	 * 
	 * @return the value of t_recharge_log.f_original_yuanbao
	 */
	public Integer getOriginalYuanbao() {
		return originalYuanbao;
	}

	/**
	 * 充值之前的元宝数 t_recharge_log.f_original_yuanbao
	 * 
	 * @param originalYuanbao
	 *            the value for t_recharge_log.f_original_yuanbao
	 */
	public void setOriginalYuanbao(Integer originalYuanbao) {
		this.originalYuanbao = originalYuanbao;
	}

	/**
	 * 本次要充值的元宝数 t_recharge_log.f_rechage_yuanbao
	 * 
	 * @return the value of t_recharge_log.f_rechage_yuanbao
	 */
	public Integer getRechageYuanbao() {
		return rechageYuanbao;
	}

	/**
	 * 本次要充值的元宝数 t_recharge_log.f_rechage_yuanbao
	 * 
	 * @param rechageYuanbao
	 *            the value for t_recharge_log.f_rechage_yuanbao
	 */
	public void setRechageYuanbao(Integer rechageYuanbao) {
		this.rechageYuanbao = rechageYuanbao;
	}

	/**
	 * 玩家IP t_recharge_log.f_ip
	 * 
	 * @return the value of t_recharge_log.f_ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 玩家IP t_recharge_log.f_ip
	 * 
	 * @param ip
	 *            the value for t_recharge_log.f_ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 运营发来的时间戳 t_recharge_log.f_time
	 * 
	 * @return the value of t_recharge_log.f_time
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * 运营发来的时间戳 t_recharge_log.f_time
	 * 
	 * @param time
	 *            the value for t_recharge_log.f_time
	 */
	public void setTime(Long time) {
		this.time = time;
	}

	/**
	 * 充值时间 t_recharge_log.f_recharge_time
	 * 
	 * @return the value of t_recharge_log.f_recharge_time
	 */
	public Date getRechargeTime() {
		return rechargeTime;
	}

	/**
	 * 充值时间 t_recharge_log.f_recharge_time
	 * 
	 * @param rechargeTime
	 *            the value for t_recharge_log.f_recharge_time
	 */
	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	/**
	 * 0为人民币充值 t_recharge_log.f_type
	 * 
	 * @return the value of t_recharge_log.f_type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 0为人民币充值 t_recharge_log.f_type
	 * 
	 * @param type
	 *            the value for t_recharge_log.f_type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 备注 t_recharge_log.f_remark
	 * 
	 * @return the value of t_recharge_log.f_remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注 t_recharge_log.f_remark
	 * 
	 * @param remark
	 *            the value for t_recharge_log.f_remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 充值时用户角色中最高等级的角色ID t_recharge_log.f_character_id
	 * 
	 * @return the value of t_recharge_log.f_character_id
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 充值时用户角色中最高等级的角色ID t_recharge_log.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_recharge_log.f_character_id
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 原始玩家角色ID t_recharge_log.f_character_initially_id
	 * 
	 * @return the value of t_recharge_log.f_character_initially_id
	 */
	public Integer getCharacterInitiallyId() {
		return characterInitiallyId;
	}

	/**
	 * 原始玩家角色ID t_recharge_log.f_character_initially_id
	 * 
	 * @param characterInitiallyId
	 *            the value for t_recharge_log.f_character_initially_id
	 */
	public void setCharacterInitiallyId(Integer characterInitiallyId) {
		this.characterInitiallyId = characterInitiallyId;
	}

	/**
	 * 充值当时角色等级 t_recharge_log.f_character_grade
	 * 
	 * @return the value of t_recharge_log.f_character_grade
	 */
	public Short getCharacterGrade() {
		return characterGrade;
	}

	/**
	 * 充值当时角色等级 t_recharge_log.f_character_grade
	 * 
	 * @param characterGrade
	 *            the value for t_recharge_log.f_character_grade
	 */
	public void setCharacterGrade(Short characterGrade) {
		this.characterGrade = characterGrade;
	}

	/**
	 * 发出充值请求的服务器IP t_recharge_log.f_from_server_ip
	 * 
	 * @return the value of t_recharge_log.f_from_server_ip
	 */
	public String getFromServerIp() {
		return fromServerIp;
	}

	/**
	 * 发出充值请求的服务器IP t_recharge_log.f_from_server_ip
	 * 
	 * @param fromServerIp
	 *            the value for t_recharge_log.f_from_server_ip
	 */
	public void setFromServerIp(String fromServerIp) {
		this.fromServerIp = fromServerIp;
	}
}
