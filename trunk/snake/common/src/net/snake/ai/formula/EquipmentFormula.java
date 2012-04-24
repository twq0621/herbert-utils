package net.snake.ai.formula;

import net.snake.commons.GenerateProbability;
import net.snake.commons.script.SGoods;
import net.snake.commons.script.EvtEquipmentFormula;


public class EquipmentFormula implements EvtEquipmentFormula{

	@Override
	public boolean canAffectEquitmentProbability() {
		int a = 1;
		int b = 800;
		return GenerateProbability.isGenerate2(b, a);
	}
	
	@Override
	public int repairAffectMaxDurability(SGoods characterGoods) {
		int affect = GenerateProbability.randomIntValue(1, 5);
		//如果影响值大于当前最大耐久则不磨损
		if(affect>characterGoods.getMaxDurability()){
			affect=0;
		}
		return affect;
	}

	@Override
	public long repairEquiement(SGoods characterGoods) {
		return (characterGoods.getMaxDurability() - characterGoods
				.getCurrDurability())
				* characterGoods.getRepairMoney();
	}

	@Override
	public long specialRepairEquiement(SGoods characterGoods) {
		return (characterGoods.getMaxDurability() - characterGoods
				.getCurrDurability())
				* characterGoods.getRepairMoney() * 4;
	}

	@Override
	public int allPurpleCrt() {
		return 500;
	}

	@Override
	public int allPurpleHp() {
		return 500;
	}

	@Override
	public int allStarAttack() {
		return 500;
	}

	@Override
	public int allStarDefence() {
		return 500;
	}

	@Override
	public int equalGradeSp() {
		return 500;
	}

	@Override
	public int equalGradeDodge() {
		return 500;
	}
}
