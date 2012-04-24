package net.snake.gamemodel.equipment.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.ai.fight.response.SkillAddiGradeMsg10296;
import net.snake.commons.GenerateProbability;
import net.snake.consts.ClientConfig;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.GoodItemId;
import net.snake.consts.Position;
import net.snake.consts.TakeMethod;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.equipment.response.EquipFrayMsg10170;
import net.snake.gamemodel.equipment.response.repair.NpcRepairEquipmentResponse;
import net.snake.gamemodel.fabao.response.RideFabaoResponse;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.logic.container.IContainer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.logic.PropertyAdditionController;
import net.snake.gamemodel.hero.response.CharacterAttributesMsg49992;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.BaseSkillManager;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.buff.Buff;
import net.snake.gamemodel.skill.logic.buff.equip.AllBornSuit;
import net.snake.gamemodel.skill.logic.buff.equip.AllPurpleSuit;
import net.snake.gamemodel.skill.logic.buff.equip.AllStarSuit;
import net.snake.gamemodel.skill.logic.buff.equip.AllStoneSuit;
import net.snake.gamemodel.skill.logic.buff.equip.EqualGradeSuit;

/**
 * 装备管理器
 * 
 * @author serv_dev
 * 
 */
public class EquipmentController extends CharacterController {
//	private static final Logger logger = Logger.getLogger(EquipmentController.class);
	/** 全等级 */
	private Buff allGrade;//
	/** 全星 */
	private Buff allStar;//
	/** 全紫 */
	private Buff allPurple;//
	/** 全满星石头 */
	private Buff allStone;//
	/** 满天生 */
	private Buff allBorn;// E
	private boolean isShelongHushenFu = false;
	/** 这里面只存放人物的12件装备 */
	private List<CharacterGoods> characterGoodsList = new ArrayList<CharacterGoods>();//
	private Map<Integer, Integer> addSkillGradeMaps = new HashMap<Integer, Integer>();// skillid,addskillgrade

	public boolean isShelongHushenFu() {
		return isShelongHushenFu;
	}

	public void setShelongHushenFu(boolean isShelongHushenFu) {
		this.isShelongHushenFu = isShelongHushenFu;
	}

	public void destroy() {
		if (characterGoodsList != null) {
			characterGoodsList.clear();
			characterGoodsList = null;
		}
		if (addSkillGradeMaps != null) {
			addSkillGradeMaps.clear();
			addSkillGradeMaps = null;
		}
		addSkillGradeMaps = null;
		allGrade = null;
		allStar = null;
		allPurple = null;
		allStone = null;
		allBorn = null;
	}

	/**
	 * 是否是全满天生装备
	 */
	public boolean isAllBornEquip() {
		return allBorn != null ? true : false;
	}

	public int getskillAddGrade(int skillid) {
		if (addSkillGradeMaps == null)
			return 0;
		Integer grade = addSkillGradeMaps.get(skillid);
		return grade == null ? 0 : grade;
	}

	public void addskillgrade(int skillid, int grade) {
		if (addSkillGradeMaps == null)
			return;
		Integer _grade = addSkillGradeMaps.get(skillid);
		if (_grade != null) {
			addSkillGradeMaps.put(skillid, _grade + grade);
		} else {
			addSkillGradeMaps.put(skillid, grade);
		}

		passiveskill(skillid, grade);
	}

	private void passiveskill(int skillid, int grade) {
		BaseSkillManager<Hero> skillManager = getCharacter().getSkillManager();
		CharacterSkill characterskill = skillManager.getCharacterSkillById(skillid);
		if (characterskill != null) {
			if (characterskill.getSkill().isPassive()) {
				skillManager.addUnPassiveGrade(grade, characterskill);
			}
		}
	}

	public void takeEquitment(CharacterGoods characterGoods) {
		if (characterGoods == null)
			return;
		if (characterGoods.getGoodModel().getBinding() == 3 && characterGoods.getBind() != 1) {
			characterGoods.setBind(CommonUseNumber.byte1);
			character.getCharacterGoodController().updateCharacterGoods(characterGoods);
		}
		if (characterGoods.getPosition() >= Position.POSTION_WUQI && characterGoods.getPosition() <= Position.POSTION_TESHU) {
			characterGoodsList.add(characterGoods);
			checkSuit();
		}

		character.sendMsg(new CharacterAttributesMsg49992(character));
	}

