package net.snake.gamemodel.map.response.goods;


import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.netio.ServerResponse;



/**
 * 怪物死亡发送物品
 * 
 * @author serv_dev
 * 
 */
public class SceneDropGoodMsg11162 extends ServerResponse {
	public SceneDropGoodMsg11162(SceneDropGood sdg) {
		this.setMsgCode(11162);
			this.writeInt(sdg.getMonsterId());
			this.writeInt(sdg.getId());
			this.writeShort(sdg.getX());
			this.writeShort(sdg.getY());
			this.writeInt(sdg.getCg().getGoodmodelId());
			this.writeInt(sdg.getCg().getCount());
			this.writeByte(sdg.getCg().getPingzhiColor());

	}
}
