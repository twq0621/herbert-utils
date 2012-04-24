package net.snake.consts;
/**
 * 上限定义
 * @author serv_dev
 */
public interface MaxLimit {

	public final static int BagCapacity = 30;// 包裹容量
	public final static int StorageCapacity = 30;// 仓库容量
	
	public final static int DODGE_MAX = 9999; // 闪避
	public final static int MOVESPEED_MAX = 9999; // 移动速度
	/**
	 * 包裹里最大支持的铜钱数
	 */
	public final static int BAG_COPPER_MAX = 900000000;
	public final static int Contribution_Max=90000000;
	public final static int STORAGE_COPPER_MAX = 900000000;
	public final static int INGOT_MAX = 900000000;
	public final static int LIJIN_MAX = 900000000;
	public final static int ZHENQI_MAX = 10000;
	public final static int ATTACT_MAX = Integer.MAX_VALUE;
	public final static int DEFENCE_MAX = Integer.MAX_VALUE; //
	public final static int CRT_MAX = Integer.MAX_VALUE; // 暴击
	public final static int HIT_MAX = Integer.MAX_VALUE; // 暴击
	public static final int POTENTIAL_MAX = Integer.MAX_VALUE;
	public static final int PRESTIGE_MAX = Integer.MAX_VALUE;
	public static final int GongChengShengWang_MAX = Integer.MAX_VALUE;
	public static final int LunJianShengWang_MAX = Integer.MAX_VALUE;
	public static final int GainTodayMaxShengWang = 300;
	public static int LongQiDan_EveryDay_Num = 1;//龙气丹每天只能产生一颗
	public static long LiaoShang_Exp = 30000000;//疗伤经验上限1亿
	public static final int GAILV_MAX = 10000;//概率上限1w
}
