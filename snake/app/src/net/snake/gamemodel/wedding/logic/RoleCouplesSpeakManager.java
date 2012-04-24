/**
 * 
 */
package net.snake.gamemodel.wedding.logic;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.snake.GameServer;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.logic.MyFriendManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.CouplesSpeak;
import net.snake.gamemodel.wedding.persistence.CouplesSpeakManager;
import net.snake.gamemodel.wedding.response.WeddingSpeakAddMsg52234;
import net.snake.gamemodel.wedding.response.WeddingSpeakDeleteMsg52236;

/**
 * 夫妻间耳语管理 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class RoleCouplesSpeakManager {
	private MyFriendManager myFriendManager;
	private CouplesSpeakController couplesSpeakController;

	public RoleCouplesSpeakManager(MyFriendManager myFriendManager) {
		this.myFriendManager = myFriendManager;
	}

	public void init() {
		int wedderId = myFriendManager.getRoleWedingManager().getWedderId();
		if (wedderId == 0) {
			return;
		}
		int maleId = 0;
		int femaleId = 0;
		if (myFriendManager.getCharacter().isMale()) {
			maleId = myFriendManager.getCharacter().getId();
			femaleId = wedderId;
		} else {
			maleId = wedderId;
			femaleId = myFriendManager.getCharacter().getId();
		}
		couplesSpeakController = CouplesSpeakManager.getInstance().fuqiLonginInitCouplesSpeak(maleId, femaleId);
	}

	public void online() {
		init();
		GameServer.executorService.schedule(new Runnable() {
			@Override
			public void run() {
				updateAddCouplesSpeakNotic();
			}
		}, 3, TimeUnit.SECONDS);
	}

	/**
	 * 
	 */
	public void downline() {
		int wedderId = myFriendManager.getRoleWedingManager().getWedderId();
		if (wedderId == 0) {
			return;
		}
		Hero con = GameServer.vlineServerManager.getCharacterById(wedderId);
		if (con != null) {
			return;
		}
		int maleId = 0;
		if (myFriendManager.getCharacter().isMale()) {
			maleId = myFriendManager.getCharacter().getId();
		} else {
			maleId = wedderId;
		}
		CouplesSpeakManager.getInstance().removeCouplesSpeakController(maleId);
	}

	public CouplesSpeakController getCouplesSpeakController() {
		return couplesSpeakController;
	}

	public void setCouplesSpeakController(CouplesSpeakController couplesSpeakController) {
		this.couplesSpeakController = couplesSpeakController;
	}

	/**
	 * @param content
	 */
	public void addCouplesSpeak(String content) {
		int wedderId = myFriendManager.getRoleWedingManager().getWedderId();
		if (wedderId == 0) {
			myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17557));
			return;
		}
		if (couplesSpeakController == null) {
			init();
		}
		if (couplesSpeakController == null) {
			myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17557));
			return;
		}
		Hero wedder = GameServer.vlineServerManager.getCharacterById(wedderId);
		CouplesSpeak cs = null;
		if (wedder == null) {
			cs = couplesSpeakController.addCouplesSpeak(myFriendManager.getCharacter(), content, 1);
		} else {
			cs = couplesSpeakController.addCouplesSpeak(myFriendManager.getCharacter(), content, 0);
		}
		if (cs == null) {
			myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17573));
			return;
		}
		myFriendManager.getCharacter().sendMsg(new WeddingSpeakAddMsg52234(myFriendManager.getCharacter(), cs));
		if (wedder != null) {
			wedder.sendMsg(new WeddingSpeakAddMsg52234(wedder, cs));
		}
	}

	/**
	 * 添加增加耳语更新操作
	 */
	public void updateAddCouplesSpeakNotic() {
		if (couplesSpeakController == null) {
			return;
		}
		List<CouplesSpeak> list = couplesSpeakController.getList();
		if (list.size() == 0) {
			return;
		}
		CouplesSpeak cs = null;
		int cId = myFriendManager.getCharacter().getId();
		for (CouplesSpeak temp : list) {
			if (temp.getSpeakId() != cId && temp.getIsNotice() == 1) {
				cs = temp;
				temp.setIsNotice(0);
			}
		}
		if (cs != null) {
			final CouplesSpeak speak = cs;
			myFriendManager.getCharacter().sendMsg(new WeddingSpeakAddMsg52234(myFriendManager.getCharacter(), cs));
			GameServer.executorServiceForDB.execute(new Runnable() {
				public void run() {
					CouplesSpeakManager.getInstance().updateIsNoticeBySpeakId(speak.getSpeakId());
				}
			});
		}
	}

	/**
	 * @param id
	 */
	public void deleteCouplesSpeak(int id) {
		int wedderId = myFriendManager.getRoleWedingManager().getWedderId();
		if (wedderId == 0) {
			myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17557));
			return;
		}
		if (couplesSpeakController == null) {
			myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17557));
			return;
		}
		CouplesSpeak cs = couplesSpeakController.getCouplesSpeakByTempId(id);
		if (cs == null) {
			myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17570));
			return;
		}
		if (cs.getId() == null) {
			myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17571));
			return;
		}
		CouplesSpeak delete = couplesSpeakController.deleteCouplesSpeak(cs.getId());
		if (delete == null) {
			myFriendManager.getCharacter().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17572));
			return;
		}
		myFriendManager.getCharacter().sendMsg(new WeddingSpeakDeleteMsg52236(delete));
		Hero wedder = GameServer.vlineServerManager.getCharacterById(wedderId);
		if (wedder == null) {
			return;
		}
		wedder.sendMsg(new WeddingSpeakDeleteMsg52236(delete));
	}

}
