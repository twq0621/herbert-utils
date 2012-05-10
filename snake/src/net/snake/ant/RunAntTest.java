package net.snake.ant;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class RunAntTest extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -132727173038392333L;
	private static final Logger logger = Logger.getLogger(RunAntTest.class);
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
	IOException {
		String antfilename = getServletContext().getRealPath("/") + "antdir" + File.separator + "goodmodel.xml";

		File antfile = new File(antfilename);
		Project p = new Project();
		try {
			p.fireBuildStarted();
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			helper.parse(p, antfile);
			p.executeTarget(p.getDefaultTarget());
			p.fireBuildFinished(null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			p.fireBuildFinished(e);
		}
	}

}
