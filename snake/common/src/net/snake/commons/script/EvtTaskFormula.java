package net.snake.commons.script;


/**
 * 任务公式
 * @author serv_dev
 *
 */
public interface EvtTaskFormula {
	
	/**
	 * 当玩家完成第10次后，	额外附加赠送经验：玩家等级*400
	 */
	public int taskExp10(SRole role) ;
	/**
	 * 当玩家完成第20次后，	额外附加奖励经验：玩家等级*1000
		额外附加奖励真气储量：玩家等级*100
	 */
	public int taskExp20(SRole role);

	/**
	 * 额外附加奖励真气储量：玩家等级*100
	 */
	public int taskZhenqi20(SRole role);
	
	/**
	 * 当玩家完成第100次后，   额外奖励经验：玩家等级*3000
	 */
	public int taskWeekExp100(SRole role);

	/**
	 * 当玩家完成第200次后，   额外奖励经验：玩家等级*8000
	 */
	public int taskWeekExp200(SRole role);

	/**
	 * 额外附加奖励真气储量：玩家等级*100
	 */
	public int taskWeekZhenqi200(SRole role);
	
}
