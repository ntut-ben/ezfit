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
    <link rel="stylesheet" href="css/groupBuying_main.css" />
    <title>Group Buying</title>
  </head>
  <body>
    <!--   Header -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 大外框 -->
    <div class="container-fulid" style="height: 145vh; position: relative;">
      <div
        id="mainBody"
        class=" p-5 my-auto h-75 w-50"
        style="position: absolute; left: 25%; top: 22%; background: rgba(255,255,255,0.8);"
      >
        <div class="text-center">
          <img src="images/step1.svg" alt="" class="w-75" />
        </div>
        <!-- 小內框 -->
        <div class="container-fulid m-3">
          <form class="w-100" action="GroupBuy.do" method="POST">
            <div class="row p-2 bg-white">
              <div class="col-12 m-2 text-center main-Font-Color bg-white h2">
                建立揪團資料
              </div>
              <div class="col-12 m-1 main-Font-Color">
                <hr class="hrStyle" />
              </div>
              <!-- 群組名稱 -->
              <div class="col-12 m-2 main-Font-Color">
                <div class="m-1 h6">群組名稱</div>
                <div class="m-1">
                  <input
                    type="text"
                    value="create"
                    name="action"
                    class="d-none"
                  />
                  <input
                    name="groupName"
                    class="p-2 input-Height w-100 rounded"
                    type="text"
                    placeholder="請輸入群組名稱"
                  />
                </div>
              </div>
              <!-- 主揪姓名 -->
              <div class="col-12 m-2 main-Font-Color">
                <div class="m-1 h6">主揪姓名</div>
                <div class="m-1">
                  <input
                    name="initiatorName"
                    class="p-2 input-Height w-100 rounded"
                    type="text"
                    placeholder="請輸入主揪姓名"
                  />
                </div>
              </div>
              <!-- email -->
              <div class="col-12 m-2 main-Font-Color">
                <div class="m-1 h6">手機</div>
                <div class="m-1">
                  <input
                    name="initiatorPhone"
                    class="p-2 input-Height w-100 rounded"
                    type="tel"
                    placeholder="請輸入手機號碼"
                  />
                </div>
              </div>
              <!-- 收件人地址 -->
              <div class="col-12 m-2 main-Font-Color">
                <div class="m-1 h6">收件地址</div>
                <div class="m-1">
                  <span
                    id="twzipcode"
                    class="twzipcode main-Font-Color rounded"
                  ></span>
                </div>
                <div class="m-1">
                  <input
                    type="text"
                    name="address"
                    id="address"
                    placeholder="請輸入收件人地址"
                    class="p-2 input-Height w-100 rounded"
                  />
                </div>
              </div>

              <div class="col-12 m-2 main-Font-Color">
                <div class="m-1 h6">截止時間</div>
                <div class="m-1">
                  <select
                    name="deadLine"
                    id="deadLine"
                    class="main-Font-Color rounded"
                  ></select>
                </div>
              </div>

              <div class="col-12 text-center main-Font-Color">
                <button
                  class="btn btn-success text-white text-center w-25"
                  type="submit"
                >
                  完成開團
                </button>
              </div>
            </div>
          </form>
        </div>
        <div class="text-center">
          如已建立揪團資料，請至「訂單管理」查看明細 <br />
          <a href="">前往訂單管理</a>
        </div>
      </div>
    </div>

    <!--    Footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.15-rc1/jquery.twzipcode.min.js"></script>
    <script
      type="text/javascript"
      src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"
    ></script>
    <script src="js/groupBuying_new.js"></script>
  </body>
</html>
