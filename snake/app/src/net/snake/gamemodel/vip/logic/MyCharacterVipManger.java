package net.snake.gamemodel.vip.logic;

import net.snake.GameServer;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.consts.BuffId;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.gamemodel.vip.bean.CharacterVip;
import net.snake.gamemodel.vip.persistence.CharacterVipManager;
import net.snake.gamemodel.vip.response.CharacterVipBufferMsg11150;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * 作用后 一段时间会消失的buffer，此类buffer只是标识作用
 * 
 * @author serv_dev
 */
public class MyCharacterVipManger {
	private static Logger logger = Logger.getLogger(MyCharacterVipManger.class);
	private Hero character;
	// private boolean isFrist=true;
	public Map<Integer, CharacterVip> map = new ConcurrentHashMap<Integer, CharacterVip>();

	public MyCharacterVipManger(Hero character) {
		this.character = character;
	}

	public void destroy() {
		if (map != null) {
			map.clear();
			// map = null;
		}
	}

	public void init() {
		try {
			Date now = new Date();
			List<CharacterVip> list = CharacterVipManager.getInstance().selecteListByCharacterId(character.getId());
			for (CharacterVip cv : list) {
				if (cv.getEndTime().after(now)) {
					cv.getEffectInfo();
					map.put(cv.getBufferId(), cv);
				} else {
					CharacterVipManager.getInstance().delete(cv);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void sendVipInfo() {
		if (map.size() == 0) {
			return;
		}
		if (!character.isFristEnterMap()) {
			return;
		}
		// isFrist=false;
		// for (final CharacterVip cv : map.values()) {
		// if (cv.getEndTime().after(new Date())) {
		// character.sendMsg(new CharacterVipBufferMsg11150(cv));
		// }
		// }
	}

	public List<CharacterVip> getCharacterVipList() {
		List<CharacterVip> list = new ArrayList<CharacterVip>();
		for (final CharacterVip cv : map.values()) {
			if (cv.getEndTime().after(new Date())) {
				list.add(cv);
			}
		}
		return list;
	}

	/**
	 * 是否vip月卡用户
	 * 
	 * @return
	 */
	public boolean isVipYueka() {
		CharacterVip cv = map.get(BuffId.vipYuekaId);
		if (cv == null) {
			return false;
		}
		if (cv.getEndTime().after(new Date())) {
			return true;
		} else {
			return false;
		}
	}

	public void update() {
		if (map.size() == 0) {
			return;
		}
		for (final CharacterVip cv : map.values()) {
			if (!cv.getEndTime().after(new Date())) {
				removeVip(cv);
			}
		}
	}

	private void removeVip(final CharacterVip cv) {
		map.remove(cv.getBufferId());
		try {
			FightMsgSender.sendCancelSustainEffectMsg(character, cv.getEffectInfo());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				CharacterVipManager.getInstance().delete(cv);
			}
		});
	}

	public boolean useVip(SkillEffect se) {
		if (isCharacterVip(se.getId())) {
			return false;
		}
		CharacterVip cv = new CharacterVip();
		cv.setBufferId(se.getId());
		cv.setCharacterId(character.getId());
		cv.setStartTime(new Date());
		long jiage = se.getDuration();
		long t = jiage * 1000;
		cv.setEndTime(new Date(System.currentTimeMillis() + t));
		map.put(cv.getBufferId(), cv);
		character.sendMsg(new CharacterVipBufferMsg11150(cv));
		CharacterVipManager.getInstance().asynInsertCharacterGiftpacks(character, cv);
		// isFrist=false;
		return true;
	}

	public boolean useVipBoardMsg(SkillEffect se) {
		if (isCharacterVip(se.getId())) {
			return false;
		}
		CharacterVip cv = new CharacterVip();
		cv.setBufferId(se.getId());
		cv.setCharacterId(character.getId());
		cv.setStartTime(new Date());
		long jiage = se.getDuration();
		long t = jiage * 1000;
		cv.setEndTime(new Date(System.currentTimeMillis() + t));
		map.put(cv.getBufferId(), cv);
		character.getEyeShotManager().sendMsg(new CharacterVipBufferMsg11150(cv));
		CharacterVipManager.getInstance().asynInsertCharacterGiftpacks(character, cv);
		// isFrist=false;
		return true;
	}

	public boolean isCharacterVip(int bufferID) {
		CharacterVip cv = map.get(bufferID);
		if (cv == null) {
			return false;
		}
		if (cv.getEndTime().after(new Date())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addKanQiFeibaohuBuffer() {
		if (isCharacterVip(BuffId.KanQiFeiBaoHUBufferId)) {
			return false;
		}
		SkillEffect se = SkillEffectManager.getInstance().getSkillEffectById(BuffId.KanQiFeiBaoHUBufferId);
		if (se == null) {
			return false;
		}
		removeHuQiFeibaohuBuffer();
		character.getEffectController().clearPkProtectEffect(true);
		return useVipBoardMsg(se);
	}

	public boolean isFeibaohuState() {
		CharacterVip cv = map.get(BuffId.KanQiFeiBaoHUBufferId);
		if (cv == null) {
			cv = map.get(BuffId.HuQiFeiBaoHuBufferId);
		}
		if (cv == null) {
			return false;
		}
		if (cv.getEndTime().after(new Date())) {
			return true;
		} else {
			removeVip(cv);
			return false;
		}
	}

	public boolean addHuQiFeibaohuBuffer() {
		if (isFeibaohuState()) {
			return false;
		}
		SkillEffect se = SkillEffectManager.getInstance().getSkillEffectById(BuffId.HuQiFeiBaoHuBufferId);
		if (se == null) {
			return false;
		}
		character.getEffectController().clearPkProtectEffect(true);
		return useVipBoardMsg(se);
	}

	/**
	 * 移除护旗非保护buffer
	 */
	public void removeHuQiFeibaohuBuffer() {
		if (isHuQiFeibaohuBuffer()) {
			CharacterVip cv = map.get(BuffId.HuQiFeiBaoHuBufferId);
			removeVip(cv);
		}
	}

	public boolean isHuQiFeibaohuBuffer() {
		CharacterVip cv = map.get(BuffId.HuQiFeiBaoHuBufferId);
		if (cv == null) {
			return false;
		}
		if (cv.getEndTime().after(new Date())) {
			return true;
		} else {
			removeVip(cv);
			return false;
		}
	}
}
