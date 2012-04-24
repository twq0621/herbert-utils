package net.snake.gamemodel.activities.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.ai.util.ArithmeticUtils;
import net.snake.commons.TimeExpression;
import net.snake.commons.GenerateProbability;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.ibatis.IbatisEntity;

public class XianshiActivityReward implements IbatisEntity {
	private static final Logger logger = Logger.getLogger(XianshiActivityReward.class);
	/**
	 * 200起进行配置 t_xianshi_activity_reward.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 关联限时f_xianshi_activity表id t_xianshi_activity_reward.f_xianshi_activity_id
	 * 
	 */
	private Integer xianshiActivityId;
	/**
	 * 活动排序 t_xianshi_activity_reward.f_order
	 * 
	 */
	private Integer order;
	/**
	 * 活动类型 3道具-兑换道具 （击杀怪物或交易得到道具，兑换礼金） t_xianshi_activity_reward.f_type
	 * 
	 */
	private Integer type;
	/**
	 * 参与活动时间限制条件 填写格式[2011][4][22-23][*];[2011][4][22-23][*] t_xianshi_activity_reward.f_time_limit
	 * 
	 */
	private String timeLimit;
	/**
	 * 充值人民币数量限制条件 领奖基本单位 对应类别 充值1 t_xianshi_activity_reward.f_monney_limit
	 * 
	 */
	private Integer monneyLimit;
	/**
	 * 道具兑换奖励限制条件格式 道具id*道具数量（目前只支持单一道具兑换） 对应类别 道具兑换礼金 道具兑换道具 t_xianshi_activity_reward.f_good_limite
	 * 
	 */
	private String goodLimite;
	/**
	 * 奖励物品 填写格式：goodid*count;goodid*count t_xianshi_activity_reward.f_reward_goods
	 * 
	 */
	private String rewardGoods;
	/**
	 * 每次获得的奖励物品的最大上限 t_xianshi_activity_reward.f_reward_good_max_count
	 * 
	 */
	private Integer rewardGoodMaxCount;
	/**
	 * 奖励礼金 t_xianshi_activity_reward.f_reward_lijin
	 * 
	 */
	private Integer rewardLijin;
	/**
	 * 充值开始时间 填写格式 1998-01-02 11:11:11 t_xianshi_activity_reward.f_start_time
	 * 
	 */
	private String startTime;
	/**
	 * 充值结束时间 填写格式 如： 1988-01-02 11:11:12 t_xianshi_activity_reward.f_end_time
	 * 
	 */
	private String endTime;
	/**
	 * 本子类活动描述 t_xianshi_activity_reward.f_desc
	 * 
	 */
	private String desc;
	/**
	 * 添加扩展内容显示 t_xianshi_activity_reward.f_kuozhan_desc
	 * 
	 */
	private String kuozhanDesc;
	/**
	 * 道具 描述类 道具id t_xianshi_activity_reward.f_view_good
	 * 
	 */
	private Integer viewGood;
	/**
	 * 查询用备注，不要重名 t_xianshi_activity_reward.f_remark
	 * 
	 */
	private String remark;
	/**
	 * 商城限购物品以及限购数量 goodId*count t_xianshi_activity_reward.f_shop_limit_good
	 * 
	 */
	private String shopLimitGood;
	/**
	 * 添加扩展内容显示国际化 t_xianshi_activity_reward.f_kuozhan_desc_i18n
	 * 
	 */
	private String kuozhanDescI18n;
	/**
	 * 查询用备注，不要重名国际化 t_xianshi_activity_reward.f_remark_i18n
	 * 
	 */
	private String remarkI18n;
	/**
	 * 奖励物品失效时间 null表示无失效时间/yyyy-mm-dd hh:mm:ss格式表示失效时间 t_xianshi_activity_reward.f_reward_good_losttime
	 * 
	 */
	private String rewardGoodLosttime;
	/**
	 * 坐骑种类限制（关联坐骑表kind字段对应） t_xianshi_activity_reward.f_horse_limit
	 * 
	 */
	private Integer horseLimit;
	/**
	 * t_xianshi_activity_reward.f_desc_i18n
	 * 
	 */
	private String descI18n;

	/**
	 * 200起进行配置 t_xianshi_activity_reward.f_id
	 * 
	 * @return the value of t_xianshi_activity_reward.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 200起进行配置 t_xianshi_activity_reward.f_id
	 * 
	 * @param id
	 *            the value for t_xianshi_activity_reward.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 关联限时f_xianshi_activity表id t_xianshi_activity_reward.f_xianshi_activity_id
	 * 
	 * @return the value of t_xianshi_activity_reward.f_xianshi_activity_id
	 */
	public Integer getXianshiActivityId() {
		return xianshiActivityId;
	}

