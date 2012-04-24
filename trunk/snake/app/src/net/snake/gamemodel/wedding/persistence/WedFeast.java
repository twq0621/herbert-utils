package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.commons.BeanUtils;
import net.snake.commons.Language;
import net.snake.commons.program.IntId;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.wedding.bean.Feast;
import net.snake.gamemodel.wedding.bean.FeastJoin;
import net.snake.gamemodel.wedding.bean.FeastPlayConfig;
import net.snake.gamemodel.wedding.bean.WedFeastJoin;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.cache.CharacterCacheManager;

import org.apache.log4j.Logger;
/**
 * 继承婚宴类。加入了自定义序号用于解决数据库存储的并发问题
 * 
 * @author serv_dev
 */
public class WedFeast extends Feast {
	// 婚宴状态 未开始
	private final static int FEAST_STATE_UNSTART = 0;
	// 婚宴状态 进行中
	private final static int FEAST_STATE_START = 1;
	// 婚宴状态 己结束
	private final static int FEAST_STATE_END = 2;

	private Logger log = Logger.getLogger(getClass());
	private FeastJoinDAO joindao = new FeastJoinDAO(SystemFactory.getCharacterSqlMapClient());
	private ConcurrentHashMap<Integer, WedFeastJoin> joiner = new ConcurrentHashMap<Integer, WedFeastJoin>();
	private static final IntId intID = new IntId(1);
	private int tempId = intID.getNextId();

	public int getTempID() {
		return tempId;
	}

	public void addJoiner(final WedFeastJoin join) {
		joiner.put(join.getCharacterid(), join);
		long amount = getGiftAmount() + join.getGift();
		if (amount >= MaxLimit.BAG_COPPER_MAX * 2) {
			setGiftAmount(MaxLimit.BAG_COPPER_MAX * 2);
		} else {
			setGiftAmount(getGiftAmount() + join.getGift());
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					joindao.insertSelective(join);
				} catch (SQLException e) {
					log.info("添加红包出错", e);
				}
			}
		});
	}

	public void clearJoiner() {
		getJoiner().clear();
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					FeastJoin feastJoin = new FeastJoin();
					feastJoin.setApplyer(getGiftAmount());
					feastJoin.setMateid(getMateId());
					joindao.deleteByApplyerAndMateid(feastJoin);
				} catch (SQLException e) {
					log.info("清除红包出错", e);
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public void loadJoiner() {
		try {
			FeastJoin feastJoin = new FeastJoin();
			feastJoin.setApplyer(getGiftAmount());
			feastJoin.setMateid(getMateId());
			List<FeastJoin> joins = joindao.select(feastJoin);
			for (FeastJoin join : joins) {
				WedFeastJoin jo = new WedFeastJoin();
				BeanUtils.copyProperties(join, jo);
				joiner.put(jo.getCharacterid(), jo);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}

	public ConcurrentHashMap<Integer, WedFeastJoin> getJoiner() {
		return joiner;
	}

	/**
	 * 是否己缴红包
	 * 
	 * @return
	 */
	public boolean isGift(Hero role) {
		return joiner.containsKey(role.getId());
	}

	/**
	 * 红包领取
	 * 
	 * @param feast
	 * @param receiver
	 * @return
	 */
	public boolean receive(Hero receiver) {
		if (!receiver.getMyFriendManager().getRoleWedingManager().isWedding()) {
			// 离婚后不能取。。婚宴要消除
			return false;
		}
		int wedderId = receiver.getMyFriendManager().getRoleWedingManager().getWedderId();
		if (!(getApplyerId() == wedderId || getMateId() == wedderId)) {
			return false;
		}
		if (!(getApplyerId().equals(receiver.getId()) || getMateId().equals(receiver.getId()))) {
			return false;
		}
		if (isStart()) {
			receiver.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50032));
			return false;
		}
		if (isUNStart()) {
			receiver.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50033));
			return false;
		}

		int amount = getGiftAmount() / 2;
		if (receiver.getId().equals(getApplyerId())) {
			if (getIsreceive1()) {
				receiver.sendMsg(new PrompMsg(50030));
				return false;
			}
			setIsreceive1(true);
			if (getIsreceive2()) {
				CharacterPropertyManager.changeCopper(receiver, amount, CopperAction.ADD_OTHER);
				receiver.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_LEFTCHAT, 50029, amount + ""));
				WedFeastManager.getInstance().removeFeast(this);
			} else {
				CharacterPropertyManager.changeCopper(receiver, amount, CopperAction.ADD_OTHER);
				WedFeastManager.getInstance().updateFeast(this);
				receiver.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_LEFTCHAT, 50029, amount + ""));
			}
			return true;
		}
		if (receiver.getId().equals(getMateId())) {
			if (getIsreceive2()) {
				receiver.sendMsg(new PrompMsg(50030));
				return false;
			}
			setIsreceive2(true);
			if (getIsreceive1()) {
				CharacterPropertyManager.changeCopper(receiver, amount, CopperAction.ADD_OTHER);
				receiver.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_LEFTCHAT, 50029, amount + ""));
				WedFeastManager.getInstance().removeFeast(this);
			} else {
				CharacterPropertyManager.changeCopper(receiver, amount, CopperAction.ADD_OTHER);
				receiver.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_LEFTCHAT, 50029, amount + ""));
				WedFeastManager.getInstance().updateFeast(this);
			}
			return true;
		}
		return false;
	}

	/**
	 * 婚宴设定
	 * 
	 * @return
	 */
	public FeastPlayConfig getConfig() {
		return FeastPlayConfigManager.getInstance().getConfig(getFasttype());
	}

	/**
	 * 获取婚宴外键
	 * 
	 * @return
	 */
	public String getFeastFk() {
		return getApplyerId() + "_" + getMateId();
	}

	/**
	 * 婚宴是否开始
	 * 
	 * @return
	 */
	public boolean isStart() {
		return getState().equals(FEAST_STATE_START);
	}

	/**
	 * 婚宴是否结束
	 * 
	 * @return
	 */
	public boolean isEnd() {
		return getState().equals(FEAST_STATE_END);
	}

	/**
	 * 婚宴是否未开始
	 * 
	 * @return
	 */
	public boolean isUNStart() {
		return getState().equals(FEAST_STATE_UNSTART);
	}

	/**
	 * 开始婚宴
	 */
	public void changeToStart() {
		setState(FEAST_STATE_START);
	}

	/**
	 * 设置为未开始
	 */
	public void changeToUNStart() {
		setState(FEAST_STATE_UNSTART);
	}

	/**
	 * 结束婚宴
	 */
	public void changeToEnd() {
		setState(FEAST_STATE_END);
	}

	public String getWedFeastName() {
		CharacterCacheEntry applyer = getApplyer();
		CharacterCacheEntry mateer = getMateer();
		String applyername = applyer != null ? applyer.getViewName() : "";
		String materName = mateer != null ? mateer.getViewName() : "";
		return applyername + Language.AND + materName + Language.OF + getMonsterModel().getNameI18n();
	}

	public CharacterCacheEntry getApplyer() {
		return CharacterCacheManager.getInstance().getCharacterCacheEntryById(getApplyerId());
	}

	public CharacterCacheEntry getMateer() {
		return CharacterCacheManager.getInstance().getCharacterCacheEntryById(getMateId());
	}

	public MonsterModel getMonsterModel() {
		return MonsterModelManager.getInstance().getFromCache(getConfig().getFeastModel());
	}

}
