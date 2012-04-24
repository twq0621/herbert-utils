package net.snake.gamemodel.faction.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.persistence.BangqiPositionManager;
import net.snake.gamemodel.faction.persistence.FactionManager;
import net.snake.gamemodel.faction.response.FactionFlagRenewHpMsg51102;
import net.snake.gamemodel.faction.response.FactionMsg51072;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.ibatis.IbatisEntity;
import net.snake.netio.ServerResponse;

public class SceneBangqi implements IbatisEntity {

	/**
	 * t_scene_bangqi.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 帮旗关联位置id t_scene_bangqi.f_bangqi_position_id
	 * 
	 */
	private Integer bangqiPositionId;
	/**
	 * 帮旗当前血量 t_scene_bangqi.f_now_hp
	 * 
	 */
	private Integer nowHp;
	/**
	 * 帮旗所属帮会 t_scene_bangqi.f_faction_id
	 * 
	 */
	private Integer factionId;
	/**
	 * 插旗线路 t_scene_bangqi.f_line
	 * 
	 */
	private Integer line;
	/**
	 * 血量上限 t_scene_bangqi.f_max_hp
	 * 
	 */
	private Integer maxHp;

	/**
	 * t_scene_bangqi.f_id
	 * 
	 * @return the value of t_scene_bangqi.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_scene_bangqi.f_id
	 * 
	 * @param id
	 *            the value for t_scene_bangqi.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 帮旗关联位置id t_scene_bangqi.f_bangqi_position_id
	 * 
	 * @return the value of t_scene_bangqi.f_bangqi_position_id
	 */
	public Integer getBangqiPositionId() {
		return bangqiPositionId;
	}

	/**
	 * 帮旗关联位置id t_scene_bangqi.f_bangqi_position_id
	 * 
	 * @param bangqiPositionId
	 *            the value for t_scene_bangqi.f_bangqi_position_id
	 */
	public void setBangqiPositionId(Integer bangqiPositionId) {
		this.bangqiPositionId = bangqiPositionId;
	}

	/**
	 * 帮旗当前血量 t_scene_bangqi.f_now_hp
	 * 
	 * @return the value of t_scene_bangqi.f_now_hp
	 */
	public Integer getNowHp() {
		return nowHp;
	}

	/**
	 * 帮旗当前血量 t_scene_bangqi.f_now_hp
	 * 
	 * @param nowHp
	 *            the value for t_scene_bangqi.f_now_hp
	 */
	public void setNowHp(Integer nowHp) {
		this.nowHp = nowHp;
	}

	/**
	 * 帮旗所属帮会 t_scene_bangqi.f_faction_id
	 * 
	 * @return the value of t_scene_bangqi.f_faction_id
	 */
	public Integer getFactionId() {
		return factionId;
	}

	/**
	 * 帮旗所属帮会 t_scene_bangqi.f_faction_id
	 * 
	 * @param factionId
	 *            the value for t_scene_bangqi.f_faction_id
	 */
	public void setFactionId(Integer factionId) {
		this.factionId = factionId;
	}

	/**
	 * 插旗线路 t_scene_bangqi.f_line
	 * 
	 * @return the value of t_scene_bangqi.f_line
	 */
	public Integer getLine() {
		return line;
	}

	/**
	 * 插旗线路 t_scene_bangqi.f_line
	 * 
	 * @param line
	 *            the value for t_scene_bangqi.f_line
	 */
	public void setLine(Integer line) {
		this.line = line;
	}

	/**
	 * 血量上限 t_scene_bangqi.f_max_hp
	 * 
	 * @return the value of t_scene_bangqi.f_max_hp
	 */
	public Integer getMaxHp() {
		return maxHp;
	}

	/**
	 * 血量上限 t_scene_bangqi.f_max_hp
	 * 
	 * @param maxHp
	 *            the value for t_scene_bangqi.f_max_hp
	 */
	public void setMaxHp(Integer maxHp) {
		this.maxHp = maxHp;
	}

