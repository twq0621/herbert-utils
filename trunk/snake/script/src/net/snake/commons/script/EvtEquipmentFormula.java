package net.snake.commons.script;


/**
 * 装备公式
 * @author serv_dev
 *
 */
public interface EvtEquipmentFormula {

	/**
	 * (最大耐久度 - 当前耐久度) * 修理单价 * 3
	 * 
	 * @param SCharacterGoods
	 * @return 修理金额（单位:铜币）
	 */
	public long specialRepairEquiement(SGoods sCharacterGoods);

	/**
	 * (最大耐久度 - 当前耐久度) * 修理单价
	 * 
	 * @param SCharacterGoods
	 * @return 修理金额（单位:铜币）
	 */
	public long repairEquiement(SGoods sCharacterGoods);

	/**
	 * 最大耐久度/当前耐久度/2 最多减10点耐久
	 * 
	 * @param SCharacterGoods
	 * @return 减少最大耐久度
	 */
	public int repairAffectMaxDurability(SGoods sCharacterGoods);

	/**
	 * 当玩家攻击怪物或者被怪物攻击时均有八百分之一的概率减少1点当前耐久
	 * 
	 * @return 概率
	 */
	public boolean canAffectEquitmentProbability();
	
	/**
	 * 全紫装带来的暴击百分比 单位为1w
	 * @return
	 */
	public int allPurpleCrt();
	
	/**
	 * 全紫装带来的血量百分比 单位为1w
	 * @return
	 */
	public int allPurpleHp();
	
	/**
	 * 全星带来的血量百分比 单位为1w
	 * @return
	 */
	public int allStarAttack();
	
	/**
	 * 全星装带来的血量百分比 单位为1w
	 * @return
	 */
	public int allStarDefence();
	
	/**
	 * 同等级带来的体力百分比
	 * @return
	 */
	public int equalGradeSp();
	
	/**
	 * 同等级带来的闪避百分比
	 * @return
	 */
	public int equalGradeDodge();
}
