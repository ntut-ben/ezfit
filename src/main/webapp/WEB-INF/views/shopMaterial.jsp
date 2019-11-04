<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>

<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="css/fontawesome-free-5.11.2-web/css/all.css" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="css/main.css" />
    <link rel="stylesheet" href="css/shopMaterial_main.css" />
    <title>Shop Material</title>
  </head>
  <body>
    <jsp:include page="header.jsp"></jsp:include>
    <!-- main -------------------------------------->
    <div id="main">
      <div class="container my-4">
        <!-- search Bar -->
        <div class="row">
          <div class="col-2 mx-auto"></div>
          <div class="col-9 mx-auto p-0 ">
            <div class="cotainer-fulid">
              <div class="row justify-content-between">
                <div class="col-8 d-flex flex-column justify-content-end">
                  <div class="my-1">
                    <form action="" id="searchForm">
                      <input
                        type="text"
                        name="search"
                        class="serarchBarInput"
                        value=""
                      />
                      <button class="serarchBarBtn text-white text-center">
                        <i class="fas fa-search text-white"></i>
                      </button>
                    </form>
                  </div>
                </div>

                <div class="col-4 text-left">
                  <div id="groupContainer" style="visibility: hidden;">
                    <div class="container-fulid">
                      <div class="row">
                        <div id="groupName" class="col-12 my-1 h5 color-439A23">
                          團購名稱
                        </div>
                        <div id="groupDeadLine" class="col-12 my-1 h6">
                          團購結單日期：2019-12-02
                        </div>
                        <div class="col-12 my-1 d-flex">
                          <div>
                            <button
                              id="groupShareBtn"
                              class="btn btn-success text-white"
                            >
                              揪朋友
                            </button>
                          </div>
                          <div class="mx-auto">
                            <button
                              id="orderMGT"
                              class="btn btn-success text-white"
                              onclick="location.href='orders'"
                            >
                              訂單管理
                            </button>
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
      <!-- Browser -->
      <div class="container my-4">
        <div class="row">
          <!-- slide Menu ---------------------------------------->
          <div id="menu" class="col-2 mx-auto " style="max-height: 100vh">
            <div class="container">
              <h4 class="m-3">商品分類</h4>
              <ul class="mx-auto">
                <li class="m-3">
                  <a
                    class="selectCate"
                    href=""
                    data-category="vegetable"
                    data-page="1"
                    >蔬菜</a
                  >
                </li>
                <li class="m-3">
                  <a
                    class="selectCate"
                    href=""
                    data-category="fruit"
                    data-page="1"
                    >水果</a
                  >
                </li>
                <li class="m-3">
                  <a
                    class="selectCate"
                    href=""
                    data-category="brute"
                    data-page="1"
                    >牛羊豬肉</a
                  >
                </li>
                <li class="m-3">
                  <a
                    class="selectCate"
                    href=""
                    data-category="poultry"
                    data-page="1"
                    >雞鴨鵝肉</a
                  >
                </li>
                <li class="m-3">
                  <a
                    href=""
                    class="selectCate"
                    href=""
                    data-category="seafood"
                    data-page="1"
                    >魚蝦海鮮</a
                  >
                </li>
                <li class="m-3">
                  <a
                    href=""
                    class="selectCate"
                    href=""
                    data-category="rice"
                    data-page="1"
                    >米麵雜糧</a
                  >
                </li>
                <li class="m-3">
                  <a
                    href=""
                    class="selectCate"
                    href=""
                    data-category="egg"
                    data-page="1"
                    >蛋奶豆製品</a
                  >
                </li>
              </ul>
            </div>
          </div>

          <!-- Present area ---------------------------------------->
          <div id="presentArea" class="col-9  mx-auto">
            <div class="container-fulid">
              <!-- 產品區 -------------------------------->
              <div id="listProduct" class="row">
                <!-- <div class="col-12 my-3">
                  <span
                    class="border-bottom-success font-size-2rem letter-spacing-015rem"
                    >蔬菜</span
                  >
                </div> -->
                <!-- <div class="col-3 my-3">
                  <div class="card" style="width: 100%;">
                    <div class="popupCard">
                      <img
                        src="productImage/001-1.jpg"
                        class="card-img-top"
                        alt=""
                      />
                    </div>

                    <div class="card-body p-0">
                      <div class="popupCard">
                        <h4 class="card-text m-0 m-2">包心白菜</h4>
                      </div>
                      <p class="card-text font-size-08rem m-0  m-2">
                        600克±10%/顆
                      </p>
                      <p class="card-text m-0  m-2">
                        $88
                      </p>

                      <form action="addCart.do" class="text-right ">
                        <input
                          type="text"
                          name="category"
                          value="IngredientProduct"
                          style="display: none;"
                        />
                        <input
                          type="text"
                          name="itemId"
                          value="1"
                          style="display: none;"
                        />
                        <input
                          type="number"
                          name="quantity"
                          min="1"
                          max="10"
                          value="1"
                          class="text-center w-25 m-1 font-size-08rem"
                        />
                        <button
                          class="text-white font-size-08rem m-1 btn btn-success letter-spacing-015rem p-1"
                        >
                          <i class="fas fa-shopping-cart"></i>加入購物車
                        </button>
                      </form>
                    </div>
                  </div>
                </div> -->
              </div>
              <!-- 頁碼區 -------------------------------->
              <div class="row my-5">
                <div class="col-12">
                  <nav aria-label="Page navigation example">
                    <ul id="page" class="pagination justify-content-center">
                      <li id="pagePrevious" class="page-item">
                        <a class="page-link" href="" aria-label="Previous">
                          <span aria-hidden="true">&laquo;</span>
                          <span class="sr-only">Previous</span>
                        </a>
                      </li>
                      <!-- <li class="page-item">
                        <a class="page-link " href="shopMaterial.html/&page=1"
                          >1</a
                        >
                      </li>
                      <li class="page-item ">
                        <a class="page-link " href="shopMaterial.html/&page=2"
                          >2</a
                        >
                      </li>
                      <li class="page-item">
                        <a class="page-link" href="shopMaterial.html/&page=3"
                          >3</a
                        >
                      </li> -->
                      <li id="pageNext" class="page-item disabled">
                        <a class="page-link" href="" aria-label="Next">
                          <span aria-hidden="true">&raquo;</span>
                          <span class="sr-only">Next</span>
                        </a>
                      </li>
                    </ul>
                  </nav>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- Item Modal -->
    <div
      class="modal fade"
      id="itemModel"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div id="itemDetail" class="modal-body">
            <!-- <div class="container">
              <div class="row">
                <div class="col-12">
                  <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-label="Close"
                  >
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="col-4 mx-auto">
                  <img
                    src="productImage/001-1.jpg"
                    alt=""
                    style="width: 100%;"
                  />
                </div>
                <div class="col-8 mx-auto">
                  <h4 class="cardFontColor">包心白菜</h4>
                  <p class="m-0 cardFontColor font-size-08rem">
                    容量包裝：600克±10%/顆
                  </p>
                  <p class="m-0 cardFontColor font-size-08rem">
                    安全等級：無毒
                  </p>
                  <p class="cardFontColor font-size-08rem">來源：某某農場</p>
                  <p class="cardFontColor font-size-08rem">
                    包心白菜屬十字花科植物，又稱為大白菜。含有胡蘿蔔素、維他命C、鈣、鉀
                    鎂等礦物質。適合清炒、煮湯、燉滷等，也可以醃漬韓式或台式泡菜。
                  </p>
                  <p class="cardFontColor font-size-08rem">
                    建議保存方式：冷藏
                  </p>
                  <form action="" class="text-right ">
                    <input
                      type="number"
                      name="quantity"
                      min="1"
                      max="10"
                      value="1"
                      class="text-center m-1 font-size-08rem"
                      style="width: 50px;"
                    />
                    <button
                      class="text-white font-size-08rem m-1 btn btn-success letter-spacing-015rem p-1"
                    >
                      <i class="fas fa-shopping-cart"></i>加入購物車
                    </button>
                  </form>
                </div>

                <div class="col-12 text-center my-3">食譜推薦</div>
                <div class="col-12">
                  <div class="container">
                    <div class="row">
                      <div class="col-3 mx-auto">食譜-1</div>
                      <div class="col-3 mx-auto">食譜-2</div>
                      <div class="col-3 mx-auto">食譜-3</div>
                    </div>
                  </div>
                </div>
              </div>
            </div> -->
          </div>
        </div>
      </div>
    </div>

    <!-- toast ------------------------------------------------------------->

    <div class="toast ">
      <div class="toast-header bg-info text-white"></div>
      <div class="toast-body"></div>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="js/nav.js"></script>
    <script src="js/shopMaterial_new.js"></script>
  </body>
</html>
