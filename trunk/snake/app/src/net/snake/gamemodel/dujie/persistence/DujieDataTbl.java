package net.snake.gamemodel.dujie.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.dujie.bean.Dujie;
import net.snake.ibatis.SystemFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

public class DujieDataTbl {
	private static DujieDataTbl instance = new DujieDataTbl();
	
	private Map<Integer, Dujie> allData_map = new HashMap<Integer, Dujie>();
	private List<Dujie> allData_list = null;
	
	private DujieDataTbl() {
	}

	public static DujieDataTbl getInstance() {
		return instance;
	}

	public Dujie getDujie(int lvl) {
		return allData_map.get(lvl);
	}
	
	public List<Dujie> getAllDujies(){
		return allData_list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadData() throws SQLException {
		SqlMapClient client=SystemFactory.getGamedataSqlMapClient();
		List list = client.queryForList("t_dujie.queryall");
		if (list == null) {
			return ;
		}
		if (list.isEmpty()) {
			return ;
		}
		allData_list = list;
		
		allData_map.clear();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Dujie dujie = (Dujie)list.get(i);
			dujie.init();
			allData_map.put(dujie.getId(),dujie);
		}
		
	}
}