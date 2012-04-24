package net.snake.gamemodel.chest.bean;

import net.snake.ibatis.IbatisEntity;

public class ChestResources  implements IbatisEntity{

	
	/**
	 * id t_chest_resources.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 组包分类id t_chest_resources.f_ches_group_id
	 * 
	 */
	private Integer chesGroupId;
	/**
	 * 真实几率 t_chest_resources.f_Probability_type
	 * 
	 */
	private Integer probabilityType;
	/**
	 * 物品的id t_chest_resources.f_goodmodel_id
	 * 
	 */
	private Integer goodmodelId;
	/**
	 * 是否全服广播1广播0不广播 t_chest_resources.f_full_service_announcement
	 * 
	 */
	private Byte fullServiceAnnouncement;
	/**
	 * 最小品级 t_chest_resources.f_loop_min
	 * 
	 */
	private Integer loopMin;
	/**
	 * 最大品级 t_chest_resources.f_loop_max
	 * 
	 */
	private Integer loopMax;
	/**
	 * 强化等级附加几率 t_chest_resources.f_grade_probability
	 * 
	 */
	private Integer gradeProbability;
	/**
	 * 强化等级附加最小等级 t_chest_resources.f_grade_min
	 * 
	 */
	private Integer gradeMin;
	/**
	 * 强化等级附加最大等级 t_chest_resources.f_grade_max
	 * 
	 */
	private Integer gradeMax;
	/**
	 * 掉落附加产生几率 t_chest_resources.f_flop_probability
	 * 
	 */
	private Integer flopProbability;
	/**
	 * 天生附加几率 t_chest_resources.f_born_probability
	 * 
	 */
	private Integer bornProbability;
	/**
	 * 天生附加最大几率 t_chest_resources.f_bron_max
	 * 
	 */
	private Integer bronMax;
	/**
	 * 天生附加最小几率 t_chest_resources.f_bron_min
	 * 
	 */
	private Integer bronMin;
	/**
	 * 1不绑定,2拾取绑定,3佩戴绑定 t_chest_resources.f_binding
	 * 
	 */
	private Byte binding;
	/**
	 * 数量 t_chest_resources.f_quantity
	 * 
	 */
	private Byte quantity;
	/**
	 * 失效的时间 t_chest_resources.f_fail_time
	 * 
	 */
	private Integer failTime;



	/**
	 * id t_chest_resources.f_id
	 * @return  the value of t_chest_resources.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * id t_chest_resources.f_id
	 * @param id  the value for t_chest_resources.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 组包分类id t_chest_resources.f_ches_group_id
	 * @return  the value of t_chest_resources.f_ches_group_id
	 * 
	 */
	public Integer getChesGroupId() {
		return chesGroupId;
	}

	/**
	 * 组包分类id t_chest_resources.f_ches_group_id
	 * @param chesGroupId  the value for t_chest_resources.f_ches_group_id
	 * 
	 */
	public void setChesGroupId(Integer chesGroupId) {
		this.chesGroupId = chesGroupId;
	}

	/**
	 * 真实几率 t_chest_resources.f_Probability_type
	 * @return  the value of t_chest_resources.f_Probability_type
	 * 
	 */
	public Integer getProbabilityType() {
		return probabilityType;
	}

	/**
	 * 真实几率 t_chest_resources.f_Probability_type
	 * @param probabilityType  the value for t_chest_resources.f_Probability_type
	 * 
	 */
	public void setProbabilityType(Integer probabilityType) {
		this.probabilityType = probabilityType;
	}

	/**
	 * 物品的id t_chest_resources.f_goodmodel_id
	 * @return  the value of t_chest_resources.f_goodmodel_id
	 * 
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 物品的id t_chest_resources.f_goodmodel_id
	 * @param goodmodelId  the value for t_chest_resources.f_goodmodel_id
	 * 
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	/**
	 * 是否全服广播1广播0不广播 t_chest_resources.f_full_service_announcement
	 * @return  the value of t_chest_resources.f_full_service_announcement
	 * 
	 */
	public Byte getFullServiceAnnouncement() {
		return fullServiceAnnouncement;
	}

	/**
	 * 是否全服广播1广播0不广播 t_chest_resources.f_full_service_announcement
	 * @param fullServiceAnnouncement  the value for t_chest_resources.f_full_service_announcement
	 * 
	 */
	public void setFullServiceAnnouncement(Byte fullServiceAnnouncement) {
		this.fullServiceAnnouncement = fullServiceAnnouncement;
	}

	/**
	 * 最小品级 t_chest_resources.f_loop_min
	 * @return  the value of t_chest_resources.f_loop_min
	 * 
	 */
	public Integer getLoopMin() {
		return loopMin;
	}

	/**
	 * 最小品级 t_chest_resources.f_loop_min
	 * @param loopMin  the value for t_chest_resources.f_loop_min
	 * 
	 */
	public void setLoopMin(Integer loopMin) {
		this.loopMin = loopMin;
	}

	/**
	 * 最大品级 t_chest_resources.f_loop_max
	 * @return  the value of t_chest_resources.f_loop_max
	 * 
	 */
	public Integer getLoopMax() {
		return loopMax;
	}

	/**
	 * 最大品级 t_chest_resources.f_loop_max
	 * @param loopMax  the value for t_chest_resources.f_loop_max
	 * 
	 */
	public void setLoopMax(Integer loopMax) {
		this.loopMax = loopMax;
	}

