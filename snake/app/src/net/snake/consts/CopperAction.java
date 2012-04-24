package net.snake.consts;

/**
 * 铜币事件
 *@author serv_dev
 */
public enum CopperAction {
//	status  smallint(6)产生原因   1、捡到  2、打怪  3、做任务（包括副本） 4、跟人交易  5、npc出售 6、其他
//	None(0xff), Up(2), Down(4), Left(8), Right(16);
	/**
	 * 拾取增加
	 */
	ADD_PICKUP(1),
	/**
	 * 打怪增加
	 */
	ADD_DAGUAI(2),
	/**
	 * 任务或副本增加
	 */
	ADD_TASKORINSTANCE(3),
	/**
	 * 交易增加
	 */
	ADD_DEAL(4),
	/**
	 * NPC出售增加
	 */
	ADD_NPCSHELL(5),
	/**
	 * 其它增加
	 */
	ADD_OTHER(6),
	/**
	 * 领取护法收益
	 */
	ADD_GUARD(7),
	/**
	 * 减少
	 */
	CONSUME(0),
	/**
	 * 存钱
	 */
	DEPOSIT(-1),
	
	/**
	 * 取钱
	 */
	TAKEFORSTORAGE(-2);
	

	
	private final int value;
	/**
	 * Constructor.
	 */
	private CopperAction(int value) {
		this.value = value;
	}
	/**
	 * Get the value.
	 * 
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

}
