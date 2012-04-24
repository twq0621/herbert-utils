package net.snake.gamemodel.shop.response;

import net.snake.netio.ServerResponse;

/**
 * 返回够买是否成功
 * 
 */
public class ShopGoodBuyMsg12104 extends ServerResponse {
	public ShopGoodBuyMsg12104(int goodNum) {
		this.setMsgCode(12104);
		this.writeInt(goodNum);
		this.writeByte(1);
	}

	public ShopGoodBuyMsg12104(int goodNum, int key, String... str) {
		this.setMsgCode(12104);
		try {
			this.writeInt(goodNum);
			this.writeByte(0);
			this.writeInterUTF(key, str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
