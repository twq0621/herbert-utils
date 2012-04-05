package game.dao;

import lion.common.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import game.entity.RoleFriend;

/**
 *  好友列表信息
 * @Title  webGame
 * @Description IRoleFriendDaoImpl.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Repository
public class RoleFriendDao extends BaseDaoImpl<RoleFriend> {

	@Override
	public Class<RoleFriend> getEntityClass() {
		return RoleFriend.class;
	}

}
