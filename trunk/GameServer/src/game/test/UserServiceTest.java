package game.test;


import static org.junit.Assert.fail;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import game.dto.Login_C2S;
import game.entity.User;
import game.service.UserService;

/**
 *  用户信息
 * @Title  webGame	
 * @Description UserServiceTest.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
	
	private UserService userService;
	
	
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	//@Test
	public void testInsertUser() {		
//		Login_C2S msg = new Login_C2S();
//		userService.login(1, msg);
		
	}


}
