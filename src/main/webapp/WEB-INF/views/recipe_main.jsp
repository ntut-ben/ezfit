<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nav.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/recipe_main.css" />

</head>

<body>

	<!-- Navigation Bar ========================================================================== -->

	<jsp:include page="header.jsp"></jsp:include>

	<!-- ========================================================================= -->

	<div class="recipe_A">
		<div class="recipe-search container">
			<div class="row p-0 m-0">
				<div class="col-6 p-0">
					<form class="form-inline"
						action="http://localhost:8080/ezfit/keyword/submit.do">
						<input id="searchRecipe" name="searchRecipe"
							class="form-control recipe-search-input" type="search"
							placeholder="請輸入關鍵字搜尋···" aria-label="Search">
						<button class="btn btn-success btn-search recipe-search-btn"
							type="button" id="searchButom">
							<img src="img/recipe_main/icon-search.svg"
								style="margin: 0 6px 5px 0;">搜尋
						</button>
					</form>
				</div>
				<div class="col-6 write-heart-btn text-right p-0">
					<button type="button" class="btn btn-success" id="writeRecipe">
						<img src="img/recipe_main/icon-write.svg">寫食譜
					</button>
					<button type="button" class="btn btn-success" id="mySaveBtn">
						<img src="img/recipe_main/icon-heart.svg">食譜收藏
					</button>
				</div>
			</div>
		</div>

		<div class="container recipe-body-bg">
			<div class="row m-0 p-0">
				<div class="col-9 p-0">
					<div id="carouselExampleIndicators" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleIndicators" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner">
							<div class="carousel-item active">
								<a href="#"><img src="https://picsum.photos/854/422"
									class="d-block w-100 editor-choice-img" alt="...">

									<div class="carousel-caption">
										<div class="mb-1">
											<span class="badge badge-pill badge-success">編輯精選</span>
										</div>
										<h5>立了參生市卻</h5>
										<p>天記勢次運</p>
									</div> </a>

							</div>
							<div class="carousel-item">
								<a href="#"><img src="https://picsum.photos/852/422"
									class="d-block w-100 editor-choice-img" alt="...">
									<div class="carousel-caption">
										<p>
											<span class="badge badge-pill badge-danger">週末時光</span>
										</p>
										<h5>明他一歷然</h5>
										<p>好止點備動良性賣</p>
									</div> </a>
							</div>
							<div class="carousel-item">
								<a href="#"><img src="https://picsum.photos/850/420"
									class="d-block w-100 editor-choice-img" alt="...">
									<div class="carousel-caption">
										<p>
											<span class="badge badge-pill badge-warning">網友最愛</span>
										</p>
										<h5>列員功第年學</h5>
										<p>術的了大投有到日成巴速</p>
									</div> </a>
							</div>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleIndicators"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next"
							href="#carouselExampleIndicators" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>

					</div>

					<div class="titles my-3">
						<span>熱門食譜</span>
					</div>


					<div class="row pop-recipes p-0 m-0">

						<div class="col-6 p-0 pr-2">
							<div class="card m-0" id='rp1'>
								<a href="" id="linkToRecipe1" class="link"><img
									src="https://picsum.photos/410/255"
									class="card-img-top pop-recipes-img recipePic" alt="..."></a>
								<div class="card-body">
									<a href="" id="linkToRecipe2" class="link">
										<div id="topOneRecipe">
											<h5 class="card-title"></h5>
											<p class="card-text"></p>
											<p></p>
											<p class="card-text mb-2"></p>
										</div>
									</a>
									<div class="likes-comments" id='likesComments1'>
										<a href="#" id="linkToRecipe3" class="link"><img
											src="img/recipe_main/icon-like.svg" alt="">&nbsp; <span></span></a>
										&nbsp;&nbsp; <a href="#" id="linkToRecipe4" class="link"><img
											src="img/recipe_main/icon-comment.svg" alt="">&nbsp; <span></span></a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-6 ">
							<div class="row p-0">
								<div class="col-12 p-0 pop-recipes-right-bg mb-3" id='rp2'>
									<div class="pop-recipes-right img-cover">
										<a href="#" class="link"><img
											src="http://placekitten.com/150/150" alt="" class="recipePic"></a>
									</div>
									<div class="pop-recipes-right-text">
										<a href="#" class="link">
											<div id="topTwoRecipe">
												<h5 id='recipe2name'></h5>
												<p id='recipe2owner'></p>
												<p id='recipe2Intro'></p>
											</div>
										</a>
										<div class="likes-comments" id="likesComments2">
											<a href="#" class="link"><img
												src="img/recipe_main/icon-like.svg" alt="">&nbsp; <span></span></a>
											&nbsp;&nbsp; <a href="#" class="link"><img
												src="img/recipe_main/icon-comment.svg" alt="">&nbsp; <span></span></a>
										</div>
									</div>
								</div>

								<div class="col-12 p-0 pop-recipes-right-bg mb-3" id='rp3'>
									<div class="pop-recipes-right img-cover">
										<a href="#" class="link"><img
											src="https://picsum.photos/151/151" class="recipePic" alt=""></a>
									</div>
									<div class="pop-recipes-right-text">
										<a href="#" class="link">
											<div id="topThreeRecipe">
												<h5></h5>
												<p></p>
												<p></p>
											</div>
										</a>
										<div class="likes-comments" id="likesComments3">
											<a href="#" class="link"><img
												src="img/recipe_main/icon-like.svg" alt="">&nbsp; <span></span></a>
											&nbsp;&nbsp; <a href="#" class="link"><img
												src="img/recipe_main/icon-comment.svg" alt="">&nbsp; <span></span></a>
										</div>
									</div>
								</div>

								<div class="col-12 p-0 pop-recipes-right-bg mb-3" id='rp4'>
									<div class="pop-recipes-right img-cover">
										<a class="link"> <img src="https://picsum.photos/152/152"
											class="recipePic" alt="">
										</a>
									</div>
									<div class="pop-recipes-right-text">
										<a href="#" class="link">
											<div id="top4Recipe">
												<h5></h5>
												<p></p>
												<p></p>
											</div>
										</a>
										<div class="likes-comments" id="likesComments4">
											<a href="#" class="link"><img
												src="img/recipe_main/icon-like.svg" alt="">&nbsp; <span>123</span></a>
											&nbsp;&nbsp; <a href="#" class="link"><img
												src="img/recipe_main/icon-comment.svg" alt="">&nbsp; <span>456</span></a>
										</div>
									</div>
								</div>

							</div>



						</div>
					</div>
					<div class="more text-right">
						<a href="#">更多內容 &#187;</a>
					</div>

					<div class="titles my-3">
						<span>本週新鮮上菜</span>
					</div>

					<div class="row new-recipes m-0" id="weekRecipeParent">

						<!-- <div class="col-3 m-0 p-2 new-recipe-item">
                            <div class="new-recipes-img">
                                <a href=""><img src="http://placekitten.com/186/132" alt="..."></a>
                            </div>
                            <div class="new-recipe-item-text">
                                <h5><a href="#">日式鮪魚菇炊飯</a></h5>
                                <p><a href="#">小潔的廚房記事</a></p>
                            </div>
                        </div> -->

						<!-- <div class="col-3 m-0 p-2 new-recipe-item">
                            <div class="new-recipes-img">
                                <a href=""><img src="https://picsum.photos/187/133" alt="..."></a>
                            </div>
                            <div class="new-recipe-item-text">
                                <h5><a href="#">日式鮪魚菇炊飯</a></h5>
                                <p><a href="#">小潔的廚房記事</a></p>
                            </div>
                        </div> -->

						<!-- <div class="col-3 m-0 p-2 new-recipe-item">
                            <div class="new-recipes-img ">
                                <a href=""><img src="https://picsum.photos/188/134" alt="..."></a>
                            </div>
                            <div class="new-recipe-item-text">
                                <h5><a href="#">日式鮪魚菇炊飯</a></h5>
                                <p><a href="#">小潔的廚房記事</a></p>
                            </div>
                        </div> -->

						<!-- <div class="col-3 m-0 p-2 new-recipe-item">
                            <div class="new-recipes-img">
                                <a href=""><img src="http://placekitten.com/189/135" alt="..."></a>
                            </div>
                            <div class="new-recipe-item-text">
                                <h5><a href="#">日式鮪魚菇炊飯</a></h5>
                                <p><a href="#">小潔的廚房記事</a></p>
                            </div>
                        </div> -->
					</div>

					<div class="more text-right">
						<a href="#">更多內容 &#187;</a>
					</div>

					<div class="titles my-3">
						<span>異國料理週</span>
					</div>

					<div class="row new-recipes m-0">

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="http://placeimg.com/190/136/any"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="https://picsum.photos/191/137"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img ">
								<a href=""><img src="https://picsum.photos/192/138"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="http://placekitten.com/193/139"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="https://loremflickr.com/194/140"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="https://picsum.photos/195/141"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img ">
								<a href=""><img src="https://picsum.photos/196/142"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="https://loremflickr.com/197/143"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

					</div>

					<div class="more text-right">
						<a href="#">更多內容 &#187;</a>
					</div>

					<div class="titles my-3">
						<span>暖心聖誕大餐 <img src="img/recipe_main/xmas-tree.svg"
							style="width: 23px;" alt=""></span>
					</div>

					<div class="row new-recipes m-0">

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="http://placeimg.com/205/151/any"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="https://picsum.photos/204/150"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img ">
								<a href=""><img src="https://picsum.photos/203/149"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="http://placekitten.com/202/148"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="https://loremflickr.com/201/147"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="https://picsum.photos/200/146"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img ">
								<a href=""><img src="https://picsum.photos/199/145"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

						<div class="col-3 m-0 p-2 new-recipe-item">
							<div class="new-recipes-img">
								<a href=""><img src="https://loremflickr.com/198/144"
									alt="..."></a>
							</div>
							<div class="new-recipe-item-text">
								<h5>
									<a href="#">日式鮪魚菇炊飯</a>
								</h5>
								<p>
									<a href="#">小潔的廚房記事</a>
								</p>
							</div>
						</div>

					</div>

					<div class="more text-right">
						<a href="#">更多內容 &#187;</a>
					</div>

					<!-- <div class="titles my-3">
                        <span>食譜分類</span>
                    </div> -->

					<div class="category my-5">
						<div class="category-title">食譜分類</div>
						<table class="mt-3 mb-2">
							<tr class="category-title-s">
								<td colspan="2">異國料理</td>
								<td colspan="2">家常食材</td>
								<td colspan="2">季節節慶</td>
								<td colspan="2">烘焙點心</td>
							</tr>

							<tr>
								<td><a href="#">韓式</a></td>
								<td><a href="#">義式</a></td>
								<td><a href="#">牛肉</a></td>
								<td><a href="#">海鮮</a></td>
								<td><a href="#">端午包粽</a></td>
								<td><a href="#">年菜</a></td>
								<td><a href="#">鬆餅</a></td>
								<td><a href="#">手作麵包</a></td>
							</tr>

							<tr>
								<td><a href="#">日式</a></td>
								<td><a href="#">港式</a></td>
								<td><a href="#">雞肉</a></td>
								<td><a href="#">菇類</a></td>
								<td><a href="#">元宵湯圓</a></td>
								<td><a href="#">中秋</a></td>
								<td><a href="#">巧克力</a></td>
								<td><a href="#">派塔</a></td>
							</tr>

							<tr>
								<td><a href="#">泰式</a></td>
								<td></td>
								<td><a href="#">豬肉</a></td>
								<td><a href="#">蔬菜</a></td>
								<td><a href="#">聖誕大餐</a></td>
								<td></td>
								<td><a href="#">中式點心</a></td>
								<td><a href="#">日式點心</a></td>
							</tr>

							<tr>
								<td><a href="#">台灣小吃</a></td>
								<td></td>
								<td><a href="#">豆類</a></td>
								<td><a href="#">五穀</a></td>
								<td><a href="#">萬聖節</a></td>
								<td></td>
								<td><a href="#">蛋糕</a></td>
								<td></td>
							</tr>

							<tr>
								<td><a href="#">西班牙</a></td>
								<td></td>
								<td><a href="#">水果</a></td>
								<td><a href="#">蛋類</a></td>
								<td><a href="#">情人節</a></td>
								<td></td>
								<td><a href="#">餅乾</a></td>
								<td></td>
							</tr>

						</table>

					</div>




				</div>


				<!-- Right Side ========================================================== -->


				<div class="col-3 p-0 pl-3 pop-keywords">
					<div class="card border-success mb-3" style="max-width: 18rem;">
						<div class="card-header">熱門關鍵字</div>
						<div class="card-body text-success" id="hotSearch">
							<!-- <a href="#" class="badge badge-success">義大利麵</a>
                            <a href="#" class="badge badge-success">馬鈴薯</a>
                            <a href="#" class="badge badge-success">茶碗蒸</a>
                            <a href="#" class="badge badge-success">玉米濃湯</a>
                            <a href="#" class="badge badge-success">豆腐</a>
                            <a href="#" class="badge badge-success">家常菜</a>
                            <a href="#" class="badge badge-success">餅乾</a>
                            <a href="#" class="badge badge-success">茶碗蒸</a>
                            <a href="#" class="badge badge-success">家常菜</a> -->
						</div>
					</div>
					<div class="ads">
						<div>
							<a href="#"><img
								src="img/recipe_main/ads/gif-banner-ad-300x250-2.gif" alt=""></a>
						</div>
						<div>
							<a href="#"><img
								src="img/recipe_main/ads/8814b257c399f4fc8224e3ce0d684d4a.gif"
								alt=""></a>
						</div>
						<div>
							<a href="#"><img
								src="img/recipe_main/ads/8c38bf32521107.56888ecb516df.gif"
								alt=""></a>
						</div>
						<div>
							<a href="#"><img src="img/recipe_main/ads/neiman_marcus.gif"
								alt=""></a>
						</div>

					</div>



				</div>




			</div>
		</div>


		<!-- Footer ======================================================================= -->
		<jsp:include page="footer.jsp"></jsp:include>




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
		<script src="${pageContext.request.contextPath}/js/nav.js"></script>
		<script src="${pageContext.request.contextPath}/js/recipe_main.js"></script>
		<script src="${pageContext.request.contextPath}/js/search.js"></script>
		<script src="${pageContext.request.contextPath}/js/hotRecipe.js"></script>
		<script
			src="${pageContext.request.contextPath}/js/linkToMySaveRecipe.js"></script>
</body>

</html>