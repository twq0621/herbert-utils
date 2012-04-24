package net.snake.gamemodel.gift.response;

import java.util.List;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.logic.RoleOlineGiftPacksManger;
import net.snake.gamemodel.guide.bean.CharacterWeekTime;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 发送登入有礼 列表信息
 * 
 * @author serv_dev
 * 
 */
public class GiftPacksOnlineListMsg50752 extends ServerResponse {
	public GiftPacksOnlineListMsg50752(RoleOlineGiftPacksManger roleOlineGiftPacksManger, List<GiftPacks> list, Hero character) {
		this.setMsgCode(50752);
		CharacterGiftpacks nowGift = roleOlineGiftPacksManger.getNowGift();
		CharacterWeekTime priWeek = roleOlineGiftPacksManger.getPriWeek();
		CharacterWeekTime nowWeek = roleOlineGiftPacksManger.getNowWeek();
		if (priWeek == null) {
			this.writeInt(0);
		} else {
			this.writeInt(priWeek.getfOnlineTime());
		}
		if (nowGift == null) {
			this.writeInt(0);
			this.writeByte(0);
		} else {
			this.writeInt(nowGift.getGp().getLijin());
			if (nowGift.getIsOwner()) {
				this.writeByte(0);
			} else {
				this.writeByte(1);
			}
		}
		if (nowWeek == null) {
			this.writeInt(0);
		} else {
			int time = (int) ((System.currentTimeMillis() - roleOlineGiftPacksManger.getStartDate().getTime()) / 1000);
			this.writeInt(nowWeek.getfOnlineTime() + time);
		}
		this.writeByte(list.size());
		for (GiftPacks gp : list) {
			this.writeInt(gp.getOnlineTimeLimit());
			this.writeInt(gp.getLijin() / 10);
		}

	}

}
