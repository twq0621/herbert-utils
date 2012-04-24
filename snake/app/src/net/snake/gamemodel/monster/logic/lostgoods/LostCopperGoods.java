package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;

/**
 * 铜币物品掉落
 * 
 * @author serv_dev
 * 
 */
public class LostCopperGoods extends Lostgoods {
	private int count = 0; // 物品数量

	public LostCopperGoods(int count) {
		this.count = count;
	}

	@Override
	public CharacterGoods dropLostGoods(Hero character, MonsterModel monster, float dropJiacheng) {
		int gradeCha = 0;
		if (character != null) {
			gradeCha = Math.abs(character.getGrade() - monster.getGrade());
		}
		if (isGradeLimit()) {
			if (gradeCha >= gradeChaValue) {
				return null;
			}
		}
		if (jiacheng < 1) {
			jiacheng = 1;
		}
		if (dropJiacheng < 100) {
			dropJiacheng = 10000f;
		}
		boolean isDrop = GenerateProbability.isGenerateToBoolean(getProbability() * jiacheng * dropJiacheng / 10000, probabilityMax);
		if (getMaxNum() > 1) {
			int nowNum = getNowNum();
			nowNum++;
			if (getMaxNum() == nowNum) {
				isDrop = true;
				nowNum = 0;
			}
			setNowNum(nowNum);
		}
		if (isDrop) {
			CharacterGoods cg = new CharacterGoods();
			cg.setGoodmodelId(-1);
			int countTemp = count;
			if (character != null && character.isAllBornEquip()) {
				countTemp = (int) (countTemp + count * 0.1f);
			}
			cg.setCount(countTemp);
			return cg;
		}
		return null;
	}

}
