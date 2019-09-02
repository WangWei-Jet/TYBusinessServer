/**
 * 
 */

$(document).ready(function() {
	var headerHeight = $("#header").css("height");
	$("#header").css({
		"line-height" : headerHeight
	});
	$("#mobileParamQuery").click(function() {
		$("#section").load("./mobileParamQueryPage");
	});
});