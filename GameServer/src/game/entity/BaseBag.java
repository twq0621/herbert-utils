package game.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  背包基本属性
 * @Title  webGame
 * @Description BaseBag.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
public class BaseBag implements Serializable{

	//ID
	private Long id;
	
	//背包类型
	private Integer bagType;
	
	//角色ID
	private Long roleId;
	
	//背包最大容量
	private Integer max;
	
	//背包所有物品ID以逗号相隔
	private String items;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getBagType() {
		return this.bagType;
	}

	public void setBagType(Integer bagType) {
		this.bagType = bagType;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Integer getMax() {
		return this.max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
	
	public String getItems() {
		return this.items;
	}

	public void setItems(String items) {
		this.items = items;
	}
	
}
