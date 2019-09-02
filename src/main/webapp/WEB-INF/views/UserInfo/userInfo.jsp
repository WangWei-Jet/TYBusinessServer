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
	src="<%=request.getContextPath()%>/resources/pages/scripts/UserInfo/userInfo.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/global/css/global.css" />

<style type="text/css">
#header {
	background-color: black;
	color: white;
	text-align: center;
	/**
	padding: 5px;
	**/
	vertical-align: middle;
	height: 10%;
}

#nav {
	line-height: 35px;
	background-color: #eeeeee;
	height: 90%;
	width: 10%;
	float: left;
	/**
	padding: 5px;
	**/
	filter: alpha(opacity = 80);
	-moz-opacity: 0.8;
	-khtml-opacity: 0.8;
	opacity: 0.8;
	filter: alpha(opacity = 80);
}

#nav p {
	border-bottom: dotted;
	border-bottom-width: 2px;
	padding: 0 auto;
	width: 100%;
}

ol, ul {
	list-style: none;
}

#nav p:hover {
	color: blue;
	cursor: pointer;
}

#section {
	/**
	border: dotted; border-width : 2px;
	**/
	height: 90%;
	width: 89%;
	text-align: center;
	float: left;
	border-width: 2px;
}

#section .discription {
	/**
	**/
	margin: 15% auto;
}
</style>
</head>

<body class="commonPageBody">
	<div id="header">
		<h1>用户信息管理</h1>
	</div>
	<div id="nav">
		<p id="userInfoQuery">用户信息查询</p>
	</div>

	<div id="section">
		<div class="discription">
			<p style="font-size: 1.5em;font-weight: bold;">目前支持以下功能:</p>
			<ul style="margin-top: 10px;">
				<li><strong style="font-size: medium;">用户信息查询</strong></li>
			</ul>
		</div>
	</div>
</body>
</html>
