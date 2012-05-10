package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import net.snake.db.*;
import java.sql.*;

public final class myjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("銆�� \r\n");
      out.write("銆�� \r\n");


	data db= new data();

	String sql="SELECT DISTINCT f_type FROM `aojian_gamedata_v2`.`t_avatar`"; 
	String sql2="SELECT DISTINCT f_type FROM `aojian_gamedata_v2`.`t_effect`"; 


	
	ResultSet rs3 =db.executeQuery(sql);
	
 
      out.write("\r\n");
      out.write("銆��<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<table width=\"100%\" border=\"0\">\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t    <td> avatar浜虹墿鎹㈣璇︾粏淇℃伅</td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t    <td> <a href=\"http://192.168.1.203:8080/antcall/ant?task=avatar&ftype=0&time=1\"  target=\"_blank\">avatar浜虹墿鎹㈣鍏ㄩ儴绫诲埆褰撳ぉ鐢熸垚</a></td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("\t  \t");
while(rs3.next()){ 
      out.write("    \r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t    <td>绫诲埆锛�);
      out.print(rs3.getInt("f_type"));
      out.write("<a href=\"http://192.168.1.203:8080/antcall/ant?task=avatar&ftype=");
      out.print(rs3.getInt("f_type"));
      out.write("&time=1\"  target=\"_blank\">avatar浜虹墿鎹㈣褰撳ぉ鐢熸垚</a></td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t    <td>绫诲埆锛�);
      out.print(rs3.getInt("f_type"));
      out.write("<a href=\"http://192.168.1.203:8080/antcall/ant?task=avatar&ftype=");
      out.print(rs3.getInt("f_type"));
      out.write("&time=0\"  target=\"_blank\">avatar浜虹墿鎹㈣鎵�湁鐢熸垚</a></td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("\t      ");
} 
      out.write("\r\n");
      out.write("\t</table>\r\n");
      out.write("\r\n");
      out.write("\t<table width=\"100%\" border=\"0\">\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t    <td> avatar_effect鏁堟灉\t</td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t  <td>\r\n");
      out.write("\t   \t <a href=\"http://192.168.1.203:8080/antcall/ant?task=avatar_effect&ftype=0&time=1\"  target=\"_blank\">avatar_effect鏁堟灉鍏ㄩ儴绫诲埆褰撳ぉ鐢熸垚</a>\r\n");
      out.write("\t\t </td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("\t  \t");

	  	rs3 =db.executeQuery(sql2);
	  	while(rs3.next()){ 
      out.write("    \r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t    <td>绫诲埆锛�);
      out.print(rs3.getInt("f_type"));
      out.write("<a href=\"http://192.168.1.203:8080/antcall/ant?task=avatar_effect&ftype=");
      out.print(rs3.getInt("f_type"));
      out.write("&time=1\"  target=\"_blank\">avatar_effect鏁堟灉褰撳ぉ鐢熸垚</a></td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("\t  <tr>\r\n");
      out.write("\t    <td>绫诲埆锛�);
      out.print(rs3.getInt("f_type"));
      out.write("<a href=\"http://192.168.1.203:8080/antcall/ant?task=avatar_effect&ftype=");
      out.print(rs3.getInt("f_type"));
      out.write("&time=0\"  target=\"_blank\">avatar_effect鏁堟灉鎵�湁鐢熸垚</a></td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("\t      ");
} 
	       db.closeConn();
	      
      out.write("\r\n");
      out.write("\t</table>\r\n");
      out.write("  \r\n");
      out.write("  \r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
