package net.snake.gamemodel.config.bean;

import net.snake.ibatis.IbatisEntity;

public class ConfigParam implements IbatisEntity {

	/**
	 * t_config_param.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 每次跳跃消耗的体力值 t_config_param.f_sp_per_jump
	 * 
	 */
	private Integer spPerJump;
	/**
	 * 跳跃冷却时间 t_config_param.f_jump_cold_time
	 * 
	 */
	private Integer jumpColdTime;
	/**
	 * 该字段尚未生效 t_config_param.f_horse_follow_distance
	 * 
	 */
	private Integer horseFollowDistance;
	/**
	 * 被玩家杀死后，对坐骑的死亡惩罚 t_config_param.f_horse_livingness_punish1
	 * 
	 */
	private Integer horseLivingnessPunish1;
	/**
	 * 被怪物杀死后，对坐骑的死亡惩罚 t_config_param.f_horse_livingness_punish2
	 * 
	 */
	private Integer horseLivingnessPunish2;
	/**
	 * 骑乘时扣除1点活力需要多长时间(秒计) t_config_param.f_horse_ride_consume
	 * 
	 */
	private Integer horseRideConsume;
	/**
	 * 展示时扣除1点活力需要多长时间(秒计) t_config_param.f_horse_show_consume
	 * 
	 */
	private Integer horseShowConsume;
	/**
	 * 休息时恢复1点活力需要多长时间(秒计) t_config_param.f_horse_rest_resume
	 * 
	 */
	private Integer horseRestResume;
	/**
	 * 打坐经验倍数（正常为1 默认值） t_config_param.f_dazuo_exp
	 * 
	 */
	private Short dazuoExp;
	/**
	 * 打坐时增加的真气倍数（正常为1默认值 ）  t_config_param.f_dazuo_zhenqi
	 * 
	 */
	private Short dazuoZhenqi;
	/**
	 * 打怪获得物品爆率倍数（正常为1 默认值 ）  t_config_param.f_baolv
	 * 
	 */
	private Short baolv;
	/**
	 * 打怪经验倍数（正常为0默认值） t_config_param.f_daguai_exp
	 * 
	 */
	private Short daguaiExp;
	/**
	 * 在传给客户端角色名和帮会名时，是否需要在名称前加区号 0不显示/1显示 t_config_param.show_server_id
	 * 
	 */
	private Integer showServerId;
	/**
	 * t_config_param.f_feasttime_exp
	 * 
	 */
	private String feasttimeExp;
	/**
	 * t_config_param.f_bulletin
	 * 
	 */
	private String bulletin;
	
	private int presecLoseSp;
	private float getspCoef1;
	private float getspCoef2;
	private float getspCoef3;
	private float getspCoef4;
	private float getbespCoef1;
	private float getbespCoef2;
	private float getbespCoef3;
	private float getbespCoef4;
	private int pin1;
	private int pin2;
	private int pin3;
	private int pin4;
	private int pin5;

	/**
	 * t_config_param.f_id
	 * @return  the value of t_config_param.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_config_param.f_id
	 * @param id  the value for t_config_param.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 每次跳跃消耗的体力值 t_config_param.f_sp_per_jump
	 * @return  the value of t_config_param.f_sp_per_jump
	 * 
	 */
	public Integer getSpPerJump() {
		return spPerJump;
	}

	/**
	 * 每次跳跃消耗的体力值 t_config_param.f_sp_per_jump
	 * @param spPerJump  the value for t_config_param.f_sp_per_jump
	 * 
	 */
	public void setSpPerJump(Integer spPerJump) {
		this.spPerJump = spPerJump;
	}

	/**
	 * 跳跃冷却时间 t_config_param.f_jump_cold_time
	 * @return  the value of t_config_param.f_jump_cold_time
	 * 
	 */
	public Integer getJumpColdTime() {
		return jumpColdTime;
	}

	/**
	 * 跳跃冷却时间 t_config_param.f_jump_cold_time
	 * @param jumpColdTime  the value for t_config_param.f_jump_cold_time
	 * 
	 */
	public void setJumpColdTime(Integer jumpColdTime) {
		this.jumpColdTime = jumpColdTime;
	}

	/**
	 * 该字段尚未生效 t_config_param.f_horse_follow_distance
	 * @return  the value of t_config_param.f_horse_follow_distance
	 * 
	 */
	public Integer getHorseFollowDistance() {
		return horseFollowDistance;
	}

	/**
	 * 该字段尚未生效 t_config_param.f_horse_follow_distance
	 * @param horseFollowDistance  the value for t_config_param.f_horse_follow_distance
	 * 
	 */
	public void setHorseFollowDistance(Integer horseFollowDistance) {
		this.horseFollowDistance = horseFollowDistance;
	}

