package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;

/**
 * 怪物掉落继承该类
 * 
 * @author serv_dev
 * 
 */
public abstract class Lostgoods {
	public static int gradeChaValue = 10;
	public static int probabilityMax = 1000000;
	private int probability;// 几率 基础单位是100000
	private int maxNum = -1;
	private int nowNum;
	private boolean gradeLimit = true; // 爆率是否受等级衰减限制（真受等级限制 false不受等级限制）
	private boolean isOwner = true; // 掉落物品是否有归属
	private int bind = 0; // 掉落物品是否绑定
	public static int jiacheng = 1;

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	public boolean isGradeLimit() {
		return gradeLimit;
	}

	public void setGradeLimit(boolean gradeLimit) {
		this.gradeLimit = gradeLimit;
	}

	/**
	 * 该物品是否掉落调用此方法 返回null 表示不掉落。如果是包裹 掉落后自动完成包裹内部处理 参数loop 为轮寻次数
	 * 
	 * @return
	 */
	public abstract CharacterGoods dropLostGoods(Hero character, MonsterModel monster, float dropJiacheng);

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public int getNowNum() {
		return nowNum;
	}

	public void setNowNum(int nowNum) {
		this.nowNum = nowNum;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public int getBind() {
		return bind;
	}

	public void setBind(int bind) {
		this.bind = bind;
	}

	public void resetItemIndex() {
	}
}
