/**
 * 
 */
function resetInput() {
	document.getElementById("userName").value = "";
	document.getElementById("password").value = "";
//alert("点击了重置");
}

$(document).ready(function() {
	var loginForm = $("#loginForm");
	loginForm.validate({
		rules : {
			userName : {
				required : true,
				minlength : 4
			}
		},
		messages : {
			userName : {
				required : "必填",
				minlength : "太短"
			}
		}
	});
	$(".userName").blur(function() {
		//alert("失去焦点");
	});
	$("#login").click(function() {
		if(loginForm.submit()){
			alert("验证通过");
		}else{
			alert("验证失败");
		}
//		return false;
		//发送ajax请求
		/**
		$.ajax({
			type : 'POST',
			async : true,
			url : './userInfo/show',
			data : $("#loginForm").serialize(),
			success : function(result) {
				$("#result").html(result);
			},
			error : function(result) {
				$("#result").html("出错了");
			}
		});
		**/
	//alert("点击了登录");
	});
});