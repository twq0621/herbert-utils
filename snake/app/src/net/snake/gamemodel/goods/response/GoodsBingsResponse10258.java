package net.snake.gamemodel.goods.response;


import net.snake.netio.ServerResponse;


/**
 * 10352
 * 
 * @author serv_dev
 * 
 */
public class GoodsBingsResponse10258 extends ServerResponse {

	/**
	 * 当人物换装的时候，绑定状态改变了，发送该消息
	 * @param index 道具位置
	 */
	public GoodsBingsResponse10258(short index,byte bind) {
		this.setMsgCode(10258);
			this.writeShort(index);
			this.writeByte(bind);
	}
}
