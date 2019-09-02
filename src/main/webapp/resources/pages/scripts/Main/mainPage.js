/**
 * 
 */

$(document).ready(function() {
	$("#footer").children().css({"line-height":$("#footer").children().css("height")});
	$(".nav").children().css({"line-height":$(".nav").children().css("height")});
	$("#navDeviceManage").click(function(){
		$(".content").load("./deviceInfo");
	});
	$("#navUserInfo").click(function(){
		$(".content").load("./userManagePage");
	});
	$("#navMobileParam").click(function(){
		$(".content").load("./mobileParamPage");
	});
	$("#navContact").click(function(){
		$(".content").load("./contactPage");
	});
});