package net.snake.gamemodel.trade.response;

import java.util.List;

import net.snake.commons.Language;
import net.snake.commons.PagedListHolder;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class StallList13006 extends ServerResponse {
	public StallList13006(int page, PagedListHolder<Hero> pagelistholder) {
		setMsgCode(13006);
		ServerResponse out = this;
		try {

			out.writeShort(pagelistholder.getPageCount());
			out.writeShort(page);
			List<Hero> stalllist = pagelistholder.getPageList();
			out.writeByte(stalllist.size());

			// 玩家ID（int)、摊主名（str),摊主等级(byte)、摊位名（str）、
			// 、物品数量(byte)、坐骑数量(byte)]
			for (Hero character : stalllist) {
				out.writeInt(character.getId());
				out.writeUTF(character.getViewName());
				out.writeByte(character.getGrade());
				String stallname = character.getStallName();
				if (stallname == null || stallname.length() == 0) {
					stallname = character.getViewName() + Language.TANWEI;
				}
				out.writeUTF(stallname);
				out.writeByte(character.getCharacterGoodController().getStallGoodsContainer().getGoodsList().size());
				out.writeByte(character.getCharacterHorseController().getStallHorseContainer().getHorseCount());
			}
			out.writeShort(pagelistholder.getSource().size());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
