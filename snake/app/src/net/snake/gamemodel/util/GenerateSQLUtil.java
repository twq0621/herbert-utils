package net.snake.gamemodel.util;

import net.snake.gamemodel.skill.bean.BuffPersisData;

/**
 * 根据对象产生CRUD SQL语句
 * 
 * @author serv_dev
 * 
 */
public class GenerateSQLUtil {

	public static String CharacterBuffSQL(BuffPersisData buffPersisData,int characterId,CRUD crud){
		String sql = null;
		if (CRUD.create == crud) {
			sql = "insert into t_character_buff(f_characterId,f_duration,f_buffValue,f_relevance_skillId,f_gotime,f_effect_id," +
					"f_duration2,f_remainpoint,f_delayrecover_time,f_type,f_jingmai_id) values("
				+ buffPersisData.getCharacterid()
				+ ","
				+ buffPersisData.getDuration()
				+ ","
				+ buffPersisData.getBuffvalue()
				+ ","
				+ buffPersisData.getRelevanceSkillid()
				+ ",'"
				+ buffPersisData.getGotime()
				+ "',"
				+ buffPersisData.getEffectId()
				+ ","
				+ buffPersisData.getDuration2()
				+ ","
				+ buffPersisData.getRemainpoint()
				+ ","
				+ buffPersisData.getDelayrecoverTime()
				+ ","
				+ buffPersisData.getEffectInfoType()
				+ ",'"
				+ buffPersisData.getJingmaiId()
				+ "')";
		}
		else if (CRUD.delete == crud) {
			sql = "delete from t_character_buff where f_characterId =" + characterId;
		}
		return sql;
	}
	
	public static String CharacterPkRecordSQL(int winer,Integer failer,int count,String daytime,CRUD crud){
		String sql = null;
		if (CRUD.create == crud) {
			sql = "insert into t_chengzhan_daypk_record(f_winer,f_failer,f_count,f_daytime) values("
				+ winer
				+ ","
				+ failer
				+ ","
				+ count
				+ ",'"
				+ daytime + "')";
		}
		else if (CRUD.delete == crud) {
			sql = "delete from t_chengzhan_daypk_record where f_winer =" + winer;
		}
		return sql;
	}

	public enum CRUD {
		/**
		 * 增加(Create)
		 */
		create,
		/**
		 * 查询(Retrieve)
		 */
		retrieve,
		/**
		 * 更新(Update)
		 */
		update,
		/**
		 * 删除(Delete)
		 */
		delete;
	}

}
