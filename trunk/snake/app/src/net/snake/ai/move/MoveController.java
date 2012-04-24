package net.snake.ai.move;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.ai.astar.PathSplit;
import net.snake.ai.formula.DistanceFormula;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.response.hero.CharacterJump10064;
import net.snake.gamemodel.map.response.hero.CharacterMove10062;
import net.snake.gamemodel.map.response.monster.MonsterMove10094;
import net.snake.netio.ServerResponse;


/**
 * 移动控制器基类
 * 
 * @author serv_dev
 */
public class MoveController implements IMoveController {
	private static final Logger logger = Logger.getLogger(MoveController.class);
	/** 服务器将速度乘以这个容错系数,则一切速度同步正常 */
	private final static float rongcuo = 160f / 142;
	/** 走路的主角 **/
	private VisibleObject vo;
	/** 制造连续行走的路径储存空间（路径有可能被切割） */
	private Deque<MoveInfo> pathQueue = new ArrayDeque<MoveInfo>();//

	// 行走和跳跃共用的属性=================
	/** 当前移动方式 **/
	private int moveType;//
	/** 存放精灵<b>当前</b>行走线路 格式为 x1,y1,x2,y2,x3,y3.....即偶数为x坐标,奇数为y坐标 **/
	private short[] lines; //
							//
	/** 计时器,开始走walklines时的时间 */
	private long beginTime = 0;//
	// private int v = 160;// 移动的速度
	/** 是否允许移动 */
	private boolean enableMove = true;//
	// 行走专用的属性===============
	/** 当前的路径位置 */
	private int pathIndex = 0;//
	/** 已经计算出来的距离 */
	private float realDistance = 0;//

	// 跳跃专用的属性
	/** 上一次跳跃结束的时间 */
	private long previousJumpEndTime = 0;//
	/** 跳跃次数 */
	int continueJumpcount = 0;

	/** 获取当前正在行走的路径段 **/
	public short[] getLines() {
		return lines;
	}

	public void destory() {
		if (pathQueue != null) {
			pathQueue.clear();
			pathQueue = null;
		}
		lines = null;
	}

	@Override
	public boolean isEnableMove() {
		return enableMove;
	}

	/**
	 * 客户端保证所有移动都停止后,才开始的攻击,也就是攻击时角色所在的点,一定等于路径中的最后一点。 <br>
	 * 因此遇到攻击消息时,服务器判定路径差距,如果不是太大 就直接停止移动,更新为路径的最后点,否则不处理,等待之后的位置矫正
	 */
	public void checkToStopTailPoint() {
		if (isMoving()) {
			// 获得最后点
			short lastx = 0;
			short lasty = 0;
			if (pathQueue.size() > 0) {// 如果还有未走的路径段
				MoveInfo last = pathQueue.getLast();
				short[] lastpath = last.path;// 找到最后的路径段最后一个坐标
				lastx = lastpath[lastpath.length - 2];
				lasty = lastpath[lastpath.length - 1];
			} else {// 找到当前的路径段最后一个坐标
				lastx = lines[lines.length - 2];
				lasty = lines[lines.length - 1];
			}

			if (vo.getGridMaxDistance(lastx, lasty) < 10) {// 这个误差是可以忽略的
				vo.setX(lastx);
				vo.setY(lasty);
				vo.getEyeShotManager().onMove();// 我要停在这里了，让我看看你们都是谁。
				stopMove();
			} else {
				// 不处理,等待之后的位置矫正
			}
		}
	}

	@Override
	public void setEnableMove(boolean enable) {
		this.enableMove = enable;
		if (!enableMove) {
			stopMove();
		}
	}

	public MoveController(VisibleObject vo) {
		this.vo = vo;
	}

	@Override
	public long getPreviousJumpEndTime() {
		return previousJumpEndTime;
	}

	/** 是不是最后一个坐标 */
	private boolean isTailPoint(short startX, short startY, short[] path) {
		return startX == path[path.length - 2] && startY == path[path.length - 1];
	}

