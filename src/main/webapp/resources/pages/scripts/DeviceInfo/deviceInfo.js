/**
 * 
 */
$(document).ready(function() {
	var headerHeight = $("#header").css("height");
	$("#header").css({"line-height":headerHeight});
	$("#deviceQuery").click(function() {
		$("#section").load("./deviceQueryPage");
	});
});