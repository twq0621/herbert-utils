/**
 * 
 */
package net.snake.gamemodel.shop.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 打折物品关闭 清除角色打折物品
 * 
 * @author serv_dev
 */

@MsgCodeAnn(msgcode = 12109)
public class ShopDazheInfoBreakProccessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		character.getMyShopManger().breakDazheShop();
	}

}
