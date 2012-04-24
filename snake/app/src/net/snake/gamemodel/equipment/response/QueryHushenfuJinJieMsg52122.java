package net.snake.gamemodel.equipment.response;


import net.snake.netio.ServerResponse;


public class QueryHushenfuJinJieMsg52122 extends ServerResponse {

	public QueryHushenfuJinJieMsg52122(int result,int goodmodelId,int copper,int julizu,int suclv,int nextgooodmodel,int nexttipsid) {
		setMsgCode(52122);
		
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
