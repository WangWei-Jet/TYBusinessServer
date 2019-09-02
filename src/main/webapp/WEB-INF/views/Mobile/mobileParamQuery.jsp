<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<!-- jquery 库
<script
	src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js">
</script>
 -->
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

<!-- 相关js路径  -->
<script
	src="<%=request.getContextPath()%>/resources/pages/scripts/Mobile/mobileParamQuery.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/global/css/global.css" />

<style type="text/css">
.error {
	color: red;
}

input {
	opacity: 0.7;
	width: 12em;
}

input.query, input.reset {
	opacity: 0.8;
	width: 8em;
	height: 2.5em;
	margin: 1em;
	text-transform: none;
	font-weight: bold;
}

#mobileParamQueryForm {
	margin: 16% auto;
	width: 300px;
}

#divQuery {
	float: left;
}

#resultNavigator {
	float: left;
	margin-left: 4%;
	display: none;
}

#resultNavigator span{
	font-size: 2em;
}

#result {
	float: left;
	text-align: left;
	padding: 10px 20px;
	border: dotted;
	border-width: 1px;
	margin-left: 4%;
	display: none;
}
</style>
</head>

<body class="contentBody">
	<form id="mobileParamQueryForm">
		<div id="divQuery">
			<div
				style="width: 300px; margin: 0px auto; border: dotted; border-width: 1px;">
				<table style="vertical-align: middle;border-spacing: 1em;">
					<tr>
						<td style="text-align: right;"><span>手机型号:</span></td>
						<td><input type="text" id="mobileModel" class="mobileModel"
							name="mobileModel" required></input></td>
					</tr>
					<tr>
						<td style="text-align: right;"><span>设备mac:</span></td>
						<td><input type="text" id="deviceMac" class="deviceMac"
							name="deviceMac" value="74:F8:DB:F8:E3:95" required></input></td>
					</tr>
				</table>
			</div>
			<div style="text-align: center">
				<input type="button" id="query" class="query" value="查询" required></input>
				<input type="button" id="reset" class="reset" value="重置"
					onclick="resetInput()"></input>
			</div>
		</div>

	</form>
	<div id="resultNavigator">
		<span>=></span>
	</div>
	<div id="result">
		xxx<br />xxx<br />xxx<br />
	</div>

</body>
</html>
