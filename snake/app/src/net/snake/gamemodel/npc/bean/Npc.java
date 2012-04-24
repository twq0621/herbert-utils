package net.snake.gamemodel.npc.bean;

import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.ibatis.IbatisEntity;

public class Npc implements IbatisEntity {

	/**
	 * 主键 t_npc.f_id
	 */
	private Integer id;
	/**
	 * x坐标 t_npc.f_x
	 */
	private Integer x;
	/**
	 * y坐标 t_npc.f_y
	 */
	private Integer y;
	/**
	 * 场景id t_npc.f_sceneid
	 */
	private Integer sceneid;
	/**
	 * npc名字 如张三 t_npc.f_name
	 */
	private String name;
	/**
	 * NPC称号 如 屠夫 t_npc.f_nickname
	 */
	private String nickname;
	/**
	 * 没有交互的简单对话 t_npc.f_simpleTalk
	 */
	private String simpletalk;
	/**
	 * npc 图片资源模板 t_npc.f_npcmodel
	 */
	private String npcmodel;
	/**
	 * npc的头像图标路径 t_npc.f_npc_icon
	 */
	private Integer npcIcon;
	/**
	 * npc大图标 t_npc.f_npc_big_icon
	 */
	private Integer npcBigIcon;
	/**
	 * npc 类型 1功能 2 任务 3商业 t_npc.f_npctype
	 */
	private Short npctype;
	/**
	 * npc功能列表,逗号分开,按功能排序,1任务功能-2NPC商店-3帮会功能-4关系功能（夫妻、师徒）-5合成功能（宝石、装备、药品）-6装备强化功能（镶嵌、打孔、拆分）-7传送功能-8仓库功能-9拍卖功能,- t_npc.f_functions
	 */
	private String functions;
	/**
	 * 任务参数 t_npc.f_function_task
	 */
	private String functionTask;
	/**
	 * 商店所卖物品列表,用逗号隔开 t_npc.f_function_shop
	 */
	private String functionShop;
	/**
	 * 传送 t_npc.f_function_transfer
	 */
	private String functionTransfer;
	/**
	 * 商店类型1武器店，2护具店，3饰品店，4药品店，5杂货店，6战场声望店 t_npc.f_shop_type
	 */
	private Byte shopType;
	/**
	 * 特定的寻路x坐标 t_npc.f_findX
	 */
	private Integer findx;
	/**
	 * 特定的寻路x坐标 t_npc.f_findY
	 */
	private Integer findy;
	/**
	 * npc的初始方向(0-7顺时针, 0是6点钟方向) t_npc.f_direction
	 */
	private Integer direction;
	/**
	 * 攻击时声音编号 t_npc.f_attack_audio
	 */
	private Integer attackAudio;
	/**
	 * 被攻击的声音 t_npc.f_hurt_audio
	 */
	private Integer hurtAudio;
	/**
	 * 死亡时的声音编号 t_npc.f_die_audio
	 */
	private Integer dieAudio;
	/**
	 * 周环任务从这个字段开始的等级限制（用于随机选择目标npc，取代endNpc） t_npc.f_min_grade
	 */
	private Integer minGrade;
	/**
	 * 周环任务从这个字段结束的等级限制（用于随机选择目标npc，取代endNpc） t_npc.f_max_grade
	 */
	private Integer maxGrade;
	/**
	 * npc 否是可转向 1可转向 0不可 t_npc.f_can_redirect
	 */
	private Integer canRedirect;
	/**
	 * npc随机说话格式同f_simpleTalk t_npc.f_randomTalk
	 */
	private String randomtalk;
	/**
	 * npc名字 如张三 国际化 t_npc.f_name_i18n
	 */
	private String nameI18n;
	/**
	 * NPC称号 如 屠夫 国际化 t_npc.f_nickname_i18n
	 */
	private String nicknameI18n;
	/**
	 * 没有交互的简单对话 国际化 t_npc.f_simpleTalk_i18n
	 */
	private String simpletalkI18n;

	/**
	 * 主键 t_npc.f_id
	 * 
	 * @return the value of t_npc.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键 t_npc.f_id
	 * 
	 * @param id
	 *            the value for t_npc.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * x坐标 t_npc.f_x
	 * 
	 * @return the value of t_npc.f_x
	 */
	public Integer getX() {
		return x;
	}

