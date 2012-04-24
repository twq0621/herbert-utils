package net.snake.commons.httplog;


/**
 * 内部日志发送类
 * 
 * @author serv_dev
 */
public class HttpInteriorLogService {
	/**
	 * @description 内部消费日志远程调用
	 * @param baseDataAnalysisLog
	 *            消费日志信息
	 */
	public void interiorConsumptionLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("consumptionLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * @description 内部登录日志输出
	 * @param baseDataAnalysisLog
	 *            登录日志信息
	 */
	public void interiorLoginLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("loginLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * @description 内部登出日志输出
	 * @param baseDataAnalysisLog
	 *            登出日志信息
	 */
	public void interiorLogoutLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("logoutLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * @description 内部玩家在线日志输出
	 * @param baseDataAnalysisLog
	 *            在线日志信息
	 */
	public void interiorOnlineLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("onlineLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * @description 内部玩家心跳日志输出
	 * @param baseDataAnalysisLog
	 *            在线日志信息
	 */
	public void interiorHeartLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("heartLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * @description 内部充值日志远程调用
	 * @param baseDataAnalysisLog
	 *            充值日志信息
	 */
	public void interiorRechargeLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("rechargeLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * @description 内部登陆加载
	 * @param baseDataAnalysisLog
	 */
	public void interiorGamePageLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("gamePageLogOut.asp", baseDataAnalysisLog);
	}

	/************************** 第二次添加 *****************************/

	/**
	 * 内部副本进入日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorInstanceInLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("instanceInLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部副本退出日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorInstanceOutLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("instanceOutLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部环任务日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorCirculationTaskLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("circulationTaskLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部打BOSS日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorBossLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("bossLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部闭关日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorBiguanLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("biguanLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部签到日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorQiandaoLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("qiandaoLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部连续登陆日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorLoginRewardLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("loginRewardLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部玩家与玩家交易日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorP2PTradeLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("P2PTradeLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部玩家挂售日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorP2PAgoraLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("P2PAgoraLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部玩家与NPC买入卖出日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorP2COptionLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("P2COptionLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 内部婚宴日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorFeastLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("feastLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 坐骑祝福值清空日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorHorseBeatitudeLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("horseBeatitudeLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 每周在线奖励日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorWeekOnlineLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("weekOnlineLogOut.asp", baseDataAnalysisLog);
	}

	/********************************* 第三次添加 ***********************************/
	/**
	 * 经脉日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorChannelLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("channelLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 暗器升阶日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorHiddenWeaponsLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("hiddenWeaponsLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 暗器提速石日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorTisushiLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("tisushiLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 炼体日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorLiantiLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("liantiLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 弓箭日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorBowLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("bowLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 丹田日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorDantianLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("dantianLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 独孤悟道日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorDuguLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("duguLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 武功技能学习日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorSkillLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("skillLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 护身符日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorHushenhuLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("hushenfuLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 装备炼制及舍利重置日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorEquipmentOneLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("equipmentOneLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 装备合成及战纹刻画日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorEquipmentTweLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("equipmentTweLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 宝石合成日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorBaoshiLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("baoshiLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 榛果日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorPineconeLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("pineconeLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 坐骑炼骨进阶日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorHorseOneLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("horseOneLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * 坐骑驯养日志
	 * 
	 * @param baseDataAnalysisLog
	 */
	public void interiorHorseTweLog(BaseDataAnalysisLog baseDataAnalysisLog) {
		this.sendInteriorHttpLogRequest("horseTweLogOut.asp", baseDataAnalysisLog);
	}

	/**
	 * @description 内部日志发送方法，仅限公司内部远程日志发送调用
	 * @param method
	 *            日志方法名
	 * @param baseDataAnalysisLog
	 *            日志内容bean
	 */
	private void sendInteriorHttpLogRequest(String method, BaseDataAnalysisLog baseDataAnalysisLog) {
	}
}
