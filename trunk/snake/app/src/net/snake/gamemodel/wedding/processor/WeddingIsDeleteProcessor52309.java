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
 * 是否同意协议离婚处理
 */
@MsgCodeAnn(msgcode = 52309)
public class WeddingIsDeleteProcessor52309 extends CharacterMsgProcessor {

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
		int weddId=character.getMyFriendManager().getRoleWedingManager().getWedderId();
		Hero wedder=GameServer.vlineServerManager.getCharacterById(weddId);
		if(wedder==null){
			if(type!=0){
			  character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17541));
			}
			return;
		}
		if(!character.getMyFriendManager().getRoleWedingManager().isHaveEndWedding(weddId)){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17561));
			return;
		}
		if(type==0){
			character.getMyFriendManager().getRoleWedingManager().clearEndWeddinglog(weddId);
			wedder.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17545));
		}else{
			if(WedFeastManager.getInstance().isFeast(character.getId())){
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17555));
				return;
			}
			wedder.getMyFriendManager().getRoleWedingManager().overWedding(false);
		}
		
	}

}
