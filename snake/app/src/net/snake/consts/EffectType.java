package net.snake.consts;

public interface EffectType {
	/**
	 * 1,增加攻击力
	 * 2,增加防御力
	 * 3,增加暴击
	 * 4,增加闪避
	 * 5,增加攻击速度
	 * 6,增加移动速度
	 * 7,增加生命上限值
	 * 8,增加内力上限值
	 * 9,增加体力上限值
	 * 10,增加潜能点个数
	 * 11,增加等级
	 * 12,增加经验
	 * 13,增加坐骑经验
	 * 14,增加真气储量
	 * 15,增加战场声望
	 * 16,恢复生命值
	 * 17,恢复内力值
	 * 18,恢复体力值
	 * 19,恢复坐骑活力
	 * 20,解除全部负面状态
	 * 21,双倍经验获得
	 * 22,双倍真气储量获得
	 * 23,吸星
	 * 24,封内力
	 * 25,封体力
	 * 26,点穴
	 * 27,中毒
	 * 28,打落武器
	 * 29,打落防具
	 * 30,击退
	 * 31,马奶
	 * 32,连斩
	 * 33,抗击退
	 * 34,抗定身
	 * 35,抗中毒
	 * 36,抵抗化功(内力)
	 * 37,抵抗绵骨 (体力)
	 * 38,抵抗吸星
	 * 39,抵抗打落武器
	 * 40,抵抗打落防具
	 * 41,双倍坐骑的经验获得
	 * 42,包裹铜币
	 * 43元宝
	 * 44礼金
	 * 45仓库铜币
	 * 46和平保护的buff
	 * 47自动续命丹
	 * 48自动聚法丹
	 * 49自动回体丹
	 * 50vip月卡
	 * 51活力神丹
	 * 52连斩延时丹
	 * 53狂攻药剂
	 * 54健体药剂
	 * 55防御药剂
	 * 56轻身药剂
	 * 57迟缓
	 * 58麻木
	 * 59洗点药剂
	 * 60砍旗非保护
	 * 61护旗非保护
	 * 62五倍经验丹
	 * 63十倍经验丹
     * 64增加肉身
     * 
	 * 80错骨分筋(降低单体目标攻击速度)
	 * 81狮吼功(降低范围内目标移动速度)
	 * 82千幻掌(降低范围内目标闪避值)
	 * 83七煞掌(降低单体目标爆击值)
	 * 84巨灵心诀(抵抗错骨分筋)
	 * 85如封似闭(抵抗狮吼功)
	 * 86听声辩位(抵抗千幻掌)
	 * 87战心诀(抵抗七煞掌)
	 * 88无敌buff
	 * 
	 * 89风雨同济
	 * 90守护
	 * 91红颜
	 * 92比翼双飞
	 * 93显示tip描述
	 * 
	 * 96防御失效
	 * 97方向控制失灵
	 * 98沉睡
	 * 99烈火
	 * 
	 * 100青龙战纹攻击力整体翻倍，
	 * 101白虎战纹忽视对手全部防御，
	 * 102反弹全部伤害，伤害减免
	 * 103玄武战纹免疫全部伤害
	 * 
	 * 104 箭支用罄
	 * 105肉身境界提升成功率提升
	 * 
	 * 106破血狂杀
	 * 107凝血离魂
	 * 108弹指神通
	 * 109失明
	 * 110经脉失效
	 * 111龙气丹
	 * 112免费提升突破肉身脱胎换骨
	 * 113 养天秘籍
	 * 
	 * 114减弱攻击力和防御力 
	 * 115增加攻击力和防御力 
	 * 116定时对是自己范围内的敌人进行攻击
	 * 117 绝技-浮屠 定时对是自己范围内的敌人进行攻击  进入无敌状态  不能移动
	 * 118 提升人物全属性 ,主要提升的属性是：攻击、防御、生命上限、法力上限，暴击、闪避
	 * 
	 *一些几个是对影响技能的被动技能的效果类型而定的：
	 * 119 提高对敌人移动速度的减弱
	 * 120 增加伤害
	 *  121 提升命中率
	 *  122 增加移动距离
	 *  
	 *  124加速内丹生产时间，生产一个内丹只能用一次
	 *  125免费复活一次
	 *  
	 *  126杀戮值
	 *  
	 * 1001战意激扬
	 * 1002武神临体
	 */
	/***/
	
	public static final int speedUpNeiDan = 124;
	
	public static final int freeFuHuo = 125;
	
