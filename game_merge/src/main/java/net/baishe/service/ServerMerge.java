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

	private Set<String> excludeTableNames = new HashSet<String>();

	@PostConstruct
	public void init() {
		excludeTableNames.add("id_relations");
	}

	public void startMerge(String targetDbName, String source1DbName, String source2DbName) {
		logger.info("targetDbName={},source1={},source2={}", new Object[] { targetDbName, source1DbName, source2DbName });
		clearTmpTable();
		initTableMaxId();
		copyTblToTargetDb();
		createIdRelations();
		parseAccount(targetDbName, source1DbName);
		parseCharacter();
		parseHorse();
	}

	private void copyTblToTargetDb() {
		List<String> tableNameList = jdbcTemplate.queryForList("select TABLE_NAME from information_schema.TABLES where TABLE_SCHEMA = 'merge_to'", String.class);
		tableNameList.removeAll(excludeTableNames);
		jdbcTemplate.execute("use merge_to");
		for (String tblName : tableNameList) {
			String dropTriggerSql = String.format("drop trigger if exists trigger_%s", tblName);
			jdbcTemplate.execute(dropTriggerSql);
			String triggerSql = String.format("CREATE TRIGGER trigger_%s BEFORE INSERT ON %s FOR EACH ROW BEGIN set NEW.f_id = null;END", tblName, tblName);
			jdbcTemplate.execute(triggerSql);
			String mergeSql = String.format("insert into merge_to.%s select * from merge_from1.%s", tblName, tblName);
			jdbcTemplate.execute(mergeSql);
		}
	}

	private void clearTmpTable() {
		jdbcTemplate.execute("use merge_to");
		String tmpTbl = "drop table IF EXISTS id_relations";
		jdbcTemplate.execute(tmpTbl);
	}

	private void createIdRelations() {
		jdbcTemplate.execute("use merge_to");
		String tmpTbl = "CREATE TABLE `id_relations` (`f_id` int(11) NOT NULL AUTO_INCREMENT, `old_id` int(11) NOT NULL DEFAULT '0', `new_id` int(11) NOT NULL DEFAULT '0',"
				+ " `tbl_name` varchar(255) NOT NULL DEFAULT '', PRIMARY KEY (`f_id`)) ENGINE=HEAP DEFAULT CHARSET=utf8;";
		jdbcTemplate.execute(tmpTbl);
	}

	/**
	 * 目标数据库每张表的最大id
	 */
	private void initTableMaxId() {
		List<String> tableNameList = jdbcTemplate.queryForList("select TABLE_NAME from information_schema.TABLES where TABLE_SCHEMA = 'merge_to'", String.class);
		for (String tblName : tableNameList) {
			int maxId = jdbcTemplate.queryForInt(String.format("select max(f_id) from merge_to.%s", tblName));
			tableMaxId.put(tblName, maxId);
		}
	}

	public void parseAccount(String targetDbName, String source1DbName) {
		List<Map<String, Object>> oldIdMapList = jdbcTemplate.queryForList("select f_id from merge_from1.t_account");
		List<Integer> oldIdList = new ArrayList<Integer>();
		for (Map<String, Object> map : oldIdMapList) {
			Object idObj = map.get("f_id");
			oldIdList.add(((Long) idObj).intValue());
		}
		String afterInsertIdSql = String.format("select f_id from merge_to.t_account where f_id > %s", tableMaxId.get("t_account"));
		List<Integer> newIdList = new ArrayList<Integer>();
		List<Map<String, Object>> objMapList = jdbcTemplate.queryForList(afterInsertIdSql);
		for (Map<String, Object> map : objMapList) {
			Object idObj = map.get("f_id");
			newIdList.add(((Long) idObj).intValue());
		}
		// 创建map
		for (int i = 0; i < oldIdList.size(); i++) {
			jdbcTemplate.update("insert into merge_to.id_relations(old_id,new_id,tbl_name) values(?,?,?)", new Object[] { oldIdList.get(i), newIdList.get(i), "t_account" });
		}
	}

	public void parseCharacter() {
		jdbcTemplate
				.update("update t_character,id_relations set t_character.f_account_id = id_relations.new_id where t_character.f_id > ? and t_character.f_account_id = id_relations.old_id and id_relations.tbl_name = ?",
						tableMaxId.get("t_character"), "t_account");
		mapId("t_character");
	}

	public void parseHorse() {
		int ret = jdbcTemplate
				.update("update t_character_horse,id_relations set t_character_horse.f_character_id = id_relations.new_id where t_character_horse.f_id > ? and t_character_horse.f_character_id = id_relations.old_id and id_relations.tbl_name = ?",
						tableMaxId.get("t_character_horse"), "t_character");
		logger.info("ret={}", ret);
	}

	private void mapId(String tblName) {
		String oldIdSql = String.format("select f_id from merge_from1.%s", tblName);
		List<Map<String, Object>> oldIdMapList = jdbcTemplate.queryForList(oldIdSql);
		List<Integer> oldIdList = new ArrayList<Integer>();
		for (Map<String, Object> map : oldIdMapList) {
			Object idObj = map.get("f_id");
			oldIdList.add(((Long) idObj).intValue());
		}
		String afterInsertIdSql = String.format("select f_id from merge_to.%s where f_id > %s", tblName, tableMaxId.get(tblName));
		List<Integer> newIdList = new ArrayList<Integer>();
		List<Map<String, Object>> objMapList = jdbcTemplate.queryForList(afterInsertIdSql);
		for (Map<String, Object> map : objMapList) {
			Object idObj = map.get("f_id");
			newIdList.add(((Long) idObj).intValue());
		}
		// 创建map
		for (int i = 0; i < oldIdList.size(); i++) {
			jdbcTemplate.update("insert into merge_to.id_relations(old_id,new_id,tbl_name) values(?,?,?)", new Object[] { oldIdList.get(i), newIdList.get(i), tblName });
		}
	}

}
