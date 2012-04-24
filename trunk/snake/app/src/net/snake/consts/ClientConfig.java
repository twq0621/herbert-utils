package net.snake.consts;

import java.util.ArrayList;
import java.util.List;

public class ClientConfig {
	// 调试相关　
	public static boolean DISABLE_HORSE_HUOLI = true;
	/** 是否启用心跳监测---------- 不允许修改此项 */
	public final static boolean HEART_BEAT_CHECK = true;//
	/** 仙境 */
	public final static int Scene_Xianjing = 30013;//
	// 30013、30002、30005、30006、30008 不能挂机的地图id
	public final static List<Integer> notAfkMapId = new ArrayList<Integer>();
	static {
		notAfkMapId.add(30013);
		notAfkMapId.add(30002);
		notAfkMapId.add(30005);
		notAfkMapId.add(30006);
		notAfkMapId.add(30008);

	}
	public final static int default_fuhuo_mapid = 0;
	public final static short default_fuhuo_x = 125;
	public final static short default_fuhuo_y = 78;

	/** 回城限定等级 */
	public final static int huichengLimitGrade = 10;//
	/** 从的卢马到牛的成就id */
	public final static int HorseDiLuToNiuAchieveId = 4131;//
	// 容错处理
	/** 是否允许变速齿轮 */
	public final static boolean PERMIT_CHANGE_FRAME_RATE = false;//
	/** 允许位置误差的大小(以逻辑格计) */
	public final static int PERMIT_LOCATION_ERROR_RANGE = 15;//
	/** 心跳时间检测间隔 ***/
	public final static int HeatBeatInterval1 = 60000;//
	/** 心跳时间检测间隔 */
	public final static int HeatBeatInterval2 = 120000;//
	/** 战斗的持续时间为30秒 */
	public final static int FightingTime = 60000;//
	/** 帧/秒 （帧就是每秒运行多少次的意思） */
	public final static int FramePerSecond = 10; //
	/** 角色默认速度 像素/每秒 */
	public final static int CharacterDefaultSpeed = 160;//
	/** 逻辑格大小 */
	public final static int TileSIZE = 25;//
	/** 客户端显示大小宽 以逻辑格数计算 */
	public final static int ScreenTileWidth = 60;// 40;//
	/** 客户端显示大小高 以逻辑格数计算 */
	public final static int ScreenTileHeight = 30;// 24;//
	/** 与npc 交易距离 */
	public final static int NPC_TRADE_LANG = 11; //
	/** 与npc 交易距离 */
	public final static int VEHICLE_SEBDER_LANG = 8; //
	/** 与玩家交易距离 */
	public final static int ROLE_TRADE_LANG = 11; //
	/** 随机点间的最小距离 */
	public final static int FEAST_MIN_LANG = 5; //
	/** 食用婚宴时的最大距离 */
	public final static int FEAST_EAT_MAX_LANG = 200;//
	/** 坐骑攻击怪物的距离 */
	//
	public final static int DISTANCE_TO_ATTACKER = 2;
	/** 展示性坐骑和主人之间的距离 */
	//
	public final static int DISTANCE_TO_OWNER = 7;
	public final static int MAXDISTANCE_TO_OWNER = 90;

}