	public void repairBodyEquips() {
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		for (CharacterGoods equipment : characterGoodController.getBodyGoodsList()) {
			if (equipment.getMaxDurability() <= 0 || equipment.getMaxDurability() <= equipment.getCurrDurability()) {
				continue;
			}
			if (equipment.getCurrDurability() == 0) {
				changeProperty(getCharacter(), equipment, TakeMethod.on);
			}
			equipment.setCurrDurability(equipment.getMaxDurability());
			characterGoodController.updateCharacterGoods(equipment);
		}
	}

	public void offEquitment(CharacterGoods characterGoods) {
		if (characterGoods == null) {
			return;
		}
		characterGoodsList.remove(characterGoods);
		checkSuit();
		if (characterGoods.getGoodModel().getPosition() == Position.POSTION_TESHU) {
			character.getEyeShotManager().sendMsg(new RideFabaoResponse(character.getId(), CommonUseNumber.byte0, characterGoods.getGoodmodelId()));
			return;
		}

		if (characterGoods.getGoodmodelId() == GoodItemId.SL_FUSHENFU) {
			setShelongHushenFu(false);
			character.getCharacterSkillManager().sendUpdateSkillShowMsg();
		} else {
			Collection<Integer> col = new ArrayList<Integer>(addSkillGradeMaps.keySet());
			if (!addSkillGradeMaps.isEmpty()) {
				for (Iterator<Integer> iterator = addSkillGradeMaps.keySet().iterator(); iterator.hasNext();) {
					int skillid = iterator.next();
					passiveskill(skillid, -addSkillGradeMaps.get(skillid));
				}
				addSkillGradeMaps.clear();
				// 更新技能相关消息
			}

			for (Iterator<Integer> iterator = col.iterator(); iterator.hasNext();) {
				Integer skillid = iterator.next();
				int addgrade = character.getCharacterSkillManager().getskilladdGrade(skillid);
				character.sendMsg(new SkillAddiGradeMsg10296(skillid, addgrade));
			}
		}
		character.sendMsg(new CharacterAttributesMsg49992(character));
	}

	/**
	 * 调用此方法，系统将判断金额是否足够这些位置上的物品修理 足够的话扣除金额并设置当前耐久为最大耐久值 普通修理的话会掉耐久的哦。
	 * 
	 * 
	 * @param num
	 *            修理的数量
	 * @param positions
	 *            修理的位置
	 * @param method
	 *            修理方式
	 */
	public void repair(byte num, short positions[], byte method) {

		CharacterGoodController characterGoodController = getCharacter().getCharacterGoodController();
		List<CharacterGoods> equipments = new ArrayList<CharacterGoods>();
		long needMoney = 0l;
		NpcRepairEquipmentResponse response = null;
		for (byte i = 0; i < num; i++) {
			CharacterGoods characterGoods = characterGoodController.getGoodsByPositon(positions[i]);
			Goodmodel goodModel = characterGoods.getGoodModel();
			if (goodModel == null || !(goodModel.isEquipment())) {
				response = new NpcRepairEquipmentResponse(40021);
				getCharacter().sendMsg(response);
				return;
			}

			if (goodModel.getDurability() == -1)
				continue;

			equipments.add(characterGoods);

			// 修理费用=（装备耐久-当前装备耐久）×修理单价
			needMoney = needMoney + method == 0 ? AppEventCtlor.getInstance().getEvtEquipmentFormula().repairEquiement(characterGoods) : AppEventCtlor.getInstance()
					.getEvtEquipmentFormula().specialRepairEquiement(characterGoods);
		}
		// 城主税收
		int tax = 0;
		if (getCharacter().getScene() == ClientConfig.Scene_Xianjing) {
			tax = (int) (FactionCityManager.getInstance().getTaxRate() * needMoney);
		}

		int currCopperMoney = getCharacter().getCopper();// 得到玩家当前金钱
		if (currCopperMoney < needMoney + tax) {
			response = new NpcRepairEquipmentResponse(40022);
			getCharacter().sendMsg(response);
			return;
		}
		// int size = equipments.size();
		// TODO
		for (CharacterGoods characterGoods : characterGoodController.getBodyGoodsList()) {

			if (method == 0) {
				characterGoods.setMaxDurability(characterGoods.getMaxDurability() - AppEventCtlor.getInstance().getEvtEquipmentFormula().repairAffectMaxDurability(characterGoods));// 对最大耐久度的影响
			}

			if (characterGoods.getMaxDurability() > 0 && characterGoods.getCurrDurability() == 0) {
				changeProperty(getCharacter(), characterGoods, TakeMethod.on);
			}
			characterGoods.setCurrDurability(characterGoods.getMaxDurability());
			characterGoodController.updateCharacterGoods(characterGoods);
		}
		CharacterPropertyManager.changeCopper(getCharacter(), -(int) (needMoney + tax), CopperAction.CONSUME);
		// 增加税收记录
		FactionCityManager.getInstance().addTaxs(tax);
		response = new NpcRepairEquipmentResponse(positions, getCharacter().getCopper());
		getCharacter().sendMsg(response);
	}

