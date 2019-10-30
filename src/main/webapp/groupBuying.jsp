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
    <div
      id="mainContainer"
      class="container-fulid"
      style=" position: relative;"
    >
      <div
        id="mainBody"
        class=" p-5 my-auto h-75 w-50"
        style="position: absolute; left: 25%; top: 22%; background: rgba(255,255,255,0.8);"
      ></div>
    </div>

    <!--    Footer -->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- copyInfo Modal -->
    <div
      class="modal fade"
      id="itemModel"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content w-50 mx-auto">
          <div id="itemDetail" class="modal-body">
            <div class="container">
              <div class="col-12">
                <div class="w-50 mx-auto my-1">
                  <img src="images/logo.png" alt="" class="w-100" />
                </div>
              </div>
              <div class="col-12 text-center">複製成功</div>
            </div>
          </div>
        </div>
      </div>
    </div>

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
