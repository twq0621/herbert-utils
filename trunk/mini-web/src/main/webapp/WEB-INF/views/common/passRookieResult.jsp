<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>新注册游戏，并且通过新手引导的目标玩家</title>
</head>

<body>
	<h4 class="prepend-top">${queryDate}新注册游戏，并且通过新手引导的目标玩家</h4>
	<div>
		<span>${passRookieCount}</span><br />
		<input id="cancel" class="button" type="button" value="返回" onclick="history.back()"/>
	</div>
</body>
</html>
