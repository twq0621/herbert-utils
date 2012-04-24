package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.netio.ServerResponse;


/**
 * 成就排行
 *
 */
public class RankingResponse50334 extends ServerResponse {

	private static int msgcode = 50334;


	public RankingResponse50334(List<CharacterRanking> characters,int mingci,int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if(null != characters){
				
				writeByte(pagecount);
				writeByte(mingci);
			    writeByte(characters.size());
			for (CharacterRanking character : characters) {
					writeInt(character.getId());
					writeUTF(character.getName());
					writeInt(character.getChengjiuPoint());
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

	

