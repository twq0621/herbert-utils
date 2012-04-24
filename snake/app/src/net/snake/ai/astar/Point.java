package net.snake.ai.astar;

import net.snake.ai.util.ArithmeticUtils;

/**
 * 记录数组下标点
 * @author serv_dev
 *
 */
final public class Point{
	
	private short x = 0;
	
	private short y = 0;
	
	private int g = 0;  //g值
	
	private int h = 0;  //h值
	
	private int f = 0;   // g + h
	
	private boolean bevel = false;  //斜角坐标
	
	private Point parent = null;
	
	public static final int BEVEL = 14; //斜角计算使用常量

	public static final int NOTBEVEL= 10; //正常角度计算使用常量
	
	private boolean isBlock = false;
	
	private Integer keyInteger;
	
	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public boolean isBlock() {
		return isBlock;
	}

	public Point(short x, short y) {
		this.x = x;
		this.y = y;
		keyInteger=ArithmeticUtils.shortToInt(this.x,this.y);
	}
	
	public Point(int x,int y){
		this.x=(short) x;
		this.y=(short) y;
		keyInteger=ArithmeticUtils.shortToInt(this.x,this.y);
	}
	@Override
	public int hashCode() {
		return keyInteger.hashCode();
	}

	/**
	 * 处理H值
	 * @param p 目标节点
	 */
	public void calculateH(Point p) {
		if (p == null)
			return;
		int xh = Math.abs(x - p.x);
		int yh = Math.abs(y - p.y);
		h = (xh + yh) * 10;
	}
	
	/**
	 * 计算G值
	 * @param p 上一个节点
	 */
	public void calculateG(Point p) {
		if (p == null) 
			return;
		g = isBevel() ? p.getG() + Point.BEVEL
				: p.getG() + Point.NOTBEVEL;
	}

	public Integer toKey() {
		return keyInteger;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Point)) {
			return false;
		}
		Point newP = (Point)obj;
		
		if (newP.x == x && newP.y == y) {
			return true;
		} 
		
		return false;
	}
	
	public boolean isBevel() {
		return bevel;
	}

	public void setBevel(boolean bevel) {
		this.bevel = bevel;
	}


	public short getX() {
		return x;
	}

	public short getY() {
		return y;
	}
	
	public int getG() {
		return g;
	}

	public int getH() {
		return h;
	}

	public Point getParent() {
		return parent;
	}

	public int getF() {
		f = g + h;
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public void setParent(Point parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Point[x=" + x + ",y=" + y + "]";
	}
	
}
