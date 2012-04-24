<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030" %>
<%@page import="net.snake.GameServer"%>
<%@page import="net.snake.quartz.*" %>
<%@page import="net.snake.quartz.*" %>
<%@page import="org.quartz.*" %>
<%@page import="java.util.*" %>
<%@page import="java.util.Calendar" %>
<%@page import="net.snake.bean.biguandazuo.MyDazuoAndUnDaduoManager"%>
<%
class DazuoJiachengExpQuartz implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		if(isJiachengTime()){
			MyDazuoAndUnDaduoManager.DAZUO_ADD_ZHENQI=3;
			GameServer.quartzapi.createJob("dazuoZhenqiUpdate","0 0 8 * * ?",DazuoJiachengExpQuartz.class);
		}else{
			MyDazuoAndUnDaduoManager.DAZUO_ADD_ZHENQI=1;
			GameServer.quartzapi.createJob("dazuoZhenqiUpdate","0 0 0 * * ?",DazuoJiachengExpQuartz.class);
		}
	}
	public boolean isJiachengTime() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		if (hours < 8) {
			return true;
		}
		return false;
	}
}
DazuoJiachengExpQuartz updateZhenqi=new DazuoJiachengExpQuartz();
if(updateZhenqi.isJiachengTime()){
	MyDazuoAndUnDaduoManager.DAZUO_ADD_ZHENQI=3;
	GameServer.quartzapi.createJob("dazuoZhenqiUpdate","0 0 8 * * ?",DazuoJiachengExpQuartz.class);
}else{
	MyDazuoAndUnDaduoManager.DAZUO_ADD_ZHENQI=1;
	GameServer.quartzapi.createJob("dazuoZhenqiUpdate","0 0 0 * * ?",DazuoJiachengExpQuartz.class);
}
GameServer.quartzapi.createJob("TimerUpdateSceneBangqiToDb","0 */2 * * *",TimerUpdateSceneBangqiToDbQuartz.class);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>游戏服务器实时监控</title>
</head>
<body>
<h1>恭喜你，任务更新成功！！每小时更新！！！</h1>
</body>
</html>