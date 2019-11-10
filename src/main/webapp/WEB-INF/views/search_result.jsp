<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title> EZfit Eat - 搜尋 的結果 </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/nav.css" />
    <link rel="stylesheet" href="css/search_result.css" />
    <link rel="stylesheet" href="css/search_bar.css" />

</head>

<body>

    <!-- Navigation Bar ========================================================================== -->
    <!-- Top ==================== -->
<jsp:include page="header.jsp"></jsp:include>
    <!-- == 搜尋列 ====================================================================== -->

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

    <!-- ======== 搜尋結果 ============ -->

    <div class="search-result-main container mb-4">
        <div class="row m-0 p-o">
            <div class="col-9">
                <div class="search-result-title">
                    找到 <span id="searchTotal">???</span>道的「<span id="result">???</span>」食譜
                </div>

                <div class="search-result-body my-3" id="parentCard">

                <!-- 卡片開始 -->
                     <%-- <div class="search-result-card my-3">
                        <div class="recipe-cover-img"> 
                            <a href="#"><img src="https://picsum.photos/200/200" alt=""></a>
                        </div>
                        <div class="browse-recipe-preview m-3 ml-3">
                            <a href="#"><p class="browse-recipe-name">食譜名稱</p></a>
                           <a href="#"><p class="result-username-by">作者名稱</p></a> 
                            <p class="browse-recipe-content-description">
                                食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜簡介食譜...
                            </p>
                            <ul class="browse-recipe-meta p-0">
                                <li class="browse-recipe-meta-fav">
                                    <img src="img/search_result/icon-like.svg" alt=""> 123
                                </li>
                                <li class="browse-recipe-meta-comment">
                                        <img src="img/search_result/icon-comment.svg" alt=""> 456
                                </li>
                            </ul>
                        </div>
                    </div>  --%>
           
             
                    
                    
                <!-- 卡片結束 -->


                </div>

<div class="mt-4">
						<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center" id="pageNumber">
								 <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li> 
							 <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li> 
								 <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
							</ul>
						</nav>
						</div>



            </div>



            <div class="col-3 p-0 pl-3 pop-keywords">

                    <div class="card border-success mb-3" style="max-width: 18rem;">
                            <div class="card-header">熱門關鍵字</div>
                            <div class="card-body text-success" id="hotSearch">
                                <%-- <a href="#" class="badge badge-success">義大利麵</a>
                                <a href="#" class="badge badge-success">馬鈴薯</a>
                                <a href="#" class="badge badge-success">茶碗蒸</a>
                                <a href="#" class="badge badge-success">玉米濃湯</a>
                                <a href="#" class="badge badge-success">豆腐</a>
                                <a href="#" class="badge badge-success">家常菜</a>
                                <a href="#" class="badge badge-success">餅乾</a>
                                <a href="#" class="badge badge-success">茶碗蒸</a>
                                <a href="#" class="badge badge-success">家常菜</a> --%>
                            </div>
                        </div>
                        <div class="ads">
                            <div><a href="#"><img src="img/search_result/ads/gif-banner-ad-300x250-2.gif" alt=""></a></div>
                            <div> <a href="#"><img src="img/search_result/ads/8814b257c399f4fc8224e3ce0d684d4a.gif"
                                        alt=""></a></div>
                            <div><a href="#"><img src="img/search_result/ads/8c38bf32521107.56888ecb516df.gif" alt=""></a>
                            </div>
                            <div><a href="#"><img src="img/search_result/ads/neiman_marcus.gif" alt=""></a></div>
    
                        </div>


            </div>

        </div>
    </div>



    <!-- Footer ======================================================================= -->
    <footer>
        <div class="search-text">
            <div class="container">
                <div class="row justify-content-center text-center">
                    <div class="form">
                        <form id="search-form" class="form-search form-horizontal">
                            <input type="text" class="input-search" placeholder="輸入 email 獲得更多優惠資訊">
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
                        <img src="img/nav/logo-all-white.svg">
                    </div>
                    <div class="social">
                        <a href="#"><img src="img/nav/facebook.svg"></a>
                        <a href="#"><img src="img/nav/twitter.svg"></a>
                        <a href="#"><img src="img/nav/instagram.svg"></a>
                        <a href="#"><img src="img/nav/line.svg"></a>
                    </div>
                </div>

                <div class="col-md-4 col-sm-12 text-center footer-margin">
                    <ul class="menu">
                        <span>關於EZfit Eat</span>
                        <li>
                            <a href="#">公司資訊</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">服務條款</a>
                        </li>

                        <li>
                            <a href="#">徵才訊息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">服務條款</a>
                        </li>

                        <li>
                            <a href="#">廣告合作</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">隱私政策</a>

                        </li>
                    </ul>
                </div>

                <div class="col-md-4 col-sm-12 text-center footer-margin">
                    <ul class="address">
                        <span>合作夥伴</span>
                        <li>
                            <img src="img/nav/tlogo.png" style="width: 70px;">
                            <img src="img/nav/w-logo.png" style="width: 70px;">
                            <img src="img/nav/logonew-1.png" style="width: 70px;">
                        </li>
                        <li>
                            <!-- <i class="fa fa-map-marker" aria-hidden="true"></i> -->
                            <a href="#">室友是友 4U4U</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="#">健客 Fititude</a>
                        </li>

                    </ul>
                </div>
            </div>
        </div>
        <div class="paymenttype text-center footer-margin">
            <img src="img/nav/logo-visa.svg" alt="">
            <img src="img/nav/logo-master.svg" alt="">
            <img src="img/nav/logo-jcb.svg" alt="">
            <img src="img/nav/logo-amex.svg" alt="">
        </div>
        <div class="copyright text-center"> Copyright ©2019 EZfit Eat 版權所有 All Rights Reserved</div>
    </footer>




    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    <script src="js/nav.js"></script>
    <script src="js/search_result.js"></script>
    <script src="js/result.js"></script>
    <script src="js/linkToMySaveRecipe.js"></script>
    <script src="js/search.js"></script>
</body>

</html>