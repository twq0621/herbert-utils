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
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


/**
 * 离婚处理器
 */
@MsgCodeAnn(msgcode = 52305)
public class WeddingRoleDeleteProcessor52305 extends CharacterMsgProcessor {

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
		byte type=request.getByte();
		if(!character.getMyFriendManager().getRoleWedingManager().isWedding()){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17557));
			return ;
		}
		if(type==1){
			if(WedFeastManager.getInstance().isFeast(character.getId())){
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17555));
				return;
			}
			character.getMyFriendManager().getRoleWedingManager().overWedding(true);
		}else{
			int weddId=character.getMyFriendManager().getRoleWedingManager().getWedderId();
			Hero wedder=GameServer.vlineServerManager.getCharacterById(weddId);
			if(wedder==null){
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17541));
				return;
			}
			wedder.getMyFriendManager().getRoleWedingManager().onRequestEndWedding(character);
		}
		
	}

}
