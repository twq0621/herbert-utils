package game.logic.account;

import lion.ann.MsgCodeAnn;
import lion.codec.INotNeedAuthProcessor;
import lion.core.GamePlayer;
import lion.message.MyRequestMsg;
import lion.processor.IThreadProcessor;
import lion.processor.MsgProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MsgCodeAnn(msgcode = 10105, accessLimit = 2000)
public class LoginGameProcessor10001 extends MsgProcessor implements IThreadProcessor, INotNeedAuthProcessor {

	public static final Logger logger = LoggerFactory.getLogger(LoginGameProcessor10001.class);

	@Override
	public void process(GamePlayer session, MyRequestMsg request) throws Exception {
		int gameaccount = request.getInt();
		final int characterid = request.getInt();
		logger.info("request enter game ,account id={},hero id={}", gameaccount, characterid);
	}

}
