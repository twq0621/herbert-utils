package net.snake.gamemodel.heroext.channel.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50216;
import net.snake.netio.message.RequestMsg;


/**    
 *     
 *    请求朋友经脉
 * @author serv_dev     
 * @version 1.0    
 * @created 2011-4-13 下午01:45:02   
 */

@MsgCodeAnn(msgcode = 50215,accessLimit=100)
public class ChannelFriendProcess50215 extends CharacterMsgProcessor{

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
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
	      character.sendMsg(new ChannelResponse50216(wedder));

	}

}
