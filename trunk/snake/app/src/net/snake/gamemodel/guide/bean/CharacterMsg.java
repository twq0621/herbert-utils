package net.snake.gamemodel.guide.bean;

import net.snake.ibatis.IbatisEntity;

public class CharacterMsg implements IbatisEntity {
    /**
	 * t_character_msg.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 角色id 更角色表关联 t_character_msg.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 消息内容 t_character_msg.f_msg
	 * 
	 */
	private String msg;
	/**
	 * t_character_msg.f_id
	 * @return  the value of t_character_msg.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * t_character_msg.f_id
	 * @param id  the value for t_character_msg.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 角色id 更角色表关联 t_character_msg.f_character_id
	 * @return  the value of t_character_msg.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}
	/**
	 * 角色id 更角色表关联 t_character_msg.f_character_id
	 * @param characterId  the value for t_character_msg.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}
	/**
	 * 消息内容 t_character_msg.f_msg
	 * @return  the value of t_character_msg.f_msg
	 * 
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * 消息内容 t_character_msg.f_msg
	 * @param msg  the value for t_character_msg.f_msg
	 * 
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public CharacterMsg(){
	}
	public CharacterMsg(int characterId,int msgKey ,String... vars){
		this.characterId=characterId;
		String msg1=msgKey+"";
		if(vars==null||vars.length==0){
			this.msg=msg1;
		}else{
			String msg2="";
			for(int i=0;i<vars.length;i++){
				String str=vars[0];
				if(i==0){
					msg2=str;
				}else{
					msg2=msg2+","+str;
				}
			}
			this.msg=msg1+":"+msg2;
		}
	}
}
