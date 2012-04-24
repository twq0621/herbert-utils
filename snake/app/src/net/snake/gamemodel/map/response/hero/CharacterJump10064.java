package net.snake.gamemodel.map.response.hero;


import net.snake.ai.move.MoveInfo;
import net.snake.netio.ServerResponse;


public class CharacterJump10064 extends ServerResponse {
	
	public CharacterJump10064(int roleid, short startx,short starty,short endx,short endy,boolean is2jitiao) {
		setMsgCode(10064);
//		DataIOUtil out = output;
//		try {
			writeInt(roleid);
			writeShort(startx);
			writeShort(starty);
			writeShort(endx);
			writeShort(endy);
			writeBoolean(is2jitiao);
//		} catch (IOException e) {
//		}
	}
	public CharacterJump10064(int roleid, MoveInfo mi) {
		this(roleid,mi.path[0],mi.path[1],mi.path[2],mi.path[3],mi.is2jitiao());
	}

}
