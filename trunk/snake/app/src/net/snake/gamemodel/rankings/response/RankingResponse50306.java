package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.netio.ServerResponse;


public class RankingResponse50306 extends ServerResponse {

	private static int msgcode = 50306;


	public RankingResponse50306(List<CharacterRanking> characters ,int mingci,int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if(null != characters){
				writeByte(pagecount);
				writeByte(mingci);
				writeByte(characters.size());
			for (CharacterRanking character : characters) {
					writeInt(character.getId());
					writeUTF(character.getName());
					//f_copper+f_storage_copper
					int count= character.getCopper();
					count =count + character.getStorageCopper();
					writeInt(count);
					writeShort(character.getMetop());
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
