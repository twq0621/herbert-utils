package net.snake.gamemodel.equipment.bean;

import net.snake.gamemodel.goods.bean.CharacterGoods;

/**
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class CombinCondition {
	
	private int luckNum;
	private int zhenhuifu;
	private CharacterGoods characterGoods;
	private EquipmentPlayconfig equipmentPlayconfig;
	
	public EquipmentPlayconfig getEquipmentPlayconfig() {
		return equipmentPlayconfig;
	}

	public void setEquipmentPlayconfig(EquipmentPlayconfig equipmentPlayconfig) {
		this.equipmentPlayconfig = equipmentPlayconfig;
	}

	public CombinCondition(CharacterGoods characterGoods) {
		this.characterGoods = characterGoods;
	}
	
	public CombinCondition(CharacterGoods characterGoods,EquipmentPlayconfig equipmentPlayconfig) {
		this.characterGoods = characterGoods;
		this.equipmentPlayconfig = equipmentPlayconfig;
	}
	
	
	public CombinCondition(CharacterGoods characterGoods,int luckNum) {
		this.characterGoods = characterGoods;
		this.luckNum = luckNum;
	}
	
	public int getZhenhuifu() {
		return zhenhuifu;
	}

	public void setZhenhuifu(int zhenhuifu) {
		this.zhenhuifu = zhenhuifu;
	}

	public int getLuckNum() {
		return luckNum;
	}

	public void setLuckNum(int luckNum) {
		this.luckNum = luckNum;
	}

	public CharacterGoods getCharacterGoods() {
		return characterGoods;
	}

	public void setCharacterGoods(CharacterGoods characterGoods) {
		this.characterGoods = characterGoods;
	}
	
	
}