	/**
	 * 被玩家杀死后，对坐骑的死亡惩罚 t_config_param.f_horse_livingness_punish1
	 * @return  the value of t_config_param.f_horse_livingness_punish1
	 * 
	 */
	public Integer getHorseLivingnessPunish1() {
		return horseLivingnessPunish1;
	}

	/**
	 * 被玩家杀死后，对坐骑的死亡惩罚 t_config_param.f_horse_livingness_punish1
	 * @param horseLivingnessPunish1  the value for t_config_param.f_horse_livingness_punish1
	 * 
	 */
	public void setHorseLivingnessPunish1(Integer horseLivingnessPunish1) {
		this.horseLivingnessPunish1 = horseLivingnessPunish1;
	}

	/**
	 * 被怪物杀死后，对坐骑的死亡惩罚 t_config_param.f_horse_livingness_punish2
	 * @return  the value of t_config_param.f_horse_livingness_punish2
	 * 
	 */
	public Integer getHorseLivingnessPunish2() {
		return horseLivingnessPunish2;
	}

	/**
	 * 被怪物杀死后，对坐骑的死亡惩罚 t_config_param.f_horse_livingness_punish2
	 * @param horseLivingnessPunish2  the value for t_config_param.f_horse_livingness_punish2
	 * 
	 */
	public void setHorseLivingnessPunish2(Integer horseLivingnessPunish2) {
		this.horseLivingnessPunish2 = horseLivingnessPunish2;
	}

	/**
	 * 骑乘时扣除1点活力需要多长时间(秒计) t_config_param.f_horse_ride_consume
	 * @return  the value of t_config_param.f_horse_ride_consume
	 * 
	 */
	public Integer getHorseRideConsume() {
		return horseRideConsume;
	}

	/**
	 * 骑乘时扣除1点活力需要多长时间(秒计) t_config_param.f_horse_ride_consume
	 * @param horseRideConsume  the value for t_config_param.f_horse_ride_consume
	 * 
	 */
	public void setHorseRideConsume(Integer horseRideConsume) {
		this.horseRideConsume = horseRideConsume;
	}

	/**
	 * 展示时扣除1点活力需要多长时间(秒计) t_config_param.f_horse_show_consume
	 * @return  the value of t_config_param.f_horse_show_consume
	 * 
	 */
	public Integer getHorseShowConsume() {
		return horseShowConsume;
	}

	/**
	 * 展示时扣除1点活力需要多长时间(秒计) t_config_param.f_horse_show_consume
	 * @param horseShowConsume  the value for t_config_param.f_horse_show_consume
	 * 
	 */
	public void setHorseShowConsume(Integer horseShowConsume) {
		this.horseShowConsume = horseShowConsume;
	}

	/**
	 * 休息时恢复1点活力需要多长时间(秒计) t_config_param.f_horse_rest_resume
	 * @return  the value of t_config_param.f_horse_rest_resume
	 * 
	 */
	public Integer getHorseRestResume() {
		return horseRestResume;
	}

	/**
	 * 休息时恢复1点活力需要多长时间(秒计) t_config_param.f_horse_rest_resume
	 * @param horseRestResume  the value for t_config_param.f_horse_rest_resume
	 * 
	 */
	public void setHorseRestResume(Integer horseRestResume) {
		this.horseRestResume = horseRestResume;
	}

	/**
	 * 打坐经验倍数（正常为1 默认值） t_config_param.f_dazuo_exp
	 * @return  the value of t_config_param.f_dazuo_exp
	 * 
	 */
	public Short getDazuoExp() {
		return dazuoExp;
	}

	/**
	 * 打坐经验倍数（正常为1 默认值） t_config_param.f_dazuo_exp
	 * @param dazuoExp  the value for t_config_param.f_dazuo_exp
	 * 
	 */
	public void setDazuoExp(Short dazuoExp) {
		this.dazuoExp = dazuoExp;
	}

	/**
	 * 打坐时增加的真气倍数（正常为1默认值 ）  t_config_param.f_dazuo_zhenqi
	 * @return  the value of t_config_param.f_dazuo_zhenqi
	 * 
	 */
	public Short getDazuoZhenqi() {
		return dazuoZhenqi;
	}

	/**
	 * 打坐时增加的真气倍数（正常为1默认值 ）  t_config_param.f_dazuo_zhenqi
	 * @param dazuoZhenqi  the value for t_config_param.f_dazuo_zhenqi
	 * 
	 */
	public void setDazuoZhenqi(Short dazuoZhenqi) {
		this.dazuoZhenqi = dazuoZhenqi;
	}

	/**
	 * 打怪获得物品爆率倍数（正常为1 默认值 ）  t_config_param.f_baolv
	 * @return  the value of t_config_param.f_baolv
	 * 
	 */
	public Short getBaolv() {
		return baolv;
	}

