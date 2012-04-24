package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.commons.BeanUtils;
import net.snake.commons.TimeExpression;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.config.bean.ConfigParam;
import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.guide.bean.CharacterMsg;
import net.snake.gamemodel.guide.persistence.CharacterMsgManager;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.wedding.bean.Feast;
import net.snake.gamemodel.wedding.bean.FeastPlayConfig;
import net.snake.gamemodel.wedding.bean.WedFeastJoin;
import net.snake.gamemodel.wedding.logic.FeastMonsterPoint;
import net.snake.gamemodel.wedding.response.wedfeast.WedFeastMessageResponse52248;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.serverenv.vline.VLineServer;
import org.apache.log4j.Logger;
/**
 * 婚宴管理器 全服单例
 * 
 * @author serv_dev
 */
public class WedFeastManager {
	private static Logger log = Logger.getLogger(WedFeastManager.class);
	private final static int FEAST_APPLY_UPDATE = 0;
	private final static int FEAST_APPLY_NEW = 1;

	private static WedFeastManager instance;
	private static FeastPlayConfigManager configManager = FeastPlayConfigManager.getInstance();
	private ConfigParam configParam = GameServer.configParamManger.getConfigParam();
	// <角色ID,婚宴>
	private ConcurrentHashMap<Integer, WedFeast> roleFeastMap = new ConcurrentHashMap<Integer, WedFeast>();
	// <婚宴ID,婚宴>
	private ConcurrentHashMap<Integer, WedFeast> feastMap = new ConcurrentHashMap<Integer, WedFeast>();
	// <角色ID,参与婚宴列表>
	private ConcurrentHashMap<Integer, List<WedFeast>> roleJoin = new ConcurrentHashMap<Integer, List<WedFeast>>();
	// 正在进行的婚宴
	private ConcurrentHashMap<Integer, List<WedFeast>> beingFeasts = new ConcurrentHashMap<Integer, List<WedFeast>>();
	// 婚宴状态
	private volatile boolean isstart = false;

	private FeastDAO dao = new FeastDAO(SystemFactory.getCharacterSqlMapClient());

	public static WedFeastManager getInstance() {
		if (instance == null) {
			instance = new WedFeastManager();
		}
		return instance;
	}
	private WedFeastManager() {
	}
	@SuppressWarnings("unchecked")
	public void init(){
		List<Feast> feasts;
		try {
			feasts = dao.select();
			if (feasts != null && feasts.size() > 0) {
				for (Feast feast : feasts) {
					WedFeast fe = new WedFeast();
					BeanUtils.copyProperties(feast, fe);
					feastMap.put(fe.getTempID(), fe);
					roleFeastMap.put(fe.getApplyerId(), fe);
					roleFeastMap.put(fe.getMateId(), fe);
					if (fe.isStart()) {
						fe.changeToEnd();
						updateFeast(fe);
					}
					// 婚宴参与人加载
					fe.loadJoiner();
					Set<Entry<Integer, WedFeastJoin>> entrySet = fe.getJoiner().entrySet();
					for (Entry<Integer, WedFeastJoin> entry : entrySet) {
						WedFeastJoin join = entry.getValue();
						roleJoin.putIfAbsent(join.getCharacterid(), new ArrayList<WedFeast>());
						List<WedFeast> list = roleJoin.get(join.getCharacterid());
						list.add(fe);

					}

				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	public boolean isStart() {
		return isstart;
	}

	/**
	 * 婚礼类型举行时间判断
	 * 
	 * @param now
	 * @return
	 */
	public void tick(long now) {
		synchronized (this) {
			if (istime(now)) {
				if (!isstart) {
					wedFeastStart();
				}
			} else {
				if (isstart) {
					wedFeastEnd();
				}
			}
		}
	}

	/**
	 * 婚宴开始前的一些处理
	 */
	private void wedFeastStart() {
		if (log.isDebugEnabled()) {
			log.debug("婚宴时间到开启");
		}
		Set<Entry<Integer, WedFeast>> entrySet = feastMap.entrySet();
		for (Entry<Integer, WedFeast> entry : entrySet) {
			WedFeast value = entry.getValue();
			// 未开始的和己结束的重新开始
			if (value.isUNStart()) {
				value.changeToStart();// 婚宴状态置为己开始
				beingFeasts.putIfAbsent(value.getLine(), new ArrayList<WedFeast>());
				List<WedFeast> list = beingFeasts.get(value.getLine());
				list.add(value);
				updateFeast(value);
				addSystemJoin(value);// 发放系统赠送的红包
			}
		}
		if (beingFeasts != null && beingFeasts.size() > 0) {
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 50023));
		}
		isstart = true;
	}

	/**
	 * 婚宴结束时的处理
	 */
	private void wedFeastEnd() {
		if (log.isDebugEnabled()) {
			log.debug("婚宴结束");
		}

		isstart = false;
		// 婚宴结束通知
		if (beingFeasts != null && beingFeasts.size() > 0) {
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 50024));
			Set<Entry<Integer, List<WedFeast>>> entrySet = beingFeasts.entrySet();
			for (Entry<Integer, List<WedFeast>> entry : entrySet) {
				// int line = entry.getKey();
				List<WedFeast> value = entry.getValue();
				for (WedFeast wedFeast : value) {
					Hero applyer = GameServer.vlineServerManager.getCharacterById(wedFeast.getApplyerId());
					Hero mate = GameServer.vlineServerManager.getCharacterById(wedFeast.getMateId());
					if (applyer != null) {
						applyer.sendMsg(new WedFeastMessageResponse52248(1, getNextDay(wedFeast), wedFeast.getFasttype(), wedFeast.getLine()));
					}
					if (mate != null) {
						mate.sendMsg(new WedFeastMessageResponse52248(1, getNextDay(wedFeast), wedFeast.getFasttype(), wedFeast.getLine()));
					}
					wedFeast.changeToEnd();
					updateFeast(wedFeast);
				}
				value.clear();
			}
			beingFeasts.clear();
		}

	}

