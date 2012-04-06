package game.logic.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import lion.common.BaseServiceImpl;
import game.entity.RoleFriend;

/**
 *  好友列表信息
 * @Title  webGame	
 * @Description RoleFriendService.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Component
@Transactional
public class RoleFriendService extends BaseServiceImpl {

	private RoleFriendDao roleFriendDao;
	
	@Resource
	public void setDao(RoleFriendDao roleFriendDao) {
		this.roleFriendDao = roleFriendDao;
	}
	
	/**
	 * 增加RoleFriend
	 */
	public void insertRoleFriend(RoleFriend roleFriend) {
	
		 roleFriendDao.insert(roleFriend);
				
	}
	
	/**
	 * 根据ID查询RoleFriend
	 */
	public void getRoleFriend(Map<String,Object> param) {
		
		roleFriendDao.get(param);
				
	}

	/**
	 * 根据查询条件查询所有符合条件的RoleFriend列表
	 */
	public void listRoleFriend(Map<String,Object> param) {
		
			List<RoleFriend> list = roleFriendDao.list(param);
	}

	/**
	 * 修改RoleFriend
	 */
	public void updateRoleFriend(RoleFriend roleFriend) {
		
		 roleFriendDao.update(roleFriend);
				
	}

	/**
	 * 根据ID删除RoleFriend
	 */
	public void deleteRoleFriend(String id) {
	
		if (id.indexOf(",") != -1) {
			String[] ids = StringUtils.split(",");
			roleFriendDao.deleteList(ids);
		} else {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", id);
		    roleFriendDao.delete(param);
		}
	}

	
}
