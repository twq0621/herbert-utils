package game.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  技能基本信息
 * @Title  webGame
 * @Description BaseSkill.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
public class BaseSkill implements Serializable{

	//ID
	private Integer id;
	
	//技能名称
	private String name;
	
	//等级限制
	private Integer level;
	
	//职业限制
	private Integer profession;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public Integer getProfession() {
		return this.profession;
	}

	public void setProfession(Integer profession) {
		this.profession = profession;
	}
	
}
