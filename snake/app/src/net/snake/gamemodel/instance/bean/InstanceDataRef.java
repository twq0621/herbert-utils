package net.snake.gamemodel.instance.bean;

import java.util.ArrayList;
import java.util.List;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.task.bean.Task;
import net.snake.gamemodel.task.persistence.TaskManager;
import net.snake.ibatis.IbatisEntity;

public class InstanceDataRef implements IbatisEntity {

	/**
	 * 副本编号 t_instance.instance_model_id
	 */
	private Integer instanceModelId;
	/**
	 * 副本名称 t_instance.instance_name
	 */
	private String instanceName;
	/**
	 * 副本类型 t_instance.instance_type
	 */
	private Byte instanceType;
	/**
	 * 生命周期 以秒计 t_instance.lifecycle
	 */
	private Integer lifecycle;
	/**
	 * 玩家人数上限 t_instance.population_high_limit
	 */
	private Integer populationHighLimit;
	/**
	 * 玩家人数下限 t_instance.population_lower_limit
	 */
	private Integer populationLowerLimit;
	/**
	 * 等级上限 t_instance.level_high_limit
	 */
	private Integer levelHighLimit;
	/**
	 * 等级下限 t_instance.level_lower_limit
	 */
	private Integer levelLowerLimit;
	/**
	 * 时间表达式格式 [年][月][日][时间段];[年][月][日][时间段];.... 用";"隔开并列的表达式, 每个"[]"里都可以用"*"表示不对该日期段进行限制,用","表示并列,用"-"表示从哪儿到哪儿 t_instance.open_time_limite
	 */
	private String openTimeLimite;
	/**
	 * 副本是否可用 1可用 0不可用 t_instance.enable
	 */
	private Integer enable;
	/**
	 * 每日允许进入次数 t_instance.enter_count_limite
	 */
	private Integer enterCountLimite;
	/**
	 * 副本开启时间描述 t_instance.instanceOpenTimeDescrip
	 */
	private String instanceopentimedescrip;
	/**
	 * 每周显示控制 t_instance.weekdayshow
	 */
	private String weekdayshow;
	/**
	 * 队伍人数0为不组队 t_instance.groupsum
	 */
	private Integer groupsum;
	/**
	 * 完成次数 t_instance.maxcount
	 */
	private Integer maxcount;
	/**
	 * 副本描述 t_instance.descpt
	 */
	private String descpt;
	/**
	 * 奖励星级 t_instance.f_rewardgrade
	 */
	private Integer fRewardgrade;
	/**
	 * 是否组队条件限制 0混合/1单人/2组队 t_instance.team_limite
	 */
	private Integer teamLimite;
	/**
	 * 任务条件限制 当前是否进行这些任务 任务id，任务id t_instance.task_limite
	 */
	private String taskLimite;
	/**
	 * 物品条件限制 物品id*数量，物品id*数量 t_instance.good_limite
	 */
	private String goodLimite;
	/**
	 * 是否允许复活 0不允许/1允许 t_instance.revive
	 */
	private Integer revive;
	/**
	 * npcID t_instance.transNpcID
	 */
	private Integer transnpcid;
	/**
	 * 副本令进入次数限制 t_instance.fubenling_limite
	 */
	private Integer fubenlingLimite;
	/**
	 * 奖励物 结构 物品id*数量;物品id*数量 t_instance.f_reward_good
	 */
	private String fRewardGood;
	/**
	 * 次数单位描述 t_instance.f_unit
	 */
	private String fUnit;
	/**
	 * 副本名称国际化 t_instance.instance_name_i18n
	 */
	private String instanceNameI18n;
	/**
	 * 副本开启时间描述国际化 t_instance.instanceOpenTimeDescrip_i18n
	 */
	private String instanceopentimedescripI18n;
	/**
	 * 副本描述国际化 t_instance.descpt_i18n
	 */
	private String descptI18n;
	/**
	 * 次数单位描述国际化 t_instance.f_unit_i18n
	 */
	private String fUnitI18n;

	/**
	 * 副本编号 t_instance.instance_model_id
	 * 
	 * @return the value of t_instance.instance_model_id
	 */
	public Integer getInstanceModelId() {
		return instanceModelId;
	}

