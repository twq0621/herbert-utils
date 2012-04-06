package game.logic.user;

import lion.common.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import game.entity.User;

/**
 *  用户信息
 * @Title  webGame
 * @Description IUserDaoImpl.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Repository
public class UserDao extends BaseDaoImpl<User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}

}
