package net.snake.gamemodel.monster.logic;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.ai.fight.bean.FighterVO;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.persistence.MyFactionCityZhengDuoManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.response.monster.MonsterFactionCtEnterEyeShot10038;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.netio.message.ResponseMsg;



public class SceneFactionCtMonster extends SceneMonster {
	private static Logger logger = Logger.getLogger(SceneFactionCtMonster.class);
	public long timeOutTime = 20 * 60 * 1000;
	public int tishiCount = 0;
	private Hero youlongCharacter; // 拿到剑的人
	private long startCatchYoulongTime = 0;// 拿到剑时间
	WhoCatchYoulongManager whoCatchYoulongManager = new WhoCatchYoulongManager(this);

	public ResponseMsg getEnterEyeshotMsg() {
		return new MonsterFactionCtEnterEyeShot10038(this);
	}

	@Override
	public void fireAttacking(VisibleObject _attacter) {
	}

	/**
	 * 死亡 1.死亡掉落物品 2.获得经验 3.清空之前身上所有的列表数据
	 */
	@Override
	public void die(VisibleObject visibleObject) {
	}

	@Override
	public void onBeAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {
	}

	public void catchyoulong(Hero character) {
		this.youlongCharacter = character;
		this.startCatchYoulongTime = System.currentTimeMillis();
		MyFactionCityZhengDuoManager.monster = this;
		MyFactionCityZhengDuoManager.youlongWeiBaMonster = null;
		character.getMyFactionCityZhengDuoManager().addCatchHuYoulongBuffer();
		character.getMyFactionManager().takeOnYouLongInGongcheng();
	}

	/**
	 * 持剑者剑掉落 回到苍龙谭
	 */
	public void dropYoulong(Hero other) {
		enterToScene(other);
	}

	public void enterToScene(Hero other) {
		if (this.youlongCharacter != null) {
			this.youlongCharacter.getMyFactionManager().takeOffYoulong();
//			GameServer.vlineServerManager.sendMsgToAllLineServer(new FactionCtMsg51140(null));
			String name = "";
			if (other != null) {
				name = other.getViewName();
			}
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14522, this.youlongCharacter.getViewName(), name));
			this.youlongCharacter = null;
		}
		MyFactionCityZhengDuoManager.monster = null;
		Scene scene = this.getSceneRef();
		scene.enterScene(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.bean.monster.SceneMonster#update(long)
	 */
	@Override
	public void update(long now) {
		super.update(now);
		updateTishiMsg();
	}

	/**
	 * 检测游龙之刃在帮战地图时间
	 */
	public void updateTishiMsg() {
		try {
			if (MyFactionCityZhengDuoManager.monster == null) {
				if (System.currentTimeMillis() - this.dieTime > timeOutTime) {
					if (tishiCount >= 2) {
						return;
					}
					tishiCount++;
					GongchengTsMap scene = (GongchengTsMap) this.getSceneRef();
					scene.endGongcheng();
					return;
				}
				if (System.currentTimeMillis() - this.dieTime > timeOutTime - 60 * 3 * 1000) {
					if (tishiCount >= 1) {
						return;
					}
					tishiCount++;
					GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14710));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.bean.monster.SceneMonster#onEnterScene(net.snake.bean.scenes .Scene)
	 */
	@Override
	public void onEnterScene(Scene scene) {
		super.onEnterScene(scene);
		this.dieTime = System.currentTimeMillis();
		this.tishiCount = 0;
		MyFactionCityZhengDuoManager.youlongWeiBaMonster = this;
		this.isCatchTishi = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.bean.monster.SceneMonster#onLeaveScene(net.snake.bean.scenes .Scene, net.snake.bean.scenes.Scene)
	 */
	@Override
	public void onLeaveScene(Scene scene, Scene newscene) {
		super.onLeaveScene(scene, newscene);
		this.isCatchTishi = false;
	}

	public WhoCatchYoulongManager getWhoCatchYoulongManager() {
		return whoCatchYoulongManager;
	}

	public void setWhoCatchYoulongManager(WhoCatchYoulongManager whoCatchYoulongManager) {
		this.whoCatchYoulongManager = whoCatchYoulongManager;
	}

	public Hero getYoulongCharacter() {
		return youlongCharacter;
	}

	public void setYoulongCharacter(Hero youlongCharacter) {
		this.youlongCharacter = youlongCharacter;
	}

	public long getStartCatchYoulongTime() {
		return startCatchYoulongTime;
	}

	public long getWeiBuchuTime() {
		return System.currentTimeMillis() - dieTime;
	}

	public void setStartCatchYoulongTime(long startCatchYoulongTime) {
		this.startCatchYoulongTime = startCatchYoulongTime;
	}

	private static final int[] heedSceneObjects = { SceneObj.SceneObjType_Hero };

	@Override
	public int[] getHeedSceneObject() {
		return heedSceneObjects;
	}

	public boolean isCatchTishi = false; // true 表示已经提示过

	/**
	 * 持有时提示消息
	 */
	public void updateCacthTimeTishi() {
		if (isCatchTishi) {
			return;
		}
		isCatchTishi = true;
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14711));
	}

	public int getSceneObjType() {
		return SceneObjType_MonsterFactionCt;
	}
}