	/**
	 * 全修包括展示马和骑乘马
	 * 
	 * @param type
	 */
	public int repairAll(int type) {

		int currMoney = getCharacter().getCopper();
		int repairPrice = getRepairPrice(type);
		int tax = 0;
		// if (getCharacter().getScene() == ClientConfig.Scene_Xianjing) {
		// tax = (int) (FactionCityManager.getInstance().getTaxRate() * repairPrice);
		// }
		if (currMoney < repairPrice + tax) {
			return 40027;
		}
		if (repairPrice == 0) {
			return 40028;
		}
		CharacterGoodController characterGoodController = getCharacter().getCharacterGoodController();
		// 执行身上装备的修理
		repair(characterGoodController.getBodyGoodsContiner(), type);
		Horse showHorse = getCharacter().getCharacterHorseController().getShowHorse();
		if (showHorse != null && showHorse.getGoodsContainer() != null && showHorse.getGoodsContainer() != null) {
			// 修理马的装备
			repair(showHorse.getGoodsContainer(), type);
		}
		CharacterPropertyManager.changeCopper(getCharacter(), -(repairPrice + tax), CopperAction.CONSUME);
		FactionCityManager.getInstance().addTaxs(tax);
		return 0;
		// 1普修
		// 2特修
	}

	private void repair(IContainer container, int type) {
		for (CharacterGoods characterGoods : container.getGoodsList()) {

			// 最大耐久可磨损
			if (characterGoods.getMaxDurability() >= 0) {

				if (type == 1) {
					// 普修对最大耐久度的影响
					int maxDurability = characterGoods.getMaxDurability() - AppEventCtlor.getInstance().getEvtEquipmentFormula().repairAffectMaxDurability(characterGoods);
					// 如果修完后最大耐久为大于等于零则执行普修否则不理会
					if (maxDurability >= 0) {
						characterGoods.setMaxDurability(maxDurability);
					} else {
						characterGoods.setMaxDurability(0);
					}
				}
				characterGoods.setCurrDurability(characterGoods.getMaxDurability());
				// 耐久为0则脱下装备
				if (characterGoods.getCurrDurability() == 0) {
					changeProperty(getCharacter(), characterGoods, TakeMethod.off);
				} else {
					changeProperty(getCharacter(), characterGoods, TakeMethod.on);
				}
				// 更新到数据库并发送消息
				container.updateGoods(characterGoods);
			} else {
				// 不可修复略过
			}
		}
	}

	/**
	 * 询价不含税 add by lzrzhao
	 * 
	 * @param type
	 * @return
	 */
	public int getRepairPrice(int type) {
		return getCharacterPrice(getCharacter(), type);
	}

	private int getCharacterPrice(Hero currentCharacter, int type) {
		Collection<CharacterGoods> bodyGoodsList = currentCharacter.getCharacterGoodController().getBodyGoodsList();
		Collection<CharacterGoods> goodsList = currentCharacter.getCharacterGoodController().getBodyGoodsContiner().getGoodsList();
		getPrice(goodsList, type);
		int price = getPrice(bodyGoodsList, type);

		int horseprice = getHorsePrice(currentCharacter, type);
		return price + horseprice;
	}

