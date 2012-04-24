package net.snake.gamemodel.faction.bean;

import net.snake.ibatis.IbatisEntity;

public class FactionCityConfig implements IbatisEntity {

	/**
	 * t_faction_city_config.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 帮主可领取的物品id t_faction_city_config.f_bangzhu_good
	 * 
	 */
	private Integer bangzhuGood;
	/**
	 * 帮主可领取数量 t_faction_city_config.f_bangzhu_good_count
	 * 
	 */
	private Integer bangzhuGoodCount;
	/**
	 * 帮主可领取的物品id t_faction_city_config.f_fubangzhu_good
	 * 
	 */
	private Integer fubangzhuGood;
	/**
	 * 帮主可领取数量 t_faction_city_config.f_fubangzhu_good_count
	 * 
	 */
	private Integer fubangzhuGoodCount;
	/**
	 * 帮主可领取的物品id t_faction_city_config.f_dazhanglao_good
	 * 
	 */
	private Integer dazhanglaoGood;
	/**
	 * 帮主可领取数量 t_faction_city_config.f_dazhanglao_good_count
	 * 
	 */
	private Integer dazhanglaoGoodCount;
	/**
	 * 帮主可领取的物品id t_faction_city_config.f_dashixiong_good
	 * 
	 */
	private Integer dashixiongGood;
	/**
	 * 帮主可领取数量 t_faction_city_config.f_dashixiong_good_count
	 * 
	 */
	private Integer dashixiongGoodCount;
	/**
	 * 帮主可领取的物品id t_faction_city_config.f_dashijie_good
	 * 
	 */
	private Integer dashijieGood;
	/**
	 * 帮主可领取数量 t_faction_city_config.f_dashijie_good_count
	 * 
	 */
	private Integer dashijieGoodCount;
	/**
	 * 入帮天数限制 t_faction_city_config.f_infaction_days
	 * 
	 */
	private Integer infactionDays;
	/**
	 * 打怪经验加成 单位 1/10000 t_faction_city_config.f_exp_jiacheng
	 * 
	 */
	private Integer expJiacheng;
	/**
	 * 打坐速度加成 单位 毫秒 t_faction_city_config.f_dazuo_jiacheng
	 * 
	 */
	private Integer dazuoJiacheng;
	/**
	 * 襄阳税收率最小值 单位 1/10000 t_faction_city_config.f_taxrate_min
	 * 
	 */
	private Integer taxrateMin;
	/**
	 * 税收最大值 1/10000 t_faction_city_config.f_taxrate_max
	 * 
	 */
	private Integer taxrateMax;
	/**
	 * 每日领取比例  单位 1/10000 t_faction_city_config.f_alltaxs_scale
	 * 
	 */
	private Integer alltaxsScale;
	/**
	 * 占城帮会成员打坐真气 经验加成buffer t_faction_city_config.f_factioncity_buffer
	 * 
	 */
	private Integer factioncityBuffer;
	/**
	 * 城主游龙之人buffer t_faction_city_config.f_chengzhu_buffer
	 * 
	 */
	private Integer chengzhuBuffer;