	/**
	 * 打怪获得物品爆率倍数（正常为1 默认值 ）  t_config_param.f_baolv
	 * @param baolv  the value for t_config_param.f_baolv
	 * 
	 */
	public void setBaolv(Short baolv) {
		this.baolv = baolv;
	}

	/**
	 * 打怪经验倍数（正常为0默认值） t_config_param.f_daguai_exp
	 * @return  the value of t_config_param.f_daguai_exp
	 * 
	 */
	public Short getDaguaiExp() {
		return daguaiExp;
	}

	/**
	 * 打怪经验倍数（正常为0默认值） t_config_param.f_daguai_exp
	 * @param daguaiExp  the value for t_config_param.f_daguai_exp
	 * 
	 */
	public void setDaguaiExp(Short daguaiExp) {
		this.daguaiExp = daguaiExp;
	}

	/**
	 * 在传给客户端角色名和帮会名时，是否需要在名称前加区号 0不显示/1显示 t_config_param.show_server_id
	 * @return  the value of t_config_param.show_server_id
	 * 
	 */
	public Integer getShowServerId() {
		return showServerId;
	}

	/**
	 * 在传给客户端角色名和帮会名时，是否需要在名称前加区号 0不显示/1显示 t_config_param.show_server_id
	 * @param showServerId  the value for t_config_param.show_server_id
	 * 
	 */
	public void setShowServerId(Integer showServerId) {
		this.showServerId = showServerId;
	}

	/**
	 * t_config_param.f_feasttime_exp
	 * @return  the value of t_config_param.f_feasttime_exp
	 * 
	 */
	public String getFeasttimeExp() {
		return feasttimeExp;
	}

	/**
	 * t_config_param.f_feasttime_exp
	 * @param feasttimeExp  the value for t_config_param.f_feasttime_exp
	 * 
	 */
	public void setFeasttimeExp(String feasttimeExp) {
		this.feasttimeExp = feasttimeExp;
	}

	/**
	 * t_config_param.f_bulletin
	 * @return  the value of t_config_param.f_bulletin
	 * 
	 */
	public String getBulletin() {
		return bulletin;
	}

	/**
	 * t_config_param.f_bulletin
	 * @param bulletin  the value for t_config_param.f_bulletin
	 * 
	 */
	public void setBulletin(String bulletin) {
		this.bulletin = bulletin;
	}

	public boolean isShowMyServerId(){
		if(getShowServerId()==0){
			return false;
		}
		return true;
	}

	public int getPresecLoseSp() {
		return presecLoseSp;
	}

	public void setPresecLoseSp(int presecLoseSp) {
		this.presecLoseSp = presecLoseSp;
	}

	public float getGetspCoef1() {
		return getspCoef1;
	}

	public void setGetspCoef1(float getspCoef1) {
		this.getspCoef1 = getspCoef1;
	}

	public float getGetspCoef2() {
		return getspCoef2;
	}

	public void setGetspCoef2(float getspCoef2) {
		this.getspCoef2 = getspCoef2;
	}

	public float getGetspCoef3() {
		return getspCoef3;
	}

	public void setGetspCoef3(float getspCoef3) {
		this.getspCoef3 = getspCoef3;
	}

	public float getGetspCoef4() {
		return getspCoef4;
	}

	public void setGetspCoef4(float getspCoef4) {
		this.getspCoef4 = getspCoef4;
	}

	public float getGetbespCoef1() {
		return getbespCoef1;
	}

	public void setGetbespCoef1(float getbespCoef1) {
		this.getbespCoef1 = getbespCoef1;
	}

	public float getGetbespCoef2() {
		return getbespCoef2;
	}

	public void setGetbespCoef2(float getbespCoef2) {
		this.getbespCoef2 = getbespCoef2;
	}

	public float getGetbespCoef3() {
		return getbespCoef3;
	}

	public void setGetbespCoef3(float getbespCoef3) {
		this.getbespCoef3 = getbespCoef3;
	}

	public float getGetbespCoef4() {
		return getbespCoef4;
	}

	public void setGetbespCoef4(float getbespCoef4) {
		this.getbespCoef4 = getbespCoef4;
	}

	public int getPin1() {
		return pin1;
	}

	public void setPin1(int pin1) {
		this.pin1 = pin1;
	}

	public int getPin2() {
		return pin2;
	}

	public void setPin2(int pin2) {
		this.pin2 = pin2;
	}

	public int getPin3() {
		return pin3;
	}

	public void setPin3(int pin3) {
		this.pin3 = pin3;
	}

	public int getPin4() {
		return pin4;
	}

	public void setPin4(int pin4) {
		this.pin4 = pin4;
	}

	public int getPin5() {
		return pin5;
	}

	public void setPin5(int pin5) {
		this.pin5 = pin5;
	}
	
}
