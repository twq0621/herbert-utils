package net.snake.ai.formula;

public class DistanceFormula {
	//通过缓存计算结果进行优化
	static float[][] distance = new float[200][200];
	static int[][] distanceRound = new int[200][200];
	static {
		for (int x = 0; x < 200; x++) {
			for (int y = 0; y < 200; y++) {
				distance[x][y]= (float) Math.sqrt(x * x + y * y);
				distanceRound[x][y]=Math.round(distance[x][y]);
			}
		}
	}
	//获得两点的距离，并进行四舍五入
	public static int getDistanceRound(int ox,int oy,int tx,int ty){
		int tempX = Math.abs(tx - ox);
		int tempY = Math.abs(ty - oy);
		if(tempX<200&&tempY<200){
			return distanceRound[tempX][tempY];
		}
		return (int) Math.round(Math.sqrt(tempX * tempX + tempY * tempY));
	}
	//获得两点的距离
	public static float getDistance2(int ox, int oy, int tx, int ty) {
		int tempX = Math.abs(tx - ox);
		int tempY = Math.abs(ty - oy);
		if(tempX<200&&tempY<200){
			return distance[tempX][tempY];
		}
		return (float) Math.sqrt(tempX * tempX + tempY * tempY);
	}
	public static int getGridMaxDistance(short x,short y,short targetx,short targety) {
		int xdis = Math.abs(targetx - x);
		int ydis = Math.abs(targety - y);
		return xdis > ydis ? xdis : ydis;
	}
	

}
