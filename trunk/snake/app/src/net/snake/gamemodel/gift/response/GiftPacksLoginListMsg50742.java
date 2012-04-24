package net.snake.gamemodel.gift.response;

import java.util.List;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.logic.MyCharacterGiftpacksManger;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 发送登入有礼 列表信息
 * 
 * @author serv_dev
 * 
 */
public class GiftPacksLoginListMsg50742 extends ServerResponse {
	
	public GiftPacksLoginListMsg50742(MyCharacterGiftpacksManger myCharacterGiftpacksManger, List<GiftPacks> list, Hero character) {
		this.setMsgCode(50742);
		this.writeByte(character.getWeekLoginCount());
		this.writeByte(list.size());
		for (GiftPacks gp : list) {

			this.writeByte(gp.getLoginLimit());
			this.writeInt(gp.getId());
			List<CharacterGoods> goodlist = gp.getGoodlist(character.getPopsinger());
			this.writeByte(goodlist.size());
			for (CharacterGoods cg : goodlist) {
				this.writeInt(cg.getGoodmodelId());
				this.writeInt(cg.getCount());
			}

			CharacterGiftpacks cgp = myCharacterGiftpacksManger.getCharacterGiftpacksByGiftId(gp.getId());
			if (cgp != null) {
				if (cgp.getIsOwner()) {
					this.writeByte(1);
				} else {
					this.writeByte(2);
				}
			} else {
				this.writeByte(0);
			}
		}

	}

}
