package cn.hxh.jetty;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	private String greeting = "Hello World!";

	public HelloWorldServlet() {
	}

	public HelloWorldServlet(String greeting) {
		this.greeting = greeting;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> headerList =(Enumeration<String>) request.getHeaders("Set-Cookie");
		final String GPVLU = "GPVLU";
		String gPVLUVal = "";
		while (headerList.hasMoreElements()) {
			String httpOnlyStr = (String) headerList.nextElement();
			System.out.println("http only cookie value:"+httpOnlyStr);
			int GPVLUIndex = httpOnlyStr.indexOf(GPVLU);
			if(GPVLUIndex != -1){
				gPVLUVal = httpOnlyStr.substring(GPVLUIndex+GPVLU.length()+1);
			}
			int endIndex = gPVLUVal.indexOf(";");
			gPVLUVal = gPVLUVal.substring(0,endIndex);
		}
		System.out.println("GPVLU:"+gPVLUVal);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>" + greeting + "</h1>");
		response.getWriter().println(
				"session=" + request.getSession(true).getId());
	}

}
