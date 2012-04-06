package game.logic.skill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import lion.common.BaseServiceImpl;
import game.dao.BaseSkillDao;
import game.entity.BaseSkill;

/**
 *  技能基本信息
 * @Title  webGame	
 * @Description BaseSkillService.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Component
@Transactional
public class BaseSkillService extends BaseServiceImpl {

	private BaseSkillDao baseSkillDao;
	
	@Resource
	public void setDao(BaseSkillDao baseSkillDao) {
		this.baseSkillDao = baseSkillDao;
	}
	
	/**
	 * 增加BaseSkill
	 */
	public void insertBaseSkill(BaseSkill baseSkill) {
	
		 baseSkillDao.insert(baseSkill);
				
	}
	
	/**
	 * 根据ID查询BaseSkill
	 */
	public void getBaseSkill(Map<String,Object> param) {
		
		baseSkillDao.get(param);
				
	}

	/**
	 * 根据查询条件查询所有符合条件的BaseSkill列表
	 */
	public void listBaseSkill(Map<String,Object> param) {
		
			List<BaseSkill> list = baseSkillDao.list(param);
	}

	/**
	 * 修改BaseSkill
	 */
	public void updateBaseSkill(BaseSkill baseSkill) {
		
		 baseSkillDao.update(baseSkill);
				
	}

	/**
	 * 根据ID删除BaseSkill
	 */
	public void deleteBaseSkill(String id) {
	
		if (id.indexOf(",") != -1) {
			String[] ids = StringUtils.split(",");
			baseSkillDao.deleteList(ids);
		} else {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", id);
		    baseSkillDao.delete(param);
		}
	}

	
}