	@Override
	public void addWalkLines(short[] gopath) {
		continueJumpcount = 0;
		/* 调用前都判断过了,不再做判断gopath合法性了 */
		short startX = gopath[0];
		short startY = gopath[1];
		if (pathQueue.size() > 0) {// 还有没有走完的路
			short[] pathInQueue = pathQueue.getLast().path;

			if (isTailPoint(startX, startY, pathInQueue)) {// 是否紧接着最后一个坐标
				PathSplit.splitPathToQueue(pathQueue, gopath);
				return;
			}

			// 新添加的路径段没有在最后一个路径段之后，是不是在中间
			for (int i = 0; i < pathInQueue.length; i += 2) {
				if (pathInQueue[i] == startX && pathInQueue[i + 1] == startY) {// 如果这个新路径段从最后一个中间开始
					pathQueue.removeLast();// 移除最后的一个并重新制作新的路径段放到最后
					if (i > 0) {// 如果第一个点就一样,就不需要截断了
						short[] newpath = new short[i + 2];
						System.arraycopy(pathInQueue, 0, newpath, 0, newpath.length);
						pathQueue.add(MoveInfo.createWalkPath(newpath));
					}
					PathSplit.splitPathToQueue(pathQueue, gopath);
					return;
				}
			}

			// 新添加的路径段没有在最后一个路径段之后，也没有在最后一个路径段中间
			for (int i = 0; i < pathInQueue.length; i += 2) {
				if (Math.abs(startX - pathInQueue[i]) <= 1 && Math.abs(startY - pathInQueue[i + 1]) <= 1) {// 在某一个位置有所偏差
					// if (pathInQueue[i] == startX && pathInQueue[i + 1] ==
					// startY) {
					pathQueue.removeLast();// 移除并重新组织
					if (i > 0) {// 如果第一个点就一样,就不需要截断了
						short[] newpath = new short[i + 2];
						System.arraycopy(pathInQueue, 0, newpath, 0, newpath.length);
						pathQueue.add(MoveInfo.createWalkPath(newpath));
					}
					PathSplit.splitPathToQueue(pathQueue, gopath);
					return;
				}
			}
		} else {// 当前的路径里看看有没有这个点,有的话，改变一下路径引用
			// 先查是否首尾连接
			if (isTailPoint(startX, startY, lines)) {
				PathSplit.splitPathToQueue(pathQueue, gopath);
				return;
			}

			for (int i = 0; i < lines.length; i += 2) {//
				if (startX == lines[i] && startY == lines[i + 1]) {
					if (i > 0) {
						short[] newpath = new short[i + 2];
						System.arraycopy(lines, 0, newpath, 0, newpath.length);
						PathSplit.splitPathToQueue(pathQueue, gopath);
						lines = newpath;
						if (pathIndex >= lines.length) {
							pathIndex = lines.length - 2;
						}
					} else {
						internalSetMoveline(MoveInfo.createWalkPath(gopath));
					}
					return;
				}
			}

			for (int i = 0; i < lines.length; i += 2) {// 如果程序进行到这里，说明客户端发来的起始坐标不在先前的路径上，所以会产生一格子的跳点情况
				if (Math.abs(startX - lines[i]) <= 1 && Math.abs(startY - lines[i + 1]) <= 1) {
					// if (startX == lines[i] && startY == lines[i + 1]) {
					if (i > 0) {
						short[] newpath = new short[i + 2];
						System.arraycopy(lines, 0, newpath, 0, newpath.length);
						PathSplit.splitPathToQueue(pathQueue, gopath);
						lines = newpath;
						if (pathIndex >= lines.length) {
							pathIndex = lines.length - 2;
						}
					} else {
						internalSetMoveline(MoveInfo.createWalkPath(gopath));
					}
					return;
				}
			}
		}

	}

	@Override
	public void setJumpLines(short[] gopath, boolean is2jitiao, long begintime) {
		if (this.vo.getSceneRef().getInstanceModelId()!=16) {
			addAndcheckContinueJumpCount();
		}
		
		if (gopath.length == 4) {// 跳跃必须是4个点
			stopMove();
			internalSetMoveline(MoveInfo.createJumpPath(gopath, is2jitiao));
			this.beginTime = begintime;
			this.previousJumpEndTime = begintime;
		}
	}

	private void addAndcheckContinueJumpCount() {
		continueJumpcount++;
		boolean instance = vo.getSceneObjType() == SceneObj.SceneObjType_Hero;
		if (!instance) {
			return;
		}
		Hero c = (Hero) vo;
		if (c.getMaxJumpCount() == null || continueJumpcount > c.getMaxJumpCount()) {
			c.setMaxJumpCount(continueJumpcount);
			c.getMyCharacterAchieveCountManger().getCharacterOtherCount().jumpCount(continueJumpcount);
		}
	}

