package net.snake.gamemodel.line.bean;

import net.snake.ibatis.IbatisEntity;

public class LineServerEntryKey implements IbatisEntity {

	/**
	 * 对应serverlist中的loginServerId t_lineserver.loginServerId
	 * 
	 */
	private Integer loginserverid;
	/**
	 * 分线ID t_lineserver.num
	 * 
	 */
	private Byte num;

	/**
	 * 对应serverlist中的loginServerId t_lineserver.loginServerId
	 * @return  the value of t_lineserver.loginServerId
	 * 
	 */
	public Integer getLoginserverid() {
		return loginserverid;
	}

	/**
	 * 对应serverlist中的loginServerId t_lineserver.loginServerId
	 * @param loginserverid  the value for t_lineserver.loginServerId
	 * 
	 */
	public void setLoginserverid(Integer loginserverid) {
		this.loginserverid = loginserverid;
	}

	/**
	 * 分线ID t_lineserver.num
	 * @return  the value of t_lineserver.num
	 * 
	 */
	public Byte getNum() {
		return num;
	}

	/**
	 * 分线ID t_lineserver.num
	 * @param num  the value for t_lineserver.num
	 * 
	 */
	public void setNum(Byte num) {
		this.num = num;
	}
}
