/**
 * 
 */
package net.snake.gamemodel.wedding.response;


import net.snake.netio.ServerResponse;


/**
 * 升级成功 弹出领取1/2配 界面
 * @author serv_dev
 */

public class WeddingMsg52242 extends ServerResponse {
public WeddingMsg52242(Integer ringID){
	this.setMsgCode(52242);
		this.writeInt(ringID);
}
}
