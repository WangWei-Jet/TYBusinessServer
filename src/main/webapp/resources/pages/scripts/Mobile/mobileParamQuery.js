/**
 * 
 */
$(document).ready(function() {
	//查询按钮点击事件
	$("#query").click(function() {
		var requestSn = $("#mobileModel").val();
		var jsonData;
		$("#result").hide();
		$("#resultNavigator").hide();
		$.ajax({
			type : 'GET',
			url : 'http://10.8.50.165:8083/DeviceConfigsServer/queryBtParam',
			dataType: "text",
			data : {
//				mac:"74:F8:DB:F8:E3:95",
				mac:$("#deviceMac").val(),
				model:$("#mobileModel").val()
			},
			success : function(result) {
				//服务器返回的json数据
//				jsonData = eval("(" + result + ")");
//				var rainAreaMaxHtml;
				//遍历json
//				for (var i = 0; i < jsonData.AreaMax.data.length; i++) {
//					rainAreaMaxHtml += jsonData[0].remarks;
//				}
				var resultText = "<strong>服务器响应结果:</strong><br /><br />"+result+"";
				/**
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
				**/
//				var deviceInfo = jsonData.deviceInfo;
				$("#resultNavigator").fadeIn("slow");
				$("#result").html(resultText).fadeIn("slow");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("#resultNavigator").fadeIn("slow");
				$("#result").html("请求出错<br />状态码:"+XMLHttpRequest.readyState).fadeIn("slow");;
			}
		});
	});
});

function resetInput() {
	//清空输入框
	$("#mobileModel").val("");
}
;