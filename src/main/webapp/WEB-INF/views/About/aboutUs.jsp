<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<!--本地已下载的jQuery框架路径 -->
<script
	src="<%=request.getContextPath()%>/resources/global/scripts/jquery-3.2.1.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/global/scripts/jquery.min.js"></script>
<!-- jquery validate 插件	 -->
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/lib/jquery.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script
	src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/pages/scripts/About/aboutUs.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/global/css/global.css" />

</head>

<body class="commonPageBody">
	aboutUs page.
	<br>
</body>
</html>