	/**
	 * 关联限时f_xianshi_activity表id t_xianshi_activity_reward.f_xianshi_activity_id
	 * 
	 * @param xianshiActivityId
	 *            the value for t_xianshi_activity_reward.f_xianshi_activity_id
	 */
	public void setXianshiActivityId(Integer xianshiActivityId) {
		this.xianshiActivityId = xianshiActivityId;
	}

	/**
	 * 活动排序 t_xianshi_activity_reward.f_order
	 * 
	 * @return the value of t_xianshi_activity_reward.f_order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * 活动排序 t_xianshi_activity_reward.f_order
	 * 
	 * @param order
	 *            the value for t_xianshi_activity_reward.f_order
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * 活动类型 3道具-兑换道具 （击杀怪物或交易得到道具，兑换礼金） t_xianshi_activity_reward.f_type
	 * 
	 * @return the value of t_xianshi_activity_reward.f_type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 活动类型 3道具-兑换道具 （击杀怪物或交易得到道具，兑换礼金） t_xianshi_activity_reward.f_type
	 * 
	 * @param type
	 *            the value for t_xianshi_activity_reward.f_type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 参与活动时间限制条件 填写格式[2011][4][22-23][*];[2011][4][22-23][*] t_xianshi_activity_reward.f_time_limit
	 * 
	 * @return the value of t_xianshi_activity_reward.f_time_limit
	 */
	public String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 参与活动时间限制条件 填写格式[2011][4][22-23][*];[2011][4][22-23][*] t_xianshi_activity_reward.f_time_limit
	 * 
	 * @param timeLimit
	 *            the value for t_xianshi_activity_reward.f_time_limit
	 */
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 充值人民币数量限制条件 领奖基本单位 对应类别 充值1 t_xianshi_activity_reward.f_monney_limit
	 * 
	 * @return the value of t_xianshi_activity_reward.f_monney_limit
	 */
	public Integer getMonneyLimit() {
		return monneyLimit;
	}

	/**
	 * 充值人民币数量限制条件 领奖基本单位 对应类别 充值1 t_xianshi_activity_reward.f_monney_limit
	 * 
	 * @param monneyLimit
	 *            the value for t_xianshi_activity_reward.f_monney_limit
	 */
	public void setMonneyLimit(Integer monneyLimit) {
		this.monneyLimit = monneyLimit;
	}

	/**
	 * 道具兑换奖励限制条件格式 道具id*道具数量（目前只支持单一道具兑换） 对应类别 道具兑换礼金 道具兑换道具 t_xianshi_activity_reward.f_good_limite
	 * 
	 * @return the value of t_xianshi_activity_reward.f_good_limite
	 */
	public String getGoodLimite() {
		return goodLimite;
	}

	/**
	 * 道具兑换奖励限制条件格式 道具id*道具数量（目前只支持单一道具兑换） 对应类别 道具兑换礼金 道具兑换道具 t_xianshi_activity_reward.f_good_limite
	 * 
	 * @param goodLimite
	 *            the value for t_xianshi_activity_reward.f_good_limite
	 */
	public void setGoodLimite(String goodLimite) {
		this.goodLimite = goodLimite;
	}

	/**
	 * 奖励物品 填写格式：goodid*count;goodid*count t_xianshi_activity_reward.f_reward_goods
	 * 
	 * @return the value of t_xianshi_activity_reward.f_reward_goods
	 */
	public String getRewardGoods() {
		return rewardGoods;
	}

	/**
	 * 奖励物品 填写格式：goodid*count;goodid*count t_xianshi_activity_reward.f_reward_goods
	 * 
	 * @param rewardGoods
	 *            the value for t_xianshi_activity_reward.f_reward_goods
	 */
	public void setRewardGoods(String rewardGoods) {
		this.rewardGoods = rewardGoods;
	}

	/**
	 * 每次获得的奖励物品的最大上限 t_xianshi_activity_reward.f_reward_good_max_count
	 * 
	 * @return the value of t_xianshi_activity_reward.f_reward_good_max_count
	 */
	public Integer getRewardGoodMaxCount() {
		return rewardGoodMaxCount;
	}

	/**
	 * 每次获得的奖励物品的最大上限 t_xianshi_activity_reward.f_reward_good_max_count
	 * 
	 * @param rewardGoodMaxCount
	 *            the value for t_xianshi_activity_reward.f_reward_good_max_count
	 */
	public void setRewardGoodMaxCount(Integer rewardGoodMaxCount) {
		this.rewardGoodMaxCount = rewardGoodMaxCount;
	}

