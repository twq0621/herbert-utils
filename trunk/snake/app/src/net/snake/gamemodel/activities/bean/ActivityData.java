package net.snake.gamemodel.activities.bean;

import net.snake.ibatis.IbatisEntity;

public class ActivityData implements IbatisEntity{

	/**
	 * 活动编号ID t_activity.id
	 * 
	 */
	private Integer id;
	/**
	 * 活动名称 t_activity.f_name
	 * 
	 */
	private String fName;
	/**
	 * 周显隐控制 t_activity.f_dayshow
	 * 
	 */
	private String fDayshow;
	/**
	 * 活动时间 8:00-9:30 t_activity.f_activity_time
	 * 
	 */
	private String fActivityTime;
	/**
	 * 最小等级 0为不限 t_activity.f_grade
	 * 
	 */
	private Integer fGrade;
	/**
	 * 发布人ID t_activity.f_fbnpcid
	 * 
	 */
	private Integer fFbnpcid;
	/**
	 * 总次数 t_activity.f_maxcount
	 * 
	 */
	private String fMaxcount;
	/**
	 * 奖励级别 t_activity.f_rewardleave
	 * 
	 */
	private Integer fRewardleave;
	/**
	 * 祥细信息 t_activity.f_desc
	 * 
	 */
	private String fDesc;
	/**
	 * 0不可统计，1可统计 t_activity.f_iscount
	 * 
	 */
	private Short fIscount;
	/**
	 * t_activity.f_taskid
	 * 
	 */
	private Integer fTaskid;
	/**
	 * 活动名称国际化 t_activity.f_name_i18n
	 * 
	 */
	private String fNameI18n;
	/**
	 * 活动时间 国际化 t_activity.f_activity_time_i18n
	 * 
	 */
	private String fActivityTimeI18n;
	/**
	 * 总次数 t_activity.f_maxcount_i18n
	 * 
	 */
	private String fMaxcountI18n;
	/**
	 * 祥细信息国际化 t_activity.f_desc_i18n
	 * 
	 */
	private String fDescI18n;
	/**
	 * 玩家引导提示 t_activity.f_newgaid_msg
	 * 
	 */
	private String fNewgaidMsg;
	/**
	 * 玩家引导行为 t_activity.f_newgaid_action
	 * 
	 */
	private String fNewgaidAction;
	/**
	 * 玩家引导提示 t_activity.f_newgaid_msg_i18n
	 * 
	 */
	private String fNewgaidMsgI18n;

	/**
	 * 活动编号ID t_activity.id
	 * @return  the value of t_activity.id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 活动编号ID t_activity.id
	 * @param id  the value for t_activity.id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 活动名称 t_activity.f_name
	 * @return  the value of t_activity.f_name
	 * 
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * 活动名称 t_activity.f_name
	 * @param fName  the value for t_activity.f_name
	 * 
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * 周显隐控制 t_activity.f_dayshow
	 * @return  the value of t_activity.f_dayshow
	 * 
	 */
	public String getfDayshow() {
		return fDayshow;
	}

	/**
	 * 周显隐控制 t_activity.f_dayshow
	 * @param fDayshow  the value for t_activity.f_dayshow
	 * 
	 */
	public void setfDayshow(String fDayshow) {
		this.fDayshow = fDayshow;
	}

	/**
	 * 活动时间 8:00-9:30 t_activity.f_activity_time
	 * @return  the value of t_activity.f_activity_time
	 * 
	 */
	public String getfActivityTime() {
		return fActivityTime;
	}

	/**
	 * 活动时间 8:00-9:30 t_activity.f_activity_time
	 * @param fActivityTime  the value for t_activity.f_activity_time
	 * 
	 */
	public void setfActivityTime(String fActivityTime) {
		this.fActivityTime = fActivityTime;
	}

	/**
	 * 最小等级 0为不限 t_activity.f_grade
	 * @return  the value of t_activity.f_grade
	 * 
	 */
	public Integer getfGrade() {
		return fGrade;
	}

	/**
	 * 最小等级 0为不限 t_activity.f_grade
	 * @param fGrade  the value for t_activity.f_grade
	 * 
	 */
	public void setfGrade(Integer fGrade) {
		this.fGrade = fGrade;
	}

	/**
	 * 发布人ID t_activity.f_fbnpcid
	 * @return  the value of t_activity.f_fbnpcid
	 * 
	 */
	public Integer getfFbnpcid() {
		return fFbnpcid;
	}

	/**
	 * 发布人ID t_activity.f_fbnpcid
	 * @param fFbnpcid  the value for t_activity.f_fbnpcid
	 * 
	 */
	public void setfFbnpcid(Integer fFbnpcid) {
		this.fFbnpcid = fFbnpcid;
	}

	/**
	 * 总次数 t_activity.f_maxcount
	 * @return  the value of t_activity.f_maxcount
	 * 
	 */
	public String getfMaxcount() {
		return fMaxcount;
	}

	/**
	 * 总次数 t_activity.f_maxcount
	 * @param fMaxcount  the value for t_activity.f_maxcount
	 * 
	 */
	public void setfMaxcount(String fMaxcount) {
		this.fMaxcount = fMaxcount;
	}

	/**
	 * 奖励级别 t_activity.f_rewardleave
	 * @return  the value of t_activity.f_rewardleave
	 * 
	 */
	public Integer getfRewardleave() {
		return fRewardleave;
	}

