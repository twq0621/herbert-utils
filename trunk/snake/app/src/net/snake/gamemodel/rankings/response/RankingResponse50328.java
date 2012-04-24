package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.netio.ServerResponse;

/**
 * 武功境界排行
 * 
 */
public class RankingResponse50328 extends ServerResponse {

	private static int msgcode = 50328;

	public RankingResponse50328(List<CharacterRanking> characters, int mingci, int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if (null != characters) {
				// private int wuxueJingjie;//武学境界
				writeByte(pagecount);
				writeByte(mingci);
				writeByte(characters.size());
				for (CharacterRanking character : characters) {
					writeInt(character.getId());
					writeUTF(character.getName());
					writeInt(character.getWuxueJingjie());
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