	private VLineServer getRandomLineServer(FeastPlayConfig config) {
		Object[] lineServersList = GameServer.vlineServerManager.getLineServersList().toArray();
		ArrayList<VLineServer> l = new ArrayList<VLineServer>();
		for (Object object : lineServersList) {
			VLineServer line = (VLineServer) object;
			int capacity = getCapacity(line.getLineid());
			if (config.getFeastAmount() + capacity <= FeastMonsterPoint.points.size()) {
				l.add(line);
			}
		}
		if (l.size() <= 0) {
			return null;
		}
		// int randomIntValue =
		// GenerateProbability.randomIntValue(0,l.size()-1);
		return l.get(l.size() - 1);
	}

	/**
	 * 线ID
	 * 
	 * @param lineid
	 * @param num
	 *            　加入的桌数
	 */
	private int getCapacity(int lineid) {
		Set<Entry<Integer, WedFeast>> entrySet = feastMap.entrySet();
		int count = 0;
		for (Entry<Integer, WedFeast> entry : entrySet) {
			WedFeast value = entry.getValue();
			if (value.isUNStart() && value.getLine() == lineid) {
				count += value.getConfig().getFeastAmount();
			}
		}
		return count;
	}

	private boolean istime(long now) {
		String feasttimeExp = configParam.getFeasttimeExp();
		if (feasttimeExp != null && !feasttimeExp.equals("")) {
			TimeExpression exp = new TimeExpression(feasttimeExp);
			return exp.isExpressionTime(now);
		}
		return false;
	}

