<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>新注册游戏，并且通过新手引导的目标玩家</title>
	<%@ include file="/WEB-INF/layouts/date-picker.jsp"%>
	<script>
		$(document).ready(function() {
			$("#inputForm").validate();
			$("#queryDate").datepicker({ dateFormat: "yy-mm-dd" });
		});
	</script>
</head>

<body>
	<form:form id="inputForm" modelAttribute="group" action="${ctx}/common/passRookie/result" method="post">
		<fieldset class="prepend-top">
			<legend>每天新注册游戏，并且通过新手引导的目标玩家</legend>
			<div>
				<label for="name" class="field">日期:</label>
				<input type="text" id="queryDate" name="queryDate" size="40" class="required" value="2012-03-03"/>
			</div>
		</fieldset>
		<div>
			<input id="submit" class="button" type="submit" value="提交"/>&nbsp;	
			<input id="cancel" class="button" type="button" value="返回" onclick="history.back()"/>
		</div>
	</form:form>
</body>
</html>
