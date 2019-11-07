<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/login/css/login.css" />
<script src="${pageContext.request.contextPath}/login/js/getPassword.js"></script>
<title>會員登入</title>

<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+TC:400&display=swap"
	rel="stylesheet" />
<script src="https://www.google.com/recaptcha/api.js"></script>

</head>
<body>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v4.0&appId=505880339962412&autoLogAppEvents=1"></script>
	<jsp:include page="../header.jsp" />
	<!-- Main -->

	<div class="main text-center text-dark" id="bigMain">
		<div class="container my-container mx-auto">
			<div class="row main">
				<div class="left col-6 mx-auto bg-light">
					<div
						class="col-10 mx-auto border-bottom border-success text-success mt-4">
						<h3>會員登入</h3>
					</div>
					<div class="col-10 mx-auto text-dark ">
						<form action="${pageContext.request.contextPath}/api/login/loginServlet" method="post" id="theForm">
							<div class="form-group mt-3">
								<input type="email" class="form-control" id="memberEmail"
									name="memberEmail" aria-describedby="emailHelp"
									placeholder="請輸入Email"
									value="${requestScope.email}${param.memberEmail}" />
							</div>
							<div class="error">${ErrorMsgKey.AccountEmptyError}</div>
							<div class="form-group">
								<input type="password" class="form-control" id="memberPassword"
									name="memberPassword" placeholder="請輸入密碼"
									value="${requestScope.password}${param.memberPassword}" />
							</div>
							<div class="error">${ErrorMsgKey.PasswordEmptyError}</div>
							<div class="error">${ErrorMsgKey.LoginError}</div>
							<div class="form-group form-check">
								<input type="checkbox" class="form-check-input" id="rememberMe"
									name="rememberMe"
									<c:if test='${requestScope.rememberMe==true}'>
                  
                  checked='checked'
               </c:if>
									value="true" /> <label class="form-check-label"
									for="exampleCheck1">記住帳號密碼</label>
							</div>

							<!-- 我是機器人 -->
							<div class="form-group w-50 ml-5">
								<div class="g-recaptcha" id="g-recaptcha-response"
									data-sitekey="6LcUJr8UAAAAADJK1RT86ze3nJFjSY68D4jvs5YL"></div>
							</div>
							<div class="error">${verifybug.verifybug}</div>
							<div class="form-group">
								<button type="submit" class="btn btn-success btn-lg btn-block">
									登入</button>
							</div>
						</form>

						<!-- Button trigger modal -->
						<button type="button" class="btn btn-link" data-toggle="modal"
							data-target="#exampleModalCenter">忘記密碼？</button>

						<!-- Modal: 忘記密碼小視窗 -->
						<div class="modal fade" id="exampleModalCenter" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalCenterTitle"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalCenterTitle">
											忘記密碼</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<label for="exampleInputEmail1">Email address</label> <input
											type="email" class="form-control" id="Email1forPwd"
											name="Email1forPwd" aria-describedby="emailHelp"
											placeholder="Enter email" /> <small id="emailHelp"
											class="form-text text-muted">Email須為已註冊帳號</small>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">取消</button>
										<button type="button" class="btn btn-success" id="getPwdBtn">取得密碼</button>
									</div>
								</div>
							</div>
						</div>

						<div class="col" id="or"></div>
						<div class="fb-login-button" data-width="" data-size="large"
							data-button-type="login_with" scope="public_profile,email"
							onlogin="checkLoginState();" data-use-continue-as="true"
							data-auto-logout-link="false"></div>
						<!-- <div class="col" id="googleButton">
							<button type="button" class="btn btn-lg btn-block">
								<img src="./img/loginGoogle.png" alt="" />
							</button>
						</div> -->
					</div>
				</div>
				<div class="right col-6 text-center">
					<div class="row justify-content-center align-content-center"
						id="smallRow">
						<div class=" col-10 text-success">
							<h2>尚未註冊會員?</h2>
						</div>
						<div class="col-10 text-center mt-4">
							<h3>歡迎加入我們，請點選下方連結</h3>
						</div>
						<div class="col-8 mt-4">
							<button type="button" class="btn btn-outline-success btn-block"
								id="registerBtn"
								onclick="window.location.href = '${pageContext.request.contextPath}/createAccount/createAccount'">
								加入會員</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>

</body>
</html>