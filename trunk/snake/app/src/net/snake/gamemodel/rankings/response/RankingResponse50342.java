package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.skill.bean.HiddenWeaponDataEntry;
import net.snake.netio.ServerResponse;

/**
 * 暗器 刺客排行
 * 
 */
public class RankingResponse50342 extends ServerResponse {

	private static int msgcode = 50342;

	public RankingResponse50342(List<HiddenWeaponDataEntry> characters, int mingci, int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if (null != characters) {
				writeByte(pagecount);
				writeByte(mingci);
				writeByte(characters.size());
				for (HiddenWeaponDataEntry character : characters) {
					writeInt(character.getCharacterId());
					writeUTF(character.getCharactername());
					writeInt(character.getXiuGrade());
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