	private int getPrice(Collection<CharacterGoods> list, int type) {

		int jiage = 0;//
		if (list != null && list.size() > 0)
			for (CharacterGoods characterGoods : list) {
				if (characterGoods.getMaxDurability() > 0) {
					// 该物品的普通维修价格
					if (type == 1) {
						jiage += AppEventCtlor.getInstance().getEvtEquipmentFormula().repairEquiement(characterGoods);
					} else if (type == 2) {
						// 特修价格
						jiage += AppEventCtlor.getInstance().getEvtEquipmentFormula().specialRepairEquiement(characterGoods);
					}
				}
			}
		return jiage;
	}

	private int getHorsePrice(Hero currentCharacter, int type) {
		Horse rideHorse = currentCharacter.getCharacterHorseController().getCurrentRideHorse();
		Horse showHorse = currentCharacter.getCharacterHorseController().getShowHorse();
		int price = 0;
		if (rideHorse != null) {
			Collection<CharacterGoods> rideGood = rideHorse.getGoodsContainer().getGoodsList();
			price += getPrice(rideGood, type);
		}
		if (showHorse != null) {
			Collection<CharacterGoods> showGood = showHorse.getGoodsContainer().getGoodsList();
			price += getPrice(showGood, type);
		}
		return price;
	}

	/**
	 * 换装
	 * 
	 * @param character
	 * @param newCharacterGoods
	 * @param method
	 * @throws IOException
	 */
	public void changeProperty(Hero character, CharacterGoods newCharacterGoods, TakeMethod method) {
		switch (method) {
		case on:
			character.getPropertyAdditionController().addChangeListener(newCharacterGoods);
			takeEquitment(newCharacterGoods);
			break;
		case off:
			character.getPropertyAdditionController().removeChangeListener(newCharacterGoods);
			offEquitment(newCharacterGoods);
		default:
			break;
		}
	}

	/**
	 * 战斗对防具的影响 每次调用都有可能降低当前耐久值
	 * 
	 * @param character
	 * @param goods
	 * 
	 * @throws IOException
	 */
	public void fightInfluenceEquitment() {

		Hero character = getCharacter();

		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		List<CharacterGoods> removeCharacterGoodsList = new ArrayList<CharacterGoods>();
		int size = characterGoodsList.size();
		if (size > 2) {
			int i = GenerateProbability.randomIntValue(0, size - 1);
			int j = GenerateProbability.randomIntValue(0, size - 1);
			if (i == j) {
				j = i + 1;
				j = j % size;
			}
			/* 受到伤害对装备耐久的影响 ** */
			CharacterGoods characterGoods = characterGoodsList.get(i);
			_effectEquipment(character, characterGoodController, removeCharacterGoodsList, characterGoods, 0);
			if (i != j) {
				CharacterGoods characterGoods2 = characterGoodsList.get(j);
				_effectEquipment(character, characterGoodController, removeCharacterGoodsList, characterGoods2, 0);
			}
		} else {
			for (Iterator<CharacterGoods> iterator = characterGoodsList.iterator(); iterator.hasNext();) {
				CharacterGoods _characterGoods = iterator.next();
				_effectEquipment(character, characterGoodController, removeCharacterGoodsList, _characterGoods, 0);
			}
		}

		if (!removeCharacterGoodsList.isEmpty()) {
			for (Iterator<CharacterGoods> iterator = removeCharacterGoodsList.iterator(); iterator.hasNext();) {
				changeProperty(character, iterator.next(), TakeMethod.off);
			}

			autoUseDaMoShi(character.getCharacterOnHoorController());
		}
	}

	private CharacterGoods _effectEquipment(Hero character, CharacterGoodController characterGoodController, List<CharacterGoods> removeCharacterGoodsList,
			CharacterGoods characterGoods, float percent) {
		if (characterGoods == null)
			return null;

		if (characterGoods.getPosition() < Position.POSTION_WUQI || characterGoods.getPosition() > Position.POSTION_TESHU) {
			return null;
		}

		if (characterGoods.getGoodModel().getDurability() == -1) {// 永不磨损
			return null;
		}

		int oldDurability = characterGoods.getCurrDurability();
		if (oldDurability <= 0)
			return null;

		if (percent > 0) {
			int _durability = (int) (characterGoods.getMaxDurability() * percent);
			int changeCurrentDurability = (oldDurability - (_durability == 0 ? 1 : _durability));
			if (changeCurrentDurability < 0) {
				changeCurrentDurability = 0;
			}
			characterGoods.setCurrDurability(changeCurrentDurability);
			character.sendMsg(new EquipFrayMsg10170(characterGoods));
			characterGoodController.updateCharacterGoods(characterGoods);
		} else {
			if (AppEventCtlor.getInstance().getEvtEquipmentFormula().canAffectEquitmentProbability()) {// 如果可以影响到装备的耐久
				int newDurability = oldDurability - 1;
				if (newDurability < 0) {
					newDurability = 0;
				}
				characterGoods.setCurrDurability(newDurability);
				character.sendMsg(new EquipFrayMsg10170(characterGoods));
				characterGoodController.updateCharacterGoods(characterGoods);
			}
		}
		if (characterGoods.getCurrDurability() <= 0 && oldDurability > 0) {// 脱掉装备了
			removeCharacterGoodsList.add(characterGoods);
		}

		return characterGoods;

	}

