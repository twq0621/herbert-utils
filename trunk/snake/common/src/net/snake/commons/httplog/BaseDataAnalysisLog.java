/**
 * @title BaseDataAnalysisLog.java
 * @package net.snake.log.bean
 * @description 
 * @author dev
 * @version 1.0 
 * @createTime 2011-4-26 上午11:48:07 
 */
package net.snake.commons.httplog;

import org.apache.log4j.Logger;

import net.snake.commons.Timer;

/**
 * 内部日志基类，所有内部日志继承此类
 * <p>
 * 子类继承后，应在初始化时首先调用父类的initGainCharacter方法，初始化公共内容
 * 获得内容前先调用fillCommonInfo方法，拼接父类信息，并写入info字串中获得
 * </p>
 * 
 * @author serv_dev
 */
public abstract class BaseDataAnalysisLog implements Cloneable {
	private static final Logger logger = Logger.getLogger(BaseDataAnalysisLog.class);
	public static final String SPLIT_CODE = "\t";
	/** 平台ID */
	private Integer dept;//
	/** 服务器物理地址id（合服后区分各服务器机器id） */
	private Integer serverAddressId;//
	/** 内部游戏ID（公司内部游戏定义） */
	private Integer interiorGameId;//
	/** 服务器ID **/
	private String sid;//
	/** 操作时间 */
	private String outTime;//
	/** 获得方或操作方运营ID */
	private String gainYunYingId;//
	/** 获得方或操作方角色ID */
	private String gainCharacterId;//
	/** 获得方或操作方角色名称 */
	private String gainCharacterName;//

	public String getGainCharacterName() {
		return gainCharacterName;
	}

	public void setGainCharacterName(String gainCharacterName) {
		this.gainCharacterName = gainCharacterName;
	}

	/** 获得方或操作方角色等级 */
	private Integer gainCharacterGrade;//
	/** 获得方或操作方登陆IP */
	private String gainCharacterLoginIp;//
	/** 日志类型，每个输出类创建将做改变 */
	private LogType logType = LogType.nothing_type;//
	/** 输出时信息串 */
	private String info;//

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * 初始化获得方或操作方的角色信息
	 * 
	 * @param gainCharacter
	 *            获得或操作方的角色对象
	 * @param logType
	 *            日志类型@see LogType
	 * 
	 */
	protected void initGainCharacter(UserInfo user, LogType logType) {
		this.logType = logType;
		this.outTime = Timer.getNowTime("yyyy-MM-dd HH:mm:ss");
//		this.dept = HttpInteriorLogService.getInstance().getDept();
//		this.interiorGameId = HttpInteriorLogService.getInstance().getGid();
//		this.serverAddressId = HttpInteriorLogService.getInstance().getSid();
		if (user != null) {
			this.sid = user.getSid();
			this.gainYunYingId = user.getUserId();
			this.gainCharacterId = user.getCharacterId();
			this.gainCharacterGrade = user.getCharacterGrade();
			this.gainCharacterLoginIp = user.getUserIp();
			this.gainCharacterName = user.getCharacterName();
		}
	}

	/**
	 * 日志输出方法，各子类实现
	 * 
	 */
	public abstract void outLog();

	/**
	 * 获得日志文本内容
	 * 
	 * @return
	 */
	public abstract String getLogInfo();

	public LogType getLogType() {
		return logType;
	}

	public void setLogType(LogType logType) {
		this.logType = logType;
	}

	public enum LogType {
		/** 无类型，初始化类型 */
		nothing_type(-1), //
		/** 用户充值 */
		rechage_type(0), //
		/** 玩家与玩家交易 */
		p2p_type(1), //
		/** 玩家与NPC交易 */
		p2c_type(2), //
		/** 挂售交易 */
		agora_type(3), //
		/** 物品销毁 */
		destroy_type(4), //
		/** 松果 */
		pinecone_type(5), //
		/** 聊天 */
		chat_type(6), //
		/** 消费 */
		consumption_type(7), //
		/** 留存率 */
		liucunlv_type(8), //
		/** 通过率 */
		tongguolv_type(9), //
		/** 坐骑祝福 */
		horse_type(10), //
		/** 在线人数 */
		online_type(11), //
		/** 用户登录 */
		login_type(12), //
		/** 用户登出 */
		logout_type(13), //
		/** 心跳日志 */
		heart_type(14), //
		/** 登陆页加载 */
		page_type(15), //
		/** 进入副本 */
		instance_in_type(16), //
		/** 退出副本 */
		instance_out_type(17), //
		/** 环任务 */
		circulation_task_type(18), //
		/** boss */
		boss_type(19), //
		/** 婚宴 */
		feast_type(20), //
		/** 闭关 */
		biguan_type(21), //
		/** 签到 */
		qiandao_type(22), //
		/** 连续登陆奖励 */
		login_reward_type(23), //
		/** 每周在线奖励 */
		week_online(24), //
		/** 经脉 */
		channel_type(25), //
		/** 暗器 */
		hidden_weapons_type(26), //
		/** 提速石 */
		tisushi_type(27), //
		/** 炼体 */
		lianti_type(28), //
		/** 弓箭 */
		bow_type(29), //
		/** 丹田 */
		dantian_type(30), //
		/** 独孤 */
		dugu_type(31), //
		/** 武学 */
		skill_type(32), //
		/** 护身符 */
		hushenfu_type(33), //
		/** 装备炼制及舍利重置消耗材料 */
		equipment1_type(34), //
		/** 装备合成及战纹刻画消耗材料 */
		equipment2_type(35), //
		/** 宝石合成 */
		baoshi_type(36), //
		/** 坐骑炼骨进阶 */
		horse1_type(37), //
		/** 坐骑驯养 */
		horse2_type(38);//

