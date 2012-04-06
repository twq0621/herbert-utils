package game.logic.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import lion.common.BaseServiceImpl;
import game.entity.BaseItem;

/**
 *  物品基础信息表
 * @Title  webGame	
 * @Description BaseItemService.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Component
@Transactional
public class BaseItemService extends BaseServiceImpl {

	private BaseItemDao baseItemDao;
	
	@Resource
	public void setDao(BaseItemDao baseItemDao) {
		this.baseItemDao = baseItemDao;
	}
	
	/**
	 * 增加BaseItem
	 */
	public void insertBaseItem(BaseItem baseItem) {
	
		 baseItemDao.insert(baseItem);
				
	}
	
	/**
	 * 根据ID查询BaseItem
	 */
	public void getBaseItem(Map<String,Object> param) {
		
		baseItemDao.get(param);
				
	}

	/**
	 * 根据查询条件查询所有符合条件的BaseItem列表
	 */
	public void listBaseItem(Map<String,Object> param) {
		
			List<BaseItem> list = baseItemDao.list(param);
	}

	/**
	 * 修改BaseItem
	 */
	public void updateBaseItem(BaseItem baseItem) {
		
		 baseItemDao.update(baseItem);
				
	}

	/**
	 * 根据ID删除BaseItem
	 */
	public void deleteBaseItem(String id) {
	
		if (id.indexOf(",") != -1) {
			String[] ids = StringUtils.split(",");
			baseItemDao.deleteList(ids);
		} else {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", id);
		    baseItemDao.delete(param);
		}
	}

	
}
