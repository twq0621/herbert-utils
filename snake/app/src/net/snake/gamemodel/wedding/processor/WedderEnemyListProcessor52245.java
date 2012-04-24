/**
 * 
 */
package net.snake.gamemodel.wedding.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.response.WedderEnemyListMsg52246;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


/**
 * 配偶仇人列表
 */


@MsgCodeAnn(msgcode = 52245)
public class WedderEnemyListProcessor52245 extends CharacterMsgProcessor implements IThreadProcessor {

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
		 int wedderId=character.getMyFriendManager().getRoleWedingManager().getWedderId();
	       if(wedderId<1){
	    		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17557));
				return;
	       }
	      Hero wedder= GameServer.vlineServerManager.getCharacterById(wedderId);
	      if(wedder==null){
	    		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17575));
				return;
	      }
          character.sendMsg(new WedderEnemyListMsg52246(wedder.getMyFriendManager().getRoleEnemyManager().getEnemtyList()));
	}

}
