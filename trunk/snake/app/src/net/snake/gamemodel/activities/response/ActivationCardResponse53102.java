package net.snake.gamemodel.activities.response;

import java.util.List;

import net.snake.gamemodel.activities.bean.ActivationCard;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class ActivationCardResponse53102 extends ServerResponse {

	public ActivationCardResponse53102(ActivationCard activationCard, Hero character) {
		this.setMsgCode(53102);
		this.writeInt(activationCard.getCopper());
		this.writeInt(activationCard.getLijin());
		this.writeInt(0);
		List<CharacterGoods> list = activationCard.getGoodlist(character.getPopsinger());
		if (list != null) {
			this.writeByte(list.size());
			for (CharacterGoods cg : list) {
				this.writeInt(cg.getGoodmodelId());
				this.writeShort(cg.getCount());
			}
		} else {
			this.writeByte(0);
		}
	}
}
