function doFirst() {
	$('#getPwdBtn').click(function(e) {
		$.ajax({
			type : "post",
			url : "http://localhost:8080/ezfit/api/login/passwordServlet",
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
