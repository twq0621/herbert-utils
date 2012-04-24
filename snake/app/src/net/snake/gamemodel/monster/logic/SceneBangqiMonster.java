package net.snake.gamemodel.monster.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.commons.script.IEventListener;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.faction.bean.SceneBangqi;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.response.monster.MonsterBangqiEnterEyeShot10036;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.ResponseMsg;

/**
 * 帮旗
 * 
 * @author serv_dev
 * 
 */
public class SceneBangqiMonster extends SceneMonster {

	private static final Logger logger = Logger.getLogger(SceneBangqiMonster.class);
	private SceneBangqi sceneBangqi;
	public int lineId = 0;

	public SceneBangqi getSceneBangqi() {
		return sceneBangqi;
	}

	public void setSceneBangqi(SceneBangqi sceneBangqi) {
		this.sceneBangqi = sceneBangqi;
	}

	public Scene getSceneRef() {
		return sceneRef;
	}

	@Override
	public void fireAttacking(VisibleObject _attacter) {
		if (isDie())
			return;
		setObjectState(VisibleObjectState.Idle);
		// setFirstAttackPlayer(_attacter);
		if (getTarget() != null)
			return;
		// getEnmityManager().addEnmityValue(_attacter, 0);
	}

	@Override
	public int changeNowHp(int changeV) {
		if (sceneBangqi != null) {
			sceneBangqi.changeNowHp(changeV);
		}
		return changeV;
	}

	/**
	 * 死亡 1.死亡掉落物品 2.获得经验 3.清空之前身上所有的列表数据
	 */
	@Override
	public void die(VisibleObject visibleObject) {
		if (getNowHp() > 0) {
			setNowHp(0);
		}
		// effectController.clearEffectListAndRemoveBuffOnBody();
		dieTime = System.currentTimeMillis();
		getEyeShotManager().sendMsg(getDieMsg());
		// ScriptEventCenter.getInstance().onMonsterDie(null, this);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_MonsterDie, new Object[] { this });

		getEnmityManager().clearEnmityList();
		// 我死了,把我的仇恨移除掉
		getEnmityManager().clearWhosEnmityisMe();
		// 因为坐骑也会打怪,打怪也会
		getPursuitPointManager().clearArroundWithMeInFightMonsterPositions();
		// 移除所有脚本定义属性
		removeAllAttribute();
		setObjectState(VisibleObjectState.Die);
		sceneBangqi.getFactionController().bangqiMonsterDie(this, (Hero) visibleObject);
	}

	@Override
	public void onBeAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {
		if (isDie())
			return;
		if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero character = (Hero) whoAttackMe;
			int lind = character.getVlineserver().getLineid();
			if (!isPkTime()) {
				return;
			}
			if (sceneBangqi.getLine() != lind) {
				return;
			}
			if (character.getGrade() < 25) {
				return;
			}
			if (!character.getMyFactionManager().isFaction()) {
				return;
			}
			int factionId = character.getMyFactionManager().getFactionId();
			if (factionId == getSceneBangqi().getFactionId()) {
				return;
			}
			// character.getFightController().updateAttackMondel(
			// System.currentTimeMillis());
			character.getMyCharacterVipManger().addKanQiFeibaohuBuffer();
		} else {
			return;
		}
		fighterVO.setHurtValue(2);
		int changeHp = changeNowHp(-fighterVO.getHurtValue());
		getEnmityManager().addEnmityValue(fighterVO.getFighter(), fighterVO.getEnmityValue());
		getEnmityManager().addHurtValue(fighterVO.getFighter(), -changeHp);
		FightMsgSender.directHurtBroadCase(fighterVO.getSkill() == null ? 0 : fighterVO.getSkill().getId(), fighterVO.getSponsor(), this, 0, fighterVO.getBaoji());
		if (isDie()) {
			die(fighterVO.getFighter());
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		SceneBangqiMonster t = new SceneBangqiMonster();
		t.setId(this.getId());
		t.setModel(this.getModel());
		t.setX(this.getX());
		t.setY(this.getY());
		t.setScene(this.getScene());
		t.setOriginalX(this.getOriginalX());
		t.setOriginalY(this.getOriginalY());
		t.setRelive(this.getRelive());
		t.setIs(this.getIs());
		// t.dropGoodJiacheng=10000;
		t.setMonsterModel(this.getMonsterModel());
		t.setSceneBangqi(this.sceneBangqi);
		return t;
	}

	@Override
	public void onBeHiddenAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {
	}

	@Override
	public int getNowHp() {
		if (this.sceneBangqi == null) {
			return 0;
		}
		return this.sceneBangqi.getNowHp();
	}

	@Override
	public int getMaxHp() {
		if (this.sceneBangqi == null) {
			return 0;
		}
		return this.sceneBangqi.getMaxHp();
	}

	@Override
	public void onEnterScene(Scene scene) {
		super.onEnterScene(scene);
		if (this.sceneBangqi != null) {
			this.sceneBangqi.addSceneBangqiMonster(this);
		}
	}

	@Override
	public void onLeaveScene(Scene scene, Scene newscene) {
		SceneBangqiMonster monster = scene.getSceneObj(SceneObj.SceneObjType_MonsterBangqi, this.getId());
		monster.getEyeShotManager().onLeaveScene(scene);
		monster.getSceneBangqi().removeSceneBangqiMonster(monster);
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	/**
	 * 发送帮旗视野消息
	 * 
	 * @param msg
	 */
	public void sendBangqiEyeShotMsg(ServerResponse msg) {
		this.sceneBangqi.sendBangqiEyeShotMsg(msg);
	}

	public boolean isPkTime() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (day == 2 || day == 4 || day == 6) {// 只有每周一，每周三，每周五晚上20：00—22：00可以砍旗
			int hours = calendar.get(Calendar.HOUR_OF_DAY);
			if (hours < 20 || hours >= 22) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	public void update(long now) {
		super.update(now);
		try {
			timerRenewNowHp();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void timerRenewNowHp() {
		int bline = sceneBangqi.getLine();
		if (bline != getLineId()) {
			return;
		}
		sceneBangqi.timerBangqiNowHp(this);
	}

	public ResponseMsg getEnterEyeshotMsg() {
		return new MonsterBangqiEnterEyeShot10036(this);
	}

	private static final int[] heedSceneObjects = { SceneObj.SceneObjType_Hero };

	@Override
	public int[] getHeedSceneObject() {
		return heedSceneObjects;
	}

	public void setObjectState(int status) {
		if (VisibleObjectState.Die != status) {
			status = VisibleObjectState.Idle;
		}
		super.setObjectState(status);
	}

	public int getSceneObjType() {
		return SceneObjType_MonsterBangqi;
	}
}