	/**
	 * 强化等级附加几率 t_chest_resources.f_grade_probability
	 * @return  the value of t_chest_resources.f_grade_probability
	 * 
	 */
	public Integer getGradeProbability() {
		return gradeProbability;
	}

	/**
	 * 强化等级附加几率 t_chest_resources.f_grade_probability
	 * @param gradeProbability  the value for t_chest_resources.f_grade_probability
	 * 
	 */
	public void setGradeProbability(Integer gradeProbability) {
		this.gradeProbability = gradeProbability;
	}

	/**
	 * 强化等级附加最小等级 t_chest_resources.f_grade_min
	 * @return  the value of t_chest_resources.f_grade_min
	 * 
	 */
	public Integer getGradeMin() {
		return gradeMin;
	}

	/**
	 * 强化等级附加最小等级 t_chest_resources.f_grade_min
	 * @param gradeMin  the value for t_chest_resources.f_grade_min
	 * 
	 */
	public void setGradeMin(Integer gradeMin) {
		this.gradeMin = gradeMin;
	}

	/**
	 * 强化等级附加最大等级 t_chest_resources.f_grade_max
	 * @return  the value of t_chest_resources.f_grade_max
	 * 
	 */
	public Integer getGradeMax() {
		return gradeMax;
	}

	/**
	 * 强化等级附加最大等级 t_chest_resources.f_grade_max
	 * @param gradeMax  the value for t_chest_resources.f_grade_max
	 * 
	 */
	public void setGradeMax(Integer gradeMax) {
		this.gradeMax = gradeMax;
	}

	/**
	 * 掉落附加产生几率 t_chest_resources.f_flop_probability
	 * @return  the value of t_chest_resources.f_flop_probability
	 * 
	 */
	public Integer getFlopProbability() {
		return flopProbability;
	}

	/**
	 * 掉落附加产生几率 t_chest_resources.f_flop_probability
	 * @param flopProbability  the value for t_chest_resources.f_flop_probability
	 * 
	 */
	public void setFlopProbability(Integer flopProbability) {
		this.flopProbability = flopProbability;
	}

	/**
	 * 天生附加几率 t_chest_resources.f_born_probability
	 * @return  the value of t_chest_resources.f_born_probability
	 * 
	 */
	public Integer getBornProbability() {
		return bornProbability;
	}

	/**
	 * 天生附加几率 t_chest_resources.f_born_probability
	 * @param bornProbability  the value for t_chest_resources.f_born_probability
	 * 
	 */
	public void setBornProbability(Integer bornProbability) {
		this.bornProbability = bornProbability;
	}

	/**
	 * 天生附加最大几率 t_chest_resources.f_bron_max
	 * @return  the value of t_chest_resources.f_bron_max
	 * 
	 */
	public Integer getBronMax() {
		return bronMax;
	}

	/**
	 * 天生附加最大几率 t_chest_resources.f_bron_max
	 * @param bronMax  the value for t_chest_resources.f_bron_max
	 * 
	 */
	public void setBronMax(Integer bronMax) {
		this.bronMax = bronMax;
	}

	/**
	 * 天生附加最小几率 t_chest_resources.f_bron_min
	 * @return  the value of t_chest_resources.f_bron_min
	 * 
	 */
	public Integer getBronMin() {
		return bronMin;
	}

	/**
	 * 天生附加最小几率 t_chest_resources.f_bron_min
	 * @param bronMin  the value for t_chest_resources.f_bron_min
	 * 
	 */
	public void setBronMin(Integer bronMin) {
		this.bronMin = bronMin;
	}

	/**
	 * 1不绑定,2拾取绑定,3佩戴绑定 t_chest_resources.f_binding
	 * @return  the value of t_chest_resources.f_binding
	 * 
	 */
	public Byte getBinding() {
		return binding;
	}

	/**
	 * 1不绑定,2拾取绑定,3佩戴绑定 t_chest_resources.f_binding
	 * @param binding  the value for t_chest_resources.f_binding
	 * 
	 */
	public void setBinding(Byte binding) {
		this.binding = binding;
	}

	/**
	 * 数量 t_chest_resources.f_quantity
	 * @return  the value of t_chest_resources.f_quantity
	 * 
	 */
	public Byte getQuantity() {
		return quantity;
	}

	/**
	 * 数量 t_chest_resources.f_quantity
	 * @param quantity  the value for t_chest_resources.f_quantity
	 * 
	 */
	public void setQuantity(Byte quantity) {
		this.quantity = quantity;
	}

	/**
	 * 失效的时间 t_chest_resources.f_fail_time
	 * @return  the value of t_chest_resources.f_fail_time
	 * 
	 */
	public Integer getFailTime() {
		return failTime;
	}

	/**
	 * 失效的时间 t_chest_resources.f_fail_time
	 * @param failTime  the value for t_chest_resources.f_fail_time
	 * 
	 */
	public void setFailTime(Integer failTime) {
		this.failTime = failTime;
	}

	private String  zhongjiangidString;
	
	

	public String getZhongjiangidString() {
		return zhongjiangidString;
	}

	public void setZhongjiangidString(String zhongjiangidString) {
		this.zhongjiangidString = zhongjiangidString;
	}

	//初始化从goodmodel加载过来
	private int grade; //品级
	private int popsinger;//门派
	private int ma=0;//马的标识
	
	
	

	public int getModel() {
		return ma;
	}

	public void setMa(int ma) {
		this.ma = ma;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getPopsinger() {
		return popsinger;
	}

	public void setPopsinger(int fPopsinger) {
		popsinger = fPopsinger;
	}
	
	
	
}
