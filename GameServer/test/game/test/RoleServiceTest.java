package game.test;


import static org.junit.Assert.fail;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import game.entity.Role;
import game.logic.role.RoleService;

/**
 *  角色信息
 * @Title  webGame	
 * @Description RoleServiceTest.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class RoleServiceTest extends AbstractJUnit4SpringContextTests {
	
	private RoleService roleService;
	
	
	@Resource
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	//@Test
	public void testInsertRole() {		
		Role role = new Role();
		role.setRoleName("test");
		role.setUserId(11l);
		roleService.insertRole(role);
		
	}

	@Test
	public void testGetRole() {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id", 4L);
		roleService.getRole(param);
	}

	@Test
	public void testListRole() {
		Map<String,Object> param = new HashMap<String,Object>();
		roleService.listRole(param);
	}

	@Test
	public void testUpdateRole() {
		Role role = new Role();
		role.setId(4L);
		role.setRoleName("testupdate");
		roleService.updateRole(role);
	}

	@Test
	public void testDeleteRole() {
		roleService.deleteRole("1");
	}

	@Test
	public void testListSplitRole() {
		//roleService.deleteRole("1");
	}

}
