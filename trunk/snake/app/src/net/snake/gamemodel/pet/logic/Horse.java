package net.snake.gamemodel.pet.logic;

import java.util.Collection;

import net.snake.ai.IEyeShotManager;
import net.snake.ai.fight.controller.BaseFightController;
import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.formula.HorseFormula;
import net.snake.ai.horsefsm.HorseBasicState;
import net.snake.ai.horsefsm.HorseFsm;
import net.snake.ai.move.IMoveController;
import net.snake.commons.VisibleObjectState;
import net.snake.commons.program.Updatable;
import net.snake.commons.script.SHorse;
import net.snake.consts.GameConstant;
import net.snake.consts.HorseStateConsts;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.consts.TakeMethod;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.container.HorseBodyGoodsContiner;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseGrade;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.persistence.HorseGradeDataProvider;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.gamemodel.pet.response.HorseInfoResponse60006;
import net.snake.gamemodel.pet.response.HorseLivingchange;
import net.snake.gamemodel.pet.response.HorseUpgradeInfo50008;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.netio.message.ResponseMsg;

/**
 * 封装马的对像 马的状态机管理
 * 
 * @author serv_dev
 * 
 */
public class Horse extends VisibleObject implements SHorse, Updatable, PropertyChangeListener {
	public boolean rideing;// 是否正在执行被骑乘动作
	/** 马的主人 */
	private Hero character;
	/** 马的模型 */
	private HorseModel horseModel;
	/** 马的相应状态信息 */
	private CharacterHorse characterHorse;
	// 马的各种控制器
	private HorseSkillManager horseskillManager = new HorseSkillManager(this);
	private HorseFsm horsefsm;
	/** 马自动攻击控制器,用于骑战和展示状态下,对人的攻击 */
	private HorseAutoAttackController aotoAttackController = new HorseAutoAttackController(this);
	/** 驯养管理器 */
	private HorseRaiseManager horseRaiseManager;
	/** 坐骑身上的装备保存　 */
	private HorseBodyGoodsContiner horsebodygoods;
	// =============================================
	boolean firstLessPercent25 = true;
	/** 交易状态的索引,交易取消时要置为-1 */
	private int tradeIndex = -1;

	private float livingnessProAdd = 0;

	public void setTradeIndex(int tradeIndex) {
		this.tradeIndex = tradeIndex;
	}

	public boolean isRemovable() {
		return characterHorse.getStatus() == HorseStateConsts.REST && isInTrade() == false && rideing == false;
	}

	public void setCharacter(Hero character) {
		this.character = character;
	}

	public int getTradeIndex() {
		return tradeIndex;
	}

	public boolean isAbleRide() {
		return getUseGradeLimit() <= character.getGrade() && getCharacterHorse().getLivingness() > 0 && (!isInTrade());

	}

	public boolean isAbleShow() {
		return getUseGradeLimit() <= character.getGrade() && getCharacterHorse().getLivingness() > 0 && (!isInTrade());

	}

	public boolean isInTrade() {
		return (tradeIndex != -1);
	}

	public void setCharacterHorse(CharacterHorse characterHorse) {
		this.characterHorse = characterHorse;
	}

	public HorseSkillManager getHorseskillManager() {
		return horseskillManager;
	}

	public HorseAutoAttackController getAotoAttackController() {
		return aotoAttackController;
	}

	public HorseRaiseManager getHorseRaiseManager() {
		return horseRaiseManager;
	}

	@Override
	public void die(VisibleObject attacker) {
		super.die(attacker);

		getEnmityManager().clearEnmityList();
		// 我死了,把我的仇恨移除掉
		getEnmityManager().clearWhosEnmityisMe();
	}

	public void addEquipPropertis() {
		Collection<CharacterGoods> horsebodygoodslist = horsebodygoods.getGoodsList();
		if (horsebodygoodslist != null) {
			for (CharacterGoods horsegoods : horsebodygoodslist) {
				if (horsegoods.getGoodModel().getLimitGrade() <= characterHorse.getGrade()) {
					character.getEquipmentController().changeProperty(character, horsegoods, TakeMethod.on);
				}
			}
		}
	}

	public void removeEquipProperties() {
		Collection<CharacterGoods> horsebodygoodslist = horsebodygoods.getGoodsList();
		if (horsebodygoodslist != null) {
			for (CharacterGoods horsegoods : horsebodygoodslist) {
				if (horsegoods.getGoodModel().getLimitGrade() <= characterHorse.getGrade()) {
					character.getEquipmentController().changeProperty(character, horsegoods, TakeMethod.off);
				}
			}
		}
	}

	// 当主人攻击目标时
	public void onOwnerAttack(VisibleObject obj) {
		this.getAotoAttackController().startAttackIfNotStart(obj);
		horsefsm.onOwnerAttack(obj);
	}

