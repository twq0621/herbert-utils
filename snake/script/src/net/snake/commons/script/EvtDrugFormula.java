package net.snake.commons.script;

/**
 * 药品公式
 * @author serv_dev
 *
 */
public interface EvtDrugFormula {

	/**
	 * 狂攻带来的攻击力百分比
	 * @return
	 */
	public int kuanGongPrecent();
	
	/**
	 * 健体带来的血量百分比
	 * @return
	 */
	public int jianTiHpPrecent();
	
	/**
	 * 健体带来的法力百分比
	 * @return
	 */
	public int jianTiMpPrecent();
	
	/**
	 * 健体带来的体力百分比
	 * @return
	 */
	public int jianTiSp();
	
	/**
	 * 防御带来的防御力百分比
	 * @return
	 */
	public int fangyuPrecent();
	
	/**
	 * 轻身带来的暴击百分比
	 * @return
	 */
	public int qingshenCrtPrecent();
	
	/**
	 * 轻身带来的闪避百分比
	 * @return
	 */
	public int qingshenDodgePrecent();
	
}
