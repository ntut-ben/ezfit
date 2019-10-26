<%-- 
  Version: eDMo
     新增功能: 
     (1)維護書籍資料的功能全部改為Annotation
     (2)存取資料庫的功能全部改為介面
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/nav.css" />

</head>

<body>
	<!-- Navigation Bar ========================================================================== -->
	<!-- Top ==================== -->
	<div style="position: fixed; width: 100%;">
		<nav id="navbar-top" class="navbar navbar-expand-lg  p-0 ">
			<div class="container-fluid m-0 p-0">
				<div class="row no-gutters justify-content-center"
					style="width: 100%; margin: 0 20px;">
					<a class="navbar-brand col-4  m-0 align-self-center" id="b"
						href="#"> <img src="img/logo-g.svg" width="120px" />
					</a>
					<div class="col-4 m-0 text-center title"
						style="min-height: 65px; padding-top: 20px;">健康餐點 ＋ 食譜社群平台</div>

					<span id="c" class="col-4 m-0 align-self-center text-right member">
						<span id="message"></span> <span id="notification"></span> <span
						id="cart"></span> <c:if test="${empty LoginOK}">
							<a onclick="window.location.href = './login/login.jsp'" id="c"
								class="col-4 m-0 align-self-center text-right"> 會員登入 </a>
						</c:if> <c:if test="${ ! empty LoginOK }">
							<img width="60px" height="40px"
								src="${pageContext.request.contextPath}/img/egg.png">
							<a
								onclick="window.location.href = './createAccount/createAccount.jsp'"
								id="c" class="col-4 m-0 align-self-center text-right">${LoginOK.name}</a>
						</c:if> <span>&nbsp; | &nbsp;</span> <c:if test="${empty LoginOK}">
							<a
								onclick="window.location.href = './createAccount/createAccount.jsp'"
								id="c" class="col-4 m-0 align-self-center text-right">註冊會員</a>
						</c:if> <c:if test="${ ! empty LoginOK }">
							<a href="<c:url value='index.jsp' />"> 登出 </a>
