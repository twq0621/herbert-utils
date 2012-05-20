package net.baishe.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ServerMerge {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Map<Integer, Integer> characterIdMap = new HashMap<Integer, Integer>();// key oldId,value newId

	Map<String, Integer> tableMaxId = new HashMap<String, Integer>();// 每张老表的最大id

	private static Logger logger = LoggerFactory.getLogger(ServerMerge.class);

	public void startMerge(String targetDbName, String source1DbName, String source2DbName) {
		logger.info("targetDbName={},source1={},source2={}", new Object[] { targetDbName, source1DbName, source2DbName });
		initTableMaxId();
		parseAccount(targetDbName, source1DbName);
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
		String oldIdSql = String.format("select f_id from merge_from1.t_account where f_id");
		List<Map<String, Object>> oldIdMapList = jdbcTemplate.queryForList(oldIdSql);
		List<Integer> oldIdList = new ArrayList<Integer>();
		for (Map<String, Object> map : oldIdMapList) {
			Object idObj = map.get("f_id");
			oldIdList.add(((Long) idObj).intValue());
		}

		int maxId = jdbcTemplate.queryForInt("select max(f_id) from merge_to.t_account");
		jdbcTemplate.execute("insert into merge_to.t_account select * from merge_from1.t_account");
		String afterInsertIdSql = String.format("select f_id from merge_to.t_account where f_id > %s", maxId);
		List<Integer> newIdList = new ArrayList<Integer>();
		List<Map<String, Object>> objMapList = jdbcTemplate.queryForList(afterInsertIdSql);
		for (Map<String, Object> map : objMapList) {
			Object idObj = map.get("f_id");
			newIdList.add(((Long) idObj).intValue());
		}
		// 创建map
		for (int i = 0; i < oldIdList.size(); i++) {
			characterIdMap.put(oldIdList.get(i), newIdList.get(i));
		}
		logger.info(characterIdMap.toString());
	}

	public void parseCharacter() {
		jdbcTemplate.execute("insert into merge_to.t_account select * from merge_from1.t_account");
		jdbcTemplate.execute("update merge_to.t_account set f_character_id = ? where f_id > ? and f_character_id = ?",tableMaxId.get("t_account"));
	}

}