	/**
	 * 奖励礼金 t_xianshi_activity_reward.f_reward_lijin
	 * 
	 * @return the value of t_xianshi_activity_reward.f_reward_lijin
	 */
	public Integer getRewardLijin() {
		return rewardLijin;
	}

	/**
	 * 奖励礼金 t_xianshi_activity_reward.f_reward_lijin
	 * 
	 * @param rewardLijin
	 *            the value for t_xianshi_activity_reward.f_reward_lijin
	 */
	public void setRewardLijin(Integer rewardLijin) {
		this.rewardLijin = rewardLijin;
	}

	/**
	 * 充值开始时间 填写格式 1998-01-02 11:11:11 t_xianshi_activity_reward.f_start_time
	 * 
	 * @return the value of t_xianshi_activity_reward.f_start_time
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 充值开始时间 填写格式 1998-01-02 11:11:11 t_xianshi_activity_reward.f_start_time
	 * 
	 * @param startTime
	 *            the value for t_xianshi_activity_reward.f_start_time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 充值结束时间 填写格式 如： 1988-01-02 11:11:12 t_xianshi_activity_reward.f_end_time
	 * 
	 * @return the value of t_xianshi_activity_reward.f_end_time
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 充值结束时间 填写格式 如： 1988-01-02 11:11:12 t_xianshi_activity_reward.f_end_time
	 * 
	 * @param endTime
	 *            the value for t_xianshi_activity_reward.f_end_time
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 本子类活动描述 t_xianshi_activity_reward.f_desc
	 * 
	 * @return the value of t_xianshi_activity_reward.f_desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 本子类活动描述 t_xianshi_activity_reward.f_desc
	 * 
	 * @param desc
	 *            the value for t_xianshi_activity_reward.f_desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 添加扩展内容显示 t_xianshi_activity_reward.f_kuozhan_desc
	 * 
	 * @return the value of t_xianshi_activity_reward.f_kuozhan_desc
	 */
	public String getKuozhanDesc() {
		return kuozhanDesc;
	}

	/**
	 * 添加扩展内容显示 t_xianshi_activity_reward.f_kuozhan_desc
	 * 
	 * @param kuozhanDesc
	 *            the value for t_xianshi_activity_reward.f_kuozhan_desc
	 */
	public void setKuozhanDesc(String kuozhanDesc) {
		this.kuozhanDesc = kuozhanDesc;
	}

	/**
	 * 道具 描述类 道具id t_xianshi_activity_reward.f_view_good
	 * 
	 * @return the value of t_xianshi_activity_reward.f_view_good
	 */
	public Integer getViewGood() {
		return viewGood;
	}

	/**
	 * 道具 描述类 道具id t_xianshi_activity_reward.f_view_good
	 * 
	 * @param viewGood
	 *            the value for t_xianshi_activity_reward.f_view_good
	 */
	public void setViewGood(Integer viewGood) {
		this.viewGood = viewGood;
	}

	/**
	 * 查询用备注，不要重名 t_xianshi_activity_reward.f_remark
	 * 
	 * @return the value of t_xianshi_activity_reward.f_remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 查询用备注，不要重名 t_xianshi_activity_reward.f_remark
	 * 
	 * @param remark
	 *            the value for t_xianshi_activity_reward.f_remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 商城限购物品以及限购数量 goodId*count t_xianshi_activity_reward.f_shop_limit_good
	 * 
	 * @return the value of t_xianshi_activity_reward.f_shop_limit_good
	 */
	public String getShopLimitGood() {
		return shopLimitGood;
	}

	/**
	 * 商城限购物品以及限购数量 goodId*count t_xianshi_activity_reward.f_shop_limit_good
	 * 
	 * @param shopLimitGood
	 *            the value for t_xianshi_activity_reward.f_shop_limit_good
	 */
	public void setShopLimitGood(String shopLimitGood) {
		this.shopLimitGood = shopLimitGood;
	}

	/**
	 * 添加扩展内容显示国际化 t_xianshi_activity_reward.f_kuozhan_desc_i18n
	 * 
	 * @return the value of t_xianshi_activity_reward.f_kuozhan_desc_i18n
	 */
	public String getKuozhanDescI18n() {
		return kuozhanDescI18n;
	}

