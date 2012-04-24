<%@ page language="java" import="net.snake.shell.Options" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;"%> 

<html>
	<head>
	
		<script>
			function editLine(){
				coffee=document.forms[0].coffee
				txt=""
				for (i=0;i<coffee.length;++ i)
				{
					if (coffee[i].checked)
					{
						txt=txt + "&";
						txt=txt + coffee[i].value + "="+document.getElementById(coffee[i].value).value;
					}
					
					
				}
				leng=txt.length;
				txt=txt.substr(1,leng-1)
				document.forms[0].action=document.forms[0]+"?"+txt;
				document.forms[0].submit();
			}
		</script>
	</head>
	<body>
	<form action="option.php" method="get">
		<table width="100%" border="1">
			<tr>
				<td>选项</td>
				<td>当前值</td>
				<td>新值</td>
				<td>是否修改</td>
			</tr>
			<tr>
				<td>是否允许  GM 命令</td>
				<td><%=Options.EnableGMCmd%></td>
				<td><input id="1" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="1"></td>
			</tr>
			<tr>
				<td>是否允许统计processor性能</td>
				<td><%=Options.ProcessorPerformance%></td>
				<td ><input id="2" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="2"></td>
			</tr>
			<tr>
				<td>是否是跨服的第三方服务器</td>
				<td><%=Options.IsCrossServ%></td>
				<td><input id="3" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="3"></td>
			</tr>
			<tr>
				<td>服务器所属的分区</td>
				<td><%=Options.ServerId%></td>
				<td><input id="4" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="4"></td>
			</tr>
			<tr>
				<td>是否开启新手卡检查程序</td>
				<td><%=Options.FresherCard_Check%></td>
				<td><input id="5" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="5"></td>
			</tr>
			<tr>
				<td>是否启用防沉迷系统</td>
				<td><%=Options.AntiAddicted%></td>
				<td><input id="6" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="6"></td>
			</tr>
			<tr>
				<td>新手村地图编号</td>
				<td><%=Options.Fresher_Familytown%></td>
				<td><input id="7" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="7"></td>
			</tr>
			<tr>
				<td>人物濒死倒计时时长，单位秒</td>
				<td><%=Options.Shock_Timeout%></td>
				<td><input id="8" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="8"></td>
			</tr>
			<tr>
				<td>怪物濒死倒计时时长，单位秒</td>
				<td><%=Options.Shock_Timeout_Monster%></td>
				<td><input id="9" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="9"></td>
			</tr>
			<tr>
				<td>当玩家选择“服”，怪物依然击杀玩家的几率 百分比</td>
				<td><%=Options.Shock_AttackProb%></td>
				<td><input id="10" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="10"></td>
			</tr>
			<tr>
				<td>人物复活倒计时时长，单位秒</td>
				<td><%=Options.Relive_Timeout%></td>
				<td><input id="11" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="11"></td>
			</tr>		
			<tr>
				<td>心跳消息号</td>
				<td><%=Options.Msg_Heart%></td>
				<td><input id="12" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="12"></td>
			</tr>
			<tr>
				<td>怒气上限</td>
				<td><%=Options.MAX_SP%></td>
				<td><input id="13" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="13"></td>
			</tr>
			<tr>
				<td>连斩可计数的等级差</td>
				<td><%=Options.KillGradeLimit%></td>
				<td><input id="14" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="14"></td>
			</tr>
			<tr>
				<td>JUMP_DISTANCE</td>
				<td><%=Options.JUMP_DISTANCE%></td>
				<td><input id="15" type="text"/></td>
				<td><input type="checkbox" name="coffee" value="15"></td>
			</tr>

	</table>
  		
			<input id="commit" type="button" value="提交" onclick="editLine()"/>
		</form>
  

</body>
</html>