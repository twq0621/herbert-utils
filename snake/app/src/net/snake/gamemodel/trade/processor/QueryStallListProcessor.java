package net.snake.gamemodel.trade.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.PagedListHolder;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.trade.response.StallList13006;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.serverenv.stall.OnlineStallManagerImp;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 13005, accessLimit = 1000)
public class QueryStallListProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int page = request.getShort();
		int param = request.getByte();// 查询类型
		if (page < 0) {
			return;
		}
		List<Hero> stalllist = null;
		if (param == 10) {// 查询所有
			stalllist = OnlineStallManagerImp.getInstance().getStallList();

		} else if (param == 0) {//
			String content = request.getString();
			stalllist = OnlineStallManagerImp.getInstance().getStallListByCharacterName(content);
		} else if (param == 1) {
			String content = request.getString();
			stalllist = OnlineStallManagerImp.getInstance().getStallListByGoodsName(content);
		} else if (param == 2) {
			String content = request.getString();
			stalllist = OnlineStallManagerImp.getInstance().getStallListByHorseName(content);
		}

		PagedListHolder<Hero> pagelistholder = new PagedListHolder<Hero>(stalllist);
		pagelistholder.setPage(page - 1);
		pagelistholder.setPageSize(10);
		character.sendMsg(new StallList13006(page, pagelistholder));

	}

}
