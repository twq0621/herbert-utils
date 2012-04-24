package net.snake.gamemodel.equipment.response.shelizi;


import net.snake.netio.ServerResponse;


public class SheliziJinJieMsg52110 extends ServerResponse {

	public SheliziJinJieMsg52110(int result,int nextpos) {
		setMsgCode(52110);
		
			writeByte(result);
				writeShort(nextpos);
	}
}
