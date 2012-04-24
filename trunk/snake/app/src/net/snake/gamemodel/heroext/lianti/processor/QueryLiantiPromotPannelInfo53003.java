package net.snake.gamemodel.heroext.lianti.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.lianti.response.LiantiPromotPanelInfo53004;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 请求主角提升炼体境界面板
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 53003, accessLimit = 100)
public class QueryLiantiPromotPannelInfo53003 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		character.sendMsg(new LiantiPromotPanelInfo53004(character));
	}
}
