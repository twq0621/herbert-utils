package net.snake.gamemodel.map.response.hero;


import net.snake.netio.ServerResponse;


public class CharacterInstantMove10120 extends ServerResponse {
	
	public CharacterInstantMove10120(int roleid,short endx,short endy) {
		setMsgCode(10120);
//		DataIOUtil out = output;
//		try {
			writeInt(roleid);
			writeShort(endx);
			writeShort(endy);
//		} catch (IOException e) {
//		}
	}

}
