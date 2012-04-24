package net.snake.gamemodel.map.response.hero;

import net.snake.consts.Position;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class AvatarChange60000 extends ServerResponse {
	public AvatarChange60000(Hero otherCharacter, Goodmodel model, boolean onOrOff) {
		this(otherCharacter, model.getId(), onOrOff);
	}

	public AvatarChange60000(Hero otherCharacter, int model, boolean onOrOff) {
		setMsgCode(60000);
		writeInt(otherCharacter.getId());
		writeBoolean(onOrOff);
		writeInt(model);
		CharacterGoods wuqigoods = otherCharacter.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_WUQI);
		if (wuqigoods != null) {
			writeByte(wuqigoods.getPin());
			writeByte(wuqigoods.getJie());
		} else {
			writeByte(0);
			writeByte(0);
		}
	}
}
