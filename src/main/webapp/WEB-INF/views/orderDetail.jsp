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
    <link rel="stylesheet" href="css/orderDetail_main.css" />
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <title>Order Detail</title>
  </head>

  <body>
    <!-- 	header -->
    <jsp:include page="header.jsp"></jsp:include>
    <div id="main">
      <div class="container my-4">
        <div class="text-center my-4" id="orderInfo"></div>

        <div id="cartListRow" class="row bg-white my-5">
          <div class="col-12 my-2">訂單明細</div>
          <div class="col-2 my-3" id="orderId">訂單編號: #12345678</div>
          <div class="col-3 my-3" id="orderTime">
            時間： 2019/10/18 星期五 10:19:35
          </div>

          <!-- 購物車明細標頭 -->
          <div class="col-12 my-2">
            <div class="container cartListHeader">
              <div class="row  py-1">
                <div class="col-7">商品</div>
                <div class="col-5">
                  <div class="container-fuild">
                    <div class="row text-right">
                      <div class="col-4">數量</div>
                      <div class="col-4">單價</div>
                      <div class="col-4">小計</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 購物車明細內容 -->
          <!-- <div class="col-12 my-2 ">
            <div class="container">
              <div class="row customBottomBorder pb-3">
                <div class="col-7">
                  <div class="container-fuild">
                    <div class="row">
                      <div class="col-2">
                        <img
                          src="productImage/001-1.jpg"
                          alt=""
                          style="width: 100%;"
                        />
                      </div>
                      <div class="col-3">
                        包心白菜 <br />
                        600克±10%/顆
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-5">
                  <div class="container-fuild">
                    <div class="row text-right">
                      <div class="col-4">
                        3
                      </div>
                      <div class="col-4">NT$ 88</div>
                      <div class="col-4">NT$ 176</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div> -->
        </div>

        <div class="col-12 text-right my-3 cartCard p-0">
          <button type="button" class="btn-Back btn btn-success text-white">
            返回上頁
          </button>
        </div>
      </div>
    </div>
    <!-- Footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="js/orderDetail_new.js"></script>
  </body>
</html>
