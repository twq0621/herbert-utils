package net.snake.gamemodel.onhoor.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.snake.ai.characterfsm.CharacterFSM;
import net.snake.ai.formula.AttackFormula;
import net.snake.commons.program.Updatable;
import net.snake.consts.BuffId;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fight.response.PkModelChanageMsg;
import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.hero.persistence.CharacterOnHoorConfigManager;
import net.snake.gamemodel.onhoor.response.AfkConfigMsg50592;
import net.snake.gamemodel.onhoor.response.BeginAfkReseponse50584;
import net.snake.gamemodel.onhoor.response.UpdateAfkTimeResponse50590;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import org.apache.log4j.Logger;

/**
 * 挂机管理器
 * 
 * @author serv_dev
 * 
 */
public class CharacterOnHoorController extends CharacterController implements Updatable {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CharacterOnHoorController.class);
	private final AutoPickUpController autoPickController = new AutoPickUpController(this);
	private final AutoRevertController autoRevertController = new AutoRevertController(this);
	private CharacterOnHoorConfig characterOnHoorConfig;
	private OnHoorState afkState = OnHoorState.off;// 是否挂机 挂机的开始与结束
	private OnHoorState afkAttack = OnHoorState.attack;// 是否挂机
	// private OnHoorState doubleZhenqiState = OnHoorState.off;//是否挂机
	// private OnHoorState doubleExpState = OnHoorState.off;//是否挂机
	private OnHoorState doubleZuoqiExpState = OnHoorState.off;// 是否挂机
	private CharacterFSM roleState;

	private static final int updateGotime = 60000;// 1分钟
	private static final int updateGotime5 = 60000 * 5;// 5分钟
	private long afkGotime = 0l;// 间隔时间
	private long afkBeginTime = 0l;
	private boolean isProtect = false;
	private int x;// 挂机的中心点
	private int y;//

	private volatile int pkmodel = 0;

	public void destroy() {
		roleState = null;
		doubleZuoqiExpState = null;
		// doubleExpState = null;
		// doubleZhenqiState = null;
		afkAttack = null;
		afkState = null;
		characterOnHoorConfig = null;

		if (roleState != null) {
			roleState.destroy();
			roleState = null;
		}

		if (autoRevertController != null) {
			autoRevertController.destroy();
		}

		if (autoRevertController != null) {
			autoRevertController.destroy();
		}
	}

	private boolean isOutScope() {
		return !AttackFormula.atkIsEnable((short) x, (short) y, character.getX(), character.getY(), characterOnHoorConfig.getAttackScope().shortValue());
	}

	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}

	public CharacterOnHoorController(Hero character) {
		super(character);
	}

	public boolean autoTOPickUp() {
		// 捡物品
		if (getCharacterOnHoorConfig().getAutoPickup()) {
			if (autoPickController.autoPickUp()) {
				return true;
			}
		}
		return false;
	}

	public OnHoorState getAfkAttack() {
		return afkAttack;
	}

	public void setAfkAttack(OnHoorState afkAttack) {
		this.afkAttack = afkAttack;
	}

	public void autoPickUpStart() {
		CharacterOnHoorConfig characterOnHoorConfig = getCharacterOnHoorConfig();
		if (characterOnHoorConfig.getAutoPickup() && isAutoOnHoor()) {
			if (!autoPickController.isStart()) {
				autoPickController.setStart(true);// 开启动作拾取寻找
			}
		}
	}

	/**
	 * 保存挂机配置
	 */
	public void saveOnHoorConfig() {
		CharacterOnHoorConfigManager.getInstance().asynUpdataCharacterOnHoorConfig(character, characterOnHoorConfig);
		setCharacterOnHoorConfig(characterOnHoorConfig);
	}

	/**
	 * @param curtime
	 */
	@SuppressWarnings("unused")
	private void isAbleClose(long curtime) {
		long gotime = curtime - afkBeginTime;

		if (gotime - this.afkGotime >= updateGotime) {
			// 更新挂机剩余时间
			character.sendMsg(new UpdateAfkTimeResponse50590(0, characterOnHoorConfig.getAttackTime() - gotime));
			this.afkGotime = gotime;
			if (curtime - afkBeginTime >= updateGotime5) {// 每一分钟检测一次
				if (isPkProtectTime()) {
					setProtect(true);
				} else {
					setProtect(false);
				}
			}
		}

		if (characterOnHoorConfig.getAttackTime() <= gotime) {
			setAfkState(OnHoorState.off);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 580));
			if (characterOnHoorConfig.getAttackTime() >= 1200000) // 如果挂机时间低于20分钟 则不回城打坐
			{
				if (characterOnHoorConfig.getBackSit()) {// 回城打坐
					Horse horse = character.getCharacterHorseController().getCurrentRideHorse();
					if (horse != null) {
						character.getCharacterHorseController().unRide();
					}
					character.setObjectState(VisibleObjectState.Idle);
					character.getMyDazuoManager().autoHuichengDazuo();
					return;
				}
			}
		}

	}

	private void aotoDoubDan() {
		if (characterOnHoorConfig.getExpdan()) {
			if (!character.getEffectController().isDoubleExp() && !character.getEffectController().isDouble5Exp() && !character.getEffectController().isDouble10Exp()) {
				if (character.getCharacterGoodController().isEnoughGoods(GoodItemId.DoubleJingYan, 1)) {
					EffectInfo effect = new EffectInfo(SkillEffectManager.getInstance().getSkillEffectById(BuffId.DoubleJingYan));
					effect.setDuration(effect.getEffect().getDuration());
					if (character.getEffectController().addEffect(effect)) {
						character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.DoubleJingYan, 1);
					}
				}
			}
		}
		if (characterOnHoorConfig.getHorseExpdan()) {
			if (!character.getEffectController().isDoubleHorseExp()) {
				if (character.getCharacterGoodController().isEnoughGoods(GoodItemId.DoubleZuoqiJingYan, 1)) {
					EffectInfo effect = new EffectInfo(SkillEffectManager.getInstance().getSkillEffectById(BuffId.DoubleZuoqiJingYan));
					effect.setDuration(effect.getEffect().getDuration());
					if (character.getEffectController().addEffect(effect)) {
						character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.DoubleZuoqiJingYan, 1);
					}
				}
			}
		}

		if (characterOnHoorConfig.getZhenqidan()) {
			if (!character.getEffectController().isDoubleZhenqi()) {
				if (character.getCharacterGoodController().isEnoughGoods(GoodItemId.DoubleZhenqi, 1)) {
					EffectInfo effect = new EffectInfo(SkillEffectManager.getInstance().getSkillEffectById(BuffId.DoubleZhenqi));
					effect.setDuration(effect.getEffect().getDuration());
					if (character.getEffectController().addEffect(effect)) {
						character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.DoubleZhenqi, 1);
					}
				}
			}
		}
	}

	/**
	 * 是否双倍的坐骑经验
	 * 
	 * @return
	 */
	public boolean isDoubleZuoqiExp() {
		return doubleZuoqiExpState == OnHoorState.on;
	}

	public CharacterFSM getRoleState() {
		return roleState;
	}

	public boolean isProtect() {
		return isProtect;
	}

	private void setProtect(boolean isProtect) {
		this.isProtect = isProtect;
	}

	public boolean isPkProtectTime() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		if (hours >= 0 && hours < 8) {
			return true;
		}
		return false;
	}

	public void setAfkState(OnHoorState afkState) {
		if (this.afkState == afkState) {
			return;
		}// 状态已经存在
		this.afkState = afkState;
		if (afkState == OnHoorState.on) {
			if (getCharacterOnHoorConfig().getAttackTime() <= 0) {
				return;
			}
			if (isPkProtectTime()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20087));
			}
			character.sendMsg(new BeginAfkReseponse50584(1));
		} else {
			this.afkGotime = 0;
			setProtect(false);
			character.sendMsg(new BeginAfkReseponse50584(0));
		}
		if (isAutoOnHoor()) {
			roleState = new CharacterFSM(character);
			afkBeginTime = System.currentTimeMillis();
			if (characterOnHoorConfig.getAutoPickup()) {
				if (!autoPickController.isStart()) {
					character.getUpdateObjManager().addFrameUpdateObject(autoPickController);
					autoPickController.setStart(true);
				}
			}
			setX(character.getX());
			setY(character.getY());
			if (character.getPkModel() > 0 && pkmodel != character.getPkModel()) {
				pkmodel = character.getPkModel();
				character.getFightController().setPkModel(0);
				character.sendMsg(new PkModelChanageMsg(character.getPkModel()));
			}
			character.sendMsg(new AfkConfigMsg50592(getCharacterOnHoorConfig(), character.getCharacterOnHoorController()));
		} else {
			roleState = null;
			if (pkmodel != character.getPkModel()) {
				character.getFightController().setPkModel(pkmodel);
				character.sendMsg(new PkModelChanageMsg(character.getPkModel()));
			}
			pkmodel = 0;
		}
	}

	public void initData() {

		try {
			CharacterOnHoorConfig characterOnHoorConfig = CharacterOnHoorConfigManager.getInstance().getCharacterOnHoorConfigList(character.getId());
			if (characterOnHoorConfig == null) {
				characterOnHoorConfig = new CharacterOnHoorConfig();
				characterOnHoorConfig.setCharacterId(character.getId());
				characterOnHoorConfig.setSkillOne(character.getSkillid());
				characterOnHoorConfig.setSkillTwo(character.getSkillid());
				characterOnHoorConfig.setSkillThree(character.getSkillid());
				CharacterOnHoorConfigManager.getInstance().insertCharacterOnHoorConfig(characterOnHoorConfig);
				characterOnHoorConfig = CharacterOnHoorConfigManager.getInstance().getCharacterOnHoorConfigList(character.getId());
			}
			setCharacterOnHoorConfig(characterOnHoorConfig);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public CharacterOnHoorConfig getCharacterOnHoorConfig() {
		return characterOnHoorConfig;
	}

	/**
	 * 再次打开自动恢复功能
	 */
	public void startAutoRevertHpMp() {
		if (!autoRevertController.isStart()) {
			autoRevertController.setStart(true);
		}
	}

	/**
	 * 
	 * 设置挂机配置 初始化与配置变更的时候调用
	 * 
	 * @param characterOnHoorConfig
	 */
	private void setCharacterOnHoorConfig(CharacterOnHoorConfig characterOnHoorConfig) {

		if (characterOnHoorConfig.getAutoPickup()) {
			if (!autoPickController.isStart() && isAutoOnHoor()) {
				character.getUpdateObjManager().addFrameUpdateObject(autoPickController);
				autoPickController.setStart(true);
			}
		} else {
			if (autoPickController != null) {
				if (autoPickController.isStart()) {
					character.getUpdateObjManager().removeFrameUpdateObject(autoPickController);
					autoPickController.setStart(false);
				}
			}
		}
		this.characterOnHoorConfig = characterOnHoorConfig;

	}

	/**
	 * 正在挂机
	 * 
	 * @return
	 */
	public boolean isAutoOnHoor() {
		return afkState == OnHoorState.on;
	}

	public enum OnHoorState {
		on, // 开启
		off, // 关闭
		attack, // 战斗
		pickup;// 拾取
	}

	@Override
	public void update(long now) {

		if (!isAutoOnHoor()) {
			getCharacter().getFightController().checkZiweiTarget(now);
			return;
		}

		// 自动挂机状态
		if (getCharacter().isZeroHp()) {
			return;
		}
		// isAbleClose(now);
		aotoDoubDan();
		if (getRoleState() != null) {
			getRoleState().update(now);
		}
		if (getCharacter().getObjectState() == VisibleObjectState.Walk) {
			if (character.getTarget() != null && isOutScope()) {
				character.getMoveController().stopMove();
				character.setTarget(null);
				getCharacter().setObjectState(VisibleObjectState.Idle);
			}
		}

		getCharacter().getFightController().checkZiweiTarget(now);
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}

}
