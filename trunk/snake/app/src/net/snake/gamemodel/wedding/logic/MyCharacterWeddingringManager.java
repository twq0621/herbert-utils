/**
 * 
 */
package net.snake.gamemodel.wedding.logic;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.CharacterWeddingring;
import net.snake.gamemodel.wedding.persistence.CharacterWeddingringManager;
import net.snake.gamemodel.wedding.response.WeddingEndSuccessMsg52306;
import net.snake.gamemodel.wedding.response.WeddingMsg52242;


/**
 * 离婚后领取1/2玉佩 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class MyCharacterWeddingringManager {
	private static Logger logger = Logger.getLogger(MyCharacterWeddingringManager.class);

	private Object lock = new Object();
	private Hero character;
	private CharacterWeddingring ring;

	public MyCharacterWeddingringManager(Hero character) {
		this.character = character;
	}

	public void init() {
		try {
			List<CharacterWeddingring> list = CharacterWeddingringManager.getInstance().getCharacterWedingRingList(character.getId());
			if (list.size() == 0) {
				return;
			}
			ring = list.get(0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void addOrUpdateRing(int ringId, int partnerId, Date date) {
		if (ring == null) {
			synchronized (lock) {
				ring = new CharacterWeddingring();
				ring.setCharacterId(character.getId());
				ring.setRingId(ringId);
				ring.setPartnerId(partnerId);
				ring.setWeddingDate(date);
			}
			CharacterWeddingringManager.getInstance().threadInsert(ring);
		} else {
			synchronized (lock) {
				ring.setCharacterId(character.getId());
				ring.setRingId(ringId);
				ring.setPartnerId(partnerId);
				ring.setWeddingDate(date);
			}
			CharacterWeddingringManager.getInstance().threadUpdate(ring);
		}
	}

	/**
	 * 领取半配
	 */
	public void lingquRing() {
		CharacterWeddingring temp = null;
		synchronized (lock) {
			if (ring == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17558));
				return;
			}

			CharacterGoods cg = CharacterGoods.createCharacterGoods(1, ring.getRingId(), 0);
			if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(cg)) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17559));
				return;
			}
			cg.setCoupleDate(ring.getWeddingDate());
			if (character.isMale()) {
				cg.setMaleName(ring.getMyName());
				cg.setFemaleName(ring.getPartnerName());
			} else {
				cg.setFemaleName(ring.getMyName());
				cg.setMaleName(ring.getPartnerName());
			}
			boolean b = character.getCharacterGoodController().getBagGoodsContiner().addGoods(cg);
			if (!b) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17559));
				return;
			}
			temp = ring;
			ring = null;
		}
		CharacterWeddingringManager.getInstance().threadDelete(temp);
	}

	public void online() {
		if (ring == null) {
			return;
		}
		if (character.getMyFriendManager().getRoleWedingManager().isWedding()) {
			character.sendMsg(new WeddingMsg52242(ring.getRingId()));
		} else {
			character.sendMsg(new WeddingEndSuccessMsg52306(0));
		}
	}
}
