/**
 * 
 */
$(document).ready(function() {
	//查询按钮点击事件
	$("#query").click(function() {
		var jsonData;
//		$("#result").hide();
		$("#divQuery").hide();
//		$("#resultNavigator").hide();
		$.ajax({
			type : 'POST',
			url : './userInfo/showIDCardInfo',
			data : {
				//				mac:"74:F8:DB:F8:E3:95",
				userId : $("#userIdNum").val(),
			},
			success : function(result) {
				//服务器返回的json数据
				jsonData = eval("(" + result + ")");
				var resultText = "";
				for (var key in jsonData) {
					if (key == "responseCode" && jsonData[key] != "00") {
						//查询失败
						$("#resultNavigator").fadeIn("slow");
						$("#result").html("查询失败:<br />" + jsonData["remarks"]).fadeIn("slow");
						return;
					}
					if (key == "idCardInfo") {
						//获取设备信息
						var deviceInfoJson = eval("(" + jsonData[key] + ")");
						for (var deviceKey in deviceInfoJson) {
							//							if (deviceKey == "picturepath") {
							//								continue;
							//							}
							if (deviceKey == "name" || deviceKey == "sex" || deviceKey == "nation" 
								|| deviceKey == "birthdate" || deviceKey == "address" 
									|| deviceKey == "idnum" || deviceKey == "signingorganization" 
										|| deviceKey == "begintime" || deviceKey == "endtime") {
//								continue;
								resultText += ("<b>" + deviceKey + ":</b><br />" + deviceInfoJson[deviceKey] + "<br />");
							}
						}
						break;
					}
					resultText = ("<b>结果:</b><br />" + jsonData["remarks"] + "<br />") + resultText;
				}
				//				var deviceInfo = jsonData.deviceInfo;
//				$("#resultNavigator").fadeIn("slow");
				$("#result").html(resultText).fadeIn("slow");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
//				$("#resultNavigator").fadeIn("slow");
				$("#result").html("请求出错<br />状态码:" + XMLHttpRequest.readyState).fadeIn("slow");
				;
			}
		});
	});
});

function resetInput() {
	//清空输入框
	$("#userIdNum").val("");
}
;