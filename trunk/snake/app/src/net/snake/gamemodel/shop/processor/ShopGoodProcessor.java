package net.snake.gamemodel.shop.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.shop.bean.Shop;
import net.snake.gamemodel.shop.persistence.ShopManager;
import net.snake.gamemodel.shop.response.ShopGoodMsg12108;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 12107 获取商城列表
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 12107)
public class ShopGoodProcessor extends CharacterMsgProcessor implements IThreadProcessor {
	public static int LIJINTYPE = 5;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int goodId = request.getInt();
		byte type = request.getByte();
		Shop shop = ShopManager.getInstance().getShopByGoodId(goodId, type);
		character.sendMsg(new ShopGoodMsg12108(shop, character));
	}

}
