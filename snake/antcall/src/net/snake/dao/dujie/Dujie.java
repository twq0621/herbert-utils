package net.snake.dao.dujie;

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
	public Integer getZhenyuan() {
		return zhenyuan;
	}

	public void setZhenyuan(Integer zhenyuan) {
		this.zhenyuan = zhenyuan;
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
	
	public Dujie() {
	}

}
