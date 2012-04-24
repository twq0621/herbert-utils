package net.snake.gamemodel.dujie.bean;

import java.sql.SQLException;

import net.snake.ibatis.SystemFactory;

public class HeroDujieDao {

	public static void insert(HeroDujieData dujie) throws SQLException {
		SystemFactory.getCharacterSqlMapClient().insert("t_hero_dujie.insert", dujie);
		//dujie.setId((Integer) newKey);
	}

	public static HeroDujieData getByHeroId(int heroId) throws SQLException {
		return (HeroDujieData) SystemFactory.getCharacterSqlMapClient().queryForObject("t_hero_dujie.selectOne", heroId);
	}

	public static void delete(Integer id) throws SQLException {
		SystemFactory.getCharacterSqlMapClient().delete("t_hero_dujie.delete", id);
	}

	public static void update(HeroDujieData dujie) throws SQLException {
		SystemFactory.getCharacterSqlMapClient().update("t_hero_dujie.updateById", dujie);
	}
	
	public static void updateIncome(HeroDujieData dujie) throws SQLException {
		SystemFactory.getCharacterSqlMapClient().update("t_hero_dujie.updateIncome", dujie);
	}
}
