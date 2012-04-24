package net.snake.gamemodel.shop.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.shop.bean.Shop;
import net.snake.netio.ServerResponse;

public class ShopGoodMsg12108 extends ServerResponse {

	public ShopGoodMsg12108(Shop shop, Hero character) {
		this.setMsgCode(12108);
		if (shop != null) {
			this.writeInt(shop.getId());
			this.writeInt(shop.getGoodmodelId());
			this.writeInt(shop.getGoodsPrice());
			this.writeInt(shop.getLimiteCount());
			if (shop.getGoodsType() == 0) {
				this.writeInt(0);
			} else {
				int count = character.getCount(shop.getGoodmodelId());
				this.writeInt(count);
			}
		} else {
			this.writeInt(0);
			this.writeInt(0);
			this.writeInt(0);
			this.writeInt(0);
			this.writeInt(0);
		}

	}
}
