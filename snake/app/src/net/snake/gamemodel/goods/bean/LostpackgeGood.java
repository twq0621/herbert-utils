package net.snake.gamemodel.goods.bean;

import net.snake.commons.GenerateProbability;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.logic.lostgoods.Lostgoods;

public class LostpackgeGood extends Lostgoods {

	private Goodspackge gpd;

	// private List<Goodmodel> goodList = new ArrayList<Goodmodel>();
	// private int nowlistIndex = 0;

	public LostpackgeGood(Goodspackge gpd) {
		this.gpd = gpd;
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
			dropJiacheng = 10000;
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
		if (!isDrop) {
			return null;
		}
		CharacterGoods cg = gpd.dropLostGoods(monster);
		if (cg == null) {
			return null;
		}
		if (this.getBind() == 1) {
			cg.setBind(CommonUseNumber.byte1);
		}
		cg.setOwner(this.isOwner());
		return cg;
	}
}
