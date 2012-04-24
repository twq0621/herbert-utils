package net.snake.gamemodel.chest.response;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.snake.commons.Language;
import net.snake.consts.BindChangeType;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.chest.bean.ChestGoods;
import net.snake.gamemodel.chest.bean.ChestResources;
import net.snake.gamemodel.chest.persistence.ChestGoodsManager;
import net.snake.gamemodel.chest.persistence.ChestGroupManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 
 * 
 * @author serv_dev
 * @version 1.0
 * @created 2011-3-22 上午06:22:26
 */
public class ChestToolsResponse {

	/**
	 * @param chestResource
	 *            奖池
	 * @param type
	 *            领奖或者摇奖
	 * @param chest
	 *            日志记录
	 * @param chest_type
	 *            宝箱类别
	 * @param character
	 *            当前人
	 * @throws IOException
	 */
	public void sendChestlist(String[] chestResource, byte type, Chest chest, int chest_type, Hero character, int bangting, ServerResponse output) throws IOException {

		Map<String, ChestGoods> mapChestGoods = character.getMyChestManager().getMapChestTypeMap().get(chest_type);

		output.writeByte(type);
		output.writeByte(chestResource.length);
		List<String> chestResourceList = Arrays.asList(chestResource);
		Collections.shuffle(chestResourceList);
		for (String string : chestResourceList) {

			ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_string().get(string);

			if (type == 2) {
				output.writeInt(0);
				output.writeInt(0);
				output.writeInt(0);
				output.writeByte(0);
			} else {
				if (null == chestResources) {
					continue;
				}
				output.writeInt(chestResources.getGoodmodelId());
				output.writeInt(ChestGroupManager.getInstance().getPosition_To_ChestResourceid(string, chest.getPosition()));
				ChestGoods chestGoods = mapChestGoods.get(string);
				if (null != chestGoods) {
					output.writeInt(chestGoods.getPingzhi());
				} else {
					output.writeInt(0);
				}

				CharacterGoods characterGoods = ChestGoodsManager.getInstance().chestGoodsTOCharacterGoods(chestGoods);
				int xingxingshu = characterGoods.getStrengthenNum();
				String yanseString = character.getMyChestManager().getYanSe(characterGoods.getPingzhiColor());
				if (xingxingshu > 9) {
					output.writeByte(CommonUseNumber.byte1);
				} else if (Language.PUTPLE_EQUIP.equals(yanseString)) {
					output.writeByte(CommonUseNumber.byte1);
				} else {
					output.writeByte((byte) chestResources.getFullServiceAnnouncement());
				}
			}
		}
		ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_string().get(chest.getChestResourcesId());
		output.writeInt(chestResources.getGoodmodelId());
		output.writeInt(ChestGroupManager.getInstance().getPosition_To_ChestResourceid(chest.getChestResourcesId(), chest.getPosition()));
		ChestGoods chestGoods = mapChestGoods.get(chest.getChestResourcesId());
		if (null != chestGoods) {
			if (bangting != -1) {
				if (bangting == BindChangeType.BIND) {
					chestGoods.setBind(CommonUseNumber.byte1);
				} else if (chestResources.getBinding() == BindChangeType.BIND) {
					chestGoods.setBind(CommonUseNumber.byte1);
				} else if (chestResources.getBinding() == BindChangeType.BIND) {
					chestGoods.setBind(CommonUseNumber.byte1);
				} else {
					chestGoods.setBind((byte) bangting);
				}
			}
			output.writeInt(chestGoods.getPingzhi());
		} else {
			output.writeInt(0);
		}
		output.writeByte((byte) chestResources.getFullServiceAnnouncement());

	}

}
