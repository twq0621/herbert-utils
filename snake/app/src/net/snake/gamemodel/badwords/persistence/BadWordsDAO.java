package net.snake.gamemodel.badwords.persistence;

import java.util.List;

import net.snake.gamemodel.badwords.bean.BadWords;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BadWordsDAO {

	private SqlMapClient sqlMapClient;

	public BadWordsDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public BadWords selectById(Integer id) throws Exception {
		BadWords record = (BadWords) sqlMapClient.queryForObject("t_badwords.selectById", id);
		return record;
	}
	
	@SuppressWarnings("unchecked")
	public List<BadWords> selectAll()throws Exception{
		List<BadWords> words=(List<BadWords>)sqlMapClient.queryForList("t_badwords.queryall");
		return words;
	}
}