	public static final int nothing = 0;
	/**增加攻击力*/
	public static final int attack = 1;
	/**增加防御力*/
	public static final int defence = 2;
	/**增加暴击*/
	public static final int crt = 3;
	/**增加闪避*/
	public static final int dodge = 4;
	/**增加攻击速度*/
	public static final int attackspeed = 5;
	/**增加移动速度*/
	public static final int movespeed = 6;
	/**增加生命上限值*/
	public static final int maxHp = 7;
	/**增加内力上限值*/
	public static final int maxMp = 8;
	/**增加体力上限值*/
	public static final int maxSp = 9;
	/**增加潜能点个数*/
	public static final int potential=10;
	/**增加经验*/
	public static final int exp = 12;
	/**增加真气储量*/
	public static final int zhenqi = 14;
	/**增加战场声望*/
	public static final int prestige=15;
	/**恢复生命值*/
	public static final int hp = 16;
	/**恢复内力值*/
	public static final int mp = 17;
	/**恢复体力值*/
	public static final int sp = 18;
	/**双倍经验获得*/
	public static final int doubleexp = 21;
	/**双倍真气储量获得*/
	public static final int doublezhenqi = 22;
	/**吸星*/
	public static final int xixue = 23;
	/**封内力*/
	public static final int fengMp = 24;
	/**封体力*/
	public static final int fengSp = 25;
	/**点穴*/
	public static final int dianxue = 26;
	/**中毒*/
	public static final int du = 27;
	/**打落武器*/
	public static final int fengwuqi = 28;
	/**打落防具*/
	public static final int fengfanju = 29;
	/**击退*/
	public static final int jitui = 30;
	public static final int manai = 31;
	public static final int lianzhan = 32;
	public static final int unjitui = 33;
	public static final int undianxue = 34;
	public static final int undu = 35;
	public static final int unfengMp = 36;
	public static final int unfengSp = 37;
	public static final int unxixue = 38;
	public static final int unfengwuqi = 39;
	public static final int unfengfanju = 40;
	public static final int doublezuoqiexp = 41;
	public static final int copper = 42;
	public static final int ingot = 43;
	public static final int lijin = 44;
	public static final int storageCopper = 45;
	public static final short pkprotect = 46;
	public static final short xumingDan = 47;
	public static final short jufaDan = 48;
	public static final short huitiDan = 49;
	public static final short horsehuoliDan = 51;
	public static final short lianzhanyanshiDan = 52;
	public static final int contribution=70;
	public static final short kgYJ = 53;
	public static final short jtYJ = 54;
	public static final short fyYJ = 55;
	public static final short qsYJ = 56;
	public static final short chihuan = 57;
	public static final short mamu = 58;
	public static final short zuoqizhufu = 59;
	public static final int double5exp = 62;
	public static final int double10exp = 63;
	
	public static final int lunjianshengwang = 71;
	public static final int mxZhenyuan = 73;
	
	public static final int reduceAttackSpeed = 80;
	public static final int reduceMoveSpeed = 81;
	public static final int reduceDodge = 82;
	public static final int reduceCrt = 83;
	public static final int deReduceAttackSpeed = 84;
	public static final int deReduceMoveSpeed = 85;
	public static final int deReduceDodge = 86;
	public static final int deReduveCrt = 87;
	public static final int wudi = 88;
	
	public static final int fengYuTongJi = 89;
	public static final int biyishuangfei = 92;
	public static final int guard = 90;
	public static final int hongyan = 91;
	public static final int longlindan = 93;
	
	public static final int nodefence = 96;
	public static final int nodirection = 97;
	public static final int sleep = 98;
	public static final int liehuo = 99;
	
	public static final int gjl = 100;
	public static final int hsfy = 101;
	public static final int ftsh = 102;
	public static final int shjm = 103;
	public static final int jianzhi_tip = 104;
	public static final int rt_up_time_tip = 105;
	public static final int po_xue_kuang_sha = 106;
	public static final int ning_xue_li_hun = 107;
	public static final int tan_zhi_shen_tong = 108;
	public static final int shi_ming = 109;
	public static final int jingmai_invalid = 110;
	public static final int long_qi_dan = 111;
	public static final int free_roushentuotaihuangu = 112;
	public static final int yangtianmiji = 113;
	public static final int unattackdefence = 114;
	public static final int attackdefence = 115;
	public static final int unhpbuff = 116;
	public static final int jueji= 117;
	public static final int appendAllPe= 118;
	public static final int unmovespeed = 119; 
	public static final int hurt = 120; 
	public static final int hit = 121; 
	public static final int distance = 122; 
	public static final int killNum = 126; 
	
	public static final int zyjy = 1001;
	public static final int wslt = 1002;
	
	
}
