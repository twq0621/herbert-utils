package lion.common;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import lion.serialize.ProtobufRedisTemplate;

public abstract class BaseDaoImpl<E> extends SqlSessionDaoSupport {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SqlSessionTemplate sqlQuerySessionTemplate;

	private SqlSessionTemplate sqlSessionTemplate;

	private StringRedisTemplate stringRedisTemplate;

	private ProtobufRedisTemplate protobufRedisTemplate;

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	public ProtobufRedisTemplate getProtobufRedisTemplate() {
		return protobufRedisTemplate;
	}

	public SqlSessionTemplate getSqlQuerySessionTemplate() {
		return sqlQuerySessionTemplate;
	}

	@Resource(name = "sqlQuerySessionFactory")
	public void setSqlQuerySessionTemplate(SqlSessionFactory sqlQuerySessionFactory) {
		this.sqlQuerySessionTemplate = new SqlSessionTemplate(sqlQuerySessionFactory);
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource
	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	@Resource
	public void setProtobufRedisTemplate(ProtobufRedisTemplate protobufRedisTemplate) {
		this.protobufRedisTemplate = protobufRedisTemplate;
	}

	public abstract Class<E> getEntityClass();

	public String getQueryName(String queryType) {
		return queryType + getEntityClass().getSimpleName();
	}

	public E insert(E entity) {
		getSqlSession().insert(getQueryName("insert"), entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	public E get(Map<String, Object> param) {
		return (E) getSqlQuerySessionTemplate().selectOne(getQueryName("get"), param);
	}

	public int update(E entity) {
		return getSqlSession().update(getQueryName("update"), entity);
	}

	public int delete(Map<String, Object> param) {
		return getSqlSession().delete(getQueryName("delete"), param);
	}

	public int deleteList(String[] ids) {
		return getSqlSession().delete(getQueryName("deleteList"), ids);
	}

	public List<E> list(Map<String, Object> param) {
		return getSqlQuerySessionTemplate().selectList(getQueryName("list"), param);

	}

	public int getCount(Map<String, Object> param) {
		Integer count = (Integer) getSqlQuerySessionTemplate().selectOne(getQueryName("getCount"), param);
		return count.intValue();
	}

	public List<E> listSplit(Map<String, Object> param) {
		return getSqlQuerySessionTemplate().selectList(getQueryName("listSplit"), param);
	}

}
