/**
 * 
 */
package net.snake.gamemodel.wedding.logic;

import net.snake.GameServer;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.wedding.bean.Couples;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.persistence.CharacterWeddingringManager;
import net.snake.gamemodel.wedding.persistence.CouplesManager;
import net.snake.gamemodel.wedding.persistence.CouplesSpeakManager;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.gamemodel.wedding.persistence.WeddingRingManager;
import net.snake.gamemodel.wedding.response.WedderFavorUpdateMsg52364;
import net.snake.gamemodel.wedding.response.WeddingMsg52242;

import org.apache.log4j.Logger;


/**
 * 夫妻聊天控制器
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */

public class CouplesController {
	private static final Logger logger = Logger.getLogger(CouplesController.class);
	public static int QIANGZHILIHUN_COPPER = 2000000;
	private Couples couples;
	public Object lock = new Object();

	/**
	 * @param couples
	 */
	public CouplesController(Couples couples) {
		this.couples = couples;
	}

	public Couples getCouples() {
		return couples;
	}

	public void setCouples(Couples couples) {
		this.couples = couples;
	}

	/**
	 * @param character
	 * @param wr
	 */
	public void upGradeRing(Hero character, WeddingRing wr) {
		synchronized (lock) {
			WeddingRing my = WeddingRingManager.getInstance()
					.getWeddingRingById(getCouples().getRingId());
			if (my == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,
						17503));
				return;
			}
			if (wr.getGrade() <= my.getGrade()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,
						17560));
				return;
			}
			boolean b = character.getCharacterGoodController()
					.getBagGoodsContiner()
					.deleteGoodsByModel(wr.getRingId(), 1);
			if (!b) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,
						17559));
				return;
			}
			giveHalfRing(character, getCouples(),true);
			getCouples().setRingId(wr.getRingId());
		}
		updateFuqiInfo();
		final Couples temp = new Couples();
		temp.setId(getCouples().getId());
		temp.setRingId(getCouples().getRingId());
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				CouplesManager.getInstance().update(temp);
			}
		});
	}

	/**
	 * 更新在线玩家夫妻属性
	 */
	public void updateFuqiInfo() {
		Hero male = GameServer.vlineServerManager.getCharacterById(this
				.getCouples().getMaleId());
		Hero feMale = GameServer.vlineServerManager.getCharacterById(this
				.getCouples().getFemaleId());
		if (male != null) {
			male.getMyFriendManager().getRoleWedingManager().updateFuqiInfo();
		}
		if (feMale != null) {
			feMale.getMyFriendManager().getRoleWedingManager().updateFuqiInfo();
		}
	}

	/**
	 * @param character
	 * @param isQiangzhi
	 */
	public void overWedding(Hero character, boolean isQiangzhi) {
		synchronized (lock) {
			if (getCouples() == null) {
				return;
			}
			if (isQiangzhi) {
				if (character.getCopper() < QIANGZHILIHUN_COPPER) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,
							17540));
					return;
				}
				CharacterPropertyManager.changeCopper(character,
						-QIANGZHILIHUN_COPPER,CopperAction.CONSUME);
			}
			Couples temp = getCouples();
			this.couples = null;
			CouplesSpeakManager.getInstance().deleteCouplesSpeakContriller(
					temp.getMaleId());
			giveHalfRing(character, temp,false);
			onEndWeddingClearFuqiInfo(temp);
			CouplesManager.getInstance().removeCouplesController(temp);
			WedFeastManager.getInstance().clearFeast(temp.getMaleId(),
					temp.getFemaleId());
			this.couples = null;
		}

	}

	/**
	 * 删号清楚夫妻关系
	 * 
	 * @param character
	 * @param isQiangzhi
	 */
	public void overWeddingWhenDeleteCharacter(int characterId) {
		synchronized (lock) {
			if (getCouples() == null) {
				return;
			}
			Couples temp = getCouples();
			this.couples = null;
			CouplesSpeakManager.getInstance().deleteCouplesSpeakContriller(
					temp.getMaleId());
			// giveHalfRing(character,temp);
			onEndWeddingClearFuqiInfo(temp);
			CouplesManager.getInstance().removeCouplesController(temp);
			 WedFeastManager.getInstance().clearFeast(temp.getMaleId(),
			 temp.getFemaleId());
			this.couples = null;
		}

	}

	/**
	 * 产生半佩
	 * 
	 * @param character
	 */
	public void giveHalfRing(Hero character, Couples temp,boolean isUpGrade
			) {
		try {
			WeddingRing wr = temp.getWr();
			Hero male = GameServer.vlineServerManager
					.getCharacterById(temp.getMaleId());
			Hero feMale = GameServer.vlineServerManager
					.getCharacterById(temp.getFemaleId());
			if (male != null) {
				male.getMyCharacterWeddingringManager().addOrUpdateRing(
						wr.getMaleGood(), temp.getFemaleId(),
						temp.getWeddingDate());
				if (isUpGrade) {
					male.sendMsg(new WeddingMsg52242(wr.getMaleGood()));
				}
			} else {
				CharacterWeddingringManager.getInstance().threadAddOrUpdate(
						wr.getMaleGood(), temp.getMaleId(),
						temp.getWeddingDate(), temp.getFemaleId());
			}
			if (feMale != null) {

				feMale.getMyCharacterWeddingringManager().addOrUpdateRing(
						wr.getFemaleGood(), temp.getMaleId(),
						temp.getWeddingDate());
				if (isUpGrade) {
					feMale.sendMsg(new WeddingMsg52242(wr.getFemaleGood()));
				}
			} else {
				CharacterWeddingringManager.getInstance().threadAddOrUpdate(
						wr.getFemaleGood(), temp.getFemaleId(),
						temp.getWeddingDate(), temp.getMaleId());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * 清楚夫妻双方在线角色结婚信息
	 * 
	 * @param temp
	 */
	public void onEndWeddingClearFuqiInfo(Couples temp) {
		try {
			Hero male = GameServer.vlineServerManager
					.getCharacterById(temp.getMaleId());
			Hero feMale = GameServer.vlineServerManager
					.getCharacterById(temp.getFemaleId());
			if (male != null) {
				male.getMyFriendManager().getRoleWedingManager()
						.onEndWedding(temp.getFemaleId());
			}
			if (feMale != null) {
				feMale.getMyFriendManager().getRoleWedingManager()
						.onEndWedding(temp.getMaleId());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	private int maleTeamFavor = 0;
	private int femaleTeamFavor = 0;

	/**
	 * 同地图打怪好感度更新
	 * 
	 * @param character
	 * @param i
	 */
	public void updateTeamFavor(Hero character, int i) {
		if (character.isMale()) {
			maleTeamFavor = maleTeamFavor + i;
		} else {
			femaleTeamFavor = femaleTeamFavor + i;
		}
		updateTeamFavorToFavor();
	}

	/**
	 * 更新夫妻好感度
	 */
	private void updateTeamFavorToFavor() {
		if (maleTeamFavor >= 40 || femaleTeamFavor >= 40) {
			maleTeamFavor = 0;
			femaleTeamFavor = 0;
			updateFuqiFavor(1);
		}
	}

	/**
	 * 夫妻间好感度更新
	 */
	public int updateFuqiFavor(int count) {
		this.couples.setMaleFavor(this.getCouples().getMaleFavor() + count);
		this.couples.setFemaleFavor(this.couples.getFemaleFavor() + count);
		final Couples temp = new Couples();
		temp.setId(this.couples.getId());
		temp.setMaleFavor(this.couples.getMaleFavor());
		temp.setFemaleFavor(this.couples.getFemaleFavor());
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				CouplesManager.getInstance().update(temp);
			}
		});
		sendFuqiFavorMsg();
		updateFuqiFavorCount();
		return count;

	}

	/**
	 * 发送夫妻间友好度更新
	 */
	public void sendFuqiFavorMsg() {
		Hero male = GameServer.vlineServerManager.getCharacterById(this
				.getCouples().getMaleId());
		Hero feMale = GameServer.vlineServerManager.getCharacterById(this
				.getCouples().getFemaleId());
		if (male != null) {
			male.sendMsg(new WedderFavorUpdateMsg52364(male, feMale,
					this.couples));
		}
		if (feMale != null) {
			feMale.sendMsg(new WedderFavorUpdateMsg52364(feMale, male,
					this.couples));
		}
	}

	/**
	 * @param character
	 */
	public void onlineUpdate(Hero character) {
		Hero wedder = null;
		if (character.isMale()) {
			wedder = GameServer.vlineServerManager
					.getCharacterById(this.couples.getFemaleId());
		} else {
			wedder = GameServer.vlineServerManager
					.getCharacterById(this.couples.getMaleId());
		}
		if (wedder != null) {
			wedder.sendMsg(new WedderFavorUpdateMsg52364(wedder, character,
					this.couples));
		}
	}

	/**
	 * @param character
	 */
	public void downlinUpdate(Hero character) {
		Hero wedder = null;
		if (character.isMale()) {
			wedder = GameServer.vlineServerManager
					.getCharacterById(this.couples.getFemaleId());
		} else {
			wedder = GameServer.vlineServerManager
					.getCharacterById(this.couples.getMaleId());
		}
		if (wedder != null) {
			wedder.sendMsg(new WedderFavorUpdateMsg52364(character.getId()));
		}
	}

	/**
	 * 更新夫妻间好感度更新统计值
	 */
	public void updateFuqiFavorCount() {
		Hero male = GameServer.vlineServerManager
				.getCharacterById(this.couples.getMaleId());
		Hero feMale = GameServer.vlineServerManager
				.getCharacterById(this.couples.getFemaleId());
		if (male != null) {
			male.getMyCharacterAchieveCountManger().getFriendCount()
					.weddingFavorCount(this.couples.getMaleFavor());
		}
		if (feMale != null) {
			feMale.getMyCharacterAchieveCountManger().getFriendCount()
					.weddingFavorCount(this.couples.getFemaleFavor());
		}
	}
}
