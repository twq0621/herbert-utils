package game.logic.item;

import lion.common.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import game.entity.BaseBag;

/**
 *  背包基本属性
 * @Title  webGame
 * @Description IBaseBagDaoImpl.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Repository
public class BaseBagDao extends BaseDaoImpl<BaseBag> {

	@Override
	public Class<BaseBag> getEntityClass() {
		return BaseBag.class;
	}

}