		private int type;

		public int getType() {
			return this.type;
		}

		LogType(int type) {
			this.type = type;
		}
	};

	/**
	 * 拼接公共内部日志信息
	 */
	protected void fillCommonInfoForInterior() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getDept()).append(BaseDataAnalysisLog.SPLIT_CODE);
		sb.append(this.getServerAddressId()).append(BaseDataAnalysisLog.SPLIT_CODE);
		sb.append(this.getInteriorGameId()).append(BaseDataAnalysisLog.SPLIT_CODE);
		sb.append(this.getOutTime()).append(BaseDataAnalysisLog.SPLIT_CODE);
		if (this.getGainCharacterId() != null) {
			sb.append(this.getSid()).append(BaseDataAnalysisLog.SPLIT_CODE);
			sb.append(this.getGainYunYingId()).append(BaseDataAnalysisLog.SPLIT_CODE);
			sb.append(this.getGainCharacterId()).append(BaseDataAnalysisLog.SPLIT_CODE);
			// sb.append(this.getGainCharacterName()).append(BaseDataAnalysisLog.SPLIT_CODE);
			sb.append(this.getGainCharacterGrade()).append(BaseDataAnalysisLog.SPLIT_CODE);
			sb.append(this.getGainCharacterLoginIp()).append(BaseDataAnalysisLog.SPLIT_CODE);
		}

		sb.append(this.getLogType().getType());

		this.info = sb.toString();
	}

	/**
	 * 拼接公共输出日志信息
	 */
	protected void fillCommonInfoForOutLog() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getDept()).append(BaseDataAnalysisLog.SPLIT_CODE);
		sb.append(this.getServerAddressId()).append(BaseDataAnalysisLog.SPLIT_CODE);
		sb.append(this.getInteriorGameId()).append(BaseDataAnalysisLog.SPLIT_CODE);
		sb.append(this.getOutTime()).append(BaseDataAnalysisLog.SPLIT_CODE);
		if (this.getGainCharacterId() != null) {
			sb.append(this.getSid()).append(BaseDataAnalysisLog.SPLIT_CODE);
			sb.append(this.getGainYunYingId()).append(BaseDataAnalysisLog.SPLIT_CODE);
			sb.append(this.getGainCharacterId()).append(BaseDataAnalysisLog.SPLIT_CODE);
			sb.append(this.getGainCharacterName()).append(BaseDataAnalysisLog.SPLIT_CODE);
			sb.append(this.getGainCharacterGrade()).append(BaseDataAnalysisLog.SPLIT_CODE);
			sb.append(this.getGainCharacterLoginIp()).append(BaseDataAnalysisLog.SPLIT_CODE);
		}

		sb.append(this.getLogType().getType());

		this.info = sb.toString();
	}

	@Override
	public BaseDataAnalysisLog clone() {
		try {
			return (BaseDataAnalysisLog) super.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getGainYunYingId() {
		return gainYunYingId;
	}

	public void setGainYunYingId(String gainYunYingId) {
		this.gainYunYingId = gainYunYingId;
	}

	public String getGainCharacterId() {
		return gainCharacterId;
	}

	public void setGainCharacterId(String gainCharacterId) {
		this.gainCharacterId = gainCharacterId;
	}

	public Integer getGainCharacterGrade() {
		return gainCharacterGrade;
	}

	public void setGainCharacterGrade(Integer gainCharacterGrade) {
		this.gainCharacterGrade = gainCharacterGrade;
	}

	public String getGainCharacterLoginIp() {
		return gainCharacterLoginIp;
	}

	public void setGainCharacterLoginIp(String gainCharacterLoginIp) {
		this.gainCharacterLoginIp = gainCharacterLoginIp;
	}

	public Integer getDept() {
		return dept;
	}

	public void setDept(Integer dept) {
		this.dept = dept;
	}

	public Integer getServerAddressId() {
		return serverAddressId;
	}

	public void setServerAddressId(Integer serverAddressId) {
		this.serverAddressId = serverAddressId;
	}

	public Integer getInteriorGameId() {
		return interiorGameId;
	}

	public void setInteriorGameId(Integer interiorGameId) {
		this.interiorGameId = interiorGameId;
	}

}