	private void dealApplySuccess(int type, WedFeast feast, Hero applyer, int feasttype, FeastPlayConfig config, VLineServer vline) {
		int wedderId = applyer.getMyFriendManager().getRoleWedingManager().getWedderId();
		feast.setLine(vline.getLineid());// 随机分线ID
		switch (type) {
		case FEAST_APPLY_UPDATE:
			feast.setApplyerId(applyer.getId());
			feast.setMateId(wedderId);
			feast.setApplytime(System.currentTimeMillis());
			feast.setFasttype(feasttype);
			if (feast.getIsreceive1() && feast.getIsreceive2()) {
				// 都领了
				feast.setGiftAmount(0);
			} else {
				if (!feast.getIsreceive1() && !feast.getIsreceive2()) {
					// 都没领 不变
				} else {
					// 不是都领了。也不是都没领。那就是有一个令了留一半
					feast.setGiftAmount(feast.getGiftAmount() / 2);
				}
			}
			feast.setIsreceive1(false);
			feast.setIsreceive2(false);
			feast.setJoinCount(0);
			feast.changeToUNStart();
			updateFeast(feast);
			clearJoinFeast(feast);
			break;
		case FEAST_APPLY_NEW:
			addFeast(feast);
			break;
		}
		// 发送系统公告
		Hero mate = GameServer.vlineServerManager.getCharacterById(applyer.getMyFriendManager().getRoleWedingManager().getWedderId());
		CharacterCacheEntry matecache = CharacterCacheManager.getInstance().getCharacterCacheEntryById(applyer.getMyFriendManager().getRoleWedingManager().getWedderId());
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 50017, applyer.getViewName(), matecache.getViewName(), config
				.getNameI18n(), getNextDay(feast) + "", feast.getLine() + ""));
		// 申请成功
		applyer.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50018));
		setState(applyer, true, true);
		if (mate != null) {
			mate.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50019));
			// 举办日
			mate.sendMsg(new WedFeastMessageResponse52248(0, getNextDay(feast), feast.getFasttype(), feast.getLine()));
			mate.setWedfeast(true);
			setState(mate, true, true);
		} else {
			// msg时间
			CharacterMsgManager.getInstance().insertCharacterMsg(
					new CharacterMsg(applyer.getMyFriendManager().getRoleWedingManager().getWedderId(), 50020, getNextDay(feast) + "", "20:00-21:00", feast.getLine() + ""));
		}

	}

	private void setState(Hero c, boolean isfeast, boolean ismsg) {
		c.setWedfeast(isfeast);
		if (ismsg) {
			c.getMyFriendManager().updateCharacterState();
		}
	}

	/**
	 * 发放系统红包
	 * 
	 * @param join
	 */
	private void addSystemJoin(final WedFeast feast) {
		FeastPlayConfig config = configManager.getConfig(feast.getFasttype());
		// FeastPlayConfig config =
		// feastPlayConfigDataSet.getConfig(feast.getFasttype());
		Integer applycost = config.getApplycost();
		int gift = applycost / 100;
		final WedFeastJoin join = new WedFeastJoin();
		join.setCharacterid(-1);
		join.setApplyer(feast.getApplyerId());
		join.setMateid(feast.getMateId());
		join.setGift(gift);
		feast.addJoiner(join);
		updateFeast(feast);

	}

	private void clearJoinFeast(final WedFeast feast) {
		// 清除婚宴的红包记录
		ConcurrentHashMap<Integer, WedFeastJoin> joiner = feast.getJoiner();
		for (Entry<Integer, WedFeastJoin> entry : joiner.entrySet()) {
			WedFeastJoin value = entry.getValue();
			List<WedFeast> list = roleJoin.get(value.getCharacterid());
			if (list != null)
				list.remove(feast);
		}
		feast.clearJoiner();
	}

	public void addFeast(final WedFeast feast) {
		feastMap.put(feast.getTempID(), feast);
		roleFeastMap.put(feast.getApplyerId(), feast);
		roleFeastMap.put(feast.getMateId(), feast);
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insert(feast);
				} catch (SQLException e) {
					log.info("更新婚宴数据时的异常", e);
				}
			}
		});

	}

	public void removeFeast(final WedFeast feast) {
		feastMap.remove(feast.getTempID());
		roleFeastMap.remove(feast.getApplyerId());
		roleFeastMap.remove(feast.getMateId());
		clearJoinFeast(feast);
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.deleteById(feast.getId());
				} catch (SQLException e) {
					log.info("删除婚宴数据时的异常", e);
				}
			}
		});

	}

	public void updateFeast(final Feast feast) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updateByIdSelective(feast);
				} catch (SQLException e) {
					log.info("更新婚宴数据时的异常", e);
				}
			}
		});
	}

	private WedFeast createFeast(int feasttype, Hero applyer) {
		WedFeast feast = new WedFeast();
		feast.setApplyerId(applyer.getId());
		feast.setMateId(applyer.getMyFriendManager().getRoleWedingManager().getWedderId());
		feast.setApplytime(System.currentTimeMillis());
		feast.setFasttype(feasttype);
		feast.setIsreceive1(false);
		feast.setIsreceive2(false);
		feast.setJoinCount(0);
		feast.setGiftAmount(0);
		feast.changeToUNStart();
		return feast;
	}

	private WedFeastJoin createFeastJoin(WedFeast feast, Hero joiner, int gift) {
		WedFeastJoin join = new WedFeastJoin();
		join.setCharacterid(joiner.getId());
		join.setApplyer(feast.getApplyerId());
		join.setMateid(feast.getMateId());
		join.setGift(gift);
		return join;
	}

	public WedFeast getFeastByID(int feastid) {
		return feastMap.get(feastid);
	}

	public ConcurrentHashMap<Integer, WedFeastJoin> getJoinerByFeast(int feastid) {
		WedFeast feast = getFeastByID(feastid);
		return feast.getJoiner();
	}

	public List<WedFeast> getFeastList() {
		Set<Integer> keySet = feastMap.keySet();
		List<WedFeast> list = new ArrayList<WedFeast>();
		for (Integer integer : keySet) {
			list.add(feastMap.get(integer));
		}
		return list;
	}

	/**
	 * 清除婚宴 只要不是开始的就清除
	 * 
	 * @param characterid
	 * @param characterid
	 */
	public void clearFeast(int characterid, int character2id) {
		WedFeast feast = roleFeastMap.get(characterid);
		WedFeast feast2 = roleFeastMap.get(character2id);
		if (feast != null && feast != null) {
			if (feast.equals(feast2)) {
				removeFeast(feast2);
			}
		}
	}

	/**
	 * 是否有婚宴且无法删除（己开始）开始前一分钟无法清除
	 * 
	 * @param characterid
	 * @return
	 */
	public boolean isFeast(int characterid) {
		WedFeast feast = getFeastByRoleid(characterid);
		if (feast != null && (feast.isStart() || istime(System.currentTimeMillis() + 60 * 1000))) {
			return true;
		}
		return false;
	}

	/**
	 * 取得正在举行的婚宴
	 * 
	 * @return
	 * @return
	 */
	public synchronized List<WedFeast> getBeingFeast(int lineid) {
		return beingFeasts.get(lineid);
	}

	public WedFeast getFeastByRoleid(int characterid) {
		return roleFeastMap.get(characterid);
	}

	/**
	 * 有未完成的婚宴
	 * 
	 * @param characterid
	 * @return
	 */
	public boolean isFeastNotEnd(int characterid) {
		WedFeast feast = getFeastByRoleid(characterid);
		if (feast != null && !feast.isEnd()) {
			return true;
		}
		return false;
	}

	/**
	 * 申请婚宴
	 * 
	 * @param feasttype
	 *            婚宴类型
	 * @param applyer
	 *            申请人
	 * @return
	 */
	public boolean applyFeast(int feasttype, Hero applyer) {
		RoleWedingManager roleWedingManager = applyer.getMyFriendManager().getRoleWedingManager();
		if (!roleWedingManager.isWedding()) {
			// 很抱歉，您还没有结婚，无法举办婚宴
			applyer.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50021));
			return false;
		}
		FeastPlayConfig config = FeastPlayConfigManager.getInstance().getConfig(feasttype);
		if (applyer.getCopper() < config.getApplycost()) {
			// 很抱歉，您的铜币不足，无法举办该级别婚宴
			applyer.sendMsg(new PrompMsg(50016));// @lanresource 铜币不够
			return false;
		}
		Integer favor = roleWedingManager.getFuqi().getCouples().getMaleFavor();
		if (favor < config.getApplyfavor()) {
			applyer.sendMsg(new PrompMsg(50015));// @lanresource 友好度不够
			return false;
		}

		WedFeast feast = getFeastByRoleid(applyer.getId());
		if (feast != null && !feast.isEnd()) {
			// 未开始的婚宴或己开始的婚宴中有该角色
			applyer.sendMsg(new PrompMsg(50014));// @lanresource
													// 您的婚宴正在筹办中，请在婚宴结束后，再申请下一轮
			return false;
		}

		VLineServer randomLineServer = getRandomLineServer(config);

		if (randomLineServer == null) {
			applyer.sendMsg(new PrompMsg(50045));// 很抱歉，今日襄阳城各线路已经摆满了婚宴餐桌，请缩小您的婚宴规模或者明日再申请婚宴。
			return false;
		}

		// 扣除应该扣的东西
		int changeCopper = CharacterPropertyManager.changeCopper(applyer, -config.getApplycost(), CopperAction.CONSUME);
		if (changeCopper == 0) {
			applyer.sendMsg(new PrompMsg(50022));// @lanresource 扣除铜币失败
			return false;
		}
		if (feast != null && feast.isEnd()) {
			// 包含己结束的婚宴但未回收的重新置状态
			dealApplySuccess(FEAST_APPLY_UPDATE, feast, applyer, feasttype, config, randomLineServer);
			return true;
		}
		if (feast == null) {
			feast = createFeast(feasttype, applyer);
			dealApplySuccess(FEAST_APPLY_NEW, feast, applyer, feasttype, config, randomLineServer);
			return true;
		}
		return false;
	}

	/**
	 * 参与婚礼 给红包
	 * 
	 * @param joiner
	 * @param feast
	 *            参与婚宴
	 * @param copper
	 *            红包
	 * @return
	 */
	public boolean joinFeast(Hero joiner, WedFeast feast, int gift) {
		if (joiner.getId().equals(feast.getApplyerId()) || joiner.getId().equals(feast.getMateId())) {
			joiner.sendMsg(new PrompMsg(50027));// 请把宴席留给客人吧
			return false;
		}
		if (feast.isEnd()) {
			joiner.sendMsg(new PrompMsg(50040));// 婚宴己经结束
			return false;
		}
		if (gift < 0) {
			gift = 0;
		}
		if (gift > MaxLimit.BAG_COPPER_MAX) {
			gift = MaxLimit.BAG_COPPER_MAX;
		}
		List<WedFeast> list = roleJoin.get(joiner.getId());
		final WedFeastJoin join = createFeastJoin(feast, joiner, gift);
		if (list == null) {
			list = new ArrayList<WedFeast>();
			roleJoin.put(join.getCharacterid(), list);
		}
		if (list.contains(feast)) {
			joiner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50031));
			// 已经给过红包
			return false;
		}
		FeastPlayConfig config = FeastPlayConfigManager.getInstance().getConfig(feast.getFasttype());
		if (join.getGift() < config.getGiftcost()) {
			joiner.sendMsg(new PrompMsg(50043));// 铜币不够参加该级别的婚宴
			return false;
		}
		if (joiner.getCopper() < join.getGift()) {
			// 很抱歉，您的铜币不足，
			joiner.sendMsg(new PrompMsg(17504));// @lanresource 铜币不够
			return false;
		}

		if (CharacterPropertyManager.changeCopper(joiner, -join.getGift(), CopperAction.CONSUME) == 0) {
			joiner.sendMsg(new PrompMsg(50022));// 扣除铜币失败
			return false;
		}

		feast.addJoiner(join);
		list.add(feast);
		joiner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50042));
		joiner.getDayInCome().dealFeastGift(1);
		updateFeast(feast);
		// 您己成功支付红包
		return true;
	}

	public static int getNextDay(WedFeast feast) {
		long nextTime = getNextTime(feast);
		Calendar instance = Calendar.getInstance();
		instance.setTimeInMillis(nextTime);
		return instance.get(Calendar.DATE);
	}

	public static long getNextTime(WedFeast feast) {

		Calendar instance = Calendar.getInstance();
		long now = System.currentTimeMillis();
		instance.setTimeInMillis(now);
		instance.set(Calendar.HOUR_OF_DAY, 20);
		instance.set(Calendar.MINUTE, 0);
		if (now >= instance.getTimeInMillis() && feast.isUNStart()) {
			instance.add(Calendar.DATE, 1);
		}
		return instance.getTimeInMillis();

	}

}
