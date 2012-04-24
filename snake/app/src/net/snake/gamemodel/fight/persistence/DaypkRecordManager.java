package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.fight.bean.DaypkRecord;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 城战pk记录管理
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class DaypkRecordManager {
	private static final Logger logger = Logger.getLogger(DaypkRecordManager.class);
	public static final String TABLE = "chengzhandaypkrecord";

	private static DaypkRecordDAO dao = new DaypkRecordDAO(SystemFactory.getCharacterSqlMapClient());

	private static DaypkRecordManager instance;

	private DaypkRecordManager() {
	}

	public static DaypkRecordManager getInstance() {
		if (instance == null) {
			instance = new DaypkRecordManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<DaypkRecord> getDaypkRecordList(int character) {
		try {
			return dao.select(character);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

}
