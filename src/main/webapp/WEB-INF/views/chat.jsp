<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Spring Boot簡單聊天室</title>
<meta charset="utf-8" />
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/websocket.css" rel="stylesheet">


<style>
.message-list {
	position: relative;
	overflow-y: scroll;
	height: 400px;
}

.contact-list {
	position: relative;
	overflow-y: scroll;
	height: 450px;
}

.alert-msg {
	
}
</style>
</head>
<body>
	<div class="alert d-none fixed-top float-right" role="alert"
		id="_alertdiv">
		<span class="alert-msg"> </span>
		<button type="button" class="close" onclick="closeAlert(this)">
			<span>&times;</span>
		</button>
	</div>
	<div class="container h-100">
		<div class="row">
			<div class="col-sm">
				<div class="card">
					<div class="card-header">The Meeting Room Communication</div>
					<div class="card-body">
						<div class="row">
							<div class="col-1">
								<label>CRID:</label>
							</div>
							<div class="col-3">
								<label th:text="${crid}" id="crid"></label>
							</div>
						</div>
						<div class="row">
							<div class="col-1">
								<label>My ID:</label>
							</div>
							<div class="col-3">
								<div class="input-group mb-3">
									<input type="text" class="form-control" placeholder="Your ID"
										aria-label="Your ID" aria-describedby="btnConnect" id="uid">
									<div class="input-group-append">
										<button class="btn btn-outline-secondary" type="button"
											id="btnConnect">Connect</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm"></div>
		</div>
		<div class="row h-100">
			<div class="col-3 h-100">
				<div class="card h-100">
					<div class="card-header">
						Contact List
						<!---->
					</div>
					<div class="card-body contact-list scrollbar-dusty-grass">
						<ul class="list-group" id="contactlist">

						</ul>
					</div>
				</div>
			</div>
			<div class="col-9 h-100">
				<div class="card">
					<div class="card-header">Messages</div>
					<div class="card-body message-list scrollbar-ripe-malinka">
						<div id="messagelistScroll">
							<ul class="list-group" id="messagelist">

							</ul>
						</div>
					</div>
					<div class="card-foot">
						<div class="row">
							<div class="col-1"></div>
							<div class="col-10">
								<div class="input-group mb-3">
									<input type="text" class="form-control"
										placeholder="Your message" aria-label="Your message"
										aria-describedby="btnSend" id="messageBox">
									<div class="input-group-append">
										<button class="btn btn-outline-secondary" type="button"
											id="btnSend">Send</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="js/websocket.js"></script>
</body>
</html>