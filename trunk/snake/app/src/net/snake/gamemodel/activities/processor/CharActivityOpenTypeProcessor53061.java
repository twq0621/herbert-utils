package net.snake.gamemodel.activities.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.activities.persistence.XianshiActivityController;
import net.snake.gamemodel.activities.persistence.XianshiActivityManager;
import net.snake.gamemodel.activities.response.ActivityTypsMsg53062;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


/**
 * 活动开启模块
 * 
 */

@MsgCodeAnn(msgcode = 53061)
public class CharActivityOpenTypeProcessor53061 extends CharacterMsgProcessor implements IThreadProcessor {

	/*
	 * (non-Javadoc)
	 * @see
	 * net.snake.commons.msgprocess.CharacterMsgProcessor#process(net.snake
	 * .bean.character.Character, net.snake.commons.message.RequestMsg)
	 */
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		List<XianshiActivityController> list = XianshiActivityManager
				.getInstance().getXianshiActivityTypeControllerCollection();
		List<XianshiActivityController> tempList = new ArrayList<XianshiActivityController>();
		for (XianshiActivityController cac : list) {
			if (cac.isTimeExpression()) {
				tempList.add(cac);
			}
		}
		if(tempList.size()==0){
			return;
		}
		character.sendMsg(new ActivityTypsMsg53062(tempList));
	}

}
