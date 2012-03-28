package cn.hxh.stat;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hxh.dao.CommonStatDao;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 统计工具
 * 
 * @author hexuhui
 * 
 */
@Component
public class CommonStatManager {

	private static Logger logger = LoggerFactory
			.getLogger(CommonStatManager.class);

	@Autowired
	private CommonStatDao commonStatDao;

	public Integer getNewRole(Channel channel, String queryDate) {
		logger.info("query date:{}", queryDate);
		Long retCount = commonStatDao.getDailyNewRole(queryDate);
		return retCount.intValue();
	}

	public Integer getPassRookieCount(String queryDate) {
		logger.info("query date:{}", queryDate);
		return 250;
	}

	public void testProtoSave() {
		commonStatDao.savePerson();
	}

	public void testProtoGet() {
		try {
			commonStatDao.getPerson();
		} catch (InvalidProtocolBufferException e) {
			logger.error("", e);
		}
	}
}
