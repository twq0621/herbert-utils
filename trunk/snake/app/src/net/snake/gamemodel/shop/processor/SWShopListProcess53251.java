package net.snake.gamemodel.shop.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.shop.bean.ShopShengwang;
import net.snake.gamemodel.shop.persistence.SwShopManager;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

@MsgCodeAnn(msgcode = 53251)
public class SWShopListProcess53251 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int type = request.getByte();
		int type2 = request.getByte();
		List<ShopShengwang> shoItemByType = SwShopManager.getInstance().getShoItemByType(type, type2);
		character.sendMsg(new SwShopListResult53251(shoItemByType, type));
	}
}

class SwShopListResult53251 extends ServerResponse {
	public SwShopListResult53251(List<ShopShengwang> list, int type) {
		setMsgCode(53252);
		ServerResponse out = this;
		out.writeByte(type);
		out.writeByte(list != null ? list.size() : 0);
		if (list != null)
			for (ShopShengwang entry : list) {
				out.writeInt(entry.getId());
				out.writeInt(entry.getGoodmodelId());
				out.writeByte(entry.getSaleType());
				out.writeInt(entry.getGoodsPrice());
			}
	}
}
