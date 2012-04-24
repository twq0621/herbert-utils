package net.snake.gamemodel.shop.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.shop.bean.Shop;
import net.snake.gamemodel.shop.persistence.ShopManager;
import net.snake.gamemodel.shop.response.ShopListMsg12102;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 12101 获取商城列表
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 12101)
public class ShopListProcessor extends CharacterMsgProcessor implements IThreadProcessor {
	public static int LIJINTYPE = 5;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte type = request.getByte();
		int countMonney = character.getAccount().getYuanbao();
		if (LIJINTYPE == type) {
			countMonney = character.getJiaozi();
		}
		List<Shop> list = ShopManager.getInstance().getListByShopType(type);
		//1热卖，2法宝，3装备，4活动类，5礼金类，6丹药类，7宝石类
		character.sendMsg(new ShopListMsg12102(type, countMonney, list));
	}

}
