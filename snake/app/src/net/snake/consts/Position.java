package net.snake.consts;
/**
 * 定义位置
 * @author serv_dev
 */
public interface Position {
	//一页包裹可以容纳的物品数
	public final static int BAG_PAGE_CAPACITY=30;

	// 包裹位置
	public final static short BagGoodsBeginPostion = 100;
	public final static short BagGoodsEndPostion = 249;
	
	public final static short AcrossBagBeginPostion=400;
	public final static short AcrossBagEndPostion=429;
	
	public final static short AcrossJianZhiBeginPostion=500;
	public final static short AcrossJianZhiEndPostion=501;
	
	
	// 仓库位置
	public final static short StorageGoodsBeginPostion = 1000;
	public final static short StorageGoodsEndPostion = 1999;
	// 摊位位置
	public final static short StallGoodsBeginPostion = 2000;
	public final static short StallGoodsEndPostion = 2009;
	// ===============================================================
	public final static short POSTION_WUQI = 1;// 武器-》
	public final static short POSTION_QIBING = 2;// 骑站武器-》无
	public final static short POSTION_YIFU = 3;// 衣服-》胸甲
	public final static short POSTION_HUSHOU = 4;// 护手-》护腕
	public final static short POSTION_HUYAO = 5;// 护腰-》腰带
	public final static short POSTION_HUXUE = 6;// 护腰-》鞋
	public final static short POSTION_TOUSHI = 7;// 头饰-》头盔
	public final static short POSTION_XIANGLIAN = 8;// 项链-》腿甲
	public final static short POSTION_JIEZHI = 9;// 戒指-》戒指
	public final static short POSTION_SHOUZHUO = 10;// 手镯-》手镯
	public final static short POSTION_YAOZHUI = 11;// 腰坠-》饰品
	public final static short POSTION_PIFENG = 12;// 披风
	public final static short POSTION_TESHU = 13;// 特殊-》法宝

	public final static short POSTION_HOURSE_ANJU = 21;// 鞍具
	public final static short POSTION_HOURSE_JIANGSHENG = 22;// 鞍具
	public final static short POSTION_HOURSE_TIJU = 23;// 蹬具
	public final static short POSTION_HOURSE_TITIE = 24;// 蹄铁
	public final static int zuheSkillBeginIndex = 1;//组合技能开始下标
	public final static int zuheSkillEndIndex = 4;//组合技能结束下标
	
	//附身符中的舍利子开始与结束位置
	public final static short HUSHENFU_SHENLIZI_BEGIN = 4600;
	public final static short HUSHENFU_SHENLIZI_END = 4650;
	public final static short HUSHENFU_NEXT_TIPS = 4610;//护身符下一个tips
	public final static short SHENLIZI_NEXT_TIPS = 4611;//舍利子下一个tips
	
	// 技能位置(快捷栏)
	public final static short QuickBarBeginPostion = 1;
	public final static short QuickBarEndPostion = 10;
	// =============================================================================
	// 身上的位置
	public final static short BodyGoodsBeginPostion = 1;
	public final static short BodyGoodsEndPostion = 13;
	// 坐骑上的位置
	public final static short HorseGoodsBeginPostion = 21;
	public final static short HorseGoodsEndPostion = 24;

	// 一个装备上镶嵌其他物品的位置
	public final static short InlayGoodsBeginPostion = 30;
	public final static short InlayGoodsEndPostion = 39;
	//装备合成查看下一等级
	public final static short CombineNextEquipment = 800;
	//宝箱奖品开始与结束位置
	public final static short BaoXiangJiangPinBegin = 4000;
	public final static short BaoXiangJiangPinEnd = 4500;
}
