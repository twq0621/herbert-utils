package net.snake.gamemodel.dujie.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个渡劫关卡。
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class Dujie {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getAdd_hp() {
		return add_hp;
	}

	public void setAdd_hp(Integer add_hp) {
		this.add_hp = add_hp;
	}

	public Integer getAdd_mp() {
		return add_mp;
	}

	public void setAdd_mp(Integer add_mp) {
		this.add_mp = add_mp;
	}

	public Integer getAdd_attack() {
		return add_attack;
	}

	public void setAdd_attack(Integer add_attack) {
		this.add_attack = add_attack;
	}

	public Integer getAdd_defence() {
		return add_defence;
	}

	public void setAdd_defence(Integer add_defence) {
		this.add_defence = add_defence;
	}

	public Integer getAdd_sp() {
		return add_sp;
	}

	public void setAdd_sp(Integer add_sp) {
		this.add_sp = add_sp;
	}

	public Integer getAdd_as() {
		return add_as;
	}

	public void setAdd_as(Integer add_as) {
		this.add_as = add_as;
	}

	public Integer getAdd_ms() {
		return add_ms;
	}

	public void setAdd_ms(Integer add_ms) {
		this.add_ms = add_ms;
	}

	public Integer getAdd_crt() {
		return add_crt;
	}

	public void setAdd_crt(Integer add_crt) {
		this.add_crt = add_crt;
	}

	public Integer getAdd_dodge() {
		return add_dodge;
	}

	public void setAdd_dodge(Integer add_dodge) {
		this.add_dodge = add_dodge;
	}

	public Integer getAdd_hit() {
		return add_hit;
	}

	public void setAdd_hit(Integer add_hit) {
		this.add_hit = add_hit;
	}

	public Integer getMax_point() {
		return max_point;
	}

	public void setMax_point(Integer max_point) {
		this.max_point = max_point;
	}

	public Integer getLvl_limit() {
		return lvl_limit;
	}

	public void setLvl_limit(Integer lvl_limit) {
		this.lvl_limit = lvl_limit;
	}

	public Integer getAdd_exp() {
		return add_exp;
	}

	public void setAdd_exp(Integer add_exp) {
		this.add_exp = add_exp;
	}

	public Integer getAdd_copper() {
		return add_copper;
	}

	public void setAdd_copper(Integer add_copper) {
		this.add_copper = add_copper;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public Integer getScene() {
		return scene;
	}

	public void setScene(Integer scene) {
		this.scene = scene;
	}

	public String getAdd_skill_1() {
		return add_skill_1;
	}

	public void setAdd_skill_1(String add_skill_1) {
		this.add_skill_1 = add_skill_1;
	}

	public String getAdd_skill_2() {
		return add_skill_2;
	}

	public void setAdd_skill_2(String add_skill_2) {
		this.add_skill_2 = add_skill_2;
	}

	public String getAdd_skill_3() {
		return add_skill_3;
	}

	public void setAdd_skill_3(String add_skill_3) {
		this.add_skill_3 = add_skill_3;
	}

	public String getAdd_skill_4() {
		return add_skill_4;
	}

	public void setAdd_skill_4(String add_skill_4) {
		this.add_skill_4 = add_skill_4;
	}

	public int[] getSkillUpdate(int pop) {
		switch (pop) {
		case 1:
			return skills_1;
		case 2:
			return skills_2;
		case 3:
			return skills_3;
		case 4:
			return skills_4;
		}
		return null;
	}

	private Integer id;
	private String name;
	private Integer scene;
	private String desc;
	private Integer add_hp;
	private Integer add_mp;
	private Integer add_attack;
	private Integer add_defence;
	private Integer add_sp;
	private Integer add_as;
	private Integer add_ms;
	private Integer add_crt;
	private Integer add_dodge;
	private Integer add_hit;
	private Integer max_point;
	private Integer lvl_limit;
	private Integer add_exp;
	private Integer add_copper;
	private String goods;
	private String add_skill_1;
	private String add_skill_2;
	private String add_skill_3;
	private String add_skill_4;
	private Integer zhenyuan;
	private String hurt;

	private int[] skills_1 = null;
	private int[] skills_2 = null;
	private int[] skills_3 = null;
	private int[] skills_4 = null;

	private List<int[]> allHurts = new ArrayList<int[]>();

	public Dujie() {
	}

	public void init() {
		if (!add_skill_1.trim().equals("")) {
			String[] skill_add_1 = add_skill_1.split(";");
			skills_1 = new int[skill_add_1.length * 2];
			fillSkillAdd(skills_1, skill_add_1);
		}
		if (!add_skill_2.trim().equals("")) {
			String[] skill_add_2 = add_skill_2.split(";");
			skills_2 = new int[skill_add_2.length * 2];
			fillSkillAdd(skills_2, skill_add_2);
		}

		if (!add_skill_3.trim().equals("")) {
			String[] skill_add_3 = add_skill_3.split(";");
			skills_3 = new int[skill_add_3.length * 2];
			fillSkillAdd(skills_3, skill_add_3);
		}

		if (!add_skill_4.trim().equals("")) {
			String[] skill_add_4 = add_skill_4.split(";");
			skills_4 = new int[skill_add_4.length * 2];
			fillSkillAdd(skills_4, skill_add_4);
		}

		if (hurt == null) {
			return;
		}

		String[] hurtStr = hurt.split(";");
		for (int i = 0; i < hurtStr.length; i++) {
			String[] data = hurtStr[i].split("#");
			int[] ints = new int[3];
			ints[0] = Integer.parseInt(data[0]);
			ints[1] = Integer.parseInt(data[1]);
			ints[2] = Integer.parseInt(data[2]);

			allHurts.add(ints);
		}
	}

	private void fillSkillAdd(int[] skill_pop, String[] skill_add) {
		for (int i = 0; i < skill_add.length; i++) {
			String[] values = skill_add[i].split("#");
			skill_pop[i * 2] = Integer.parseInt(values[0]);
			skill_pop[i * 2 + 1] = Integer.parseInt(values[1]);
		}
	}

	public Integer getZhenyuan() {
		return zhenyuan;
	}

	public void setZhenyuan(Integer zhenyuan) {
		this.zhenyuan = zhenyuan;
	}

	public String getHurt() {
		return hurt;
	}

	public void setHurt(String hurt) {
		this.hurt = hurt;
	}

	public List<int[]> getAllHurtData() {
		return allHurts;
	}

}
