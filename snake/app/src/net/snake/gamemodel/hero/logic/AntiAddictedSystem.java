package net.snake.gamemodel.hero.logic;

import java.util.Date;

import net.snake.gamemodel.common.response.AntiAddictedMsg10070;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.shell.Options;

/**
 * 防沉迷系统
 * 
 * @author serv_dev
 * 
 */
public class AntiAddictedSystem {

	private final Hero character;

	public AntiAddictedSystem(Hero character) {
		this.character = character;
	}

	/** 5L * 60 * 60 * 1000 */
	private final long timeClear = 5L * 60 * 60 * 1000;// 5L * 60 * 60 *
	/** 经验加成 */
	// 1000;//5小时
	private float expPlusScale = 1;//
	private boolean isFirst = true;
	/** 是否受限制（防沉迷对这个玩家是否生效）,true限制，false不限制 */
	private boolean isLimit = false;//
	private boolean isLimitAccount = false;
	private Date startDate;
	private long updateTime;

	/**
	 * 角色上线调用此方法
	 */
	public void online() {
		/*
		 * if (!ClientConfig.IS_LIMITE_ON_TIME) { return; }
		 */
		if (!Options.AntiAddicted) {
			return;
		}
		if (!isFirst) {
			return;
		}
		isFirst = false;
		isLimitAccount = character.getAccount().isAccountLimit();
		startDate = new Date();
		this.expPlusScale = 1f;
		if (!isLimitAccount) {
			return;
		}
		Date olddate = character.getLastdate();
		startDate = new Date();
		if (startDate.getTime() - olddate.getTime() >= timeClear) {
			character.setLimitOnlineTime(0);
			this.expPlusScale = 1f;
		}
		updateTime = System.currentTimeMillis();
		updateNextState();
	}

	public void downLine() {
		/*
		 * if (!ClientConfig.IS_LIMITE_ON_TIME) { return; }
		 */
		if (!Options.AntiAddicted) {
			return;
		}
		if (!isLimitAccount) {
			return;
		}
		long limiteTime = getLimiteOnlineTime();
		int limiteLeiji = (int) (limiteTime / 1000);
		character.setLimitOnlineTime(limiteLeiji);
	}

	public void update() {
		/*
		 * if (!ClientConfig.IS_LIMITE_ON_TIME) { return; }
		 */
		if (!Options.AntiAddicted) {
			return;
		}
		if (!isLimitAccount) {
			return;
		}
		//1334562683673 1334558626159

		if (System.currentTimeMillis() > updateTime) {
			updateNextState();
		}
	}

	public void updateNextState() {
		long limitOnlineTime = getLimiteOnlineTime();
		long h = limitOnlineTime / (60 * 60 * 1000);// 累计在线时间
		if (h < 1) {// 小于一小时
			long update1 = (h + 1) * (60 * 60 * 1000) - limitOnlineTime;
			updateTime = update1 + System.currentTimeMillis() + 3000;
			return;
		} else if (h < 3) {
			character.sendMsg(new AntiAddictedMsg10070(60029, h + ""));
			long update1 = (h + 1) * (60 * 60 * 1000) - limitOnlineTime;
			updateTime = update1 + System.currentTimeMillis() + 3000;
		} else if (h == 3) {
			character.sendMsg(new AntiAddictedMsg10070(60030, h + ""));
			long update1 = (h + 1) * (60 * 60 * 1000) - limitOnlineTime;
			updateTime = update1 + System.currentTimeMillis() + 3000;
		} else if (h < 5) {// 不足5小时，
			expPlusScale = 0.5f;
			character.sendMsg(new AntiAddictedMsg10070(60031));
			long update1 = (h + 1) * (60 * 60 * 1000) - limitOnlineTime;
			if (update1 <= 30 * 60 * 1000) {
				updateTime = update1 + System.currentTimeMillis() + 3000;
			} else {
				updateTime = update1 - 30 * 60 * 1000 + System.currentTimeMillis() + 3000;
			}
		} else {// 5小时后，每15分钟轮回一次
			expPlusScale = 0f;
			character.sendMsg(new AntiAddictedMsg10070(60032));
			updateTime = System.currentTimeMillis() + 15 * 60 * 1000;
		}
	}

	/**
	 * 防沉迷累计在线时间 单位毫秒
	 */
	public long getLimiteOnlineTime() {
		return character.getLimitOnlineTime() * 1000 + System.currentTimeMillis() - startDate.getTime();
	}

	public float getExpPlusScale() {
		/*
		 * if (ClientConfig.IS_LIMITE_ON_TIME) { return expPlusScale; }
		 */
		if (Options.AntiAddicted) {
			return expPlusScale;
		}
		return 1f;

	}

	public void setExpPlusScale(float expPlusScale) {
		if (expPlusScale < 1) {
			expPlusScale = 1f;
		}
		this.expPlusScale = expPlusScale;
	}

	/**
	 * 是否受限制,true限制，false不限制
	 * 
	 * @return
	 */
	public boolean getIsLimit() {
		return isLimit;
	}

	/**
	 * 是否受限制,true限制，false不限制 转换byte表示
	 * 
	 * @return
	 */
	public byte getIsLimitToByte() {
		if (isLimit) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 是否受限制,true限制，false不限制
	 * 
	 * @param isLimit
	 */
	public void setIsLimit(Boolean isLimit) {
		this.isLimit = isLimit;
	}
}
