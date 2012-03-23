package cn.hxh.stat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hxh.dao.CommonStatDao;

@Component
public class CommonStatManager {

	private static Logger logger = LoggerFactory
			.getLogger(CommonStatManager.class);

	@Autowired
	private CommonStatDao commonStatDao;

	public Integer getNewRole(String queryDate) {
		logger.info("query date:{}", queryDate);
		Long retCount = commonStatDao.getDailyNewRole(queryDate);
		return retCount.intValue();
	}

	public Integer getPassRookieCount(String queryDate) {
		logger.info("query date:{}", queryDate);
		return 250;
	}
}