	/**
	 * x坐标 t_npc.f_x
	 * 
	 * @param x
	 *            the value for t_npc.f_x
	 */
	public void setX(Integer x) {
		this.x = x;
	}

	/**
	 * y坐标 t_npc.f_y
	 * 
	 * @return the value of t_npc.f_y
	 */
	public Integer getY() {
		return y;
	}

	/**
	 * y坐标 t_npc.f_y
	 * 
	 * @param y
	 *            the value for t_npc.f_y
	 */
	public void setY(Integer y) {
		this.y = y;
	}

	/**
	 * 场景id t_npc.f_sceneid
	 * 
	 * @return the value of t_npc.f_sceneid
	 */
	public Integer getSceneid() {
		return sceneid;
	}

	/**
	 * 场景id t_npc.f_sceneid
	 * 
	 * @param sceneid
	 *            the value for t_npc.f_sceneid
	 */
	public void setSceneid(Integer sceneid) {
		this.sceneid = sceneid;
	}

	/**
	 * npc名字 如张三 t_npc.f_name
	 * 
	 * @return the value of t_npc.f_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * npc名字 如张三 t_npc.f_name
	 * 
	 * @param name
	 *            the value for t_npc.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * NPC称号 如 屠夫 t_npc.f_nickname
	 * 
	 * @return the value of t_npc.f_nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * NPC称号 如 屠夫 t_npc.f_nickname
	 * 
	 * @param nickname
	 *            the value for t_npc.f_nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 没有交互的简单对话 t_npc.f_simpleTalk
	 * 
	 * @return the value of t_npc.f_simpleTalk
	 */
	public String getSimpletalk() {
		return simpletalk;
	}

	/**
	 * 没有交互的简单对话 t_npc.f_simpleTalk
	 * 
	 * @param simpletalk
	 *            the value for t_npc.f_simpleTalk
	 */
	public void setSimpletalk(String simpletalk) {
		this.simpletalk = simpletalk;
	}

	/**
	 * npc 图片资源模板 t_npc.f_npcmodel
	 * 
	 * @return the value of t_npc.f_npcmodel
	 */
	public String getNpcmodel() {
		return npcmodel;
	}

	/**
	 * npc 图片资源模板 t_npc.f_npcmodel
	 * 
	 * @param npcmodel
	 *            the value for t_npc.f_npcmodel
	 */
	public void setNpcmodel(String npcmodel) {
		this.npcmodel = npcmodel;
	}

	/**
	 * npc的头像图标路径 t_npc.f_npc_icon
	 * 
	 * @return the value of t_npc.f_npc_icon
	 */
	public Integer getNpcIcon() {
		return npcIcon;
	}

	/**
	 * npc的头像图标路径 t_npc.f_npc_icon
	 * 
	 * @param npcIcon
	 *            the value for t_npc.f_npc_icon
	 */
	public void setNpcIcon(Integer npcIcon) {
		this.npcIcon = npcIcon;
	}

	/**
	 * npc大图标 t_npc.f_npc_big_icon
	 * 
	 * @return the value of t_npc.f_npc_big_icon
	 */
	public Integer getNpcBigIcon() {
		return npcBigIcon;
	}

	/**
	 * npc大图标 t_npc.f_npc_big_icon
	 * 
	 * @param npcBigIcon
	 *            the value for t_npc.f_npc_big_icon
	 */
	public void setNpcBigIcon(Integer npcBigIcon) {
		this.npcBigIcon = npcBigIcon;
	}

	/**
	 * npc 类型 1功能 2 任务 3商业 t_npc.f_npctype
	 * 
	 * @return the value of t_npc.f_npctype
	 */
	public Short getNpctype() {
		return npctype;
	}

	/**
	 * npc 类型 1功能 2 任务 3商业 t_npc.f_npctype
	 * 
	 * @param npctype
	 *            the value for t_npc.f_npctype
	 */
	public void setNpctype(Short npctype) {
		this.npctype = npctype;
	}

	/**
	 * npc功能列表,逗号分开,按功能排序,1任务功能-2NPC商店-3帮会功能-4关系功能（夫妻、师徒）-5合成功能（宝石、装备、药品）-6装备强化功能（镶嵌、打孔、拆分）-7传送功能-8仓库功能-9拍卖功能,- t_npc.f_functions
	 * 
	 * @return the value of t_npc.f_functions
	 */
	public String getFunctions() {
		return functions;
	}

