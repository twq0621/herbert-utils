package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.chest.response.ChestTreasureMap_SquirrelResponse60112;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;

public class CharacterUseTreasureMapGood implements UseGoodAction{
	private Goodmodel gm;
	public CharacterUseTreasureMapGood(Goodmodel gm) {
		this.gm = gm;
	}
	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon,List<IUseItemListener> listeners) {
		character.sendMsg(new ChestTreasureMap_SquirrelResponse60112(character,gm));
		return false;
	}

}
