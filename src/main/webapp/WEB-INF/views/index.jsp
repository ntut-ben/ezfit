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
<title>EZfit Eat 健康餐點 ＋ 食譜社群平台</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="index/css/nav.css" />
<link rel="stylesheet" href="index/css/index.css" />
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script src="index/js/index.js"></script>
</head>

<body>
	<jsp:include page="header.jsp" />
	<!-- Body  ================================================================ -->
	<!-- intro ================================================================ -->
	<div class="index-body">
		<div class="intro text-center">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xl-3 col-sm-0"></div>
					<div class="col-xl-6 col-sm-12">
						<div class="intro-text-bg">
							<div class="intro-text">
								<h2>聰明選擇 • 健康生活</h2>
								<p>方便的減脂期、增肌期或減肥餐點，健身族群事半功倍的飲食好夥伴重視食品安全、無化學保鮮劑的冷凍健身餐全台配送到府健康效率補充運動能量。對應不同體型及運動量聰明選擇三餐，美味又健康！
								</p>
							</div>
						</div>
					</div>
					<div class="col-xl-3 col-sm-0"></div>
				</div>
			</div>
		</div>
		<!--  delivery ============================================================ -->
		<div class="container-fluid delivery-bg-top">
			<div class="row">
				<!-- <div class="col-xl-1 col-lg-0 rte-green"></div> -->
				<div class="col-xl-6 col-lg-6 rte-green-text text-center">
					<div id="carouselExampleSlidesOnly" class="carousel slide"
						data-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active">
								<p>
									<img src="index/img/index/check.svg" alt="">嚴選優質食材
								</p>
							</div>
							<div class="carousel-item">
								<p>
									<img src="index/img/index/check.svg" alt="">使用100%初榨橄欖油
								</p>
							</div>
							<div class="carousel-item">
								<p>
									<img src="index/img/index/check.svg" alt="">少油、低鈉、低熱量
								</p>
							</div>
						</div>
					</div>

				</div>
				<div class="col-xl-6 col-lg-6 rte-white-text">
					<h4>外食總是太油、太鹹？</h4>
					<h4>體態不夠好嗎？</h4>
					<p>我們打造了這個健康、美味又方便的法式餐盒EZfit Eat
						秉持著法式烹調的講究，展現食物的原味，嚴選優質食材不添加防腐劑與味精每天推出不同選擇，並提供宅配服務到全台灣的每個角落，給想維持健康體態的你！
					</p>
				</div>
				<!-- <div class="col-xl-1 col-lg-0 rte-white"></div> -->
			</div>
		</div>
		<div class="container-fluid delivery-bg-btm">
			<div class="row">

				<div
					class="col-xl-6 col-lg-6 col-md-12 col-sm-12 rte-green-light-text text-center m-auto">
					<div class="delivery">
						<img src="index/img/index/delivery.svg" alt="">
					</div>
				</div>
				<div class="col-xl-6 col-lg-6 col-md-12 col-sm-12 deliverguy">
					<img src="index/img/index/deliver.jpg" alt="">
				</div>

			</div>
		</div>

		<!-- example ============================================================== -->
		<div class="example">
			<h2>健康飲食計畫參考範例</h2>
			<p>
				<br> <b>強身健體計畫，7日 - 早餐 ＋ 中餐 ＋ 晚餐計畫</b>
			</p>

			<div class="example-img">
				<p>早餐 Breakfast</p>
				<div>
					<img src="index/img/index/breakfast.jpg" alt="">
				</div>
				<p>午餐 Lunch</p>
				<div>
					<img src="index/img/index/lunch.jpg" alt="">
				</div>
				<p>晚餐 Dinner</p>
				<div>
					<img src="index/img/index/dinner.jpg" alt="">
				</div>
			</div>
			<div class="bigger-pic text-right">
				<a href="#">點此看大圖</a>
			</div>
		</div>

		<!-- Groceries ============================================================ -->
		<div class="groceries-card">
			<div class="groceries">
				<div class="groceries-text text-center">
					<h1>給喜愛下廚的你</h1>
					<hr>
					<p>從餐桌到產地的平台，連結消費者與生產者的需求，因為我們彼此都需要更「安心」、更「友善」、更「直接」的消費模式除了健康方便的飲食計畫餐之外，我們也與當地農場合作，提供最新鮮最優質的食材宅配到府，讓您只管享受烹飪的樂趣，省去上菜市場購買食材的麻煩。
					</p>
				</div>
			</div>
			<div class="text-center m-3" style="color: rgb(0, 122, 0);">
				<h3>本日促銷</h3>

			</div>

			<div class="card-deck">
				<div class="card grocery-card">
					<img
						src="index/img/index/groceries/blurred-background-close-up-delicious-1395004.jpg"
						class="card-img-top" alt="...">
					<div class="card-img-overlay grocery-card-text">
						<h4>栗南瓜</h4>
						<p class="card-text">500公克±10% 產地 : 花蓮</p>
						<h4 class="card-text">NT$ 60</h4>
					</div>
				</div>
				<div class="card grocery-card">
					<img
						src="index/img/index/groceries/boletus-champignon-delicious-53494.jpg"
						class="card-img-top" alt="...">
					<div class="card-img-overlay grocery-card-text">
						<h4>鴻禧菇</h4>
						<p class="card-text">100公克±10% 產地：台灣</p>
						<h4 class="card-text">NT$ 40</h4>
					</div>
				</div>
				<div class="card grocery-card">
					<img
						src="index/img/index/groceries/bowl-bunch-close-up-2325843.jpg"
						class="card-img-top" alt="...">
					<div class="card-img-overlay grocery-card-text">
						<h4>寶貝波菜</h4>
						<p class="card-text">250公克±10% 產地：台灣</p>
						<h4 class="card-text">NT$ 37</h4>
					</div>
				</div>
				<div class="card grocery-card">
					<img
						src="index/img/index/groceries/broccoli-close-up-color-399629.jpg"
						class="card-img-top" alt="...">
					<div class="card-img-overlay grocery-card-text">
						<h4>青花菜</h4>
						<p class="card-text">350公克±10% 產地：台灣</p>
						<h4 class="card-text">NT$ 80</h4>
					</div>
				</div>
			</div>
			<div class="card-deck">
				<div class="card grocery-card">
					<img
						src="index/img/index/groceries/carrots-close-up-farmers-market-143133.jpg"
						class="card-img-top" alt="...">
					<div class="card-img-overlay grocery-card-text">
						<h4>紅蘿波</h4>
						<p class="card-text">400公克±10% 產地 : 花蓮</p>
						<h4 class="card-text">NT$ 22</h4>
					</div>
				</div>
				<div class="card grocery-card">
					<img src="index/img/index/groceries/chili-peper-food-red-63596.jpg"
						class="card-img-top" alt="...">
					<div class="card-img-overlay grocery-card-text">
						<h4>紅辣椒</h4>
						<p class="card-text">100公克±10% 產地：台灣</p>
						<h4 class="card-text">NT$ 30</h4>
					</div>
				</div>
				<div class="card grocery-card">
					<img
						src="index/img/index/groceries/chopping-board-food-fresh-259648.jpg"
						class="card-img-top" alt="...">
					<div class="card-img-overlay grocery-card-text">
						<h4>蘑菇</h4>
						<p class="card-text">250公克±10% 產地：台灣</p>
						<h4 class="card-text">NT$ 58</h4>
					</div>
				</div>
				<div class="card grocery-card">
					<img
						src="index/img/index/groceries/close-up-harvest-potatoes-162673.jpg"
						class="card-img-top" alt="...">
					<div class="card-img-overlay grocery-card-text">
						<h4>馬鈴薯</h4>
						<p class="card-text">350公克±10% 產地：台灣</p>
						<h4 class="card-text">NT$ 50</h4>
					</div>
				</div>
			</div>
			<div class="grocery-slid">
				<div id="carouselExampleSlidesOnly" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img
								src="index/img/index/groceries/blurred-background-close-up-delicious-1395004.jpg"
								class="d-block w-100" alt="...">
							<div class="carousel-caption ">
								<h4>栗南瓜</h4>
								<p class="card-text">500公克±10% 產地 : 花蓮</p>
								<h4 class="card-text">NT$ 60</h4>
							</div>
						</div>
						<div class="carousel-item">
							<img
								src="index/img/index/groceries/boletus-champignon-delicious-53494.jpg"
								class="d-block w-100" alt="...">
							<div class="carousel-caption">
								<h4>鴻禧菇</h4>
								<p class="card-text">100公克±10% 產地：台灣</p>
								<h4 class="card-text">NT$ 40</h4>
							</div>
						</div>
						<div class="carousel-item">
							<img
								src="index/img/index/groceries/bowl-bunch-close-up-2325843.jpg"
								class="d-block w-100" alt="...">
							<div class="carousel-caption">
								<h4>寶貝波菜</h4>
								<p class="card-text">250公克±10% 產地：台灣</p>
								<h4 class="card-text">NT$ 37</h4>
							</div>
						</div>
						<div class="carousel-item">
							<img
								src="index/img/index/groceries/broccoli-close-up-color-399629.jpg"
								class="d-block w-100" alt="...">
							<div class="carousel-caption">
								<h4>青花菜</h4>
								<p class="card-text">350公克±10% 產地：台灣</p>
								<h4 class="card-text">NT$ 80</h4>
							</div>
						</div>
						<div class="carousel-item">
							<img
								src="index/img/index/groceries/carrots-close-up-farmers-market-143133.jpg"
								class="d-block w-100" alt="...">
							<div class="carousel-caption">
								<h4>紅蘿波</h4>
								<p class="card-text">400公克±10% 產地 : 花蓮</p>
								<h4 class="card-text">NT$ 22</h4>
							</div>
						</div>
						<div class="carousel-item">
							<img
								src="index/img/index/groceries/chili-peper-food-red-63596.jpg"
								class="d-block w-100" alt="...">
							<div class="carousel-caption">
								<h4>紅辣椒</h4>
								<p class="card-text">100公克±10% 產地：台灣</p>
								<h4 class="card-text">NT$ 30</h4>
							</div>
						</div>
						<div class="carousel-item">
							<img
								src="index/img/index/groceries/close-up-harvest-potatoes-162673.jpg"
								class="d-block w-100" alt="...">
							<div class="carousel-caption">
								<h4>蘑菇</h4>
								<p class="card-text">250公克±10% 產地：台灣</p>
								<h4 class="card-text">NT$ 58</h4>
							</div>
						</div>
						<div class="carousel-item">
							<img
								src="index/img/index/groceries/chopping-board-food-fresh-259648.jpg"
								class="d-block w-100" alt="...">
							<div class="carousel-caption">
								<h4>馬鈴薯</h4>
								<p class="card-text">350公克±10% 產地：台灣</p>
								<h4 class="card-text">NT$ 50</h4>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="text-center p-3">
				<button type="button" class="btn btn-outline-success">進入商城選購</button>
			</div>

		</div>

		<!-- Recipe ================================================================ -->
		<div class="recipe">
			<div class="container-fluid">
				<div class="row">
					<div class="col-xl-6 col-sm-12">
						<div class="intro-text-bg">
							<div class="intro-text">
								<h2>分享你的拿手料理</h2>
								<h4>你也可以成為一個料理網紅！</h4>
								<p>尋找平台記錄自己的私房料理嗎？這邊有愛好下廚、做菜的網友們分享超過 60000
									篇食譜任您免費瀏覽和收藏食譜，還可料理直播！歡迎加入這個的廚藝社群，一起天天享受烹飪。</p>
								<br>
								<h4>食材打包功能</h4>
								<p>看到喜歡的食譜再也不用東奔西跑搜集所需材料了！貼心的食材打包功能，將食譜裡所需要的食材直接新鮮宅配到府。</p>
								<div class="text-center">
									<button type="button" class="btn btn-outline-success">進入食譜社群</button>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xl-6 col-sm-0"></div>
				</div>
			</div>
		</div>

	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">計算小工具</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<div class="form-group row mb-2">
							<label for="inputSex" class="col-sm-2 col-form-label">性別</label>
							<div class="col-8">
								<select class="custom-select mr-sm-2" id="gender">
									<option selected="selected">性別</option>
									<option value="boy">男</option>
									<option value="girl">女</option>
								</select>
							</div>
						</div>

						<div class="form-group row mb-2">
							<label for="inputHeight" class="col-sm-2 col-form-label text-sm">身高</label>
							<div class="input-group col-8">
								<input type="text" class="form-control" id="height"
									aria-describedby="basic-addon2" name="memberHeight" />
								<div class="input-group-append">
									<span class="input-group-text" id="basic-addon2">公分</span>
								</div>
							</div>
						</div>

						<div class="form-group row mb-2">
							<label for="input" class="col-sm-2 col-form-label">體重</label>
							<div class="input-group col-8">
								<input type="text" class="form-control" id="weight"
									aria-describedby="basic-addon2" name="memberWeight" />
								<div class="input-group-append">
									<span class="input-group-text" id="basic-addon2">公斤</span>
								</div>
							</div>
						</div>
						<div class="form-group row mb-2">
							<label for="inputName" class="col-sm-2 col-form-label">年齡</label>
							<div class="col-8">
								<input type="text" class="form-control" name="memberName"
									id="age" />
							</div>
						</div>
						<div class="form-group row mb-2">
							<label for="inputSex" class="col-sm-2 col-form-label">日常活動等級</label>
							<div class="col-8">
								<select class="custom-select mr-sm-2" id="exercise">
									<option selected="selected">請選擇</option>
									<option value="1.2">久坐 (辦公室工作,沒什麼運動)</option>
									<option value="1.375">輕量活動 (每週輕鬆運動3~5天)</option>
									<option value="1.55">中度活動 (每週中等強度運動3~5天)</option>
									<option value="1.725">高度活動 (每週高等強度運動6~7天)</option>
									<option value="1.9">非常高度度活動 (勞力密集工作或每天訓練)</option>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-success"
							onclick='calculate()'>開始計算</button>
					</div>
					<div id="result"></div>
				</div>
			</div>
		</div>
	</div>
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
						<img src="index/img/nav/logo-all-white.svg">
					</div>
					<div class="social">
						<a href="#"><img src="index/img/nav/facebook.svg"></a> <a
							href="#"><img src="index/img/nav/twitter.svg"></a> <a
							href="#"><img src="index/img/nav/instagram.svg"></a> <a
							href="#"><img src="index/img/nav/line.svg"></a>
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
						<li><img src="index/img/nav/tlogo.png" style="width: 70px;">
							<img src="index/img/nav/w-logo.png" style="width: 70px;"> <img
							src="index/img/nav/logonew-1.png" style="width: 70px;"></li>
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
			<img src="index/img/nav/logo-visa.svg" alt=""> <img
				src="index/img/nav/logo-master.svg" alt=""> <img
				src="index/img/nav/logo-jcb.svg" alt=""> <img
				src="index/img/nav/logo-amex.svg" alt="">
		</div>
		<div class="copyright text-center">Copyright ©2019 EZfit Eat
			版權所有 All Rights Reserved</div>
	</footer>





	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
	<script src="index/js/nav.js"></script>


</body>
</html>