//package net.baishe.dao;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.mybatis.spring.support.SqlSessionDaoSupport;
//import org.springframework.stereotype.Component;
//import org.springside.examples.miniservice.entity.User;
//
//@Component
//public class CommonDao extends SqlSessionDaoSupport {
//
//	public User getUser(Long id) {
//		return (User) getSqlSession().selectOne("Account.getUser", id);
//	}
//
//	public void clearId(String dbName) {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("sql", "update merge_from1.t_account set f_account_initially_id = 6");
//		getSqlSession().update("Common.updateBySql", params);
//	}
//
//	public void updateBySql(String parameter) {
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("sql", parameter);
//		getSqlSession().update("Common.updateBySql", params);
//	}
//
//	public Object selectOneBySql(String sql)
//	{
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("sql", sql);
//		return getSqlSession().selectOne("Common.selectOneBySql", params);
//	}
//}
