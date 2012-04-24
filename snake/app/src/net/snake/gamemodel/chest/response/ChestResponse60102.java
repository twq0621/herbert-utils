package net.snake.gamemodel.chest.response;

import java.io.IOException;
import java.util.List;

import net.snake.commons.Timer;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.chest.bean.ChestResources;
import net.snake.gamemodel.chest.persistence.ChestGroupManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;
import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author serv_dev
 * @version 1.0
 * @created 2011-3-22 上午06:21:59
 */
public class ChestResponse60102 extends ServerResponse {
	private static Logger logger = Logger.getLogger(ChestResponse60102.class);
	private ChestToolsResponse chestToolsResponse = new ChestToolsResponse();

	public ChestResponse60102(Hero character, Goodmodel goodmodel) {
		setMsgCode(60102);

		CharacterGoods cGoods = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(goodmodel.getId().intValue());
		// 宝图逻辑
		if (cGoods.getGoodModel().getKind() == 17) {
			int goodid = 0, canzi = 0, baozangmapid = 0;
			if (GoodItemId.putongbaozang == cGoods.getGoodmodelId().intValue()) {
				goodid = GoodItemId.kongmingsuo;
				canzi = GoodItemId.putongcanzi;
				baozangmapid = GoodItemId.putongbaozang;

			} else {
				goodid = GoodItemId.tianjisuo;
				canzi = GoodItemId.zhenqicanzi;
				baozangmapid = GoodItemId.zhenqibaozhang;
			}
			CharacterGoods cGoodsCanZi = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(canzi);
			if (cGoodsCanZi == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1017, GoodmodelManager.getInstance().get(canzi).getNameI18n()));
				return;
			}
			if (cGoodsCanZi.isInTrade()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1018, cGoodsCanZi.getGoodModel().getNameI18n()));
				return;
			}
			CharacterGoods cGoodsMap = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(baozangmapid);
			if (cGoodsMap == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1017, GoodmodelManager.getInstance().get(baozangmapid).getNameI18n()));
				return;
			}

			if (cGoodsMap.isInTrade()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1018, cGoodsMap.getGoodModel().getNameI18n()));
				return;
			}
			sendChestTreasureMap(character, cGoods, cGoodsCanZi, goodid, cGoodsMap);
		} else {
			// 宝箱逻辑
			sendChest(character, goodmodel, cGoods);
		}
		writeInt(goodmodel.getId());
	}

	/**
	 * @param character
	 *            当前人
	 * @param goodmodel
	 *            类别
	 * @param cGoods
	 *            物品对象
	 */
	public void sendChest(Hero character, Goodmodel goodmodel, CharacterGoods cGoods) {
		int bangting = -1;
		boolean type = false;
		Chest chestNew = null;
		List<Chest> chestList = character.getMyChestManager().getChestlist();
		for (int a = chestList.size(); a > 0; a--) {
			Chest chest = chestList.get(a - 1);
			if (chest.getGoodmodelType().equals(goodmodel.getId())) {
				if (chest.getType() == 1 && chest.getTurn() == 1) {
					// 扣宝箱
					if (cGoods.isInTrade()) {
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1018, cGoods.getGoodModel().getNameI18n()));
						return;
					}
					character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cGoods.getPosition(), 1);
					bangting = cGoods.getBind();
					chestNew = character.getMyChestManager().getRepeatChestList(goodmodel.getId(), character.getGrade(), character.getPopsinger(), goodmodel.getId());
					break;
				} else {
					chestNew = chest;
					type = true;
					break;

				}
			}
		}

		if (character.getMyChestManager().getChestlist().size() == 0 || null == chestNew) {
			// 扣宝箱
			if (cGoods.isInTrade()) {
				//
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1018, cGoods.getGoodModel().getNameI18n()));
				return;
			}
			character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cGoods.getPosition(), 1);
			bangting = cGoods.getBind();
			chestNew = character.getMyChestManager().getRepeatChestList(goodmodel.getId(), character.getGrade(), character.getPopsinger(), goodmodel.getId());
		}

		String chestResource[] = chestNew.getChestList().split(",");
		ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_string().get(chestNew.getChestResourcesId());
		int a = 0;
		for (String string : chestResource) {
			ChestResources chestResources2 = ChestGroupManager.getInstance().getMapChestResources_string().get(string);
			if (null == chestResources2) {
				a = a + 1;
			}
		}

		// 查看数据库包厢数据和人物获得的id是不是一样
		if (chestResources == null || a != 0) {
			chestNew.setType(CommonUseNumber.byte1);
			chestNew.setTime(Timer.getNowTime("yyyy-MM-dd HH:mm:ss"));
			chestNew.setTurn(CommonUseNumber.byte1);
			if (chestNew.getId() != null) {
				character.getMyChestManager().updateChest(chestNew);
			}
			// 查看上次离线是不是有没有领的装备
			type = false;
			chestNew = character.getMyChestManager().getRepeatChestList(goodmodel.getId(), character.getGrade(), character.getPopsinger(), goodmodel.getId());
			String chestResource2[] = chestNew.getChestList().split(",");
			chestResource = chestResource2;
		}
		try {
			// 上次领奖判断true是未领取
			if (type && chestNew.getTurn() == 1) {
				chestToolsResponse.sendChestlist(chestResource, (byte) 2, chestNew, goodmodel.getId(), character, bangting, this);
			} else {
				chestToolsResponse.sendChestlist(chestResource, CommonUseNumber.byte1, chestNew, goodmodel.getId(), character, bangting, this);
			}
		} catch (IOException e) {
			logger.error("ChestResponse60102(Chest, chestToolsResponse)", e); //$NON-NLS-1$
		}
	}

	/**
	 * @param character
	 * @param cGoods
	 *            宝图的类别
	 * @param cGoodsCanZi
	 *            铲子的对象
	 * @param mapEqChest
	 *            宝图对应宝箱id 方便抽奖共用一个奖品池
	 */
	public void sendChestTreasureMap(Hero character, CharacterGoods cGoods, CharacterGoods cGoodsCanZi, int mapEqChest, CharacterGoods cGoodsMap) {

		Goodmodel goodmodel = cGoods.getGoodModel();

		boolean type = false;
		Chest chestNew = null;
		int bangting = -1;
		List<Chest> chestList = character.getMyChestManager().getChestlist();
		for (int a = chestList.size(); a > 0; a--) {
			Chest chest = chestList.get(a - 1);
			if (chest.getGoodmodelType().equals(goodmodel.getId())) {
				if (chest.getType() == 1) {
					// 扣铲子
					character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cGoodsCanZi.getPosition(), 1);
					bangting = cGoodsCanZi.getBind();
					// 扣地图
					character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cGoodsMap.getPosition(), 1);
					chestNew = character.getMyChestManager().getRepeatChestList(mapEqChest, character.getGrade(), character.getPopsinger(), goodmodel.getId());

					break;
				} else {
					chestNew = chest;
					type = true;
					break;
				}
			}
		}

		if (character.getMyChestManager().getChestlist().size() == 0 || null == chestNew) {
			// 扣铲子
			character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cGoodsCanZi.getPosition(), 1);
			bangting = cGoodsCanZi.getBind();
			// 扣地图
			character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cGoodsMap.getPosition(), 1);
			chestNew = character.getMyChestManager().getRepeatChestList(mapEqChest, character.getGrade(), character.getPopsinger(), goodmodel.getId());
		}

		String chestResource[] = chestNew.getChestList().split(",");
		ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_string().get(chestNew.getChestResourcesId());
		int a = 0;
		for (String string : chestResource) {
			ChestResources chestResources2 = ChestGroupManager.getInstance().getMapChestResources_string().get(string);
			if (null == chestResources2) {
				a = a + 1;
			}
		}

		// 查看数据库包厢数据和人物获得的id是不是一样
		if (chestResources == null || a != 0) {
			chestNew.setType(CommonUseNumber.byte1);
			chestNew.setTime(Timer.getNowTime("yyyy-MM-dd HH:mm:ss"));
			chestNew.setTurn(CommonUseNumber.byte1);
			if (chestNew.getId() != null) {
				character.getMyChestManager().updateChest(chestNew);
			}
			// 查看上次离线是不是有没有领的装备
			type = false;
			chestNew = character.getMyChestManager().getRepeatChestList(mapEqChest, character.getGrade(), character.getPopsinger(), goodmodel.getId());
			String chestResource2[] = chestNew.getChestList().split(",");
			chestResource = chestResource2;
		}

		try {
			// 上次领奖判断true是未领取
			if (type) {
				chestToolsResponse.sendChestlist(chestResource, (byte) 2, chestNew, goodmodel.getId(), character, bangting, this);
			} else {
				chestToolsResponse.sendChestlist(chestResource, CommonUseNumber.byte1, chestNew, goodmodel.getId(), character, bangting, this);
			}

		} catch (IOException e) {
			logger.error("ChestResponse60102(ChestTreasureMap, chestToolsResponse)", e); //$NON-NLS-1$
		}

	}

}
