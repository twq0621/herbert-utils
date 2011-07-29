/**
 * 
 */
package cn.hxh.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 全局公用线程池
 * @author fangweng
 * @email fangweng@taobao.com
 * @date 2011-6-23
 *
 */
public class GlobalThreadPool {
	
	final static ThreadPoolExecutor globalThreadPool;
	private static final Log logger = LogFactory.getLog(GlobalThreadPool.class);
	
	static 
	{
		int coreGlobalThreadCount = 100;
		int maxGlobalThreadCount = 2000;
		int maxGlobalQueueLength = 500;
		
		if (System.getProperty("coreGlobalThreadCount") != null)
		{
			coreGlobalThreadCount = Integer.valueOf(System.getProperty("coreGlobalThreadCount"));
			logger.warn("use coreGlobalThreadCount :" + coreGlobalThreadCount);
		}
		
		if (System.getProperty("maxGlobalThreadCount") != null)
		{
			maxGlobalThreadCount = Integer.valueOf(System.getProperty("maxGlobalThreadCount"));
			logger.warn("use maxGlobalThreadCount :" + maxGlobalThreadCount);
		}
		
		if (System.getProperty("maxGlobalQueueLength") != null)
		{
			maxGlobalQueueLength = Integer.valueOf(System.getProperty("maxGlobalQueueLength"));
			logger.warn("use maxGlobalQueueLength :" + maxGlobalQueueLength);
		}
		
		globalThreadPool = new ThreadPoolExecutor(coreGlobalThreadCount,maxGlobalThreadCount, 
				0L, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(maxGlobalQueueLength),
				new NamedThreadFactory("global_worker"));
	}
	
	public static String snapshot()
	{
		StringBuilder result = new StringBuilder();
		
		
		result.append(" CorePoolSize : ").append(globalThreadPool.getCorePoolSize())
			.append(" MaxPoolSize : ").append(globalThreadPool.getMaximumPoolSize()).append(" ,\r\n <br/>");
		
		result.append(" Current current active thread count: ").append(globalThreadPool.getActiveCount()).append(" ,\r\n <br/>");
		result.append(" Current current thread count: ").append(globalThreadPool.getPoolSize()).append(" ,\r\n <br/>");
		result.append(" Used maximum thread : ").append(globalThreadPool.getLargestPoolSize()).append(" ,\r\n <br/>");
		result.append(" Current total task count: ").append(globalThreadPool.getQueue().size()).append(" ,\r\n <br/>");
		
		return result.toString();
	}
	
	public static void submitTask(Runnable task)
	{
		globalThreadPool.execute(task);
	}
	

}
