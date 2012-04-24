package net.snake.context;

import net.snake.api.context.IServerContext;
import net.snake.api.datatbl.IStaticDataTbl;
import net.snake.api.datatbl.StaticDataMnger;
import net.snake.ibatis.SystemFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

class StaticDataManager extends StaticDataMnger {

	@Override
	public void setupDataTable(IServerContext context) throws Exception {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void reload(String name, IServerContext context) throws Exception {
		IStaticDataTbl tbl = map.get(name);
		if (tbl == null) {
			return;
		}
		SqlMapClient client = SystemFactory.getGamedataSqlMapClient();
		tbl.loadData(context, client);
	}

}
