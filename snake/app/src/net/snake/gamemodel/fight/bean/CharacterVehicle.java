package net.snake.gamemodel.fight.bean;

import net.snake.gamemodel.fight.persistence.GongchengVehicleManager;
import net.snake.ibatis.IbatisEntity;


public class CharacterVehicle  implements IbatisEntity{
    /**
     *  t_character_vehicle.f_id
     *
     * 
     */
    private Integer id;

    /**
     * 关联角色id t_character_vehicle.f_character_id
     *
     * 
     */
    private Integer characterId;

    /**
     * 攻城车类别 t_character_vehicle.f_vehicle_id
     *
     * 
     */
    private Integer vehicleId;

    /**
     * 攻城炮弹数量 t_character_vehicle.f_vehicle_count
     *
     * 
     */
    private Integer vehicleCount;

    /**
     *  t_character_vehicle.f_id
     *
     * @return the value of t_character_vehicle.f_id
     *
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     *  t_character_vehicle.f_id
     *
     * @param id the value for t_character_vehicle.f_id
     *
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 关联角色id t_character_vehicle.f_character_id
     *
     * @return the value of t_character_vehicle.f_character_id
     *
     * 
     */
    public Integer getCharacterId() {
        return characterId;
    }

    /**
     * 关联角色id t_character_vehicle.f_character_id
     *
     * @param characterId the value for t_character_vehicle.f_character_id
     *
     * 
     */
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    /**
     * 攻城车类别 t_character_vehicle.f_vehicle_id
     *
     * @return the value of t_character_vehicle.f_vehicle_id
     *
     * 
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * 攻城车类别 t_character_vehicle.f_vehicle_id
     *
     * @param vehicleId the value for t_character_vehicle.f_vehicle_id
     *
     * 
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 攻城炮弹数量 t_character_vehicle.f_vehicle_count
     *
     * @return the value of t_character_vehicle.f_vehicle_count
     *
     * 
     */
    public Integer getVehicleCount() {
        return vehicleCount;
    }

    /**
     * 攻城炮弹数量 t_character_vehicle.f_vehicle_count
     *
     * @param vehicleCount the value for t_character_vehicle.f_vehicle_count
     *
     * 
     */
    public void setVehicleCount(Integer vehicleCount) {
        this.vehicleCount = vehicleCount;
    }
    
    private GongchengVehicle gongchengVehicle;
    public GongchengVehicle getGongchengVehicle(){
    	if(gongchengVehicle==null){
    		this.gongchengVehicle=GongchengVehicleManager.getInstance().getGongchengVehicleByVehicleId(this.vehicleId);
    	}
    	return this.gongchengVehicle;
    }
}
