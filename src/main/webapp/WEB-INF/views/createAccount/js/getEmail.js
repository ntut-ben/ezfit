function doFirst() {
	$('#veriCodeButton').click(function(e) {
		
		$.ajax({
			type : "post",
			url : "http://localhost:8080/ezfit/api/createAccount/emailServlet",
			data : {
				memberName : $('#memberName').val(),
				memberEmail : $('#memberEmail').val()
			},
			dataType : "text",
			success : function(response) {
				alert(response);
//				document.getElementById('checkCode').innerHTML = response;
			},
		});

	});

}
window.addEventListener('load', doFirst);


