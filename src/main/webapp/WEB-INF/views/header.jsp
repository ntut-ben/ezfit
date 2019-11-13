<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" /> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/index/css/nav.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/index/css/index.css" />
<!-- <script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script> -->
<script src="${pageContext.request.contextPath}/index/js/nav.js"></script>
<script src="${pageContext.request.contextPath}/index/js/index.js"></script>

<c:set var='debug' value='true' scope='application' />

<div class="header-one">

	<nav id="navbar-top" class="navbar navbar-expand-lg  p-0 ">
		<div class="container-fluid m-0 p-0">
			<div class="row no-gutters justify-content-center">
				<a class="navbar-logo col-4  m-0 align-self-center" id="b"
					href="${pageContext.request.contextPath}/index"> <img
					src="${pageContext.request.contextPath}/index/img/nav/logo-g.svg">
				</a>
				<div class="col-4 m-0 text-center title">健康餐點 ＋ 食譜社群平台</div>

				<span id="c"
					class="col-4 m-0 align-self-center text-right member dropdown">
					<c:if test="${ ! empty LoginOK }">
						<img class="img"
							style="width: 40px; height: 40px; border: 1px solid green; border-radius: 50%;"
							<c:choose>
							<c:when test='${! empty FB }'>src='${LoginOK.memberImage}'</c:when>
							<c:when test='${(empty FB) and (empty LoginOK.memberImage)}'>src='${pageContext.request.contextPath}/index/img/nav/logo-g.svg'</c:when>
							<c:otherwise> src='${pageContext.request.contextPath}/MemberImage/${LoginOK.pkey}.jpg' </c:otherwise>  
							</c:choose>>
					</c:if> <span id="notification"></span> <span id="cart"> </span> <c:if
						test="${empty LoginOK}">
						<a
							onclick="window.location.href = '${pageContext.request.contextPath}/login/login'"
							id="c" class="col-4 m-0 align-self-center text-right"> 會員登入 </a>
					</c:if> <c:if test="${ ! empty LoginOK }">
						<span class="col-4 m-0 align-self-center text-right"
							id="dropdownMenuButton" data-toggle="dropdown"
							data-hover="dropdown" aria-haspopup="true" aria-expanded="false">
							${LoginOK.name} </span>
						<div class="dropdown-menu p-1 hoverdrop"
							aria-labelledby="dropdownMenuButton"
							style="right: -40px; left: unset; min-width: unset;">
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/my_page?ownerId=${LoginOK.pkey}&page=1&year=2019">我的食譜</a> <a
								class="dropdown-item"
								href="${pageContext.request.contextPath}/memberPage/memberPage">管理帳號</a>
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/orders">訂單管理</a> <a
								class="dropdown-item"
								href="${pageContext.request.contextPath}/groupBuying">發起團購</a> <a
								class="dropdown-item"
								href="${pageContext.request.contextPath}/cartList">購物車</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item"
								href="${pageContext.request.contextPath}/login/logout">登出</a>
						</div>
					</c:if> <c:if test="${empty LoginOK}">
						<span>&nbsp; | &nbsp;</span>
					</c:if> <c:if test="${empty LoginOK}">
						<a
							onclick="window.location.href = '${pageContext.request.contextPath}/createAccount/createAccount'"
							id="c" class="col-4 m-0 align-self-center text-right">註冊會員</a>
					</c:if>
				</span>
			</div>
		</div>
	</nav>
	<!-- Bottom ================================================================== -->
	<nav id="navbar-btm"
		class="navbar navbar-expand-lg navbar-light p-0 m-0">
		<div class="container-fluid m-0 p-0">
			<div
				class="row no-gutters justify-content-center align-middle navbar-btm-row">
				<a class="navbar-logo col-3 m-0 align-self-center" id="a"
					href="${pageContext.request.contextPath}/index"> <img id="a"
					src="${pageContext.request.contextPath}/index/img/nav/logo-w.svg">
				</a>

				<div class="btn-group col-6 m-0 p-0" role="group"
					aria-label="Button group with nested dropdown">
					<table class="nav-table">
						<tr>
							<td>
								<div class="btn-group dropdown" role="group">

									<button id="btnGroupDrop1" type="button"
										class="btn btn-success dropdown-toggle" data-toggle="dropdown"
										data-hover="dropdown" aria-haspopup="true"
										aria-expanded="false">健康飲食計劃餐</button>
									<div
										class="dropdown-menu plan-menu dropdown-menu-center hoverdrop"
										aria-labelledby="btnGroupDrop1">
										<div class="container pl-1">
											<div class="row">
												<div class="col-4">
													<div class="card">
														<img
															src="${pageContext.request.contextPath}/index/img/nav/bread.jpg"
															class="card-img-top" alt="...">
														<div class="card-img-overlay">
															<h5 class="card-title text-center">健康營養計畫</h5>
														</div>
														<div class="card-body">
															<p class="card-text">堅持使用新鮮食材，少油、低鈉，讓外食族的您也 能吃得健康。</p>
															<div class="text-right">
																<a
																	href="${pageContext.request.contextPath}/work-out-plane?plane=keep"
																	class="btn btn-success">現在就訂</a>
															</div>

														</div>
													</div>
												</div>
												<div class="col-4">
													<div class="card">
														<img
															src="${pageContext.request.contextPath}/index/img/nav/bowl.jpg"
															class="card-img-top" alt="...">
														<div class="card-img-overlay">
															<h5 class="card-title text-center">強身健體計畫</h5>
														</div>
														<div class="card-body">
															<p class="card-text">針對增肌族群，提供最豐富、優質的蛋白質，讓您 的一天充滿活力。</p>
															<div class="text-right">
																<a
																	href="${pageContext.request.contextPath}/work-out-plane?plane=muscle"
																	class="btn btn-success">現在就訂</a>
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
													<div class="card">
														<img
															src="${pageContext.request.contextPath}/index/img/nav/ella.jpg"
															class="card-img-top" alt="...">
														<div class="card-img-overlay">
															<h5 class="card-title text-center">輕盈窈窕計畫</h5>
														</div>
														<div class="card-body">
															<p class="card-text">減肥不用再餓肚子，精算熱量、營養調配，讓您吃 的健康，瘦的漂亮。</p>
															<div class="text-right">
																<a
																	href="${pageContext.request.contextPath}/work-out-plane?plane=fit"
																	class="btn btn-success">現在就訂</a>
															</div>

														</div>
													</div>
												</div>
												<div class="col-4">
													<div class="card coming-soon">

														<img
															src="${pageContext.request.contextPath}/img/nav/lily.jpg"
															class="card-img-top" alt="...">
														<div class="card-img-overlay">
															<h5 class="card-title text-center">晚餐隨選</h5>
														</div>
														<div class="card-body">
															<p class="card-text">
																可自由選擇，讓瞭解自己需求的人能夠選擇想要的晚餐。<span
																	style="color: red; font-size: 12px; letter-spacing: 0;">Coming
																	soon...</span>
															</p>
															<div class="text-right">
																<a href="" class="btn btn-secondary disabled"
																	aria-disabled="true">即將推出</a>
															</div>
														</div>
													</div>
												</div>
												<div class="col-4 cal">
													<div class="card text-white mb-3">
														<div class="card-body">
															<h4 class="m-2">我適合哪個計畫呢？</h4>
															<p class="cal-text m-2">利用我們的小工具，幫你搭配最適合你的餐點計畫</p>
															<div class="text-right cal-btn">
																<a href="#" class="btn btn-light" data-toggle="modal"
																	data-target="#exampleModalCenter">開始使用小工具</a>
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
										class="btn btn-success dropdown-toggle" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">食材選購</button>
									<div class="dropdown-menu hoverdrop"
										aria-labelledby="btnGroupDrop2" style="width: 600px;">
										<div style="color: #2eaa00;">快速分類</div>
										<img
											src="${pageContext.request.contextPath}/index/img/nav/Path233.svg"
											style="width: 300px;" alt="...">
										<div class="container grocery">
											<div class="row p-0">
												<div class="col-3 p-0 ">

													<li><a
														href="${pageContext.request.contextPath}/shopMaterial?category=vegetable">蔬菜</a></li>
													<li><a
														href="${pageContext.request.contextPath}/shopMaterial?category=fruit">水果</a></li>
													<li><a
														href="${pageContext.request.contextPath}/shopMaterial?category=brute">牛羊豬肉</a></li>
													<li><a
														href="${pageContext.request.contextPath}/shopMaterial?category=poultry">雞鴨鵝肉</a></li>
													<li><a
														href="${pageContext.request.contextPath}/shopMaterial?category=seafood">魚蝦海鮮</a></li>
													<li><a
														href="${pageContext.request.contextPath}/shopMaterial?category=rice">米麵雜糧</a></li>
													<br> <br>
													<li><a
														href="${pageContext.request.contextPath}/shopMaterial">
															<img
															src="${pageContext.request.contextPath}/index/img/nav/goshop.svg"
															style="width: 120px;">
													</a></li>

												</div>
												<div class="col-3 p-0">
													<li><a href="#">醃漬/加工品</a></li>
													<li><a
														href="${pageContext.request.contextPath}/shopMaterial?category=egg">蛋/蛋製品</a></li>
													<li><a href="#">奶類/奶製品</a></li>
													<li><a href="#">豆腐/豆製品</a></li>
													<li><a href="#">麵粉/烘焙用粉</a></li>
												</div>
												<div class="col-6 p-0 text-right">
													<img
														src="${pageContext.request.contextPath}/index/img/nav/asparagus.jpg"
														style="width: 235px;" alt="">

												</div>
											</div>
										</div>
									</div>

								</div>
							</td>
							<td>
								<button
									onclick="location.href='${pageContext.request.contextPath}/recipe_main'"
									type="button" class="btn btn-success">食譜社群</button>
							</td>
							<td>
								<button
									onclick="location.href='${pageContext.request.contextPath}/faqs'"
									type="button" class="btn btn-success">常見問答</button>
							</td>
						</tr>
					</table>
				</div>

				<span id="d"
					class="col-3 m-0 align-self-center text-right member dropdown">
					<c:if test="${ ! empty LoginOK }">
						<img class="img"
							style="width: 40px; height: 40px; border: 1px solid white; border-radius: 50%;"
							<c:choose>
							<c:when test='${! empty FB }'>src='${LoginOK.memberImage}'</c:when>
							<c:when test='${(empty FB) and (empty LoginOK.memberImage)}'>src='${pageContext.request.contextPath}/index/img/nav/logo-w.svg'</c:when>
							<c:otherwise> src='${pageContext.request.contextPath}/MemberImage/${LoginOK.pkey}.jpg' </c:otherwise>  
							</c:choose>>
					</c:if> <span id="notification"></span> <span id="cart"></span> <c:if
						test="${empty LoginOK}">
						<a
							onclick="window.location.href = '${pageContext.request.contextPath}/login/login'"
							id="c" class="col-4 m-0 align-self-center text-right"> 會員登入 </a>
					</c:if> <c:if test="${ ! empty LoginOK }">
						<span class="col-4 m-0 align-self-center text-right"
							id="dropdownMenuButton" data-toggle="dropdown"
							data-hover="dropdown" aria-haspopup="true" aria-expanded="false">
							${LoginOK.name} </span>
						<div class="dropdown-menu p-1 hoverdrop text-danger"
							aria-labelledby="dropdownMenuButton"
							style="right: -40px; left: unset; min-width: unset;">
							<a class="dropdown-item text-black-50"
								href="${pageContext.request.contextPath}/my_page?ownerId=${LoginOK.pkey}&page=1&year=2019">我的食譜</a> <a
								class="dropdown-item text-black-50"
								href="${pageContext.request.contextPath}/memberPage/memberPage">管理帳號</a>
							<a class="dropdown-item text-black-50"
								href="${pageContext.request.contextPath}/orders">訂單管理</a> <a
								class="dropdown-item text-black-50"
								href="${pageContext.request.contextPath}/groupBuying">發起團購</a> <a
								class="dropdown-item text-black-50"
								href="${pageContext.request.contextPath}/cartList">購物車</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item text-black-50"
								href="${pageContext.request.contextPath}/login/logout">登出</a>
						</div>

					</c:if> <c:if test="${empty LoginOK}">
						<span>&nbsp; | &nbsp;</span>
					</c:if> <c:if test="${empty LoginOK}">
						<a
							onclick="window.location.href = '${pageContext.request.contextPath}/createAccount/createAccount'"
							id="c" class="col-4 m-0 align-self-center text-right">註冊會員</a>
					</c:if> <!-- <img src="img/logged-in-all.svg" style="width: 280px;"> -->
				</span>
			</div>
		</div>
	</nav>
