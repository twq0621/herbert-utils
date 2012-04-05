package game.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  角色快捷键
 * @Title  webGame
 * @Description RoleShortcut.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
public class RoleShortcut implements Serializable{

	//ID
	private Integer id;
	
	//角色ID
	private Long roleId;
	
	//技能ID
	private Integer skillId;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public Integer getSkillId() {
		return this.skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	
}
