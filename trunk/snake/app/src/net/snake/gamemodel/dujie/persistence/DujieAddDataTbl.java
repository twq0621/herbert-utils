package net.snake.gamemodel.dujie.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.dujie.bean.DujieAdd;
import net.snake.ibatis.SystemFactory;


import com.ibatis.sqlmap.client.SqlMapClient;

public class DujieAddDataTbl {
	private static DujieAddDataTbl instance = new DujieAddDataTbl();

	private DujieAdd add=null;
	

	private DujieAddDataTbl() {
	}

	public static DujieAddDataTbl getInstance() {
		return instance;
	}

	public DujieAdd getDujieAdd() {
		return add;
	}


	@SuppressWarnings("rawtypes")
	public void loadData() throws SQLException {
		SqlMapClient client = SystemFactory.getGamedataSqlMapClient();
		List list = client.queryForList("t_dujie_add.queryall");
		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		add = (DujieAdd) list.get(0);
		add.init();
	}
}
