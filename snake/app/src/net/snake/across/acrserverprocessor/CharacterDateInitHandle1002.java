package net.snake.across.acrserverprocessor;

import net.snake.across.msg.CharacteInitResult1003;
import net.snake.commons.ZipUtil;
import net.snake.commons.msgprocess.AuthSTSHandler;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gmtool.net.Msg;
import net.snake.shell.Options;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import datatransport.util.CharacterDateIoUtil;

/**
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class CharacterDateInitHandle1002 extends AuthSTSHandler {
	private static Logger logger = Logger.getLogger(CharacterDateInitHandle1002.class);

	@Override
	public void handleAuthMessage(IoSession session, Msg message) throws Exception {
		if (!Options.IsCrossServ) {
			return;
		}
		logger.info("开始执行消息号：" + message.getFunction());
		long time = System.currentTimeMillis();

		logger.info("解压缩前数据大小为:" + message.getContent().length);
		message.setContent(ZipUtil.unZip(message.getContent()));
		logger.info("解压缩后数据大小为:" + message.getContent().length + "  压缩所用时间为:" + (System.currentTimeMillis() - time));
		AcrossEtc ae = CharacterDateIoUtil.ioCharacterToDb(message);
		session.write(new CharacteInitResult1003(ae));
		logger.info("执行完毕消息号：" + message.getFunction() + " 执行时间：" + (System.currentTimeMillis() - time));
	}

}