	private BangqiPosition bangqiPosition;
	private FactionController factionController;
	private int sendHpJingBaoCount = 0;

	public BangqiPosition getBangqiPosition() {
		if (this.bangqiPosition == null) {
			this.bangqiPosition = BangqiPositionManager.getInstance().getBangqiPositionById(this.bangqiPositionId);
		}
		return bangqiPosition;
	}

	public FactionController getFactionController() {
		if (this.factionController == null) {
			this.factionController = FactionManager.getInstance().getFactionControllerByFactionID(this.factionId);
		}
		return factionController;
	}

	public SceneBangqi() {

	}

	public SceneBangqi(BangqiPosition position, int lineId, FactionController factionController2) {
		this.bangqiPositionId = position.getId();
		this.line = lineId;
		this.factionId = factionController2.getFaction().getId();
		this.factionController = factionController2;
		int maxHp = factionController2.getFaction().getFactionFlag().getfBangqiMaxhp();
		this.nowHp = maxHp;
		this.maxHp = maxHp;
	}

	public synchronized void changeNowHp(int changeV) {
		if (this.getNowHp() + changeV <= 0) {
			changeV = 0 - this.getNowHp();
			this.setNowHp(0);
		} else {
			this.setNowHp(this.getNowHp() + changeV);
		}
		int jibenHp = this.getMaxHp() / 10;
		if (this.getNowHp() + jibenHp * (sendHpJingBaoCount + 1) < this.getMaxHp()) {
			sendHpJingBaoCount++;
			// 本帮在"
			// + this.getLine() + "线" + bangqiPosition.getSceneName()
			// + "地图" + bangqiPosition.getX() + ","
			// + bangqiPosition.getY() + "坐标的帮旗正被攻击，请前往保护，当前血量剩余"
			// + this.nowHp * 100 / this.maxHp + "%"
			getFactionController().sendFactionMsg(
					new FactionMsg51072(11027, this.getLine() + "", bangqiPosition.getSceneNameI18n(), bangqiPosition.getX() + "", "" + bangqiPosition.getY(),
							(this.nowHp * 100 / this.maxHp) + ""));
		}
	}

	private Map<Integer, SceneBangqiMonster> map = new ConcurrentHashMap<Integer, SceneBangqiMonster>();
	private long renewNowHp = 0;
	public static int timerTime = 60 * 1000;// 单位 毫秒

	public void addSceneBangqiMonster(SceneBangqiMonster monster) {
		map.put(monster.getLineId(), monster);
	}

	public void removeSceneBangqiMonster(SceneBangqiMonster monster) {
		map.remove(monster.getLineId());
	}

	public void sendBangqiEyeShotMsg(ServerResponse msg) {
		for (SceneBangqiMonster monster : map.values()) {
			monster.getEyeShotManager().sendMsg(msg);
		}
	}

	public void updateAllSceneBangqiMonsterBangqi(SceneBangqiMonster sceneMonster) {
		for (SceneBangqiMonster monster : map.values()) {
			monster.setMonsterModel(sceneMonster.getMonsterModel());
			monster.getPropertyAdditionController().recompute();
			monster.getEyeShotManager().sendMsg(monster.getEnterEyeshotMsg());
		}
	}

	public void timerBangqiNowHp(SceneBangqiMonster monster) {
		long now = System.currentTimeMillis();
		if (now - this.renewNowHp < timerTime) {
			return;
		}
		this.renewNowHp = now;
		if (this.maxHp == this.nowHp) {
			return;
		}
		renewNowHp(getFactionController().getFaction().getFactionFlag().getRenewHp());
		sendBangqiEyeShotMsg(new FactionFlagRenewHpMsg51102(monster));
	}

	private synchronized int renewNowHp(int addHp) {
		if (this.nowHp + addHp > this.maxHp) {
			addHp = this.maxHp - this.nowHp;
		}
		this.setNowHp(this.nowHp + addHp);
		return addHp;
	}

	public synchronized void nowHpToMax(Integer nowHp) {
		this.nowHp = nowHp;
	}
}
