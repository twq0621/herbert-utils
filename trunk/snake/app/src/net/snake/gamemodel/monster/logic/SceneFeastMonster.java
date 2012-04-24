package net.snake.gamemodel.monster.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.ai.astar.Point;
import net.snake.ai.formula.CharacterFormula;
import net.snake.ai.formula.DistanceFormula;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.map.response.monster.MonsterFeastEnterEyeShot10048;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.wedding.bean.FeastPlayConfig;
import net.snake.gamemodel.wedding.persistence.WedFeast;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.netio.message.ResponseMsg;



/**
 * 婚宴怪
 * 
 * @author serv_dev
 */
public class SceneFeastMonster extends SceneMonster {
	List<Integer> joiner = new ArrayList<Integer>();
	WedFeast feast;
	Point point;

	private Logger log = Logger.getLogger(getClass());

	/**
	 * 
	 * @param feast
	 *            所属婚宴
	 * @param point
	 *            位置
	 * @param count
	 *            波数
	 */
	public SceneFeastMonster(WedFeast feast, Point point, int count) {
		this.feast = feast;
		this.point = point;
		setId(SceneMonster.getNewID());
		setX(getPoint().getX());
		setY(getPoint().getY());
		setOriginalX(getX());
		setOriginalY(getY());
		setMonsterModel(feast.getMonsterModel());
		setReplaceName(feast.getWedFeastName() + "第" + count + "波");
		init();
		if (log.isDebugEnabled()) {
			log.debug(this.geReplacetName() + point + "");
		}
	}

	public boolean eatFeast(Hero joiner) {
		if (isDie() || getNowHp() <= 0) {
			joiner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50038));
			return false;
		}
		if (joiner.getId().equals(getFeast().getApplyerId()) || joiner.getId().equals(getFeast().getMateId())) {
			joiner.sendMsg(new PrompMsg(50027));// 请把宴席留给客人吧
			return false;
		}
		if (!getFeast().isGift(joiner)) {// 是否给过红包
			joiner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50028));
			return false;
		}
		if (isJoin(joiner.getId())) {// 是否食用过
			joiner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50039));
			return false;
		}
		if (DistanceFormula.getDistanceRound(getX(), getY(), joiner.getX(), joiner.getY()) > ClientConfig.FEAST_EAT_MAX_LANG) {// 距离
																																// 检查
			// 距离过远
			joiner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 50025));
			return false;
		}
		Integer feastLower = getConfig().getFeastLower();
		// 当日上限检查
		int feastExp = CharacterFormula.getFeastExp(joiner);
		boolean flag = false;
		int realexp = 0;
		int realzhenqi = 0;
		if (checkMaxExp(joiner, feastExp)) {
			flag = true;
			try {
				CharacterFormula.experienceProcess(joiner, feastExp);
				joiner.getDayInCome().dealFeastExp(feastExp);
				realexp = feastExp;
			} catch (Exception e) {
				log.warn("增加食用婚宴经验失败", e);
			}
		} else {
			joiner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50036));
			// 经验达到每日最大值通知
		}
		int feastZhenQi = CharacterFormula.getFeastZhenQi(joiner);
		if (checkMaxZQ(joiner, feastZhenQi)) {
			flag = true;
			CharacterPropertyManager.changeZhenqi(joiner, feastZhenQi);
			joiner.getDayInCome().dealFeastZhenqi(feastZhenQi);
			realzhenqi = feastZhenQi;
		} else {
			joiner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50037));
			// 真气达到最大值
		}
		joiner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50034, realexp + "", realzhenqi + ""));
		if (flag) {
			Integer applyerId = getFeast().getApplyerId();
			Hero applyer = GameServer.vlineServerManager.getCharacterById(applyerId);
			if (applyer != null) {
				// 如果玩家在线
				int ownerExp = CharacterFormula.getFeastOwnerExp(applyer.getGrade());
				int ownerZhenQi = CharacterFormula.getFeastOwnerZhenQi(applyer.getGrade());
				try {
					if (checkMaxExp(applyer, ownerExp)) {
						CharacterFormula.experienceProcess(applyer, ownerExp);
						applyer.getDayInCome().dealFeastExp(ownerExp);
					} else {
						ownerExp = 0;
					}
					if (checkMaxZQ(applyer, ownerZhenQi)) {
						ownerZhenQi = CharacterPropertyManager.changeZhenqi(applyer, ownerZhenQi);
						applyer.getDayInCome().dealFeastZhenqi(ownerZhenQi);
					} else {
						ownerZhenQi = 0;
					}
					applyer.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50035, ownerExp + "", ownerZhenQi + ""));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
			Integer mateId = getFeast().getMateId();
			Hero mate = GameServer.vlineServerManager.getCharacterById(mateId);
			if (mate != null) {
				// 如果玩家在线
				int ownerExp = CharacterFormula.getFeastOwnerExp(mate.getGrade());
				int ownerZhenQi = CharacterFormula.getFeastOwnerZhenQi(mate.getGrade());
				try {
					if (checkMaxExp(mate, ownerExp)) {
						CharacterFormula.experienceProcess(mate, ownerExp);
						mate.getDayInCome().dealFeastExp(ownerExp);
					} else {
						ownerExp = 0;
					}
					if (checkMaxZQ(mate, ownerZhenQi)) {
						CharacterPropertyManager.changeZhenqi(mate, ownerZhenQi);
						mate.getDayInCome().dealFeastZhenqi(ownerZhenQi);
					} else {
						ownerZhenQi = 0;
					}
					mate.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50035, ownerExp + "", ownerZhenQi + ""));
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}

			addJoin(joiner.getId());
			getFeast().setJoinCount(getFeast().getJoinCount() + 1);
			WedFeastManager.getInstance().updateFeast(getFeast());
			// 婚宴怪减血
			changeNowHp(-feastLower);
			if (isDie()) {
				setObjectState(VisibleObjectState.Dispose);
			}
			return true;
		}
		return false;
	}

	public void addJoin(int roleid) {
		joiner.add(roleid);
	}

	private boolean checkMaxExp(Hero joiner, int addvalue) {
		Integer dayExp = joiner.getDayInCome().getCountData().getfFeastExp();
		if (dayExp + addvalue > CharacterFormula.getDayMaxFeastExp(joiner)) {
			return false;
		}
		return true;
	}

	private boolean checkMaxZQ(Hero joiner, int addvalue) {
		Integer dayExp = joiner.getDayInCome().getCountData().getfFeastZhengqi();
		if (dayExp + addvalue > CharacterFormula.getDayMaxFeastZhenQi(joiner)) {
			return false;
		}
		return true;
	}

	public boolean isJoin(int roleid) {
		return joiner.contains(roleid);
	}

	/**
	 * 剩余份数
	 * 
	 * @return
	 */
	public int getSurplus() {
		return getNowHp() / getConfig().getFeastLower();
	}

	/** 进入他人视野发送的消息 **/
	@Override
	public ResponseMsg getEnterEyeshotMsg() {
		return new MonsterFeastEnterEyeShot10048(getId(), getModel(), geReplacetName(), getX(), getY(), getFeast().getTempID());
	}

	public WedFeast getFeast() {
		return feast;
	}

	public Point getPoint() {
		return point;
	}

	private FeastPlayConfig getConfig() {
		return getFeast().getConfig();
	}

	public int getSceneObjType() {
		return SceneObjType_MonsterFeast;
	}
}
