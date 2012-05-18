package game.logic.role;

import lion.ibatis.BaseDaoImpl;

import org.springframework.stereotype.Repository;
import game.entity.Role;

/**
 *  角色信息
 * @Title  webGame
 * @Description IRoleDaoImpl.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Repository
public class RoleDao extends BaseDaoImpl<Role> {

	@Override
	public Class<Role> getEntityClass() {
		return Role.class;
	}

}
