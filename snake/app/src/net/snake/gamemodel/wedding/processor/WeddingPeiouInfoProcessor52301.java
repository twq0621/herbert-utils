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
import net.snake.gamemodel.hero.response.CharacterAttributesMsg49992;
import net.snake.gamemodel.wedding.response.WeddingPeiouInfoResponse52230;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 请求配偶属性
 * 
 */
@MsgCodeAnn(msgcode = 52301)
public class WeddingPeiouInfoProcessor52301 extends CharacterMsgProcessor implements IThreadProcessor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.commons.msgprocess.CharacterMsgProcessor#process(net.snake
	 * .bean.character.Character, net.snake.commons.message.RequestMsg)
	 */
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		if (!character.getMyFriendManager().getRoleWedingManager().isWedding()) {
			character.sendMsg(new WeddingPeiouInfoResponse52230(0, 0));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17523));
			return;
		}
		int id = character.getMyFriendManager().getRoleWedingManager().getWedderId();
		Hero otherCharacter = GameServer.vlineServerManager.getCharacterById(id);
		// 全服查询
		int ringId = character.getMyFriendManager().getRoleWedingManager().getFuqiWeddingRing().getRingId();
		if (otherCharacter != null) {
			character.sendMsg(new WeddingPeiouInfoResponse52230(ringId, otherCharacter));
			character.sendMsg(new CharacterAttributesMsg49992(otherCharacter));
		} else {
			character.sendMsg(new WeddingPeiouInfoResponse52230(ringId, 1));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17590));
		}
	}

}
