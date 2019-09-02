/**
 * 
 */
$(document).ready(function() {
	//查询按钮点击事件
	$("#query").click(function() {
		var requestSn = $("#deviceSN").val();
		var jsonData;
		$("#result").hide();
		$("#resultNavigator").hide();
		$.ajax({
			type : 'POST',
			async : true,
			url : './deviceQuery',
			data : {
				deviceSn : requestSn
			},
			success : function(result) {
				//服务器返回的json数据
				jsonData = eval("(" + result + ")");
//				var rainAreaMaxHtml;
				//遍历json
//				for (var i = 0; i < jsonData.AreaMax.data.length; i++) {
//					rainAreaMaxHtml += jsonData[0].remarks;
//				}
				var resultText = "";
				for(var key in jsonData){
					if(key == "responseCode" && jsonData[key] != "00"){
						//查询失败
						$("#resultNavigator").fadeIn("slow");
						$("#result").html("查询失败:<br />"+jsonData["remarks"]).fadeIn("slow");
						return;
					}
					if(key == "remarks"){
						resultText += ("<b>结果:</b><br />"+jsonData[key]+"<br />");
					}
					if(key == "deviceInfo"){
						//获取设备信息
						var deviceInfoJson = eval("(" + jsonData[key] + ")");
						for(var deviceKey in deviceInfoJson){
							resultText += ("<b>"+deviceKey+":</b><br />"+deviceInfoJson[deviceKey]+"<br />");
						}
						break;
					}
				}
//				var deviceInfo = jsonData.deviceInfo;
				$("#resultNavigator").fadeIn("slow");
				$("#result").html(resultText).fadeIn("slow");
			},
			error : function(result) {
				$("#resultNavigator").fadeIn("slow");
				$("#result").html("出错了").fadeIn("slow");;
			}
		});
	});
});

function resetInput() {
	//清空输入框
	$("#deviceSN").val("");
}
;