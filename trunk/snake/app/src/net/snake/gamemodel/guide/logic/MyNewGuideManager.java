package net.snake.gamemodel.guide.logic;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.gamemodel.across.bean.AcrossIncome;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.activities.bean.ActivityData;
import net.snake.gamemodel.activities.persistence.ActivityDataManager;
import net.snake.gamemodel.activities.persistence.DoubActivityManager;
import net.snake.gamemodel.guide.bean.CharacterNewguide;
import net.snake.gamemodel.guide.persistence.CharacterNewguideManager;
import net.snake.gamemodel.guide.response.CharacterNewGuideMsg50652;
import net.snake.gamemodel.guide.response.NewGuideActivityMsg50678;
import net.snake.gamemodel.guide.response.NewGuideLingqushouyiMsg50680;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.map.logic.LunjiantaiTsMap;

/**
 * 角色新手引导管理器（对于服务器相对简单只是对客户端新手引导进行简单存储） Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class MyNewGuideManager {
	private static Logger logger = Logger.getLogger(MyNewGuideManager.class);

	private Map<Short, CharacterNewguide> map = new HashMap<Short, CharacterNewguide>();
	private Hero character;

	// private boolean flag = true;// true 标识要发送 第一次进入场景
	public void destory() {
		if (map != null) {
			map.clear();
			map = null;
		}
	}

	public void init() {
		try {
			List<CharacterNewguide> list = CharacterNewguideManager.getInstance().selecteListByCharacterId(character.getId());
			if (list.size() == 0) {
				return;
			}
			for (CharacterNewguide cng : list) {
				map.put(cng.getGuideNum(), cng);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public MyNewGuideManager(Hero character) {
		this.character = character;
		// flag = true;
	}

	/**
	 * 检测是否完存在活动类型的新手引导
	 */
	public void online() {
		if (DoubActivityManager.getInstance().checkStartTime(System.currentTimeMillis())) {
			sendDoubleExpZhenqiActivityMsg();
		}
		// sendLunjianTaiActivityMsg();
		sendGongchengZhanActivityMsg();
		sendKuafuzhanMsg();
		sendLingquShouyiMsg();
	}

	/**
	 * 发送玩家新手当前引导记录
	 */
	public void sendNewcomeLeader() {
		if (!character.isFristEnterMap()) {
			return;
		}
		character.sendMsg(new CharacterNewGuideMsg50652(map.values()));
		// flag = false;
	}

	public CharacterNewguide getCharacterNewguide(short giudeNum) {
		return map.get(giudeNum);
	}

	public void addCharacterNewguide(CharacterNewguide cng) {
		map.put(cng.getGuideNum(), cng);
	}

	public void addOrUpdateNewGuide(byte type, byte giudeNum, byte isfinish, byte count) throws IOException {
		CharacterNewguide cng = getCharacterNewguide(giudeNum);
		if (cng == null) {
			cng = new CharacterNewguide();
			cng.setType(type);
			cng.setGuideNum((short) giudeNum);
			cng.setCharacterId(character.getId());
			cng.setGuideCount((short) count);
			if (isfinish == 0) {
				cng.setGuideIsfinish(false);
				cng.setStartDate(new Date());
			} else {
				cng.setGuideIsfinish(true);
				cng.setStartDate(new Date());
				cng.setEndDate(new Date());
			}
			addCharacterNewguide(cng);
			final CharacterNewguide cfg = cng;
			GameServer.executorServiceForDB.execute(new Runnable() {
				@Override
				public void run() {
					CharacterNewguideManager.getInstance().insert(cfg);
				}
			});
			// CharacterNewguideManager.getInstance().asynInsertCharacterNewguide(character,
			// cng);
			return;
		}
		cng.setGuideCount((short) count);
		if (isfinish == 0) {
			cng.setGuideIsfinish(false);
		} else {
			cng.setGuideIsfinish(true);
			cng.setEndDate(new Date());
		}
		CharacterNewguideManager.getInstance().asynUpdateCharacterNewguide(character, cng);
	}

	/**
	 * t_activityz表中记入活动的开启的引导
	 * 
	 * @param type
	 */
	public void sendActivityNewGaidMsg(int type) {
		ActivityData ac = ActivityDataManager.getInstance().getActivityDataById(type);
		if (ac == null) {
			return;
		}
		character.sendMsg(new NewGuideActivityMsg50678(ac));
	}

	public void sendLunjianTaiActivityMsg() {
		if (character.getGrade() < 60) {
			return;
		}
		if (!LunjiantaiTsMap.isOpentTime) {
			return;
		}
		// sendActivityNewGaidMsg(8);
	}

	/**
	 * 
	 */
	public void sendGongchengZhanActivityMsg() {
		if (character.getGrade() <= 25) {
			return;
		}
		if (!GongchengTsMap.isGongchenging) {
			return;
		}
		sendActivityNewGaidMsg(9);
	}

	/**
	 * 双倍真气经验开启 玩家提示
	 */
	public void sendDoubleExpZhenqiActivityMsg() {
		sendActivityNewGaidMsg(3);
	}

	/**
	 * 
	 */
	public void sendKuafuzhanMsg() {
		if (character.getGrade() < 60) {
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null) {
			if (!asd.isTimeExpression(System.currentTimeMillis() + 6000)) {
				return;
			}
		}
		sendActivityNewGaidMsg(10);

	}

	/**
	 * 领取收益
	 */
	public void sendLingquShouyiMsg() {
		if (character.getGrade() < 60) {
			return;
		}
		AcrossIncome ai = character.getMyCharacterAcrossIncomeManager().getAi();
		if (ai == null) {
			return;
		}
		if (ai.getExp() <= 0 && ai.getCopper() <= 0 && ai.getShengwang() <= 0) {
			return;
		}
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null) {
			if (asd.isTimeExpression()) {
				return;
			}
		}
		character.sendMsg(new NewGuideLingqushouyiMsg50680());
	}
}
