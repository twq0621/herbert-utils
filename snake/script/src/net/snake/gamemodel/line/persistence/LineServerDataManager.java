package net.snake.gamemodel.line.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.line.bean.LineServerEntry;
import net.snake.ibatis.SystemFactory;

public class LineServerDataManager {
	LineServerEntryDAO dao;

	public LineServerDataManager(){
		dao = new LineServerEntryDAO(SystemFactory.getGamedataSqlMapClient());
	}

	/** 获得某一服的所有分线服务器列表(仅enable==1的) */
	@SuppressWarnings("unchecked")
	public List<LineServerEntry> getLineServerEntryList(int serverid) throws SQLException {
		return dao.selectByServerId(serverid);
	}

}
