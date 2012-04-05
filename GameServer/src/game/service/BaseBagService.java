package game.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import lion.common.BaseServiceImpl;
import game.dao.BaseBagDao;
import game.entity.BaseBag;

/**
 *  背包基本属性
 * @Title  webGame	
 * @Description BaseBagService.java
 * @Copyright (c) 2012
 * @Company www.kingnet.com
 * 
 * @author wujian
 * @version 1.0
 * @date Apr 4, 2012
 */
@Component
@Transactional
public class BaseBagService extends BaseServiceImpl {

	private BaseBagDao baseBagDao;
	
	@Resource
	public void setDao(BaseBagDao baseBagDao) {
		this.baseBagDao = baseBagDao;
	}
	
	/**
	 * 增加BaseBag
	 */
	public void insertBaseBag(BaseBag baseBag) {
	
		 baseBagDao.insert(baseBag);
				
	}
	
	/**
	 * 根据ID查询BaseBag
	 */
	public void getBaseBag(Map<String,Object> param) {
		
		baseBagDao.get(param);
				
	}

	/**
	 * 根据查询条件查询所有符合条件的BaseBag列表
	 */
	public void listBaseBag(Map<String,Object> param) {
		
			List<BaseBag> list = baseBagDao.list(param);
	}

	/**
	 * 修改BaseBag
	 */
	public void updateBaseBag(BaseBag baseBag) {
		
		 baseBagDao.update(baseBag);
				
	}

	/**
	 * 根据ID删除BaseBag
	 */
	public void deleteBaseBag(String id) {
	
		if (id.indexOf(",") != -1) {
			String[] ids = StringUtils.split(",");
			baseBagDao.deleteList(ids);
		} else {
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", id);
		    baseBagDao.delete(param);
		}
	}

	
}
