package net.snake.gamemodel.goods.response;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

public class BodyGoodsUpdata10180 extends ServerResponse {

	public BodyGoodsUpdata10180(CharacterGoods fromGoods) {
		setMsgCode(10180);
		writeShort(fromGoods.getPosition());
		writeInt(fromGoods.getGoodmodelId());
		writeByte(fromGoods.getJinjie());//X
		writeByte(fromGoods.getPingzhiColor());//X
		writeByte(fromGoods.getBind());
		writeInt(fromGoods.getCurrDurability());
		writeBoolean(fromGoods.isAllStar());
		writeByte(fromGoods.getStrengthenNum());//X
		writeBoolean(false);// 是否刻有战纹图腾
		writeBoolean(fromGoods.isManxingGems());// 是否是全满星得
		writeBoolean(fromGoods.isBestEquipmment());// 是否是最好的装备
		writeBoolean(fromGoods.isMaxBornAttribute());// 是否满天生
		// writeByte(fromGoods.)
	}

}
