package net.snake.gamemodel.faction.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 设置是否自动同意任意帮主向我发出的入帮邀请(该选项在帮会列表主界面，和系统设置面板内)
 *
 */
@MsgCodeAnn(msgcode = 51067)
public class SystemAccessAppllyProcssor51067 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		byte type=request.getByte();
		character.getMyFactionManager().systemAccessApplyFaction(type);
	}

}
