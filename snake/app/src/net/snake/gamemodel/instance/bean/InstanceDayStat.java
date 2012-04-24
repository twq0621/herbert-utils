package net.snake.gamemodel.instance.bean;

import net.snake.ibatis.IbatisEntity;


public class InstanceDayStat extends InstanceDayStatKey  implements IbatisEntity{

	/**
	 * 重置副本次数 t_instance_daystat.resetInstanceCount
	 * 
	 */
	private Short resetinstancecount;
	/**
	 * 副本令今日进入次数 t_instance_daystat.fubenling_count
	 * 
	 */
	private Integer fubenlingCount;

	/**
	 * 重置副本次数 t_instance_daystat.resetInstanceCount
	 * @return  the value of t_instance_daystat.resetInstanceCount
	 * 
	 */
	public Short getResetinstancecount() {
		return resetinstancecount;
	}

	/**
	 * 重置副本次数 t_instance_daystat.resetInstanceCount
	 * @param resetinstancecount  the value for t_instance_daystat.resetInstanceCount
	 * 
	 */
	public void setResetinstancecount(Short resetinstancecount) {
		this.resetinstancecount = resetinstancecount;
	}

	/**
	 * 副本令今日进入次数 t_instance_daystat.fubenling_count
	 * @return  the value of t_instance_daystat.fubenling_count
	 * 
	 */
	public Integer getFubenlingCount() {
		return fubenlingCount;
	}

	/**
	 * 副本令今日进入次数 t_instance_daystat.fubenling_count
	 * @param fubenlingCount  the value for t_instance_daystat.fubenling_count
	 * 
	 */
	public void setFubenlingCount(Integer fubenlingCount) {
		this.fubenlingCount = fubenlingCount;
	}
}