	/**
	 * 副本编号 t_instance.instance_model_id
	 * 
	 * @param instanceModelId
	 *            the value for t_instance.instance_model_id
	 */
	public void setInstanceModelId(Integer instanceModelId) {
		this.instanceModelId = instanceModelId;
	}

	/**
	 * 副本名称 t_instance.instance_name
	 * 
	 * @return the value of t_instance.instance_name
	 */
	public String getInstanceName() {
		return instanceName;
	}

	/**
	 * 副本名称 t_instance.instance_name
	 * 
	 * @param instanceName
	 *            the value for t_instance.instance_name
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	/**
	 * 副本类型 t_instance.instance_type
	 * 
	 * @return the value of t_instance.instance_type
	 */
	public Byte getInstanceType() {
		return instanceType;
	}

	/**
	 * 副本类型 t_instance.instance_type
	 * 
	 * @param instanceType
	 *            the value for t_instance.instance_type
	 */
	public void setInstanceType(Byte instanceType) {
		this.instanceType = instanceType;
	}

	/**
	 * 生命周期 以秒计 t_instance.lifecycle
	 * 
	 * @return the value of t_instance.lifecycle
	 */
	public Integer getLifecycle() {
		return lifecycle;
	}

	/**
	 * 生命周期 以秒计 t_instance.lifecycle
	 * 
	 * @param lifecycle
	 *            the value for t_instance.lifecycle
	 */
	public void setLifecycle(Integer lifecycle) {
		this.lifecycle = lifecycle;
	}

	/**
	 * 玩家人数上限 t_instance.population_high_limit
	 * 
	 * @return the value of t_instance.population_high_limit
	 */
	public Integer getPopulationHighLimit() {
		return populationHighLimit;
	}

	/**
	 * 玩家人数上限 t_instance.population_high_limit
	 * 
	 * @param populationHighLimit
	 *            the value for t_instance.population_high_limit
	 */
	public void setPopulationHighLimit(Integer populationHighLimit) {
		this.populationHighLimit = populationHighLimit;
	}

	/**
	 * 玩家人数下限 t_instance.population_lower_limit
	 * 
	 * @return the value of t_instance.population_lower_limit
	 */
	public Integer getPopulationLowerLimit() {
		return populationLowerLimit;
	}

	/**
	 * 玩家人数下限 t_instance.population_lower_limit
	 * 
	 * @param populationLowerLimit
	 *            the value for t_instance.population_lower_limit
	 */
	public void setPopulationLowerLimit(Integer populationLowerLimit) {
		this.populationLowerLimit = populationLowerLimit;
	}

	/**
	 * 等级上限 t_instance.level_high_limit
	 * 
	 * @return the value of t_instance.level_high_limit
	 */
	public Integer getLevelHighLimit() {
		return levelHighLimit;
	}

	/**
	 * 等级上限 t_instance.level_high_limit
	 * 
	 * @param levelHighLimit
	 *            the value for t_instance.level_high_limit
	 */
	public void setLevelHighLimit(Integer levelHighLimit) {
		this.levelHighLimit = levelHighLimit;
	}

	/**
	 * 等级下限 t_instance.level_lower_limit
	 * 
	 * @return the value of t_instance.level_lower_limit
	 */
	public Integer getLevelLowerLimit() {
		return levelLowerLimit;
	}

	/**
	 * 等级下限 t_instance.level_lower_limit
	 * 
	 * @param levelLowerLimit
	 *            the value for t_instance.level_lower_limit
	 */
	public void setLevelLowerLimit(Integer levelLowerLimit) {
		this.levelLowerLimit = levelLowerLimit;
	}

	/**
	 * 时间表达式格式 [年][月][日][时间段];[年][月][日][时间段];.... 用";"隔开并列的表达式, 每个"[]"里都可以用"*"表示不对该日期段进行限制,用","表示并列,用"-"表示从哪儿到哪儿 t_instance.open_time_limite
	 * 
	 * @return the value of t_instance.open_time_limite
	 */
	public String getOpenTimeLimite() {
		return openTimeLimite;
	}

	/**
	 * 时间表达式格式 [年][月][日][时间段];[年][月][日][时间段];.... 用";"隔开并列的表达式, 每个"[]"里都可以用"*"表示不对该日期段进行限制,用","表示并列,用"-"表示从哪儿到哪儿 t_instance.open_time_limite
	 * 
	 * @param openTimeLimite
	 *            the value for t_instance.open_time_limite
	 */
	public void setOpenTimeLimite(String openTimeLimite) {
		this.openTimeLimite = openTimeLimite;
	}