	public Horse(Hero character, CharacterHorse characterHorse) {
		this.characterHorse = characterHorse;
		this.character = character;
		horseModel = HorseModelDataProvider.getInstance().getHorseModelByID(characterHorse.getHorseModelId());
		setId(characterHorse.getId());
		horseRaiseManager = new HorseRaiseManager(this);
		horsebodygoods = character.getCharacterGoodController().getAndRemoveHorseBodyGoodsContainer(getId());
		horsebodygoods.setHorse(this);
	}

	public HorseBodyGoodsContiner getGoodsContainer() {
		return horsebodygoods;
	}

	public Hero getCharacter() {
		return character;
	}

	public CharacterHorse getCharacterHorse() {
		return characterHorse;
	}

	public HorseModel getHorseModel() {
		return horseModel;
	}

	public void setHorseModel(HorseModel horseModel) {
		this.horseModel = horseModel;
	}

	public int getSelfPrice() {
		int allCount = getSkillManager().getAllSkillCount();
		return HorseFormula.getHorsePrice(characterHorse, allCount, allCount - 5);
	}

	@Override
	public void update(long now) {
		super.update(now);
		horsefsm.update(now);
	}

	/**
	 * 灵宠的活力值下降，则根据规则降低对人物的加成 1) 活力值120 - 100之间：灵宠处于亢奋阶段，属性加成、技能效果和持续时间额外增加20% 2) 活力值为30-99之间：灵宠处于正常战斗状态，属性加成、技能效果和持续时间为正常值； 3) 活力值为1-29时:灵宠处于疲劳状态，属性加成、技能效果和持续时间减半直到活力值恢复； 4)
	 * 活力值为0时：灵宠强制进入休息状态，不可参战；
	 * 
	 * @param now
	 */
	private void livingnesChange() {
		int livingness = characterHorse.getLivingness();
		if (livingness >= 100 && livingnessProAdd != 20) {
			livingnessProAdd = 0.2f;
			character.getPropertyAdditionController().addChangeListener(this);
		} else if (livingness >= 30 && livingness <= 99 && livingnessProAdd != 0) {
			livingnessProAdd = 0;
			character.getPropertyAdditionController().addChangeListener(this);
		} else if (livingness >= 1 && livingness <= 29 && livingnessProAdd != -50) {
			livingnessProAdd = -0.5f;
			character.getPropertyAdditionController().addChangeListener(this);
		}
	}

	public PropertyEntity getPropertyEntity() {

		PropertyEntity pe = new PropertyEntity();

		pe.addExtraProperty(Property.attack, (int) ((characterHorse.getAttack() + characterHorse.getExtraAttack()) * (1 + livingnessProAdd)));

		pe.addExtraProperty(Property.defence, (int) ((characterHorse.getDefence() + characterHorse.getExtraDefence()) * (1 + livingnessProAdd)));

		pe.addExtraProperty(Property.maxHp, (int) ((characterHorse.getHp() + characterHorse.getExtraHp()) * (1 + livingnessProAdd)));

		pe.addExtraProperty(Property.maxMp, (int) ((characterHorse.getMp() + characterHorse.getExtraMp()) * (1 + livingnessProAdd)));

		pe.addExtraProperty(Property.dodge, (int) ((characterHorse.getDodge() + characterHorse.getExtraDodge()) * (1 + livingnessProAdd)));

		pe.addExtraProperty(Property.crt, (int) ((characterHorse.getCrt() + characterHorse.getExtraCrt()) * (1 + livingnessProAdd)));

		pe.addExtraProperty(Property.hit, (int) ((characterHorse.getHit() + characterHorse.getExtraHit()) * (1 + livingnessProAdd)));

		pe.addExtraProperty(Property.attackspeed, (int) (horseModel.getAddOwnerAtkspeed() * (1 + livingnessProAdd)));

		pe.addExtraProperty(Property.movespeed, (int) (horseModel.getAddOwnerMovespeed() * (1 + livingnessProAdd)));

		return pe;
	}

	public void initHorse() {
		this.livingnesChange();
		horseskillManager.init();
		horsefsm = new HorseFsm(this);
		horsefsm.changeStatus(characterHorse.getStatus());
		effectController = new EffectController(character);
		setMoveSpeed(horseModel.getHorseMovespeed());
		getPropertyAdditionController().recompute();
		aotoAttackController.initData();
	}

	@Override
	public IMoveController getMoveController() {
		return null;
	}

	@Override
	public HorseSkillManager getSkillManager() {
		return getHorseskillManager();
	}

