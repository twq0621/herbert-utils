package net.snake.gamemodel.dujie.bean;

import net.snake.ibatis.IbatisEntity;

public final class HeroDujieData  implements IbatisEntity{
	private int heroId;
	private String name;
	private int num;
	private int roushen;
	private int process;
	private int isguard;
	private int gs;
	private int income;
	private int head;
	
public HeroDujieData() {
}

	public int getRoushen() {
		return roushen;
	}

	public void setRoushen(int roushen) {
		this.roushen = roushen;
	}

	public int getHeroId() {
		return heroId;
	}

	public void setHeroId(int heroId) {
		this.heroId = heroId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getProcess() {
		return process;
	}

	public void setProcess(int process) {
		this.process = process;
	}

	public int getIsguard() {
		return isguard;
	}

	public void setIsguard(int isguard) {
		this.isguard = isguard;
	}

	public int getGs() {
		return gs;
	}

	public void setGs(int gs) {
		this.gs = gs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

}