	/**
	 * 副本是否可用 1可用 0不可用 t_instance.enable
	 * 
	 * @return the value of t_instance.enable
	 */
	public Integer getEnable() {
		return enable;
	}

	/**
	 * 副本是否可用 1可用 0不可用 t_instance.enable
	 * 
	 * @param enable
	 *            the value for t_instance.enable
	 */
	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	/**
	 * 每日允许进入次数 t_instance.enter_count_limite
	 * 
	 * @return the value of t_instance.enter_count_limite
	 */
	public Integer getEnterCountLimite() {
		return enterCountLimite;
	}

	/**
	 * 每日允许进入次数 t_instance.enter_count_limite
	 * 
	 * @param enterCountLimite
	 *            the value for t_instance.enter_count_limite
	 */
	public void setEnterCountLimite(Integer enterCountLimite) {
		this.enterCountLimite = enterCountLimite;
	}

	/**
	 * 副本开启时间描述 t_instance.instanceOpenTimeDescrip
	 * 
	 * @return the value of t_instance.instanceOpenTimeDescrip
	 */
	public String getInstanceopentimedescrip() {
		return instanceopentimedescrip;
	}

	/**
	 * 副本开启时间描述 t_instance.instanceOpenTimeDescrip
	 * 
	 * @param instanceopentimedescrip
	 *            the value for t_instance.instanceOpenTimeDescrip
	 */
	public void setInstanceopentimedescrip(String instanceopentimedescrip) {
		this.instanceopentimedescrip = instanceopentimedescrip;
	}

	/**
	 * 每周显示控制 t_instance.weekdayshow
	 * 
	 * @return the value of t_instance.weekdayshow
	 */
	public String getWeekdayshow() {
		return weekdayshow;
	}

	/**
	 * 每周显示控制 t_instance.weekdayshow
	 * 
	 * @param weekdayshow
	 *            the value for t_instance.weekdayshow
	 */
	public void setWeekdayshow(String weekdayshow) {
		this.weekdayshow = weekdayshow;
	}

	/**
	 * 队伍人数0为不组队 t_instance.groupsum
	 * 
	 * @return the value of t_instance.groupsum
	 */
	public Integer getGroupsum() {
		return groupsum;
	}

	/**
	 * 队伍人数0为不组队 t_instance.groupsum
	 * 
	 * @param groupsum
	 *            the value for t_instance.groupsum
	 */
	public void setGroupsum(Integer groupsum) {
		this.groupsum = groupsum;
	}

	/**
	 * 完成次数 t_instance.maxcount
	 * 
	 * @return the value of t_instance.maxcount
	 */
	public Integer getMaxcount() {
		return maxcount;
	}

	/**
	 * 完成次数 t_instance.maxcount
	 * 
	 * @param maxcount
	 *            the value for t_instance.maxcount
	 */
	public void setMaxcount(Integer maxcount) {
		this.maxcount = maxcount;
	}

	/**
	 * 副本描述 t_instance.descpt
	 * 
	 * @return the value of t_instance.descpt
	 */
	public String getDescpt() {
		return descpt;
	}

	/**
	 * 副本描述 t_instance.descpt
	 * 
	 * @param descpt
	 *            the value for t_instance.descpt
	 */
	public void setDescpt(String descpt) {
		this.descpt = descpt;
	}

	/**
	 * 奖励星级 t_instance.f_rewardgrade
	 * 
	 * @return the value of t_instance.f_rewardgrade
	 */
	public Integer getfRewardgrade() {
		return fRewardgrade;
	}

	/**
	 * 奖励星级 t_instance.f_rewardgrade
	 * 
	 * @param fRewardgrade
	 *            the value for t_instance.f_rewardgrade
	 */
	public void setfRewardgrade(Integer fRewardgrade) {
		this.fRewardgrade = fRewardgrade;
	}

	/**
	 * 是否组队条件限制 0混合/1单人/2组队 t_instance.team_limite
	 * 
	 * @return the value of t_instance.team_limite
	 */
	public Integer getTeamLimite() {
		return teamLimite;
	}

