package net.snake.gamemodel.chest.processor;
/**
 * 
 * 
 * 显示未领取的宝箱类型
 */
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.chest.response.ChestResponse60122;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


@MsgCodeAnn(msgcode = 60121)
public class ChestShowTypeProcess60121 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int a = 0;
		for (Chest chest : character.getMyChestManager().getChestlist()) {
			if(chest.getType() == 0 && chest.getGoodmodelType() != 7004){
				character.sendMsg(new ChestResponse60122(chest.getGoodmodelType()));
				a++;
				break;
			}
		}
		if(a == 0){
			character.sendMsg(new ChestResponse60122(0));
		}
	}

}
