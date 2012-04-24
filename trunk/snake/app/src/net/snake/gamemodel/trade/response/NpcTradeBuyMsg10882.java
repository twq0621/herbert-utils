package net.snake.gamemodel.trade.response;


import net.snake.netio.ServerResponse;


/**
 * 从npc处购买成功	NpcID(short),物品模型id(int),数量(int)
 *
 */
public class NpcTradeBuyMsg10882 extends ServerResponse{
	public NpcTradeBuyMsg10882(int npcId,int goodId,int count,int monney){
		this.setMsgCode(10882);
			this.writeInt(npcId);
			this.writeInt(goodId);
			this.writeInt(count);
			this.writeInt(monney);
		
	}
}
