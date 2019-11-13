<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>寫食譜 - EZfit Eat 健康餐點 ＋ 食譜社群平台</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/nav.css" />
<link rel="stylesheet" href="css/publish_recipe.css" />

</head>

<body>

	<!-- Navigation Bar ========================================================================== -->
	<jsp:include page="header.jsp"></jsp:include>

	<!-- 寫食譜 ========================================================================= -->

	<div class="add-recipe">
		<div class="recipe-search container">
			<div class="row p-0 m-0">
				<div class="col-6 p-0">
					<form class="form-inline">
						<input id="searchRecipe" class="form-control recipe-search-input"
							type="search" placeholder="請輸入關鍵字搜尋···" aria-label="Search">
						<button class="btn btn-success btn-search recipe-search-btn"
							type="button" id="searchButom">
							<img src="img/search_bar/icon-search.svg"
								style="margin: 0 6px 5px 0;">搜尋
						</button>
					</form>
				</div>
				<div class="col-6 write-heart-btn text-right p-0" id="writeRecipe">
					<button type="button" class="btn btn-success" id="searchButom">
						<img src="img/search_bar/icon-write.svg">寫食譜
					</button>
					<button type="button" class="btn btn-success" id="mySaveBtn">
						<img src="img/search_bar/icon-heart.svg">食譜收藏
					</button>
				</div>
			</div>
		</div>

	</div>
	<form id="recipe" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="recipePk" value="0" id="recipePk">
		<div class="container add-recipe-body-bg">
			<div class="row m-0 p-0">
				<div class="col-7 p-0">
					<input class="enter-recipe-title" type="text" placeholder="請填寫食譜標題"
						maxlength="20" id="recipeName" name="recipeName">
					<div class="max-text-length">
						剩餘<span id="recipeTitleTextCount">20</span>個字
					</div>
					<div class="recipe-about">
						<div class="row m-0 p-0">
							<div class="col-7 m-0 p-0 ">
								<label id="uploadCoverImgLabel"><input type="file"
									id="uploadCoverImg" accept="image/*" name="recipePic"></label>

								<img id="coverImg" src="img/publish_recipe/add_photo.svg">
							</div>


							<div class="col-5 m-0 p-0 pl-3 recipe-about-text">
								<p class="mb-1">烹調時間（分鐘）</p>
								<select class="form-control" name="spendTime" id="spendTime">
									<option value="null">未設定</option>
									<option value="5">5</option>
									<option value="10">10</option>
									<option value="15">15</option>
									<option value="20">20</option>
									<option value="30">30</option>
									<option value="45">45</option>
									<option value="60">60</option>
									<option value="90">90</option>
									<option value="120">120</option>
									<option value="180+">180+</option>
								</select>


								<p class="mt-2 mb-1">份量（人份）</p>
								<select class="form-control" name="servings" id="servings">
									<option value="null">未設定</option>
									<option value="2">2</option>
									<option value="4">4</option>
									<option value="6">6</option>
									<option value="8">8</option>
									<option value="10">10</option>
									<option value="12">12</option>
								</select>
								<p class="mt-2 mb-1" style="color: #3b3b3b;">簡介</p>
								<textarea name="introduction" id="brief" cols="32" rows="9"
									maxlength="180"></textarea>
								<p class="mb-0 text-right brief-max-text-length">
									剩餘<span id="briefTextCount">180</span>個字
								</p>
								<input type="hidden" name="published" id="published">
							</div>

						</div>
					</div>
					<div class="titles my-3">步驟</div>

					<!-- <input type="hidden" value="0" name="methodLength" id="methodLength"> -->
					<div class="directions mt-2 p-0" id="directions">
						<div class="row m-0 p-2 step" id="step1">
							<div class="col-4 m-0 p-0">
								<input type="hidden" name="mdPk0" value="0"> <label
									id="uploadStepImgLabel0"> <input type="file"
									id="uploadStepImg0" accept="image/*" name="methodPic0">
								</label> <img id="stepImg0" src="img/publish_recipe/add_photo_small.svg">
							</div>
							<div class="col-8 step-controller m-0 p-0">
								<div class="step-num"></div>
								<div class="step-icons">
									<img src="img/publish_recipe/icon_add.svg" id="rrr"
										class="handle" alt=""> <img
										src="img/publish_recipe/icon_delete.svg" class="handle" alt=""
										style="display: none">
									<!-- <img src="img/publish_recipe/icon_drag.svg" class="handle" alt=""> -->
								</div>
								<div class="direction-text">
									<textarea id="directionText0" name="method0" id="" cols="30"
										rows="10" maxlength="150"></textarea>
									<p class="mb-0 text-right direction-text-length">
										剩餘<span id="directionTextCount">150</span>個字
								</div>
							</div>
						</div>


						<!-- <div class="row m-0 p-2 step">
                            <div class="col-4 m-0 p-0">
                                <label id="uploadStepImgLabel"><input type="file" id="uploadStepImg"
                                        accept="image/*"></label>

                                <img id="stepImg" src="img/publish_recipe/add_photo_small.svg">
                            </div>
                            <div class="col-8 step-controller m-0 p-0">
                                <div class="step-num"> </div>
                                <div class="step-icons">
                                    <img src="img/publish_recipe/icon_add.svg" class="handle" alt="">
                                    <img src="img/publish_recipe/icon_delete.svg" class="handle" alt="">
                                    <img src="img/publish_recipe/icon_drag.svg" class="handle" alt="">
                                </div>
                                <div class="direction-text">
                                    <textarea id="directionText" name="" id="" cols="30" rows="10"
                                        maxlength="150"></textarea>
                                    <p class="mb-0 text-right direction-text-length">剩餘<span
                                            id="directionTextCount">150</span>個字
                                </div>
                            </div>
                        </div> -->


						<!-- <div class="row m-0 p-2 step" id="step3">
                        <div class="col-4 m-0 p-0">
                            <label id="uploadStepImgLabel"><input type="file" id="uploadStepImg"
                                    accept="image/*"></label>

                            <img id="stepImg" src="img/publish_recipe/add_photo_small.svg">
                        </div>
                        <div class="col-8 step-controller m-0 p-0">
                            <div class="step-num"> </div>
                            <div class="step-icons">
                                <img src="img/publish_recipe/icon_add.svg" class="handle" alt="">
                                <img src="img/publish_recipe/icon_delete.svg" class="handle" alt="">
                                <img src="img/publish_recipe/icon_drag.svg" class="handle" alt="">
                            </div>
                            <div class="direction-text">
                                <textarea id="directionText" name="" id="" cols="30" rows="10"
                                    maxlength="150"></textarea>
                                <p class="mb-0 text-right direction-text-length">剩餘<span
                                        id="directionTextCount">150</span>個字
                            </div>
                        </div>
                    </div> -->


					</div>





				</div>




				<!-- Right Side ========================================================== -->


				<div class="col-5 p-0 pl-3">
					<button type="button" class="btn btn-success post-recipe-btn"
						id="publishBT">發 佈</button>
					<button type="button" class="btn btn-outline-success draft-btn"
						id="saveBT">儲存草稿</button>
					<button type="button" class="btn btn-outline-danger delete-btn"
						id="deleteBT">刪除</button>
					<button type="button" class="btn btn-outline-secondary cancel-btn"
						id="cancelBT">取消</button>

					<div class="ingredients p-2">
						<p>食材</p>
						<!-- <input type="hidden" name="materalLength" id="materalLength"> -->
						<div id="ingredientItems">
							<div class="ingredient-items">
								<input type="hidden" name="mlPk0" value="0"> <input
									type="text" class="ingredient-name" placeholder="請輸入食材名稱"
									name="materalName0" id="materalName0"><input
									type="text" class="ingredient-qty" placeholder="份量" name="unit"
									id="materalQty0" value="">
								<div class="ingredient-icons">
									<img src="img/publish_recipe/icon_delete.svg" class="handle"
										alt="">
									<!-- <img src="img/publish_recipe/icon_drag.svg" class="handle" alt=""> -->
								</div>
							</div>

							<!-- <div class="ingredient-items">
                                <input type="text" class="ingredient-name" placeholder="請輸入食材名稱"><input type="text"
                                    class="ingredient-qty" placeholder="份量">
                                <div class="ingredient-icons">
                                    <img src="img/publish_recipe/icon_delete.svg" class="handle" alt="">
                                    <img src="img/publish_recipe/icon_drag.svg" class="handle" alt="">
                                </div>
                            </div> -->

							<!-- <div class="ingredient-items">
                                <input type="text" class="ingredient-name" placeholder="請輸入食材名稱">
                                <input type="text" class="ingredient-qty" placeholder="份量">
                                <div class="ingredient-icons">
                                    <img src="img/publish_recipe/icon_delete.svg" class="handle" alt="">
                                    <img src="img/publish_recipe/icon_drag.svg" class="handle" alt="">
                                </div>
                            </div> -->
						</div>

						<div class="add-ingredient-item text-center" id="addMateral">
							<img src="img/publish_recipe/icon_add_gray.svg" alt="">
						</div>

					</div>
				</div>
			</div>

			<!-- 回到頂部＝＝＝＝＝＝＝ -->
			<div id="return-to-top">
				<img src="img/publish_recipe/back-to-top.svg">
			</div>

		</div>
	</form>










	<!-- Footer ======================================================================= -->
	<jsp:include page="footer.jsp"></jsp:include>




	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
		integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
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
	<script src="js/publish_recipe.js"></script>
	<script src="js/writeRecipe.js"></script>
	<script src="js/loadRecipe.js"></script>
	<script src="js/linkToMySaveRecipe.js"></script>
	 <script src="js/search.js"></script>
	<!-- <script src="js/瀚文JS/test.js"></script> -->

</body>

</html>