	/**
	 * npc功能列表,逗号分开,按功能排序,1任务功能-2NPC商店-3帮会功能-4关系功能（夫妻、师徒）-5合成功能（宝石、装备、药品）-6装备强化功能（镶嵌、打孔、拆分）-7传送功能-8仓库功能-9拍卖功能,- t_npc.f_functions
	 * 
	 * @param functions
	 *            the value for t_npc.f_functions
	 */
	public void setFunctions(String functions) {
		this.functions = functions;
	}

	/**
	 * 任务参数 t_npc.f_function_task
	 * 
	 * @return the value of t_npc.f_function_task
	 */
	public String getFunctionTask() {
		return functionTask;
	}

	/**
	 * 任务参数 t_npc.f_function_task
	 * 
	 * @param functionTask
	 *            the value for t_npc.f_function_task
	 */
	public void setFunctionTask(String functionTask) {
		this.functionTask = functionTask;
	}

	/**
	 * 商店所卖物品列表,用逗号隔开 t_npc.f_function_shop
	 * 
	 * @return the value of t_npc.f_function_shop
	 */
	public String getFunctionShop() {
		return functionShop;
	}

	/**
	 * 商店所卖物品列表,用逗号隔开 t_npc.f_function_shop
	 * 
	 * @param functionShop
	 *            the value for t_npc.f_function_shop
	 */
	public void setFunctionShop(String functionShop) {
		this.functionShop = functionShop;
	}

	/**
	 * 传送 t_npc.f_function_transfer
	 * 
	 * @return the value of t_npc.f_function_transfer
	 */
	public String getFunctionTransfer() {
		return functionTransfer;
	}

	/**
	 * 传送 t_npc.f_function_transfer
	 * 
	 * @param functionTransfer
	 *            the value for t_npc.f_function_transfer
	 */
	public void setFunctionTransfer(String functionTransfer) {
		this.functionTransfer = functionTransfer;
	}

	/**
	 * 商店类型1武器店，2护具店，3饰品店，4药品店，5杂货店，6战场声望店 t_npc.f_shop_type
	 * 
	 * @return the value of t_npc.f_shop_type
	 */
	public Byte getShopType() {
		return shopType;
	}

	/**
	 * 商店类型1武器店，2护具店，3饰品店，4药品店，5杂货店，6战场声望店 t_npc.f_shop_type
	 * 
	 * @param shopType
	 *            the value for t_npc.f_shop_type
	 */
	public void setShopType(Byte shopType) {
		this.shopType = shopType;
	}

	/**
	 * 特定的寻路x坐标 t_npc.f_findX
	 * 
	 * @return the value of t_npc.f_findX
	 */
	public Integer getFindx() {
		return findx;
	}

	/**
	 * 特定的寻路x坐标 t_npc.f_findX
	 * 
	 * @param findx
	 *            the value for t_npc.f_findX
	 */
	public void setFindx(Integer findx) {
		this.findx = findx;
	}

	/**
	 * 特定的寻路x坐标 t_npc.f_findY
	 * 
	 * @return the value of t_npc.f_findY
	 */
	public Integer getFindy() {
		return findy;
	}

	/**
	 * 特定的寻路x坐标 t_npc.f_findY
	 * 
	 * @param findy
	 *            the value for t_npc.f_findY
	 */
	public void setFindy(Integer findy) {
		this.findy = findy;
	}

	/**
	 * npc的初始方向(0-7顺时针, 0是6点钟方向) t_npc.f_direction
	 * 
	 * @return the value of t_npc.f_direction
	 */
	public Integer getDirection() {
		return direction;
	}

	/**
	 * npc的初始方向(0-7顺时针, 0是6点钟方向) t_npc.f_direction
	 * 
	 * @param direction
	 *            the value for t_npc.f_direction
	 */
	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	/**
	 * 攻击时声音编号 t_npc.f_attack_audio
	 * 
	 * @return the value of t_npc.f_attack_audio
	 */
	public Integer getAttackAudio() {
		return attackAudio;
	}

	/**
	 * 攻击时声音编号 t_npc.f_attack_audio
	 * 
	 * @param attackAudio
	 *            the value for t_npc.f_attack_audio
	 */
	public void setAttackAudio(Integer attackAudio) {
		this.attackAudio = attackAudio;
	}

