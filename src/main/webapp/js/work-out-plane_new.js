//  產品價錢
var checkPrice = $("#workoutPlane option:selected").data("price");
// 運費
var shipPrice = 90;

$(document).ready(function() {
  // 購物車按鈕
  $(".btn-addCart").click(function(e) {
    e.preventDefault();
    console.log(1);
    cart(this);
  });

  imgCircleWidth = $(".nutritionCircle").css("width");
  $(".nutritionCircle").css("height", imgCircleWidth);

  shippingFeed = 90;
  getDate();
  getPlaneProduct();

  $("#workoutPlane").change(function(e) {
    e.preventDefault();
    getFoodData();
  });

  for (let x = 0; x < $(".tabTable").length; x++) {
    const tabTableArray = $(".tabTable")[x];
    console.log($(tabTableArray).find("tr").length);
    for (let y = 0; y < $(tabTableArray).find("tr").length; y++) {
      const element = $(tabTableArray).find("tr")[y];
      if (y % 2 == 1) {
        $(element).css("background", "#FFFFFF");
      } else {
        $(element).css("background", "#F4F8F2");
      }
    }
  }
});

function getFoodData() {
  $.urlParam = function(name) {
    var results = new RegExp("[?&]" + name + "=([^&#]*)").exec(
      window.location.search
    );
    return results !== null ? results[1] || 0 : false;
  };
  plane = $.urlParam("plane");
  modifyMeal = $.urlParam("modifyMeal");

  if (modifyMeal == "true") {
    localStorage.clear();
  } else {
    // 取出之前的餐點
    if (window.localStorage.length != 0) {
      dinnerArray = JSON.parse(localStorage.getItem("saveDinner"));
      lunchArray = JSON.parse(localStorage.getItem("saveLunch"));
      breakfastArray = JSON.parse(localStorage.getItem("saveBreakfast"));
      $("#workoutPlane").val(localStorage.getItem("workoutPlane"));
      $("#dateForSelect").val(localStorage.getItem("dateForSelect"));
      main(breakfastArray, lunchArray, dinnerArray);
      localStorage.clear();
    } else {
      // generate meal data
      lunchArray = [];
      dinnerArray = [];
      breakfastArray = [];

      main(breakfastArray, lunchArray, dinnerArray);
    }
  }
}

function getDate() {
  var week;
  startDay = 2;
  dieTime = 5;
  for (let index = startDay; index < dieTime; index++) {
    day = new Date().getDay() + index;
    if (day >= 7) {
      day -= 7;
    }
    if (day == 0) week = "星期日";

    if (day == 1) week = "星期一";

    if (day == 2) week = "星期二";

    if (day == 3) week = "星期三";

    if (day == 4) week = "星期四";

    if (day == 5) week = "星期五";

    if (day == 6) week = "星期六";

    month = new Date().getMonth() + 1;
    date = new Date().getDate() + index;
    afterChecked = checkDays(date, month);
    month = afterChecked.split("|")[0];
    date = afterChecked.split("|")[1];
    year = new Date().getFullYear();
    dateForShip = `${year}年${month}月${date}日，${week}`;

    dateData = `<option data-ship="${year}-${month}-${date}" value="${year}-${month}-${date}-${week}">${dateForShip}</option>`;

    $("#dateForSelect").append(dateData);
  }
}

function checkDays(date, month) {
  switch (month) {
    case 1:
      return bigMonth(date, month);
    case 2:
      return february(date, month);
    case 3:
      return bigMonth(date, month);
    case 4:
      return smallMonth(date, month);
    case 5:
      return bigMonth(date, month);
    case 6:
      return smallMonth(date, month);
    case 7:
      return bigMonth(date, month);
    case 8:
      return bigMonth(date, month);
    case 9:
      return smallMonth(date, month);
    case 10:
      return bigMonth(date, month);
    case 11:
      return smallMonth(date, month);
    case 12:
      return bigMonth(date, month);
    default:
      break;
  }
}

function bigMonth(date, month) {
  if (date > 31) {
    month += 1;
    date -= 31;
  }
  return month + "|" + date;
}
function smallMonth(date, month) {
  if (date > 30) {
    month += 1;
    date -= 30;
  }
  return month + "|" + date;
}
function february(date, month) {
  if (date > 28) {
    month += 1;
    date -= 28;
  }
  return month + "|" + date;
}
// 取得 天數 及 餐數
function getPlane() {
  return (workoutPlane = $("#workoutPlane").val());
}
// 取得運送日期
function getOrderDate() {
  return (dateForSelect = $("#dateForSelect").val());
}
// plane的天數
function getTimeSlot(workoutPlane) {
  switch (workoutPlane) {
    case "seven":
      return 7;
      break;
    case "five":
      return 5;
      break;
    case "three":
      return 3;
      break;
    default:
      break;
  }
}