	public void setWalkLines(short[] gopath, long begintime) {
		continueJumpcount = 0;
		stopMove();
		if (gopath != null && gopath.length > 3) {
			// 目标点
			if (gopath.length > PathSplit.MAXPATHPOINT) {
				PathSplit.splitPathToQueue(pathQueue, gopath);
				getNextPathSplit();
			} else {
				internalSetMoveline(MoveInfo.createWalkPath(gopath));
			}
			this.beginTime = begintime;

		}
	}

	@Override
	public boolean checkArrived() {
		return moveByTime();
	}

	/** 获得下一个路径分断 */
	private void getNextPathSplit() {
		MoveInfo mi = pathQueue.poll();
		if (mi != null) {
			internalSetMoveline(mi);
		} else {
			lines = null;
			moveType = MoveInfo.TYPE_STAND;
		}
	}

	private void internalSetMoveline(MoveInfo mi) {
		lines = mi.path;
		moveType = mi.moveType;
		pathIndex = 0;
		realDistance = 0;

		if (moveType == MoveInfo.TYPE_JUMP || moveType == MoveInfo.TYPE_JUMP2) {
			vo.setLocation(Location.Location_KONG);
		} else {
			vo.setLocation(Location.Location_DI);
		}
		vo.setX(lines[0]);
		vo.setY(lines[1]);
		broadMovePath(mi);
	}

	@Override
	public void stopMove() {
		if (moveType == MoveInfo.TYPE_WALK) {
			lines = null;
			moveType = MoveInfo.TYPE_STAND;
		}
		pathQueue.clear();

	}

	/**
	 * {@inheritDoc} <br>
	 * 当前路径段为空，或者没有下一段路径
	 */
	@Override
	public boolean isMoving() {
		return lines != null || pathQueue.size() > 0;
	}

	@Override
	public int getMovingType() {
		return moveType;
	}

