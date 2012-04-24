<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% 
String name = request.getParameter("name");
String passwd = request.getParameter("passwd");
if(name!=null && passwd!=null){
	if(passwd.equals("abc!(*@001")&& name.equals("kn_admin")){
		session.setAttribute("logon","no001");
		response.sendRedirect("_console.jsp");
	}
}
String alert="请正确填写姓名和密码<br/>";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录授权</title>
</head>
<body>

<%=alert%>
<form action="_login.jsp" method="post">
姓名<input name="name" type="text"/><br/>
密码<input name="passwd" type="password"/><br/>
<input type="submit"/>
</form>


</body>
</html>