// generate meal data
function pickMain(data, timeSlot, mealArray) {
  mealArray.clear();
  while (mealArray.size < timeSlot) {
    random = Math.floor(Math.random() * data.length);
    if (data[random].meal == "main") {
      mealArray.add(data[random]);
    }
  }
  return mealArray;
}

function pickBreakfast(data, timeSlot, mealArray) {
  while (mealArray.length < timeSlot) {
    random = Math.floor(Math.random() * data.length);
    if (data[random].meal == "breakfast") {
      mealArray.push(data[random]);
    }
  }
  return mealArray;
}

function createProductElement(array) {
  appData = "";
  countDay = 0;
  for (let index = 0; index < array.length; index++) {
    fileName = array[index].fileName.split(",");
    countDay = index + 1;
    appData += `<div class="col-2 col-2-c hover_shadow" data-id="${
      array[index].id
    }" data-day="${countDay}">
第${index + 1}天
<div class="card text-center" style="width: 100%;">
<div class="imgSrc" style=" background: url(productImage/${
      fileName[0]
    }); background-size:cover;"></div>
<div class="card-body px-2 py-1">
<div class="cardTitle my-1">${array[index].name}</div>
<div class="cardBody my-1">
${array[index].introduction.substr(0, 15)}....
</div>
<div class="cardDetail my-1" data-id="${array[index].id}">詳細資訊</div>
</div>
</div>
</div>`;
  }

  return appData;
}

function getPlaneProduct() {
  $.ajax({
    type: "GET",
    cache: false,
    url: `http://localhost:8080/ezfit/PlaneProductRetrieve.do`,
    dataType: "json",
    success: function(data) {
      $("#workoutPlane")
        .children("option")
        .remove();

      planeData = "";
      data.forEach(element => {
        planeData += ` <option value="${element.shorthandOfPlane}" data-meals="${element.meals}" data-id="${element.id}" data-days="${element.days}" data-price="${element.price}">
          ${element.alias}
        </option>`;
      });

      $("#workoutPlane").append(planeData);
    },
    complete: function(xhr) {
      if (xhr.status == 200) {
        getFoodData();
      }
    }
  });
}

function getShippingFeedData() {
  shipDate = $("#dateForSelect option:selected").val();
  shipCount = 1;
  shipPrice = 90;
  shipSubTotal = shipCount * shipPrice;
  shipDateParse = shipDate.split("-");
  monthParse = shipDateParse[1];
  dateParse = shipDateParse[2];
  weekParse = shipDateParse[3];
  shipData = "";
  $("#shippingRow")
    .children(".col-3")
    .remove();
  shipData += `<div class="col-3">運費，配送時間${monthParse}/${dateParse}${weekParse}</div>
  <div class="col-3 text-right">${shipCount}</div>
  <div class="col-3 text-right">$${shipPrice}</div>
  <div class="col-3 text-right">$${shipSubTotal}</div>`;
  $("#shippingRow .col-12:last-child").before(shipData);
  totalPrice();
}

function getCheckFeedData() {
  checkData = "";
  $("#checkRow")
    .children(".col-3")
    .remove();
  checkCount = 1;
  checkPrice = $("#workoutPlane option:selected").data("price");
  checkName = $("#workoutPlane option:selected")
    .text()
    .trim()
    .split("-");
  checkName = `${checkName[0]}, ${checkName[1]}`;
  checkSubtotalPrice = checkCount * checkPrice;
  checkData = `<div class="col-3">${checkName}</div>
  <div class="col-3 text-right">${checkCount}</div>
  <div class="col-3 text-right">$${checkPrice}</div>
  <div class="col-3 text-right">$${checkSubtotalPrice}</div>`;

  $("#checkRow .col-12:last-child").before(checkData);
  totalPrice();
}

function totalPrice() {
  total = checkPrice + shipPrice;
  $("#totalPrice").text(`$${total}`);
}

