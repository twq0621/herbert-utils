package cn.hxh.common;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import cn.hxh.core.ProtobufRedisTemplate;


public abstract class BaseDaoImpl extends SqlSessionDaoSupport {

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
   
}
