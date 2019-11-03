<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" /> -->


<c:set var='debug' value='true' scope='application' />


<!-- Footer  ============================================================== -->
<footer>
	<div class="search-text">
		<div class="container">
			<div class="row justify-content-center text-center">
				<div class="form">
					<form id="search-form" class="form-search form-horizontal">
						<input type="text" class="input-search"
							placeholder="輸入 email 獲得更多優惠資訊">
						<button type="submit" class="btn-search">訂閱</button>
					</form>
				</div>

			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">

			<div class="col-md-4 col-sm-12 text-center footer-margin">
				<div class="logo">
					<img
						src="${pageContext.request.contextPath}/index/img/nav/logo-all-white.svg">
				</div>
				<div class="social">
					<a href="#"><img
						src="${pageContext.request.contextPath}/index/img/nav/facebook.svg"></a>
					<a href="#"><img
						src="${pageContext.request.contextPath}/index/img/nav/twitter.svg"></a>
					<a href="#"><img
						src="${pageContext.request.contextPath}/index/img/nav/instagram.svg"></a>
					<a href="#"><img
						src="${pageContext.request.contextPath}/index/img/nav/line.svg"></a>
				</div>
			</div>

			<div class="col-md-4 col-sm-12 text-center footer-margin">
				<ul class="menu">
					<span>關於EZfit Eat</span>
					<li><a href="#">公司資訊</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="#">服務條款</a></li>

					<li><a href="#">徵才訊息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="#">服務條款</a></li>

					<li><a href="#">廣告合作</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="#">隱私政策</a></li>
				</ul>
			</div>

			<div class="col-md-4 col-sm-12 text-center footer-margin">
				<ul class="address">
					<span>合作夥伴</span>
					<li><img
						src="${pageContext.request.contextPath}/index/img/nav/tlogo.png"
						style="width: 70px;"> <img
						src="${pageContext.request.contextPath}/index/img/nav/w-logo.png"
						style="width: 70px;"> <img
						src="${pageContext.request.contextPath}/index/img/nav/logonew-1.png"
						style="width: 70px;"></li>
					<li>
						<!-- <i class="fa fa-map-marker" aria-hidden="true"></i> --> <a
						href="#">室友是友 4U4U</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#">健客
							Fititude</a>
					</li>

				</ul>
			</div>
		</div>
	</div>
	<div class="paymenttype text-center footer-margin">
		<img
			src="${pageContext.request.contextPath}/index/img/nav/logo-visa.svg"
			alt=""> <img
			src="${pageContext.request.contextPath}/index/img/nav/logo-master.svg"
			alt=""> <img
			src="${pageContext.request.contextPath}/index/img/nav/logo-jcb.svg"
			alt=""> <img
			src="${pageContext.request.contextPath}/index/img/nav/logo-amex.svg"
			alt="">
	</div>
	<div class="copyright text-center">Copyright ©2019 EZfit Eat 版權所有
		All Rights Reserved</div>
</footer>

