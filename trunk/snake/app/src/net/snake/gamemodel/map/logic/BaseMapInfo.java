package net.snake.gamemodel.map.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.snake.ai.astar.AStarNewArithmetic;
import net.snake.ai.astar.Point;
import net.snake.ai.formula.DistanceFormula;
import net.snake.ai.util.BrokenLine;
import net.snake.commons.GenerateProbability;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.monster.bean.SceneMonster;

import org.apache.log4j.Logger;

/**
 * 该类为地图的基本信息
 * 
 * @author serv_dev
 * 
 */
public abstract class BaseMapInfo implements Scene, Cloneable {
	private static Logger logger = Logger.getLogger(BaseMapInfo.class);

	/***/
	protected static final long refershCheck = 1 * 60 * 1000;
	/** 所有的传送点 */
	protected Set<MapTeleport> teleports4set = new HashSet<MapTeleport>();//
	/** 地图id */
	protected int id;//
	/** 地图名称 */
	protected String name;//
	/***/
	protected String showName;
	/** 地图描述 */
	protected String description;//
	/** 纵向数点vertical */
	protected short grid_v;//
	/** 横向数点horizontal */
	protected short grid_h;//
	/** 复活点区域中心点x;如果是副本入口地图，也需要有进入点，因为有的副本并不通过传送点进去 */
	protected short reliveX = -1; //
	/** 复活点区域中心点Y;如果是副本入口地图，也需要有进入点，因为有的副本并不通过传送点进去 */
	protected short reliveY = -1; //
									//
	/***/
	protected int characterEyeShotLimit = 0;
	/** 所有的逻辑坐标格 */
	protected short[][] blockTiles; //
	/** 缓存每个格子的周边逻辑格的状态 */
	protected byte[][] cacheMap; //
	/** 是副本场景吗 不等于0则为副本场景 */
	protected int instanceModelId = 0;
	/** 入口点x */
	protected short enterX;//
	/** 入口点y */
	protected short enterY;//
	/** 场景pvp模式 1标识可pk （自由模式）0标识不可pk(和平模式) */
	protected int pvpModel;//
	// protected boolean enableLocationMonitor = false;// 是否开启地图位置监控
	/***/
	protected int reliveSceneId;
	/***/
	protected List<SceneMonster> allMonsterWillAddToScene;
	/** 进入等级限制 */
	private Integer enterLevelLimit; //
	/** 刷怪描述 */
	private String monsterDesc; //
	/** 练级等级描述 */
	private String exerciseDesc;//
	/** boss描述 */
	private String bossDesc; //
	/** boss刷新时间描述 */
	private String bossTimeDesc;//
	/** boss物品掉落描述 */
	private String bossDropGoods;//
	/** 是否死亡pk保护 0保护 1不保护 */
	private int pkProtect;//
	/** pk等级保护 */
	private boolean pkGradeProtect;//
	/** 当前地图玩家是否已经进入 */
	private int instanceCount = 0;//
	/***/
	private int[][] centerRandomPoints;
	/** 经验加倍区 */
	protected int[][] expTiles; //
	/** 跨服战复活区 1无剑人员复活区 2有剑人员复活区 */
	protected int[][] fuhuoTiles; //
	/** 无剑人员复活区 */
	protected List<int[]> wujianFuHuo;//
	/** 有剑人员复活区 */
	protected List<int[]> youjianFuHuo;//

	public List<int[]> getWujianFuHuo() {
		return wujianFuHuo;
	}

	public void setWujianFuHuo(List<int[]> wujianFuHuo) {
		this.wujianFuHuo = wujianFuHuo;
	}

	public List<int[]> getYoujianFuHuo() {
		return youjianFuHuo;
	}

	public void setYoujianFuHuo(List<int[]> youjianFuHuo) {
		this.youjianFuHuo = youjianFuHuo;
	}

	@Override
	public int[][] getFuhuoTiles() {
		return fuhuoTiles;
	}

	public void setFuhuoTiles(int[][] fuhuoTiles) {
		this.fuhuoTiles = fuhuoTiles;
	}

	@Override
	public boolean isPkGradeProtect() {
		return pkGradeProtect;
	}