function clickTab() {
  // 預設顯示第一個 Tab
  var _showTab = 0;
  var $defaultLi = $("ul.tabs li")
    .eq(_showTab)
    .parent()
    .addClass("active");
  $($defaultLi.find("a").attr("href"))
    .siblings()
    .hide();

  // 當 li 頁籤被點擊時...
  // 若要改成滑鼠移到 li 頁籤就切換時, 把 click 改成 mouseover
  $("ul.tabs li")
    .click(function() {
      // 找出 li 中的超連結 href(#id)
      var $this = $(this),
        _clickTab = $this.find("a").attr("href");
      // 把目前點擊到的 li 頁籤加上 .active
      // 並把兄弟元素中有 .active 的都移除 class
      $this
        .parent()
        .addClass("active")
        .siblings(".active")
        .removeClass("active");
      // 淡入相對應的內容並隱藏兄弟元素
      $(_clickTab)
        .stop(false, true)
        .fadeIn()
        .siblings()
        .hide();

      return false;
    })
    .find("a")
    .focus(function() {
      this.blur();
    });
}

function getSingleMealBox(id) {
  $.ajax({
    type: "POST",
    cache: false,
    url: `http://localhost:8080/ezfit/MealBox.do`,
    data: `productId=${id}`,
    dataType: "json",
    success: function(data) {
      $("#detialInfoContainer")
        .children(".detialInfo")
        .remove();

      fileName = data.fileName.split(",");
      detailData = "";
      detailData += `<!-- left product image -->
      <div class="col-6 mx-auto detialInfo">
        <img
          src="productImage/${fileName[0]}"
          alt=""
          style="width: 100%;"
        />
      </div>
      <!-- right product info -->
      <div class="col-6 mx-auto detialInfo">
        <h4 class="text-center color-838383 my-2 font-weight-bold">
          ${data.name}
        </h4>
        <p class="color-838383 my-2">
          ${data.introduction}
        </p>
        <!-- Tab select -->
        <div class="abgne_tab">
          <ul class="tabs">
            <div class="container text-center">
              <div class="row">
                <div class="col-6 tab-header tab-header-left">
                  <li>
                    <a class="targetTab" href="#tab1">營養標示</a>
                  </li>
                </div>
                <div class="col-6  tab-header tab-header-right">
                  <li>
                    <a class="targetTab" href="#tab2">安心食材</a>
                  </li>
                </div>
              </div>
            </div>
          </ul>
          <!-- 營養標示 -->
          <div class=" tab_container overflow-auto">
            <div id="tab1" class="tab_content p-3">
              <table class="mx-0 tabTable w-100">
                <tr>
                  <td class="text-left">卡路里：</td>
                  <td class="text-right">${data.calories}Cal</td>
                </tr>
                <tr>
                  <td class="text-left">蛋白質：</td>
                  <td class="text-right">${data.protein}g</td>
                </tr>
                <tr>
                  <td class="text-left">脂肪：</td>
                  <td class="text-right">${data.fat}g</td>
                </tr>
                <tr>
                  <td class="text-left">碳水化合物：</td>
                  <td class="text-right">${data.carbohydrate}g</td>
                </tr>
              </table>
            </div>
            <!-- 食材標示 -->
            <div id="tab2" class="tab_content p-3">
              <p class="mb-1">食材</p>
              <table id="materialTable" class="mx-0 tabTable w-100">
              </table>
            </div>
          </div>
        </div>
      </div>
            `;

      $("#detialInfoContainer").append(detailData);
      tableData = "";
      $("#materialTable")
        .find("tr")
        .remove();

      data.ingredientProducts.forEach(element => {
        tableData += ` 
          <tr>
            <td class="text-left">${element.name}</td>
            <td class="text-right">${element.source}</td>
          </tr>
        `;
      });

      $("#materialTable").append(tableData);
    },
    complete: function(xhr) {
      if (xhr.status == 200) {
        $("#itemModel").modal("show");
        // 啟動分頁功能 for 詳細資料
        clickTab();
      }
    }
  });
}