	/**
	 * t_faction_city_config.f_id
	 * @return  the value of t_faction_city_config.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_faction_city_config.f_id
	 * @param id  the value for t_faction_city_config.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_bangzhu_good
	 * @return  the value of t_faction_city_config.f_bangzhu_good
	 * 
	 */
	public Integer getBangzhuGood() {
		return bangzhuGood;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_bangzhu_good
	 * @param bangzhuGood  the value for t_faction_city_config.f_bangzhu_good
	 * 
	 */
	public void setBangzhuGood(Integer bangzhuGood) {
		this.bangzhuGood = bangzhuGood;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_bangzhu_good_count
	 * @return  the value of t_faction_city_config.f_bangzhu_good_count
	 * 
	 */
	public Integer getBangzhuGoodCount() {
		return bangzhuGoodCount;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_bangzhu_good_count
	 * @param bangzhuGoodCount  the value for t_faction_city_config.f_bangzhu_good_count
	 * 
	 */
	public void setBangzhuGoodCount(Integer bangzhuGoodCount) {
		this.bangzhuGoodCount = bangzhuGoodCount;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_fubangzhu_good
	 * @return  the value of t_faction_city_config.f_fubangzhu_good
	 * 
	 */
	public Integer getFubangzhuGood() {
		return fubangzhuGood;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_fubangzhu_good
	 * @param fubangzhuGood  the value for t_faction_city_config.f_fubangzhu_good
	 * 
	 */
	public void setFubangzhuGood(Integer fubangzhuGood) {
		this.fubangzhuGood = fubangzhuGood;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_fubangzhu_good_count
	 * @return  the value of t_faction_city_config.f_fubangzhu_good_count
	 * 
	 */
	public Integer getFubangzhuGoodCount() {
		return fubangzhuGoodCount;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_fubangzhu_good_count
	 * @param fubangzhuGoodCount  the value for t_faction_city_config.f_fubangzhu_good_count
	 * 
	 */
	public void setFubangzhuGoodCount(Integer fubangzhuGoodCount) {
		this.fubangzhuGoodCount = fubangzhuGoodCount;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_dazhanglao_good
	 * @return  the value of t_faction_city_config.f_dazhanglao_good
	 * 
	 */
	public Integer getDazhanglaoGood() {
		return dazhanglaoGood;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_dazhanglao_good
	 * @param dazhanglaoGood  the value for t_faction_city_config.f_dazhanglao_good
	 * 
	 */
	public void setDazhanglaoGood(Integer dazhanglaoGood) {
		this.dazhanglaoGood = dazhanglaoGood;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_dazhanglao_good_count
	 * @return  the value of t_faction_city_config.f_dazhanglao_good_count
	 * 
	 */
	public Integer getDazhanglaoGoodCount() {
		return dazhanglaoGoodCount;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_dazhanglao_good_count
	 * @param dazhanglaoGoodCount  the value for t_faction_city_config.f_dazhanglao_good_count
	 * 
	 */
	public void setDazhanglaoGoodCount(Integer dazhanglaoGoodCount) {
		this.dazhanglaoGoodCount = dazhanglaoGoodCount;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_dashixiong_good
	 * @return  the value of t_faction_city_config.f_dashixiong_good
	 * 
	 */
	public Integer getDashixiongGood() {
		return dashixiongGood;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_dashixiong_good
	 * @param dashixiongGood  the value for t_faction_city_config.f_dashixiong_good
	 * 
	 */
	public void setDashixiongGood(Integer dashixiongGood) {
		this.dashixiongGood = dashixiongGood;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_dashixiong_good_count
	 * @return  the value of t_faction_city_config.f_dashixiong_good_count
	 * 
	 */
	public Integer getDashixiongGoodCount() {
		return dashixiongGoodCount;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_dashixiong_good_count
	 * @param dashixiongGoodCount  the value for t_faction_city_config.f_dashixiong_good_count
	 * 
	 */
	public void setDashixiongGoodCount(Integer dashixiongGoodCount) {
		this.dashixiongGoodCount = dashixiongGoodCount;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_dashijie_good
	 * @return  the value of t_faction_city_config.f_dashijie_good
	 * 
	 */
	public Integer getDashijieGood() {
		return dashijieGood;
	}

	/**
	 * 帮主可领取的物品id t_faction_city_config.f_dashijie_good
	 * @param dashijieGood  the value for t_faction_city_config.f_dashijie_good
	 * 
	 */
	public void setDashijieGood(Integer dashijieGood) {
		this.dashijieGood = dashijieGood;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_dashijie_good_count
	 * @return  the value of t_faction_city_config.f_dashijie_good_count
	 * 
	 */
	public Integer getDashijieGoodCount() {
		return dashijieGoodCount;
	}

	/**
	 * 帮主可领取数量 t_faction_city_config.f_dashijie_good_count
	 * @param dashijieGoodCount  the value for t_faction_city_config.f_dashijie_good_count
	 * 
	 */
	public void setDashijieGoodCount(Integer dashijieGoodCount) {
		this.dashijieGoodCount = dashijieGoodCount;
	}

	/**
	 * 入帮天数限制 t_faction_city_config.f_infaction_days
	 * @return  the value of t_faction_city_config.f_infaction_days
	 * 
	 */
	public Integer getInfactionDays() {
		return infactionDays;
	}

	/**
	 * 入帮天数限制 t_faction_city_config.f_infaction_days
	 * @param infactionDays  the value for t_faction_city_config.f_infaction_days
	 * 
	 */
	public void setInfactionDays(Integer infactionDays) {
		this.infactionDays = infactionDays;
	}

	/**
	 * 打怪经验加成 单位 1/10000 t_faction_city_config.f_exp_jiacheng
	 * @return  the value of t_faction_city_config.f_exp_jiacheng
	 * 
	 */
	public Integer getExpJiacheng() {
		return expJiacheng;
	}

	/**
	 * 打怪经验加成 单位 1/10000 t_faction_city_config.f_exp_jiacheng
	 * @param expJiacheng  the value for t_faction_city_config.f_exp_jiacheng
	 * 
	 */
	public void setExpJiacheng(Integer expJiacheng) {
		this.expJiacheng = expJiacheng;
	}

	/**
	 * 打坐速度加成 单位 毫秒 t_faction_city_config.f_dazuo_jiacheng
	 * @return  the value of t_faction_city_config.f_dazuo_jiacheng
	 * 
	 */
	public Integer getDazuoJiacheng() {
		return dazuoJiacheng;
	}

	/**
	 * 打坐速度加成 单位 毫秒 t_faction_city_config.f_dazuo_jiacheng
	 * @param dazuoJiacheng  the value for t_faction_city_config.f_dazuo_jiacheng
	 * 
	 */
	public void setDazuoJiacheng(Integer dazuoJiacheng) {
		this.dazuoJiacheng = dazuoJiacheng;
	}

	/**
	 * 襄阳税收率最小值 单位 1/10000 t_faction_city_config.f_taxrate_min
	 * @return  the value of t_faction_city_config.f_taxrate_min
	 * 
	 */
	public Integer getTaxrateMin() {
		return taxrateMin;
	}

	/**
	 * 襄阳税收率最小值 单位 1/10000 t_faction_city_config.f_taxrate_min
	 * @param taxrateMin  the value for t_faction_city_config.f_taxrate_min
	 * 
	 */
	public void setTaxrateMin(Integer taxrateMin) {
		this.taxrateMin = taxrateMin;
	}

	/**
	 * 税收最大值 1/10000 t_faction_city_config.f_taxrate_max
	 * @return  the value of t_faction_city_config.f_taxrate_max
	 * 
	 */
	public Integer getTaxrateMax() {
		return taxrateMax;
	}

	/**
	 * 税收最大值 1/10000 t_faction_city_config.f_taxrate_max
	 * @param taxrateMax  the value for t_faction_city_config.f_taxrate_max
	 * 
	 */
	public void setTaxrateMax(Integer taxrateMax) {
		this.taxrateMax = taxrateMax;
	}

	/**
	 * 每日领取比例  单位 1/10000 t_faction_city_config.f_alltaxs_scale
	 * @return  the value of t_faction_city_config.f_alltaxs_scale
	 * 
	 */
	public Integer getAlltaxsScale() {
		return alltaxsScale;
	}

	/**
	 * 每日领取比例  单位 1/10000 t_faction_city_config.f_alltaxs_scale
	 * @param alltaxsScale  the value for t_faction_city_config.f_alltaxs_scale
	 * 
	 */
	public void setAlltaxsScale(Integer alltaxsScale) {
		this.alltaxsScale = alltaxsScale;
	}

	/**
	 * 占城帮会成员打坐真气 经验加成buffer t_faction_city_config.f_factioncity_buffer
	 * @return  the value of t_faction_city_config.f_factioncity_buffer
	 * 
	 */
	public Integer getFactioncityBuffer() {
		return factioncityBuffer;
	}

	/**
	 * 占城帮会成员打坐真气 经验加成buffer t_faction_city_config.f_factioncity_buffer
	 * @param factioncityBuffer  the value for t_faction_city_config.f_factioncity_buffer
	 * 
	 */
	public void setFactioncityBuffer(Integer factioncityBuffer) {
		this.factioncityBuffer = factioncityBuffer;
	}

	/**
	 * 城主游龙之人buffer t_faction_city_config.f_chengzhu_buffer
	 * @return  the value of t_faction_city_config.f_chengzhu_buffer
	 * 
	 */
	public Integer getChengzhuBuffer() {
		return chengzhuBuffer;
	}

	/**
	 * 城主游龙之人buffer t_faction_city_config.f_chengzhu_buffer
	 * @param chengzhuBuffer  the value for t_faction_city_config.f_chengzhu_buffer
	 * 
	 */
	public void setChengzhuBuffer(Integer chengzhuBuffer) {
		this.chengzhuBuffer = chengzhuBuffer;
	}
}
