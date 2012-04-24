package net.snake.gamemodel.dujie.bean;

import net.snake.ibatis.IbatisEntity;

public class Hufazhen implements IbatisEntity {
	private Integer id;
	private String name;
	private Integer need;
	private Integer need_shentong;
	private Integer add_ap;
	private Integer add_dp;
	private Integer add_hp;
	private Integer add_crt;
	private Integer add_ht;
	private Integer add_dodge;
	private Integer add_as;
	private Integer add_ms;
	private Integer shentong_buff;
	
	private Integer tianjieId;

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

	public Integer getNeed() {
		return need;
	}

	public void setNeed(Integer need) {
		this.need = need;
	}

	public Integer getNeed_shentong() {
		return need_shentong;
	}

	public void setNeed_shentong(Integer need_shentong) {
		this.need_shentong = need_shentong;
	}

	public Integer getAdd_ap() {
		return add_ap;
	}

	public void setAdd_ap(Integer add_ap) {
		this.add_ap = add_ap;
	}

	public Integer getAdd_dp() {
		return add_dp;
	}

	public void setAdd_dp(Integer add_dp) {
		this.add_dp = add_dp;
	}

	public Integer getAdd_hp() {
		return add_hp;
	}

	public void setAdd_hp(Integer add_hp) {
		this.add_hp = add_hp;
	}

	public Integer getAdd_crt() {
		return add_crt;
	}

	public void setAdd_crt(Integer add_crt) {
		this.add_crt = add_crt;
	}

	public Integer getAdd_ht() {
		return add_ht;
	}

	public void setAdd_ht(Integer add_ht) {
		this.add_ht = add_ht;
	}

	public Integer getAdd_dodge() {
		return add_dodge;
	}

	public void setAdd_dodge(Integer add_dodge) {
		this.add_dodge = add_dodge;
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

	public Integer getShentong_buff() {
		return shentong_buff;
	}

	public void setShentong_buff(Integer shentong_buff) {
		this.shentong_buff = shentong_buff;
	}

	public Integer getTianjieId() {
		return tianjieId;
	}

	public void setTianjieId(Integer tianjieid) {
		this.tianjieId = tianjieid;
	}
}