</div>
<!-- RWD small Navbar ===================================================== -->

<div class="header-two">
	<nav id="nav-small"
		class="navbar navbar-expand-lg navbar-dark bg-light">
		<a class="navbar-brand" href="#"><img
			src="${pageContext.request.contextPath}/index/img/nav/logo-w.svg"
			alt=""></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown1"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 健康營養計畫餐 </a>
					<div class="dropdown-menu dropdown-menu-rwd"
						aria-labelledby="navbarDropdown1">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/work-out-plane?plane=keep">健康營養計畫</a>
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/work-out-plane?plane=muscle">強身健體計畫</a>
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/work-out-plane?plane=fit">輕盈窈窕計畫</a>
						<a class="dropdown-item" href="#">晚餐隨選</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">我適合哪個計畫呢？<br>利用我們的小工具，幫你搭配最適合你的餐點計畫
						</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown2"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> 食材選購 </a>
					<div class="dropdown-menu dropdown-menu-rwd"
						aria-labelledby="navbarDropdown2">
						<a class="dropdown-item" href="#">蔬菜</a> <a class="dropdown-item"
							href="#">水果</a> <a class="dropdown-item" href="#">牛羊豬肉</a> <a
							class="dropdown-item" href="#">雞鴨鵝肉</a> <a class="dropdown-item"
							href="#">魚蝦海鮮</a> <a class="dropdown-item" href="#">米麵雜糧</a> <a
							class="dropdown-item" href="#">醃漬/加工品</a> <a
							class="dropdown-item" href="#">豆腐/豆製品</a> <a
							class="dropdown-item" href="#">蛋/蛋製品</a> <a class="dropdown-item"
							href="#">麵粉/烘焙用粉</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">進入商城選購</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#">食譜社群</a></li>
				<li class="nav-item"><a class="nav-link" href="#">常見問答</a></li>


			</ul>

		</div>


	</nav>
</div>






