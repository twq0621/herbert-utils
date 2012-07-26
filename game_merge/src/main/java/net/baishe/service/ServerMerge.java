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
	}

	public void startMerge(String targetDbName, String source1DbName, String source2DbName) {
		logger.info("开始合服操作,targetDb={}", targetDbName);
		this.targetDbName = targetDbName;
		parseOneDb(source1DbName);
		parseOneDb(source2DbName);
	}

	public void parseOneDb(String sourceDbName) {
		logger.info("处理一个database的合服操作：source={}", new Object[] { sourceDbName });
		clearTmpTable();
		initTableMaxId();
		copyTblToTargetDb(sourceDbName);
		createIdRelations();
		parseAccount(sourceDbName);
		parseCharacter();
		parseHorse();
	}

	private void copyTblToTargetDb(String sourceDbName) {
		String sql1 = String.format("select TABLE_NAME from information_schema.TABLES where TABLE_SCHEMA = '%s'", targetDbName);
		List<String> tableNameList = jdbcTemplate.queryForList(sql1, String.class);
		tableNameList.removeAll(excludeTableNames);
		jdbcTemplate.execute("use " + targetDbName);
		for (String tblName : tableNameList) {
			String dropTriggerSql = String.format("drop trigger if exists trigger_%s", tblName);
			jdbcTemplate.execute(dropTriggerSql);
			String triggerSql = String.format("CREATE TRIGGER trigger_%s BEFORE INSERT ON %s FOR EACH ROW BEGIN set NEW.f_id = null;END", tblName, tblName);
			jdbcTemplate.execute(triggerSql);
			String mergeSql = String.format("insert into %s.%s select * from %s.%s", targetDbName, sourceDbName, tblName, tblName);
			jdbcTemplate.execute(mergeSql);
		}
	}

	private void clearTmpTable() {
		jdbcTemplate.execute("use " + targetDbName);
		jdbcTemplate.execute("drop table IF EXISTS id_relations");
	}

	private void createIdRelations() {
		jdbcTemplate.execute("use " + targetDbName);
		String tmpTbl = "CREATE TABLE `id_relations` (`f_id` int(11) NOT NULL AUTO_INCREMENT, `old_id` int(11) NOT NULL DEFAULT '0', `new_id` int(11) NOT NULL DEFAULT '0',"
				+ " `tbl_name` varchar(255) NOT NULL DEFAULT '', PRIMARY KEY (`f_id`)) ENGINE=HEAP DEFAULT CHARSET=utf8;";
		jdbcTemplate.execute(tmpTbl);
	}

	/**
	 * 目标数据库每张表的最大id
	 */
	private void initTableMaxId() {
		String sql1 = String.format("select TABLE_NAME from information_schema.TABLES where TABLE_SCHEMA = '%s'", targetDbName);
		List<String> tableNameList = jdbcTemplate.queryForList(sql1, String.class);
		for (String tblName : tableNameList) {
			int maxId = jdbcTemplate.queryForInt(String.format("select max(f_id) from %s.%s", targetDbName, tblName));
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
