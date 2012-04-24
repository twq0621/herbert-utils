package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class Personals implements IbatisEntity {

	/**
	 * 角色id t_personals.f_character_id
	 */
	private Integer characterId;
	/**
	 * 昵称 t_personals.f_nicheng
	 */
	private String nicheng;
	/**
	 * 生日 t_personals.f_shengri
	 */
	private String shengri;
	/**
	 * 1男0女 t_personals.f_sex
	 */
	private Integer sex;
	/**
	 * 血型 t_personals.f_xiexing
	 */
	private String xiexing;
	/**
	 * 省份 t_personals.f_shengfen
	 */
	private String shengfen;
	/**
	 * 城市 t_personals.f_chengshi
	 */
	private String chengshi;
	/**
	 * 职业 t_personals.f_zhiye
	 */
	private String zhiye;
	/**
	 * 院校 t_personals.f_yuanxiao
	 */
	private String yuanxiao;
	/**
	 * 邮箱 t_personals.f_mail
	 */
	private String mail;
	/**
	 * 地址 t_personals.f_http
	 */
	private String http;
	/**
	 * qq t_personals.f_qq
	 */
	private String qq;
	/**
	 * 联系号码 t_personals.f_lianxihaoma
	 */
	private String lianxihaoma;
	/**
	 * 1-完全保密 2-向好友开放 3-完全开放 t_personals.f_baomichengdu
	 */
	private Integer baomichengdu;
	/**
	 * 上传文件大小 t_personals.f_size
	 */
	private String size;
	/**
	 * 相片地址 t_personals.f_url
	 */
	private String url;
	/**
	 * 0未审核1通过审核 t_personals.f_shenhe
	 */
	private Integer shenhe;
	/**
	 * 年龄 t_personals.f_age
	 */
	private Integer age;
	/**
	 * 上传时间 t_personals.f_update_time
	 */
	private String updateTime;

	/**
	 * 角色id t_personals.f_character_id
	 * 
	 * @return the value of t_personals.f_character_id
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_personals.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_personals.f_character_id
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 昵称 t_personals.f_nicheng
	 * 
	 * @return the value of t_personals.f_nicheng
	 */
	public String getNicheng() {
		return nicheng;
	}

	/**
	 * 昵称 t_personals.f_nicheng
	 * 
	 * @param nicheng
	 *            the value for t_personals.f_nicheng
	 */
	public void setNicheng(String nicheng) {
		this.nicheng = nicheng;
	}

	/**
	 * 生日 t_personals.f_shengri
	 * 
	 * @return the value of t_personals.f_shengri
	 */
	public String getShengri() {
		return shengri;
	}

	/**
	 * 生日 t_personals.f_shengri
	 * 
	 * @param shengri
	 *            the value for t_personals.f_shengri
	 */
	public void setShengri(String shengri) {
		this.shengri = shengri;
	}

	/**
	 * 1男0女 t_personals.f_sex
	 * 
	 * @return the value of t_personals.f_sex
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 1男0女 t_personals.f_sex
	 * 
	 * @param sex
	 *            the value for t_personals.f_sex
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 血型 t_personals.f_xiexing
	 * 
	 * @return the value of t_personals.f_xiexing
	 */
	public String getXiexing() {
		return xiexing;
	}

	/**
	 * 血型 t_personals.f_xiexing
	 * 
	 * @param xiexing
	 *            the value for t_personals.f_xiexing
	 */
	public void setXiexing(String xiexing) {
		this.xiexing = xiexing;
	}

	/**
	 * 省份 t_personals.f_shengfen
	 * 
	 * @return the value of t_personals.f_shengfen
	 */
	public String getShengfen() {
		return shengfen;
	}

	/**
	 * 省份 t_personals.f_shengfen
	 * 
	 * @param shengfen
	 *            the value for t_personals.f_shengfen
	 */
	public void setShengfen(String shengfen) {
		this.shengfen = shengfen;
	}

	/**
	 * 城市 t_personals.f_chengshi
	 * 
	 * @return the value of t_personals.f_chengshi
	 */
	public String getChengshi() {
		return chengshi;
	}

	/**
	 * 城市 t_personals.f_chengshi
	 * 
	 * @param chengshi
	 *            the value for t_personals.f_chengshi
	 */
	public void setChengshi(String chengshi) {
		this.chengshi = chengshi;
	}

	/**
	 * 职业 t_personals.f_zhiye
	 * 
	 * @return the value of t_personals.f_zhiye
	 */
	public String getZhiye() {
		return zhiye;
	}

	/**
	 * 职业 t_personals.f_zhiye
	 * 
	 * @param zhiye
	 *            the value for t_personals.f_zhiye
	 */
	public void setZhiye(String zhiye) {
		this.zhiye = zhiye;
	}

	/**
	 * 院校 t_personals.f_yuanxiao
	 * 
	 * @return the value of t_personals.f_yuanxiao
	 */
	public String getYuanxiao() {
		return yuanxiao;
	}

	/**
	 * 院校 t_personals.f_yuanxiao
	 * 
	 * @param yuanxiao
	 *            the value for t_personals.f_yuanxiao
	 */
	public void setYuanxiao(String yuanxiao) {
		this.yuanxiao = yuanxiao;
	}

	/**
	 * 邮箱 t_personals.f_mail
	 * 
	 * @return the value of t_personals.f_mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * 邮箱 t_personals.f_mail
	 * 
	 * @param mail
	 *            the value for t_personals.f_mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 地址 t_personals.f_http
	 * 
	 * @return the value of t_personals.f_http
	 */
	public String getHttp() {
		return http;
	}

	/**
	 * 地址 t_personals.f_http
	 * 
	 * @param http
	 *            the value for t_personals.f_http
	 */
	public void setHttp(String http) {
		this.http = http;
	}

	/**
	 * qq t_personals.f_qq
	 * 
	 * @return the value of t_personals.f_qq
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * qq t_personals.f_qq
	 * 
	 * @param qq
	 *            the value for t_personals.f_qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 联系号码 t_personals.f_lianxihaoma
	 * 
	 * @return the value of t_personals.f_lianxihaoma
	 */
	public String getLianxihaoma() {
		return lianxihaoma;
	}

	/**
	 * 联系号码 t_personals.f_lianxihaoma
	 * 
	 * @param lianxihaoma
	 *            the value for t_personals.f_lianxihaoma
	 */
	public void setLianxihaoma(String lianxihaoma) {
		this.lianxihaoma = lianxihaoma;
	}

	/**
	 * 1-完全保密 2-向好友开放 3-完全开放 t_personals.f_baomichengdu
	 * 
	 * @return the value of t_personals.f_baomichengdu
	 */
	public Integer getBaomichengdu() {
		return baomichengdu;
	}

	/**
	 * 1-完全保密 2-向好友开放 3-完全开放 t_personals.f_baomichengdu
	 * 
	 * @param baomichengdu
	 *            the value for t_personals.f_baomichengdu
	 */
	public void setBaomichengdu(Integer baomichengdu) {
		this.baomichengdu = baomichengdu;
	}

	/**
	 * 上传文件大小 t_personals.f_size
	 * 
	 * @return the value of t_personals.f_size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * 上传文件大小 t_personals.f_size
	 * 
	 * @param size
	 *            the value for t_personals.f_size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * 相片地址 t_personals.f_url
	 * 
	 * @return the value of t_personals.f_url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 相片地址 t_personals.f_url
	 * 
	 * @param url
	 *            the value for t_personals.f_url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 0未审核1通过审核 t_personals.f_shenhe
	 * 
	 * @return the value of t_personals.f_shenhe
	 */
	public Integer getShenhe() {
		return shenhe;
	}

	/**
	 * 0未审核1通过审核 t_personals.f_shenhe
	 * 
	 * @param shenhe
	 *            the value for t_personals.f_shenhe
	 */
	public void setShenhe(Integer shenhe) {
		this.shenhe = shenhe;
	}

	/**
	 * 年龄 t_personals.f_age
	 * 
	 * @return the value of t_personals.f_age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 年龄 t_personals.f_age
	 * 
	 * @param age
	 *            the value for t_personals.f_age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * 上传时间 t_personals.f_update_time
	 * 
	 * @return the value of t_personals.f_update_time
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 上传时间 t_personals.f_update_time
	 * 
	 * @param updateTime
	 *            the value for t_personals.f_update_time
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	// 添加3个临时字段用来保存花和表情图表心情
	private int huxcount;
	private String biaoqingtubiao;
	private String xingqing;

	public int getHuxcount() {
		return huxcount;
	}

	public void setHuxcount(int huxcount) {
		this.huxcount = huxcount;
	}

	public String getBiaoqingtubiao() {
		return biaoqingtubiao;
	}

	public void setBiaoqingtubiao(String biaoqingtubiao) {
		this.biaoqingtubiao = biaoqingtubiao;
	}

	public String getXingqing() {
		return xingqing;
	}

	public void setXingqing(String xingqing) {
		this.xingqing = xingqing;
	}
}
