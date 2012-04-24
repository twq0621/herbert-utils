package net.snake.gamemodel.instance.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.instance.bean.InstanceDataRef;
import net.snake.gamemodel.instance.bean.InstanceDayStat;
import net.snake.ibatis.SystemFactory;
import org.apache.log4j.Logger;


public class InstanceDataProvider implements  CacheUpdateListener {
	private static Logger logger = Logger.getLogger(InstanceDataProvider.class);
	// DAO
	private InstanceDataRefDAO instanceDataRefDAO = new InstanceDataRefDAO(SystemFactory.getGamedataSqlMapClient());

	private InstanceDayStatDAO instanceDayStatDAO = new InstanceDayStatDAO(SystemFactory.getCharacterSqlMapClient());
	// 缓存数据
	private Map<Integer, InstanceDataRef> instanceDataRefMap = new HashMap<Integer, InstanceDataRef>();// instance数据原型映射
	// 排行榜使用
	private int ranKingId[];

	private List<InstanceDataRef> instanceDataRefList;
	// 单例实现=====================================
	private static InstanceDataProvider instance;

	private InstanceDataProvider() {
	}

	public static InstanceDataProvider getInstance() {
		if (instance == null) {
			instance = new InstanceDataProvider();
		}
		return instance;
	}

	// 单例实现========================================

	public int[] getInstanCeId() {
		return ranKingId;
	}
	@SuppressWarnings("unchecked")
	public void initInstanceDataRef() throws Exception {
		instanceDataRefList = instanceDataRefDAO.select();

		BeanTool.addOrUpdate(instanceDataRefMap, instanceDataRefList, "instanceModelId");
		for (InstanceDataRef instance : instanceDataRefMap.values()) {
			instance.init();
		}
		// 排行榜使用
		List<InstanceDataRef> rankingListID = instanceDataRefDAO.selectByEnable(1);
		ranKingId = new int[rankingListID.size()];
		int a = 0;
		for (InstanceDataRef instanceDataRef : rankingListID) {
			ranKingId[a] = instanceDataRef.getInstanceModelId();
			a = a + 1;
		}
	}

	public void deletePriCount(int characterId, Date date) {
		InstanceDayStat instanceDayStat = new InstanceDayStat();
		instanceDayStat.setCharacterid(characterId);
		instanceDayStat.setStatdate(date);
		try {
			instanceDayStatDAO.deleteByStatdate(instanceDayStat);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public InstanceDataRef getInstanceDataRef(int id) {
		return instanceDataRefMap.get(id);
	}

	public List<InstanceDataRef> getInstanceDataRefList() {
		return instanceDataRefList;
	}

	public void setInstanceDataRefList(List<InstanceDataRef> instanceDataRefList) {
		this.instanceDataRefList = instanceDataRefList;
	}

	// 获得玩家某日的副本次数
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Integer, InstanceDayStat> getCharacterInstanceDayStat(int characterid, Date onlyymd) throws SQLException {
		InstanceDayStat instanceDayStat = new InstanceDayStat();
		instanceDayStat.setCharacterid(characterid);
		instanceDayStat.setStatdate(onlyymd);
		List<InstanceDayStat> characterInstanceMap = instanceDayStatDAO.select(instanceDayStat);
		Map t = BeanTool.listToMap(characterInstanceMap, "instancemodelid");
		return t;
	}

	public void updateInstanceDayStat(InstanceDayStat currentCount) throws SQLException {
		int updaterow = instanceDayStatDAO.updateByPrimaryKey(currentCount);
		if (updaterow == 0) {
			instanceDayStatDAO.insert(currentCount);
		}
	}

	@Override
	public void reload() {
		try {
			logger.info("start reload instance config");
			initInstanceDataRef();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "instanceconfig";
	}

}
