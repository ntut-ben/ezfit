<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>webSocket-测试用户</title>
<script type="text/javascript">
	$(function() {
		var websocket;
		if ('WebSocket' in window) {
			console.log("此浏览器支持websocket");
			websocket = new WebSocket("ws://localhost:8080/git_ezfit/chat/12345");
		} else if ('MozWebSocket' in window) {
			alert("此浏览器只支持MozWebSocket");
		} else {
			alert("此浏览器只支持SockJS");
		}
		websocket.onopen = function(evnt) {
			$("#tou").html("链接服务器成功!")
		};
		websocket.onmessage = function(evnt) {
			$("#msg").html($("#msg").html() + "<br/>" + evnt.data);
		};
		websocket.onerror = function(evnt) {
		};
		websocket.onclose = function(evnt) {
			$("#tou").html("与服务器断开了链接!")
		}

		$('#send').click(function() {
			send();
		});

		function send() {
			if (websocket != null) {
				var message = document.getElementById('message').value;
				websocket.send(message);
			} else {
				alert('未与服务器链接.');
			}
		}
	});
</script>
</head>

<body>
	<div class="page-header" id="tou">webSocket多终端聊天测试</div>
	<div class="well" id="msg"></div>
	<div class="col-lg">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="发送信息..."
				id="message"> <span class="input-group-btn">
				<button class="btn btn-default" type="button" id="send">发送</button>
			</span>
		</div>
	</div>
</body>

</html>