	/**
	 * 是否组队条件限制 0混合/1单人/2组队 t_instance.team_limite
	 * 
	 * @param teamLimite
	 *            the value for t_instance.team_limite
	 */
	public void setTeamLimite(Integer teamLimite) {
		this.teamLimite = teamLimite;
	}

	/**
	 * 任务条件限制 当前是否进行这些任务 任务id，任务id t_instance.task_limite
	 * 
	 * @return the value of t_instance.task_limite
	 */
	public String getTaskLimite() {
		return taskLimite;
	}

	/**
	 * 任务条件限制 当前是否进行这些任务 任务id，任务id t_instance.task_limite
	 * 
	 * @param taskLimite
	 *            the value for t_instance.task_limite
	 */
	public void setTaskLimite(String taskLimite) {
		this.taskLimite = taskLimite;
	}

	/**
	 * 物品条件限制 物品id*数量，物品id*数量 t_instance.good_limite
	 * 
	 * @return the value of t_instance.good_limite
	 */
	public String getGoodLimite() {
		return goodLimite;
	}

	/**
	 * 物品条件限制 物品id*数量，物品id*数量 t_instance.good_limite
	 * 
	 * @param goodLimite
	 *            the value for t_instance.good_limite
	 */
	public void setGoodLimite(String goodLimite) {
		this.goodLimite = goodLimite;
	}

	/**
	 * 是否允许复活 0不允许/1允许 t_instance.revive
	 * 
	 * @return the value of t_instance.revive
	 */
	public Integer getRevive() {
		return revive;
	}

	/**
	 * 是否允许复活 0不允许/1允许 t_instance.revive
	 * 
	 * @param revive
	 *            the value for t_instance.revive
	 */
	public void setRevive(Integer revive) {
		this.revive = revive;
	}

	/**
	 * npcID t_instance.transNpcID
	 * 
	 * @return the value of t_instance.transNpcID
	 */
	public Integer getTransnpcid() {
		return transnpcid;
	}

	/**
	 * npcID t_instance.transNpcID
	 * 
	 * @param transnpcid
	 *            the value for t_instance.transNpcID
	 */
	public void setTransnpcid(Integer transnpcid) {
		this.transnpcid = transnpcid;
	}

	/**
	 * 副本令进入次数限制 t_instance.fubenling_limite
	 * 
	 * @return the value of t_instance.fubenling_limite
	 */
	public Integer getFubenlingLimite() {
		return fubenlingLimite;
	}

	/**
	 * 副本令进入次数限制 t_instance.fubenling_limite
	 * 
	 * @param fubenlingLimite
	 *            the value for t_instance.fubenling_limite
	 */
	public void setFubenlingLimite(Integer fubenlingLimite) {
		this.fubenlingLimite = fubenlingLimite;
	}

	/**
	 * 奖励物 结构 物品id*数量;物品id*数量 t_instance.f_reward_good
	 * 
	 * @return the value of t_instance.f_reward_good
	 */
	public String getfRewardGood() {
		return fRewardGood;
	}

	/**
	 * 奖励物 结构 物品id*数量;物品id*数量 t_instance.f_reward_good
	 * 
	 * @param fRewardGood
	 *            the value for t_instance.f_reward_good
	 */
	public void setfRewardGood(String fRewardGood) {
		this.fRewardGood = fRewardGood;
	}

	/**
	 * 次数单位描述 t_instance.f_unit
	 * 
	 * @return the value of t_instance.f_unit
	 */
	public String getfUnit() {
		return fUnit;
	}

	/**
	 * 次数单位描述 t_instance.f_unit
	 * 
	 * @param fUnit
	 *            the value for t_instance.f_unit
	 */
	public void setfUnit(String fUnit) {
		this.fUnit = fUnit;
	}

	/**
	 * 副本名称国际化 t_instance.instance_name_i18n
	 * 
	 * @return the value of t_instance.instance_name_i18n
	 */
	public String getInstanceNameI18n() {
		return instanceNameI18n;
	}

	/**
	 * 副本名称国际化 t_instance.instance_name_i18n
	 * 
	 * @param instanceNameI18n
	 *            the value for t_instance.instance_name_i18n
	 */
	public void setInstanceNameI18n(String instanceNameI18n) {
		this.instanceNameI18n = instanceNameI18n;
	}

