package net.snake.gamemodel.shop.logic;

import org.apache.log4j.Logger;

import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.shop.response.ShopDazheListMsg12112;

/**
 * 打折卡倒计时 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class ShopDazheTimer implements Updatable {
	private static final Logger logger = Logger.getLogger(ShopDazheTimer.class);
	SafeTimer st;
	Hero character;
	public boolean isStart = false;

	public ShopDazheTimer(Hero character) {
		this.character = character;
	}

	public void start(int jiage) {
		if (isStart) {
			return;
		}
		this.isStart = true;
		st = new SafeTimer(jiage * 1000);
		character.getUpdateObjManager().addFrameUpdateObject(this);
		int countMonney = character.getAccount().getYuanbao();
		character.sendMsg(new ShopDazheListMsg12112(MyShopManger.dazheType, jiage, countMonney, character.getMyShopManger().getDazheList()));
	}

	public void shutdown() {
		if (!this.isStart) {
			return;
		}
		this.isStart = false;
		character.getUpdateObjManager().removeFrameUpdateObject(this);
	}

	@Override
	public void update(long now) {
		if (st.isFirstOK(now)) {
			this.isStart = false;
			character.getUpdateObjManager().removeFrameUpdateObject(this);
			try {
				character.getMyShopManger().endLingshiDazhe();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
