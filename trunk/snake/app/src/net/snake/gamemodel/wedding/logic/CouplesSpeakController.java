/**
 * 
 */
package net.snake.gamemodel.wedding.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.CouplesSpeak;
import net.snake.gamemodel.wedding.persistence.CouplesSpeakManager;


/**
 * 夫妻耳语数据存储控制（以夫妻为单位进行操作）
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class CouplesSpeakController {
	private int maleId = 0;
	private int femaleId = 0;
	private List<CouplesSpeak> list = new ArrayList<CouplesSpeak>();

	/**
	 * @param maleId2
	 */
	public CouplesSpeakController(int maleId2, int femaleId2) {
		this.maleId = maleId2;
		this.femaleId = femaleId2;
	}

	public void init() {
		List<CouplesSpeak> list1 = CouplesSpeakManager.getInstance()
				.getListByMaleId(maleId);
		if (list1.size() == 0) {
			return;
		}
		list = list1;
	}

	/**
 * 
 */
	public void destroy() {
		list.clear();
	}

	/*
 * 
 */
	public CouplesSpeak addCouplesSpeak(Hero speaker, String context,
			int isNotice) {
		if (list.size() >= 30) {
			return null;
		}
		CouplesSpeak cs;
		synchronized (this) {
			cs = new CouplesSpeak();
			cs.setMaleId(this.maleId);
			cs.setFemaleId(this.femaleId);
			cs.setSpeakId(speaker.getId());
			cs.setSpeakDate(new Date());
			cs.setContent(context);
			cs.setIsNotice(isNotice);
			list.add(cs);
		}
		final CouplesSpeak temp = cs;
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				CouplesSpeakManager.getInstance().insert(temp);
			}
		});
		return temp;
	}
	public CouplesSpeak deleteCouplesSpeak(final int id) {
		CouplesSpeak remove = null;
		synchronized (this) {
			remove = removeCouplesSpeakById(id);
		}
		if (remove == null) {
			return null;
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				CouplesSpeakManager.getInstance().deleteById(id);

			}
		});
		return remove;
	}

	public CouplesSpeak getCouplesSpeakByTempId(int tempId) {
		if (list.size() == 0) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			CouplesSpeak cs = list.get(i);
			if (tempId == cs.getTempId()) {
				return cs;
			}
		}
		return null;
	}

	private CouplesSpeak removeCouplesSpeakById(int id) {
		if (list.size() == 0) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			CouplesSpeak cs = list.get(i);
			if (id == cs.getId()) {
				list.remove(i);
				return cs;
			}
		}
		return null;
	}

	public int getCouplesSpeakSize() {
		return list.size();
	}

	public List<CouplesSpeak> getList() {
		return list;
	}

}