	/**
	 * 副本开启时间描述国际化 t_instance.instanceOpenTimeDescrip_i18n
	 * 
	 * @return the value of t_instance.instanceOpenTimeDescrip_i18n
	 */
	public String getInstanceopentimedescripI18n() {
		return instanceopentimedescripI18n;
	}

	/**
	 * 副本开启时间描述国际化 t_instance.instanceOpenTimeDescrip_i18n
	 * 
	 * @param instanceopentimedescripI18n
	 *            the value for t_instance.instanceOpenTimeDescrip_i18n
	 */
	public void setInstanceopentimedescripI18n(String instanceopentimedescripI18n) {
		this.instanceopentimedescripI18n = instanceopentimedescripI18n;
	}

	/**
	 * 副本描述国际化 t_instance.descpt_i18n
	 * 
	 * @return the value of t_instance.descpt_i18n
	 */
	public String getDescptI18n() {
		return descptI18n;
	}

	/**
	 * 副本描述国际化 t_instance.descpt_i18n
	 * 
	 * @param descptI18n
	 *            the value for t_instance.descpt_i18n
	 */
	public void setDescptI18n(String descptI18n) {
		this.descptI18n = descptI18n;
	}

	/**
	 * 次数单位描述国际化 t_instance.f_unit_i18n
	 * 
	 * @return the value of t_instance.f_unit_i18n
	 */
	public String getfUnitI18n() {
		return fUnitI18n;
	}

	/**
	 * 次数单位描述国际化 t_instance.f_unit_i18n
	 * 
	 * @param fUnitI18n
	 *            the value for t_instance.f_unit_i18n
	 */
	public void setfUnitI18n(String fUnitI18n) {
		this.fUnitI18n = fUnitI18n;
	}

	private List<CharacterGoods> goodList;
	private List<Task> taskList;

	public void init() {
		if (goodLimite != null && !goodLimite.equals("")) {
			String[] goods = goodLimite.split(",");
			goodList = new ArrayList<CharacterGoods>();
			GoodmodelManager gmManager = GoodmodelManager.getInstance();
			for (int i = 0; i < goods.length; i++) {
				String[] good = goods[i].split("*");
				Integer goodId = Integer.parseInt(good[0]);
				Integer count = Integer.parseInt(good[1]);
				Goodmodel gm = gmManager.get(goodId);
				if (gm != null) {
					CharacterGoods cg = CharacterGoods.createCharacterGoods(count, gm, 0, 0);
					cg.setGoodmodelId(goodId);
					goodList.add(cg);
				}
			}
		}
		if (taskLimite != null && !taskLimite.equals("")) {
			String[] tasks = taskLimite.split(",");
			TaskManager taskManager = TaskManager.getInstance();
			taskList = new ArrayList<Task>();
			for (int i = 0; i < tasks.length; i++) {
				int taskId = Integer.parseInt(tasks[i]);
				Task task = taskManager.get(taskId);
				if (task != null) {
					taskList.add(task);
				}
			}
		}
		if (this.fRewardGood != null && !this.fRewardGood.equals("")) {
			String[] goods = this.fRewardGood.split(";");
			rewardGoodList = new ArrayList<CharacterGoods>();
			GoodmodelManager gmManager = GoodmodelManager.getInstance();
			for (int i = 0; i < goods.length; i++) {
				String[] good = goods[i].split("[*]");
				Integer goodId = Integer.parseInt(good[0]);
				Integer count = Integer.parseInt(good[1]);
				Goodmodel gm = gmManager.get(goodId);
				if (gm != null) {
					CharacterGoods cg = CharacterGoods.createCharacterGoods(count, gm, 0, 0);
					cg.setGoodmodelId(goodId);
					cg.setBind(CommonUseNumber.byte1);
					rewardGoodList.add(cg);
				}
			}
		}
	}

	public List<CharacterGoods> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<CharacterGoods> goodList) {
		this.goodList = goodList;
	}

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	private List<CharacterGoods> rewardGoodList = new ArrayList<CharacterGoods>();

	public List<CharacterGoods> getRewardGoodList() {
		return rewardGoodList;
	}

	public void setRewardGoodList(List<CharacterGoods> rewardGoodList) {
		this.rewardGoodList = rewardGoodList;
	}

}
