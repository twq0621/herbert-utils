package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.netio.ServerResponse;

/**
 * 返回天下第一
 * 
 * @author serv_dev
 * 
 */
public class RankingResponse40024 extends ServerResponse {

	private static int msgcode = 40024;

	public RankingResponse40024(List<CharacterTianXiaDiYi> characters,
			int mingci, int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if (null != characters && !characters.isEmpty()) {

				writeByte(pagecount);
				writeByte(mingci);
				writeByte(characters.size());
				for (CharacterTianXiaDiYi characterRanking : characters) {
					writeInt(characterRanking.getCharacterId());
					writeUTF(characterRanking.getCharacterName());
					writeUTF(characterRanking.getServerId().toString());
					writeShort(characterRanking.getGrade());
					writeUTF(characterRanking.getShenglv().toString());
					writeShort(characterRanking.getLunjianShengwang());
					writeByte(characterRanking.getZhanchangId());
				}
			} else {
				writeByte(0);
				writeByte(0);
				writeByte(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
