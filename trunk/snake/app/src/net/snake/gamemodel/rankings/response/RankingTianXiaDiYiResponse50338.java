package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.commons.Language;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.netio.ServerResponse;

public class RankingTianXiaDiYiResponse50338 extends ServerResponse {

	private static int msgcode = 50338;

	public RankingTianXiaDiYiResponse50338(List<CharacterGoods> characterGoods, CharacterTianXiaDiYi characterTianXiaDiYi, int oldcharacterid, byte flashsuoyin) {
		super.setMsgCode(msgcode);
		try {
			// private int contemptcount; //人物鄙视
			// private int worshipcount;//人物崇拜

			if (null != characterGoods) {
				writeByte(flashsuoyin);
				writeInt(0);
				writeInt(0);
				writeByte(characterGoods.size());
				for (CharacterGoods goods : characterGoods) {
					writeInt(goods.getGoodmodelId());
					writeShort(goods.getPosition());
					writeInt(goods.getPingzhiColor());
					if (goods.isAllStar()) {
						writeByte(CommonUseNumber.byte1);
					} else {
						writeByte(CommonUseNumber.byte0);
					}
					writeByte(goods.getJinjie());
					writeBoolean((goods.getTotem() != null && !goods.getTotem().isEmpty()));// 是否刻有战纹图腾
					writeBoolean(goods.isManxingGems());// 是否是全满星得
					writeBoolean(goods.isBestEquipmment());// 是否是最好的装备
					writeBoolean(goods.isMaxBornAttribute());
				}
				writeUTF(Language.CHENGHAO);
				writeInt(oldcharacterid);
				// 验证是不是骑马

				if (characterTianXiaDiYi.getHorseModelId() != 0) {
					writeByte(1);
				} else {
					writeByte(0);
				}
				// 人物门派
				writeInt(characterTianXiaDiYi.getPopsinger() == null ? 0 : characterTianXiaDiYi.getPopsinger());
				// 马的id
				if (characterTianXiaDiYi.getHorseModelId() != 0) {
					writeInt(characterTianXiaDiYi.getHorseModelId());
				} else {
					writeInt(0);
				}
				writeInt(0);
			} else {
				writeByte(flashsuoyin);
				writeInt(-1);
				writeInt(-1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
