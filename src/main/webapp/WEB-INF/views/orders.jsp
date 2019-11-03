<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <title>EZfit Eat - 訂單管理</title>
  <link
    rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
  />
  <link rel="stylesheet" href="css/nav.css" />
  <link rel="stylesheet" href="css/orders.css" />
</head>
<html>
  <body>
    <jsp:include page="header.jsp"></jsp:include>
    <!-- Navigation Bar ========================================================================== -->
    <!-- Top ==================== -->

    <!-- RWD small Navbar =========================================================== -->

    <!-- ========================================================================= -->

    <div class="orders-main">
      <div class="container orders-body">
        <div class="row">
          <div class="col-12">
            <div class="orders-title mb-5">
              <img src="img/orders/payment.svg" alt="" /> 訂單管理
            </div>
            <div class="order-detail-tabs">
              <ul class="nav nav-pills mb-2" id="pills-tab" role="tablist">
                <li class="nav-item mr-2">
                  <a
                    class="nav-link active"
                    id="pills-home-tab"
                    data-toggle="pill"
                    href="#pills-home"
                    role="tab"
                    aria-controls="pills-home"
                    aria-selected="true"
                    >訂單記錄</a
                  >
                </li>
                <li class="nav-item">
                  <a
                    class="nav-link"
                    id="pills-profile-tab"
                    data-toggle="pill"
                    href="#pills-profile"
                    role="tab"
                    aria-controls="pills-profile"
                    aria-selected="false"
                    >團購管理</a
                  >
                </li>
              </ul>
              <!-- 訂單記錄 -->
              <div class="tab-content p-3" id="pills-tabContent">
                <div
                  class="tab-pane fade show active"
                  id="pills-home"
                  role="tabpanel"
                  aria-labelledby="pills-home-tab"
                >
                  <table class="table table-hover">
                    <thead>
                      <tr>
                        <th scope="col">交易時間</th>
                        <th scope="col">訂單號碼</th>
                        <th scope="col">收件人</th>
                        <th scope="col">商品明細</th>
                        <th scope="col">總金額</th>
                      </tr>
                    </thead>
                    <tbody id="orderTbody">
                      <!-- <tr>
                        <td>2019-10-03 18:04</td>
                        <td>#A201910138843</td>
                        <td>王小明</td>
                        <td><a href="#">點此查看明細</a></td>
                        <td>1380 元</td>
                      </tr> -->
                    </tbody>
                  </table>
                </div>

                <!-- 團購管理 -->
                <div
                  class="tab-pane fade"
                  id="pills-profile"
                  role="tabpanel"
                  aria-labelledby="pills-profile-tab"
                >
                  <div class="group-buy-main">
                    <div class="accordion" id="accordionExample"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <script
      src="https://code.jquery.com/jquery-3.4.1.min.js"
      integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"
    ></script>
    <script src="js/nav.js"></script>
    <script src="js/orders.js"></script>
  </body>
</html>
