package game.entity;


import java.util.Date;

import java.io.Serializable;

/**
 *  好友列表信息
 * @Title  webGame
 * @Description RoleFriend.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
public class RoleFriend implements Serializable{

	//ID
	private Integer id;
	
	//角色ID
	private Long roleId;
	
	//好友角色ID
	private String friendIds;
	
	//好友类型,有白名单，黑名单，陌生人，公会好友等
	private Integer friendType;
	
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
	
	public String getFriendIds() {
		return this.friendIds;
	}

	public void setFriendIds(String friendIds) {
		this.friendIds = friendIds;
	}
	
	public Integer getFriendType() {
		return this.friendType;
	}

	public void setFriendType(Integer friendType) {
		this.friendType = friendType;
	}
	
}
