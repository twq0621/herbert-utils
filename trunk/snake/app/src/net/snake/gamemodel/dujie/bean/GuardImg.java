package net.snake.gamemodel.dujie.bean;


/**
 * 玩家选中的护法数据
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class GuardImg {
	public static final int Guards_Normal=1;
	public static final int Guards_Advanced=2;
	public static final int Guards_Super=3;
	
	public int id;
	public int type;
	public int headImg=0;
	public String name;
	public int gs;
	public int fee;
	
	public int mxZhenyuan;
	public int currZhenyuan;
	
	public GuardImg preNode;
	public GuardImg nextNode;
	
	public GuardImg() {
		id=-1;
		preNode = null;
		nextNode = null;
	}
	

}
