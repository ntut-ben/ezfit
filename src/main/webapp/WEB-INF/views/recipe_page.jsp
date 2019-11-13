<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>EZfit Eat 食譜社群平台</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/nav.css" />
<link rel="stylesheet" href="css/recipe_page.css" />

</head>

<body>

	<!-- Navigation Bar ========================================================================== -->
	<jsp:include page="header.jsp"></jsp:include>
	<!-- ========================================================================= -->

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
	<div class="container  recipe-page-body">
		<div class="row m-0 p-0">
			<div class="col-9 m-0 p-0">
				<div class="recipe-page-title-bg">
					<div class="recipe-page-title" id="recipeName">鐵板黑胡椒洋蔥四季豆炒雞肉</div>

					<div class="share-btns">

						<div class="a2a_kit a2a_kit_size_32 a2a_default_style">
							<a class="a2a_button_facebook"></a> <a class="a2a_button_line"></a>
							<a class="a2a_button_facebook_messenger"></a> <a
								class="a2a_button_email"></a> <a class="a2a_button_print"></a>
						</div>
					</div>

					<div class="likes-comments-icons text-right">
						<a href="#"><img src="img/recipe_page/icon-like.svg" alt=""><span>收藏</span><span
							id="save">362</span></a> <span>&nbsp;&nbsp;</span> <a href="#"><img
							src="img/recipe_page/icon-comment.svg" alt=""><span>留言</span><span
							id="chat">2</span></a>
					</div>
				</div>

				<div class="row m-0 recipe-cover">

					<div class="col-6 m-0 p-0">
						<img src="https://picsum.photos/854/422" class="recipe-cover-img"
							alt="" id="recipePic">
					</div>
					<div class="col-6 m-0 p-0 pl-3 about-this-recipe">
						<div class="member-details">
							<a href="#" id='memberLink1'><img
								src="http://placekitten.com/90/90" class="profile-img" alt="" id='memberHeadPic'></a>
							<div class="member-name">
								<a href="#" id='memberLink2'><p class="m-0" id='ownerName'>作者名稱</p></a>
								<p class="m-0">
									<span id="num">250</span><span>食譜</span><span class="ml-3">2550</span><span>粉絲</span>
								</p>
							</div>
							<div class="ml-5">
								<button type="button"
									class="btn btn-outline-success follow-btn mt-3 mr-0">追蹤</button>
							</div>

						</div>

						<div class="recipe-brief">
							<p id="introduction">
								鐵板料理的美味，那熱騰騰上桌的一剎那炒熱同桌的氣氛。今天用低脂的雞胸肉，盡情享受這令人興奮的料理。歡迎來陳太太的菜菜味，享受家庭親子美好食光~
							</p>
						</div>
						<div class="text-center like-recipe">
							<button type="button" class="btn btn-success"
								style="width: 150px;" id="followBtn">
								<img src="img/recipe_page/icon-heart-solid.svg"
									class="pb-1 pr-1" alt=""> 收 藏
							</button>
						</div>
					</div>
				</div>

				<div class="recipe-details-main">
					<div class="recipe-details-info">
						<div class="title-ingredients">所需食材</div>
						<div style="display: flex; flex-direction: row;">
							<div class="servings" id="servings">
								<img src="img/recipe_page/icon-servings.svg" alt="">份量：4人份
							</div>
							<div class="cooking-time" id="spendTime">
								<img src="img/recipe_page/icon-cookingtime.svg" alt=""
									id="spendTime">時間：20分鐘
							</div>
						</div>

					</div>
					<div class="ingredients-groups">
						<div class="ingredients" id="ingredients">
							<!-- <div class="ingredient">
                                <div class="ingredient-name"><a href="#">雞胸肉</a></div>
                                <div class="ingredient-unit">200g</div>
                            </div> -->
							<!-- <div class="ingredient">
                                <div class="ingredient-name"><a href="#">大蒜</a></div>
                                <div class="ingredient-unit">1瓣</div>
                            </div> -->
							<!-- <div class="ingredient">
                                <div class="ingredient-name"><a href="#">四季豆</a></div>
                                <div class="ingredient-unit">100g</div>
                            </div>
                            <div class="ingredient">
                                <div class="ingredient-name"><a href="#">洋蔥</a></div>
                                <div class="ingredient-unit">1/2顆</div>
                            </div>
                            <div class="ingredient">
                                <div class="ingredient-name"><a href="#">青蔥</a></div>
                                <div class="ingredient-unit">1枝</div>
                            </div> -->
						</div>
						<div class="text-center m-5 pack-ingredients">
							<button type="button" class="btn btn-success" id="package">
								<img src="img/recipe_page/icon-add-to-cart.svg"
									class="pb-1 pr-2" alt="">打包食材
							</button>
						</div>
						<!-- <div class="title-seasoning">所需調味料</div> -->
						<!-- <div class="seasoning-main">
                            <div class="seasoning">
                                <div class="seasoning-name">沙拉油</div>
                                <div class="seasoning-unit">1大匙</div>
                            </div>
                            <div class="seasoning">
                                <div class="seasoning-name">醬油</div>
                                <div class="seasoning-unit">1大匙</div>
                            </div>
                            <div class="seasoning">
                                <div class="seasoning-name">黑胡椒調味粉</div>
                                <div class="seasoning-unit">3大匙</div>
                            </div>
                            <div class="seasoning">
                                <div class="seasoning-name">蒜風味油</div>
                                <div class="seasoning-unit">1匙</div>
                            </div>
                            <div class="seasoning">
                                <div class="seasoning-name">水</div>
                                <div class="seasoning-unit">5大匙</div>
                            </div>
                            <div class="seasoning">
                                <div class="seasoning-name">糖</div>
                                <div class="seasoning-unit">1大匙</div>
                            </div>
                            <div class="seasoning">
                                <div class="seasoning-name">鹽巴</div>
                                <div class="seasoning-unit">1/2小匙</div>
                            </div>
                            <div class="seasoning">
                                <div class="seasoning-name">胡椒粉</div>
                                <div class="seasoning-unit">少許</div>
                            </div>
                            <div class="seasoning">
                                <div class="seasoning-name">米酒</div>
                                <div class="seasoning-unit">1大匙</div>
                            </div>
                        </div> -->
					</div>
				</div>

				<div class="instructions-main mt-3 mb-3" id="methodSec">
					<!-- <div class="instruction-step">
                        <div class="step-img">
                            <img src="https://picsum.photos/300/300" alt="">
                        </div>
                        <div class="step">
                            <div class="step-number">1</div>
                            <div class="step-instruction">將雞胸肉切塊（我切的比較大），用醃肉調味料醃15分鐘以上。</div>
                        </div>
                    </div> -->

					<!-- <div class="instruction-step">
                        <div class="step-img">
                            <img src="https://picsum.photos/200/200" alt="">
                        </div>
                        <div class="step">
                            <div class="step-number">2</div>
                            <div class="step-instruction">全部加在麵糊裡~加一匙鹽和胡椒粉調味~(麵糊可依個人口味調整~喜歡脆一點的水就少放~海鮮也可依個人口味再增減)</div>
                        </div>
                    </div> -->

					<!-- <div class="instruction-step">
                        <div class="step-img">
                            <img src="https://picsum.photos/250/250" alt="">
                        </div>
                        <div class="step">
                            <div class="step-number">3</div>
                            <div class="step-instruction">全部加在麵糊裡~加一匙鹽和胡椒粉調味~(麵糊可依個人口味調整~喜歡脆一點的水就少放~海鮮也可依個人口味再增減)</div>
                        </div>
                    </div> -->
				</div>

				<div class="comments-main p-3 mb-5" id="chatParent">
					<div class="title-comments">
						討論 <span id="chatTotal">共 2 則</span>
					</div>
					<div class="leave-comment-main mt-3 pb-3">
						<div class="your-profile-img">
							<img src="http://placekitten.com/90/90" id='head'>
						</div>
						<div class="leave-comment pl-4 ">
							<textarea name="comment" id="comment" cols="30" rows="10"
								maxlength="400" placeholder="加入討論..."></textarea>
							<div class="submit-comment">
								<div>
									<div class="comment-text-count">
										剩餘 <span id="commentTextCount">400</span> 個字
									</div>
									<div class="terms-conditions">
										發佈即代表已知悉並詳閱「<a href="">EZfit Eat 服務條款</a>」之規範。
									</div>
								</div>
								<button type="button" class="btn btn-outline-success"
									id="submitBtn">確定送出</button>
								<!-- 需設定如果textarea無輸入，按鈕則失效-->
							</div>

						</div>
					</div>

					<!-- <div class="members-comments mt-4 pb-2" >
                        <div class="member-profile-img"><a href="#"><img src="http://placekitten.com/92/92" alt=""></a></div>
                        <div class="comment-box ml-4">
                            <div class="member-name p-0"><a href="#">會員名稱</a></div>
                        <div class="comment-text pt-3">留言內容。作者可直接回覆留言 or not ？</div>
                        <div class="comment-time pt-4">2天前 （或是直接顯示日期＋時間？）</div>
                        </div>
                        
                    </div> -->

					<!-- <div class="members-comments mt-4 pb-2">
                        <div class="member-profile-img"><a href="#"><img src="http://placekitten.com/91/91" alt=""></a></div>
                        <div class="comment-box ml-4">
                            <div class="member-name p-0"><a href="#">會員名稱</a></div>
                        <div class="comment-text pt-3">留言內容。作者可直接回覆留言 or not ？</div>
                        <div class="comment-time pt-4">2天前 （或是直接顯示日期＋時間？）</div>
                        </div>
                        
                    </div> -->

				</div>




			</div>
			<!-- col-9 end -->

			<div class="col-3 m-0 p-5">
				<div class="ads">
					<div>
						<a href="#"><img
							src="img/recipe_page/ads/gif-banner-ad-300x250-2.gif" alt=""></a>
					</div>
					<div>
						<a href="#"><img
							src="img/recipe_page/ads/8814b257c399f4fc8224e3ce0d684d4a.gif"
							alt=""></a>
					</div>
					<div>
						<a href="#"><img
							src="img/recipe_page/ads/8c38bf32521107.56888ecb516df.gif" alt=""></a>
					</div>
					<div>
						<a href="#"><img src="img/recipe_page/ads/neiman_marcus.gif"
							alt=""></a>
					</div>

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
	<script>
		var a2a_config = a2a_config || {};
		a2a_config.locale = "zh-TW";
	</script>
	<script async src="https://static.addtoany.com/menu/page.js"></script>
	<script src="js/nav.js"></script>
	<script src="js/recipe_page.js"></script>
	<script src="js/chat.js"></script>
	<script src="js/linkToMySaveRecipe.js"></script>
	<script src="js/search.js"></script>
</body>

</html>