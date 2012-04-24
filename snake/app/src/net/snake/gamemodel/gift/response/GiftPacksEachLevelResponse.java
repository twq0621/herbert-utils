package net.snake.gamemodel.gift.response;

import java.util.List;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.netio.ServerResponse;

/**
 * 60200 用于弹出在右下角的每级升级礼包
 * 
 * 
 * 礼包ID(int),打开等级（short），铜钱（int），礼金（int），礼品数量（byte）{礼品模型id（int）,礼品模型数量（byte）}，描述信息(str)
 * 
 * @author jack
 * 
 */
public class GiftPacksEachLevelResponse extends ServerResponse {

	public GiftPacksEachLevelResponse(CharacterGiftpacks nowGift, int popsinger) {
		this.setMsgCode(60200);
		GiftPacks gp = nowGift.getGp();
		this.writeInt(gp.getId());
		this.writeShort(gp.getGradeLimit());
		this.writeInt(gp.getCopper());
		this.writeInt(gp.getLijin());
		List<CharacterGoods> goodlist = gp.getGoodlist(popsinger);
		this.writeByte(goodlist.size());
		for (CharacterGoods cg : goodlist) {
			this.writeInt(cg.getGoodmodelId());
			this.writeByte(cg.getCount());
		}
		try {
			this.writeUTF(gp.getDescStrI18n());
		} catch (Exception e) {
		}
	}
}
