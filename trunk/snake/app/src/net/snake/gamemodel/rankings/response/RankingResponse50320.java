package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.netio.ServerResponse;


/**
 * boss排行
 *
 */
public class RankingResponse50320 extends ServerResponse {

	private static int msgcode = 50320;


	public RankingResponse50320(List<CharacterRanking> characters,int mingci,int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if(null != characters){
				//	private int bossKill;//击杀BOSS数
				writeByte(pagecount);
				writeByte(mingci);
			    writeByte(characters.size());
			for (CharacterRanking characterRanking : characters) {
					writeInt(characterRanking.getId());
					writeUTF(characterRanking.getName());
					writeInt(characterRanking.getBossKill());
					writeShort(characterRanking.getMetop());
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

	

