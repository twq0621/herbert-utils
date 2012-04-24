package net.snake.gamemodel.skill.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.snake.GameServer;
import net.snake.ai.formula.AttackFormula;
import net.snake.commons.GenerateProbability;
import net.snake.consts.BuffId;
import net.snake.consts.CopperAction;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;
import net.snake.gamemodel.skill.bean.HiddenWeapons;
import net.snake.gamemodel.skill.persistence.CharacterHiddenWeaponManager;
import net.snake.gamemodel.skill.persistence.HiddenWeaponsManager;
import net.snake.gamemodel.skill.response.hiddenweapon.HiddenPinJingMsg52012;
import net.snake.gamemodel.skill.response.hiddenweapon.HiddenPoMsg52014;
import net.snake.gamemodel.skill.response.hiddenweapon.OpenHiddenMsg52006;
import net.snake.gamemodel.skill.response.hiddenweapon.OpenHiddenPropsMsg52020;

/**
 * 暗器系统
 * 
 * @author serv_dev
 * 
 */
public class CharacterHiddenWeaponController extends CharacterController {
	private static final int maxGrade = 7;
	private CharacterHiddenWeapon characterHiddenWeapon;

	public CharacterHiddenWeaponController(Hero character) {
		super(character);
	}

	public void initData() {
		characterHiddenWeapon = CharacterHiddenWeaponManager.getInstance().queryCharacterHiddenWeaponByCharacterId(character.getId());
		// TODO
		if (characterHiddenWeapon != null) {
			int tmpGrade = HiddenWeaponsManager.getInstance().getMaxGrade(characterHiddenWeapon.getGrade() - 1);
			if (characterHiddenWeapon.getXiuGrade() <= tmpGrade) {
				characterHiddenWeapon.setXiuGrade(tmpGrade + 1);
				CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, characterHiddenWeapon);
			}
			reloadHiddenWeaponPropertyAddition();
		}
	}

	public void reloadHiddenWeaponPropertyAddition() {
		if (characterHiddenWeapon != null && characterHiddenWeapon.getHiddenWeapons().getGrade() == maxGrade) {
			if (characterHiddenWeapon.getIsOpenHiddenProps()) {
				character.getPropertyAdditionController().addChangeListener(characterHiddenWeapon);
			}
		}
	}

	public void setCharacterHiddenWeapon(CharacterHiddenWeapon characterHiddenWeapon) {
		this.characterHiddenWeapon = characterHiddenWeapon;
	}

	public List<VisibleObject> filter(List<VisibleObject> voInRegion) {
		List<VisibleObject> _voInRegion = new ArrayList<VisibleObject>();
		int i = 0;
		for (VisibleObject vo : voInRegion) {

			if (vo.isZeroHp())
				continue;

			if (AttackFormula.atkIsEnable2(character.getX(), character.getY(), vo.getX(), vo.getY(), characterHiddenWeapon.getAttackDis())) {// 在效果的范围内
				if (i >= characterHiddenWeapon.getAttackTargets())
					break;

				if (!characterHiddenWeapon.triblv(character, vo)) {
					continue;
				}
				_voInRegion.add(vo);
				i++;
			}
		}
		return _voInRegion;
	}

	public CharacterHiddenWeapon getCharacterHiddenWeapon() {
		return characterHiddenWeapon;
	}

	public void openHidden(boolean hidden) {
		if (characterHiddenWeapon != null) {
			characterHiddenWeapon.setIsUse(hidden);
			CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, characterHiddenWeapon);
			character.sendMsg(new OpenHiddenMsg52006(characterHiddenWeapon.getIsUse()));
		}
	}

	/**
	 * 使用一个提速石
	 */
	public void useOneStoneUpMastery() {
		if (characterHiddenWeapon != null) {
			int maxMastery = characterHiddenWeapon.getHiddenWeapons().getMastery();
			int nowMastery = characterHiddenWeapon.getNowMastery();
			if (maxMastery == nowMastery) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 562));
				return;
			}
			if (!character.getCharacterGoodController().isEnoughGoods(GoodItemId.AQTSS, 1)) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 563));
				return;
			}

			characterHiddenWeapon.addMastery(character, 50);
			CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, characterHiddenWeapon);
			character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.AQTSS, 1);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 564, characterHiddenWeapon.getHiddenWeapons().getNameI18n(), 50 + "", 1 + ""));
		}
	}

	/**
	 * 使用所有提速石
	 */
	public void useAllStoneUpMastery() {
		if (characterHiddenWeapon != null) {
			int maxMastery = characterHiddenWeapon.getHiddenWeapons().getMastery();
			int nowMastery = characterHiddenWeapon.getNowMastery();
			if (maxMastery == nowMastery) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 562));
				return;
			}

			int useStone = (maxMastery - nowMastery) % 50 > 0 ? ((maxMastery - nowMastery) / 50 + 1) : (maxMastery - nowMastery) / 50;
			int hasStone = character.getCharacterGoodController().getBagGoodsCountByModelID(GoodItemId.AQTSS);
			if (hasStone <= useStone) {
				useStone = hasStone;
			}

			if (useStone == 0 || !character.getCharacterGoodController().isEnoughGoods(GoodItemId.AQTSS, useStone)) {

				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 563));
				return;
			}

			characterHiddenWeapon.addMastery(character, 50 * useStone);
			CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, characterHiddenWeapon);
			character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.AQTSS, useStone);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 564, characterHiddenWeapon.getHiddenWeapons().getNameI18n(), (50 * useStone) + "", useStone + ""));
		}
	}

	public void po(boolean shenqi) {
		if (characterHiddenWeapon == null) {
			return;
		}
		int xiuGrade = characterHiddenWeapon.getHiddenWeapons().getXiuGrade();
		int maxGrade = HiddenWeaponsManager.getInstance().getMaxGrade(characterHiddenWeapon.getGrade());
		int maxMastery = characterHiddenWeapon.getHiddenWeapons().getMastery();
		int nowMastery = characterHiddenWeapon.getNowMastery();
		// boolean freeTuPoActive = LinshiActivityManager.getInstance().isTimeByLinshiActivityID(45);//暗器突破瓶颈免熟练度
		boolean freeTuPoActive = character.getEffectController().getBufferInBufferListByBufferId(BuffId.anqitupomianshuliandu) == null ? false : true;
		if (freeTuPoActive) {
			int currentGrade = characterHiddenWeapon.getGrade();
			final HiddenWeapons hideHiddenWeapons = HiddenWeaponsManager.getInstance().getHiddenWeaponsByGrade(currentGrade + 1, maxGrade + 1);
			if (hideHiddenWeapons == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 561));
				return;
			}
			HiddenWeapons currentHideHiddenWeapons = HiddenWeaponsManager.getInstance().getHiddenWeaponsByGrade(currentGrade, maxGrade);
			int needCopper = currentHideHiddenWeapons.getUpgradeNeedCopper();
			if (character.getCopper() < needCopper) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 565));
				return;
			}
			int needZhenqi = currentHideHiddenWeapons.getUpgradeNeedZhenqi();
			if (character.getZhenqi() < needZhenqi) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 566));
				return;
			}

			boolean activiting = LinshiActivityManager.getInstance().isTimeByLinshiActivityID(15);
			if (shenqi && activiting) {// 如果是在活动中【大小礼包很给力】
				if (character.getCharacterGoodController().isEnoughGoods(GoodItemId.SQMP, 1)) {
					character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.SQMP, 1);
				} else {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20077));
					return;
				}
			} else {
				ArrayList<CharacterGoods> goods = new ArrayList<CharacterGoods>();
				String needGoodsStr = currentHideHiddenWeapons.getUpgradeNeedGoods();
				if (needGoodsStr != null && !"".equals(needGoodsStr.trim())) {
					String[] needGoodsArr = needGoodsStr.split(";");
					for (int i = 0; i < needGoodsArr.length; i++) {
						String _needGoodsStr = needGoodsArr[i];
						if (_needGoodsStr != null && !"".equals(_needGoodsStr.trim())) {
							String[] _needGoodsArr = _needGoodsStr.split(",");
							if (_needGoodsArr.length == 2) {
								CharacterGoods cg = new CharacterGoods();
								cg.setGoodmodelId(Integer.parseInt(_needGoodsArr[0]));
								cg.setCount(Integer.parseInt(_needGoodsArr[1]));

								if (character.getCharacterGoodController().isEnoughGoods(cg.getGoodmodelId(), cg.getCount())) {
									goods.add(cg);
								} else {
									character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 567, "" + GoodmodelManager.getInstance().get(cg.getGoodmodelId()).getNameI18n()));
									return;
								}
							}
						}
					}
				}

				if (!goods.isEmpty()) {
					for (Iterator<CharacterGoods> iterator = goods.iterator(); iterator.hasNext();) {
						CharacterGoods characterGoods = iterator.next();
						character.getCharacterGoodController().deleteGoodsFromBag(characterGoods.getGoodmodelId(), characterGoods.getCount());
					}
				}
			}
			CharacterPropertyManager.changeCopper(character, -needCopper, CopperAction.CONSUME);
			CharacterPropertyManager.changeZhenqi(character, -needZhenqi);
			boolean success = false;

			if (!success) {
				success = characterHiddenWeapon.getTupoCnt() >= currentHideHiddenWeapons.getUpgradeMaxCnt()
						|| (characterHiddenWeapon.getTupoCnt() >= currentHideHiddenWeapons.getUpgradeMinCnt() && GenerateProbability.defaultIsGenerate(currentHideHiddenWeapons
								.getUpgradeTribRealLv()));
			}
			// 概率判定
			if (success) {
				characterHiddenWeapon.setGrade(currentGrade + 1);
				// characterHiddenWeapon.setXiuGrade(xiuGrade + 1);
				characterHiddenWeapon.setXiuGrade(maxGrade + 1);
				characterHiddenWeapon.setHiddenWeapons(hideHiddenWeapons);
				characterHiddenWeapon.setNowMastery(0);
				characterHiddenWeapon.setTupoCnt(0);
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 568));
				character.sendMsg(new HiddenPoMsg52014());

				/*
				 * 暗器系统突破瓶颈后增加屏幕中央的滚动公告提示： 恭喜【XXXX玩家名字】玩家成功突破暗器修炼瓶颈， 掌握到了【XXXX突破后进阶至的暗器名字】暗器的使用门路
				 */
				//
				GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 569, character.getViewName(), hideHiddenWeapons.getNameI18n()));
				characterHiddenWeapon.addMastery(character, nowMastery);
				reloadHiddenWeaponPropertyAddition();

			} else {// 失败
				characterHiddenWeapon.setTupoCnt(characterHiddenWeapon.getTupoCnt() + 1);
				characterHiddenWeapon.setLuckValue(characterHiddenWeapon.getLuckValue() + GenerateProbability.randomIntValue(1, 5));
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 570));

			}
			sendInitMsg();
			CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, characterHiddenWeapon);

		} else {
			if (xiuGrade == maxGrade && maxMastery == nowMastery) {
				int currentGrade = characterHiddenWeapon.getGrade();
				final HiddenWeapons hideHiddenWeapons = HiddenWeaponsManager.getInstance().getHiddenWeaponsByGrade(currentGrade + 1, xiuGrade + 1);
				if (hideHiddenWeapons == null) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 561));
					return;
				}
				HiddenWeapons currentHideHiddenWeapons = HiddenWeaponsManager.getInstance().getHiddenWeaponsByGrade(currentGrade, xiuGrade);
				int needCopper = currentHideHiddenWeapons.getUpgradeNeedCopper();
				if (character.getCopper() < needCopper) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 565));
					return;
				}

				int needZhenqi = currentHideHiddenWeapons.getUpgradeNeedZhenqi();
				if (character.getZhenqi() < needZhenqi) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 566));
					return;
				}

				boolean activiting = LinshiActivityManager.getInstance().isTimeByLinshiActivityID(15);
				if (shenqi && activiting) {// 如果是在活动中【大小礼包很给力】
					if (character.getCharacterGoodController().isEnoughGoods(GoodItemId.SQMP, 1)) {
						character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.SQMP, 1);
					} else {
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20077));
						return;
					}
				} else {
					ArrayList<CharacterGoods> goods = new ArrayList<CharacterGoods>();
					String needGoodsStr = currentHideHiddenWeapons.getUpgradeNeedGoods();
					if (needGoodsStr != null && !"".equals(needGoodsStr.trim())) {
						String[] needGoodsArr = needGoodsStr.split(";");
						for (int i = 0; i < needGoodsArr.length; i++) {
							String _needGoodsStr = needGoodsArr[i];
							if (_needGoodsStr != null && !"".equals(_needGoodsStr.trim())) {
								String[] _needGoodsArr = _needGoodsStr.split(",");
								if (_needGoodsArr.length == 2) {
									CharacterGoods cg = new CharacterGoods();
									cg.setGoodmodelId(Integer.parseInt(_needGoodsArr[0]));
									cg.setCount(Integer.parseInt(_needGoodsArr[1]));

									if (character.getCharacterGoodController().isEnoughGoods(cg.getGoodmodelId(), cg.getCount())) {
										goods.add(cg);
									} else {
										character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 567, "" + GoodmodelManager.getInstance().get(cg.getGoodmodelId()).getNameI18n()));
										return;
									}
								}
							}
						}
					}

					if (!goods.isEmpty()) {
						for (Iterator<CharacterGoods> iterator = goods.iterator(); iterator.hasNext();) {
							CharacterGoods characterGoods = iterator.next();
							character.getCharacterGoodController().deleteGoodsFromBag(characterGoods.getGoodmodelId(), characterGoods.getCount());
						}
					}
				}
				CharacterPropertyManager.changeCopper(character, -needCopper, CopperAction.CONSUME);
				CharacterPropertyManager.changeZhenqi(character, -needZhenqi);

				// 活动的时候免费突破一次三阶以上的暗器
				boolean success = false;

				if (!success) {
					success = characterHiddenWeapon.getTupoCnt() >= currentHideHiddenWeapons.getUpgradeMaxCnt()
							|| (characterHiddenWeapon.getTupoCnt() >= currentHideHiddenWeapons.getUpgradeMinCnt() && GenerateProbability.defaultIsGenerate(currentHideHiddenWeapons
									.getUpgradeTribRealLv()));
				}

				// 概率判定
				if (success) {
					characterHiddenWeapon.setGrade(currentGrade + 1);
					characterHiddenWeapon.setXiuGrade(xiuGrade + 1);
					characterHiddenWeapon.setHiddenWeapons(hideHiddenWeapons);
					characterHiddenWeapon.setNowMastery(0);
					characterHiddenWeapon.setLuckValue(0);
					characterHiddenWeapon.setTupoCnt(0);
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 568));
					character.sendMsg(new HiddenPoMsg52014());

					/*
					 * 暗器系统突破瓶颈后增加屏幕中央的滚动公告提示： 恭喜【XXXX玩家名字】玩家成功突破暗器修炼瓶颈， 掌握到了【XXXX突破后进阶至的暗器名字】暗器的使用门路
					 */
					//
					GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 569, character.getViewName(), hideHiddenWeapons
							.getNameI18n()));
					characterHiddenWeapon.addMastery(character, nowMastery);
					reloadHiddenWeaponPropertyAddition();

				} else {// 失败
					characterHiddenWeapon.setTupoCnt(characterHiddenWeapon.getTupoCnt() + 1);
					characterHiddenWeapon.setLuckValue(characterHiddenWeapon.getLuckValue() + GenerateProbability.randomIntValue(1, 5));
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 570));

				}
				sendInitMsg();
				CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, characterHiddenWeapon);
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 571));
			}
		}
	}

	public void openHiddenProps(int method) {
		if (characterHiddenWeapon != null) {
			if (characterHiddenWeapon.getIsOpenHiddenProps()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20135));
				character.sendMsg(new OpenHiddenPropsMsg52020(false));
				return;
			}

			if (characterHiddenWeapon.getHiddenWeapons().getGrade() != maxGrade) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20134));
				character.sendMsg(new OpenHiddenPropsMsg52020(false));
				return;
			}

			if (method == 1) {// 活动打开
				if (!LinshiActivityManager.getInstance().isTimeByLinshiActivityID(54)) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1085));
					character.sendMsg(new OpenHiddenPropsMsg52020(false));
					return;
				} else {
					String needGoodsStr = characterHiddenWeapon.getHiddenWeapons().getActivityOpenHiddenProps();
					if (needGoodsStr != null) {
						String[] ngstr = needGoodsStr.split(";");
						// 验证数量
						for (int i = 0; i < ngstr.length; i++) {
							if (ngstr[i] != null && !"".equals(ngstr[i])) {
								String[] $ngstr = ngstr[i].split("\\*");
								if ($ngstr.length >= 2) {
									int goodId = Integer.parseInt($ngstr[0]);
									int goodnum = Integer.parseInt($ngstr[1]);
									if (!character.getCharacterGoodController().isEnoughGoods(goodId, goodnum)) {
										character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 567, GoodmodelManager.getInstance().get(goodId).getName()));
										character.sendMsg(new OpenHiddenPropsMsg52020(false));
										return;
									}
								}
							}
						}

						// 收回
						for (int i = 0; i < ngstr.length; i++) {
							if (ngstr[i] != null && !"".equals(ngstr[i])) {
								String[] $ngstr = ngstr[i].split("\\*");
								if ($ngstr.length >= 2) {
									int goodId = Integer.parseInt($ngstr[0]);
									int goodnum = Integer.parseInt($ngstr[1]);
									character.getCharacterGoodController().deleteGoodsFromBag(goodId, goodnum);
								}
							}
						}

						characterHiddenWeapon.setIsOpenHiddenProps(true);
						CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, characterHiddenWeapon);
						character.sendMsg(new OpenHiddenPropsMsg52020(characterHiddenWeapon.getIsOpenHiddenProps()));
						reloadHiddenWeaponPropertyAddition();

					}
				}
			} else {
				String needGoodsStr = characterHiddenWeapon.getHiddenWeapons().getCommonOpenHiddenProps();
				if (needGoodsStr != null) {
					String[] ngstr = needGoodsStr.split(";");
					// 验证数量
					for (int i = 0; i < ngstr.length; i++) {
						if (ngstr[i] != null && !"".equals(ngstr[i])) {
							String[] $ngstr = ngstr[i].split("\\*");
							if ($ngstr.length >= 2) {
								int goodId = Integer.parseInt($ngstr[0]);
								int goodnum = Integer.parseInt($ngstr[1]);
								if (!character.getCharacterGoodController().isEnoughGoods(goodId, goodnum)) {
									character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 567, GoodmodelManager.getInstance().get(goodId).getName()));
									character.sendMsg(new OpenHiddenPropsMsg52020(false));
									return;
								}
							}
						}
					}

					// 收回
					for (int i = 0; i < ngstr.length; i++) {
						if (ngstr[i] != null && !"".equals(ngstr[i])) {
							String[] $ngstr = ngstr[i].split("\\*");
							if ($ngstr.length >= 2) {
								int goodId = Integer.parseInt($ngstr[0]);
								int goodnum = Integer.parseInt($ngstr[1]);
								character.getCharacterGoodController().deleteGoodsFromBag(goodId, goodnum);
							}
						}
					}

					characterHiddenWeapon.setIsOpenHiddenProps(true);
					CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, characterHiddenWeapon);
					character.sendMsg(new OpenHiddenPropsMsg52020(characterHiddenWeapon.getIsOpenHiddenProps()));
					reloadHiddenWeaponPropertyAddition();
				}
			}
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20136));
			character.sendMsg(new OpenHiddenPropsMsg52020(false));
		}
	}

	public void clearLuck() {
		if (characterHiddenWeapon != null) {
			characterHiddenWeapon.setLuckValue(0);
			CharacterHiddenWeaponManager.getInstance().asynUpdateCharacterHiddenWeapon(character, characterHiddenWeapon);
			sendInitMsg();
		}
	}

	public void sendInitMsg() {
		if (characterHiddenWeapon == null)
			return;
//		character.sendMsg(new CharacterHiddenWeaponInitMsg52008(true, characterHiddenWeapon));
	}

	/**
	 * 新手提示
	 */
	public void sendXinshoutishiMsg() {
		if (characterHiddenWeapon != null) {
			if (characterHiddenWeapon.getNowMastery() >= characterHiddenWeapon.getHiddenWeapons().getMastery()
					&& characterHiddenWeapon.getXiuGrade() >= HiddenWeaponsManager.getInstance().getMaxGrade(characterHiddenWeapon.getGrade())
					&& HiddenWeaponsManager.getInstance().getMaxGrade(characterHiddenWeapon.getGrade() + 1) != 0) {
				character.sendMsg(new HiddenPinJingMsg52012());
			}
		}
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}

}