	/**
	 * 被攻击的声音 t_npc.f_hurt_audio
	 * 
	 * @return the value of t_npc.f_hurt_audio
	 */
	public Integer getHurtAudio() {
		return hurtAudio;
	}

	/**
	 * 被攻击的声音 t_npc.f_hurt_audio
	 * 
	 * @param hurtAudio
	 *            the value for t_npc.f_hurt_audio
	 */
	public void setHurtAudio(Integer hurtAudio) {
		this.hurtAudio = hurtAudio;
	}

	/**
	 * 死亡时的声音编号 t_npc.f_die_audio
	 * 
	 * @return the value of t_npc.f_die_audio
	 */
	public Integer getDieAudio() {
		return dieAudio;
	}

	/**
	 * 死亡时的声音编号 t_npc.f_die_audio
	 * 
	 * @param dieAudio
	 *            the value for t_npc.f_die_audio
	 */
	public void setDieAudio(Integer dieAudio) {
		this.dieAudio = dieAudio;
	}

	/**
	 * 周环任务从这个字段开始的等级限制（用于随机选择目标npc，取代endNpc） t_npc.f_min_grade
	 * 
	 * @return the value of t_npc.f_min_grade
	 */
	public Integer getMinGrade() {
		return minGrade;
	}

	/**
	 * 周环任务从这个字段开始的等级限制（用于随机选择目标npc，取代endNpc） t_npc.f_min_grade
	 * 
	 * @param minGrade
	 *            the value for t_npc.f_min_grade
	 */
	public void setMinGrade(Integer minGrade) {
		this.minGrade = minGrade;
	}

	/**
	 * 周环任务从这个字段结束的等级限制（用于随机选择目标npc，取代endNpc） t_npc.f_max_grade
	 * 
	 * @return the value of t_npc.f_max_grade
	 */
	public Integer getMaxGrade() {
		return maxGrade;
	}

	/**
	 * 周环任务从这个字段结束的等级限制（用于随机选择目标npc，取代endNpc） t_npc.f_max_grade
	 * 
	 * @param maxGrade
	 *            the value for t_npc.f_max_grade
	 */
	public void setMaxGrade(Integer maxGrade) {
		this.maxGrade = maxGrade;
	}

	/**
	 * npc 否是可转向 1可转向 0不可 t_npc.f_can_redirect
	 * 
	 * @return the value of t_npc.f_can_redirect
	 */
	public Integer getCanRedirect() {
		return canRedirect;
	}

	/**
	 * npc 否是可转向 1可转向 0不可 t_npc.f_can_redirect
	 * 
	 * @param canRedirect
	 *            the value for t_npc.f_can_redirect
	 */
	public void setCanRedirect(Integer canRedirect) {
		this.canRedirect = canRedirect;
	}

	/**
	 * npc随机说话格式同f_simpleTalk t_npc.f_randomTalk
	 * 
	 * @return the value of t_npc.f_randomTalk
	 */
	public String getRandomtalk() {
		return randomtalk;
	}

	/**
	 * npc随机说话格式同f_simpleTalk t_npc.f_randomTalk
	 * 
	 * @param randomtalk
	 *            the value for t_npc.f_randomTalk
	 */
	public void setRandomtalk(String randomtalk) {
		this.randomtalk = randomtalk;
	}

	/**
	 * npc名字 如张三 国际化 t_npc.f_name_i18n
	 * 
	 * @return the value of t_npc.f_name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * npc名字 如张三 国际化 t_npc.f_name_i18n
	 * 
	 * @param nameI18n
	 *            the value for t_npc.f_name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * NPC称号 如 屠夫 国际化 t_npc.f_nickname_i18n
	 * 
	 * @return the value of t_npc.f_nickname_i18n
	 */
	public String getNicknameI18n() {
		return nicknameI18n;
	}

	/**
	 * NPC称号 如 屠夫 国际化 t_npc.f_nickname_i18n
	 * 
	 * @param nicknameI18n
	 *            the value for t_npc.f_nickname_i18n
	 */
	public void setNicknameI18n(String nicknameI18n) {
		this.nicknameI18n = nicknameI18n;
	}

