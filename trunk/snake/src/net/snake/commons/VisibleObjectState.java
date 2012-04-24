package net.snake.commons;

/**
 * 状态，不分角色和怪物。且状态是离散的。
 * @author serv_dev
 * Copyright (c) 2011 Kingnet
 */
public class VisibleObjectState {
	
	/**等待濒死的服软**/
	public static final int ShockWaiting=1;
	
	/**消失 */
	public static final int Dispose=2;
	
	/**死亡 */
	public static final int Die=3;
	
	/**复位 */
	public static final int Reset=4;
	
	/**准备复位 */
	public static final int IsReset=5;
	
	/**击退 */
	public static final int Jitui=6;

	/**逃跑 */
	public static final int Escape=7;
	
	/**追击  */
	public static final int Pursuit=8;
	
	/**准备追击 */
	public static final int Ispursuit=9;

	/**准备逃跑 */
	public static final int Isescape=10;

	/**攻击 */
	public static final int Attack=11;

	/**巡逻 */
	public static final int Patrol=12;

	/**休闲 */
	public static final int Idle=13;

	/**濒死*/
	public static final int Shock=14;
	
	/**行走 */
	public static final int Walk=15;
	
	/**打坐 */
	public static final int Dazuo=16;
	
	/**被攻击 */
	public static final int BeAttacked=20;
	
	public static final int SHOW_ATTACK=17;
	public static final int SHOW_FLLOW=18;
	public static final int SHOW_PURSUIT=19;
}