	/**
	 * pve死亡惩罚
	 * 
	 * @param percent
	 */
	public List<CharacterGoods> pveDieInfluenceDefend(float percent) {
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		List<CharacterGoods> removeCharacterGoodsList = new ArrayList<CharacterGoods>();
		List<CharacterGoods> influenceGoodsList = new ArrayList<CharacterGoods>();
		int size = characterGoodsList.size();
		if (size > 2) {
			int i = GenerateProbability.randomIntValue(0, size - 1);
			int j = GenerateProbability.randomIntValue(0, size - 1);
			if (i == j) {
				j = i + 1;
				j = j % size;
			}
			/* 受到伤害对装备耐久的影响 ** */
			CharacterGoods characterGoods = characterGoodsList.get(i);
			if (_effectEquipment(character, characterGoodController, removeCharacterGoodsList, characterGoods, percent) != null) {
				influenceGoodsList.add(characterGoods);
			}
			if (i != j) {
				CharacterGoods characterGoods2 = characterGoodsList.get(j);
				if (_effectEquipment(character, characterGoodController, removeCharacterGoodsList, characterGoods2, percent) != null) {
					influenceGoodsList.add(characterGoods2);
				}
			}
		} else {
			for (Iterator<CharacterGoods> iterator = characterGoodsList.iterator(); iterator.hasNext();) {
				CharacterGoods _characterGoods = iterator.next();
				if (_effectEquipment(character, characterGoodController, removeCharacterGoodsList, _characterGoods, percent) != null) {
					influenceGoodsList.add(_characterGoods);
				}
			}
		}

		if (!removeCharacterGoodsList.isEmpty()) {

			for (Iterator<CharacterGoods> iterator = removeCharacterGoodsList.iterator(); iterator.hasNext();) {
				changeProperty(character, iterator.next(), TakeMethod.off);
			}

			CharacterOnHoorController characterOnHoorController = character.getCharacterOnHoorController();
			autoUseDaMoShi(characterOnHoorController);
		}

		return influenceGoodsList;
	}

