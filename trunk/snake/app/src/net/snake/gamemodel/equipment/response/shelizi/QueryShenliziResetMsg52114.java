package net.snake.gamemodel.equipment.response.shelizi;


import net.snake.netio.ServerResponse;


public class QueryShenliziResetMsg52114 extends ServerResponse {

	public QueryShenliziResetMsg52114(int result,int goodmodelId,int jingliangshi,int copper,int suclv) {
		setMsgCode(52114);
		
			writeByte(result);
			if (result > 0) {
			writeInt(goodmodelId);
			writeInt(jingliangshi);
			writeInt(copper);
			writeInt(suclv);
			}
	}
}
