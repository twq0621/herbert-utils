package net.snake.ai.move;

import net.snake.netio.ServerResponse;

public interface IMoveController {
	public abstract void destory();
	/**是否允许移动*/
	public abstract boolean isEnableMove();
	/**设置是否可以移动*/
	public abstract void setEnableMove(boolean enable);
	
	/**获得上次跳跃完成的时间*/
	public abstract long getPreviousJumpEndTime();
	
	/**添加移动路径*/
	public abstract void addWalkLines(short[] gopath);
	/**获得当前线路*/
	public short[] getLines();
	//添加跳跃路径
//	public abstract void addJumpLines(short[] gopath);
	/**设置跳跃路径*/
	public abstract void setJumpLines(short[] gopath,boolean is2jitiao, long begintime);
	/**设置移动路径 */
	public abstract void setWalkLines(short[] gopath, long begintime);
	
	public abstract void instantMove(short x,short y);
	public abstract void checkToStopTailPoint();
	/** 执行时间更新,并返回是否走到头了**/
	public abstract boolean checkArrived();
	/**停止移动并清空路线*/
	public abstract void stopMove();
	//暂时移动，但是并不清
//	public abstract void pauseMove();
	/** 是否正在移动*/
	public abstract boolean isMoving();
	/**获得移动的方式*/
	public abstract int getMovingType();
	/**获得当前的移动消息 */
	public abstract ServerResponse getMoveMsg();

	public abstract int getV();

}
