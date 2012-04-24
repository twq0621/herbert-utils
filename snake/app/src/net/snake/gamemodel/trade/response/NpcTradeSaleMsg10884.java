package net.snake.gamemodel.trade.response;


import net.snake.netio.ServerResponse;


/**
 * 卖东西给NPC成功	NpcID(short),物品模板id(int),物品数量(int)
 *
 */
public class NpcTradeSaleMsg10884 extends ServerResponse{
	public NpcTradeSaleMsg10884(int npcId,int goodId,int count,int monney){
		this.setMsgCode(10884);
			this.writeInt(npcId);
			this.writeInt(goodId);
			this.writeInt(count);
			this.writeInt(monney);
		
	}
}
