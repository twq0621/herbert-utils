package net.snake.gamemodel.dujie.bean;

import net.snake.ibatis.IbatisEntity;


public class DujieAdd  implements IbatisEntity{
	private int id;
	private String add_hp;
	private String add_mp;
	private String add_attack;
	private String add_defence;
	private String add_crt;
	private String add_dodge;
	private String add_hit;
	
	private float[] propAdd4Popsigner1=new float[7];
	private float[] propAdd4Popsigner2=new float[7];
	private float[] propAdd4Popsigner3=new float[7];
	private float[] propAdd4Popsigner4=new float[7];
	
	public float getAddhpByPopsigner(int pop){
		return propValue(pop,0);
	}
	public float getAddmpByPopsigner(int pop){
		return propValue(pop,1);
	}
	public float getAddattackByPopsigner(int pop){
		return propValue(pop,2);
	}
	public float getAdddefenceByPopsigner(int pop){
		return propValue(pop,3);
	}
	public float getAddcrtByPopsigner(int pop){
		return propValue(pop,4);
	}
	public float getAdddodgeByPopsigner(int pop){
		return propValue(pop,5);
	}
	public float getAddhitByPopsigner(int pop){
		return propValue(pop,6);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdd_hp() {
		return add_hp;
	}

	public void setAdd_hp(String add_hp) {
		this.add_hp = add_hp;
	}

	public String getAdd_mp() {
		return add_mp;
	}

	public void setAdd_mp(String add_mp) {
		this.add_mp = add_mp;
	}

	public String getAdd_attack() {
		return add_attack;
	}

	public void setAdd_attack(String add_attack) {
		this.add_attack = add_attack;
	}

	public String getAdd_defence() {
		return add_defence;
	}

	public void setAdd_defence(String add_defence) {
		this.add_defence = add_defence;
	}

	public String getAdd_crt() {
		return add_crt;
	}

	public void setAdd_crt(String add_crt) {
		this.add_crt = add_crt;
	}

	public String getAdd_dodge() {
		return add_dodge;
	}

	public void setAdd_dodge(String add_dodge) {
		this.add_dodge = add_dodge;
	}

	public String getAdd_hit() {
		return add_hit;
	}

	public void setAdd_hit(String add_hit) {
		this.add_hit = add_hit;
	}
	
	public void init(){
		parsStr(add_hp,0);
		parsStr(add_mp,1);
		parsStr(add_attack,2);
		parsStr(add_defence,3);
		parsStr(add_crt,4);
		parsStr(add_dodge,5);
		parsStr(add_hit,6);
	}
	
	private float propValue(int pop,int propIndex){
		switch (pop) {
		//少林派 - 古冥
		case 1:
			return propAdd4Popsigner1[propIndex];
		//全真教 - 浮屠
		case 2:
			return propAdd4Popsigner2[propIndex];
		//古墓派 - 云月
		case 3:
			return propAdd4Popsigner3[propIndex];
		//桃花岛 - 缈玉
		case 4:
			return propAdd4Popsigner4[propIndex];
		}
		return 0;
	}
	
	private void parsStr(String string,int propIndex){
		String[] values=string.split(";");
		
		propAdd4Popsigner1[propIndex]=Float.parseFloat(values[0]);
		propAdd4Popsigner2[propIndex]=Float.parseFloat(values[1]);
		propAdd4Popsigner3[propIndex]=Float.parseFloat(values[2]);
		propAdd4Popsigner4[propIndex]=Float.parseFloat(values[3]);
	}
}
