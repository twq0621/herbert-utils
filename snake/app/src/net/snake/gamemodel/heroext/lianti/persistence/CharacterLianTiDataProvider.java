package net.snake.gamemodel.heroext.lianti.persistence;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.lianti.bean.CharacterLiantiDataEntry;
import net.snake.ibatis.SystemFactory;


import java.sql.SQLException;

import org.apache.log4j.Logger;
public class CharacterLianTiDataProvider {
private static Logger logger = Logger
		.getLogger(CharacterLianTiDataProvider.class);	
	CharacterLiantiDataEntryDAO dao = new CharacterLiantiDataEntryDAO(
			SystemFactory.getCharacterSqlMapClient());
	private static CharacterLianTiDataProvider instance;

	private CharacterLianTiDataProvider() {
	}

	public static CharacterLianTiDataProvider getInstance() {
		if (instance == null) {
			instance = new CharacterLianTiDataProvider();
		}
		return instance;
	}
	public void asynUpdateCharacterLiantiData(Hero character,final CharacterLiantiDataEntry entry){
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updateByPrimaryKeySelective(entry);
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
				}
			}
		});
	
	}
	public CharacterLiantiDataEntry getLianTiDataByCharacterId(int characterid) throws SQLException {
		CharacterLiantiDataEntry t= dao.selectByPrimaryKey(characterid);
		if(t==null){
			t=new CharacterLiantiDataEntry();
			t.setLiantiJingjieId(1);//默认一级
			t.setCharacterId(characterid);
			t.setAqJv(0);
			t.setAtk(0);
			t.setAtkSpeed(0);
			t.setDef(0);
			t.setFjsh(0);
			t.setHp(0);
			t.setMoveSpeed(0);
			t.setMp(0);
			t.setShjm(0);
			t.setSp(0);
			t.setTupoCount(0);
			t.setZhufu(0);
			t.setUsegoodCount(0);
			t.setPutiCardUsecount(0);
			dao.insertSelective(t);
		}
		return t;
	}
	
	
}
