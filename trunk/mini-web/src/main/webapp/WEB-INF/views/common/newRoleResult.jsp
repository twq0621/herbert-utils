<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>新注册的角色</title>
</head>

<body>
	<h4 class="prepend-top">${queryDate}新注册的角色数量</h4>
	<div>
		<span>${newUserCount}</span><br />
		<input id="cancel" class="button" type="button" value="返回" onclick="history.back()"/>
	</div>
</body>
</html>
