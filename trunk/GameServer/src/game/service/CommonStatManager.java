package game.service;

import game.dao.CommonStatDao;
import game.dto.GetNewRole_S2C;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 统计工具
 * 
 * @author hexuhui
 * 
 */
@Component
public class CommonStatManager {

	private static Logger logger = LoggerFactory.getLogger(CommonStatManager.class);

	@Autowired
	private CommonStatDao commonStatDao;

	public void getNewRole(Channel channel, String queryDate) {
		logger.info("query date:{}", queryDate);
		// Long retCount = commonStatDao.getDailyNewRole(queryDate);
		GetNewRole_S2C retMsg = new GetNewRole_S2C();
		retMsg.setCode(1);
		retMsg.setRoleCount(100);
		channel.write(retMsg);
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
