package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class HeroXingfu implements IbatisEntity {
	
	private int id;
	private int type;
	private int xfvalue;
	private int modelId;
	private int heroId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getXfvalue() {
		return xfvalue;
	}

	public void setXfvalue(int xfvalue) {
		this.xfvalue = xfvalue;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public int getHeroId() {
		return heroId;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

}
