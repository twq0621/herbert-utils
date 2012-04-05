package game.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import lion.common.BaseServiceImpl;
import game.dao.RoleDao;
import game.entity.Role;

/**
 *  角色信息
 * @Title  webGame	
 * @Description RoleService.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Component
@Transactional
public class RoleService extends BaseServiceImpl {

	private RoleDao roleDao;
	
	@Resource
	public void setDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	/**
	 * 增加Role
	 */
	public void insertRole(Role role) {
	
		 roleDao.insert(role);
				
	}
	
	/**
	 * 根据ID查询Role
	 */
	public void getRole(Map<String,Object> param) {
		
		roleDao.get(param);
				
	}

	/**
	 * 根据查询条件查询所有符合条件的Role列表
	 */
	public void listRole(Map<String,Object> param) {
		
			List<Role> list = roleDao.list(param);
	}

	/**
	 * 修改Role
	 */
	public void updateRole(Role role) {
		
		 roleDao.update(role);
				
	}

	/**
	 * 根据ID删除Role
	 */
	public void deleteRole(String id) {
	
		if (id.indexOf(",") != -1) {
			String[] ids = StringUtils.split(",");
			roleDao.deleteList(ids);
		} else {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", id);
		    roleDao.delete(param);
		}
	}

	
}
