package net.snake.consts;
/**
 * 技能状态
 * @author serv_dev
 */
public enum SkillStatus {
	/**论剑群攻无法使用*/
	lunjianqungongunused(8),//
	commonCooling(7),//公共冷却时间按
	cooling(2),//冷却时间
	no_enough_hp(5),//血不足
	no_enough_mp(3),//蓝不足
	no_enough_sp(6),//体力不足
	invalid(1),//无效的
	ok(0);
	
	public int getStatus(){
		return status;
	}
	private int status;
	private SkillStatus(int status) {
		this.status = status;
	}
}
