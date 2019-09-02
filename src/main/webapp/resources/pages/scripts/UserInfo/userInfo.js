/**
 * 
 */

$(document).ready(function() {
	var headerHeight = $("#header").css("height");
	$("#header").css({
		"line-height" : headerHeight
	});
	$("#userInfoQuery").click(function() {
		$("#section").load("./userInfoQueryPage");
	});
});