	/**
	 * 没有交互的简单对话 国际化 t_npc.f_simpleTalk_i18n
	 * 
	 * @return the value of t_npc.f_simpleTalk_i18n
	 */
	public String getSimpletalkI18n() {
		return simpletalkI18n;
	}

	/**
	 * 没有交互的简单对话 国际化 t_npc.f_simpleTalk_i18n
	 * 
	 * @param simpletalkI18n
	 *            the value for t_npc.f_simpleTalk_i18n
	 */
	public void setSimpletalkI18n(String simpletalkI18n) {
		this.simpletalkI18n = simpletalkI18n;
	}

	private String[] functionarray;

	public boolean iscontainsFunction(String function) {
		if (functionarray == null) {
			try {
				functionarray = functions.split(",");
			} catch (Exception e) {
			}
		}
		if (functionarray == null) {
			return false;
		}
		for (String func : functionarray) {
			try {
				if (function.equals(func)) {
					return true;
				}
			} catch (NumberFormatException e) {
			}
		}
		return false;

	}

	// 1任务功能-2NPC商店-3帮会功能-4关系功能（夫妻、师徒）-5合成功能（宝石、装备、药品）-6装备强化功能（镶嵌、打孔、拆分）
	// -7传送功能-8仓库功能-9拍卖功能
	public final static String NPC_TASK = "1";
	public final static String NPC_SHOP = "2";
	public final static String NPC_FACTION = "3";
	public final static String NPC_RELATE = "4";
	public final static String NPC_HECHENG = "5";
	public final static String NPC_QIANGHUA = "6";
	public final static String NPC_TELEPORT = "7";
	public final static String NPC_STOCKROOM = "8";
	public final static String NPC_PAIMAI = "9";
	public final static String NPC_XIDIAN = "15";
	public final static String NPC_EXCHANGEMAP = "16";
	public final static String NPC_EXCHANGEFACTIONCT = "22";

	
	public final static String NPC_PET = "10";			//宠物
	public final static String NPC_PLOY = "11";		//活动
	public final static String NPC_HELP = "12";		//询问
	
	public final static String NPC_REPAIR = "13";		//修理
	public final static String NPC_AWARD_EXP = "14";	//领取奖励经验
	public final static String NPC_INSTANCE = "16";	//副本
	
	public final static String NPC_COLLECT = "17";		//古玩收集
	
	public final static String NPC_SQUIRREL = "18";	//松鼠抽奖
	
	public final static String NPC_STONE = "19"; 		//宝石合成
	
	public final static String NPC_AMULT = "20";		//护身符
	
	public final static String NPC_SHEN_LONG_AMULT = "21";//神龙护身符
	
	
	public final static String NPC_ZHAN_WEI_KE_HUA = "23";//战纹刻画
	
	public final static String NPC_LINGQU_MIZONG = "24";//领取迷踪阵通关经验奖励
		
	public final static String NPC_JIHUO_ANQI_YINCANG = "25";//激活暗器隐藏属性
		
	public final static String NPC_TO_LUNJIANTAI = "27";//传入：论剑台
	
	public final static String NPC_GIFT_CARD = "28";//卡号兑换礼品
		
	public final static String NPC_TO_KUAFU_LUNJIANTAI = "29";//传入跨服战场
	
	public final static String NPC_LUNJIAN_MALL = "30";//论剑声望商店购物
	
	public final static String NPC_CHEGNZHAN_MALL = "31";//城战声望商店购物
	
	public final static String NPC_LEAVE_FUBEN = "32";//离开副本
	
	public final static String NPC_DUJIE = "33";//渡劫
	
	//以下为临时的功能
	public final static String NPC_TUNVLANG_DUIHUAN = "1001"; 		//兔女郎兑换

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ===========================================================
	/**
	 * 验证npc 和玩家的距离
	 * 
	 * @param npc
	 * @param character
	 * @return
	 */
	public static boolean validateNpc(Npc npc, SceneObj character) {
		int npcsceneid = npc.getSceneid();
		int charactersceneid = character.getScene();
		if (npcsceneid == charactersceneid) {
			int npcx = npc.getX();
			int npcy = npc.getY();
			int cx = character.getX();
			int cy = character.getY();
			int jvlix = Math.abs(npcx - cx);
			int jvliy = Math.abs(npcy - cy);
			if (jvlix > 10 || jvliy > 10) {
				return false;
			}

		} else {
			return false;
		}
		return true;
	}
}
