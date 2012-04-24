package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.netio.ServerResponse;

/**
 * 经脉排行
 * 
 */
public class RankingResponse50326 extends ServerResponse {

	private static int msgcode = 50326;

	public RankingResponse50326(List<CharacterRanking> characters, int mingci, int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if (null != characters) {
				writeByte(pagecount);
				writeByte(mingci);
				writeByte(characters.size());
				for (CharacterRanking character : characters) {
					writeInt(character.getId());
					writeUTF(character.getName());
					writeInt(character.getChannelXuewei());
					writeShort(character.getMetop());
				}
			} else {
				writeByte(0);
				writeByte(0);
				writeByte(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