function main(breakfastArray, lunchArray, dinnerArray) {
  $.ajax({
    type: "POST",
    cache: false,
    url: `http://localhost:8080/ezfit/MealBox.do`,
    data: $("#planeForm").serialize() + "&plane=" + plane,
    dataType: "json",
    success: function(data) {
      $("#lunchRow")
        .children(".col-2-c")
        .remove();
      $("#breakfastRow")
        .children(".col-2-c")
        .remove();
      $("#dinnerRow")
        .children(".col-2-c")
        .remove();

      splitPlane = getPlane().split("-");
      workoutPlaneMeals = "";
      if (splitPlane.length > 2) {
        workoutPlaneMeals = splitPlane[1] + splitPlane[2];
      } else if (splitPlane.length == 2) {
        workoutPlaneMeals = splitPlane[1];
      }

      workoutPlane = splitPlane[0];
      timeSlot = getTimeSlot(workoutPlane);

      // generate random meal data
      if (
        lunchArray.length == 0 &&
        dinnerArray.length == 0 &&
        breakfastArray.length == 0
      ) {
        lunchArray = new Set();
        lunchArray = pickMain(data, timeSlot, lunchArray);
        dinnerArray = new Set();
        dinnerArray = pickMain(data, timeSlot, dinnerArray);
        breakfastArray = [];
        breakfastArray = pickBreakfast(data, timeSlot, breakfastArray);

        if (workoutPlaneMeals == "1b2a") {
          $("#breakfastRow").css("display", "");
          $("#dinnerRow").css("display", "");
          $("#lunchtRow").css("display", "");
          // append breakfast
          appData = createProductElement(breakfastArray);
          $("#breakfastRow div:last-child").before(appData);
          // append lucnh
          appData = createProductElement(Array.from(lunchArray));
          $("#lunchRow div:last-child").before(appData);
          // append dinner
          appData = createProductElement(Array.from(dinnerArray));
          $("#dinnerRow div:last-child").before(appData);
        } else if (workoutPlaneMeals == "1b1a") {
          $("#breakfastRow").css("display", "");
          $("#dinnerRow").css("display", "none");
          $("#lunchtRow").css("display", "");
          // append breakfast
          appData = createProductElement(breakfastArray);
          $("#breakfastRow div:last-child").before(appData);
          // append lucnh
          appData = createProductElement(Array.from(lunchArray));
          $("#lunchRow div:last-child").before(appData);
        } else if (workoutPlaneMeals == "2a") {
          $("#breakfastRow").css("display", "none");
          $("#dinnerRow").css("display", "");
          $("#lunchtRow").css("display", "");
          // append lucnh
          appData = createProductElement(Array.from(lunchArray));
          $("#lunchRow div:last-child").before(appData);
          // append dinner
          appData = createProductElement(Array.from(dinnerArray));
          $("#dinnerRow div:last-child").before(appData);
        }
      } else {
        var days = $("#workoutPlane option:selected").data("days");
        newBreakfastArray = [];
        newLunchArray = [];
        newDinnerArray = [];
        console.log(days);
        for (let index = 0; index < days; index++) {
          data.forEach(element => {
            if (element.id == breakfastArray[index]) {
              newBreakfastArray.push(element);
            }
            if (lunchArray[index] == element.id) {
              newLunchArray.push(element);
            }
            if (dinnerArray[index] == element.id) {
              newDinnerArray.push(element);
            }
          });
        }

        if (workoutPlaneMeals == "1b2a") {
          $("#breakfastRow").css("display", "");
          $("#dinnerRow").css("display", "");
          $("#lunchtRow").css("display", "");
          // append breakfast
          appData = createProductElement(newBreakfastArray);
          $("#breakfastRow div:last-child").before(appData);
          // append lucnh
          appData = createProductElement(Array.from(newLunchArray));
          $("#lunchRow div:last-child").before(appData);
          // append dinner
          appData = createProductElement(Array.from(newDinnerArray));
          $("#dinnerRow div:last-child").before(appData);
        } else if (workoutPlaneMeals == "1b1a") {
          $("#breakfastRow").css("display", "");
          $("#dinnerRow").css("display", "none");
          $("#lunchtRow").css("display", "");
          // append breakfast
          appData = createProductElement(newBreakfastArray);
          $("#breakfastRow div:last-child").before(appData);
          // append lucnh
          appData = createProductElement(Array.from(newLunchArray));
          $("#lunchRow div:last-child").before(appData);
        } else if (workoutPlaneMeals == "2a") {
          $("#breakfastRow").css("display", "none");
          $("#dinnerRow").css("display", "");
          $("#lunchtRow").css("display", "");
          // append lucnh
          appData = createProductElement(Array.from(newLunchArray));
          $("#lunchRow div:last-child").before(appData);
          // append dinner
          appData = createProductElement(Array.from(newDinnerArray));
          $("#dinnerRow div:last-child").before(appData);
        }
      }
    },
    complete: function(XMLHttpRequest, textStatus) {
      // 圓形圖
      imgWidth = $(".imgSrc").css("width");
      $(".imgSrc").css("height", imgWidth);

      // 依據日期產生運費
      getShippingFeedData();
      $("#dateForSelect").change(function(e) {
        e.preventDefault();
        getShippingFeedData();
      });

      // 依據計畫產生運費
      getCheckFeedData();
      $("#checkRow").change(function(e) {
        e.preventDefault();
        getCheckFeedData();
      });

      // card 詳細資料 hover 效果
      $(".cardDetail").hover(
        function() {
          $(this).css("cursor", "pointer");
          $(this).css("color", "#2EAA00");
        },
        function() {
          $(this).css("cursor", "auto");
          $(this).css("color", "");
        }
      );
      // show 產品資訊
      $(".cardDetail").click(function(e) {
        e.preventDefault();
        id = $(this).data("id");
        getSingleMealBox(id);
      });

      // 更換產品資訊
      $(".changeMeal").click(function(e) {
        e.preventDefault();

        breakfast = $("#breakfastRow").find(".col-2-c");
        lunch = $("#lunchRow").find(".col-2-c");
        dinner = $("#dinnerRow").find(".col-2-c");
        breakfasts = [];
        lunches = [];
        dinners = [];

        Array.from(breakfast).forEach(element => {
          breakfasts.push($(element).data("id"));
        });
        Array.from(lunch).forEach(element => {
          lunches.push($(element).data("id"));
        });
        Array.from(dinner).forEach(element => {
          dinners.push($(element).data("id"));
        });
        // 將原本資料儲存在本地storage
        localStorage.setItem("saveBreakfast", JSON.stringify(breakfasts));
        localStorage.setItem("saveLunch", JSON.stringify(lunches));
        localStorage.setItem("saveDinner", JSON.stringify(dinners));
        localStorage.setItem("dateForSelect", $("#dateForSelect").val());
        localStorage.setItem(
          "workoutPlane",
          $("#workoutPlane option:selected").val()
        );

        parentRow = $(this).parents(".row");
        meal = $(this).data("meal");
        meals = $("#workoutPlane option:selected").data("meals");
        days = $("#workoutPlane option:selected").data("days");
        href = `modifyMeals.jsp?plane=${plane}&modifyMeal=true&meal=${meal}&meals=${workoutPlaneMeals}&shipDate=${shipDate}&days=${days}`;
        whichDay = "";
        for (let index = 0; index < days; index++) {
          switch (index) {
            case 0:
              whichDay = "first";
              break;
            case 1:
              whichDay = "second";
              break;
            case 2:
              whichDay = "third";
              break;
            case 3:
              whichDay = "fourth";
              break;
            case 4:
              whichDay = "fivth";
              break;
            case 5:
              whichDay = "sixth";
              break;
            case 6:
              whichDay = "seventh";
              break;
            default:
              break;
          }

          record = $(parentRow).find(".col-2-c")[index];
          href += `&${whichDay}=${$(record).data("id")}`;
        }
        window.location.href = href;
      });
    }
  });
}

