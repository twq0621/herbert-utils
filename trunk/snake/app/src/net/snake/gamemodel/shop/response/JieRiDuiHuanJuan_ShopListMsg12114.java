package net.snake.gamemodel.shop.response;

import java.util.List;

import net.snake.gamemodel.shop.bean.Shop;
import net.snake.netio.ServerResponse;

public class JieRiDuiHuanJuan_ShopListMsg12114 extends ServerResponse {

	public JieRiDuiHuanJuan_ShopListMsg12114(int type, int countMonney, List<Shop> list) {
		this.setMsgCode(12114);
		this.writeByte(type);
		this.writeInt(countMonney);
		if (list == null || list.size() == 0) {
			this.writeByte(0);
			return;
		}
		this.writeByte(list.size());
		for (Shop shop : list) {
			this.writeInt(shop.getId());
			this.writeInt(shop.getGoodmodelId());
			this.writeInt(shop.getGoodsPrice());
			this.writeInt(shop.getGoodsOldPrice());
			this.writeByte(shop.getHotMark());
			this.writeByte(shop.getAbateMark());
		}

	}
}
