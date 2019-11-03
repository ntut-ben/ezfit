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
                    <tbody>
                      <tr>
                        <td>2019-10-03 18:04</td>
                        <td>#A201910138843</td>
                        <td>王小明</td>
                        <td><a href="#">點此查看明細</a></td>
                        <td>1380 元</td>
                      </tr>
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
                    <div class="accordion" id="accordionExample">
                      <!-- 進行中的團購 -->

                      <div class="card">
                        <div class="card-header" id="headingOne">
                          <h2 class="mb-0">
                            <button
                              class="btn btn-link collapsed text-left"
                              type="button"
                              data-toggle="collapse"
                              data-target="#collapseOne"
                              aria-expanded="false"
                              aria-controls="collapseOne"
                            >
                              <p>
                                <span
                                  >日期：<span id="group-buy-time"
                                    >2019-10-28 （一）15:03</span
                                  >
                                  &nbsp;|&nbsp;
                                </span>
                                <span
                                  >揪團編號：<span id="group-buy-no">0001</span>
                                  &nbsp;|&nbsp;
                                </span>
                                <span
                                  >訂單編號：<span id="group-buy-order-no"
                                    >12345678</span
                                  >
                                  &nbsp;|&nbsp;
                                </span>
                                <span
                                  >出貨時間：<span id="group-buy-shipping-time"
                                    >2019-11-12 (二)</span
                                  ></span
                                >
                                <span class="badge badge-pill badge-success">
                                  進行中
                                </span>
                              </p>
                              <p>
                                <span
                                  >主揪人：<span id="group-buy-hostname"
                                    >張君雅</span
                                  >
                                  &nbsp;|&nbsp;
                                </span>
                                <span
                                  >收貨地址：<span id="group-buy-addr"
                                    >台北市忠孝東路三段一號</span
                                  ></span
                                >
                              </p>
                            </button>
                          </h2>
                        </div>

                        <div
                          id="collapseOne"
                          class="collapse"
                          aria-labelledby="headingOne"
                          data-parent="#accordionExample"
                        >
                          <div class="card-body p-3">
                            <div class="action-btns">
                              <div class="group-list pt-2">揪團名單</div>
                              <div>
                                <button
                                  type="button"
                                  class="btn btn-outline-success btn-sm action-btn"
                                >
                                  <img
                                    class="btn-icons-g"
                                    src="img/orders/ic_local_grocery_store_24px.svg"
                                    alt=""
                                  />
                                  <img
                                    class="btn-icons-w"
                                    src="img/orders/ic_local_grocery_store_24px_w.svg"
                                    alt=""
                                  />
                                  加入點餐
                                </button>
                                <button
                                  type="button"
                                  class="btn btn-outline-success btn-sm action-btn"
                                >
                                  <img
                                    class="btn-icons-g"
                                    src="img/orders/ic_content_copy_24px.svg"
                                    alt=""
                                  />
                                  <img
                                    class="btn-icons-w"
                                    src="img/orders/ic_content_copy_24px_w.svg"
                                    alt=""
                                  />
                                  複製分享連結
                                </button>
                                <button
                                  type="button"
                                  class="btn btn-outline-success btn-sm action-btn"
                                >
                                  <img
                                    class="btn-icons-g"
                                    src="img/orders/ic_assignment_turned_in_24px.svg"
                                    alt=""
                                  />
                                  <img
                                    class="btn-icons-w"
                                    src="img/orders/ic_assignment_turned_in_24px_w.svg"
                                    alt=""
                                  />
                                  收單
                                </button>
                              </div>
                            </div>

                            <div class="group-buy-member-name pt-3">
                              <img src="img/orders/ic_face_24px.svg" alt="" />
                              班先生
                            </div>
                            <div class="group-buy-order-items pb-3">
                              <div class="group-buy-product mt-2">
                                <img
                                  src="https://picsum.photos/200/200"
                                  alt=""
                                />
                                <div class="ml-3">
                                  <p id="product-name">商品名稱</p>
                                  <p>數量：<span id="qty">2</span></p>
                                </div>
                              </div>
                              <div class="unit-price">$135</div>
                            </div>
                            <div class="group-buy-order-items pb-3">
                              <div class="group-buy-product mt-2">
                                <img
                                  src="https://picsum.photos/160/160"
                                  alt=""
                                />
                                <div class="ml-3">
                                  <p id="product-name">商品名稱</p>
                                  <p>數量：<span id="qty">2</span></p>
                                </div>
                              </div>
                              <div class="unit-price">$135</div>
                            </div>

                            <div class="total-cost text-right py-3">
                              總計：<span id="">270</span> 元
                            </div>

                            <div class="group-buy-member-name pt-3">
                              <img src="img/orders/ic_face_24px.svg" alt="" />
                              丁先生
                            </div>
                            <div class="group-buy-order-items pb-3">
                              <div class="group-buy-product mt-2">
                                <img
                                  src="https://picsum.photos/150/150"
                                  alt=""
                                />
                                <div class="ml-3">
                                  <p id="product-name">商品名稱</p>
                                  <p>數量：<span id="qty">2</span></p>
                                </div>
                              </div>
                              <div class="unit-price">$135</div>
                            </div>
                            <div class="total-cost text-right py-3">
                              總計：<span id="">135</span> 元
                            </div>
                          </div>
                        </div>
                      </div>

                      <!-- 以收單的團購 -->

                      <div class="card">
                        <div class="card-header" id="headingTwo">
                          <h2 class="mb-0">
                            <button
                              class="btn btn-link collapsed text-left"
                              type="button"
                              data-toggle="collapse"
                              data-target="#collapseTwo"
                              aria-expanded="false"
                              aria-controls="collapseTwo"
                            >
                              <p>
                                <span
                                  >日期：<span id="group-buy-time"
                                    >2019-10-28 （一）15:03</span
                                  >
                                  &nbsp;|&nbsp;
                                </span>
                                <span
                                  >揪團編號：<span id="group-buy-no">0261</span>
                                  &nbsp;|&nbsp;
                                </span>
                                <span
                                  >訂單編號：<span id="group-buy-order-no"
                                    >87654321</span
                                  >
                                  &nbsp;|&nbsp;
                                </span>
                                <span
                                  >出貨時間：<span id="group-buy-shipping-time"
                                    >2019-11-12 (二)</span
                                  ></span
                                >
                              </p>
                              <p>
                                <span
                                  >主揪人：<span id="group-buy-hostname"
                                    >張君雅</span
                                  >
                                  &nbsp;|&nbsp;
                                </span>
                                <span
                                  >收貨地址：<span id="group-buy-addr"
                                    >台北市忠孝東路三段一號</span
                                  ></span
                                >
                              </p>
                            </button>
                          </h2>
                        </div>
                        <div
                          id="collapseTwo"
                          class="collapse"
                          aria-labelledby="headingTwo"
                          data-parent="#accordionExample"
                        >
                          <div class="card-body p-3">
                            <div class="action-btns">
                              <div class="group-list pb-1">揪團名單</div>
                              <div class="closed">已收單</div>
                            </div>

                            <div class="group-buy-member-name pt-3">
                              <img src="img/orders/ic_face_24px.svg" alt="" />
                              班先生
                            </div>
                            <div class="group-buy-order-items pb-3">
                              <div class="group-buy-product mt-2">
                                <img
                                  src="https://picsum.photos/200/200"
                                  alt=""
                                />
                                <div class="ml-3">
                                  <p id="product-name">商品名稱</p>
                                  <p>數量：<span id="qty">2</span></p>
                                </div>
                              </div>
                              <div class="unit-price">$135</div>
                            </div>
                            <div class="group-buy-order-items pb-3">
                              <div class="group-buy-product mt-2">
                                <img
                                  src="https://picsum.photos/160/160"
                                  alt=""
                                />
                                <div class="ml-3">
                                  <p id="product-name">商品名稱</p>
                                  <p>數量：<span id="qty">2</span></p>
                                </div>
                              </div>
                              <div class="unit-price">$135</div>
                            </div>

                            <div class="total-cost text-right py-3">
                              總計：<span id="">270</span> 元
                            </div>

                            <div class="group-buy-member-name pt-3">
                              <img src="img/orders/ic_face_24px.svg" alt="" />
                              丁先生
                            </div>
                            <div class="group-buy-order-items pb-3">
                              <div class="group-buy-product mt-2">
                                <img
                                  src="https://picsum.photos/150/150"
                                  alt=""
                                />
                                <div class="ml-3">
                                  <p id="product-name">商品名稱</p>
                                  <p>數量：<span id="qty">2</span></p>
                                </div>
                              </div>
                              <div class="unit-price">$135</div>
                            </div>
                            <div class="total-cost text-right py-3">
                              總計：<span id="">135</span> 元
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
