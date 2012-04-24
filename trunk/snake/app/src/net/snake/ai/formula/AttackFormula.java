package net.snake.ai.formula;

import java.util.Random;

import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.VisibleObject;

//import net.snake.script.ScriptEventCenter;

/**
 * 攻击相关的公式
 * 
 * @author serv_dev
 * 
 * 
 */
public class AttackFormula {
	private static Random random = new Random();

	/**
	 * 求两条线的夹角是否小于指定的角度 求一个点是否在指定的夹角之内
	 * 
	 * @param x1
	 *            圆心
	 * @param y1
	 * @param x2
	 *            目标点
	 * @param y2
	 * @param x3
	 *            判断点
	 * @param y3
	 * @param angle
	 * @param radius
	 * @return true是
	 */
	public static boolean inAngle(int x1, int y1, int x2, int y2, int x3, int y3, int angle, int radius) {
		short[] point = getStraightLinePoint(x1, y1, x2, y2, radius);
		if (point == null)
			return false;
		if (DistanceFormula.getDistance2(x3, y3, point[0], point[1]) > radius) {
			return false;
		}

		if (x3 - x1 == 0) {
			if (DistanceFormula.getDistance2(x1, y1, x3, y3) <= radius) {
				return true;
			} else {
				return false;
			}
		}
		int angle_2 = angle / 2;
		if (Math.abs(x3 - x1) < 5) {
			angle_2 = angle_2 + 10;
		}
		if (x2 - x1 == 0) {
			double tanβ = (double) (y3 - y1) / (double) (x3 - x1);
			double tanα = 0;
			return 90 - Math.atan(Math.abs((tanα - tanβ) / (1 + tanα * tanβ))) * 180 / Math.PI < angle_2;
		}
		double tanα = (double) (y2 - y1) / (double) (x2 - x1);
		double tanβ = (double) (y3 - y1) / (double) (x3 - x1);
		// tanθ=∣(k2- k1）/(1+ k1k2）∣
		return Math.atan(Math.abs((tanα - tanβ) / (1 + tanα * tanβ))) * 180 / Math.PI < angle_2;
	}

	/**
	 * 获取点$p1相对于$p0点旋转$angle角度之后的点的坐标
	 * 
	 * @param $p1
	 *            目标点
	 * @param $p0
	 *            圆心点
	 * @param $angle
	 * @return
	 * 
	 */
	public static short[] getRotPoint(int x1, int y1, int x2, int y2, int angle) {
		double dangle = angle * Math.PI / 180;
		double x = 0;
		double y = 0;
		x = Math.cos(dangle) * (x1 - x2) - Math.sin(dangle) * (y1 - y2) + x2;
		y = Math.cos(dangle) * (y1 - y2) + Math.sin(dangle) * (x1 - x2) + y2;
		return new short[] { (short) x, (short) y };
	}

	// public static void main(String[] args) {
	// // AttackFormula--
	// //int x1=110;int y1=125;int x2=104;int y2=126;int x3=98;int y3=130;int
	// angle=60;int radius=10;
	// int x1=0;int y1=0;int x2=7;int y2=2;int x3=98;int y3=130;int angle=60;int
	// radius=10;
	// boolean tf = inAngle(x1, y1, x2, y2, x3, y3, angle, radius);
	// System.out.println(tf);
	// }

	/**
	 * 是否在直线射点上
	 * 
	 * @param x1
	 *            原点x
	 * @param y1
	 *            原点y
	 * @param x2
	 *            目标点x
	 * @param y2
	 *            目标点y
	 * @param x3
	 *            验证点x
	 * @param y3
	 *            验证点y
	 * @return true 在一个直线射程内
	 */
	public static boolean isInLine(int x1, int y1, int x2, int y2, int x3, int y3) {
		float k = 0;
		float b = 0;

		if ((x2 - x1) == 0 && (y2 - y1 == 0)) {
			return true;
		}

		if ((x2 - x1) == 0) {
			if (y2 > y1) {
				return (x3 - x1) == 0 && y3 > y1;
			} else {
				return (x3 - x1) == 0 && y3 < y1;
			}
		}

		if ((y2 - y1 == 0)) {
			if (x2 > x1) {
				return y3 - y1 == 0 && x3 > x1;
			} else {
				return y3 - y1 == 0 && x3 < x1;
			}
		}

		if (x2 < x1 && x3 > x1) {
			return false;
		}
		if (x2 > x1 && x3 < x1) {
			return false;
		}

		k = (float) (y2 - y1) / (float) (x2 - x1);
		b = y1 - k * x1;
		return Math.abs(k * x3 - y3 + b) / Math.sqrt(k * k + 1) <= 3;
		// return y3 == k * x3 + b;
	}

