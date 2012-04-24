package net.snake.gamemodel.gift.response;

import java.util.List;

import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class GiftPacksGradeMsg50702 extends ServerResponse {
	
	public GiftPacksGradeMsg50702(GiftPacks gp, int goodId, int positon, Hero character) {
		this.setMsgCode(50702);
		this.writeInt(goodId);
		this.writeShort(positon);
		this.writeShort(gp.getGradeLimit());
		this.writeInt(gp.getCopper());
		this.writeInt(gp.getLijin());
		List<CharacterGoods> list = gp.getGoodlist(character.getPopsinger());
		this.writeByte(list.size());
		for (CharacterGoods cg : list) {
			this.writeInt(cg.getGoodmodelId());
			this.writeInt(cg.getCount());
		}

	}
}