<%-- 							<% --%>
// 								session.invalidate();
<%-- 							%> --%>
						</c:if> <!-- <img src="img/logged-in-all.svg" style="width: 280px;"> -->
					</span>
				</div>
			</div>
		</nav>
		<!-- Bottom ================================== -->
		<nav id="navbar-btm"
			class="navbar navbar-expand-lg navbar-light p-0 m-0">
			<div class="container-fluid m-0 p-0">
				<div class="row no-gutters justify-content-center align-middle"
					style="width: 100%; margin: 0 20px;">
					<a class="navbar-brand col-3 m-0 align-self-center" id="a" href="#">
						<img id="a" src="img/logo-w.svg" width="120px" />
					</a>

					<div class="btn-group col-6 m-0 p-0" role="group"
						aria-label="Button group with nested dropdown">
						<table class="nav-table">
							<tr>
								<td>
									<div class="btn-group dropdown" role="group">
										<button id="btnGroupDrop1" type="button"
											class="btn btn-success dropdown-toggle"
											data-toggle="dropdown" data-hover="dropdown"
											aria-haspopup="true" aria-expanded="false">健康飲食計劃餐</button>
										<div class="dropdown-menu plan-menu dropdown-menu-center"
											aria-labelledby="btnGroupDrop1">
											<div class="container pl-1">
												<div class="row">
													<div class="col-4">
														<div class="card" style="width: 275px;">
															<img src="img/bread.jpg" class="card-img-top" alt="..." />
															<div class="card-img-overlay">
																<h5 class="card-title text-center">健康營養計畫</h5>
															</div>
															<div class="card-body">
																<p class="card-text">堅持使用新鮮食材，少油、低鈉，讓外食族的您也 能吃得健康。</p>
																<div class="text-right">
																	<a href="#" class="btn btn-success">現在就訂</a>
																</div>
															</div>
														</div>
													</div>
													<div class="col-4">
														<div class="card" style="width: 275px;">
															<img src="img/bowl.jpg" class="card-img-top" alt="..." />
															<div class="card-img-overlay">
																<h5 class="card-title text-center">強身健體計畫</h5>
															</div>
															<div class="card-body">
																<p class="card-text">針對增肌族群，提供最豐富、優質的蛋白質，讓您 的一天充滿活力。
																</p>
																<div class="text-right">
																	<a href="#" class="btn btn-success">現在就訂</a>
																</div>
															</div>
														</div>
													</div>
													<div class="col-4">
														<div style="width: 275px;"></div>
													</div>
												</div>
												<div class="row pt-2">
													<div class="col-4">
														<div class="card" style="width: 275px;">
															<img src="img/ella.jpg" class="card-img-top" alt="..." />
															<div class="card-img-overlay">
																<h5 class="card-title text-center">輕盈窈窕計畫</h5>
															</div>
															<div class="card-body">
																<p class="card-text">減肥不用再餓肚子，精算熱量、營養調配，讓您吃
																	的健康，瘦的漂亮。</p>
																<div class="text-right">
																	<a href="#" class="btn btn-success">現在就訂</a>
																</div>
															</div>
														</div>
													</div>
													<div class="col-4">
														<div class="card" style="width: 275px;">
															<img src="img/lily.jpg" class="card-img-top" alt="..." />
															<div class="card-img-overlay">
																<h5 class="card-title text-center">晚餐隨選</h5>
															</div>
															<div class="card-body">
																<p class="card-text">可自由選擇，讓瞭解自己需求的人能夠選擇想 要的晚餐。</p>
																<div class="text-right">
																	<a href="#" class="btn btn-success">現在就訂</a>
																</div>
															</div>
														</div>
													</div>
													<div class="col-4">
														<div class="card text-white mb-3"
															style="width: 275px; min-height: 268px; background-color: #2eaa00; border: none;">
															<div class="card-body">
																<h4 class="cal m-2">我適合哪個計畫呢？</h4>
																<p class="cal-text m-2">利用我們的小工具，幫你搭配最適合你的餐點計畫</p>
																<div class="text-right"
																	style="padding: 120px 10px 0 10px;">
																	<a href="#" class="btn btn-light"
																		style="border-radius: 5px; color: #2eaa00;">開始使用小工具</a>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</td>
								<td>
									<div class="btn-group dropdown" role="group">
										<button id="btnGroupDrop2" type="button"
											class="btn btn-success dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">食材選購</button>
										<div class="dropdown-menu" aria-labelledby="btnGroupDrop2"
											style="width: 600px;">
											<div style="color: #2eaa00;">快速分類</div>
											<img src="img/Path233.svg" style="width: 300px;" alt="..." />
											<div class="container grocery">
												<div class="row p-0">
													<div class="col-3 p-0">
														<li><a href="#">蔬菜</a></li>
														<li><a href="#">水果</a></li>
														<li><a href="#">牛羊豬肉</a></li>
														<li><a href="#">雞鴨鵝肉</a></li>
														<li><a href="#">魚蝦海鮮</a></li>
														<li><a href="#">米麵雜糧</a></li> <br /> <br />
														<li><a href="shopMaterial.html""><img src="img/goshop.svg"
																style="width: 120px;" /></a></li>
													</div>
													<div class="col-3 p-0">
														<li><a href="#">醃漬/加工品</a></li>
														<li><a href="#">蛋/蛋製品</a></li>
														<li><a href="#">奶類/奶製品</a></li>
														<li><a href="#">豆腐/豆製品</a></li>
														<li><a href="#">麵粉/烘焙用粉</a></li>
													</div>
													<div class="col-6 p-0 text-right">
														<img src="img/asparagus.jpg" style="width: 235px;" alt="" />
													</div>
												</div>
											</div>
										</div>
									</div>
								</td>
								<td>
									<button type="button" class="btn btn-success">食譜社群</button>
								</td>
								<td>
									<button type="button" class="btn btn-success">常見問答</button>
								</td>
							</tr>
						</table>
					</div>

					<span id="d" class="col-3 m-0 align-self-center text-right member">
						<span id="message"></span><span id="notification"></span><span
						id="cart"></span> <a id="login" href="http://www.google.com">會員登入</a><span>&nbsp;
							| &nbsp;</span><a id="register" href="#">註冊會員</a>
					</span>
				</div>
			</div>
		</nav>
	</div>

	<!-- ========================================================================= -->

	<div>
		<img src="img/black-bean-close-up-cooked-1640774.jpg" width="100%" />
		<img src="img/basil-delicious-food-459469.jpg" width="100%" /> <img
			src="img/close-up-colors-cooking-2284166.jpg" width="100%" /> <img
			src="img/tryitnow.jpg" width="100%" />
	</div>

	<!-- ============================================================================= -->

	<footer>
		<div class="search-text">
			<div class="container">
				<div class="row justify-content-center text-center">
					<div class="form">
						<form id="search-form" class="form-search form-horizontal">
							<input type="text" class="input-search"
								placeholder="輸入 email 獲得更多優惠資訊" />
							<button type="submit" class="btn-search">訂閱</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-6 col-xs-12 ">
					<div class="logo">
						<img src="img/logo-all-white.svg" style="width: 250px;" />
					</div>
					<div class="social">
						<a href="#"><img src="img/facebook.svg" /></a> <a href="#"><img
							src="img/twitter.svg" /></a> <a href="#"><img
							src="img/instagram.svg" /></a> <a href="#"><img
							src="img/line.svg" /></a>
					</div>
				</div>

				<div class="col-md-4 col-sm-6 col-xs-12">
					<ul class="menu">
						<span>關於EZfit Eat</span>
						<li><a href="#">公司資訊</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
							href="#">服務條款</a></li>

						<li><a href="#">徵才訊息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
							href="#">服務條款</a></li>

						<li><a href="#">廣告合作</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
							href="#">隱私權政策</a></li>
					</ul>
				</div>

				<div class="col-md-4 col-sm-6 col-xs-12 text-center">
					<ul class="address">
						<span>合作夥伴</span>
						<li><img src="./img/tlogo.png" style="width: 70px;" /> <img
							src="./img/w-logo.png" style="width: 70px;" /> <img
							src="./img/logonew-1.png" style="width: 70px;" /></li>
						<li><i class="fa fa-map-marker" aria-hidden="true"></i> <a
							href="#">室友是友 4U4U</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#">健客
								Fititude</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="paymenttype text-center">
			<img src="img/logo-visa.svg" alt="" /> <img
				src="img/logo-master.svg" alt="" /> <img src="img/logo-jcb.svg"
				alt="" /> <img src="img/logo-amex.svg" alt="" />
		</div>
		<div class="copyright text-center">Copyright ©2019 EZfit Eat
			版權所有 All Rights Reserved</div>
	</footer>

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
	<script src="js/nav.js"></script>
</body>
</html>