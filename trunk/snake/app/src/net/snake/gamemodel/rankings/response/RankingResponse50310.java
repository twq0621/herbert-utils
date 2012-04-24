package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.pet.bean.HorseCharacterView;
import net.snake.netio.ServerResponse;


public class RankingResponse50310 extends ServerResponse {

	private static int msgcode = 50310;


	public RankingResponse50310(List<HorseCharacterView> horseCharacterViews ,int mingci,int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if(null != horseCharacterViews){
				writeByte(pagecount);
				writeByte(mingci);
				writeByte(horseCharacterViews.size());
			for (HorseCharacterView horseCharacterViews2 : horseCharacterViews) {
					writeInt(horseCharacterViews2.getCharacterId());
					writeUTF(horseCharacterViews2.getName());
					writeInt(horseCharacterViews2.getHorsePrice());
					writeInt(horseCharacterViews2.getId());//马的characterhorseid
					writeShort(horseCharacterViews2.getMetop());	
			}
		
			}else {
				writeByte(0);
				writeByte(0);
				writeByte(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	
}
