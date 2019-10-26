<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
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
    <link
      rel="stylesheet"
      type="text/css"
      href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"
    />
    <link rel="stylesheet" href="css/main.css" />
    <link rel="stylesheet" href="css/modifyMeals.css" />
    <title>modify Meals</title>
  </head>
  <body>
    <!--   Header -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- Main -->
    <div id="" class="container-fuild  pt-5">
      <!-- Title -->
      <div class="container pt-5">
        <div class="row pt-5">
          <div class="col-12 mt-4" id="title">
            <h4 id="titleHeader"></h4>
          </div>
        </div>
      </div>

      <!-- change your meal -->
      <div id="mainContainer" class="container py-3 my-5">
        <div class="row" id="chooseRow">
          <!-- 天數 -->
          <!-- <div class="col-12 day my-2"><h5>第一天</h5></div> -->
          <!-- 選擇餐點 -->

          <!-- section of meal for polling -->
          <!-- <div class="col-12 px-3">
            <div class="multiple-items text-center color-838383">
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
            </div>
          </div> -->

          <!-- 分隔線 -->
          <!-- <div class="col-12 my-2"><hr /></div> -->

          <!-- 天數 -->
          <!-- <div class="col-12 day my-2"><h5>第二天</h5></div> -->
          <!-- 選擇餐點 -->

          <!-- section of meal for polling -->
          <!-- <div class="col-12 px-3">
            <div class="multiple-items text-center color-838383">
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
              <div class="p-2" style=" width: 185px;">
                <img src="productImage/1032-1.jpg" alt="" style="width:100%;" />
                <h5 class="my-1">好吃的早餐</h5>
                <p class="my-2 cardBody">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Omnis, eveniet!
                </p>
                <div class="my-1 cardDetail">詳細資訊</div>
              </div>
            </div>
          </div> -->

          <!-- 分隔線 -->
          <!-- <div class="col-12 my-2"><hr /></div> -->
        </div>
        <div class="row">
          <!-- 送出 -->
          <div class="col-5 mx-auto text-center">
            <button
              type="button"
              class="btn btn-success text-white w-50 my-3"
              id="cancleChange"
            >
              <h5 class="m-0" style="letter-spacing: 1px;">取消更換</h5>
            </button>
          </div>

          <div class="col-5 mx-auto text-center">
            <button
              type="button"
              class="btn btn-success text-white w-50 my-3"
              id="submitChange"
            >
              <h5 class="m-0" style="letter-spacing: 1px;">確定更換</h5>
            </button>
          </div>
        </div>
      </div>
    </div>
    <!--    Footer -->
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
            <div class="container">
              <div id="detialInfoContainer" class="row ">
                <div class="col-12">
                  <button
                    type="button"
                    class="close"
                    data-dismiss="modal"
                    aria-label="Close"
                  >
                    <span aria-hidden="true" class="font-weight-bold h4"
                      >&times;</span
                    >
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script
      type="text/javascript"
      src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"
    ></script>
    <script src="js/modifyMeals.js"></script>
  </body>
</html>