	/**
	 * 
	 * 直线求点 参考公式 y = kx + b ((x1-x2)^2+(y1-y2)^2) = r*r
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param radius
	 */
	public static short[] getStraightLinePoint(int x1, int y1, int x2, int y2, int radius) {
		short x3 = 0;
		short y3 = 0;
		float k = 0;
		float b = 0;

		if ((x2 - x1) == 0 && (y2 - y1 == 0)) {
			return null;
		}

		if ((x2 - x1) == 0) {
			if (y2 > y1) {
				x3 = (short) x1;
				y3 = (short) (y1 + radius);
				return new short[] { x3, y3 };
			} else {
				x3 = (short) x1;
				y3 = (short) (y1 - radius);
				return new short[] { x3, y3 };
			}
		}

		if ((y2 - y1 == 0)) {
			if (x2 > x1) {
				y3 = (short) y1;
				x3 = (short) (x1 + radius);
				return new short[] { x3, y3 };
			} else {
				y3 = (short) y1;
				x3 = (short) (x1 - radius);
				return new short[] { x3, y3 };
			}
		}

		k = (float) (y2 - y1) / (float) (x2 - x1);
		if (k == 0 || k * k + 1 == 0)
			return null;
		b = y1 - k * x1;
		double tmpV = Math.sqrt(Math
				.abs(((radius * radius - (y1 * y1 + b * b + x1 * x1 - 2 * y1 * b)) + (k * b - x1 - k * y1) * (k * b - x1 - k * y1) / (k * k + 1)) / (k * k + 1)));
		double tmpV2 = (k * b - x1 - k * y1) / (k * k + 1);
		x3 = (short) (tmpV - tmpV2);
		if ((x2 < x1 && x3 > x1) || (x2 > x1 && x3 < x1)) {
			x3 = (short) (-tmpV - tmpV2);
		}
		y3 = (short) (x3 * k + b);
		// if (y2 < y1 && y3 > y1) {
		// x3 = (short)(-tmpV - tmpV2);
		// y3 = (short)(x3*k+b);
		// }
		return new short[] { x3, y3 };
	}

	public static float[] getLineKB(int x1, int y1, int x2, int y2) {
		float[] kb = new float[2];
		float k = 0;
		float b = 0;
		if ((x2 - x1) == 0 && (y2 - y1 == 0)) {
			return null;
		}
		if ((x2 - x1) == 0) {
			return null;
		}
		k = (float) (y2 - y1) / (float) (x2 - x1);
		b = y1 - k * x1;
		kb[0] = k;
		kb[1] = b;
		return kb;
	}

	/**
	 * 计算直线攻击，释放者要瞬移到受害者的目标点
	 * 
	 * @param x1
	 *            释放者的x坐标
	 * @param y1
	 *            释放者的y坐标
	 * @param x2
	 *            受害者的x坐标
	 * @param y2
	 *            受害者的y坐标
	 * @param radius
	 *            释放距离
	 * @return
	 */
	public static short[] getLineAttackPoint(short x1, short y1, short x2, short y2, VisibleObject character) {
		int x3 = 0;
		int y3 = 0;
		if (x1 == x2) {
			x3 = x1;
		} else if (x1 > x2) {
			x3 = x2 + 1;
		} else {
			x3 = x2 - 1;
		}
		if (y1 == y2) {
			y3 = y1;
		} else if (y1 > y2) {
			y3 = y2 + 1;
		} else {
			y3 = y2 - 1;
		}
		boolean tf = character.getSceneRef().isPermitted((short) x3, (short) y3);
		if (tf) {
			return new short[] { (short) x3, (short) y3 };
		} else {
			return null;
		}

	}

