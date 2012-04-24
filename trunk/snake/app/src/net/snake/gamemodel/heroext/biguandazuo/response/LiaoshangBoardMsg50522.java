package net.snake.gamemodel.heroext.biguandazuo.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;


/**
 * 玩家状态广播
 * 
 * @author serv_dev
 * 
 */
public class LiaoshangBoardMsg50522 extends ServerResponse {
	public LiaoshangBoardMsg50522(Hero liaoshanger, Hero shoushanger) {
		this.setMsgCode(50522);
			this.writeInt(liaoshanger.getId());
			if(shoushanger==null){
				this.writeByte(0);
				return;
			}
			this.writeByte(1);
			this.writeInt(shoushanger.getId());
			// this.writeUTF(shuangXiu.getName());
			this.writeShort(shoushanger.getX());
			this.writeShort(shoushanger.getY());
	}

}
