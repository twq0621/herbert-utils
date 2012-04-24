package net.snake.gamemodel.gift.response;

import java.util.Date;

import net.snake.commons.DateUtil;
import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 领取登入礼包是否成功
 * 
 * @author serv_dev
 * 
 */
public class GiftPacksDayQiandaoMsg50758 extends ServerResponse {
	public GiftPacksDayQiandaoMsg50758(CharacterGiftpacks cgp, Hero character) {
		this.setMsgCode(50758);
		this.writeInt(character.getLeijiGainLijin());
		if (cgp.getIsOwner()) {
			this.writeInt(getMonthLijin());
		} else {
			this.writeInt(getMonthLijin() + 10);
		}
		this.writeBoolean(cgp.getIsOwner());
	}

	private int getMonthLijin() {
		int shengyuDays = DateUtil.shenyuDaysInMonth(new Date());
		return shengyuDays / 7 * 1000 + shengyuDays * 10;
	}
}