	@Override
	public ServerResponse getMoveMsg() {
		if (isMoving()) {
			if (moveType == MoveInfo.TYPE_WALK) {
				if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
					return new CharacterMove10062(vo.getId(), lines, pathIndex);
				} else if (vo.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
					return new MonsterMove10094(vo.getId(), lines, pathIndex);
				}
			} else {
				if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
					return new CharacterJump10064(vo.getId(), vo.getX(), vo.getY(), lines[2], lines[3], moveType == MoveInfo.TYPE_JUMP2);
				}
			}
		}
		return null;
	}

	@Override
	public int getV() {
		return vo.getPropertyAdditionController().getExtraMoveSpeed();
	}

	private void broadMovePath(MoveInfo mi) {
		if (mi.moveType == MoveInfo.TYPE_WALK) {
			if (vo.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
				vo.getEyeShotManager().sendMsg(new MonsterMove10094(vo.getId(), mi.path));
			} else if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterMove10062(vo.getId(), mi.path, pathIndex), (Hero) vo);
			} 
		} else {
			if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				vo.getEyeShotManager().sendMsg(new CharacterJump10064(vo.getId(), mi), (Hero) vo);
			} 
		}
	}

	/** 如果走完路径了,则返回true,否则返回false */
	protected boolean moveByTime() {
		if (!isMoving()) {
			return true;
		}
		if (moveType == MoveInfo.TYPE_WALK) {
			return walkMoveByTime();
		} else {
			return jumpMoveByTime();
		}
	}

	/** 最大跳跃速度 */
	private static final int J = 450;
	/** 某一行走速度 */
	private static final int crossX = 320;
	/** 某一跳跃速度(此值应大于0小于J) */
	private static final int crossY = 350;
	/**
	 * 反比例函数系数.此值应根据x=crossX时，期望的跳跃速度y=crossY时确定,由此公式：y = J + K/(x-K/J)推算出K = (J*x*y-j*j*x)/y代入即可算出
	 */
	private static final float K = (J * crossX * crossY - J * J * crossX) / (float) crossY;
	private static final int shortestJumpTime = 500;

	private int getJumpSpeed() {
		float xishu = moveType == MoveInfo.TYPE_JUMP2 ? 1.5f : 1;
		return (int) ((J + K / (getV() - K / J)) * xishu);
	}

	private boolean jumpMoveByTime() {
		int v = getJumpSpeed();
		long now = System.currentTimeMillis();
		int goTime = (int) (now - beginTime);// 已经过去的总时间
		int currentS = v * goTime / 1000; // 理论上应该走过的路径

		// 总路径长为
		float totollength = DistanceFormula.getDistance2(lines[0], lines[1], lines[2], lines[3]) * ClientConfig.TileSIZE;
		float percent = currentS / totollength;
		if (percent > 1) {// 走完了
			vo.setX(lines[2]);
			vo.setY(lines[3]);
			if (goTime <= shortestJumpTime) {// 时间太短了,要延迟落地
				GameServer.executorService.schedule(new Runnable() {
					@Override
					public void run() {
						vo.setLocation(Location.Location_DI);
					}
				}, shortestJumpTime - goTime, TimeUnit.MILLISECONDS);
				// vo.getUpdateObjManager().addFrameUpdateLaterTask(
				// new Runnable() {
				// @Override
				// public void run() {
				// vo.setLocation(Location.Location_DI);
				// }
				// }, shortestJumpTime - goTime);
			} else {
				vo.setLocation(Location.Location_DI);
			}
			vo.onMove();
			if (logger.isDebugEnabled()) {
				if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
					logger.debug("character jump x:" + vo.getX() + " y:" + vo.getY());
				} else if (vo.getSceneObjType() == SceneObj.SceneObjType_Horse) {
					logger.debug("horse jump x:" + vo.getX() + " y:" + vo.getY());
				}
			}

			return hasNextMoveInfo();
		} else {
			vo.setX((short) (lines[0] + (lines[2] - lines[0]) * percent));
			vo.setY((short) (lines[1] + (lines[3] - lines[1]) * percent));
			vo.onMove();
			if (logger.isDebugEnabled()) {
				if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
					logger.debug("character jump x:" + vo.getX() + " y:" + vo.getY());
				} else if (vo.getSceneObjType() == SceneObj.SceneObjType_Horse) {
					logger.debug("horse jump x:" + vo.getX() + " y:" + vo.getY());
				}
			}
			return false;
		}
	}

	private boolean walkMoveByTime() {
		long now = System.currentTimeMillis();
		float goTime = (float) (now - beginTime);// 已经过去的总时间

		float currentS = getV() * rongcuo * goTime / 1000; // 理论上应该走过的路径
		if (pathIndex + 2 < lines.length) {// 路径还没有 走完,开始走
			// 计算当前点到下一个点的距离,以像素计
			// float distance = getDistance();
			float distance = DistanceFormula.getDistance2(lines[pathIndex], lines[pathIndex + 1], lines[pathIndex + 2], lines[pathIndex + 3]) * ClientConfig.TileSIZE;
			// 理论上应该走过的路径>已实施的路径+下一格的路径时,换下一点

			while (currentS >= realDistance + distance && pathIndex + 2 < lines.length) {
				realDistance = realDistance + distance;// 已经走了这么长
				vo.setX(lines[pathIndex + 2]);
				vo.setY(lines[pathIndex + 3]);
				vo.onMove();
				if (logger.isDebugEnabled()) {
					if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {
						logger.debug("character walk x:" + vo.getX() + " y:" + vo.getY());
					} else if (vo.getSceneObjType() == SceneObj.SceneObjType_Horse) {
						logger.debug("horse walk x:" + vo.getX() + " y:" + vo.getY());
					}
				}

				pathIndex = pathIndex + 2;
				if (pathIndex + 2 < lines.length) {// 设置下一段距离
					// distance = getDistance();
					distance = DistanceFormula.getDistance2(lines[pathIndex], lines[pathIndex + 1], lines[pathIndex + 2], lines[pathIndex + 3]) * ClientConfig.TileSIZE;
				} else {// 本路径已经走到头了
					break;
				}
			}
		}

		if (pathIndex + 2 < lines.length) {
			// 因为时间片不够,本次循环不能再走了
			return false;
		}
		// 当前路径走完了
		return hasNextMoveInfo();

	}

	private boolean hasNextMoveInfo() {
		getNextPathSplit();
		if (lines == null) {
			return true;
		} else {
			beginTime = System.currentTimeMillis();
			return false;
		}
	}

	// private float getDistance() {
	// return DistanceFormula.getDistance2(lines[pathIndex],
	// lines[pathIndex + 1], lines[pathIndex + 2],
	// lines[pathIndex + 3])
	// * ClientConfig.TileSIZE;
	// }

	@Override
	public void instantMove(short x, short y) {
		stopMove();
		vo.setX(x);
		vo.setY(y);
		vo.onMove();
		vo.getEyeShotManager().sendMsg(vo.getInstanceMoveMsg());
	}

}
