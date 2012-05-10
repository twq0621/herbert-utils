package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\r\n");
      out.write("    \r\n");
      out.write("    <title>宸ュ叿鐢熸垚xml璇风偣鍑昏鐢熸垚鐨勮〃鏄�/title>\r\n");
      out.write("    \r\n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\r\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \r\n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\r\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\r\n");
      out.write("\t<!--\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">\r\n");
      out.write("\t-->\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("  <h1>杩欑粰鏄椿鍔ㄦ墦鍖�/h1>\r\n");
      out.write("  \t\t涓嬭竟鏄綋鍓嶆墽琛岀殑妯＄増type鏄�鐨勮瘽灏辨槸鎵ц瀹屼簡1鐨勮瘽灏辨槸姝ｅ湪鎵ц涓�br>\r\n");
      out.write("   <tr bgcolor=\"#FF0000\"><iframe src=\"/antcall/anttype\" name=\"ifrmname\" id=\"ifrmid\" width=\"500\" height=\"40\" frameborder=\"0\" align=\"top\" ></iframe></tr>\r\n");
      out.write("   \t<script type=\"text/javascript\">  \r\n");
      out.write(" function reflesh(){\t\r\n");
      out.write("\r\n");
      out.write("document.location.reload();\r\n");
      out.write("}\r\n");
      out.write("setTimeout(\"reflesh()\",10*1000);//姣�0绉掗挓鍒锋柊涓�\r\n");
      out.write("</script>\r\n");
      out.write("  \r\n");
      out.write("  <body>\r\n");
      out.write("    t<br/>\r\n");
      out.write("  \t<a href=\"/antcall/ant?task=build\"  target=\"_blank\">鐢熸垚鎵�湁琛ㄩ厤缃畑ml(鎺掗櫎avatar浜虹墿鎹㈣锛宎vatar_effect鏁堟灉)</a><br>\r\n");
      out.write("\t<br/>\r\n");
      out.write("  \t<a href=\"/antcall/ant?task=updateFlash\"  target=\"_blank\">鏇存柊瀹㈡埛绔洰褰�瀵瑰鎴风璧勬簮杩涜鍔犲瘑,骞跺皢鍔犲瘑鍚庣殑鏂囦欢鑷姩鎻愪氦svn</a><br>\r\n");
      out.write("\t<br/>\r\n");
      out.write("\t    <a href=\"/antcall/ant?task=lianti\"  target=\"_blank\">lianti浜虹墿杩炰綋锛堣繖涓笉鍐嶇敓鎴愭墍鏈夐厤缃畑ml閲岃竟闇�鍗曠嫭鐢熸垚</a><br>\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("\t\t<a href=\"/antcall/ant?task=language\"  target=\"_blank\">lan璇█閰嶇疆</a><br>\t\r\n");
      out.write("\t<br/>\r\n");
      out.write("\t<a href=\"/antcall/ant?task=hiddenweapons\"  target=\"_blank\">鏇存柊hiddenweapons鏆楀櫒</a><br>\r\n");
      out.write("\t<a href=\"/antcall/ant?task=equipmentplayconfig\"  target=\"_blank\">鏇存柊equipmentplayconfig.xml瑁呭鐜╂硶</a><br>\r\n");
      out.write("\t<br/>\r\n");
      out.write("\t<a href=\"/antcall/ant?task=goodsdc\"  target=\"_blank\">goodsdc鍙ょ帺鏀堕泦</a><br>\r\n");
      out.write("\t<a href=\"/antcall/ant?task=instance\"  target=\"_blank\">instance鍓湰</a><br>\r\n");
      out.write("\t<a href=\"/antcall/ant?task=achieve\"  target=\"_blank\">achieve浜虹墿鎴愬氨</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=goodmodel\"  target=\"_blank\">goodmodel</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=npc\"  target=\"_blank\">npc</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=horsemodel\"  target=\"_blank\">horsemodel</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=map\"  target=\"_blank\">trans浼犻�鐐�/a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=map2\"  target=\"_blank\">map鍦板浘鏁版嵁</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=task\"  target=\"_blank\">task</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=taskdialog\"  target=\"_blank\">taskdialog</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=skill\"  target=\"_blank\">skill</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=effect\"  target=\"_blank\">skilleffect</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=monstermodel\"  target=\"_blank\">monstermodel</a><br>\r\n");
      out.write("\t<a href=\"/antcall/ant?task=sceneMonster\"  target=\"_blank\">sceneMonster</a><br>\t\r\n");
      out.write("\t<br/>\r\n");
      out.write("\t\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=avatar\"  target=\"_blank\">avatar浜虹墿鎹㈣</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=avatar_effect\"  target=\"_blank\">avatar_effect鏁堟灉</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=iconToSwf\"  target=\"_blank\">灏忓浘鏍囨墦鍖卻wf锛屽ぇ鍥炬爣鐩綍鏋勫缓</a><br>\r\n");
      out.write("   \t<a href=\"/antcall/ant?task=sound\"  target=\"_blank\">闊抽swf鐢熸垚</a><br>\r\n");
      out.write("\t<br/>\r\n");
      out.write("\t---------------------------------------------------------------------------------------------------------<br>\r\n");
      out.write("\t");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
