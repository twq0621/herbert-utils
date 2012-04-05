package game.dao;

import lion.common.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import game.entity.BaseSkill;

/**
 *  技能基本信息
 * @Title  webGame
 * @Description IBaseSkillDaoImpl.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Repository
public class BaseSkillDao extends BaseDaoImpl<BaseSkill> {

	@Override
	public Class<BaseSkill> getEntityClass() {
		return BaseSkill.class;
	}

}
