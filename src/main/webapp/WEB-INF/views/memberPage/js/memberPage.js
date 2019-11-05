$(document).ready(function() {
	$("#datepicker").datepicker({
		changeMonth : true,
		changeYear : true
	});
	// 設定年份
	$("#datepicker").datepicker({
		yearRange : "1919:2001"
	});
	var yearRange = $(".selector").datepicker("option", "yearRange");
	$("#datepicker").datepicker("option", "yearRange", "1919:2001");

});
