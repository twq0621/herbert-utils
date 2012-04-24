package net.snake.gamemodel.equipment.response.shelizi;


import net.snake.netio.ServerResponse;


public class QuerySheliziJinJieMsg52108 extends ServerResponse {

	public QuerySheliziJinJieMsg52108(int result,int goodmodelId,int copper,int julizu,int suclv,int nextgooodmodel,int nexttipsid) {
		setMsgCode(52108);
		
			writeByte(result);
			if (result > 0) {
				writeInt(goodmodelId);
				writeInt(copper);
				writeShort(julizu);
				writeInt(suclv);
				writeInt(nextgooodmodel);
				writeInt(nexttipsid);
			}
	}
}
