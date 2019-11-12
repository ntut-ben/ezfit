<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>EZfit Eat - 我的食譜</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/nav.css" />
<link rel="stylesheet" href="css/my_page.css" />

</head>

<body>

	<!-- Navigation Bar ========================================================================== -->
	<!-- Top ==================== -->
	<jsp:include page="header.jsp"></jsp:include>

	<!-- 搜尋 ========================================================================= -->


	<div class="recipe-search container">
		<div class="row p-0 m-0">
			<div class="col-6 p-0">
				 <form class="form-inline">
                    <input id="searchRecipe" class="form-control recipe-search-input" type="search" placeholder="請輸入關鍵字搜尋···"
                        aria-label="Search">
                    <button class="btn btn-success btn-search recipe-search-btn" type="button" id="searchButom">
                        <img src="img/search_bar/icon-search.svg" style="margin:0 6px 5px 0;">搜尋</button>
                </form>
			</div>
			 <div class="col-6 write-heart-btn text-right p-0" id="writeRecipe">
                <button type="button" class="btn btn-success" id="searchButom"><img src="img/search_bar/icon-write.svg" >寫食譜</button>
                <button type="button" class="btn btn-success" id="mySaveBtn"><img src="img/search_bar/icon-heart.svg">食譜收藏</button>
            </div>
		</div>
	</div>
	<!--  =================================================== -->
	<div class="my-page-main">
		<div class="container my-page mb-4">
			<div class="row user-header p-0 m-0">
				<div class="col-12 cover-avatar-row p-0 m-0">
					<div class="profile-cover">
						<img id="profileCover" src="https://loremflickr.com/1200/400/dessert" alt="">
					</div>
					<div class="avatar">
						<img src="http://placekitten.com/200/200" id='headPhoto'  >
					</div>
					<div class="name-description-row">
						<div class="name-settings-row">
							<div class="name-box">
								<a href="#" id="memberName">張君雅</a>
							</div>
							<div class="setting-btn">
								<div class="dropdown">
									<button class="btn btn-outline-success dropdown-toggle btn-sm"
										type="button" id="dropdownMenuButton" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false">編輯</button>
									<div class="dropdown-menu dropdown-menu-right text-center"
										aria-labelledby="dropdownMenuButton">
										<label class="change-cover-img">更換封面圖片 <input
											id="uploadCoverImg" type="file" name="takePic">

										</label>
										<hr style="margin: 10px;">
										<button type="button" class="btn change-description-text"
											data-toggle="modal" data-target="#myModal">編輯個人簡介</button>
										<!-- 彈出輸入視窗的部分在下面 （編輯個人簡介彈出視窗） 的地方 -->
									</div>
								</div>
							</div>

						</div>

						<div class="description-box" id="memberIntro">
							料理的溫度來自人與人之間，喜歡將創意融入料理將故事融入料理，將美與生活結合將美與料理結合用輕鬆愉快的氛圍將料理分享出去。為愛料理感受幸福滋味歡迎你一起來花媽甜心派品味生活，創意生活，開心過生活親子料理，創意料理公益活動，專欄寫作。
						</div>

					</div>
				</div>



			</div>
			<div class="row  my-recipe-body m-0 mt-4 p-0">
				<div class="col-3 time-line p-0 m-0 ">
					<div class="my-recipes-title text-center" id="whosRecipe">
						我的食譜</div>
					<div class="time-line-title">時間序</div>

					<div class="list-group list-group-flush">
						<a href="#"
							class="list-group-item list-group-item-action time-line-year"
							id="link2019">
							<div id="year">2019</div>
							<div id="year2019">23</div>
						</a> <a href="#"
							class="list-group-item list-group-item-action time-line-year"
							id="link2018">
							<div id="year">2018</div>
							<div id="year2018">66</div>
						</a> <a href="#"
							class="list-group-item list-group-item-action time-line-year"
							id="link2017">
							<div id="year">2017</div>
							<div id="year2017">843</div>
						</a> <a href="#"
							class="list-group-item list-group-item-action time-line-year"
							id="link2016">
							<div id="year">2016</div>
							<div id="year2016">165</div>
						</a>
					</div>
				</div>
				<!--col-3 最外-->

				<div class="col-9 my-recipes-list">
					<div class="my-recipes-list-bg container m-2">
						<div class="row m-0 p-0" id="recipeParent">


							<!-- <div class="col-4 p-2 m-0">
                                <div class="recipe-item-cards">
                                    <a href="#">
                                        <div class="recipe-cover-img">
                                            <img src="https://picsum.photos/255/255" alt="">
                                        </div>
                                        <div class="recipe-info p-2">
                                            <div class="recipe-publish-time">
                                                2019-09-14
                                            </div>
                                            <div class="recipe-title mt-2">
                                                香煎嫩雞吐司
                                            </div>
                                            <div class="recipe-description mt-2">
                                                把吐司變成盒子，裝進嫩煎雞肉和鬆軟炒蛋還用了桂冠沙拉醬清爽滑口的...
                                            </div>
                                        </div>
                                    </a>
                                    <div class="like-comment-icons mt-3 p-2">
                                        <div class="comments">
                                            <img src="img/my_page/icons/icon-comment.svg" alt="">
                                            <span>126</span>
                                        </div>
                                        <div class="likes">
                                            <img src="img/my_page/icons/icon-like.svg" alt=""> <span>126</span>
                                        </div>
                                        <button id="edit">編輯</button>
                                        <button id="delete">刪除</button>
                                    </div>
                                </div>
                            </div> -->



















						</div>
					</div>

					<div class="mt-4">
						<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center" id="pageNumber">
								<!-- <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li> -->
								<!-- <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li> -->
								<!-- <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li> -->
							</ul>
						</nav>

						<!-- ==========  編輯個人簡介彈出視窗 ======================= -->

						<div class="edit-description-modal">
							<div class="modal inmodal fade" id="myModal" tabindex="-1"
								role="dialog" aria-hidden="true" style="display: none;">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content animated bounceInRight">
										<div class="modal-header">
											<span class="modal-title" style="margin: 0 auto;">編輯個人簡介</span>
										</div>
										<div class="p-3">
											<textarea name="" id="editDescriptionText" cols="30"
												rows="10" maxlength="200" id="editMyIntro"></textarea>
											<div class="edit-description-modal-footer">
												<div class="text-count-box pt-1">
													還剩 <span id="textCountResult">200</span> 個字
												</div>
												<div class="cancel-save-btns">
													<button type="button" class="btn btn-white btn-sm"
														data-dismiss="modal">取消</button>
													<button type="button" class="btn btn-success btn-sm"
														id="introBtn">儲存</button>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>


					</div>

				</div>

			</div>
		</div>


	</div>




	<!--container my-page-main-->


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
	<script src="js/nav.js"></script>
	<script src="js/my_page.js"></script>
	<script src="js/loadMyRecipe.js"></script>
	<script src="js/linkToMySaveRecipe.js"></script>
	<script src="js/search.js"></script>
</body>

</html>