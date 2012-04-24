package net.snake.gamemodel.gift.response;

import java.util.List;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class GiftPacksGradeListMsg50712 extends ServerResponse {
	public GiftPacksGradeListMsg50712(CharacterGiftpacks nowGift, List<GiftPacks> list, Hero character) {
		this.setMsgCode(50712);
		this.writeByte(list.size());
		int grade = nowGift.getGp().getGradeLimit();
		for (GiftPacks gp : list) {
			this.writeInt(gp.getGoodId());
			this.writeShort(gp.getGradeLimit());
			this.writeInt(gp.getCopper());
			this.writeInt(gp.getLijin());
			List<CharacterGoods> goodlist = gp.getGoodlist(character.getPopsinger());
			this.writeByte(goodlist.size());
			for (CharacterGoods cg : goodlist) {
				this.writeInt(cg.getGoodmodelId());
				this.writeInt(cg.getCount());
			}
			if (gp.getGradeLimit() < grade) {
				this.writeByte(1);
			} else if (gp.getGradeLimit() > grade) {
				this.writeByte(0);
			} else {
				if (nowGift.getIsOwner()) {
					this.writeByte(1);
				} else {
					this.writeByte(0);
				}
			}
		}

	}

	public static void main(String[] str) {

	}
}