	public void setPkGradeProtect(boolean pkGradeProtect) {
		this.pkGradeProtect = pkGradeProtect;
	}

	public boolean isPkProtect() {
		return pkProtect == 0;
	}

	public void setPkProtect(int pkProtect) {
		this.pkProtect = pkProtect;
	}

	@Override
	public Set<MapTeleport> getTeleports4set() {
		return teleports4set;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Set getAllTeleports() {
		return teleports4set;
	}

	public short getReliveX() {
		return reliveX;
	}

	public short getReliveY() {
		return reliveY;
	}

	public int getReliveSceneId() {
		return reliveSceneId;
	}

	public void setReliveSceneId(int reliveSceneId) {
		this.reliveSceneId = reliveSceneId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public void setAllMonsterWillAddToScene(List<SceneMonster> allMonsterWillAddToScene) {
		this.allMonsterWillAddToScene = allMonsterWillAddToScene;
	}

	public List<SceneMonster> getAllMonsterWillAddToScene() {
		return allMonsterWillAddToScene;
	}

	private Teleport teleporttoWorld = null;

	public short[] findWay(short fromX, short fromY, short toX, short toY) {
		if (fromX == toX && fromY == toY) {
			return null;
		}
		if (!isPermitted(fromX, fromY) || !isPermitted(toX, toY))
			return null;
		return AStarNewArithmetic.search(toX, toY, fromX, fromY, grid_h, grid_v, blockTiles, cacheMap);
	}

	public List<Point> findWayII(short fromX, short fromY, short toX, short toY) {
		if (fromX == toX && fromY == toY) {
			return null;
		}
		if (!isPermitted(fromX, fromY) || !isPermitted(toX, toY))
			return null;
		return AStarNewArithmetic.lowPath(toX, toY, fromX, fromY, grid_h, grid_v, blockTiles, cacheMap);
	}

	public short[][] getBlockTiles() {
		return blockTiles;
	}

	public short getEnterX() {
		return enterX;
	}

	public void setEnterX(short enterX) {
		this.enterX = enterX;
	}

	public short getEnterY() {
		return enterY;
	}

	public void setEnterY(short enterY) {
		this.enterY = enterY;
	}

	@Override
	public int[][] getExpTiles() {
		return expTiles;
	}

	public void setExpTiles(int[][] expTiles) {
		this.expTiles = expTiles;
	}

	public void setTiles(short[][] blockTiles) {
		this.blockTiles = blockTiles;
	}

	public void addTeleport(MapTeleport teleport) {
		teleports4set.add(teleport);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public int getCharacterEyeShotLimit() {
		return characterEyeShotLimit;
	}

	public void setCharacterEyeShotLimit(int characterEyeShotLimit) {
		this.characterEyeShotLimit = characterEyeShotLimit;
	}

	public Teleport getTeleportInstanceToWorld() {
		// 不是副本地图没有此功能,
		if (!isInstanceScene()) {
			return null;
		}
		if (teleporttoWorld != null) {
			return teleporttoWorld;
		}
		for (Teleport teleport : teleports4set) {
			Scene scene = getSceneManager().getSceneByID(teleport.getTargetSceneID());
			if (scene != null && !scene.isInstanceScene()) {
				teleporttoWorld = teleport;
				break;
			}
		}
		return teleporttoWorld;
	}

	public boolean isInstanceScene() {
		return instanceModelId != 0;
	}

	public void setPvpModel(int pvpModel) {
		this.pvpModel = pvpModel;
	}

	public void setInstanceModelId(int instanceModelId) {
		this.instanceModelId = instanceModelId;
	}

	public int getInstanceModelId() {
		return instanceModelId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getGrid_v() {
		return grid_v;
	}

	public short getGrid_h() {
		return grid_h;
	}

	public void setGrid_v(short gridV) {
		grid_v = gridV;
	}

	public void setGrid_h(short gridH) {
		grid_h = gridH;
	}

	public void setReliveX(short initX) {
		this.reliveX = initX;
	}

	public void setReliveY(short initY) {
		this.reliveY = initY;
	}

	public short getHeight() {
		return grid_v;
	}

	public short getWidth() {
		return grid_h;
	}

	public boolean isPermitted(short x, short y) {
		if (x < 0 || y < 0 || x >= grid_h || y >= grid_v) {
			return false;
		}
		return blockTiles[x][y] == 1 ? false : true;
	}

	public String toString() {
		return this.name + " id:" + id;
	}

	/**
	 * 缓存所有格子周边逻辑格的状态
	 * 
	 * @param cacheMap
	 * @param arr
	 */
	public void initAllPoint() {
		cacheMap = new byte[grid_h][grid_v];
		int xlen = blockTiles.length;
		for (short i = 0; i < xlen; i++) {
			int ylen = blockTiles[i].length;
			for (short j = 0; j < ylen; j++) {
				byte value = AStarNewArithmetic.checkBlockAroundPoint(i, j, xlen, ylen, blockTiles);
				cacheMap[i][j] = value;
			}
		}

		centerRandomPoints();
	}

	/**
	 * 将地图的中央部分切割出来，放进15个大小的数组里。 以方便取得随机地图点
	 * 
	 * @return
	 */
	public int[][] getCenterRandomPoints() {
		return centerRandomPoints;
	}

	public short[] getAroundPoint(short x, short y, int radius) {

		for (int i = 0; i < 8; i++) {
			int nextx = x;
			int nexty = y;
			switch (i) {
			case 0:
				nextx = nextx + radius;
				break;
			case 1:
				nexty = nexty + radius;
				break;
			case 2:
				nextx = nextx - radius;
				break;
			case 3:
				nexty = nexty - radius;
				break;
			case 4:
				nextx = nextx + radius;
				nexty = nexty + radius;
				break;
			case 5:
				nextx = nextx + radius;
				nexty = nexty - radius;
				break;
			case 6:
				nextx = nextx - radius;
				nexty = nexty + radius;
				break;
			case 7:
				nextx = nextx - radius;
				nexty = nexty - radius;
				break;
			default:
				break;
			}
			if (isPermitted((short) nextx, (short) nexty)) {
				return new short[] { (short) nextx, (short) nexty };
			}

		}
		return null;

	}

	public short[] getRandomPath(VisibleObject vo, int radius) {
		Random random = new Random();
		for (int i = 0; i < 8; i++) {
			int direction = random.nextInt(8);
			int nextx = vo.getX();
			int nexty = vo.getY();
			switch (direction) {
			case 0:
				nextx = nextx + radius;
				break;
			case 1:
				nexty = nexty + radius;
				break;
			case 2:
				nextx = nextx - radius;
				break;
			case 3:
				nexty = nexty - radius;
				break;
			case 4:
				nextx = nextx + radius;
				nexty = nexty + radius;
				break;
			case 5:
				nextx = nextx + radius;
				nexty = nexty - radius;
				break;
			case 6:
				nextx = nextx - radius;
				nexty = nexty + radius;
				break;
			case 7:
				nextx = nextx - radius;
				nexty = nexty - radius;
				break;
			default:
				break;
			}
			short[] path = findWay(vo.getX(), vo.getY(), (short) nextx, (short) nexty);
			if (path != null) {
				return path;
			}
		}
		return null;

	}

	public short[] getRandomPathII(VisibleObject vo, int radius) {
		Random random = new Random();
		for (int i = 0; i < 16; i++) {
			int direction = random.nextInt(16);
			int nextx = vo.getX();
			int nexty = vo.getY();
			switch (direction) {
			case 0:
				nextx = nextx + radius;
				break;
			case 1:
				nexty = nexty + radius;
				break;
			case 2:
				nextx = nextx - radius;
				break;
			case 3:
				nexty = nexty - radius;
				break;
			case 4:
				nextx = nextx + radius;
				nexty = nexty + radius;
				break;
			case 5:
				nextx = nextx + radius;
				nexty = nexty - radius;
				break;
			case 6:
				nextx = nextx - radius;
				nexty = nexty + radius;
				break;
			case 7:
				nextx = nextx - radius;
				nexty = nexty - radius;
				break;
			case 8:
				nextx = nextx + radius;
				nexty = nexty + radius/2;
				break;
			case 9:
				nextx = nextx + radius/2;
				nexty = nexty + radius;
				break;
			case 10:
				nextx = nextx - radius/2;
				nexty = nexty + radius;
				break;
			case 11:
				nextx = nextx - radius;
				nexty = nexty + radius/2;
				break;
			case 12:
				nextx = nextx - radius;
				nexty = nexty - radius/2;
				break;
			case 13:
				nextx = nextx - radius/2;
				nexty = nexty - radius;
				break;
			case 14:
				nextx = nextx + radius/2;
				nexty = nexty - radius;
				break;
			case 15:
				nextx = nextx + radius;
				nexty = nexty - radius/2;
				break;
			default:
				break;
			}
			short[] path = findWay(vo.getX(), vo.getY(), (short) nextx, (short) nexty);
			if (path != null) {
				return path;
			}
		}
		return null;

	}

	public Integer getEnterLevelLimit() {
		return enterLevelLimit;
	}

	public void setEnterLevelLimit(Integer enterLevelLimit) {
		this.enterLevelLimit = enterLevelLimit;
	}

	public String getMonsterDesc() {
		return monsterDesc;
	}

	public void setMonsterDesc(String monsterDesc) {
		this.monsterDesc = monsterDesc;
	}

	public String getExerciseDesc() {
		return exerciseDesc;
	}

	public void setExerciseDesc(String exerciseDesc) {
		this.exerciseDesc = exerciseDesc;
	}

	public String getBossDesc() {
		return bossDesc;
	}

	public void setBossDesc(String bossDesc) {
		this.bossDesc = bossDesc;
	}

	public String getBossTimeDesc() {
		return bossTimeDesc;
	}

	public void setBossTimeDesc(String bossTimeDesc) {
		this.bossTimeDesc = bossTimeDesc;
	}

	public String getBossDropGoods() {
		return bossDropGoods;
	}

	public void setBossDropGoods(String bossDropGoods) {
		this.bossDropGoods = bossDropGoods;
	}

	/**
	 * 获取N个随机分布不重复的的落点,并且排除l中的点 用于全地图同时生成怪物
	 * 
	 * @param l
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<Point> getRandomPoint(List<Point> l, int count) throws Exception {
		if (getWidth() * getHeight() < count) {
			throw new Exception("要求的落点数量超过了地图的范围");
		}
		long currentTimeMillis = System.currentTimeMillis();
		List<Point> points = new ArrayList<Point>();
		int querycount = 0;
		while (points.size() <= count && querycount < 5000) {
			querycount++;
			int x = GenerateProbability.randomIntValue(0, 160);
			int y = GenerateProbability.randomIntValue(0, 160);
			Point p = new Point((short) x, (short) y);
			if (!checkPoint(l, p, points)) {
				continue;
			}
			points.add(p);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("获取随机分布落点耗时" + (System.currentTimeMillis() - currentTimeMillis) + ",互斥量" + "落点数量" + count);
		}
		return points;
	}

	public Point getRandomPoint() {
		List<Point> randomPoint;
		try {
			randomPoint = getRandomPoint(null, 1);
			if (randomPoint != null && randomPoint.size() >= 1) {
				return randomPoint.get(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	private boolean checkPoint(List<Point> l, Point point, List<Point> l2) {
		if (point == null) {
			return false;
		}
		if (!isPermitted((short) point.getX(), (short) point.getY())) {
			// 是否合法落点
			return false;
		}
		if (l != null)
			for (Point point1 : l) {
				if (DistanceFormula.getDistanceRound(point1.getX(), point1.getY(), point.getX(), point.getY()) < ClientConfig.FEAST_MIN_LANG) {
					// 附近没有其它宴席
					return false;
				}
			}
		if (l2 != null)
			for (Point point1 : l2) {
				if (DistanceFormula.getDistanceRound(point1.getX(), point1.getY(), point.getX(), point.getY()) < ClientConfig.FEAST_MIN_LANG) {
					// 附近没有其它宴席
					return false;
				}
			}
		return true;
	}

	public short[] getRandomPoint(short x, short y, int radius) {
		short[] point = new short[2];
		point[0] = (short) x;
		point[1] = (short) y;
		for (int i = 0; i < 40; i++) {
			int count = (int) GenerateProbability.randomIntValue(1, radius);
			int nx = x;
			int ny = y;
			for (int j = 0; j < count; j++) {
				int temp = GenerateProbability.randomIntValue(-1000, 1000);
				int rand;
				if (temp > 0) {
					rand = 1;
				} else {
					rand = -1;
				}
				if (j % 2 == 0) {
					nx = nx + rand;
				} else {
					ny = ny + rand;
				}
				if (!isPermitted((short) nx, (short) ny)) {
					break;
				}
			}
			if (isPermitted((short) nx, (short) ny) && getTransprot((short) nx, (short) ny) == null) {
				point[0] = (short) nx;
				point[1] = (short) ny;
				return point;
			}
		}
		return point;
	}

	@Override
	public Point getRandomPoint2(short x, short y, int radius) {
		for (int i = 1; i < radius; i++) {
			Point p = BrokenLine.getAroundPoint(x, y, i, getBlockTiles(), null);
			if (p != null) {
				return p;
			}
		}
		return null;
	}

	public short[] getRandomPoint(int x, int y, int radius) {
		short[] point = new short[2];
		point[0] = (short) x;
		point[1] = (short) y;
		for (int i = 0; i < 40; i++) {
			int xdirection = GenerateProbability.randomIntValue(1, 2);
			int ydirection = GenerateProbability.randomIntValue(1, 2);
			int xpoint;
			int ypoint;
			if (xdirection == 1) {
				xpoint = (int) GenerateProbability.randomIntValue(x - radius, x);
			} else {
				xpoint = (int) GenerateProbability.randomIntValue(x, x + radius);
			}
			if (ydirection == 1) {
				ypoint = (int) GenerateProbability.randomIntValue(y - radius, y);
			} else {
				ypoint = (int) GenerateProbability.randomIntValue(y, y + radius);
			}
			if (isPermitted((short) xpoint, (short) ypoint) && getTransprot((short) xpoint, (short) ypoint) == null) {
				point[0] = (short) xpoint;
				point[1] = (short) ypoint;
				return point;
			}
		}
		boolean ispass = isPermitted((short) x, (short) y);
		if (!ispass) {
			for (int i = 0; i < 10; i++) {
				if (isPermitted((short) (x - 1), (short) (y - 1))) {
					point[0] = (short) (x - 1);
					point[1] = (short) (y - 1);
					break;
				}
				if (isPermitted((short) (x + 1), (short) (y - 1))) {
					point[0] = (short) (x + 1);
					point[1] = (short) (y - 1);
					break;
				}
				if (isPermitted((short) (x + 1), (short) (y + 1))) {
					point[0] = (short) (x + 1);
					point[1] = (short) (y + 1);
					break;
				}
				if (isPermitted((short) (x - 1), (short) (y + 1))) {
					point[0] = (short) (x - 1);
					point[1] = (short) (y + 1);
					break;
				}
			}
		}
		return point;
	}

	public Teleport getTransprot(short x, short y) {
		for (MapTeleport teleport : teleports4set) {
			if (teleport.isTeleport(x, y)) {
				return teleport;
			}
		}
		return null;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * 将地图的中央部分切割出来，放进15个大小的数组里。 以方便取得随机地图点
	 * 
	 * @return 15个大小的数组
	 */
	private void centerRandomPoints() {
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		int _x1 = x / 2;
		int _x2 = x / 4;

		int _y1 = 0;
		int _y2 = y / 4;
		int[][] arr = new int[15][2];
		// arr[0][0] = 1;
		for (int i = 0; i < 3; i++) {
			int tmpX1 = _x1;
			_y1 = _y1 + _y2;
			for (int j = 0; j < 5; j++) {
				if (!(getBlockTiles()[tmpX1][_y1] == 1)) {
					arr[i * 5 + j][0] = tmpX1;
					arr[i * 5 + j][1] = _y1;
				}
				tmpX1 = tmpX1 + _x2;
			}
		}
		centerRandomPoints = arr;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Collection getSMonsterCollection() {
		return allMonsterWillAddToScene;
	}

	public int getInstanceCount() {
		return instanceCount;
	}

	public void setInstanceCount(int instanceCount) {
		this.instanceCount = instanceCount;
	}
}
