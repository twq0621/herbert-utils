package net.snake.gamemodel.map.response.monster;


import net.snake.netio.ServerResponse;


public class MonsterInstantMove10122 extends ServerResponse {
	
	public MonsterInstantMove10122(int id,short endx,short endy) {
		setMsgCode(10122);
		ServerResponse out = this;
			out.writeInt(id);
			out.writeShort(endx);
			out.writeShort(endy);
	}

}
