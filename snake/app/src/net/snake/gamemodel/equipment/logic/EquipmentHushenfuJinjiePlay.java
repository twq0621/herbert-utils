//package net.snake.gamemodel.equipment.logic;
//
//import net.snake.commons.GenerateProbability;
//import net.snake.commons.httplog.HttpInteriorLogService;
//import net.snake.consts.CopperAction;
//import net.snake.consts.GoodItemId;
//import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
//import net.snake.gamemodel.common.response.PrompMsg;
//import net.snake.gamemodel.common.response.TipMsg;
//import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
//import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
//import net.snake.gamemodel.equipment.response.DelayLoadingMsg52120;
//import net.snake.gamemodel.equipment.response.HushenfuJinJieMsg52116;
//import net.snake.gamemodel.goods.bean.CharacterGoods;
//import net.snake.gamemodel.goods.logic.CharacterGoodController;
//import net.snake.gamemodel.hero.bean.Hero;
//import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
//import net.snake.gamemodel.log.logic.HushenfuLog;
//
//public class EquipmentHushenfuJinjiePlay {
//	/**
//	 * 装备升阶
//	 * 
//	 * @param equipment
//	 * @param zhenxingfu
//	 *            镇星符
//	 * @param zhenghunfu
//	 *            镇魂符
//	 * @param xingyunjing
//	 *            幸运晶
//	 * @return
//	 */
//	public boolean createHushenfuNextCharacterGoods(final CharacterGoods equipment) {
//
//		if (vailidateHushenfu(equipment)) {
//			CharacterGoodController characterGoodController = character.getCharacterGoodController();
//			EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEquipmentPlayconfigByScheme(equipment.getGoodModel().getPlayconfigScheme());
//
//			if (equipment.getTupoCnt() >= equipmentPlayconfig.getSheliziUpgradeMaxNum()
//					|| (equipment.getTupoCnt() >= equipmentPlayconfig.getSheliziUpgradeMinNum() && GenerateProbability.defaultIsGenerate(equipmentPlayconfig
//							.getShenjieProbability()))) {
//				character.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
//					@Override
//					public void run() {
//						try {
//							_createHushenfuNextCharacterGoods(equipment);
//						} catch (Exception e) {
//							logger.error(e.getMessage(), e);
//						}
//
//					}
//
//				}, 5 * 1000);
//
//				character.sendMsg(new DelayLoadingMsg52120());
//			} else {
//				// 消耗真气以及物品
//
//				if (!characterGoodController.combineDeleteCailiao(equipment, false, 1, equipmentPlayconfig.getShenjieshi(), equipmentPlayconfig.getShenjieshiNum())) {
//					logger.warn("包裹管理器计算出错");
//					return false;
//				}
//				equipment.setTupoCnt(equipment.getTupoCnt() + 1);
//				characterGoodController.updateCharacterGoods(equipment);
//				CharacterPropertyManager.changeCopper(character, -equipmentPlayconfig.getSheliziCopper(), CopperAction.CONSUME);
//				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20063, equipmentPlayconfig.getShenjieshiNum() + "", equipmentPlayconfig.getSheliziCopper() + ""));
//				getCharacter().sendMsg(new HushenfuJinJieMsg52116(0, equipment.getPosition()));
//			}
//			// 添加护身符日志
//			try {
//				HushenfuLog hushenfuLog = new HushenfuLog(character, equipmentPlayconfig.getNextGoodmodelId(), 0, HushenfuLog.CODE_100);
//				HttpInteriorLogService.getInstance().interiorHushenhuLog(hushenfuLog);
//			} catch (Exception e) {
//				logger.error(e.getMessage());
//			}
//
//			return true;
//		}
//		return false;
//	}
//
//	private boolean vailidateHushenfu(final CharacterGoods equipment) {
//		if (!equipment.getGoodModel().isEquipment()) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20064));
//			getCharacter().sendMsg(new HushenfuJinJieMsg52116(0, equipment.getPosition()));
//			return false;
//		}
//
//		if (!equipment.getGoodModel().isFuShenfu()) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20064));
//			getCharacter().sendMsg(new HushenfuJinJieMsg52116(0, equipment.getPosition()));
//			return false;
//		}
//
//		if (equipment.isInTrade()) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 599));
//			getCharacter().sendMsg(new HushenfuJinJieMsg52116(0, equipment.getPosition()));
//			return false;
//		}
//
//		final EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEquipmentPlayconfigByScheme(equipment.getGoodModel().getPlayconfigScheme());
//		// 是null就是不能合成此物品
//		if (null == equipmentPlayconfig) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20065));
//			getCharacter().sendMsg(new HushenfuJinJieMsg52116(0, equipment.getPosition()));
//			return false;
//		}
//
//		final Hero own = getCharacter();
//		final CharacterGoodController characterGoodController = own.getCharacterGoodController();
//
//		final int nextGoodModelId = equipmentPlayconfig.getNextGoodmodelId();
//		// 验证是不是到最高级了不能再合成了
//		if (nextGoodModelId == 1) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20068));
//			getCharacter().sendMsg(new HushenfuJinJieMsg52116(0, equipment.getPosition()));
//			return false;
//		}
//
//		if (nextGoodModelId == GoodItemId.SL_FUSHENFU) {// 要合成神龙护身符
//			getCharacter().sendMsg(new HushenfuJinJieMsg52116(0, equipment.getPosition()));
//			return false;
//		}
//
//		if (!(nextGoodModelId == GoodItemId.SL_FUSHENFU && LinshiActivityManager.getInstance().isTimeByLinshiActivityID(13))) {// 要合成神龙护身符
//			// 聚灵珠数量
//			final int needShenJie = equipmentPlayconfig.getShenjieshiNum();
//			if (needShenJie > characterGoodController.getBagGoodsCountByModelID(equipmentPlayconfig.getShenjieshi())) {
//				// 聚灵珠数量不足
//				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20066));
//				getCharacter().sendMsg(new HushenfuJinJieMsg52116(0, equipment.getPosition()));
//				return false;
//			}
//		}
//
//		int needCopper = equipmentPlayconfig.getSheliziCopper();
//		if (character.getCopper() < needCopper) {
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20067));
//			getCharacter().sendMsg(new HushenfuJinJieMsg52116(0, equipment.getPosition()));
//			return false;
//		}
//
//		return true;
//	}
//	private void _createHushenfuNextCharacterGoods(CharacterGoods equipment) {
//		if (vailidateHushenfu(equipment)) {
//			CharacterGoodController characterGoodController = character.getCharacterGoodController();
//			EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEquipmentPlayconfigByScheme(equipment.getGoodModel().getPlayconfigScheme());
//			CharacterGoods characterGoods = equipment.createNextHushenfuCharacterGoods(equipmentPlayconfig.getNextGoodmodelId(), true);
//			if (characterGoods == null)
//				return;
//
//			if (!characterGoodController.combineDeleteCailiao(characterGoods, true, 1, equipmentPlayconfig.getShenjieshi(), equipmentPlayconfig.getShenjieshiNum())) {
//				logger.warn("包裹管理器计算出错");
//				return;
//			}
//			CharacterPropertyManager.changeCopper(character, -equipmentPlayconfig.getSheliziCopper(), CopperAction.CONSUME);
//			characterGoods.setPosition(equipment.getPosition());
//			characterGoods.setTupoCnt(0);
//			characterGoodController.deleteCharacterGoods(equipment);
//			characterGoodController.addGoodsToBag(characterGoods);
//			getCharacter().sendMsg(new HushenfuJinJieMsg52116(1, characterGoods.getPosition()));
//			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20062, equipmentPlayconfig.getShenjieshiNum() + "", equipmentPlayconfig.getSheliziCopper() + ""));
//
//			character.getMyCharacterAchieveCountManger().getEquipmentCount().hechengEquipment(characterGoods);
//
//		}
//	}
//}
