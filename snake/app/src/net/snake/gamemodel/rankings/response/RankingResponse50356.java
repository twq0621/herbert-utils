package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.netio.ServerResponse;


/**
 * 论剑排行

 *
 */
public class RankingResponse50356 extends ServerResponse {

	private static int msgcode = 50356;


	public RankingResponse50356(List<CharacterRanking> characters,int mingci,int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if(null != characters){
				writeByte(pagecount);
				writeByte(mingci);
			    writeByte(characters.size());
			for (CharacterRanking character : characters) {
					writeInt(character.getId());
					writeUTF(character.getName());
					writeInt(character.getLunjianShengWang());
					writeShort(character.getMetop());
			 }
			}else {
				writeByte(0);
				writeByte(0);
				writeByte(0);
			}
		}
			 catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		
	}

	

