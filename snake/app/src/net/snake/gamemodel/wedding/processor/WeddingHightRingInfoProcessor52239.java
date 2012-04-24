/**
 * 
 */
package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.persistence.WeddingRingManager;
import net.snake.gamemodel.wedding.response.WeddingRingMsg52240;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


/**
 * 返回高级玉佩属性
 */

@MsgCodeAnn(msgcode = 52239)

public class WeddingHightRingInfoProcessor52239 extends CharacterMsgProcessor implements IThreadProcessor{

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
		  int ringId=request.getInt();
          WeddingRing wr=WeddingRingManager.getInstance().getWeddingRingById(ringId);
          if(wr==null){
          	character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17503));
          	return ;
          }
          WeddingRing my=character.getMyFriendManager().getRoleWedingManager().getFuqiWeddingRing();
         if(my==null){
         	character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17557));
         	return ;
         }
         if(wr.getGrade()<my.getGrade()){
        		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17560));
        	 return;
         }
         character.sendMsg(new WeddingRingMsg52240(wr));
	}

}
