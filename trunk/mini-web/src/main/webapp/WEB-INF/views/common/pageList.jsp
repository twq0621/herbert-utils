<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>常规信息管理</title>
	<script>
	 $(document).ready(function() {
		$("#message").fadeOut(3000);
	});
	</script>
</head>

<body>
	<h4 class="prepend-top">功能列表</h4>
	<c:if test="${not empty message}">
		<div id="message" class="success">${message}</div>	
	</c:if>
	
	<table id="contentTable">
		<tr><th>数据周期</th><th>需要分析内容</th><th>具体公式和内容</th><th>操作</th></tr>
		<tr>
			<td>每日</td>
			<td>每日新用户</td>
			<td>新注册的用户</td>
			<td>
				<a href="newRole" id="viewLink-1">查看</a>
			</td>
		</tr>
		<tr>
			<td>每日</td>
			<td>目标用户</td>
			<td>新注册游戏，并且通过新手引导的目标玩家</td>
			<td>
				<a href="passRookie" id="viewLink-2">查看</a>
			</td>
		</tr>
	</table>
</body>
</html>
