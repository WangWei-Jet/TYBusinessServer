<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html">
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
	src="<%=request.getContextPath()%>/resources/pages/scripts/Main/mainPage.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/global/css/global.css" />
<style>
* {
	margin: 0;
	padding: 0;
	font-size: 14px;
}

a {
	color: #333;
	text-decoration: none
}

.nav {
	list-style: none;
	height: 8%;
	/**
	border-bottom: 10px solid #F60;
	**/
	margin: 2% auto 0 auto;
	/**
	padding-left: 50px;
	border: dotted;
	border-width: 2px;
	**/
	width: 80%;
	filter: alpha(opacity = 80);
	-moz-opacity: 0.8;
	-khtml-opacity: 0.8;
	opacity: 0.8;
}

.content {
	height: 86%;
	/**
	border-bottom: 10px solid #F60;
	**/
	margin: 0 auto;
	/**
	padding-left: 50px;
	border: dotted; border-width : 2px;
	**/
	filter: alpha(opacity = 80);
	-moz-opacity: 0.8;
	-khtml-opacity: 0.8;
	opacity: 0.8;
	width: 80%;
}

.nav li {
	float: left;
	width: 20%;
}

.nav li a {
	display: block;
	height: 100%;
	text-align: center;
	background: #efefef;
	margin-left: 1px;
}

.nav li a.on, .nav li a:hover {
	background: #F60;
	color: #fff;
	cursor: pointer;
}

#footer p{
	position: relative;
	background-color: black;
	color: white;
	text-align: center;
	margin: 0 auto;
	height: 4%;
	width: 80%;
}
</style>

</head>

<body class="commonPageBody">
	<ul class="nav">
		<li><a id="navMain">首 页</a></li>
		<li><a id="navDeviceManage">设备管理</a></li>
		<li><a id="navUserInfo">用户信息查询</a></li>
		<li><a id="navMobileParam">手机参数查询</a></li>
		<li><a id="navContact">联系我们</a></li>
	</ul>
	<div class="content"></div>

	<div id="footer">
		<p>Copyright ? OneWay.com</p>
	</div>
</body>
</html>
