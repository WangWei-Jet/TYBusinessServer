<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
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
	src="<%=request.getContextPath()%>/resources/pages/scripts/DeviceInfo/deviceInfo.js"></script>

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

ol{
	list-style: none;
}

#nav p:hover {
	color: blue;
	cursor: pointer;
}

#section {
	/**
	border: dotted;
	border-width: 2px;
	**/
	height: 90%;
	width: 88%;
	float: left;
}

#section .discription {
	/**
	border: dotted;
	border-width: 2px;
	**/
	margin: 13% auto;
}
</style>
</head>

<body class="commonPageBody">
	<div id="header">
		<h1>终端管理</h1>
	</div>

	<div id="nav">
		<p id="deviceQuery">终端查询</p>
		<p id="deviceInput">终端录入</p>
		<p id="deviceModify">终端修改</p>
		<p id="deviceDelete">终端删除</p>
	</div>

	<div id="section">
		<div class="discription">
			<h2>终端管理</h2>
			<p>
				<strong>当前支持以下功能:</strong>
			</p>
			<ol>
				<li>终端查询</li>
				<li>终端录入</li>
				<li>终端信息修改</li>
				<li>终端删除</li>
			</ol>
		</div>
	</div>
</body>
</html>
