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
	src="<%=request.getContextPath()%>/resources/pages/scripts/UserInfo/userInfoQuery.js"></script>

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

#userInfoQueryForm {
	margin: auto;
	width: 300px;
	/**
	display: none;
	**/
	border: dotted;
	border-width: 1px;
}

#divQuery {
	float: left;
	position:absolute;
	top: 40%;
}

/**
#resultNavigator {
	float: left;
	margin-left: 4%;
	display: none;
}

#resultNavigator span{
	font-size: 2em;
}
**/

#result {
	text-align: left;
	padding: 10px 20px;
	border: dotted;
	border-width: 1px;
	width: 300px;
	margin: auto;
	position:absolute;
	top: 40%;
	/**
	**/
	display: none;
}
</style>
</head>

<body class="contentBody">
	<form id="userInfoQueryForm">
		<div id="divQuery">
			<div
				style="width: 300px; margin: 0px auto; border: dotted; border-width: 1px;">
				<table style="vertical-align: middle;border-spacing: 1em;">
					<tr>
						<td style="text-align: right;"><span>身份证号:</span></td>
						<td><input type="text" id="userIdNum" class="userIdNum"
							name="userIdNum" required></input></td>
					</tr>
				</table>
			</div>
			<div style="text-align: center">
				<input type="button" id="query" class="query" value="查询" required></input>
				<input type="button" id="reset" class="reset" value="重置"
					onclick="resetInput()"></input>
			</div>
		</div>
		<div id="result">
			dsfljsdoifjpsdjfpjspdjgpsdjpgfdsfsdfsdfsdfsdfsdfsadgsadgasdsdfsdf165165165
		</div>

	</form>
	<!-- 
	<div id="resultNavigator">
		<span>=></span>
	</div>
	 -->

</body>
</html>
