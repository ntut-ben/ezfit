function doFirst() {
	$('#veriCodeButton').click(function(e) {
		$.ajax({
			type : "post",
			url : "emailServlet.do",
			data : {
				memberName : $('#memberName').val(),
				memberEmail : $('#memberEmail').val()
			},
			dataType : "text",
			success : function(response) {
				alert('已發送驗證碼到: ' + $('#memberEmail').val());
				document.getElementById('checkCode').innerHTML = response;
			},
		});

	});

}
window.addEventListener('load', doFirst);


