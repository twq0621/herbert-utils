package net.snake.api.datatbl;

import net.snake.api.context.IServerContext;

import com.ibatis.sqlmap.client.SqlMapClient;

public interface IStaticDataTbl<T> {


	public T getDataCntlor();
	
	public void loadData(IServerContext context,SqlMapClient client)throws Exception;
	
	public String getName();
	
}