// 購物車功能
function cart(cartBtn) {
  breakfast = $("#breakfastRow").find(".col-2-c");
  lunch = $("#lunchRow").find(".col-2-c");
  dinner = $("#dinnerRow").find(".col-2-c");

  // Array.from(breakfast).forEach(element => {
  //   breakfasts.push($(element).data("id"));
  // });
  breakfasts = [];
  lunches = [];
  dinners = [];

  Array.from(breakfast).forEach(element => {
    breakfasts.push($(element).data("id"));
  });
  Array.from(lunch).forEach(element => {
    lunches.push($(element).data("id"));
  });
  Array.from(dinner).forEach(element => {
    dinners.push($(element).data("id"));
  });

  var json1 = {
    breakfasts: breakfasts,
    dinners: dinners,
    lunches: lunches,
    action: ["planeAdd"],
    productId: [$("#workoutPlane option:selected").data("id")],
    ship: [$("#dateForSelect option:selected").data("ship")]
  };

  $.ajax({
    type: "POST",
    url: "http://localhost:8080/ezfit/ShopCart.do?",
    contentType: "application/json;charset=utf-8",
    cache: false,
    data: JSON.stringify(json1),
    dataType: "json",
    beforeSend: function() {
      $(".btn-addCart").attr("disabled", true);
    },
    complete: function(xhr) {
      $(".btn-addCart").attr("disabled", false);
      location.href = "http://localhost:8080/ezfit/cartList.html";
    }
  });
}
