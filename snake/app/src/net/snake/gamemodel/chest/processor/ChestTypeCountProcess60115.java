package net.snake.gamemodel.chest.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.chest.response.ChestResponse60116;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 
 * 宝箱类型统计
 */
@MsgCodeAnn(msgcode = 60115)
public class ChestTypeCountProcess60115 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}

		int typeid = request.getInt();// 请求查看统计的类别
		int count = 0;
		if (typeid == GoodItemId.lansongguo) {
			count = character.getMyChestManager().getChestCount().getChesttype3100();
		} else if (typeid == GoodItemId.lvsongguo) {
			count = character.getMyChestManager().getChestCount().getChesttype3200();
		} else if (typeid == GoodItemId.zisongguo) {
			count = character.getMyChestManager().getChestCount().getChesttype3300();
		}
		character.sendMsg(new ChestResponse60116(typeid, (short) count));
	}

}
