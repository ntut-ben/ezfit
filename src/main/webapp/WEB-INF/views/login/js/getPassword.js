function doFirst() {
	$('#getPwdBtn').click(function(e) {
		$.ajax({
			type : "post",
			url : "passwordServlet.do",
			data : {
				memberName : $('#memberName').val(),
				Email1forPwd : $('#Email1forPwd').val()
			},
			dataType : "text",
			success : function(response) {
				alert(response);
				// document.getElementById('checkCode').innerHTML = response;
			},
		});

	});

}
window.addEventListener('load', doFirst);
