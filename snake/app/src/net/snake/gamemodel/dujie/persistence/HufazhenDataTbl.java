package net.snake.gamemodel.dujie.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.dujie.bean.Hufazhen;
import net.snake.ibatis.SystemFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HufazhenDataTbl {

	private static HufazhenDataTbl instance = new HufazhenDataTbl();

	private Map<Integer, Hufazhen> allData_map = new HashMap<Integer, Hufazhen>();
	private List<Hufazhen> allData_list = null;

	private HufazhenDataTbl() {
	}

	public static HufazhenDataTbl getInstance() {
		return instance;
	}

	// ====================================
	public Hufazhen getHufaData(int id) {
		return allData_map.get(id);
	}

	public List<Hufazhen> getAllHufas() {
		return allData_list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadData() throws SQLException {
		SqlMapClient client = SystemFactory.getGamedataSqlMapClient();
		List list = client.queryForList("t_hufa.queryall");

		if (list == null) {
			return;
		}
		if (list.isEmpty()) {
			return;
		}
		allData_list = list;

		allData_map.clear();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Hufazhen dujie = (Hufazhen) list.get(i);
			allData_map.put(dujie.getId(), dujie);
		}

	}
}
