package net.snake.gamemodel.wedding.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.persistence.WedFeast;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.gamemodel.wedding.response.wedfeast.WedFeastJoinResponse52256;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


/**
 * 给红包
 *@author serv_dev
 */
@MsgCodeAnn(msgcode = 52255,accessLimit=100)
public class WedFeastJoinProcess52255 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		int feastid = request.getInt();
		int gift = request.getInt();
		WedFeast feast = WedFeastManager.getInstance().getFeastByID(feastid);

		if (feast != null) {
			if (WedFeastManager.getInstance().joinFeast(character, feast, gift)) {
				character.sendMsg(new WedFeastJoinResponse52256(0));
			} else {
				character.sendMsg(new WedFeastJoinResponse52256(1));
			}
		}
	}
}
