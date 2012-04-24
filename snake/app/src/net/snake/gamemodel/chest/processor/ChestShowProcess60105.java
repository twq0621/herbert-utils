package net.snake.gamemodel.chest.processor;

/**
 * 
 * 宝箱加成数据显示
 */
import java.util.Map;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.chest.bean.ChestGoods;
import net.snake.gamemodel.chest.persistence.ChestGoodsManager;
import net.snake.gamemodel.chest.persistence.ChestGroupManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.EquipmentDetailMsg50102;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 60105)
public class ChestShowProcess60105 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		// 查看配包里边有没有宝箱
		int positionid = request.getInt();// 索引位置
		Integer typeid = ChestGroupManager.getInstance().getChest_type(positionid);// 返回物品类别
		Map<String, ChestGoods> mapChestGoods = character.getMyChestManager().getMapChestTypeMap().get(typeid);
		Chest chest2 = character.getMyChestManager().getChest(typeid);
		if (chest2 == null) {
			return;
		}
		if (mapChestGoods == null) {
			return;
		}
		String chestresourceid = ChestGroupManager.getInstance().getChestResourceid_To_Position(positionid, chest2.getPosition());
		ChestGoods chestGoods = mapChestGoods.get(chestresourceid);
		if (null != chestGoods) {
			CharacterGoods characterGoods = ChestGoodsManager.getInstance().chestGoodsTOCharacterGoods(chestGoods);
			characterGoods.setCharacterId(character.getId());
			characterGoods.setPosition((short) positionid);
			if (characterGoods.getGoodModel().isEquipment()) {
				characterGoods.equipmentUpdate();
				character.sendMsg(new EquipmentDetailMsg50102(characterGoods));
			}

			character.sendMsg(new EquipmentDetailMsg50102(characterGoods));
		}

	}

}
