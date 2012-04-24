package net.snake.gamemodel.hero.logic;

import java.util.Map;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.HeroXingfu;
import net.snake.gamemodel.hero.persistence.HeroXingfuManager;

public class MyHeroXingfuController {

	protected final Hero hero;

	Map<String, HeroXingfu> heroXingfuMap;

	public MyHeroXingfuController(Hero hero) {
		this.hero = hero;
	}

	public Hero getHero() {
		return this.hero;
	}

	public void init() {
		heroXingfuMap = HeroXingfuManager.getInstance().getHeroXingfu(hero.getId());
	}

	public HeroXingfu getHeroXingfu(int type, int modelId) {
		HeroXingfu heroXingfu = heroXingfuMap.get(type + "_" + modelId);
		if (heroXingfu == null) {
			heroXingfu = this.createHeroXingfu(type, modelId);
		}
		return heroXingfu;
	}

	private HeroXingfu createHeroXingfu(int type, int modelId) {
		HeroXingfu heroXingfu = new HeroXingfu();
		heroXingfu.setHeroId(hero.getId());
		heroXingfu.setType(type);
		heroXingfu.setModelId(modelId);
		heroXingfu.setXfvalue(0);

		return heroXingfu;
	}
}
