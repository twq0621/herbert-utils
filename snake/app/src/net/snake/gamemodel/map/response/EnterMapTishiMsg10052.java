package net.snake.gamemodel.map.response;


import net.snake.netio.ServerResponse;


public class EnterMapTishiMsg10052 extends ServerResponse {

	public EnterMapTishiMsg10052(short x,short y,String msg) {
		this.setMsgCode(10052);
		try {
		
			this.writeShort(x);
			this.writeShort(y);
			this.writeUTF(msg);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
