package net.snake.gamemodel.heroext.wudao.persistence;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.ai.formula.CharacterFormula;
import net.snake.commons.BeanUtils;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.fight.persistence.DGWDManager;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.heroext.wudao.bean.CharacterDGWD;
import net.snake.gamemodel.heroext.wudao.bean.DGWD;
import net.snake.gamemodel.heroext.wudao.bean.DgwdModel;
import net.snake.gamemodel.heroext.wudao.response.WDGXMsg10510;
import net.snake.gamemodel.heroext.wudao.response.WDUpgradeResult53266;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

public class DGWDController extends CharacterController {
	private static final Logger logger = Logger.getLogger(DGWDController.class);
	/** 默认打座光效 */
	private static int DEFAULT_DZGX = 10329;

	public DGWDController(Hero character) {
		super(character);

	}

	DGWD dgwd;
	private CharacterDGWDDAO dao = new CharacterDGWDDAO(SystemFactory.getCharacterSqlMapClient());

	public void destroy() {
		dgwd = null;
	}

	/**
	 * 初始
	 * 
	 * @throws SQLException
	 */
	public void init() {
		CharacterDGWD entry = null;
		try {
			entry = dao.selectByPrimaryKey(character.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		if (entry != null) {
			dgwd = new DGWD();
			BeanUtils.copyProperties(entry, dgwd);
			// 上次更新时间离登录时间超过24小时且幸运值不为零的清掉
			if (dgwd.getLuckLastclear() != null && dgwd.getLuck() > 0) {
				long lasttime = dgwd.getLuckLastclear().getTime();
				long elapsetime = System.currentTimeMillis() - lasttime;
				long hour = elapsetime / (1000 * 60 * 60);
				if (hour >= 24) {
					dgwd.setBeforeLuck(entry.getLuck());
					dgwd.setLuck(0);
					dgwd.setLuckLastclear(new Date(System.currentTimeMillis()));
					try {
						dao.updateByPrimaryKey(dgwd);
					} catch (SQLException e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
			initCharacterProperty(dgwd);
		} else {
			dgwd = DGWD.createNewDGWD(character);
			try {
				dao.insert(dgwd);
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	private void initCharacterProperty(DGWD dgwd) {
		if (dgwd != null && dgwd.getNowwd() > 0) {
			for (int i = 1; i <= dgwd.getNowwd(); i++) {
				character.getPropertyAdditionController().addChangeListener(DGWDManager.getInstance().getModel(i));
			}
		}
	}

	/**
	 * 悟道
	 * 
	 * @return
	 * @throws IOException
	 */
	public void upgrade() throws IOException {
		if (dgwd.getNowwd() >= 9) {
			return;
		}
		DgwdModel model = dgwd.getNextModel();
		Integer upNeedgoodid = model.getConsumeGoodid();
		Integer upNeedgoodnum = model.getConsumeNum();
		Integer consumeZq = model.getConsumeZq();
		Integer upNeedMinFaild = model.getNeedMinfaild();
		Integer upNeedMaxFaild = model.getNeedMaxfaild();
		if (consumeZq != null && consumeZq >= 0) {
			if (character.getZhenqi() < consumeZq) {
				character.sendRightPrompMsg(604);
				return;
			}
		}
		if (upNeedgoodid != null && upNeedgoodid > 0 && upNeedgoodnum != null && upNeedgoodnum > 0) {
			int bagGoodsCountByModelID = character.getCharacterGoodController().getBagGoodsCountByModelID(upNeedgoodid);
			if (bagGoodsCountByModelID < upNeedgoodnum) {
				Goodmodel goodmodel = GoodmodelManager.getInstance().get(upNeedgoodid);
				character.sendRightPrompMsg(50073, goodmodel.getNameI18n());
				processUpCheckFaild();
				return;
			} else {
				if (!character.getCharacterGoodController().deleteGoodsFromBag(upNeedgoodid, upNeedgoodnum)) {
					// 扣除物品失败
					character.sendRightPrompMsg(50066);
					processUpCheckFaild();
					return;
				}
			}
		}
		if (consumeZq != null && consumeZq >= 0) {
			CharacterPropertyManager.changeZhenqi(character, -consumeZq);
		}
		if (dgwd.getFaildcount() <= upNeedMinFaild) {
			processUpFaild();
			return;
		}
		if (dgwd.getFaildcount() < upNeedMaxFaild && !GenerateProbability.defaultIsGenerate(model.getUpprob())) {
			// 失败
			processUpFaild();
			return;
		}
		processUpSuccess();
	}

	public void processUpCheckFaild() throws IOException {
		// 操作失败
		character.sendRightPrompMsg(50074);
		character.sendMsg(new WDUpgradeResult53266(false));
	}

	public void processUpSuccess() throws IOException {
		dgwd.setNowwd(dgwd.getNowwd() + 1);
		dgwd.setLuck(0);
		dgwd.setFaildcount(0);
		syncUpdate();
		character.getPropertyAdditionController().addChangeListener(dgwd.getModel());
		character.sendRightPrompMsg(50075, dgwd.getModel().getNameI18n());
		character.sendMsg(new WDUpgradeResult53266(true));
		character.getEyeShotManager().sendMsg(new WDGXMsg10510(character.getId(), getDZEffectId()));
		CharacterFormula.sendCharacterProperties(character);
	}

	public void processUpFaild() throws IOException {
		DgwdModel model = dgwd.getNextModel();
		int randomIntValue = GenerateProbability.randomIntValue(model.getFaildZhufuMin(), model.getFaildZhufuMax());
		dgwd.setLuck(dgwd.getLuck() + randomIntValue);
		dgwd.setFaildcount(dgwd.getFaildcount() + 1);
		syncUpdate();
		character.sendRightPrompMsg(50076, randomIntValue + "");
		character.sendMsg(new WDUpgradeResult53266(false));
	}

	private void syncUpdate() {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updateByPrimaryKeySelective(dgwd);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	private void clearzhufu() {
		if (Options.IsCrossServ) {
			return;
		}
		if (dgwd == null) {
			return;
		}
		// 只设内存..数据库另行统一UPDATE
		if (dgwd != null) {
			dgwd.setBeforeLuck(dgwd.getLuck());
			dgwd.setLuck(0);
			dgwd.setLuckLastclear(new Date(System.currentTimeMillis()));
		}
	}

	public static void clearAllZhuFu() {
		if (Options.IsCrossServ) {
			return;
		}
		CharacterRun characterRun = new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.getDgwdController().clearzhufu();
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
			CharacterManager.getInstance().clearLuckValueForDGWD();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 上线时作最后清除时间的判断 大于24小时则清零
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}

	public DGWD getDGWD() {
		return dgwd;
	}

	/**
	 * 是否悟通了某式
	 * 
	 * @param model
	 * @return
	 */
	public boolean hasDGWD(DgwdModel model) {
		return dgwd != null && dgwd.getNowwd() >= model.getId();
	}

	public int getProb(String item) {
		int crt = 0;
		int aqjv = 0;
		int hwhurt = 0;
		int bow = 0;
		int dantian = 0;
		if (dgwd != null && dgwd.getNowwd() > 0) {
			for (int i = 1; i <= dgwd.getNowwd(); i++) {
				DgwdModel model = DGWDManager.getInstance().getModel(i);
				if ("crt".equals(item)) {
					int tmp = model.getReduceBtscrt() + model.getReduceGmcrt() + model.getReduceSlcrt() + model.getReduceThdcrt() + model.getReduceWdcrt();
					crt += (model.getReduceAllcrt() + tmp / 5) / 100;
				}
				if ("aqjv".equals(item)) {
					aqjv += model.getReduceAqjv() / 100;
				}
				if ("hwhurt".equals(item)) {
					hwhurt += model.getReduceHwhurt() / 100;
				}
				if ("bow".equals(item)) {
					bow += model.getReduceBow() / 100;
				}
				if ("dantian".equals(item)) {
					dantian += model.getReduceDantian() / 100;
				}
			}
		}
		if ("crt".equals(item)) {
			return crt;
		}
		if ("aqjv".equals(item)) {
			return aqjv;
		}
		if ("hwhurt".equals(item)) {
			return hwhurt;
		}
		if ("bow".equals(item)) {
			return bow;
		}
		if ("dantian".equals(item)) {
			return dantian;
		}
		return 0;
	}

	public int getDZEffectId() {
		// if (getDGWD() != null && getDGWD().getNowwd() > 0) {
		// return getDGWD().getModel().getDzeffectId();
		// } else {
		// }
		return DEFAULT_DZGX;
	}

	public void toPersistence() throws SQLException {
		if (dgwd != null)
			dao.updateByPrimaryKey(dgwd);
	}
}
