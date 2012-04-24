package net.snake.consts;

import net.snake.commons.Language;

/**
 * quickbar的位置和postion并不冲突
 * byte 1身上,2包裹,3仓库,4摊位,5马
 * @author serv_dev
 * Copyright (c) 2011 Kingnet
 */
public enum GoodsContainerType {
	
	//
	onBody(1,Language.BODY), onBag(2,Language.BAG), onStorage(3,Language.STORE), onStall(4,Language.STALL),onHorse(5,Language.HORSE),
	//TODO BAG
	onAcrossBag(6,Language.BAG),noWay(0,Language.NOWAY);
	//========================================
	private int typevalue;//在数据库的标记
	private String name;
	private GoodsContainerType(int typevalue,String name) {
		this.typevalue = typevalue;
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public int getTypeValue(){
		return this.typevalue;
	}
	public static GoodsContainerType getByValue(int num){
		switch (num) {
		case 1:
			return onBody;
		case 2:
			return onBag;
		case 3:
			return onStorage;
		case 4:
			return onStall;
		case 5:
			return onHorse;
		case 6:
			return onAcrossBag;
		default:
			return noWay;
		}
	}
	
}