	public void changeStatus(int rest) {
		if (HorseStateConsts.SHOW == rest) {
			long neidanUsetime = System.currentTimeMillis() - characterHorse.getNeidanStarttime();
			characterHorse.setNeidanUsetime(neidanUsetime);
		} else if (HorseStateConsts.REST == rest) {
			characterHorse.setNeidanStarttime(System.currentTimeMillis());
			characterHorse.setNeidanCdtime(characterHorse.getNeidanCdtime() - characterHorse.getNeidanUsetime());
		}
		horsefsm.changeStatus(rest);
	}

	public void addLivingness(int addvalue) {
		characterHorse.setLivingness(characterHorse.getLivingness() + addvalue);
		if (characterHorse.getLivingness() > characterHorse.getLivingnessMax()) {
			characterHorse.setLivingness(characterHorse.getLivingnessMax());
		}
		if (characterHorse.getLivingness() < 0) {
			characterHorse.setLivingness(0);
		}
		int percent25livingness = (int) (characterHorse.getLivingnessMax() * 0.25f);

		if (characterHorse.getLivingness() < percent25livingness && firstLessPercent25) {
			firstLessPercent25 = false;
			// 发送消息
			// 您当前骑乘的坐骑已经快筋疲力尽了，请使用草料为其恢复活力（襄阳城坐骑装备店有坐骑草料出售
			if (characterHorse.getStatus() == HorseStateConsts.SHOW) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 550));
			}
		}
		if (characterHorse.getLivingness() > percent25livingness && firstLessPercent25 == false) {
			firstLessPercent25 = true;
		}
		this.livingnesChange();
		character.sendMsg(new HorseLivingchange(getId(), characterHorse.getLivingness(), characterHorse.getLivingnessMax()));

		if (characterHorse.getLivingness() <= 0) {
			horsefsm.changeStatus(HorseStateConsts.REST);
			return;
		}
	}

	public HorseBasicState getCurrentState() {
		return horsefsm.getCurrentState();
	}

	@Override
	public ResponseMsg getInstanceMoveMsg() {
		return null;// new HorseInstantMove10124(getId(), getX(), getY());
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.zuoqi;
	}

	/**
	 * exp为角色获得的原始经验
	 * 
	 * @param exp
	 */
	public void gainedMonsterExp(SceneMonster monster) {
		if (characterHorse.getGrade() < character.getGrade()) {
			int exp = HorseFormula.getExp(character, monster, this);
			horseGainedExp(exp);
		}

	}

	// 坐骑获得经验
	public void horseGainedExp(int exp) {

		if (character.getEffectController().isDoubleHorseExp()) {
			exp = exp + exp;
		}
		characterHorse.setExperience(characterHorse.getExperience() + exp);
		// 当前级
		HorseGrade horsegrade = HorseGradeDataProvider.getInstance().getHorseGradeById(characterHorse.getGrade());
		int exper = horsegrade.getLevelExperience();
		int currentgrade = characterHorse.getGrade();
		// 马的升级经验计算
		while (horsegrade != null && characterHorse.getExperience() >= horsegrade.getLevelExperience()) {
			if (characterHorse.getGrade() >= character.getGrade()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60056));
				break;
			}
			horsegrade = HorseGradeDataProvider.getInstance().getHorseGradeById(characterHorse.getGrade() + 1);
			if (horsegrade == null) {
				break;
			}
			characterHorse.setGrade(characterHorse.getGrade() + 1);
			HorseFormula.horseUpgratePro(characterHorse);
			characterHorse.setExperience(characterHorse.getExperience() - exper);
			if (characterHorse.getGrade() <= GameConstant.maxLearnSkillGrade) {
				int skillcnt = horseskillManager.getAllSkillCount();
				int cnt = characterHorse.getGrade() / GameConstant.preGradeSkill;
				int canlrnSkill = cnt - skillcnt;
				if (canlrnSkill > 0) {
					for (int i = 0; i < canlrnSkill; i++) {
						short pos = (short) (horseskillManager.getAllSkillCount());
						if (pos < 0) {
							pos = 0;
						}
						horseskillManager.saveHorseSkill(characterHorse, pos);
					}
				}
			} else {
				horseskillManager.saveShenji(characterHorse);
			}
			// 下马
			if (characterHorse.getGrade() > character.getGrade()) {
				if (characterHorse.getStatus() == HorseStateConsts.SHOW) {
					changeStatus(HorseStateConsts.REST);
				}
			}
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60058, characterHorse.getName(), characterHorse.getGrade()));
		}
		if (characterHorse.getGrade() > currentgrade) {
			character.sendMsg(new HorseUpgradeInfo50008(this));
			character.sendMsg(new HorseInfoResponse60006(character, this));
			if (characterHorse.getStatus() == HorseStateConsts.SHOW) {
				character.getPropertyAdditionController().addChangeListener(this);
				addEquipPropertis();// 装备属性重检查一下,可能有的装备以前不能穿,现在能穿了
			}
		}
	}

	public void addSHJM() {
		if (horseskillManager.getNeipanShenji() == null) {
			return;
		}
		if (!horseskillManager.getNeipanShenji().able2Use(character)) {
			return;
		}
		EffectInfo effect = new EffectInfo(horseskillManager.getNeipanShenji().getSkill().getEffect());
		// SHJM shjm = new SHJM(effect);
		character.getEffectController().addEffect(effect);
		horseskillManager.getNeipanShenji().xiaohaoValue(character);
	}

	public void speedUpNeiDan() {
		if (horseskillManager.getTianyuanyangshengShenji() == null) {
			return;
		}
		if (!horseskillManager.getTianyuanyangshengShenji().able2Use(character)) {
			return;
		}
		int cdtime = horseskillManager.getTianyuanyangshengShenji().getSkill().getEffect().getHurtValue() * 1000 * horseskillManager.getTianyuanyangshengShenji().getGrade();
		this.characterHorse.setNeidanCdtime(characterHorse.getNeidanCdtime() - cdtime);
		horseskillManager.getTianyuanyangshengShenji().xiaohaoValue(character);
	}

	@Override
	public ResponseMsg getEnterEyeshotMsg() {
		return null;// new HorseEnterEyeShot10034(this);
	}

	@Override
	public ResponseMsg getLeaveEyeshotMsg() {
		return null;// new HorseLeaveEyeShot10082(id);
	}

	@Override
	public IEyeShotManager getEyeShotManager() {
		return character.getEyeShotManager();
	}

	@Override
	public ResponseMsg getDieMsg() {
		return null;
	}

	@Override
	public byte getSpriteType() {
		return 3;
	}

	@Override
	public int changeNowHp(int changeV) {
		return 0;
	}

	@Override
	public int changeNowMp(int changeV) {
		return 0;
	}

	@Override
	public int changeNowSp(int changeV) {
		return 0;
	}

	@Override
	public boolean isZeroHp() {
		return characterHorse.getLivingness() <= 0;
	}

	@Override
	public void onEnterScene(Scene scene) {
		// getEyeShotManager().onEnterScene(scene, true);
	}

	@Override
	public void onLeaveScene(Scene scene, Scene newscene) {
		// getEyeShotManager().onLeaveScene(scene);
		// getMoveController().stopMove();
		// getEnmityManager().clearEnmityList();
		// getEnmityManager().clearWhosEnmityisMe();
		// setSceneRef(null);
	}

	public int getUseGradeLimit() {
		return horseModel.getUseMeGradeLimit() > characterHorse.getGrade() ? horseModel.getUseMeGradeLimit() : characterHorse.getGrade();
	}

	@Override
	public int getStatus() {
		return getCharacterHorse().getStatus();
	}

	@Override
	public int getKind() {
		return this.getHorseModel().getKind();
	}

	public void updateCharacterProp() {
		Horse ridehorse = this.character.getCharacterHorseController().getCurrentRideHorse();
		int id = this.getId();
		if (ridehorse != null && id == ridehorse.getId()) {
			ridehorse.changeStatus(HorseStateConsts.REST);
			if (ridehorse.isAbleRide()) {
				ridehorse.changeStatus(HorseStateConsts.SHOW);
			}
		}
	}

	@Override
	public int[] getHeedSceneObject() {
		return null;
	}

	@Override
	public BaseFightController getFightController() {
		return null;
	}

	@Override
	public void setObjectState(int state) {

	}

	@Override
	public int getObjectState() {
		return getCharacterHorse().getStatus();
	}

	@Override
	public ResponseMsg getShockMsg() {
		return null;
	}

	@Override
	public int getSceneObjType() {
		return SceneObjType_Horse;
	}

	public int getAttackHurtCorrect() {
		return 0;
	}

	@Override
	public int getCurrentStat() {
		return this.getObjectState();
	}

	public int getAttack() {
		return this.characterHorse.getAttack();
	}

	public int getDefence() {
		return this.characterHorse.getDefence();
	}

	public int getCrt() {
		return characterHorse.getCrt();
	}

	public int getDodge() {
		return characterHorse.getDodge();
	}

	public int getHit() {
		return characterHorse.getHit();
	}

	public int getMaxHp() {
		return characterHorse.getHp();
	}

	public int getMaxMp() {
		return characterHorse.getMp();
	}

	@Override
	public void setStandState() {
		this.setObjectState(VisibleObjectState.Idle);
	}

	public short getGrade() {
		return this.characterHorse.getGrade().shortValue();
	}
	
}
