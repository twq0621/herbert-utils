//package net.snake.gamemodel.equipment.logic;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import net.snake.commons.GenerateProbability;
//import net.snake.commons.Language;
//import net.snake.commons.httplog.HttpInteriorLogService;
//import net.snake.consts.GameConstant;
//import net.snake.consts.GoodItemId;
//import net.snake.gamemodel.common.response.PrompMsg;
//import net.snake.gamemodel.common.response.TipMsg;
//import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
//import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
//import net.snake.gamemodel.equipment.response.CailiaoCombining50120;
//import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
//import net.snake.gamemodel.equipment.response.DelayLoadingMsg50128;
//import net.snake.gamemodel.goods.bean.CharacterGoods;
//import net.snake.gamemodel.goods.logic.CharacterGoodController;
//import net.snake.gamemodel.hero.bean.Hero;
//import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
//import net.snake.gamemodel.log.logic.EquipmentOneLog;
//
//public class GemMaterialCombiningPlay {
//	/**
//	 * 宝石材料升阶
//	 * 
//	 * @param equipment
//	 *            原材料
//	 * @param cailiao1
//	 *            辅材料1
//	 * @param cailiao2
//	 *            辅材料2
//	 * @param cailiao3
//	 *            辅材料3
//	 * @param xinyunNum
//	 *            幸运晶
//	 * @return
//	 */
//	public boolean cailiaoCombining(final CharacterGoods equipment, final int cailiao1, final int cailiao2, final int cailiao3, final int xinyunNum) {
//
//		if (xinyunNum > 9) {
//			return false;
//		}
//
//		if (equipment.isInTrade()) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 610));
//			getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//			return false;
//		}
//
//		// 消耗的真气
//		final EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEquipmentPlayconfigByScheme(equipment.getGoodModel().getPlayconfigScheme());
//
//		if (equipmentPlayconfig == null) {
//			getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//			return false;
//		}
//		if (cailiao1 != equipmentPlayconfig.getFuCailiao1() || cailiao2 != equipmentPlayconfig.getFuCailiao2() || cailiao3 != equipmentPlayconfig.getFuCailiao3()) {
//			return false;
//		}
//
//		if (getCharacter().getZhenqi() < equipmentPlayconfig.getCailiaoCombineZhenqi()) {
//			getCharacter().sendMsg(new CombineFailMsg50150(3));
//			return false;
//		}
//
//		// 幸运晶
//		final Hero own = getCharacter();
//		final CharacterGoodController characterGoodController = own.getCharacterGoodController();
//		int hasXinyunNum = characterGoodController.getBagGoodsCountByModelID(GoodItemId.XINGYUNJING_ID);
//		if (xinyunNum > hasXinyunNum) {// 检查幸运晶数量个数
//			getCharacter().sendMsg(new CombineFailMsg50150(4));
//			return false;
//		}
//
//		Map<Integer, Integer> tmpGoodMap = new HashMap<Integer, Integer>();
//		tmpGoodMap.put(equipment.getGoodmodelId(), 1);
//		if (tmpGoodMap.get(cailiao1) != null) {
//			tmpGoodMap.put(cailiao1, tmpGoodMap.get(cailiao1) + equipmentPlayconfig.getFuCailiao1Num());
//		} else {
//			tmpGoodMap.put(cailiao1, equipmentPlayconfig.getFuCailiao1Num());
//		}
//
//		if (tmpGoodMap.get(cailiao2) != null) {
//			tmpGoodMap.put(cailiao2, tmpGoodMap.get(cailiao2) + equipmentPlayconfig.getFuCailiao2Num());
//		} else {
//			tmpGoodMap.put(cailiao2, equipmentPlayconfig.getFuCailiao2Num());
//		}
//
//		if (tmpGoodMap.get(cailiao3) != null) {
//			tmpGoodMap.put(cailiao3, tmpGoodMap.get(cailiao3) + equipmentPlayconfig.getFuCailiao3Num());
//		} else {
//			tmpGoodMap.put(cailiao3, equipmentPlayconfig.getFuCailiao3Num());
//		}
//
//		for (Iterator<Integer> iterator = tmpGoodMap.keySet().iterator(); iterator.hasNext();) {
//			Integer goodModelId = iterator.next();
//			if (goodModelId == 0)
//				continue;
//
//			if (characterGoodController.getBagGoodsCountByModelID(cailiao1) < tmpGoodMap.get(goodModelId)) {
//				// 合成宝石材料数量不足
//				getCharacter().sendMsg(new CombineFailMsg50150(12));
//				return false;
//			}
//		}
//
//		CharacterGoods reGood1 = new CharacterGoods();
//		reGood1.setGoodmodelId(cailiao1);
//		reGood1.setCount(equipmentPlayconfig.getFuCailiao1Num());
//		reGood1.setPosition((short) 0);
//		CharacterGoods reGood2 = new CharacterGoods();
//		reGood2.setGoodmodelId(cailiao2);
//		reGood2.setCount(equipmentPlayconfig.getFuCailiao2Num());
//		reGood2.setPosition((short) 0);
//		CharacterGoods reGood3 = new CharacterGoods();
//		reGood3.setGoodmodelId(cailiao3);
//		reGood3.setCount(equipmentPlayconfig.getFuCailiao3Num());
//		reGood3.setPosition((short) 0);
//
//		int nextGoodmodelId = equipmentPlayconfig.getNextGoodmodelId();
//		Collection<CharacterGoods> removeGoods = new ArrayList<CharacterGoods>();
//		CharacterGoods reGood = new CharacterGoods();
//		reGood.setGoodmodelId(equipment.getGoodmodelId());
//		reGood.setCount(1);
//		reGood.setPosition(equipment.getPosition());
//		removeGoods.add(reGood);
//		removeGoods.add(reGood1);
//		removeGoods.add(reGood2);
//		removeGoods.add(reGood3);
//		Collection<CharacterGoods> addGoods = new ArrayList<CharacterGoods>();
//		CharacterGoods addGood = CharacterGoods.createCharacterGoods(1, nextGoodmodelId, 0);
//		addGoods.add(addGood);
//		addGood.setBind(equipment.getBind());
//		if (!characterGoodController.getBagGoodsContiner().isHasSpaceForBindFirstRemoveAndAddGoods(removeGoods, addGoods)) {
//			getCharacter().sendMsg(new CombineFailMsg50150(13));
//			return false;
//		}
//
//		character.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					_cailiaoCombining(equipment, cailiao1, cailiao2, cailiao3, xinyunNum, equipmentPlayconfig, own, characterGoodController);
//				} catch (Exception e) {
//					logger.error(e.getMessage(), e);
//				}
//
//			}
//		}, 5 * 1000);
//		character.sendMsg(new DelayLoadingMsg50128());
//		return true;
//	}
//
//	private void _cailiaoCombining(final CharacterGoods equipment, final int cailiao1, final int cailiao2, final int cailiao3, final int xinyunNum,
//			final EquipmentPlayconfig equipmentPlayconfig, final Hero own, final CharacterGoodController characterGoodController) {
//
//		if (equipment.isInTrade()) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 610));
//			getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//			return;
//		}
//
//		if (xinyunNum > GameConstant.xingyunMaxNum) {
//			getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//			return;
//		}
//
//		if (equipmentPlayconfig == null) {
//			getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//			return;
//		}
//
//		// 消耗的真气
//		if (getCharacter().getZhenqi() < equipmentPlayconfig.getCailiaoCombineZhenqi()) {
//			getCharacter().sendMsg(new CombineFailMsg50150(3));
//			getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//			return;
//		}
//
//		// 幸运晶
//		int hasXinyunNum = characterGoodController.getBagGoodsCountByModelID(GoodItemId.XINGYUNJING_ID);
//		if (xinyunNum > hasXinyunNum) {// 检查幸运晶数量个数
//			getCharacter().sendMsg(new CombineFailMsg50150(4));
//			getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//			return;
//		}
//
//		if (cailiao1 != equipmentPlayconfig.getFuCailiao1() || cailiao2 != equipmentPlayconfig.getFuCailiao2() || cailiao3 != equipmentPlayconfig.getFuCailiao3()) {
//			getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//			return;
//		}
//
//		Map<Integer, Integer> tmpGoodMap = new HashMap<Integer, Integer>();
//		tmpGoodMap.put(equipment.getGoodmodelId(), 1);
//		if (tmpGoodMap.get(cailiao1) != null) {
//			tmpGoodMap.put(cailiao1, tmpGoodMap.get(cailiao1) + equipmentPlayconfig.getFuCailiao1Num());
//		} else {
//			tmpGoodMap.put(cailiao1, equipmentPlayconfig.getFuCailiao1Num());
//		}
//
//		if (tmpGoodMap.get(cailiao2) != null) {
//			tmpGoodMap.put(cailiao2, tmpGoodMap.get(cailiao2) + equipmentPlayconfig.getFuCailiao2Num());
//		} else {
//			tmpGoodMap.put(cailiao2, equipmentPlayconfig.getFuCailiao2Num());
//		}
//
//		if (tmpGoodMap.get(cailiao3) != null) {
//			tmpGoodMap.put(cailiao3, tmpGoodMap.get(cailiao3) + equipmentPlayconfig.getFuCailiao3Num());
//		} else {
//			tmpGoodMap.put(cailiao3, equipmentPlayconfig.getFuCailiao3Num());
//		}
//
//		for (Iterator<Integer> iterator = tmpGoodMap.keySet().iterator(); iterator.hasNext();) {
//			Integer goodModelId = iterator.next();
//			if (goodModelId == 0)
//				continue;
//
//			if (characterGoodController.getBagGoodsCountByModelID(cailiao1) < tmpGoodMap.get(goodModelId)) {
//				// 合成宝石材料数量不足
//				getCharacter().sendMsg(new CombineFailMsg50150(12));
//				getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//				return;
//			}
//		}
//
//		int xinyunProbability = equipmentPlayconfig.getCailiaoCombineProbability() + xinyunNum * GameConstant.XingYunJinglv;
//
//		// 消耗真气以及物品
//		CharacterGoods reGood1 = new CharacterGoods();
//		reGood1.setGoodmodelId(cailiao1);
//		reGood1.setCount(equipmentPlayconfig.getFuCailiao1Num());
//		reGood1.setPosition((short) 0);
//		CharacterGoods reGood2 = new CharacterGoods();
//		reGood2.setGoodmodelId(cailiao2);
//		reGood2.setCount(equipmentPlayconfig.getFuCailiao2Num());
//		reGood2.setPosition((short) 0);
//		CharacterGoods reGood3 = new CharacterGoods();
//		reGood3.setGoodmodelId(cailiao3);
//		reGood3.setCount(equipmentPlayconfig.getFuCailiao3Num());
//		reGood3.setPosition((short) 0);
//
//		StringBuffer msg = new StringBuffer();
//		if (GenerateProbability.defaultIsGenerate(xinyunProbability)) {
//			// 宝石、材料为可叠加物品，处理起来有点麻烦
//			int nextGoodmodelId = equipmentPlayconfig.getNextGoodmodelId();
//			Collection<CharacterGoods> removeGoods = new ArrayList<CharacterGoods>();
//			CharacterGoods reGood = new CharacterGoods();
//			reGood.setGoodmodelId(equipment.getGoodmodelId());
//			reGood.setCount(1);
//			reGood.setPosition(equipment.getPosition());
//
//			removeGoods.add(reGood);
//			removeGoods.add(reGood1);
//			removeGoods.add(reGood2);
//			removeGoods.add(reGood3);
//			Collection<CharacterGoods> addGoods = new ArrayList<CharacterGoods>();
//			CharacterGoods addGood = CharacterGoods.createCharacterGoods(1, nextGoodmodelId, 0);
//			addGood.setBind(equipment.getBind());
//			addGoods.add(addGood);
//			if (characterGoodController.getBagGoodsContiner().isHasSpaceForBindFirstRemoveAndAddGoods(removeGoods, addGoods)) {
//				CharacterPropertyManager.changeZhenqi(own, -equipmentPlayconfig.getCailiaoCombineZhenqi());
//				// characterGoodController.deleteGoodsFromBag(GoodItemId.XINGYUNJING_ID,xinyunNum);
//				// equipment.setCount(equipment.getCount() - 1);
//				// if (equipment.getCount() == 0) {
//				// characterGoodController.deleteCharacterGoods(equipment);
//				// } else {
//				// characterGoodController.updateCharacterGoods(equipment);
//				// }
//
//				if (!characterGoodController.getBagGoodsContiner().deleteSplitGoods(equipment.getPosition(), 1)) {
//					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 611));
//					getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//					return;
//				}
//
//				if (!characterGoodController.combineDeleteCailiao(addGood, true, 4, GoodItemId.XINGYUNJING_ID, xinyunNum, cailiao1, equipmentPlayconfig.getFuCailiao1Num(),
//						cailiao2, equipmentPlayconfig.getFuCailiao2Num(), cailiao3, equipmentPlayconfig.getFuCailiao3Num())) {
//					logger.warn("包裹管理器计算出错");
//					return;
//				}
//
//				// characterGoodController.addGoodsToBag(addGoods);
//				CharacterGoods cg = characterGoodController.getBagGoodsContiner().addAndReturnLast(addGood);
//				// characterGoodController.deleteGoodsFromBag(cailiao1,equipmentPlayconfig.getFuCailiao1Num());
//				// characterGoodController.deleteGoodsFromBag(cailiao2,equipmentPlayconfig.getFuCailiao2Num());
//				// characterGoodController.deleteGoodsFromBag(cailiao3,equipmentPlayconfig.getFuCailiao3Num());
//				getCharacter().sendMsg(new CailiaoCombining50120(1, cg.getPosition()));// 先发消息成功再更新
//				String tipMsg[] = new String[2];
//				if (equipment.getGoodModel().getKind() == 6) {// 可删除的代码
//					if (reGood1.getCount() > 0) {
//						msg.append(reGood1.getGoodModel().getNameI18n()).append(Language.GEM).append(reGood1.getCount()).append(Language.ONE);
//					}
//
//					if (reGood2.getCount() > 0) {
//						msg.append(",").append(reGood2.getGoodModel().getNameI18n()).append(Language.GEM).append(reGood2.getCount()).append(Language.ONE);
//					}
//
//					if (reGood2.getCount() > 0) {
//						msg.append(",").append(reGood3.getGoodModel().getNameI18n()).append(Language.GEM).append(reGood3.getCount()).append(Language.ONE);
//					}
//
//					msg.append(Language.LUCKY_CRYSTAL).append(xinyunNum).append(Language.ONE + "," + Language.ZHENQI).append(equipmentPlayconfig.getCailiaoCombineZhenqi())
//							.append(" ");
//					tipMsg[0] = msg.toString();
//					tipMsg[1] = addGood.getGoodModel().getNameI18n();
//					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1091, msg.toString()));
//				} else {
//					msg.append(equipment.getGoodModel().getNameI18n()).append(Language.MAIN_STUFF + "1" + Language.ONE);
//					if (xinyunNum > 0) {
//						msg.append(" ").append(Language.LUCKY_CRYSTAL + xinyunNum);
//					}
//
//					if (equipmentPlayconfig.getCailiaoCombineZhenqi() > 0) {
//						msg.append(" ").append(Language.ZHENQI + equipmentPlayconfig.getCailiaoCombineZhenqi());
//					}
//
//					if (reGood1.getCount() > 0) {
//						msg.append(",");
//						msg.append(reGood1.getGoodModel().getNameI18n()).append(Language.AID_STUFF + reGood1.getCount()).append(Language.ONE);
//					}
//
//					if (reGood2.getCount() > 0) {
//						msg.append(",").append(reGood2.getGoodModel().getNameI18n()).append(Language.AID_STUFF + reGood2.getCount()).append(Language.ONE);
//					}
//
//					if (reGood2.getCount() > 0) {
//						msg.append(",").append(reGood3.getGoodModel().getNameI18n()).append(Language.AID_STUFF).append(reGood3.getCount()).append(Language.ONE);
//					}
//					tipMsg[0] = msg.toString();
//					msg.delete(0, msg.length());
//					tipMsg[1] = msg.append(addGood.getGoodModel().getNameI18n()).append(Language.NEW_STUFF).append(addGood.getCount()).append(Language.ONE).toString();
//					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1092, tipMsg[0], tipMsg[1]));
//				}
//
//				// getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//				// character.getMyCharacterAchieveCountManger()
//				// .getEquipmentCount().baoshiHechengEquipment(addGood);
//			} else {
//				// character.sendMsg(new TipMsg(TipMsg.MSGPOSITION_ERRORTIP,
//				// "包裹没有剩余的空间"));
//				getCharacter().sendMsg(new CombineFailMsg50150(13));
//			}
//		} else {// 失败
//			CharacterPropertyManager.changeZhenqi(own, -equipmentPlayconfig.getCailiaoCombineZhenqi());
//			// characterGoodController.deleteGoodsFromBag(GoodItemId.XINGYUNJING_ID,xinyunNum);
//			// characterGoodController.deleteGoodsFromBag(cailiao1,equipmentPlayconfig.getFuCailiao1Num());
//			// characterGoodController.deleteGoodsFromBag(cailiao2,equipmentPlayconfig.getFuCailiao2Num());
//			// characterGoodController.deleteGoodsFromBag(cailiao3,equipmentPlayconfig.getFuCailiao3Num());
//			if (!characterGoodController.combineDeleteCailiao(equipment, false, 4, GoodItemId.XINGYUNJING_ID, xinyunNum, cailiao1, equipmentPlayconfig.getFuCailiao1Num(),
//					cailiao2, equipmentPlayconfig.getFuCailiao2Num(), cailiao3, equipmentPlayconfig.getFuCailiao3Num())) {
//				logger.warn("包裹管理器计算出错");
//				return;
//			}
//			getCharacter().sendMsg(new CailiaoCombining50120(0, 0));
//			if (equipment.getGoodModel().getKind() == 6) {
//				msg.append("");
//				if (reGood1.getCount() > 0) {
//					msg.append(reGood1.getGoodModel().getNameI18n()).append(Language.GEM + reGood1.getCount()).append(Language.ONE);
//				}
//
//				if (reGood2.getCount() > 0) {
//					msg.append(",").append(reGood2.getGoodModel().getNameI18n()).append(Language.GEM).append(reGood2.getCount()).append(Language.ONE);
//				}
//
//				if (reGood2.getCount() > 0) {
//					msg.append(",").append(reGood3.getGoodModel().getNameI18n()).append(Language.GEM).append(reGood3.getCount()).append(Language.ONE);
//				}
//
//				msg.append(Language.LUCKY_CRYSTAL).append(xinyunNum).append(Language.ONE + "," + Language.ZHENQI).append(equipmentPlayconfig.getCailiaoCombineZhenqi());
//				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1093, msg.toString()));
//			} else {
//				if (xinyunNum > 0) {
//					msg.append(Language.LUCKY_CRYSTAL + xinyunNum);
//				}
//
//				if (equipmentPlayconfig.getCailiaoCombineZhenqi() > 0) {
//					msg.append(" ").append(Language.ZHENQI + equipmentPlayconfig.getCailiaoCombineZhenqi());
//				}
//
//				// msg.append(Language.LUCKY_CRYSTAL).append(xinyunNum)
//				// .append(Language.ONE + "," + Language.ZHENQI)
//				// .append(equipmentPlayconfig.getCailiaoCombineZhenqi())
//				// .append(" ");
//				if (reGood1.getCount() > 0) {
//					msg.append(reGood1.getGoodModel().getNameI18n()).append(Language.AID_STUFF + reGood1.getCount()).append(Language.ONE);
//				}
//
//				if (reGood2.getCount() > 0) {
//					msg.append(",").append(reGood2.getGoodModel().getNameI18n()).append(Language.AID_STUFF).append(reGood2.getCount()).append(Language.ONE);
//				}
//
//				if (reGood2.getCount() > 0) {
//					msg.append(",").append(reGood3.getGoodModel().getNameI18n()).append(Language.AID_STUFF + reGood3.getCount()).append(Language.ONE);
//				}
//				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1094, msg.toString()));
//				msg.append("");
//
//			}
//
//		}
//		// 添加材料合成日志
//		try {
//			EquipmentOneLog equipmentOneLog = new EquipmentOneLog(character, 0, xinyunNum, EquipmentOneLog.CODE_1000);
//			HttpInteriorLogService.getInstance().interiorEquipmentOneLog(equipmentOneLog);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//
//	}
//
//}
