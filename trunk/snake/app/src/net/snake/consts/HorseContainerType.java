package net.snake.consts;

import net.snake.commons.Language;

public enum HorseContainerType {
	// quickbar的位置和postion并不冲突
	// byte 1身上,2包裹,3仓库,4摊位,5马

	onBag(2, Language.BAG), onStorage(3, Language.STORE), onStall(4, Language.STALL);
	// ========================================
	private int typevalue;// 在数据库的标记
	private String name;

	private HorseContainerType(int typevalue, String name) {
		this.typevalue = typevalue;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getTypeValue() {
		return this.typevalue;
	}

	public static HorseContainerType getByValue(int num) {
		switch (num) {
		case 2:
			return onBag;
		case 3:
			return onStorage;
		case 4:
			return onStall;
		}
		return null;
	}

}
