package net.snake.gamemodel.faction.processor.factioncity;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.FactionCity;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.response.factioncity.FactionCity51110;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


/**
 * 请求税率与税收面板
 * 
 * 
 */
@MsgCodeAnn(msgcode = 51109)
public class FactionCtTaxInfoPrcocessor51109 extends CharacterMsgProcessor implements IThreadProcessor{

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		FactionCity factionCity = FactionCityManager.getInstance()
				.getFactionCity();
		character.sendMsg(new FactionCity51110(factionCity));
	}

}
