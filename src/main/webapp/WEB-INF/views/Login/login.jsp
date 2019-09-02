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
	src="<%=request.getContextPath()%>/resources/pages/scripts/Login/login.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/global/css/global.css" />

<style type="text/css">
.error {
	color: red;
}

input {
	opacity: 0.7;
	width: 11em;
}

input.login, input.reset {
	opacity: 0.8;
	width: 8em;
	height: 2.5em;
	margin: 1em;
	text-transform: none;
	font-weight: bold;
}
</style>
</head>

<body id="loginPageBody">
	<form action="./mainPage" method="get" style="margin-top: 16%;"
		id="loginForm">
		<h2 style="text-align: center">登 录</h2>
		<div
			style=" border: dotted; border-width: 2px; width: 300px; margin: 0px auto;">
			<table style="vertical-align: middle;border-spacing: 1em;">
				<tr>
					<td style="text-align: right;"><span>用户名:</span></td>
					<td><input type="text" id="userName" class="userName"
						name="userName" required></input></td>
				</tr>
				<tr>
					<td style="text-align: right;"><span>密码:</span></td>
					<td><input type="password" id="password" class="password"
						name="userPwd"></input></td>
				</tr>
			</table>
			<!-- 
			<p>
				<label>用户名:</label> <input type="text" id="userName"
					class="userName" name="userName" required></input>
			</p>
			<p>
				<label>密码:</label> <input type="password" id="password"
					class="password" name="userPwd"></input>
			</p>
			 -->
			<span id="result"></span>
		</div>
		<div style="text-align: center">
			<input type="button" id="login" class="login" value="登录"></input> <input
				type="button" id="reset" class="reset" value="重置"
				onclick="resetInput()"></input>
		</div>

		<!-- 
		<span>用户名:</span> <input type="text" id="userName" class="userName"></input>
		<br/> <span>密码:</span> <input type="password" id="password"
			class="password"></input> <br />
		<div>
			<input type="submit" id="login" class="login" value="Login"
				style="text-align: left;"></input> <input type="button" id="reset"
				class="reset" value="Reset" style="text-align: right;"></input>
		</div>
		 -->

	</form>

</body>
</html>
