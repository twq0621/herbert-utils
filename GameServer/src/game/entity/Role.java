package game.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  角色信息
 * @Title  webGame
 * @Description Role.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
public class Role implements Serializable{

	//ID
	private Long id;
	
	//用户ID
	private Long userId;
	
	//角色名称
	private String roleName;
	
	//职业类型ID
	private Integer careerId;
	
	//性别
	private Integer sex;
	
	//等级
	private Integer level;
	
	//经验
	private Integer exp;
	
	//新手任务ID
	private Integer rookieId;
	
	//帮会ID
	private Integer guildId;
	
	//金币
	private Integer gold;
	
	//绑定RMB
	private Integer bindRmb;
	
	//场景ID
	private Integer sceneId;
	
	//生命值HP
	private Integer hp;
	
	//魔法值MP
	private Integer mp;
	
	//智慧
	private Integer wisdom;
	
	//敏捷
	private Integer dexterity;
	
	//力量
	private Integer strength;
	
	//坐标X
	private Integer x;
	
	//坐标Y
	private Integer y;
	
	//物理防御
	private Integer physicalDefense;
	
	//物理伤害
	private Integer physicalDamage;
	
	//法术防御
	private Integer magicDefense;
	
	//法术伤害
	private Integer magicDamage;
	
	//在线时间
	private Long onlineTime;
	
	//创建日期
	private Date createDate;
	
	//PK值
	private Integer pkValue;
	
	//最后退出时间
	private Date lastLogoutDate;
	
	//GM级别
	private Integer gmGrade;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Integer getCareerId() {
		return this.careerId;
	}

	public void setCareerId(Integer careerId) {
		this.careerId = careerId;
	}
	
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getExp() {
		return this.exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}
	
	public Integer getRookieId() {
		return this.rookieId;
	}

	public void setRookieId(Integer rookieId) {
		this.rookieId = rookieId;
	}
	
	public Integer getGuildId() {
		return this.guildId;
	}

	public void setGuildId(Integer guildId) {
		this.guildId = guildId;
	}
	
	public Integer getGold() {
		return this.gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}
	
	public Integer getBindRmb() {
		return this.bindRmb;
	}

	public void setBindRmb(Integer bindRmb) {
		this.bindRmb = bindRmb;
	}
	
	public Integer getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}
	
	public Integer getHp() {
		return this.hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}
	
	public Integer getMp() {
		return this.mp;
	}

	public void setMp(Integer mp) {
		this.mp = mp;
	}
	
	public Integer getWisdom() {
		return this.wisdom;
	}

	public void setWisdom(Integer wisdom) {
		this.wisdom = wisdom;
	}
	
	public Integer getDexterity() {
		return this.dexterity;
	}

	public void setDexterity(Integer dexterity) {
		this.dexterity = dexterity;
	}
	
	public Integer getStrength() {
		return this.strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}
	
	public Integer getX() {
		return this.x;
	}

	public void setX(Integer x) {
		this.x = x;
	}
	
	public Integer getY() {
		return this.y;
	}

	public void setY(Integer y) {
		this.y = y;
	}
	
	public Integer getPhysicalDefense() {
		return this.physicalDefense;
	}

	public void setPhysicalDefense(Integer physicalDefense) {
		this.physicalDefense = physicalDefense;
	}
	
	public Integer getPhysicalDamage() {
		return this.physicalDamage;
	}

	public void setPhysicalDamage(Integer physicalDamage) {
		this.physicalDamage = physicalDamage;
	}
	
	public Integer getMagicDefense() {
		return this.magicDefense;
	}

	public void setMagicDefense(Integer magicDefense) {
		this.magicDefense = magicDefense;
	}
	
	public Integer getMagicDamage() {
		return this.magicDamage;
	}

	public void setMagicDamage(Integer magicDamage) {
		this.magicDamage = magicDamage;
	}
	
	public Long getOnlineTime() {
		return this.onlineTime;
	}

	public void setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Integer getPkValue() {
		return this.pkValue;
	}

	public void setPkValue(Integer pkValue) {
		this.pkValue = pkValue;
	}
	
	public Date getLastLogoutDate() {
		return this.lastLogoutDate;
	}

	public void setLastLogoutDate(Date lastLogoutDate) {
		this.lastLogoutDate = lastLogoutDate;
	}
	
	public Integer getGmGrade() {
		return this.gmGrade;
	}

	public void setGmGrade(Integer gmGrade) {
		this.gmGrade = gmGrade;
	}
	
}
