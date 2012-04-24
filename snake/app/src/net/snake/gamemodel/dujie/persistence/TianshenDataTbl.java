package net.snake.gamemodel.dujie.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.dujie.bean.Tianshen;
import net.snake.ibatis.SystemFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

public class TianshenDataTbl {
	private static TianshenDataTbl instance = new TianshenDataTbl();

	private Map<Integer, Tianshen> allData_map = new HashMap<Integer, Tianshen>();
	private List<Tianshen> allData_list = null;

	private TianshenDataTbl() {
	}

	public static TianshenDataTbl getInstance() {
		return instance;
	}

	// ====================================
	public Tianshen getTianshen(int id) {
		return allData_map.get(id);
	}

	public List<Tianshen> getAllTianshens() {
		return allData_list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadData() throws SQLException {
		SqlMapClient client = SystemFactory.getGamedataSqlMapClient();
		List list = client.queryForList("t_tianshen.queryall");

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
			Tianshen tianshen = (Tianshen) list.get(i);
			allData_map.put(tianshen.getId(), tianshen);
		}

	}

	/**
	 * 返回一个分页的天神
	 * 
	 * @param page
	 * @return
	 */
	public List<Tianshen> getPagedTianshens(int page) {
		int offset = (page - 1) * 10;
		int size = allData_list.size();
		int temp = 10 + offset;
		int leng = size < temp ? size : temp;

		ArrayList<Tianshen> p = new ArrayList<Tianshen>();
		for (int i = offset; i < leng; i++) {
			Tianshen shen = allData_list.get(i);
			p.add(shen);
		}
		return p;
	}
}
