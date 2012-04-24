package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.netio.ServerResponse;


public class RankingResponse50318 extends ServerResponse {

	private static int msgcode = 50318;


	public RankingResponse50318(List<CharacterRanking> characters ,int mingci,int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if(null != characters){
				writeByte(pagecount);
				writeByte(mingci);
				writeByte(characters.size());
			for (CharacterRanking character : characters) {
					writeInt(character.getId());
					writeUTF(character.getName());
					//f_max_continue_killcount
					writeInt(character.getMaxContinueKillcount());
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
