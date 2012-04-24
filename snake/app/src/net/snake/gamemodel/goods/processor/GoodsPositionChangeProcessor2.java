package net.snake.gamemodel.goods.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodsContainerType;
import net.snake.consts.Position;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 10075)
public class GoodsPositionChangeProcessor2 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		short srcIndex = request.getShort();
		int srcHorseid = request.getInt(); 
		int destcontainer = request.getByte();
		short destIndex = request.getShort();
		int destHorseid = request.getInt();

		if (Options.IsCrossServ) {
			// 如果发生在跨服包裹上
			if (srcIndex >= Position.BagGoodsBeginPostion && srcIndex < Position.BagGoodsBeginPostion + Position.BAG_PAGE_CAPACITY && destIndex >= Position.BagGoodsBeginPostion
					&& destIndex < Position.BagGoodsBeginPostion + Position.BAG_PAGE_CAPACITY) {
				// 允许包裹内的移动发生
			} else {
				// 不允许非包裹内的移动发生
				return;
			}
		}

		GoodsContainerType postion = GoodsContainerType.getByValue(destcontainer);
		if (postion == GoodsContainerType.onStorage && !NpcManager.getInstance().isInNpcAround(character, Npc.NPC_STOCKROOM)) {
			return;
		}

		if (postion == GoodsContainerType.noWay) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 876));
			return;
		}
		character.getCharacterGoodController().movePosition(srcIndex, srcHorseid, postion, destIndex, destHorseid);
		// 原位置索引(short),马的ID(INT 0表示不是马) 目标容器(byte 1身上,2包裹,3仓库,4摊位,5马,6跨服背包),
		// 新位置索引(short若为0,表示由服务器从容器上自动查找位置),马的ID(INT 0表示不是马)

	}
}
