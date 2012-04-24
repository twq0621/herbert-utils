package net.snake.consts;
/**
 * 1面攻击(群攻)2线攻击(群攻)3点攻击(单攻)4点攻击(单攻+群攻)
 * @author serv_dev
 */
public enum ArrowWay {
	NULL(0),
	face(1),
	line(2),
	point_one(3),
	point_mul(4),
	point_one_nojump(5);

	private int num;
	private ArrowWay(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}

	public static ArrowWay getSkillType(int num) {
		switch (num) {
		case 1:
			return face;
		case 2:
			return line;
		case 3:
			return point_one;
		case 4:
			return point_mul;
		case 5: 
			return point_one_nojump;
		default:
			return NULL;
		}
	}
}
