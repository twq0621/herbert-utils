package net.snake.gamemodel.task.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class Task implements IbatisEntity {

	/**
	 * 主键id(自增长) t_task.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 界面任务id t_task.f_task_id
	 * 
	 */
	private Integer taskId;
	/**
	 * 任务名称 t_task.f_name
	 * 
	 */
	private String name;
	/**
	 * 如:1主线 2支线 3日环式任务4.日押镖任务5周环式任务6.升级环式任务 t_task.f_type
	 * 
	 */
	private Byte type;
	/**
	 * 功能类型:0复合任务,1刺杀怪物 2寻找NPC 3收集 4到达指定区域 5传送 6采集 7触发动作 8捕获坐骑 t_task.f_functionType
	 * 
	 */
	private Integer functiontype;
	/**
	 * 是否可放弃（1可放弃 0不可放弃） t_task.f_islost
	 * 
	 */
	private Boolean islost;
	/**
	 * 是否可重复接（1可重复 0不可重复） t_task.f_isRepeat
	 * 
	 */
	private Boolean isrepeat;
	/**
	 * 任务发布方式 1 npc 发布 2创建角色时添加 3系统发布 4道具发布 5陷阱点触发 t_task.f_publishStyle
	 * 
	 */
	private Byte publishstyle;
	/**
	 * 任务接受等级下限 t_task.f_receptionLevel
	 * 
	 */
	private Short receptionlevel;
	/**
	 * 接受任务时等级上限 t_task.f_receptionUpLevel
	 * 
	 */
	private Short receptionuplevel;
	/**
	 * 起始NPC t_task.f_beginNpc
	 * 
	 */
	private Integer beginnpc;
	/**
	 * 结束npc id t_task.f_endNpc
	 * 
	 */
	private Integer endnpc;
	/**
	 * 任务目标物品 (物品id#物品数量#场景id_x_y_距离),* t_task.f_targetGoods
	 * 
	 */
	private String targetgoods;
	/**
	 * 任务目标类型怪 (怪物模板id#数量#场景_x_y_距目标几步停止),* t_task.f_targetMonster
	 * 
	 */
	private String targetmonster;
	/**
	 * 目标Npc (npcid),* t_task.f_targetNpc
	 * 
	 */
	private String targetnpc;
	/**
	 * 格式 以格子为单位 场景id t_task.f_targetArea
	 * 
	 */
	private String targetarea;
	/**
	 * 动作触发 多个动作以,分割 （1组队，2添加好友3使用喇叭 4装备物品 5使用恢复类道具 6道具合成 7宝石镶嵌 8 装备修理 9买入道具 10卖出道具 11秘籍升级 12将技能/道具拖入快捷栏 ) t_task.f_targetAction
	 * 
	 */
	private String targetaction;
	/**
	 * 完成任务等级 t_task.f_endTaskLevel
	 * 
	 */
	private Integer endtasklevel;
	/**
	 * 单个任务时间限制以分钟为单位 -1为没有时间限制 t_task.f_limitTime
	 * 
	 */
	private Short limittime;
	/**
	 * 奖励经验(奖励) t_task.f_experience
	 * 
	 */
	private Integer experience;
	/**
	 * 奖励铜币(奖励) t_task.f_copper
	 * 
	 */
	private Integer copper;
	/**
	 * 奖励金币(礼金)(奖励) t_task.f_gold
	 * 
	 */
	private Integer gold;
	/**
	 * 固定任务奖励(数量不能大于物品上限)格式 (物品id#数量#门派id),*(奖励) t_task.f_goods
	 * 
	 */
	private String goods;
	/**
	 * 任务发起描述 t_task.f_taskDes
	 * 
	 */
	private String taskdes;
	/**
	 * 未完成任务描述 t_task.f_unEndDes
	 * 
	 */
	private String unenddes;
	/**
	 * 完成任务描述 t_task.f_endDes
	 * 
	 */
	private String enddes;
	/**
	 * 任务描述 t_task.f_publishDes
	 * 
	 */
	private String publishdes;
	/**
	 * 好感值 t_task.f_goodFeel
	 * 
	 */
	private Integer goodfeel;
	/**
	 * 帮派贡献(奖励) t_task.f_party
	 * 
	 */
	private Integer party;
	/**
	 * 善恶值 t_task.f_goodBad
	 * 
	 */
	private Integer goodbad;
	/**
	 * 称号id(奖励) t_task.f_title
	 * 
	 */
	private Integer title;
	/**
	 * 接受任务时获得的任务品（不可叠加的物品）格式 物品id:交任务时是否系统收回(0/1)零为不收回/1收回 t_task.f_taskGoods
	 * 
	 */
	private String taskgoods;
	/**
	 * 任务完成任时取走的物品ID t_task.f_takeGoods
	 * 
	 */
	private Integer takegoods;
	/**
	 * 奖励战场声望(奖励) t_task.f_warrepute
	 * 
	 */
	private Integer warrepute;
	/**
	 * 励奖的buffID(奖励) t_task.f_buffid
	 * 
	 */
	private Integer buffid;
	/**
	 * 奖励的全服公告(奖励) t_task.f_bulletin
	 * 
	 */
	private Integer bulletin;
	/**
	 * 奖励的真气量(奖励) t_task.f_zhenqi
	 * 
	 */
	private Integer zhenqi;
	/**
	 * 环任务的难度 t_task.f_loopTaskDifficulty
	 * 
	 */
	private Integer looptaskdifficulty;
	/**
	 * 环任务奖励的丰厚度 t_task.f_loopTaskBonus
	 * 
	 */
	private Integer looptaskbonus;
	/**
	 * 任务条件：捕获坐骑ID (坐骑id#数量#场景_x_y_距离) t_task.f_targetHorse
	 * 
	 */
	private String targethorse;
	/**
	 * 道具触发任务(道具id) t_task.f_triggerGoods
	 * 
	 */
	private Integer triggergoods;
	/**
	 * 陷阱触发任务(场景id_x_y_距离) t_task.f_triggerScene
	 * 
	 */
	private String triggerscene;
	/**
	 * 取走玩家的铜币 t_task.f_takecopper
	 * 
	 */
	private Integer takecopper;
	/**
	 * 前置任务排序用id(暂时没用) t_task.f_premiseId
	 * 
	 */
	private Integer premiseid;
	/**
	 * 前提任务id t_task.f_premiseTask
	 * 
	 */
	private Integer premisetask;
	/**
	 * 工具用于跟踪 插入/修改时间 t_task.f_time
	 * 
	 */
	private Date time;
	/**
	 * 0不扣坐骑1扣坐骑 t_task.f_horseDrop
	 * 
	 */
	private Integer horsedrop;
	/**
	 * (身上是否有穿戴武器)有没有佩戴武器 t_task.f_needWuqi
	 * 
	 */
	private Integer needwuqi;
	/**
	 * (身上是否有穿戴衣服)有没有佩戴衣服 t_task.f_needDress
	 * 
	 */
	private Integer needdress;
	/**
	 * (需要已学习的武功id) 有没有学会武功 t_task.f_needSkill
	 * 
	 */
	private Integer needskill;
	/**
	 * (限定总的武功等级)武功层数是否达到 t_task.f_needWugongGrade
	 * 
	 */
	private Integer needwugonggrade;
	/**
	 * 任务目标描述 t_task.f_taskTargetDes
	 * 
	 */
	private String tasktargetdes;
	/**
	 * 判断玩家是否从商城中购买过某个物品（物品可自定义输入） (逗号相隔的道具ID#数量) t_task.f_targetShopping
	 * 
	 */
	private String targetshopping;
	/**
	 * 充值金额(判断玩家是否充值过)(单位元宝) t_task.f_targetRecharge
	 * 
	 */
	private String targetrecharge;
	/**
	 * 判断玩家是否使用过坐骑进攻本功能：将的卢马成功地进阶合成为金甲神牛(1为需进阶0不需进阶) t_task.f_targetMountUpgrade
	 * 
	 */
	private String targetmountupgrade;
	/**
	 * 判断玩家当前是否组队，队伍总人数是否>X(格式:"目标队伍人数" 或 "") t_task.f_targetGroup
	 * 
	 */
	private String targetgroup;
	/**
	 * 判断玩家当前拥有好友人数的数量是否>X(格式:"目标好友人数" 或 "") t_task.f_targetFriend
	 * 
	 */
	private String targetfriend;
	/**
	 * 判断玩家某个部位是否佩戴了装备ID(格式:"目标部位ID#目标装备ID" 或 "") t_task.f_targetEquip
	 * 
	 */
	private String targetequip;
	/**
	 * 判断玩家某个武功的等级是否大于X(格式:"目标武功ID#目标等级" 或 "") t_task.f_targetSkillLv
	 * 
	 */
	private String targetskilllv;
	/**
	 * 判断玩家整体武功境界层数是否大于X(格式:"目标等级" 或 "") t_task.f_targetAllSkillLv
	 * 
	 */
	private String targetallskilllv;
	/**
	 * 判断玩家某个经脉穴位是否冲通(格式:"目标穴位ID" 或 "") t_task.f_targetPoint
	 * 
	 */
	private String targetpoint;
	/**
	 * 判断玩家某条经脉是否冲通(格式:"目标经脉ID" 或 "") t_task.f_targetChannel
	 * 
	 */
	private String targetchannel;
	/**
	 * 判断玩家身上是否有某个BUFF-ID(格式:"目标BUFF ID" 或 "") t_task.f_targetBuff
	 * 
	 */
	private String targetbuff;
	/**
	 * 环任务最大上限次数 t_task.f_loopMaxCount
	 * 
	 */
	private Integer loopmaxcount;
	/**
	 * 奖励暗器(暗器id) t_task.f_hiddenWeapon
	 * 
	 */
	private Integer hiddenweapon;
	/**
	 * 接任务开始时间 t_task.f_accept_begin_time
	 * 
	 */
	private Date acceptBeginTime;
	/**
	 * 交任务结束时间 t_task.f_complete_end_time
	 * 
	 */
	private Date completeEndTime;
	/**
	 * 任务名称国际化 t_task.f_name_i18n
	 * 
	 */
	private String nameI18n;
	/**
	 * 未完成任务描述际化国 t_task.f_unEndDes_i18n
	 * 
	 */
	private String unenddesI18n;
	/**
	 * 完成任务描述国际化 t_task.f_endDes_i18n
	 * 
	 */
	private String enddesI18n;
	/**
	 * 任务描述 t_task.f_publishDes_i18n
	 * 
	 */
	private String publishdesI18n;
	/**
	 * 任务目标描述国际化 t_task.f_taskTargetDes_i18n
	 * 
	 */
	private String tasktargetdesI18n;
	/**
	 * 是否奖历弓箭0否 1是 t_task.f_bow
	 * 
	 */
	private Integer bow;
	/**
	 * 是否奖历丹田0否 1是 t_task.f_dantian
	 * 
	 */
	private Integer dantian;

	/**
	 * 主键id(自增长) t_task.f_id
	 * 
	 * @return the value of t_task.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键id(自增长) t_task.f_id
	 * 
	 * @param id
	 *            the value for t_task.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 界面任务id t_task.f_task_id
	 * 
	 * @return the value of t_task.f_task_id
	 */
	public Integer getTaskId() {
		return taskId;
	}

	/**
	 * 界面任务id t_task.f_task_id
	 * 
	 * @param taskId
	 *            the value for t_task.f_task_id
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	/**
	 * 任务名称 t_task.f_name
	 * 
	 * @return the value of t_task.f_name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 任务名称 t_task.f_name
	 * 
	 * @param name
	 *            the value for t_task.f_name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 如:1主线 2支线 3日环式任务4.日押镖任务5周环式任务6.升级环式任务 t_task.f_type
	 * 
	 * @return the value of t_task.f_type
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * 如:1主线 2支线 3日环式任务4.日押镖任务5周环式任务6.升级环式任务 t_task.f_type
	 * 
	 * @param type
	 *            the value for t_task.f_type
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * 功能类型:0复合任务,1刺杀怪物 2寻找NPC 3收集 4到达指定区域 5传送 6采集 7触发动作 8捕获坐骑 t_task.f_functionType
	 * 
	 * @return the value of t_task.f_functionType
	 */
	public Integer getFunctiontype() {
		return functiontype;
	}

	/**
	 * 功能类型:0复合任务,1刺杀怪物 2寻找NPC 3收集 4到达指定区域 5传送 6采集 7触发动作 8捕获坐骑 t_task.f_functionType
	 * 
	 * @param functiontype
	 *            the value for t_task.f_functionType
	 */
	public void setFunctiontype(Integer functiontype) {
		this.functiontype = functiontype;
	}

	/**
	 * 是否可放弃（1可放弃 0不可放弃） t_task.f_islost
	 * 
	 * @return the value of t_task.f_islost
	 */
	public Boolean getIslost() {
		return islost;
	}

	/**
	 * 是否可放弃（1可放弃 0不可放弃） t_task.f_islost
	 * 
	 * @param islost
	 *            the value for t_task.f_islost
	 */
	public void setIslost(Boolean islost) {
		this.islost = islost;
	}

	/**
	 * 是否可重复接（1可重复 0不可重复） t_task.f_isRepeat
	 * 
	 * @return the value of t_task.f_isRepeat
	 */
	public Boolean getIsrepeat() {
		return isrepeat;
	}

	/**
	 * 是否可重复接（1可重复 0不可重复） t_task.f_isRepeat
	 * 
	 * @param isrepeat
	 *            the value for t_task.f_isRepeat
	 */
	public void setIsrepeat(Boolean isrepeat) {
		this.isrepeat = isrepeat;
	}

	/**
	 * 任务发布方式 1 npc 发布 2创建角色时添加 3系统发布 4道具发布 5陷阱点触发 t_task.f_publishStyle
	 * 
	 * @return the value of t_task.f_publishStyle
	 */
	public Byte getPublishstyle() {
		return publishstyle;
	}

	/**
	 * 任务发布方式 1 npc 发布 2创建角色时添加 3系统发布 4道具发布 5陷阱点触发 t_task.f_publishStyle
	 * 
	 * @param publishstyle
	 *            the value for t_task.f_publishStyle
	 */
	public void setPublishstyle(Byte publishstyle) {
		this.publishstyle = publishstyle;
	}

	/**
	 * 任务接受等级下限 t_task.f_receptionLevel
	 * 
	 * @return the value of t_task.f_receptionLevel
	 */
	public Short getReceptionlevel() {
		return receptionlevel;
	}

	/**
	 * 任务接受等级下限 t_task.f_receptionLevel
	 * 
	 * @param receptionlevel
	 *            the value for t_task.f_receptionLevel
	 */
	public void setReceptionlevel(Short receptionlevel) {
		this.receptionlevel = receptionlevel;
	}

	/**
	 * 接受任务时等级上限 t_task.f_receptionUpLevel
	 * 
	 * @return the value of t_task.f_receptionUpLevel
	 */
	public Short getReceptionuplevel() {
		return receptionuplevel;
	}

	/**
	 * 接受任务时等级上限 t_task.f_receptionUpLevel
	 * 
	 * @param receptionuplevel
	 *            the value for t_task.f_receptionUpLevel
	 */
	public void setReceptionuplevel(Short receptionuplevel) {
		this.receptionuplevel = receptionuplevel;
	}

	/**
	 * 起始NPC t_task.f_beginNpc
	 * 
	 * @return the value of t_task.f_beginNpc
	 */
	public Integer getBeginnpc() {
		return beginnpc;
	}

	/**
	 * 起始NPC t_task.f_beginNpc
	 * 
	 * @param beginnpc
	 *            the value for t_task.f_beginNpc
	 */
	public void setBeginnpc(Integer beginnpc) {
		this.beginnpc = beginnpc;
	}

	/**
	 * 结束npc id t_task.f_endNpc
	 * 
	 * @return the value of t_task.f_endNpc
	 */
	public Integer getEndnpc() {
		return endnpc;
	}

	/**
	 * 结束npc id t_task.f_endNpc
	 * 
	 * @param endnpc
	 *            the value for t_task.f_endNpc
	 */
	public void setEndnpc(Integer endnpc) {
		this.endnpc = endnpc;
	}

	/**
	 * 任务目标物品 (物品id#物品数量#场景id_x_y_距离),* t_task.f_targetGoods
	 * 
	 * @return the value of t_task.f_targetGoods
	 */
	public String getTargetgoods() {
		return targetgoods;
	}

	/**
	 * 任务目标物品 (物品id#物品数量#场景id_x_y_距离),* t_task.f_targetGoods
	 * 
	 * @param targetgoods
	 *            the value for t_task.f_targetGoods
	 */
	public void setTargetgoods(String targetgoods) {
		this.targetgoods = targetgoods;
	}

	/**
	 * 任务目标类型怪 (怪物模板id#数量#场景_x_y_距目标几步停止),* t_task.f_targetMonster
	 * 
	 * @return the value of t_task.f_targetMonster
	 */
	public String getTargetmonster() {
		return targetmonster;
	}

	/**
	 * 任务目标类型怪 (怪物模板id#数量#场景_x_y_距目标几步停止),* t_task.f_targetMonster
	 * 
	 * @param targetmonster
	 *            the value for t_task.f_targetMonster
	 */
	public void setTargetmonster(String targetmonster) {
		if (targetmonster==null) {
			this.targetmonster = targetmonster;
			return ;
		}
		
		String[] strs = targetmonster.split(",");
		for (int i = 0; i < strs.length; i++) {
			String[] fields=strs[i].split("#");
			if (fields.length>4) {
				strs[i] = fields[0]+"#"+fields[1]+"#"+fields[2]+"#"+fields[3];
			}
		}
		targetmonster="";
		for (int i = 0; i < (strs.length-1); i++) {
			targetmonster = targetmonster+strs[i]+",";
		}
		targetmonster = targetmonster+strs[strs.length-1];
		
		this.targetmonster = targetmonster;
	}

	/**
	 * 目标Npc (npcid),* t_task.f_targetNpc
	 * 
	 * @return the value of t_task.f_targetNpc
	 */
	public String getTargetnpc() {
		return targetnpc;
	}

	/**
	 * 目标Npc (npcid),* t_task.f_targetNpc
	 * 
	 * @param targetnpc
	 *            the value for t_task.f_targetNpc
	 */
	public void setTargetnpc(String targetnpc) {
		this.targetnpc = targetnpc;
	}

	/**
	 * 格式 以格子为单位 场景id t_task.f_targetArea
	 * 
	 * @return the value of t_task.f_targetArea
	 */
	public String getTargetarea() {
		return targetarea;
	}

	/**
	 * 格式 以格子为单位 场景id t_task.f_targetArea
	 * 
	 * @param targetarea
	 *            the value for t_task.f_targetArea
	 */
	public void setTargetarea(String targetarea) {
		this.targetarea = targetarea;
	}

	/**
	 * 动作触发 多个动作以,分割 （1组队，2添加好友3使用喇叭 4装备物品 5使用恢复类道具 6道具合成 7宝石镶嵌 8 装备修理 9买入道具 10卖出道具 11秘籍升级 12将技能/道具拖入快捷栏 ) t_task.f_targetAction
	 * 
	 * @return the value of t_task.f_targetAction
	 */
	public String getTargetaction() {
		return targetaction;
	}

	/**
	 * 动作触发 多个动作以,分割 （1组队，2添加好友3使用喇叭 4装备物品 5使用恢复类道具 6道具合成 7宝石镶嵌 8 装备修理 9买入道具 10卖出道具 11秘籍升级 12将技能/道具拖入快捷栏 ) t_task.f_targetAction
	 * 
	 * @param targetaction
	 *            the value for t_task.f_targetAction
	 */
	public void setTargetaction(String targetaction) {
		this.targetaction = targetaction;
	}

	/**
	 * 完成任务等级 t_task.f_endTaskLevel
	 * 
	 * @return the value of t_task.f_endTaskLevel
	 */
	public Integer getEndtasklevel() {
		return endtasklevel;
	}

	/**
	 * 完成任务等级 t_task.f_endTaskLevel
	 * 
	 * @param endtasklevel
	 *            the value for t_task.f_endTaskLevel
	 */
	public void setEndtasklevel(Integer endtasklevel) {
		this.endtasklevel = endtasklevel;
	}

	/**
	 * 单个任务时间限制以分钟为单位 -1为没有时间限制 t_task.f_limitTime
	 * 
	 * @return the value of t_task.f_limitTime
	 */
	public Short getLimittime() {
		return limittime;
	}

	/**
	 * 单个任务时间限制以分钟为单位 -1为没有时间限制 t_task.f_limitTime
	 * 
	 * @param limittime
	 *            the value for t_task.f_limitTime
	 */
	public void setLimittime(Short limittime) {
		this.limittime = limittime;
	}

	/**
	 * 奖励经验(奖励) t_task.f_experience
	 * 
	 * @return the value of t_task.f_experience
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * 奖励经验(奖励) t_task.f_experience
	 * 
	 * @param experience
	 *            the value for t_task.f_experience
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * 奖励铜币(奖励) t_task.f_copper
	 * 
	 * @return the value of t_task.f_copper
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 奖励铜币(奖励) t_task.f_copper
	 * 
	 * @param copper
	 *            the value for t_task.f_copper
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 奖励金币(礼金)(奖励) t_task.f_gold
	 * 
	 * @return the value of t_task.f_gold
	 */
	public Integer getGold() {
		return gold;
	}

	/**
	 * 奖励金币(礼金)(奖励) t_task.f_gold
	 * 
	 * @param gold
	 *            the value for t_task.f_gold
	 */
	public void setGold(Integer gold) {
		this.gold = gold;
	}

	/**
	 * 固定任务奖励(数量不能大于物品上限)格式 (物品id#数量#门派id),*(奖励) t_task.f_goods
	 * 
	 * @return the value of t_task.f_goods
	 */
	public String getGoods() {
		return goods;
	}

	/**
	 * 固定任务奖励(数量不能大于物品上限)格式 (物品id#数量#门派id),*(奖励) t_task.f_goods
	 * 
	 * @param goods
	 *            the value for t_task.f_goods
	 */
	public void setGoods(String goods) {
		this.goods = goods;
	}

	/**
	 * 任务发起描述 t_task.f_taskDes
	 * 
	 * @return the value of t_task.f_taskDes
	 */
	public String getTaskdes() {
		return taskdes;
	}

	/**
	 * 任务发起描述 t_task.f_taskDes
	 * 
	 * @param taskdes
	 *            the value for t_task.f_taskDes
	 */
	public void setTaskdes(String taskdes) {
		this.taskdes = taskdes;
	}

	/**
	 * 未完成任务描述 t_task.f_unEndDes
	 * 
	 * @return the value of t_task.f_unEndDes
	 */
	public String getUnenddes() {
		return unenddes;
	}

	/**
	 * 未完成任务描述 t_task.f_unEndDes
	 * 
	 * @param unenddes
	 *            the value for t_task.f_unEndDes
	 */
	public void setUnenddes(String unenddes) {
		this.unenddes = unenddes;
	}

	/**
	 * 完成任务描述 t_task.f_endDes
	 * 
	 * @return the value of t_task.f_endDes
	 */
	public String getEnddes() {
		return enddes;
	}

	/**
	 * 完成任务描述 t_task.f_endDes
	 * 
	 * @param enddes
	 *            the value for t_task.f_endDes
	 */
	public void setEnddes(String enddes) {
		this.enddes = enddes;
	}

	/**
	 * 任务描述 t_task.f_publishDes
	 * 
	 * @return the value of t_task.f_publishDes
	 */
	public String getPublishdes() {
		return publishdes;
	}

	/**
	 * 任务描述 t_task.f_publishDes
	 * 
	 * @param publishdes
	 *            the value for t_task.f_publishDes
	 */
	public void setPublishdes(String publishdes) {
		this.publishdes = publishdes;
	}

	/**
	 * 好感值 t_task.f_goodFeel
	 * 
	 * @return the value of t_task.f_goodFeel
	 */
	public Integer getGoodfeel() {
		return goodfeel;
	}

	/**
	 * 好感值 t_task.f_goodFeel
	 * 
	 * @param goodfeel
	 *            the value for t_task.f_goodFeel
	 */
	public void setGoodfeel(Integer goodfeel) {
		this.goodfeel = goodfeel;
	}

	/**
	 * 帮派贡献(奖励) t_task.f_party
	 * 
	 * @return the value of t_task.f_party
	 */
	public Integer getParty() {
		return party;
	}

	/**
	 * 帮派贡献(奖励) t_task.f_party
	 * 
	 * @param party
	 *            the value for t_task.f_party
	 */
	public void setParty(Integer party) {
		this.party = party;
	}

	/**
	 * 善恶值 t_task.f_goodBad
	 * 
	 * @return the value of t_task.f_goodBad
	 */
	public Integer getGoodbad() {
		return goodbad;
	}

	/**
	 * 善恶值 t_task.f_goodBad
	 * 
	 * @param goodbad
	 *            the value for t_task.f_goodBad
	 */
	public void setGoodbad(Integer goodbad) {
		this.goodbad = goodbad;
	}

	/**
	 * 称号id(奖励) t_task.f_title
	 * 
	 * @return the value of t_task.f_title
	 */
	public Integer getTitle() {
		return title;
	}

	/**
	 * 称号id(奖励) t_task.f_title
	 * 
	 * @param title
	 *            the value for t_task.f_title
	 */
	public void setTitle(Integer title) {
		this.title = title;
	}

	/**
	 * 接受任务时获得的任务品（不可叠加的物品）格式 物品id:交任务时是否系统收回(0/1)零为不收回/1收回 t_task.f_taskGoods
	 * 
	 * @return the value of t_task.f_taskGoods
	 */
	public String getTaskgoods() {
		return taskgoods;
	}

	/**
	 * 接受任务时获得的任务品（不可叠加的物品）格式 物品id:交任务时是否系统收回(0/1)零为不收回/1收回 t_task.f_taskGoods
	 * 
	 * @param taskgoods
	 *            the value for t_task.f_taskGoods
	 */
	public void setTaskgoods(String taskgoods) {
		this.taskgoods = taskgoods;
	}

	/**
	 * 任务完成任时取走的物品ID t_task.f_takeGoods
	 * 
	 * @return the value of t_task.f_takeGoods
	 */
	public Integer getTakegoods() {
		return takegoods;
	}

	/**
	 * 任务完成任时取走的物品ID t_task.f_takeGoods
	 * 
	 * @param takegoods
	 *            the value for t_task.f_takeGoods
	 */
	public void setTakegoods(Integer takegoods) {
		this.takegoods = takegoods;
	}

	/**
	 * 奖励战场声望(奖励) t_task.f_warrepute
	 * 
	 * @return the value of t_task.f_warrepute
	 */
	public Integer getWarrepute() {
		return warrepute;
	}

	/**
	 * 奖励战场声望(奖励) t_task.f_warrepute
	 * 
	 * @param warrepute
	 *            the value for t_task.f_warrepute
	 */
	public void setWarrepute(Integer warrepute) {
		this.warrepute = warrepute;
	}

	/**
	 * 励奖的buffID(奖励) t_task.f_buffid
	 * 
	 * @return the value of t_task.f_buffid
	 */
	public Integer getBuffid() {
		return buffid;
	}

	/**
	 * 励奖的buffID(奖励) t_task.f_buffid
	 * 
	 * @param buffid
	 *            the value for t_task.f_buffid
	 */
	public void setBuffid(Integer buffid) {
		this.buffid = buffid;
	}

	/**
	 * 奖励的全服公告(奖励) t_task.f_bulletin
	 * 
	 * @return the value of t_task.f_bulletin
	 */
	public Integer getBulletin() {
		return bulletin;
	}

	/**
	 * 奖励的全服公告(奖励) t_task.f_bulletin
	 * 
	 * @param bulletin
	 *            the value for t_task.f_bulletin
	 */
	public void setBulletin(Integer bulletin) {
		this.bulletin = bulletin;
	}

	/**
	 * 奖励的真气量(奖励) t_task.f_zhenqi
	 * 
	 * @return the value of t_task.f_zhenqi
	 */
	public Integer getZhenqi() {
		return zhenqi;
	}

	/**
	 * 奖励的真气量(奖励) t_task.f_zhenqi
	 * 
	 * @param zhenqi
	 *            the value for t_task.f_zhenqi
	 */
	public void setZhenqi(Integer zhenqi) {
		this.zhenqi = zhenqi;
	}

	/**
	 * 环任务的难度 t_task.f_loopTaskDifficulty
	 * 
	 * @return the value of t_task.f_loopTaskDifficulty
	 */
	public Integer getLooptaskdifficulty() {
		return looptaskdifficulty;
	}

	/**
	 * 环任务的难度 t_task.f_loopTaskDifficulty
	 * 
	 * @param looptaskdifficulty
	 *            the value for t_task.f_loopTaskDifficulty
	 */
	public void setLooptaskdifficulty(Integer looptaskdifficulty) {
		this.looptaskdifficulty = looptaskdifficulty;
	}

	/**
	 * 环任务奖励的丰厚度 t_task.f_loopTaskBonus
	 * 
	 * @return the value of t_task.f_loopTaskBonus
	 */
	public Integer getLooptaskbonus() {
		return looptaskbonus;
	}

	/**
	 * 环任务奖励的丰厚度 t_task.f_loopTaskBonus
	 * 
	 * @param looptaskbonus
	 *            the value for t_task.f_loopTaskBonus
	 */
	public void setLooptaskbonus(Integer looptaskbonus) {
		this.looptaskbonus = looptaskbonus;
	}

	/**
	 * 任务条件：捕获坐骑ID (坐骑id#数量#场景_x_y_距离) t_task.f_targetHorse
	 * 
	 * @return the value of t_task.f_targetHorse
	 */
	public String getTargethorse() {
		return targethorse;
	}

	/**
	 * 任务条件：捕获坐骑ID (坐骑id#数量#场景_x_y_距离) t_task.f_targetHorse
	 * 
	 * @param targethorse
	 *            the value for t_task.f_targetHorse
	 */
	public void setTargethorse(String targethorse) {
		this.targethorse = targethorse;
	}

	/**
	 * 道具触发任务(道具id) t_task.f_triggerGoods
	 * 
	 * @return the value of t_task.f_triggerGoods
	 */
	public Integer getTriggergoods() {
		return triggergoods;
	}

	/**
	 * 道具触发任务(道具id) t_task.f_triggerGoods
	 * 
	 * @param triggergoods
	 *            the value for t_task.f_triggerGoods
	 */
	public void setTriggergoods(Integer triggergoods) {
		this.triggergoods = triggergoods;
	}

	/**
	 * 陷阱触发任务(场景id_x_y_距离) t_task.f_triggerScene
	 * 
	 * @return the value of t_task.f_triggerScene
	 */
	public String getTriggerscene() {
		return triggerscene;
	}

	/**
	 * 陷阱触发任务(场景id_x_y_距离) t_task.f_triggerScene
	 * 
	 * @param triggerscene
	 *            the value for t_task.f_triggerScene
	 */
	public void setTriggerscene(String triggerscene) {
		this.triggerscene = triggerscene;
	}

	/**
	 * 取走玩家的铜币 t_task.f_takecopper
	 * 
	 * @return the value of t_task.f_takecopper
	 */
	public Integer getTakecopper() {
		return takecopper;
	}

	/**
	 * 取走玩家的铜币 t_task.f_takecopper
	 * 
	 * @param takecopper
	 *            the value for t_task.f_takecopper
	 */
	public void setTakecopper(Integer takecopper) {
		this.takecopper = takecopper;
	}

	/**
	 * 前置任务排序用id(暂时没用) t_task.f_premiseId
	 * 
	 * @return the value of t_task.f_premiseId
	 */
	public Integer getPremiseid() {
		return premiseid;
	}

	/**
	 * 前置任务排序用id(暂时没用) t_task.f_premiseId
	 * 
	 * @param premiseid
	 *            the value for t_task.f_premiseId
	 */
	public void setPremiseid(Integer premiseid) {
		this.premiseid = premiseid;
	}

	/**
	 * 前提任务id t_task.f_premiseTask
	 * 
	 * @return the value of t_task.f_premiseTask
	 */
	public Integer getPremisetask() {
		return premisetask;
	}

	/**
	 * 前提任务id t_task.f_premiseTask
	 * 
	 * @param premisetask
	 *            the value for t_task.f_premiseTask
	 */
	public void setPremisetask(Integer premisetask) {
		this.premisetask = premisetask;
	}

	/**
	 * 工具用于跟踪 插入/修改时间 t_task.f_time
	 * 
	 * @return the value of t_task.f_time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * 工具用于跟踪 插入/修改时间 t_task.f_time
	 * 
	 * @param time
	 *            the value for t_task.f_time
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * 0不扣坐骑1扣坐骑 t_task.f_horseDrop
	 * 
	 * @return the value of t_task.f_horseDrop
	 */
	public Integer getHorsedrop() {
		return horsedrop;
	}

	/**
	 * 0不扣坐骑1扣坐骑 t_task.f_horseDrop
	 * 
	 * @param horsedrop
	 *            the value for t_task.f_horseDrop
	 */
	public void setHorsedrop(Integer horsedrop) {
		this.horsedrop = horsedrop;
	}

	/**
	 * (身上是否有穿戴武器)有没有佩戴武器 t_task.f_needWuqi
	 * 
	 * @return the value of t_task.f_needWuqi
	 */
	public Integer getNeedwuqi() {
		return needwuqi;
	}

	/**
	 * (身上是否有穿戴武器)有没有佩戴武器 t_task.f_needWuqi
	 * 
	 * @param needwuqi
	 *            the value for t_task.f_needWuqi
	 */
	public void setNeedwuqi(Integer needwuqi) {
		this.needwuqi = needwuqi;
	}

	/**
	 * (身上是否有穿戴衣服)有没有佩戴衣服 t_task.f_needDress
	 * 
	 * @return the value of t_task.f_needDress
	 */
	public Integer getNeeddress() {
		return needdress;
	}

	/**
	 * (身上是否有穿戴衣服)有没有佩戴衣服 t_task.f_needDress
	 * 
	 * @param needdress
	 *            the value for t_task.f_needDress
	 */
	public void setNeeddress(Integer needdress) {
		this.needdress = needdress;
	}

	/**
	 * (需要已学习的武功id) 有没有学会武功 t_task.f_needSkill
	 * 
	 * @return the value of t_task.f_needSkill
	 */
	public Integer getNeedskill() {
		return needskill;
	}

	/**
	 * (需要已学习的武功id) 有没有学会武功 t_task.f_needSkill
	 * 
	 * @param needskill
	 *            the value for t_task.f_needSkill
	 */
	public void setNeedskill(Integer needskill) {
		this.needskill = needskill;
	}

	/**
	 * (限定总的武功等级)武功层数是否达到 t_task.f_needWugongGrade
	 * 
	 * @return the value of t_task.f_needWugongGrade
	 */
	public Integer getNeedwugonggrade() {
		return needwugonggrade;
	}

	/**
	 * (限定总的武功等级)武功层数是否达到 t_task.f_needWugongGrade
	 * 
	 * @param needwugonggrade
	 *            the value for t_task.f_needWugongGrade
	 */
	public void setNeedwugonggrade(Integer needwugonggrade) {
		this.needwugonggrade = needwugonggrade;
	}

	/**
	 * 任务目标描述 t_task.f_taskTargetDes
	 * 
	 * @return the value of t_task.f_taskTargetDes
	 */
	public String getTasktargetdes() {
		return tasktargetdes;
	}

	/**
	 * 任务目标描述 t_task.f_taskTargetDes
	 * 
	 * @param tasktargetdes
	 *            the value for t_task.f_taskTargetDes
	 */
	public void setTasktargetdes(String tasktargetdes) {
		this.tasktargetdes = tasktargetdes;
	}

	/**
	 * 判断玩家是否从商城中购买过某个物品（物品可自定义输入） (逗号相隔的道具ID#数量) t_task.f_targetShopping
	 * 
	 * @return the value of t_task.f_targetShopping
	 */
	public String getTargetshopping() {
		return targetshopping;
	}

	/**
	 * 判断玩家是否从商城中购买过某个物品（物品可自定义输入） (逗号相隔的道具ID#数量) t_task.f_targetShopping
	 * 
	 * @param targetshopping
	 *            the value for t_task.f_targetShopping
	 */
	public void setTargetshopping(String targetshopping) {
		this.targetshopping = targetshopping;
	}

	/**
	 * 充值金额(判断玩家是否充值过)(单位元宝) t_task.f_targetRecharge
	 * 
	 * @return the value of t_task.f_targetRecharge
	 */
	public String getTargetrecharge() {
		return targetrecharge;
	}

	/**
	 * 充值金额(判断玩家是否充值过)(单位元宝) t_task.f_targetRecharge
	 * 
	 * @param targetrecharge
	 *            the value for t_task.f_targetRecharge
	 */
	public void setTargetrecharge(String targetrecharge) {
		this.targetrecharge = targetrecharge;
	}

	/**
	 * 判断玩家是否使用过坐骑进攻本功能：将的卢马成功地进阶合成为金甲神牛(1为需进阶0不需进阶) t_task.f_targetMountUpgrade
	 * 
	 * @return the value of t_task.f_targetMountUpgrade
	 */
	public String getTargetmountupgrade() {
		return targetmountupgrade;
	}

	/**
	 * 判断玩家是否使用过坐骑进攻本功能：将的卢马成功地进阶合成为金甲神牛(1为需进阶0不需进阶) t_task.f_targetMountUpgrade
	 * 
	 * @param targetmountupgrade
	 *            the value for t_task.f_targetMountUpgrade
	 */
	public void setTargetmountupgrade(String targetmountupgrade) {
		this.targetmountupgrade = targetmountupgrade;
	}

	/**
	 * 判断玩家当前是否组队，队伍总人数是否>X(格式:"目标队伍人数" 或 "") t_task.f_targetGroup
	 * 
	 * @return the value of t_task.f_targetGroup
	 */
	public String getTargetgroup() {
		return targetgroup;
	}

	/**
	 * 判断玩家当前是否组队，队伍总人数是否>X(格式:"目标队伍人数" 或 "") t_task.f_targetGroup
	 * 
	 * @param targetgroup
	 *            the value for t_task.f_targetGroup
	 */
	public void setTargetgroup(String targetgroup) {
		this.targetgroup = targetgroup;
	}

	/**
	 * 判断玩家当前拥有好友人数的数量是否>X(格式:"目标好友人数" 或 "") t_task.f_targetFriend
	 * 
	 * @return the value of t_task.f_targetFriend
	 */
	public String getTargetfriend() {
		return targetfriend;
	}

	/**
	 * 判断玩家当前拥有好友人数的数量是否>X(格式:"目标好友人数" 或 "") t_task.f_targetFriend
	 * 
	 * @param targetfriend
	 *            the value for t_task.f_targetFriend
	 */
	public void setTargetfriend(String targetfriend) {
		this.targetfriend = targetfriend;
	}

	/**
	 * 判断玩家某个部位是否佩戴了装备ID(格式:"目标部位ID#目标装备ID" 或 "") t_task.f_targetEquip
	 * 
	 * @return the value of t_task.f_targetEquip
	 */
	public String getTargetequip() {
		return targetequip;
	}

	/**
	 * 判断玩家某个部位是否佩戴了装备ID(格式:"目标部位ID#目标装备ID" 或 "") t_task.f_targetEquip
	 * 
	 * @param targetequip
	 *            the value for t_task.f_targetEquip
	 */
	public void setTargetequip(String targetequip) {
		this.targetequip = targetequip;
	}

	/**
	 * 判断玩家某个武功的等级是否大于X(格式:"目标武功ID#目标等级" 或 "") t_task.f_targetSkillLv
	 * 
	 * @return the value of t_task.f_targetSkillLv
	 */
	public String getTargetskilllv() {
		return targetskilllv;
	}

	/**
	 * 判断玩家某个武功的等级是否大于X(格式:"目标武功ID#目标等级" 或 "") t_task.f_targetSkillLv
	 * 
	 * @param targetskilllv
	 *            the value for t_task.f_targetSkillLv
	 */
	public void setTargetskilllv(String targetskilllv) {
		this.targetskilllv = targetskilllv;
	}

	/**
	 * 判断玩家整体武功境界层数是否大于X(格式:"目标等级" 或 "") t_task.f_targetAllSkillLv
	 * 
	 * @return the value of t_task.f_targetAllSkillLv
	 */
	public String getTargetallskilllv() {
		return targetallskilllv;
	}

	/**
	 * 判断玩家整体武功境界层数是否大于X(格式:"目标等级" 或 "") t_task.f_targetAllSkillLv
	 * 
	 * @param targetallskilllv
	 *            the value for t_task.f_targetAllSkillLv
	 */
	public void setTargetallskilllv(String targetallskilllv) {
		this.targetallskilllv = targetallskilllv;
	}

	/**
	 * 判断玩家某个经脉穴位是否冲通(格式:"目标穴位ID" 或 "") t_task.f_targetPoint
	 * 
	 * @return the value of t_task.f_targetPoint
	 */
	public String getTargetpoint() {
		return targetpoint;
	}

	/**
	 * 判断玩家某个经脉穴位是否冲通(格式:"目标穴位ID" 或 "") t_task.f_targetPoint
	 * 
	 * @param targetpoint
	 *            the value for t_task.f_targetPoint
	 */
	public void setTargetpoint(String targetpoint) {
		this.targetpoint = targetpoint;
	}

	/**
	 * 判断玩家某条经脉是否冲通(格式:"目标经脉ID" 或 "") t_task.f_targetChannel
	 * 
	 * @return the value of t_task.f_targetChannel
	 */
	public String getTargetchannel() {
		return targetchannel;
	}

	/**
	 * 判断玩家某条经脉是否冲通(格式:"目标经脉ID" 或 "") t_task.f_targetChannel
	 * 
	 * @param targetchannel
	 *            the value for t_task.f_targetChannel
	 */
	public void setTargetchannel(String targetchannel) {
		this.targetchannel = targetchannel;
	}

	/**
	 * 判断玩家身上是否有某个BUFF-ID(格式:"目标BUFF ID" 或 "") t_task.f_targetBuff
	 * 
	 * @return the value of t_task.f_targetBuff
	 */
	public String getTargetbuff() {
		return targetbuff;
	}

	/**
	 * 判断玩家身上是否有某个BUFF-ID(格式:"目标BUFF ID" 或 "") t_task.f_targetBuff
	 * 
	 * @param targetbuff
	 *            the value for t_task.f_targetBuff
	 */
	public void setTargetbuff(String targetbuff) {
		this.targetbuff = targetbuff;
	}

	/**
	 * 环任务最大上限次数 t_task.f_loopMaxCount
	 * 
	 * @return the value of t_task.f_loopMaxCount
	 */
	public Integer getLoopmaxcount() {
		return loopmaxcount;
	}

	/**
	 * 环任务最大上限次数 t_task.f_loopMaxCount
	 * 
	 * @param loopmaxcount
	 *            the value for t_task.f_loopMaxCount
	 */
	public void setLoopmaxcount(Integer loopmaxcount) {
		this.loopmaxcount = loopmaxcount;
	}

	/**
	 * 奖励暗器(暗器id) t_task.f_hiddenWeapon
	 * 
	 * @return the value of t_task.f_hiddenWeapon
	 */
	public Integer getHiddenweapon() {
		return hiddenweapon;
	}

	/**
	 * 奖励暗器(暗器id) t_task.f_hiddenWeapon
	 * 
	 * @param hiddenweapon
	 *            the value for t_task.f_hiddenWeapon
	 */
	public void setHiddenweapon(Integer hiddenweapon) {
		this.hiddenweapon = hiddenweapon;
	}

	/**
	 * 接任务开始时间 t_task.f_accept_begin_time
	 * 
	 * @return the value of t_task.f_accept_begin_time
	 */
	public Date getAcceptBeginTime() {
		return acceptBeginTime;
	}

	/**
	 * 接任务开始时间 t_task.f_accept_begin_time
	 * 
	 * @param acceptBeginTime
	 *            the value for t_task.f_accept_begin_time
	 */
	public void setAcceptBeginTime(Date acceptBeginTime) {
		this.acceptBeginTime = acceptBeginTime;
	}

	/**
	 * 交任务结束时间 t_task.f_complete_end_time
	 * 
	 * @return the value of t_task.f_complete_end_time
	 */
	public Date getCompleteEndTime() {
		return completeEndTime;
	}

	/**
	 * 交任务结束时间 t_task.f_complete_end_time
	 * 
	 * @param completeEndTime
	 *            the value for t_task.f_complete_end_time
	 */
	public void setCompleteEndTime(Date completeEndTime) {
		this.completeEndTime = completeEndTime;
	}

	/**
	 * 任务名称国际化 t_task.f_name_i18n
	 * 
	 * @return the value of t_task.f_name_i18n
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 任务名称国际化 t_task.f_name_i18n
	 * 
	 * @param nameI18n
	 *            the value for t_task.f_name_i18n
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}

	/**
	 * 未完成任务描述际化国 t_task.f_unEndDes_i18n
	 * 
	 * @return the value of t_task.f_unEndDes_i18n
	 */
	public String getUnenddesI18n() {
		return unenddesI18n;
	}

	/**
	 * 未完成任务描述际化国 t_task.f_unEndDes_i18n
	 * 
	 * @param unenddesI18n
	 *            the value for t_task.f_unEndDes_i18n
	 */
	public void setUnenddesI18n(String unenddesI18n) {
		this.unenddesI18n = unenddesI18n;
	}

	/**
	 * 完成任务描述国际化 t_task.f_endDes_i18n
	 * 
	 * @return the value of t_task.f_endDes_i18n
	 */
	public String getEnddesI18n() {
		return enddesI18n;
	}

	/**
	 * 完成任务描述国际化 t_task.f_endDes_i18n
	 * 
	 * @param enddesI18n
	 *            the value for t_task.f_endDes_i18n
	 */
	public void setEnddesI18n(String enddesI18n) {
		this.enddesI18n = enddesI18n;
	}

	/**
	 * 任务描述 t_task.f_publishDes_i18n
	 * 
	 * @return the value of t_task.f_publishDes_i18n
	 */
	public String getPublishdesI18n() {
		return publishdesI18n;
	}

	/**
	 * 任务描述 t_task.f_publishDes_i18n
	 * 
	 * @param publishdesI18n
	 *            the value for t_task.f_publishDes_i18n
	 */
	public void setPublishdesI18n(String publishdesI18n) {
		this.publishdesI18n = publishdesI18n;
	}

	/**
	 * 任务目标描述国际化 t_task.f_taskTargetDes_i18n
	 * 
	 * @return the value of t_task.f_taskTargetDes_i18n
	 */
	public String getTasktargetdesI18n() {
		return tasktargetdesI18n;
	}

	/**
	 * 任务目标描述国际化 t_task.f_taskTargetDes_i18n
	 * 
	 * @param tasktargetdesI18n
	 *            the value for t_task.f_taskTargetDes_i18n
	 */
	public void setTasktargetdesI18n(String tasktargetdesI18n) {
		this.tasktargetdesI18n = tasktargetdesI18n;
	}

	/**
	 * 是否奖历弓箭0否 1是 t_task.f_bow
	 * 
	 * @return the value of t_task.f_bow
	 */
	public Integer getBow() {
		return bow;
	}

	/**
	 * 是否奖历弓箭0否 1是 t_task.f_bow
	 * 
	 * @param bow
	 *            the value for t_task.f_bow
	 */
	public void setBow(Integer bow) {
		this.bow = bow;
	}

	/**
	 * 是否奖历丹田0否 1是 t_task.f_dantian
	 * 
	 * @return the value of t_task.f_dantian
	 */
	public Integer getDantian() {
		return dantian;
	}

	/**
	 * 是否奖历丹田0否 1是 t_task.f_dantian
	 * 
	 * @param dantian
	 *            the value for t_task.f_dantian
	 */
	public void setDantian(Integer dantian) {
		this.dantian = dantian;
	}

	public boolean isLoopTask() {
		if (getType() == Day_TASK || getType() == Day_YaBiao_TASK || getType() == Week_TASK || getType() == Upgrade_TASK) {
			return true;
		}
		return false;
	}

	public boolean isWeekOrDayLoopTask() {
		if (getType() == Day_TASK || getType() == Week_TASK) {
			return true;
		}
		return false;
	}

	/**
	 * 任务上限
	 */
	public static final int TASKLIMIT_UP = 30;
	/**
	 * 主线任务
	 */
	public static final int MAIN_TASK = 1;
	/**
	 * 支线任务
	 */
	public static final int BRANCH_TASK = 2;
	/**
	 * 每日环任务
	 */
	public static final int Day_TASK = 3;
	/**
	 * 每日押镖任务
	 */
	public static final int Day_YaBiao_TASK = 4;
	/**
	 * 周环任务
	 */
	public static final int Week_TASK = 5;
	/**
	 * 练级环任务
	 */
	public static final int Upgrade_TASK = 6;

	// // 功能类型:0复合任务,1刺杀怪物 2寻找NPC 3收集 4到达指定区域 5传送 6采集 7触发动作 8捕获坐骑(用于任务跟踪)
	public static final int KILL_MONSTER = 1;
	public static final int SEARCH_NPC = 2;
	public static final int SHOUJI = 3;
	public static final int QUYU = 4;
	public static final int CHUANSONG = 5;
	public static final int CAIJI = 6;
	public static final int DONGZUO = 7;
	public static final int FUHE = 0;
	public static final int BUHUO = 8;
	public static final int SHOPPING = 9;
	public static final int YUANBAO = 51;
	public static final int MOUNTUPGRADE = 52;

	public static final int Day_Task_Limit = 20;// 日环任务上限

	public static final int Day_Task_YaBiao_Limit = 5;// 日环任务上限(押镖)

	public static final int Week_Task_Limit = 200;// 周环任务上限
}
