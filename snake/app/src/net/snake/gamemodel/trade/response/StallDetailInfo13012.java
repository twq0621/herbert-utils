package net.snake.gamemodel.trade.response;

import java.util.Collection;

import net.snake.commons.Language;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.ServerResponse;


public class StallDetailInfo13012 extends ServerResponse {

	public StallDetailInfo13012(Hero stallOwner) {
		// 玩家ID（int)、摊主名（str),摊主等级(byte)、摊位名（str）
		// 物品数量(byte) {物品ID(int),索引位置(short),数量(byte)，铜钱(int),元宝(int)}
		// 坐骑数量(byte){坐骑ID(INT),坐骑模型id(int),坐骑名子(str),铜钱(int),元宝(int)}
		setMsgCode(13012);
		ServerResponse out = this;
		try {
			out.writeInt(stallOwner.getId());
			out.writeUTF(stallOwner.getViewName());
			out.writeByte(stallOwner.getGrade());
			String stallname = stallOwner.getStallName();
			if (stallname == null || stallname.length() == 0) {
				stallname = stallOwner.getViewName() + Language.TANWEI;
			}
			out.writeUTF(stallname);
			Collection<CharacterGoods> goodslist = stallOwner
					.getCharacterGoodController().getStallGoodsContainer().getGoodsList();
			out.writeByte(goodslist.size());
			for (CharacterGoods goods : goodslist) {
				out.writeInt(goods.getGoodmodelId());
				out.writeShort(goods.getPosition());
				out.writeInt(goods.getCount());
				out.writeInt(goods.getStallCopper());
				out.writeInt(goods.getStallIngot());
				out.writeByte(goods.getPingzhiColor());
				out.writeByte(goods.getJinjie());
				out.writeBoolean(goods.isAllStar());
			}
			Collection<Horse> horselist = stallOwner
					.getCharacterHorseController().getStallHorseContainer()
					.getHorseCollection();
			out.writeByte(horselist.size());
			for (Horse horse : horselist) {
				out.writeInt(horse.getId());
				out.writeInt(horse.getCharacterHorse().getHorseModelId());
				out.writeUTF(horse.getHorseModel().getNameI18n());
				out.writeInt(horse.getCharacterHorse().getStallCopper());
				out.writeInt(horse.getCharacterHorse().getStallIngot());
			}

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