	/**
	 * 添加扩展内容显示国际化 t_xianshi_activity_reward.f_kuozhan_desc_i18n
	 * 
	 * @param kuozhanDescI18n
	 *            the value for t_xianshi_activity_reward.f_kuozhan_desc_i18n
	 */
	public void setKuozhanDescI18n(String kuozhanDescI18n) {
		this.kuozhanDescI18n = kuozhanDescI18n;
	}

	/**
	 * 查询用备注，不要重名国际化 t_xianshi_activity_reward.f_remark_i18n
	 * 
	 * @return the value of t_xianshi_activity_reward.f_remark_i18n
	 */
	public String getRemarkI18n() {
		return remarkI18n;
	}

	/**
	 * 查询用备注，不要重名国际化 t_xianshi_activity_reward.f_remark_i18n
	 * 
	 * @param remarkI18n
	 *            the value for t_xianshi_activity_reward.f_remark_i18n
	 */
	public void setRemarkI18n(String remarkI18n) {
		this.remarkI18n = remarkI18n;
	}

	/**
	 * 奖励物品失效时间 null表示无失效时间/yyyy-mm-dd hh:mm:ss格式表示失效时间 t_xianshi_activity_reward.f_reward_good_losttime
	 * 
	 * @return the value of t_xianshi_activity_reward.f_reward_good_losttime
	 */
	public String getRewardGoodLosttime() {
		return rewardGoodLosttime;
	}

	/**
	 * 奖励物品失效时间 null表示无失效时间/yyyy-mm-dd hh:mm:ss格式表示失效时间 t_xianshi_activity_reward.f_reward_good_losttime
	 * 
	 * @param rewardGoodLosttime
	 *            the value for t_xianshi_activity_reward.f_reward_good_losttime
	 */
	public void setRewardGoodLosttime(String rewardGoodLosttime) {
		this.rewardGoodLosttime = rewardGoodLosttime;
	}

	/**
	 * 坐骑种类限制（关联坐骑表kind字段对应） t_xianshi_activity_reward.f_horse_limit
	 * 
	 * @return the value of t_xianshi_activity_reward.f_horse_limit
	 */
	public Integer getHorseLimit() {
		return horseLimit;
	}

	/**
	 * 坐骑种类限制（关联坐骑表kind字段对应） t_xianshi_activity_reward.f_horse_limit
	 * 
	 * @param horseLimit
	 *            the value for t_xianshi_activity_reward.f_horse_limit
	 */
	public void setHorseLimit(Integer horseLimit) {
		this.horseLimit = horseLimit;
	}

	/**
	 * t_xianshi_activity_reward.f_desc_i18n
	 * 
	 * @return the value of t_xianshi_activity_reward.f_desc_i18n
	 */
	public String getDescI18n() {
		return descI18n;
	}

	/**
	 * t_xianshi_activity_reward.f_desc_i18n
	 * 
	 * @param descI18n
	 *            the value for t_xianshi_activity_reward.f_desc_i18n
	 */
	public void setDescI18n(String descI18n) {
		this.descI18n = descI18n;
	}

	private List<CharacterGoods> goodLimiteList;
	private int shoplimitGoodId = 0;
	private int shoplimitGoodCount = 0;
	private Date startDate;
	private Date endDate;
	private List<CharacterGoods> listReward;
	private Integer lostHaveTime;

