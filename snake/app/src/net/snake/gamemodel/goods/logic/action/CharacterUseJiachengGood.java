package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.ai.formula.CharacterFormula;
import net.snake.api.IUseItemListener;
import net.snake.consts.EffectType;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.chest.logic.ChestTools;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.response.CoolingTimeMsg50142;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

/**
 * 使用加成物品 对角色属性产生影响 // 【1,增加攻击力2,增加防御力3,增加暴击4,增加闪避5,增加攻击速度6,增加移动速度7,增加生命上限值8,增加内力上限值9 ,增加体力上限值10,增加潜能点个数 11 ,增加等级 12,增加经验13,增加坐骑经验14,增加真气储量15,增加战场声望16,恢复生命值17,恢复内力值18,
 * 恢复体力值19,恢复坐骑活力20,解除全部负面状态21,双倍经验获得22,双倍真气储量,64肉身
 * 
 * 
 */
public class CharacterUseJiachengGood implements UseGoodAction {
	private static final Logger logger = Logger.getLogger(CharacterUseJiachengGood.class);
	private Goodmodel gm;

	public CharacterUseJiachengGood(Goodmodel gm) {
		this.gm = gm;
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int position, List<IUseItemListener> listeners) {
		boolean isSendFailMsg = true;

		if (position < 1) {
			CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(goodId);
			if (cg == null) {
				return false;
			} else {
				position = cg.getPosition();
				isSendFailMsg = false;
			}
		}
		if (gm.getLimitGrade() != null && gm.getLimitGrade() > character.getGrade()) {
			if (isSendFailMsg) {
				//
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1019, gm.getLimitGrade() + ""));
			}
			return false;
		}
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) position);
		if (cg == null) {
			return false;
		}
		if (cg.isInTrade()) {
			if (isSendFailMsg) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			}
			return false;
		}
		if (cg.getLastDate() != null) {
			if (System.currentTimeMillis() > cg.getLastDate().getTime()) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 877));
				}
				return false;
			}
		}
		byte type = gm.getDrugType().byteValue();
		int value = gm.getDrugValue();
		switch (type) {
		case 1:
			if (character.getPropertyAdditionController().getExtraAttack() >= MaxLimit.ATTACT_MAX) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 799));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			boolean b = character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1);
			if (b) {
				CharacterPropertyManager.changeAttack(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 2:
			if (character.getPropertyAdditionController().getExtraDefend() >= MaxLimit.DEFENCE_MAX) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 800));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			boolean bb = character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1);
			if (bb) {
				CharacterPropertyManager.changeDefence(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 3:
			if (character.getPropertyAdditionController().getExtraCrt() >= MaxLimit.CRT_MAX) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 801));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeCrt(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 4:// 4,增加闪避
			if (character.getPropertyAdditionController().getExtraDodge() >= MaxLimit.DODGE_MAX) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 802));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeDodge(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 5:// 5,增加攻击速度
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeAtkColdtime(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 6:// 6,增加移动速度
			if (character.getPropertyAdditionController().getExtraMoveSpeed() >= MaxLimit.MOVESPEED_MAX) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 803));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeMoveSpeed(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 7:// 7,增加生命上限值
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeMaxHp(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 8:// 8,增加内力上限值

			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeMaxMp(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 9:// 9,增加体力上限值
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeMaxSp(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 10:// 10,增加潜能点个数
			if (character.getPotential() >= MaxLimit.POTENTIAL_MAX) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 804));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changePotential(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 11:
			long _maxExpe = character.getNextExperience();
			long addExp = _maxExpe - character.getNowHp();
			try {
				if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
					CharacterFormula.experienceProcess(character, addExp + 10);
				}
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			character.sendMsg(new CoolingTimeMsg50142(goodId));
			return true;
		case 12:// 12,增加经验==============
			try {
				if (isCoolingTime(character, isSendFailMsg)) {
					return false;
				}
				if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
					CharacterFormula.experienceProcess(character, value);
					character.sendMsg(new CoolingTimeMsg50142(goodId));
				}
				return true;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return false;
		case 14:// 14,增加真气储量
			int size = listeners.size();
			for (int i = 0; i < size; i++) {
				boolean goon = listeners.get(i).beforeUseItem(character, goodId, position, gm);
				if (!goon) {
					return false;
				}
			}

			if (character.getZhenqi() >= MaxLimit.ZHENQI_MAX) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 806));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}

			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeZhenqi(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}

			for (int i = 0; i < size; i++) {
				listeners.get(i).onUseItem(character, gm);
			}
			return true;
		case 15:// 15,增加战场声望
			if (character.getPrestige() >= MaxLimit.PRESTIGE_MAX) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 807));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changePrestige(character, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 16:// 16,恢复生命值
			if (character.getNowHp() >= character.getPropertyAdditionController().getExtraMaxHp()) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 808));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeNowHp(character, value);
				recoverSpousePhysique(character, EffectType.hp, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 17:// 17,恢复内力值
			if (character.getEffectController().isMpOver()) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 809));
				}
				return false;
			}
			if (character.getNowMp() >= character.getPropertyAdditionController().getExtraMaxMp()) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 810));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeNowMp(character, value);
				recoverSpousePhysique(character, EffectType.mp, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 18:// 18,恢复体力值
			if (character.getEffectController().isSpOver()) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 811));
				}
				return false;
			}

			if (character.getNowSp() >= character.getPropertyAdditionController().getExtraMaxSp()) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 812));
				}
				return false;
			}
			if (isCoolingTime(character, isSendFailMsg)) {
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				CharacterPropertyManager.changeNowSp(character, value);
				recoverSpousePhysique(character, EffectType.sp, value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 19:// 19,恢复坐骑活力
			Horse myHorse = character.getCharacterHorseController().getCurrentRideHorse();
			if (myHorse == null) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 813));
				}
				return false;
			}
			if (myHorse.getCharacterHorse().getLivingness() >= myHorse.getCharacterHorse().getLivingnessMax()) {
				if (isSendFailMsg) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 814));
				}
				return false;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				myHorse.getHorseRaiseManager().changeLivingness(value);
				character.sendMsg(new CoolingTimeMsg50142(goodId));
			}
			return true;
		case 61:// 61,松果友好丹

			if (character.getMyChestManager().getChestCount().getChestUseCount().intValue() != 0) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60033));
				return false;
			}

			if (character.getMyChestManager().getChestCount().getChesttypeExchangeCount() == -1) {// 金松果重置验证
				if (!character.getMyChestManager().getYanZhengJinSongGuo()) {
					return false;
				}
			}

			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				int count = 0;
				count = character.getMyChestManager().getChestCount().getChesttypeExchangeCount();
				character.getMyChestManager().getChestCount().setChesttypeExchangeCount(count + value);
				character.getMyChestManager().getChestCount().setChestUseCount(1);
				int countExchange = 0;
				countExchange = character.getMyChestManager().getChestCount().getChesttypeExchangeCount();
				int exchangeCiShu = countExchange / 1500;
				ChestTools.addChestExchange(countExchange, character.getMyChestManager().getChestCount(), exchangeCiShu);

				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60034));
			}
			return true;
		case 64:
			// HeroDujieData dujie = character.getDujieCtrlor().getHeroDujieData();
			// dujie.setRoushen(value+dujie.getRoushen());
			if (character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
				ServerResponse response = character.getDujieCtrlor().increaseRoushen(value);
				if (response != null) {
					character.sendMsg(response);
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,60057,value));
				}
				return true;
			}
		default:
			return false;
		}
	}

	/**
	 * 返回true表示不可可以使用
	 * 
	 * @param character
	 * @return
	 */
	private boolean isCoolingTime(Hero character, boolean isSendMsg) {
		boolean b = character.getCharacterGoodController().getCoolingTimeManager().isUseGoodsAndUpdate(gm);
		if (!b) {
			if (isSendMsg) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 820));
			}
		}
		return !b;
	}

	/**
	 * 并蒂连枝 结婚后，当自己的配偶与自己处于同一地图时，配偶使用药品时自己也得到分享。 药品效果分享：仅限恢复生命，恢复内力，恢复体力的药品。 分享百分比 = 该玉佩上绑定的并蒂连枝技能的消耗值 / 100 Skill表f_depletion_value 字段 药品分享并不会减少自己使用药品时的药效，而是额外分一部分给配偶。
	 * 
	 * @param character
	 * @param type
	 * @param value
	 */
	private void recoverSpousePhysique(Hero character, int type, int value) {
		RoleWedingManager rwMgr = character.getMyFriendManager().getRoleWedingManager();
		if (rwMgr != null && rwMgr.isWedding()) {
			Scene scene = character.getSceneRef();
			Hero wedder = scene.getSceneObj(SceneObj.SceneObjType_Hero, rwMgr.getWedderId());
			if (wedder != null) {
				int bingtilianzhi = rwMgr.getFuqiWeddingRing().getSkillB();
				CharacterSkill characterskill = character.getSkillManager().getCharacterSkillById(bingtilianzhi);
				if (characterskill != null) {
					double v = value * characterskill.getSkill().getDepletionValue() / 100f;
					switch (type) {
					case EffectType.hp:
						CharacterPropertyManager.changeNowHp(wedder, (int) v);
						break;
					case EffectType.mp:
						CharacterPropertyManager.changeNowMp(wedder, (int) v);
						break;
					case EffectType.sp:
						CharacterPropertyManager.changeNowSp(wedder, (int) v);
						break;
					default:
						break;
					}
				}
			}
		}
	}
}
