package net.snake.ai.move;

public class MoveInfo {
	public final static int TYPE_STAND=0;//静止
	public final static int TYPE_WALK=1;//行走
	public final static int TYPE_JUMP=2;//跳跃
	public final static int TYPE_JUMP2=3;//二级跳

	public int moveType;
	//如果移动方式是跳跃，标记是否是2级跳
//	public boolean is2jitiao=false;
	//移动路线
	public short[] path;
	public static MoveInfo createWalkPath(short[] path){
		MoveInfo mi=new MoveInfo();
		mi.path=path;
		mi.moveType=TYPE_WALK;
		return mi;
	}
	public static MoveInfo createJumpPath(short[] path,boolean is2jitiao){
		MoveInfo mi=new MoveInfo();
		mi.path=path;
		mi.moveType=is2jitiao?TYPE_JUMP2:TYPE_JUMP;
//		mi.is2jitiao=is2jitiao;
		return mi;
	}
	public boolean is2jitiao(){
		return moveType==TYPE_JUMP2;
	}
}