	public void init() {
		if (this.rewardGoods != null && this.rewardGoods.length() > 0) {
			listReward = new ArrayList<CharacterGoods>();
			String[] goodstr = this.rewardGoods.split(";");
			Date lostTime = null;
			if (this.rewardGoodLosttime != null && this.rewardGoodLosttime.length() > 0) {
				if (this.rewardGoodLosttime.length() < 10) {
					try {
						lostHaveTime = Integer.parseInt(this.rewardGoodLosttime);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				} else {
					try {
						lostTime = ArithmeticUtils.stringToDate(this.rewardGoodLosttime);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}

			}
			for (int i = 0; i < goodstr.length; i++) {
				String[] reward = goodstr[i].split("[*]");
				int rewardId = Integer.parseInt(reward[0]);
				int count = Integer.parseInt(reward[1]);
				CharacterGoods cg = CharacterGoods.createCharacterGoods(count, rewardId, 0);
				if (lostTime != null) {
					cg.setLastDate(lostTime);
				}
				listReward.add(cg);

			}
		}
		if (this.startTime != null && this.startTime.length() > 0) {
			startDate = ArithmeticUtils.stringToDate(this.startTime);
		}
		if (this.endTime != null && this.endTime.length() > 0) {
			endDate = ArithmeticUtils.stringToDate(this.endTime);
		}
		if (this.shopLimitGood != null && this.shopLimitGood.length() > 0) {
			String[] str = this.shopLimitGood.split("[*]");
			shoplimitGoodId = Integer.parseInt(str[0]);
			shoplimitGoodCount = Integer.parseInt(str[1]);
		}
		if (this.goodLimite == null || this.goodLimite.length() < 1) {
			return;
		}
		goodLimiteList = new ArrayList<CharacterGoods>();
		String[] goodstrLimitTemp = this.goodLimite.split(";");
		for (int i = 0; i < goodstrLimitTemp.length; i++) {
			String[] str = goodstrLimitTemp[i].split("[*]");
			int limitGoodId = Integer.parseInt(str[0]);
			int limitGoodCount = Integer.parseInt(str[1]);
			CharacterGoods delete = new CharacterGoods();
			delete.setGoodmodelId(limitGoodId);
			delete.setCount(limitGoodCount);
			goodLimiteList.add(delete);
		}
	}

	public boolean isTimeExpression() {
		if (this.getTimeLimit() == null || this.getTimeLimit().length() == 0) {
			return true;
		}
		TimeExpression te = new TimeExpression(this.getTimeLimit());
		return te.isExpressionTime(System.currentTimeMillis());
	}

	public int getShoplimitGoodId() {
		return shoplimitGoodId;
	}

	public void setShoplimitGoodId(int shoplimitGoodId) {
		this.shoplimitGoodId = shoplimitGoodId;
	}

	public int getShoplimitGoodCount() {
		return shoplimitGoodCount;
	}

	public void setShoplimitGoodCount(int shoplimitGoodCount) {
		this.shoplimitGoodCount = shoplimitGoodCount;
	}

	public List<CharacterGoods> getGoodLimiteList() {
		return goodLimiteList;
	}

	public void setGoodLimiteList(List<CharacterGoods> goodLimiteList) {
		this.goodLimiteList = goodLimiteList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<CharacterGoods> getListReward() {
		return listReward;
	}

	public void setListReward(List<CharacterGoods> listReward) {
		this.listReward = listReward;
	}

	private List<XianshiActivityReward> lijinXaList;

	public List<XianshiActivityReward> getLijinXaList() {
		return lijinXaList;
	}

	public void setLijinXaList(List<XianshiActivityReward> lijinXaList) {
		this.lijinXaList = lijinXaList;
	}

	public void addLijinXa(XianshiActivityReward xar) {
		if (lijinXaList == null) {
			lijinXaList = new ArrayList<XianshiActivityReward>();
		}
		lijinXaList.add(xar);
	}

	/**
	 * 领取指定物品奖励
	 * 
	 * @param goodId
	 * @return
	 */
	public CharacterGoods getMyRewardCg(int goodId) {
		if (listReward == null || listReward.size() == 0) {
			return null;
		}
		if (listReward.size() == 1) {
			return listReward.get(0);
		}
		for (CharacterGoods cg : listReward) {
			if (cg.getGoodmodelId() == goodId) {
				return cg;
			}
		}
		return null;
	}

	public List<CharacterGoods> getMyAllListReward(int lingquCount) {
		List<CharacterGoods> list = new ArrayList<CharacterGoods>();
		for (CharacterGoods cg : listReward) {
			CharacterGoods reward = null;
			try {
				reward = (CharacterGoods) cg.clone();
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
			if (reward != null) {
				reward.setBind(CommonUseNumber.byte1);
				reward.setCount(cg.getCount() * lingquCount);
				Date lostDate = getLostHaveDate();
				if (lostDate != null) {
					reward.setLastDate(lostDate);
				}
				list.add(reward);
			}

		}
		return list;
	}

	/**
	 * 获取失效时间
	 * 
	 * @return
	 */
	private Date getLostHaveDate() {
		if (lostHaveTime == null || lostHaveTime < 10) {
			return null;
		} else {
			Date lostDate = new Date(System.currentTimeMillis() + lostHaveTime * 1000);
			return lostDate;
		}
	}

	/**
	 * 随机有几率获取某奖励 以后需要完善的方法
	 * 
	 * @param lingquCount
	 * @return
	 */
	public List<CharacterGoods> getRandomRewardList() {
		List<CharacterGoods> list = new ArrayList<CharacterGoods>();
		if (listReward == null || listReward.size() == 0) {
			return null;
		}
		int index = GenerateProbability.randomIntValue(0, listReward.size() - 1);
		CharacterGoods cg = listReward.get(index);
		try {
			CharacterGoods reward = (CharacterGoods) cg.clone();
			reward.setBind(CommonUseNumber.byte1);
			list.add(reward);
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}

		return list;
	}

}