	private void autoUseDaMoShi(CharacterOnHoorController characterOnHoorController) {
		if (characterOnHoorController.isAutoOnHoor() && characterOnHoorController.getCharacterOnHoorConfig().getMoshixiuli()) {
			if (character.getCharacterGoodController().isEnoughGoods(GoodItemId.DaMoShi, 1)) {
				if (character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.DaMoShi, 1)) {
					repairBodyEquips();
				}
			}
		}
	}

	/**
	 * 检查是否满足套装属性
	 * 
	 * @return
	 */
	public boolean checkSuit() {
		int grade = -1;
		boolean bolAllGrade = true;
		boolean bolAllStar = true;
		boolean bolAllPurple = true;
		boolean bolAllStone = true;
		boolean bolAllBorn = true;
		int characterequipcount = 0;
		for (Iterator<CharacterGoods> iterator = characterGoodsList.iterator(); iterator.hasNext();) {
			CharacterGoods characterGoods = iterator.next();

			if (characterGoods.getPosition() < Position.POSTION_WUQI || characterGoods.getPosition() > Position.POSTION_TESHU) {
				continue;
			}

			int oldDurability = characterGoods.getCurrDurability();
			if (oldDurability <= 0) {
				continue;
			}

			Goodmodel model = characterGoods.getGoodModel();
			if (grade == -1)
				grade = model.getGrade();

			if (bolAllGrade && grade != model.getGrade()) {// 是同一等级的
				bolAllGrade = false;
			}

			if (bolAllBorn && !characterGoods.isMaxBornAttribute()) {
				bolAllBorn = false;
			}

			if (bolAllStar && !characterGoods.isAllStar()) {
				bolAllStar = false;
			}

			if (bolAllPurple && characterGoods.getPingzhiColor() < 4) {
				bolAllPurple = false;
			}

			if (bolAllStone && !characterGoods.isManxingGems()) {
				bolAllStone = false;
			}

			characterequipcount++;
		}
		if (characterequipcount < 12) {
			clearAllGradeBuff();
			clearAllStarBuff();
			clearAllPurpleBuff();
			clearAllStoneBuff();
			clearAllBornBuff();
			return false;
		}
		PropertyAdditionController propertyController = getCharacter().getPropertyAdditionController();
		EffectInfo effectInfo = new EffectInfo(null);
		effectInfo.setAffecter(character);
		if (bolAllGrade) {
			if (allGrade == null) {
				allGrade = new EqualGradeSuit(effectInfo);
				propertyController.addChangeListener(allGrade);
			}
		} else {
			clearAllGradeBuff();
		}

		if (bolAllStar) {
			if (allStar == null) {
				allStar = new AllStarSuit(effectInfo);
				propertyController.addChangeListener(allStar);
			}
		} else {
			clearAllStarBuff();
		}

		if (bolAllPurple) {
			if (allPurple == null) {
				allPurple = new AllPurpleSuit(effectInfo);
				propertyController.addChangeListener(allPurple);
			}
		} else {
			clearAllPurpleBuff();
		}

		if (bolAllBorn) {
			if (allBorn == null) {
				allBorn = new AllBornSuit(effectInfo);
				propertyController.addChangeListener(allBorn);
			}
		} else {
			clearAllBornBuff();
		}

		if (bolAllStone) {
			if (allStone == null) {
				allStone = new AllStoneSuit(effectInfo);
				propertyController.addChangeListener(allStone);
			}
		} else {
			clearAllStoneBuff();
		}

		return true;
	}

	/**
	 * 清除全等级buff
	 */
	private void clearAllGradeBuff() {
		PropertyAdditionController propertyController = getCharacter().getPropertyAdditionController();
		if (allGrade != null) {
			propertyController.removeChangeListener(allGrade);
			allGrade = null;
		}
	}

	/**
	 * 清除全星buff
	 */
	private void clearAllStarBuff() {
		Hero character = getCharacter();
		PropertyAdditionController propertyController = character.getPropertyAdditionController();

		if (allStar != null) {
			propertyController.removeChangeListener(allStar);
			allStar = null;
		}
	}

	/**
	 * 清除全天生buff
	 */
	private void clearAllBornBuff() {
		Hero character = getCharacter();
		PropertyAdditionController propertyController = character.getPropertyAdditionController();

		if (allBorn != null) {
			propertyController.removeChangeListener(allBorn);
			allBorn = null;
		}
	}

	/**
	 * 清除全紫buff
	 */
	private void clearAllPurpleBuff() {
		Hero character = getCharacter();
		PropertyAdditionController propertyController = character.getPropertyAdditionController();

		if (allPurple != null) {
			propertyController.removeChangeListener(allPurple);
			allPurple = null;
		}
	}

	/**
	 * 清除全紫buff
	 */
	private void clearAllStoneBuff() {
		Hero character = getCharacter();
		PropertyAdditionController propertyController = character.getPropertyAdditionController();

		if (allStone != null) {
			propertyController.removeChangeListener(allStone);
			allStone = null;
		}
	}

	public Buff getAllGrade() {
		return allGrade;
	}

	public void setAllGrade(Buff allGrade) {
		this.allGrade = allGrade;
	}

	public Buff getAllStar() {
		return allStar;
	}

	public void setAllStar(Buff allStar) {
		this.allStar = allStar;
	}

	public Buff getAllPurple() {
		return allPurple;
	}

	public void setAllPurple(Buff allPurple) {
		this.allPurple = allPurple;
	}

	public EquipmentController(Hero character) {
		super(character);
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		int s1 = characterGoodsList.size();
		int s2 = addSkillGradeMaps.size();
		return s1 + s2;
	}

}