	public static short[] getLinePoint(short x1, short y1, short x2, short y2, VisibleObject character, int dis) {
		int x3 = 0;
		int y3 = 0;
		int max = 0;
		int maxX = Math.abs(x1 - x2);
		int maxY = Math.abs(y1 - y2);
		int biliX = 1;
		int biliY = 1;
		// 计算xy轴的步伐率
		if (maxX > maxY) {
			max = maxX;
			biliX = Math.round((float) maxX / maxY);
			if (maxY == 0 || biliX > 5) {
				biliX = 1;
			}
		} else {
			max = maxY;
			biliY = Math.round((float) maxY / maxX);
			if (maxY == 0 || biliY > 5) {
				biliY = 1;
			}
		}
		if (max > dis) {
			max = dis;
		}
		// 计算xy轴的步伐
		if (x1 == x2) {
			x3 = 0;
		} else if (x1 > x2) {
			x3 = -1 * biliX;
		} else {
			x3 = biliX;
		}
		if (y1 == y2) {
			y3 = 0;
		} else if (y1 > y2) {
			y3 = -1 * biliY;
		} else {
			y3 = biliY;
		}
		// 计算这条直线上的路径
		short x = 0;
		short y = 0;
		short tx = x1;
		short ty = y1;
		for (int i = 1; i < max; i++) {
			x = (short) (x1 + i * x3);
			y = (short) (y1 + i * y3);
			boolean tf = character.getSceneRef().isPermitted(x, y);
			if (!tf) {
				break;
			} else {
				int tempdis = (int) Math.round(Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1)));
				if (tempdis > dis) {
					break;
				}
				tx = x;
				ty = y;
			}
			if (x == x2) {
				x1 = x;
				x3 = 0;
				if (y3 < 0) {
					if (y < y2) {
						break;
					}
				} else {
					if (y > y2) {
						break;
					}
				}
			}
			if (y == y2) {
				y1 = y;
				y3 = 0;
				if (x3 < 0) {
					if (x < x2) {
						break;
					}
				} else {
					if (x > x2) {
						break;
					}
				}
			}
			if (y3 == 0 && x3 == 0) {
				break;
			}
		}
		return new short[] { tx, ty };
	}

	/**
	 * 
	 * @param p1
	 *            攻击者x 或者y坐标点
	 * @param p2
	 *            被攻击者x 或者y坐标点
	 * @param distance
	 *            击退距离
	 * @return
	 */
	public static short jituiPoint(int p1, int p2, int distance) {
		if (p1 > p2) {
			return (short) (p2 - distance);
		} else {
			return (short) (p2 + distance);
		}
	}

	/**
	 * 判断攻击范围是否有效
	 * 
	 * @param x
	 * @param y
	 * @param bgjX
	 * @param bgjY
	 * @param juli
	 *            距离 攻击范围
	 * @return true有效，false无效
	 */
	public static boolean atkIsEnable(short x, short y, short bgjX, short bgjY, short juli) {
		// int minX = x - juli;
		// int maxX = x + juli;
		// int minY = y - juli;
		// int maxY = y + juli;
		// if (bgjX < minX || bgjX > maxX || bgjY < minY || bgjY > maxY) {
		// return false;
		// }

		if (DistanceFormula.getDistance2(x, y, bgjX, bgjY) > Math.round(Math.sqrt(2 * juli * juli))) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个坐标是否在一个椭圆内(a>b>0)
	 * 
	 * @param a
	 *            长轴半径
	 * @param b
	 *            短轴半径
	 * @param x
	 *            圆心x
	 * @param y
	 *            圆心y
	 * @param tx
	 *            判断点x
	 * @param ty
	 *            判断点y
	 * @return
	 */
	public static boolean atkIsEnableOval(int a, int b, int x, int y, int tx, int ty) {
		if (a < b) {
			return false;
		}
		int tmpX = tx - x;
		int tmpY = ty - y;
		int res = tmpX * tmpX / (a * a) + tmpY * tmpY / (b * b);
		if (res > 1) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param a
	 * @param cx1
	 * @param cy1
	 * @param cx2
	 * @param cy2
	 * @param tx
	 * @param ty
	 * @return
	 */
	public static boolean atkIsEnableOvalC(int a, int cx1, int cy1, int cx2, int cy2, int tx, int ty) {
		int a2 = (int) (Math.sqrt((tx - cx1) * (tx - cx1) + (ty - cy1) * (ty - cy1)) + Math.sqrt((tx - cx2) * (tx - cx2) + (ty - cy2) * (ty - cy2)));
		if (a2 > 2 * a) {
			return false;
		}
		return true;
	}

	public static boolean atkIsEnableRectangle(int angle, float k, float b, int tx, int ty) {
		double d = (k * tx - ty + b) / Math.sqrt(k * k);
		d = Math.abs(d);
		if (d > angle) {
			return false;
		}
		return true;
	}

	public static boolean atkIsEnable2(short x, short y, short bgjX, short bgjY, int juli) {
		int minX = x - juli;
		int maxX = x + juli;
		int minY = y - juli;
		int maxY = y + juli;
		if (bgjX < minX || bgjX > maxX || bgjY < minY || bgjY > maxY) {
			return false;
		}

		// if (DistanceFormula.getDistance2(x, y, bgjX, bgjY) >
		// Math.round(Math.sqrt(2 * juli * juli))) {
		// return false;
		// }
		return true;
	}

	public static int[] atkIsEnablePoint(short x, short y, short bgjX, short bgjY, int juli) {
		int _x = 0, _y = 0;
		if (x < bgjX) {
			_x = x + (juli - 1);
		} else {
			_x = x - (juli - 1);
		}

		if (y < bgjY) {
			_y = y + (juli - 1);
		} else {
			_y = y - (juli - 1);
		}

		return new int[] { _x, _y };
	}

	// public static void main(String[] args) {
	// //5--攻击方id:265,x:66,y:58----被攻击方id:100014,x:61,y:56------------
	// atkIsEnable((short)66,(short)58,(short)61,(short)56,(short)5);
	// }

	/**
	 * 几率
	 * 
	 * @param
	 * @return
	 */
	public static boolean probabilityEnable(float probability) {
		int r = random.nextInt(100);
		if (r <= probability) {
			return true;
		}
		return false;
	}

	public static boolean probabilityEnable(int probability) {
		if (probability == 100) {
			return true;
		}
		if (probability == 0) {
			return false;
		}

		int r = random.nextInt(100);
		if (r <= probability) {
			return true;
		}
		return false;
	}

	/**
	 * 是否命中
	 * 
	 * @param attacker
	 * @param affecter
	 * @return
	 */
	public static boolean hit(VisibleObject attacker, VisibleObject affecter) {
		AppEventCtlor eventCtlor = AppEventCtlor.getInstance();
		int maybe=eventCtlor.getEvtFightFormula().getHit(attacker, affecter);
		return probabilityEnable(maybe);
	}

	/**
	 * 击退触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return
	 */
	public static boolean knockbackTriggerlv(int skillGrade, VisibleObject attacker, VisibleObject affecter) {
		int lv = AppEventCtlor.getInstance().getEvtSkillFormula().knockbackTriggerlv(skillGrade, attacker, affecter);
		return probabilityEnable(lv);
	}

	/**
	 * 
	 @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return
	 */
	public static boolean poisoningTriggerlv(int skillGrade, VisibleObject attacker, VisibleObject affecter) {
		int lv = AppEventCtlor.getInstance().getEvtSkillFormula().poisoningTriggerlv(skillGrade, attacker, affecter);
		return probabilityEnable(lv);
	}

	/**
	 * 点穴触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return
	 */
	public static boolean hitvitalpointTriggerlv(int skillGrade, VisibleObject attacker, VisibleObject affecter) {
		int lv = AppEventCtlor.getInstance().getEvtSkillFormula().hitvitalpointTriggerlv(skillGrade, attacker, affecter);
		return probabilityEnable(lv);
	}

	/**
	 * 绵骨触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public static boolean fengSpTriggerlv(int skillGrade, VisibleObject attacker, VisibleObject affecter) {
		int lv = AppEventCtlor.getInstance().getEvtSkillFormula().fengSpTriggerlv(skillGrade, attacker, affecter);
		return probabilityEnable(lv);
	}

	/**
	 * 化功触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public static boolean fengMpTriggerlv(int skillGrade, VisibleObject attacker, VisibleObject affecter) {
		int lv = AppEventCtlor.getInstance().getEvtSkillFormula().fengMpTriggerlv(skillGrade, attacker, affecter);
		return probabilityEnable(lv);
	}

	public static boolean bangpaiSkillLv(int skillGrade, int enemySkillGrade) {
		int lv = AppEventCtlor.getInstance().getEvtSkillFormula().bangpaiSkillTribLv(skillGrade, enemySkillGrade);
		return probabilityEnable(lv);
	}

	/**
	 * 吸星触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public static boolean xixueTriggerlv(int skillGrade, VisibleObject attacker, VisibleObject affecter) {
		int lv = AppEventCtlor.getInstance().getEvtSkillFormula().xixueTriggerlv(skillGrade, attacker, affecter);
		return probabilityEnable(lv);
	}

	/**
	 * 白手入刃触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public static boolean fengWuqiTriggerlv(int skillGrade, VisibleObject attacker, VisibleObject affecter) {
		int lv = AppEventCtlor.getInstance().getEvtSkillFormula().fengWuqiTriggerlv(skillGrade, attacker, affecter);
		return probabilityEnable(lv);
	}

	/**
	 * 脱袍卸甲触发几率 (自己心法等级-对方抗性心法等级)*2/100，最小为0，最大为100%
	 * 
	 * @param skillGrade
	 *            自己心法等级
	 * @param attacker
	 * @param affecter
	 * @return 百分比 例如：50% 返回的是50（所以不用在/100）
	 */
	public static boolean fengHujuTriggerlv(int skillGrade, VisibleObject attacker, VisibleObject affecter) {
		int lv = AppEventCtlor.getInstance().getEvtSkillFormula().fengHujuTriggerlv(skillGrade, attacker, affecter);
		return probabilityEnable(lv);
	}

}
