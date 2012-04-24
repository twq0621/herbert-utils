package net.snake.gamemodel.wedding.bean;

import net.snake.ibatis.IbatisEntity;

public class Feast  implements IbatisEntity{

	/**
	 * t_feast.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 申请人ID t_feast.f_applyer_id
	 * 
	 */
	private Integer applyerId;
	/**
	 * 偶配ID t_feast.f_mate_id
	 * 
	 */
	private Integer mateId;
	/**
	 * 宴婚级别 t_feast.f_fasttype
	 * 
	 */
	private Integer fasttype;
	/**
	 * 申请时间 t_feast.f_applytime
	 * 
	 */
	private Long applytime;
	/**
	 * 0 未开始,1 进行中,2 己结束 t_feast.f_state
	 * 
	 */
	private Integer state;
	/**
	 * 参与人次 t_feast.f_join_count
	 * 
	 */
	private Integer joinCount;
	/**
	 * 申请人取领标记  0未领取,非0己领取 t_feast.f_isreceive1
	 * 
	 */
	private Boolean isreceive1;
	/**
	 * 偶配取领标记  0未领取,非0己领取 t_feast.f_isreceive2
	 * 
	 */
	private Boolean isreceive2;
	/**
	 * 包红金额 t_feast.f_gift_amount
	 * 
	 */
	private Integer giftAmount;
	/**
	 * 举办的线 t_feast.f_line
	 * 
	 */
	private Integer line;

	/**
	 * t_feast.f_id
	 * @return  the value of t_feast.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_feast.f_id
	 * @param id  the value for t_feast.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 申请人ID t_feast.f_applyer_id
	 * @return  the value of t_feast.f_applyer_id
	 * 
	 */
	public Integer getApplyerId() {
		return applyerId;
	}

	/**
	 * 申请人ID t_feast.f_applyer_id
	 * @param applyerId  the value for t_feast.f_applyer_id
	 * 
	 */
	public void setApplyerId(Integer applyerId) {
		this.applyerId = applyerId;
	}

	/**
	 * 偶配ID t_feast.f_mate_id
	 * @return  the value of t_feast.f_mate_id
	 * 
	 */
	public Integer getMateId() {
		return mateId;
	}

	/**
	 * 偶配ID t_feast.f_mate_id
	 * @param mateId  the value for t_feast.f_mate_id
	 * 
	 */
	public void setMateId(Integer mateId) {
		this.mateId = mateId;
	}

	/**
	 * 宴婚级别 t_feast.f_fasttype
	 * @return  the value of t_feast.f_fasttype
	 * 
	 */
	public Integer getFasttype() {
		return fasttype;
	}

	/**
	 * 宴婚级别 t_feast.f_fasttype
	 * @param fasttype  the value for t_feast.f_fasttype
	 * 
	 */
	public void setFasttype(Integer fasttype) {
		this.fasttype = fasttype;
	}

	/**
	 * 申请时间 t_feast.f_applytime
	 * @return  the value of t_feast.f_applytime
	 * 
	 */
	public Long getApplytime() {
		return applytime;
	}

	/**
	 * 申请时间 t_feast.f_applytime
	 * @param applytime  the value for t_feast.f_applytime
	 * 
	 */
	public void setApplytime(Long applytime) {
		this.applytime = applytime;
	}

	/**
	 * 0 未开始,1 进行中,2 己结束 t_feast.f_state
	 * @return  the value of t_feast.f_state
	 * 
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 0 未开始,1 进行中,2 己结束 t_feast.f_state
	 * @param state  the value for t_feast.f_state
	 * 
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 参与人次 t_feast.f_join_count
	 * @return  the value of t_feast.f_join_count
	 * 
	 */
	public Integer getJoinCount() {
		return joinCount;
	}

	/**
	 * 参与人次 t_feast.f_join_count
	 * @param joinCount  the value for t_feast.f_join_count
	 * 
	 */
	public void setJoinCount(Integer joinCount) {
		this.joinCount = joinCount;
	}

	/**
	 * 申请人取领标记  0未领取,非0己领取 t_feast.f_isreceive1
	 * @return  the value of t_feast.f_isreceive1
	 * 
	 */
	public Boolean getIsreceive1() {
		return isreceive1;
	}

	/**
	 * 申请人取领标记  0未领取,非0己领取 t_feast.f_isreceive1
	 * @param isreceive1  the value for t_feast.f_isreceive1
	 * 
	 */
	public void setIsreceive1(Boolean isreceive1) {
		this.isreceive1 = isreceive1;
	}

	/**
	 * 偶配取领标记  0未领取,非0己领取 t_feast.f_isreceive2
	 * @return  the value of t_feast.f_isreceive2
	 * 
	 */
	public Boolean getIsreceive2() {
		return isreceive2;
	}

	/**
	 * 偶配取领标记  0未领取,非0己领取 t_feast.f_isreceive2
	 * @param isreceive2  the value for t_feast.f_isreceive2
	 * 
	 */
	public void setIsreceive2(Boolean isreceive2) {
		this.isreceive2 = isreceive2;
	}

	/**
	 * 包红金额 t_feast.f_gift_amount
	 * @return  the value of t_feast.f_gift_amount
	 * 
	 */
	public Integer getGiftAmount() {
		return giftAmount;
	}

	/**
	 * 包红金额 t_feast.f_gift_amount
	 * @param giftAmount  the value for t_feast.f_gift_amount
	 * 
	 */
	public void setGiftAmount(Integer giftAmount) {
		this.giftAmount = giftAmount;
	}

	/**
	 * 举办的线 t_feast.f_line
	 * @return  the value of t_feast.f_line
	 * 
	 */
	public Integer getLine() {
		return line;
	}

	/**
	 * 举办的线 t_feast.f_line
	 * @param line  the value for t_feast.f_line
	 * 
	 */
	public void setLine(Integer line) {
		this.line = line;
	}
}
