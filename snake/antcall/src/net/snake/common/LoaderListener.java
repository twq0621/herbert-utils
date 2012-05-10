package net.snake.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 * 鍒濆鍖栧寲鍚堥攢姣併�<br />
 * 1. 鍒濆鍖朇onstants甯搁噺. <br />
 * 
 * @author serv_dev *
 */
public class LoaderListener implements ServletContextListener {

	private static Logger logger = LoggerFactory
			.getLogger(LoaderListener.class);
	
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		String rootpath = sce.getServletContext().getRealPath("/");
		if (rootpath != null) {
			rootpath = rootpath.replaceAll("\\\\", "/");
		} else {
			rootpath = "/";
		}
			    if (!rootpath.endsWith("/")) {
			      rootpath = rootpath + "/";
			    }
			    Constants.root = rootpath;
			   // Constants.CONFIGPATH = rootpath + "WEB-INF/classes/";	   
		    
			    logger.info("Application Run!");
			    logger.info("webroot:" + rootpath);
			}


//	}
}

