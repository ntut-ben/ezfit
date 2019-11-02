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
    <link rel="stylesheet" href="css/main.css" />
    <link rel="stylesheet" href="css/work-out-plane_main.css" />
    <title>Work-out-plane</title>
  </head>
  <body>
    <!--   Header -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- Plane Select -->
    <div id="planeBody" class="container-fuild  pt-5">
      <!-- Slogn Title -->
      <div class="container py-5">
        <div class="row">
          <div class="col-12 mt-5">
            <h3 id="title" class="text-white">健身強體計畫</h3>
          </div>
          <div class="col-12  mb-5 text-white">幫助你在短時間內達成目標</div>
        </div>
      </div>
      <!-- 營養標示 -->
      <div class="container-fuild" id="nutritionBar">
        <div class="container">
          <div class="row">
            <div class="ml-auto col-4  text-white text-center">
              <p>平均營養標示</p>
              <hr class="border border-white bg-white" />
              <div class="d-flex ">
                <div id="col" class="mx-auto text-center">
                  卡路里 <br />450-650
                </div>
                <div id="protien" class="mx-auto text-center">
                  蛋白質 <br />55g
                </div>
                <div id="fat" class="mx-auto text-center">脂肪 <br />15g</div>
                <div id="carb" class="mx-auto text-center">
                  碳水化合物 <br />30g
                </div>
              </div>
            </div>
            <div class="col-2">
              <img
                id="titlePic"
                class="rounded-circle nutritionCircle"
                src="images/nutrition_workout.svg"
                alt=""
              />
            </div>
          </div>
        </div>
      </div>
      <!-- 日期選擇 -->
      <div class="container-fuild " id="dateBar">
        <div class="container">
          <div class="row">
            <form action="" id="planeForm" style="width: 100%;">
              <div class="col-12 py-4">
                <div class="mx-auto w-50 text-white">
                  請選擇配送日期 <br />
                  <select
                    name="dateForSelect"
                    id="dateForSelect"
                    class="selectStyle w-100"
                  >
                    <!-- <option value="">2019年11月18日，星期一</option> -->
                  </select>
                </div>
              </div>
              <!-- 選擇方案 -->
              <div class="col-12 py-4">
                <div class="mx-auto w-50 text-white">
                  請選擇方案 <br />
                  <select
                    name="workoutPlane"
                    id="workoutPlane"
                    class="selectStyle w-100"
                  >
                  </select>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- main -->
    <div class="container-fuild py-5" id="main">
      <div class="container p-3" id="mainBody">
        <!-- breakfast row -->
        <div id="breakfastRow" class="row px-3">
          <div class="col-6 my-3">早餐</div>
          <div class="col-6 text-right my-3">
            <button
              class="btn btn-success text-white changeMeal"
              data-meal="breakfast"
            >
              更換早餐
            </button>
          </div>

          <div class="col-12 my-3">
            <hr class="mealHr" />
          </div>
        </div>

        <!-- lunch row -->
        <div id="lunchRow" class="row px-3">
          <div class="col-6 my-3">午餐</div>
          <div class="col-6 text-right my-3">
            <button
              class="btn btn-success text-white changeMeal"
              data-meal="lunch"
            >
              更換午餐
            </button>
          </div>
          <div class="col-12 my-3">
            <hr class="mealHr" />
          </div>
        </div>

        <!-- dinner row -->
        <div id="dinnerRow" class="row px-3">
          <div class="col-6 my-3">晚餐</div>
          <div class="col-6 text-right my-3">
            <button
              class="btn btn-success text-white changeMeal"
              data-meal="dinner"
            >
              更換晚餐
            </button>
          </div>
          <div class="col-12 my-3">
            <hr class="mealHr" />
          </div>
        </div>

        <!-- check Title -->
        <div class="row font-color">
          <div class="col-12">
            <h4 class="font-weight-bold">您所選擇的商品</h4>
          </div>
          <div class="col-3 mt-3">商品</div>
          <div class="col-3 text-right  mt-3">數量</div>
          <div class="col-3 text-right  mt-3">單價</div>
          <div class="col-3 text-right  mt-3">小計</div>
          <div class="col-12">
            <hr class="checkHr " />
          </div>
        </div>

        <!-- check shipping -->
        <div id="shippingRow" class="row font-color">
          <div class="col-12">
            <hr class="mealHr " />
          </div>
        </div>

        <!-- check work out plane -->
        <div id="checkRow" class=" row font-color">
          <div class="col-12">
            <hr class="checkHr " />
          </div>
        </div>

        <!-- check summary -->
        <div class="row font-color">
          <div class="col-6 font-weight-bold">總計:</div>
          <div id="totalPrice" class="col-6 text-right"></div>
          <div class="col-12 text-right my-3">
            <button class="btn btn-success text-white btn-addCart">
              加入購物車
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
    <script src="js/work-out-plane_new.js"></script>
  </body>
</html>
