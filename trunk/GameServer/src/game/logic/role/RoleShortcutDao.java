package game.logic.role;

import lion.common.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import game.entity.RoleShortcut;

/**
 *  角色快捷键
 * @Title  webGame
 * @Description IRoleShortcutDaoImpl.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Repository
public class RoleShortcutDao extends BaseDaoImpl<RoleShortcut> {

	@Override
	public Class<RoleShortcut> getEntityClass() {
		return RoleShortcut.class;
	}

}
