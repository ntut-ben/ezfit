<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/createAccount.css">
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
<script src="js/createAccount.js"></script>
<script src="js/jquery.validate.js"></script>
<script src="js/additional-methods.js"></script>
<script src="js/getEmail.js"></script>
<!-- <script src="js/facebook.js"></script> -->
<!-- <script src="js/googleLogin"></script> -->
<title>加入會員</title>

</head>
<body>
	<script async defer crossorigin="anonymous"
		src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v4.0&appId=505880339962412&autoLogAppEvents=1"></script>
	<div id="topWhite">
		<div id="textInTop">健康餐點＋食譜社群平台</div>
		<div id="logo"></div>
	</div>
	<div class="greenBackground">
		<div class="main">健康飲食計劃餐</div>
		<div class="main">食材選購</div>
		<div class="main">食譜社群</div>
		<div class="main">常見問答</div>
	</div>
	<div id="image"></div>
	<table id="login" valign="top">
		<tr>
			<td id="loginLeft">加入會員
				<div class="greenLine"></div>
				<form action ="memberServlet.do" method="post" id="theForm">
					<input type="text" class="accountInfo" placeholder="姓名"
						id="memberName" name="memberName" value="${requestScope.name}${param.memberName}"> 
					<input type="email"
						class="accountInfo" placeholder="Email" id="memberEmail"
						name="memberEmail" value="${requestScope.email}${param.memberEmail}"> 
					<div class="error">${MsgMap.errorEmail}</div>
					<input type="password"
						class="accountInfo" placeholder="密碼 (8~12位元, 需包含英文大小寫及數字)"
						id="memberPassword" name="memberPassword" value="${requestScope.password}${param.memberPassword}"> 
					<input
						type="password" class="accountInfo" placeholder="確認密碼"
						id="memberPasswordCheck" name="memberPasswordCheck"> 
					<input
						type="text" class="accountInfo"
						placeholder="請按右方取得驗證碼 (發送至上方Email)" name="veriCode" id="veriCode">
					<button type="button" id="veriCodeButton" name="code">取得驗證碼</button>
					<div id="checkCode" name="checkCode" class="error">${MsgMap.errorCode}</div>
					<p>
						<input type="checkbox" id="serviceRule" name="serviceRule">我已經閱讀並同意
						<a href="https://www.facebook.com/legal/terms" name="service">服務條款</a>
					</p>
					<input class="submit" type="submit" value="加入會員">
				</form> <img src="pic/OR.png" id="OR"> 
				<div class="fb-login-button" data-width="380" data-size="medium" data-button-type="login_with" data-auto-logout-link="false" data-use-continue-as="false">
				Facebook登入
				</div>

				<button id="googleButton">
					<img src="pic/google.png" class="img" >
				</button> <br>
			</td>
			<td id="loginRight"><span id="span1">已經有帳號了？</span><br>
				<div></div> <span id="span2">請點選下方連結登入</span><br>
				<p></p> <input type="button" id="loginButton" value="會員登入"
				onclick="window.location.href = '../login/login.jsp'">
		</tr>
	</table>
</body>
</html>