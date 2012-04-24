/**
 * 
 */
package net.snake.gamemodel.wedding.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.persistence.WeddingRingManager;
import net.snake.gamemodel.wedding.response.WeddingRingListMsg52218;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


/**
 * 请求购买玉佩列表
 */

@MsgCodeAnn(msgcode = 52217)
public class WeddingRingListProcessor52217 extends CharacterMsgProcessor implements IThreadProcessor{

	/* (non-Javadoc)
	 * @see net.snake.commons.msgprocess.CharacterMsgProcessor#process(net.snake.bean.character.Character, net.snake.commons.message.RequestMsg)
	 */
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
           List<WeddingRing> list= WeddingRingManager.getInstance().getWeddingRingList();
           character.sendMsg(new WeddingRingListMsg52218(list));
	}

}
