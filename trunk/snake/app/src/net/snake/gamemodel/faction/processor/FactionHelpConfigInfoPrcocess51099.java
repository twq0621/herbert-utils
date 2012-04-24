package net.snake.gamemodel.faction.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 帮会帮主面板显示设置
 *
 */
@MsgCodeAnn(msgcode = 51099)
public class FactionHelpConfigInfoPrcocess51099 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		 byte type=request.getByte();
         if(!character.getMyFactionManager().isFaction()){
        	 return;
         }
         character.getMyFactionManager().changeFactionIsTishiMsg(type);
	}

}
