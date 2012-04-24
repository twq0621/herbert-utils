package net.snake.gamemodel.shop.response;

import net.snake.netio.ServerResponse;

/**
 * 返回够买是否成功
 * 
 */
public class JieRiDuiHuanJuan_ShopGoodTakeMsg12116 extends ServerResponse {
	public JieRiDuiHuanJuan_ShopGoodTakeMsg12116(int goodNum) {
		this.setMsgCode(12116);
		this.writeInt(goodNum);
		this.writeByte(1);
	}

	public JieRiDuiHuanJuan_ShopGoodTakeMsg12116(int goodNum, int key, String... str) {
		this.setMsgCode(12116);
		try {
			this.writeInt(goodNum);
			this.writeByte(0);
			this.writeInterUTF(key, str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
