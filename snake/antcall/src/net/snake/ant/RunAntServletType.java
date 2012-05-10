package net.snake.ant;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.snake.common.Constants;

public class RunAntServletType extends HttpServlet{
	
	private static final long serialVersionUID = 3599993072014489654L;

	private void writeResponse(HttpServletResponse resp, String content)throws IOException {
		OutputStream os = resp.getOutputStream();
		resp.setContentType("text/html");
		os.write(content.getBytes("utf-8"));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		 a=b&c=d&e=f
		 StringBuilder sb = new StringBuilder();
		 sb.append("name=");
		 sb.append(Constants.NAMEString+"&");
		 sb.append("time=");
		 sb.append(Constants.KSTimeString+"&");
		 sb.append("type=");
		 sb.append(Constants.TYPE);
		 
		writeResponse(resp,sb.toString());
		
	}
}
