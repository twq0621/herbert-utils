package cn.hxh.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloHandler extends AbstractHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(HelloHandler.class);

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		logger.info("request uri:{}", target);
		long startTime = System.currentTimeMillis();
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		response.getWriter().println("<h1>Hello World</h1>");
		response.sendRedirect("http://www.163.com");
		long endTime = System.currentTimeMillis();
		logger.info("cost ms={}", endTime - startTime);
	}

}
