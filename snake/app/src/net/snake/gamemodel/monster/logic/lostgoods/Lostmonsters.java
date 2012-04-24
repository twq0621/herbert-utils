package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;


/**
 * 怪物掉落继承该类
 * @author serv_dev
 *
 */
public abstract class Lostmonsters{
public static int gradeChaValue=20;
private int probability;//几率  基础单位是10000
private int maxNum=-1;
private int nowNum;
protected static int jiacheng=1;
private boolean gradeLimit=true; //爆率是否受等级衰减限制（真受等级限制 false不受等级限制）

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
 * 该物品是否掉落调用此方法 返回null 表示不掉落。如果是包裹 掉落后自动完成包裹内部处理
 * 参数loop 为轮寻次数
 * @return
 */
public abstract SceneMonster dropLostMonsters(Hero character, MonsterModel monster);

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

}
