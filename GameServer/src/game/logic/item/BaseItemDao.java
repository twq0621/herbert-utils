package game.logic.item;

import lion.common.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import game.entity.BaseItem;

/**
 *  物品基础信息表
 * @Title  webGame
 * @Description IBaseItemDaoImpl.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Repository
public class BaseItemDao extends BaseDaoImpl<BaseItem> {

	@Override
	public Class<BaseItem> getEntityClass() {
		return BaseItem.class;
	}

}
