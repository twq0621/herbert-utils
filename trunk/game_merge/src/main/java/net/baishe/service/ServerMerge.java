package net.baishe.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ServerMerge {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Map<String, Integer> tableMaxId = new HashMap<String, Integer>();// 每张老表的最大id

	private static Logger logger = LoggerFactory.getLogger(ServerMerge.class);

	private String targetDbName;

	private Set<String> excludeTableNames = new HashSet<String>();

	@PostConstruct
	public void init() {
		excludeTableNames.add("id_relations");
		excludeTableNames.add("t_across_income");
		excludeTableNames.add("t_channel_zhenlong");
		excludeTableNames.add("t_character_bow");
		excludeTableNames.add("t_character_dantian");
		excludeTableNames.add("t_character_dgwd");
		excludeTableNames.add("t_character_hidden_weapon");
		excludeTableNames.add("t_character_instance");
		excludeTableNames.add("t_character_lianti");
		excludeTableNames.add("t_character_photo");
		excludeTableNames.add("t_character_tianxiadiyi_goods");
		excludeTableNames.add("t_chengzhan_daypk_record");
		excludeTableNames.add("t_chest_count");
		excludeTableNames.add("t_chest_goods");
		excludeTableNames.add("t_daypk_record");
		excludeTableNames.add("t_personals");
		excludeTableNames.add("t_scene_bangqi");
		excludeTableNames.add("t_worship_contempt");
		excludeTableNames.add("t_monster_lastkill");
		excludeTableNames.add("t_t");

		// TODO tmp remove
		excludeTableNames.add("t_character_achieve");
		excludeTableNames.add("t_character_achieve_count");
		excludeTableNames.add("t_character_giftpacks");
	}

	public void startMerge(String targetDbName, String source1DbName, String source2DbName) {
		logger.info("开始合服操作,targetDb={}", targetDbName);
		this.targetDbName = targetDbName;
		parseOneDb(source1DbName);
		parseOneDb(source2DbName);
		logger.info("合服顺利结束");
	}

	/**
	 * 去掉唯一索引
	 */
	private void dropIndexs() {
		logger.info("drop indexs");
		jdbcTemplate.execute("use " + targetDbName);
		jdbcTemplate.execute("alter table character_vip drop index character_id");
		//jdbcTemplate.execute("alter table t_character_achieve drop index character_id");
		//jdbcTemplate.execute("alter table t_character_achieve_count drop index idx_characterid");
		jdbcTemplate.execute("alter table t_character_buff drop index f_character_id");
		jdbcTemplate.execute("alter table t_character_count drop index index_character_count");
		jdbcTemplate.execute("alter table t_character_dayincome_count drop index idx_characterid");
		jdbcTemplate.execute("alter table t_character_doufa drop index uni_characterid");
		jdbcTemplate.execute("alter table t_character_dujie drop index idx_characterid");
		jdbcTemplate.execute("alter table t_character_friend drop index idx_character");
		//jdbcTemplate.execute("alter table t_character_giftpacks drop index idx_character_id");
		jdbcTemplate.execute("alter table t_character_golden_pk drop index uni_characterid");
		//jdbcTemplate.execute("alter table t_character_goods drop index idx_character_id");
		jdbcTemplate.execute("alter table t_character_goods_dc drop index idx_characterid");
		jdbcTemplate.execute("alter table t_character_horse drop index idx_character_id");
		jdbcTemplate.execute("alter table t_character_msg drop index idx_character_id");
		//jdbcTemplate.execute("alter table t_character_newguide drop index idx_character_id");
		jdbcTemplate.execute("alter table t_character_onhoor_config drop index idx_characterid");
		jdbcTemplate.execute("alter table t_character_propcd drop index idx_character_id");
		jdbcTemplate.execute("alter table t_character_skill drop index idx_character_id");
		//jdbcTemplate.execute("alter table t_character_task drop index idx_character_id");
		jdbcTemplate.execute("alter table t_character_task_shopping drop index characterid");
		jdbcTemplate.execute("alter table t_faction_character drop index uni_characterid");
		jdbcTemplate.execute("alter table t_fubenranking drop index f_character_id");
		jdbcTemplate.execute("alter table t_instance_daystat drop index idx_characterid");
	}

	private void addIndexs() {
		logger.info("add indexs");
		jdbcTemplate.execute("use " + targetDbName);
		jdbcTemplate.execute("alter table character_vip add key `character_id` (`f_character_id`)");
		//jdbcTemplate.execute("alter table t_character_achieve add key `character_id` (`f_character_id`)");
		//jdbcTemplate.execute("alter table t_character_achieve_count add key `idx_characterid` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_character_buff add key `f_character_id` (`f_characterId`)");
		jdbcTemplate.execute("alter table t_character_count add key `index_character_count` (`f_character_id`,`f_count_type`)");
		jdbcTemplate.execute("alter table t_character_dayincome_count add key `idx_characterid` (`f_characterid`)");
		jdbcTemplate.execute("alter table t_character_doufa add key `uni_characterid` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_character_dujie add key `idx_characterid` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_character_friend add key `idx_character` (`f_character_id`)");
		//jdbcTemplate.execute("alter table t_character_giftpacks add key `idx_character_id` (`character_id`)");
		jdbcTemplate.execute("alter table t_character_golden_pk add key `uni_characterid` (`f_character_id`)");
		//jdbcTemplate.execute("alter table t_character_goods add key `idx_character_id` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_character_goods_dc add key `idx_characterid` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_character_horse add key `idx_character_id` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_character_msg add key `idx_character_id` (`f_character_id`)");
		//jdbcTemplate.execute("alter table t_character_newguide add key `idx_character_id` (`character_id`)");
		jdbcTemplate.execute("alter table t_character_onhoor_config add key `idx_characterid` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_character_propcd add key `idx_character_id` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_character_skill add key `idx_character_id` (`f_character_id`)");
		//jdbcTemplate.execute("alter table t_character_task add key `idx_character_id` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_character_task_shopping add key `characterid` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_faction_character add key `uni_characterid` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_fubenranking add key `f_character_id` (`f_character_id`)");
		jdbcTemplate.execute("alter table t_instance_daystat add key `idx_characterid` (`characterId`,`instanceModelId`,`statdate`)");
	}

	public void parseOneDb(String sourceDbName) {
		logger.info("处理一个database的合服操作：source={}", new Object[] { sourceDbName });
		clearTmpTable();
		dropIndexs();
		initTableMaxId();
		copyTblToTargetDb(sourceDbName);
		createIdRelations();
		// parseAccount(sourceDbName);
		// parseCharacter();
		mapId(sourceDbName, "t_character");
		mapId(sourceDbName, "t_character_horse");
		mapId(sourceDbName, "t_faction");
		addIndexs();
		parseCharacterId("character_vip", "f_character_id");
		parseCharacterId("t_character_achieve", "f_character_id");
		parseCharacterId("t_character_achieve_count", "f_character_id");
		parseCharacterId("t_character_activation_card", "f_character_id");
		parseCharacterId("t_character_buff", "f_characterId");
		parseCharacterId("t_character_count", "f_character_id");
		parseCharacterId("t_character_dayincome_count", "f_characterid");
		parseCharacterId("t_character_doufa", "f_character_id");
		parseCharacterId("t_character_dujie", "f_character_id");
		parseCharacterId("t_character_friend", "f_character_id");
		parseCharacterId("t_character_friend", "f_friend_id");
		parseCharacterId("t_character_giftpacks", "character_id");
		parseCharacterId("t_character_goods", "f_character_id");
		parseHorseId("t_character_goods", "f_in_horse_id");
		parseCharacterId("t_character_goods_dc", "f_character_id");
		parseCharacterId("t_character_horse", "f_character_id");
		parseCharacterId("t_character_idea", "f_character_id");
		parseCharacterId("t_character_mail", "character_id");
		parseCharacterId("t_character_mail", "sender");
		parseCharacterId("t_character_msg", "f_character_id");
		parseCharacterId("t_character_newguide", "character_id");
		parseCharacterId("t_character_onhoor_config", "f_character_id");
		parseCharacterId("t_character_propcd", "f_character_id");
		parseCharacterId("t_character_random", "character_id");
		parseCharacterId("t_character_skill", "f_character_id");
		parseCharacterId("t_character_task", "f_character_id");
		parseCharacterId("t_character_task_shopping", "f_character_id");
		parseCharacterId("t_faction", "f_bangzhu_id");
		parseCharacterId("t_faction_character", "f_character_id");
		parseFactionId("t_faction_character", "f_faction_id");
		parseCharacterId("t_fubenranking", "f_character_id");
		parseFactionId("t_gongcheng_date", "f_faction_id");
		parseCharacterId("t_hero_activity", "f_hero_id");
		parseCharacterId("t_instance_daystat", "characterId");
	}

	private void copyTblToTargetDb(String sourceDbName) {
		String sql1 = String.format("select TABLE_NAME from information_schema.TABLES where TABLE_SCHEMA = '%s'", targetDbName);
		List<String> tableNameList = jdbcTemplate.queryForList(sql1, String.class);
		tableNameList.removeAll(excludeTableNames);
		jdbcTemplate.execute("use " + targetDbName);
		for (String tblName : tableNameList) {
			logger.info("copy table:{}", tblName);
			String dropTriggerSql = String.format("drop trigger if exists trigger_%s", tblName);
			jdbcTemplate.execute(dropTriggerSql);
			String idFieldName = "f_id";
			if (tblName.equals("t_character_goods") || tblName.equals("t_character_task")) {
				idFieldName = "f_base_id";
			}
			String triggerSql = String.format("CREATE TRIGGER trigger_%s BEFORE INSERT ON %s FOR EACH ROW BEGIN set NEW.%s = null;END", tblName, tblName, idFieldName);
			jdbcTemplate.execute(triggerSql);
			String mergeSql = String.format("insert into %s.%s (select * from %s.%s order by %s asc)", targetDbName, tblName, sourceDbName, tblName, idFieldName);
			jdbcTemplate.execute(mergeSql);
		}
	}

	private void clearTmpTable() {
		logger.info("drop table id_relations");
		jdbcTemplate.execute("use " + targetDbName);
		jdbcTemplate.execute("drop table IF EXISTS id_relations");
	}

	private void createIdRelations() {
		logger.info("create table id_relations");
		jdbcTemplate.execute("use " + targetDbName);
		jdbcTemplate.execute("SET global max_heap_table_size=524288000");
		String tmpTbl = "CREATE TABLE `id_relations` (`f_id` int(11) NOT NULL AUTO_INCREMENT, `old_id` int(11) NOT NULL DEFAULT '0', `new_id` int(11) NOT NULL DEFAULT '0',"
				+ " `tbl_name` varchar(255) NOT NULL DEFAULT '', PRIMARY KEY (`f_id`),KEY `id_and_name` (`old_id`,`tbl_name`)) ENGINE=HEAP DEFAULT CHARSET=utf8;";
		jdbcTemplate.execute(tmpTbl);
	}

	/**
	 * 目标数据库每张表的最大id
	 */
	private void initTableMaxId() {
		logger.info("get table max id");
		tableMaxId.clear();
		String sql1 = String.format("select TABLE_NAME from information_schema.TABLES where TABLE_SCHEMA = '%s'", targetDbName);
		List<String> tableNameList = jdbcTemplate.queryForList(sql1, String.class);
		tableNameList.removeAll(excludeTableNames);
		for (String tblName : tableNameList) {
			String idFieldName = "f_id";
			if (tblName.equals("t_character_goods") || tblName.equals("t_character_task")) {
				idFieldName = "f_base_id";
			}
			int maxId = jdbcTemplate.queryForInt(String.format("select max(%s) from %s.%s", idFieldName, targetDbName, tblName));
			tableMaxId.put(tblName, maxId);
		}
	}

	public void parseAccount(String sourceDbName) {
		String sql1 = String.format("select f_id from %s.t_account", sourceDbName);
		List<Map<String, Object>> oldIdMapList = jdbcTemplate.queryForList(sql1);
		List<Integer> oldIdList = new ArrayList<Integer>();
		for (Map<String, Object> map : oldIdMapList) {
			Object idObj = map.get("f_id");
			oldIdList.add(((Long) idObj).intValue());
		}
		String afterInsertIdSql = String.format("select f_id from %s.t_account where f_id > %s", targetDbName, tableMaxId.get("t_account"));
		List<Integer> newIdList = new ArrayList<Integer>();
		List<Map<String, Object>> objMapList = jdbcTemplate.queryForList(afterInsertIdSql);
		for (Map<String, Object> map : objMapList) {
			Object idObj = map.get("f_id");
			newIdList.add(((Long) idObj).intValue());
		}
		// 创建map
		String sql2 = String.format("insert into %s.id_relations(old_id,new_id,tbl_name) values(?,?,?)", targetDbName);
		for (int i = 0; i < oldIdList.size(); i++) {
			jdbcTemplate.update(sql2, new Object[] { oldIdList.get(i), newIdList.get(i), "t_account" });
		}
	}

	public void parseCharacter() {
		jdbcTemplate
				.update("update t_character,id_relations set t_character.f_account_id = id_relations.new_id where t_character.f_id > ? and t_character.f_account_id = id_relations.old_id and id_relations.tbl_name = ?",
						tableMaxId.get("t_character"), "t_account");
	}

	private void parseCharacterId(String tblName, String characterFieldName) {
		logger.info("update characterId:tbl={}", tblName);
		String idFieldName = "f_id";
		if (tblName.equals("t_character_goods") || tblName.equals("t_character_task")) {
			idFieldName = "f_base_id";
		}
		String sql = String.format("update %s,id_relations set %s.%s = id_relations.new_id where %s.%s > ? and %s.%s = id_relations.old_id and id_relations.tbl_name = ?", tblName,
				tblName, characterFieldName, tblName, idFieldName, tblName, characterFieldName);
		jdbcTemplate.update(sql, tableMaxId.get(tblName), "t_character");
	}

	private void parseFactionId(String tblName, String characterFieldName) {
		logger.info("update factionId:tbl={}", tblName);
		String sql = String.format("update %s,id_relations set %s.%s = id_relations.new_id where %s.f_id > ? and %s.%s = id_relations.old_id and id_relations.tbl_name = ?",
				tblName, tblName, characterFieldName, tblName, tblName, characterFieldName);
		jdbcTemplate.update(sql, tableMaxId.get(tblName), "t_faction");
	}

	private void parseHorseId(String tblName, String characterFieldName) {
		logger.info("update horseId:tbl={}", tblName);
		String idFieldName = "f_id";
		if (tblName.equals("t_character_goods") || tblName.equals("t_character_task")) {
			idFieldName = "f_base_id";
		}
		String sql = String.format("update %s,id_relations set %s.%s = id_relations.new_id where %s.%s > ? and %s.%s = id_relations.old_id and id_relations.tbl_name = ?", tblName,
				tblName, characterFieldName, tblName, idFieldName, tblName, characterFieldName);
		jdbcTemplate.update(sql, tableMaxId.get(tblName), "t_character_horse");
	}

	private void mapId(String sourceDbName, String tblName) {
		logger.info("map id:sourceDbName={},tblName={}", sourceDbName, tblName);
		String oldIdSql = String.format("select f_id from %s.%s order by f_id asc", sourceDbName, tblName);
		List<Map<String, Object>> oldIdMapList = jdbcTemplate.queryForList(oldIdSql);
		List<Integer> oldIdList = new ArrayList<Integer>();
		for (Map<String, Object> map : oldIdMapList) {
			Object idObj = map.get("f_id");
			oldIdList.add(((Number) idObj).intValue());
		}
		String afterInsertIdSql = String.format("select f_id from %s.%s where f_id > %s order by f_id asc", targetDbName, tblName, tableMaxId.get(tblName));
		List<Integer> newIdList = new ArrayList<Integer>();
		List<Map<String, Object>> objMapList = jdbcTemplate.queryForList(afterInsertIdSql);
		for (Map<String, Object> map : objMapList) {
			Object idObj = map.get("f_id");
			newIdList.add(((Number) idObj).intValue());
		}
		// 创建map
		for (int i = 0; i < oldIdList.size(); i++) {
			jdbcTemplate
					.update("insert into " + targetDbName + ".id_relations(old_id,new_id,tbl_name) values(?,?,?)", new Object[] { oldIdList.get(i), newIdList.get(i), tblName });
		}
	}

}