	/**
	 * 奖励级别 t_activity.f_rewardleave
	 * @param fRewardleave  the value for t_activity.f_rewardleave
	 * 
	 */
	public void setfRewardleave(Integer fRewardleave) {
		this.fRewardleave = fRewardleave;
	}

	/**
	 * 祥细信息 t_activity.f_desc
	 * @return  the value of t_activity.f_desc
	 * 
	 */
	public String getfDesc() {
		return fDesc;
	}

	/**
	 * 祥细信息 t_activity.f_desc
	 * @param fDesc  the value for t_activity.f_desc
	 * 
	 */
	public void setfDesc(String fDesc) {
		this.fDesc = fDesc;
	}

	/**
	 * 0不可统计，1可统计 t_activity.f_iscount
	 * @return  the value of t_activity.f_iscount
	 * 
	 */
	public Short getfIscount() {
		return fIscount;
	}

	/**
	 * 0不可统计，1可统计 t_activity.f_iscount
	 * @param fIscount  the value for t_activity.f_iscount
	 * 
	 */
	public void setfIscount(Short fIscount) {
		this.fIscount = fIscount;
	}

	/**
	 * t_activity.f_taskid
	 * @return  the value of t_activity.f_taskid
	 * 
	 */
	public Integer getfTaskid() {
		return fTaskid;
	}

	/**
	 * t_activity.f_taskid
	 * @param fTaskid  the value for t_activity.f_taskid
	 * 
	 */
	public void setfTaskid(Integer fTaskid) {
		this.fTaskid = fTaskid;
	}

	/**
	 * 活动名称国际化 t_activity.f_name_i18n
	 * @return  the value of t_activity.f_name_i18n
	 * 
	 */
	public String getfNameI18n() {
		return fNameI18n;
	}

	/**
	 * 活动名称国际化 t_activity.f_name_i18n
	 * @param fNameI18n  the value for t_activity.f_name_i18n
	 * 
	 */
	public void setfNameI18n(String fNameI18n) {
		this.fNameI18n = fNameI18n;
	}

	/**
	 * 活动时间 国际化 t_activity.f_activity_time_i18n
	 * @return  the value of t_activity.f_activity_time_i18n
	 * 
	 */
	public String getfActivityTimeI18n() {
		return fActivityTimeI18n;
	}

	/**
	 * 活动时间 国际化 t_activity.f_activity_time_i18n
	 * @param fActivityTimeI18n  the value for t_activity.f_activity_time_i18n
	 * 
	 */
	public void setfActivityTimeI18n(String fActivityTimeI18n) {
		this.fActivityTimeI18n = fActivityTimeI18n;
	}

	/**
	 * 总次数 t_activity.f_maxcount_i18n
	 * @return  the value of t_activity.f_maxcount_i18n
	 * 
	 */
	public String getfMaxcountI18n() {
		return fMaxcountI18n;
	}

	/**
	 * 总次数 t_activity.f_maxcount_i18n
	 * @param fMaxcountI18n  the value for t_activity.f_maxcount_i18n
	 * 
	 */
	public void setfMaxcountI18n(String fMaxcountI18n) {
		this.fMaxcountI18n = fMaxcountI18n;
	}

	/**
	 * 祥细信息国际化 t_activity.f_desc_i18n
	 * @return  the value of t_activity.f_desc_i18n
	 * 
	 */
	public String getfDescI18n() {
		return fDescI18n;
	}

	/**
	 * 祥细信息国际化 t_activity.f_desc_i18n
	 * @param fDescI18n  the value for t_activity.f_desc_i18n
	 * 
	 */
	public void setfDescI18n(String fDescI18n) {
		this.fDescI18n = fDescI18n;
	}

	/**
	 * 玩家引导提示 t_activity.f_newgaid_msg
	 * @return  the value of t_activity.f_newgaid_msg
	 * 
	 */
	public String getfNewgaidMsg() {
		return fNewgaidMsg;
	}

	/**
	 * 玩家引导提示 t_activity.f_newgaid_msg
	 * @param fNewgaidMsg  the value for t_activity.f_newgaid_msg
	 * 
	 */
	public void setfNewgaidMsg(String fNewgaidMsg) {
		this.fNewgaidMsg = fNewgaidMsg;
	}

	/**
	 * 玩家引导行为 t_activity.f_newgaid_action
	 * @return  the value of t_activity.f_newgaid_action
	 * 
	 */
	public String getfNewgaidAction() {
		return fNewgaidAction;
	}

	/**
	 * 玩家引导行为 t_activity.f_newgaid_action
	 * @param fNewgaidAction  the value for t_activity.f_newgaid_action
	 * 
	 */
	public void setfNewgaidAction(String fNewgaidAction) {
		this.fNewgaidAction = fNewgaidAction;
	}

	/**
	 * 玩家引导提示 t_activity.f_newgaid_msg_i18n
	 * @return  the value of t_activity.f_newgaid_msg_i18n
	 * 
	 */
	public String getfNewgaidMsgI18n() {
		return fNewgaidMsgI18n;
	}

	/**
	 * 玩家引导提示 t_activity.f_newgaid_msg_i18n
	 * @param fNewgaidMsgI18n  the value for t_activity.f_newgaid_msg_i18n
	 * 
	 */
	public void setfNewgaidMsgI18n(String fNewgaidMsgI18n) {
		this.fNewgaidMsgI18n = fNewgaidMsgI18n;
	}
}
