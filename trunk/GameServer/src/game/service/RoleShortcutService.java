package game.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import lion.common.BaseServiceImpl;
import game.dao.RoleShortcutDao;
import game.entity.RoleShortcut;

/**
 *  角色快捷键
 * @Title  webGame	
 * @Description RoleShortcutService.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Component
@Transactional
public class RoleShortcutService extends BaseServiceImpl {

	private RoleShortcutDao roleShortcutDao;
	
	@Resource
	public void setDao(RoleShortcutDao roleShortcutDao) {
		this.roleShortcutDao = roleShortcutDao;
	}
	
	/**
	 * 增加RoleShortcut
	 */
	public void insertRoleShortcut(RoleShortcut roleShortcut) {
	
		 roleShortcutDao.insert(roleShortcut);
				
	}
	
	/**
	 * 根据ID查询RoleShortcut
	 */
	public void getRoleShortcut(Map<String,Object> param) {
		
		roleShortcutDao.get(param);
				
	}

	/**
	 * 根据查询条件查询所有符合条件的RoleShortcut列表
	 */
	public void listRoleShortcut(Map<String,Object> param) {
		
			List<RoleShortcut> list = roleShortcutDao.list(param);
	}

	/**
	 * 修改RoleShortcut
	 */
	public void updateRoleShortcut(RoleShortcut roleShortcut) {
		
		 roleShortcutDao.update(roleShortcut);
				
	}

	/**
	 * 根据ID删除RoleShortcut
	 */
	public void deleteRoleShortcut(String id) {
	
		if (id.indexOf(",") != -1) {
			String[] ids = StringUtils.split(",");
			roleShortcutDao.deleteList(ids);
		} else {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", id);
		    roleShortcutDao.delete(param);
		}
	}

	
}
