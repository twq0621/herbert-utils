package net.snake.gamemodel.shop.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.shop.bean.Shop;
import net.snake.gamemodel.shop.persistence.ShopManager;
import net.snake.gamemodel.shop.response.JieRiDuiHuanJuan_ShopListMsg12114;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 获得节日兑用劵列表
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 12113, accessLimit = 100)
public class JieRiDuiHuanJuan_ShopListProcessor12113 extends CharacterMsgProcessor implements IThreadProcessor {
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
		character.sendMsg(new JieRiDuiHuanJuan_ShopListMsg12114(type, countMonney, list));
	}

}
