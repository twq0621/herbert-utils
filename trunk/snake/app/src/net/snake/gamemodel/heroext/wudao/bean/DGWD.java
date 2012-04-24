package net.snake.gamemodel.heroext.wudao.bean;

import net.snake.gamemodel.fight.persistence.DGWDManager;
import net.snake.gamemodel.hero.bean.Hero;

public class DGWD extends CharacterDGWD {
	public DgwdModel getModel() {
		return DGWDManager.getInstance().getModel(getNowwd());
	}

	public DgwdModel getNextModel() {
		return DGWDManager.getInstance().getModel(getNowwd() + 1);
	}

	// 获取初始悟道模型
	public static DgwdModel getFirstWD() {
		return DGWDManager.getInstance().getModel(1);
	}

	public static DGWD createNewDGWD(Hero c) {
		DGWD dgwd = new DGWD();
		dgwd.setCharacterid(c.getId());
		dgwd.setFaildcount(0);
		dgwd.setLuck(0);
		dgwd.setNowwd(0);
		dgwd.setBeforeLuck(0);
		return dgwd;
	}
}
