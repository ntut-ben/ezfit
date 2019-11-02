<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員登入</title>
<link rel="stylesheet" href="main.css">

<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
<script src="new.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+TC:400&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="header_footer.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<div id="notification-menu">
				<div id="web-title">健康餐點 ＋ 食譜社群平台</div>
				<img id="title-logo" src="header_footer_image_icon/logo.png">

			</div>
			<div id="drop-down-menu">
				<div id="drop-down-menu-box">
					<div id="healthy-diet-plan-meal-menu-box">
						<div id="healthy-diet-plan-meal-menu">健康飲食計畫餐</div>
						<div class="ar"></div>
					</div>
					<div id="food-ingredients-menu-box">
						<div id="food-ingredients-menu">食材選購</div>
						<div class="ar"></div>
					</div>
					<div id="recipe-menu">食譜社群</div>
					<div id="qa-menu">常見問答</div>
				</div>
			</div>
		</header>
		<main>
		<div id="login">
			<div id="loginPart">
				<div id="loginText">會員登入</div>
				<hr id="green">
				<form action="${pageContext.request.contextPath}/api/login/loginServlet" method="post" id="theForm">
					<input type="email" class="accountInfo" placeholder="請輸入Email"
						id="memberEmail" name="memberEmail"
						value="${requestScope.email}${param.memberEmail}" />
					<div class="error">${ErrorMsgKey.AccountEmptyError}</div>
					<input type="password" class="accountInfo" placeholder="請輸入密碼"
						id="memberPassword" name="memberPassword"
						value="${requestScope.password}${param.memberPassword}" />
					<div class="error">${ErrorMsgKey.PasswordEmptyError}</div>
					<p>
						<input type="checkbox" name="rememberMe" value="true"
							<c:if test="${requestScope.rememberMe==true}" checked='checked'>
               </c:if>> 記住我

					</p>
					<div class="error">${ErrorMsgKey.LoginError}</div>
					<input class="submit" type="submit" value="登入" />
				</form>
				<div id="forget">忘記密碼？</div>

				<div id="gray">
					<span style="white-space: pre"></span><span class="line"></span> <span
						style="white-space: pre"></span><span class="txt">OR</span> <span
						style="white-space: pre"></span><span class="line"></span>
				</div>
				<img src="pic/loginFB.png" alt=""
					onclick="window.location.href='https://www.facebook.com'"> <img
					src="pic/loginGoogle.png" alt=""
					onclick="window.location.href='https://www.google.com'">
			</div>
			<div id="linkJoinus">
				<div id="joinText">尚未註冊會員?</div>
				<div id="welcome">歡迎加入我們，請點選下方連結</div>
				<button type="button" id="linkButton"
					onclick="window.location.href = '../createAccount/createAccount'">加入會員</button>
			</div>
			<div style="clear: both"></div>
		</div>
		</main>

		<footer>
			<div id="email-subscription">
				<input id="email-subscription-input-text" type="text"
					placeholder="輸入email獲得更多優惠資訊">
				<button id="subscription-button">訂閱</button>
			</div>
			<div id="about-us-box">
				<img id="footer-logo" src="header_footer_image_icon/logo-white.png">
				<div id="about-us">關於EZfit Eat</div>
				<div id="about-us-box-1">
					<p>公司資訊</p>
					<p>徵才訊息</p>
					<p>廣告合作</p>
				</div>
				<div id="about-us-box-2">
					<p>品牌資產</p>
					<p>服務條款</p>
					<p>隱私權政策</p>
				</div>
				<div id="partner">合作夥伴</div>
				<img id="taitung-farm-link"
					src="header_footer_image_icon/supplier_logo_1.png"> <img
					id="fusheng-vegetables-link"
					src="header_footer_image_icon/supplier_logo_2.png"> <img
					id="wotian-farm-link"
					src="header_footer_image_icon/supplier_logo_3.png">
				<div id="partner-java012-1">健客</div>
				<div id="partner-java012-2">室友是友 4U4U</div>
				<div id="community-box">
					<img id="email-link" src="header_footer_image_icon/email_icon.png".png">
					<img id="instagram-link"
						src="header_footer_image_icon/instagram_icon.png".png"> <img
						id="twitter-link" src="header_footer_image_icon/twitter_icon.png".png">
					<img id="facebook-link"
						src="header_footer_image_icon/facebook_icon.png".png">
				</div>
			</div>
			<div id="credit-card-icon">
				<img src="header_footer_image_icon/credit_card_icon_1.png"> <img
					src="header_footer_image_icon/credit_card_icon_2.png"> <img
					src="header_footer_image_icon/credit_card_icon_3.png"> <img
					src="header_footer_image_icon/credit_card_icon_4.png">
			</div>
			<div id="copyright-notice">Copyright ©2019 EZfit Eat 版權所有 All
				Rights Reserved</div>
		</footer>
	</div>
</body>
</html>