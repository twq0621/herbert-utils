package net.snake.gamemodel.heroext.dantian.persistence;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.commons.BeanUtils;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.logic.RoleWedingManager;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.heroext.dantian.bean.CharacterDanTian;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.heroext.dantian.bean.DantianModel;
import net.snake.gamemodel.heroext.dantian.response.DanTianQiXuanMsg10508;
import net.snake.gamemodel.heroext.dantian.response.UpGradeResult53156;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.shell.Options;
import org.apache.log4j.Logger;


/**
 * 丹田控制器
 * 
 * @author serv_dev
 */
public class DanTianController extends CharacterController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DanTianController.class);

	DanTian dantian;
	private CharacterDanTianDAO dao = new CharacterDanTianDAO(SystemFactory.getCharacterSqlMapClient());

	public DanTianController(Hero character) {
		super(character);
	}

	public void destroy() {
		dantian = null;
	}

	/**
	 * 初始
	 * 
	 * @throws SQLException
	 */
	public void init() {
		CharacterDanTian entry = null;
		try {
			entry = dao.selectByPrimaryKey(character.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		if (entry != null) {
			dantian = new DanTian();
			BeanUtils.copyProperties(entry, dantian);
			character.setDantiangrade(entry.getDantianid());
			// 上次更新时间离登录时间超过24小时且幸运值不为零的清掉
			if (dantian.getLuckLastclear() != null && dantian.getLuck() > 0) {
				long lasttime = dantian.getLuckLastclear().getTime();
				long elapsetime = System.currentTimeMillis() - lasttime;
				long hour = elapsetime / (1000 * 60 * 60);
				if (hour >= 24) {
					dantian.setBeforeLuck(entry.getLuck());
					dantian.setLuck(0);
					dantian.setLuckLastclear(new Date(System.currentTimeMillis()));
					dao.syncUpdate(dantian);
				}
				if (logger.isDebugEnabled()) {
					logger.debug(character.getId() + "玩家的丹田祝福值异常 ,己修正"); //$NON-NLS-1$
				}
				// 为了防止在祝福值清零时玩家正在下线而不能清除的数据
			}
			character.getPropertyAdditionController().addChangeListener(dantian);
		}
	}

	/**
	 * 首次获取丹田
	 */
	public void acquisitionDanTian() {
		if (dantian != null) {
			return;
		}
		dantian = new DanTian();
		dantian.setCharacterid(character.getId());
		dantian.setDantianid(1);
		dantian.setFaildcount(0);
		dantian.setLuck(0);
		dao.syncInsert(dantian);
		character.getPropertyAdditionController().addChangeListener(dantian);
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50070, dantian.getModel().getNameI18n()));
		character.setDantiangrade(dantian.getDantianid());

		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					CharacterManager.getInstance().update(character);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
		CharacterCacheManager.getInstance().updateCharacterCacheEntry(character);
		updateFloatIco();
		character.getCharacterSkillManager().sendUpdateSkillShowMsg();
		return;
	}

	public void updateFloatIco() {
		RoleWedingManager roleWedingManager = character.getMyFriendManager().getRoleWedingManager();
		int wedderId = roleWedingManager.getWedderId();
		Hero cache = CharacterManager.getInstance().getCache(wedderId);
		if (cache != null) {
			cache.getEyeShotManager().sendMsg(
					new DanTianQiXuanMsg10508(cache.getId(), cache.getDanTianController().getQixuan1ResID(), cache.getDanTianController().getQixuan2ResID()));
		}
		character.getEyeShotManager().sendMsg(new DanTianQiXuanMsg10508(character.getId(), getQixuan1ResID(), getQixuan2ResID()));
	}

	/**
	 * 丹田升阶
	 * 
	 * @return
	 * @throws IOException
	 */
	public void upgrade() throws IOException {
		if (dantian == null) {
			// 没有丹田 正常情况下不会到这里.所以不作处理
			return;
		}
		if (dantian.getDantianid() >= 8) {
			// 正常情况下不会到这里.所以不作处理
			// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,50065));
			return;
		}
		final DantianModel model = dantian.getModel();
		Integer upNeedgoodid = model.getUpNeedgoodid();
		Integer upNeedgoodnum = model.getUpNeedgoodnum();
		Integer upNeedMinFaild = model.getUpNeedMinFaild();
		Integer upNeedMaxFaild = model.getUpNeedMaxFaild();
		if (upNeedgoodid != null && upNeedgoodid > 0 && upNeedgoodnum != null && upNeedgoodnum > 0) {
			int bagGoodsCountByModelID = character.getCharacterGoodController().getBagGoodsCountByModelID(upNeedgoodid);
			if (bagGoodsCountByModelID < upNeedgoodnum) {
				Goodmodel goodmodel = GoodmodelManager.getInstance().get(upNeedgoodid);
				// ”很抱歉，突破所需的碧灵丹数量不足“
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60017, goodmodel.getNameI18n()));
				processUpCheckFaild();
				return;
			} else {
				if (!character.getCharacterGoodController().deleteGoodsFromBag(upNeedgoodid, upNeedgoodnum)) {
					// 扣除物品失败
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50066));
					processUpCheckFaild();
					return;
				}
			}
		}

		if (dantian.getFaildcount() <= upNeedMinFaild) {
			// 小于最小次数必定失败
			processUpFaild();
			return;
		}

		if (dantian.getFaildcount() < upNeedMaxFaild && !GenerateProbability.defaultIsGenerate(model.getUpProbability())) {
			// 失败
			processUpFaild();
			return;
		}
		processUpSuccess();
	}

	public void processUpCheckFaild() throws IOException {
		// for deleting DantianModel model = dantian.getModel();
		character.sendMsg(new UpGradeResult53156(0));
	}

	public void processUpSuccess() throws IOException {
		character.getPropertyAdditionController().removeChangeListener(dantian);
		dantian.setDantianid(dantian.getDantianid() + 1);
		dantian.setLuck(0);
		dantian.setFaildcount(0);
		dao.syncUpdate(dantian);
		character.getPropertyAdditionController().addChangeListener(dantian);
		character.setDantiangrade(dantian.getDantianid());
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				try {
					CharacterManager.getInstance().update(character);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
		CharacterCacheManager.getInstance().updateCharacterCacheEntry(character);
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50067, dantian.getModel().getNameI18n()));
		updateFloatIco();
		character.sendMsg(new UpGradeResult53156(1));
		character.getCharacterSkillManager().sendUpdateSkillShowMsg();
	}

	public void processUpFaild() throws IOException {
		int randomIntValue = GenerateProbability.randomIntValue(dantian.getModel().getUpfaildZhufuMin(), dantian.getModel().getUpfaildZhufuMax());
		dantian.setLuck(dantian.getLuck() + randomIntValue);
		dantian.setFaildcount(dantian.getFaildcount() + 1);
		dao.syncUpdate(dantian);
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50068, randomIntValue));
		character.sendMsg(new UpGradeResult53156(0));
	}

	private void clearzhufu() {
		if (Options.IsCrossServ) {
			return;
		}
		if (dantian == null) {
			return;
		}
		// 只设内存..数据库另行统一UPDATE
		if (dantian != null) {
			dantian.setBeforeLuck(dantian.getLuck());
			dantian.setLuck(0);
			dantian.setLuckLastclear(new Date(System.currentTimeMillis()));
		}
		// dao.syncUpdate(dantian);
	}

	public static void clearZhuFu() {
		if (Options.IsCrossServ) {
			return;
		}
		CharacterRun characterRun = new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.getDanTianController().clearzhufu();
			}
		};
		try {
			// 清除在线玩家的祝福值
			GameServer.vlineServerManager.runToOnlineCharacter(characterRun);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			// 清除数据库中的祝福值
			CharacterManager.getInstance().clearLuckValueForDantian();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 上线时作最后清除时间的判断 大于24小时则清零
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}

	public DanTian getDanTian() {
		return dantian;
	}

	public void toPersistence() throws SQLException {
		if (dantian != null)
			dao.updateByPrimaryKey(dantian);
	}

	public int getQixuan1ResID() {
		return getQiXuanRes(new CharacterCacheEntry(character));
	}

	public int getQixuan2ResID() {
		if (character.getMyFriendManager().getRoleWedingManager().isWedding()) {
			int wedderDanTian = character.getMyFriendManager().getRoleWedingManager().getWedderDanTian();
			int wedderMenPai = character.getMyFriendManager().getRoleWedingManager().getWedderMenPai();
			return getQiXuanRes(wedderMenPai, wedderDanTian);
		}
		return 0;
	}

	private static int getQiXuanRes(int wedderPosinger, int dantiangrade) {
		DantianModel dt = DantianModelCacheManager.getInstance().getModelById(dantiangrade);
		if (dt != null && dantiangrade != -1 && wedderPosinger != -1) {
			// 职业 0 - 无,1 - 少林,2 - 武当,3 - 古墓,4 - 峨眉
			int popsinger = wedderPosinger;
			switch (popsinger) {
			case 1:
				return dt.getShaolinResid();
			case 2:
				return dt.getQuanzhenResid();
			case 3:
				return dt.getGumuResid();
			case 4:
				return dt.getEmeiResid();
			}
		}
		return 0;
	}

	private static int getQiXuanRes(CharacterCacheEntry c) {
		int dantianggrade = c.getDantiangrade();
		DantianModel dt = DantianModelCacheManager.getInstance().getModelById(dantianggrade);
		if (dt != null) {
			// 职业 0 - 无,1 - 少林,2 - 武当,3 - 古墓,4 - 峨眉
			int popsinger = c.getPopsinger();
			switch (popsinger) {
			case 1:
				return dt.getShaolinResid();
			case 2:
				return dt.getQuanzhenResid();
			case 3:
				return dt.getGumuResid();
			case 4:
				return dt.getEmeiResid();
			}
		}
		return 0;
	}
}
