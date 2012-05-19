package lion.shell;

/**
 * 服务器一些选项，多数是开关 变量
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
public final class Options {
	/** 是否允许开始GM 命令 */
	public static volatile boolean EnableGMCmd = true;
	/** TODO 是否允许统计processor性能 */
	public static volatile boolean ProcessorPerformance = false;
	
	/** 是否是跨服的第三方服务器 */
	public static volatile boolean IsCrossServ=false;
	/**服务器所属的分区*/
	public static int ServerId=0;
	/**新手卡检查程序是否开启*/
	public static boolean FresherCard_Check=false;
	/**是否启用防沉迷系统*/
	public static boolean AntiAddicted=true;
	/**新手村地图编号*/
	public static int Fresher_Familytown=30013;
	/**shock time second*/
	public static int Shock_Timeout=10;//人物濒死倒计时
	public static int Shock_Timeout_Monster=8;//怪物濒死倒计时
	public static int Shock_AttackBase=100;//基数
	public static int Shock_AttackProb=2;//杀
	/**角色复活延时秒数*/
	public static int Relive_Timeout=5;
	/**心跳消息号*/
	public static int Msg_Heart=40301;
	public static int MAX_SP=10000;//怒气上限
	public static int JUMP_DISTANCE = 22;
	/**连斩等级限制**/
	public static int KillGradeLimit=10;
	/**六个中文字符*/
	public static final String ChineseChars_6="^[\\u4E00-\\u9FA5\\uF900-\\uFA2D\\w]{1,6}$";
	/**Coefficient of Guard's fee*/
	public static int Coefficient=30;
}
