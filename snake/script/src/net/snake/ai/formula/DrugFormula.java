package net.snake.ai.formula;

import net.snake.commons.script.EvtDrugFormula;

public class DrugFormula implements EvtDrugFormula{

	@Override
	public int kuanGongPrecent() {
		return 1000;
	}

	@Override
	public int jianTiHpPrecent() {
		return 1000;
	}

	@Override
	public int jianTiMpPrecent() {
		return 1000;
	}

	@Override
	public int jianTiSp() {
		return 50;
	}

	@Override
	public int fangyuPrecent() {
		return 1000;
	}

	@Override
	public int qingshenCrtPrecent() {
		return 1000;
	}

	@Override
	public int qingshenDodgePrecent() {
		return 500;
	}


}
