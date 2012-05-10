<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
　　<%@ page contentType="text/html;"%> 
　　<%@ page import="net.snake.db.*,java.sql.*"%> 
<%

	data db= new data();

	String sql="SELECT DISTINCT f_type FROM `aojian_gamedata_fan1`.`t_avatar`"; 
	String sql2="SELECT DISTINCT f_type FROM `aojian_gamedata_fan1`.`t_effect`"; 


	
	ResultSet rs3 =db.executeQuery(sql);
	
 %>
　　<body>



	<table width="100%" border="0">
	  <tr>
	    <td> avatar人物换装详细信息</td>
	  </tr>
	  <tr>
	    <td> <a href="http://192.168.1.203:8181/antcall/ant?task=avatar&ftype=0&time=1"  target="_blank">avatar人物换装全部类别当天生成</a></td>
	  </tr>
	  	<%while(rs3.next()){ %>    
	  <tr>
	    <td>类别：<%=rs3.getInt("f_type")%><a href="http://192.168.1.203:8181/antcall/ant?task=avatar&ftype=<%=rs3.getInt("f_type")%>&time=1"  target="_blank">avatar人物换装当天生成</a></td>
	  </tr>
	  <tr>
	    <td>类别：<%=rs3.getInt("f_type")%><a href="http://192.168.1.203:8181/antcall/ant?task=avatar&ftype=<%=rs3.getInt("f_type")%>&time=0"  target="_blank">avatar人物换装所有生成</a></td>
	  </tr>
	      <%} %>
	</table>

	<table width="100%" border="0">
	  <tr>
	    <td> avatar_effect效果	</td>
	  </tr>
	  <tr>
	  <td>
	   	 <a href="http://192.168.1.203:8181/antcall/ant?task=avatar_effect&ftype=0&time=1"  target="_blank">avatar_effect效果全部类别当天生成</a>
		 </td>
	  </tr>
	  	<%
	  	rs3 =db.executeQuery(sql2);
	  	while(rs3.next()){ %>    
	  <tr>
	    <td>类别：<%=rs3.getInt("f_type")%><a href="http://192.168.1.203:8181/antcall/ant?task=avatar_effect&ftype=<%=rs3.getInt("f_type")%>&time=1"  target="_blank">avatar_effect效果当天生成</a></td>
	  </tr>
	  <tr>
	    <td>类别：<%=rs3.getInt("f_type")%><a href="http://192.168.1.203:8181/antcall/ant?task=avatar_effect&ftype=<%=rs3.getInt("f_type")%>&time=0"  target="_blank">avatar_effect效果所有生成</a></td>
	  </tr>
	      <%} 
	       db.closeConn();
	      %>
	</table>
  
  

</body>
</html>