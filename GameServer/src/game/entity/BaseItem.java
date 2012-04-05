package game.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  物品基础信息表
 * @Title  webGame
 * @Description BaseItem.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
public class BaseItem implements Serializable{

	//${fieldMap.fieldComment}
	private Integer id;
	
	//类型
	private String type;
	
	//物品名称
	private String name;
	
	//使用最低级别
	private Integer level;
	
	//图标
	private Integer icon;
	
	//品质
	private Integer quality;
	
	//职业限制
	private Integer profession;
	
	//有效期
	private Date validTime;
	
	//价格
	private Integer price;
	
	//颜色ID
	private Integer colorId;
	
	//物品总数
	private Integer count;
	
	//堆叠上限
	private Integer max;
	
	//物品吐槽
	private String remark;
	
	//是否绑定
	private Integer isBingding;
	
	//物品效果
	private String about;
	
	//力量
	private Integer strong;
	
	//智力
	private Integer inte;
	
	//体力
	private Integer phy;
	
	//精神
	private Integer spi;
	
	//物理攻击
	private Integer att;
	
	//魔法攻击
	private Integer mat;
	
	//物理防御
	private Integer def;
	
	//魔法防御
	private Integer mef;
	
	//maxHp
	private Integer maxHp;
	
	//maxMp
	private Integer maxMp;
	
	//移动速度
	private Integer moveSpeed;
	
	//命中率
	private Integer hit;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public Integer getIcon() {
		return this.icon;
	}

	public void setIcon(Integer icon) {
		this.icon = icon;
	}
	
	public Integer getQuality() {
		return this.quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}
	
	public Integer getProfession() {
		return this.profession;
	}

	public void setProfession(Integer profession) {
		this.profession = profession;
	}
	
	public Date getValidTime() {
		return this.validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
	
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Integer getColorId() {
		return this.colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}
	
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getMax() {
		return this.max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
	
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getIsBingding() {
		return this.isBingding;
	}

	public void setIsBingding(Integer isBingding) {
		this.isBingding = isBingding;
	}
	
	public String getAbout() {
		return this.about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	
	public Integer getStrong() {
		return this.strong;
	}

	public void setStrong(Integer strong) {
		this.strong = strong;
	}
	
	public Integer getInte() {
		return this.inte;
	}

	public void setInte(Integer inte) {
		this.inte = inte;
	}
	
	public Integer getPhy() {
		return this.phy;
	}

	public void setPhy(Integer phy) {
		this.phy = phy;
	}
	
	public Integer getSpi() {
		return this.spi;
	}

	public void setSpi(Integer spi) {
		this.spi = spi;
	}
	
	public Integer getAtt() {
		return this.att;
	}

	public void setAtt(Integer att) {
		this.att = att;
	}
	
	public Integer getMat() {
		return this.mat;
	}

	public void setMat(Integer mat) {
		this.mat = mat;
	}
	
	public Integer getDef() {
		return this.def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}
	
	public Integer getMef() {
		return this.mef;
	}

	public void setMef(Integer mef) {
		this.mef = mef;
	}
	
	public Integer getMaxHp() {
		return this.maxHp;
	}

	public void setMaxHp(Integer maxHp) {
		this.maxHp = maxHp;
	}
	
	public Integer getMaxMp() {
		return this.maxMp;
	}

	public void setMaxMp(Integer maxMp) {
		this.maxMp = maxMp;
	}
	
	public Integer getMoveSpeed() {
		return this.moveSpeed;
	}

	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	public Integer getHit() {
		return this.hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}
	
}
