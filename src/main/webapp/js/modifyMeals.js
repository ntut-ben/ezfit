var plane;
var meals;
var meal;
var days;
var modifyMeal;
var mealArray = [];

$(document).ready(function() {
  imgWidth = $(".imgSrc").css("width");
  $(".imgSrc").css("height", imgWidth);

  $.urlParam = function(name) {
    var results = new RegExp("[?&]" + name + "=([^&#]*)").exec(
      window.location.search
    );
    return results !== null ? results[1] || 0 : false;
  };
  // 取出URL參數
  plane = $.urlParam("plane");
  meals = $.urlParam("meals");
  meal = $.urlParam("meal");
  days = $.urlParam("days");
  modifyMeal = $.urlParam("modifyMeal");
  for (let index = 0; index < parseInt(days); index++) {
    switch (index) {
      case 0:
        mealArray.push($.urlParam("first"));
        break;
      case 1:
        mealArray.push($.urlParam("second"));
        break;
      case 2:
        mealArray.push($.urlParam("third"));
        break;
      case 3:
        mealArray.push($.urlParam("fourth"));
        break;
      case 4:
        mealArray.push($.urlParam("fivth"));
        break;
      case 5:
        mealArray.push($.urlParam("sixth"));
        break;
      case 6:
        mealArray.push($.urlParam("seventh"));
        break;
      default:
        break;
    }
  }
  // 設定標題
  if (meal == "breakfast") {
    $("#titleHeader").html("更換早餐");
    mealRename = "breakfast";
  } else if (meal == "lunch") {
    $("#titleHeader").html("更換午餐");
    mealRename = "main";
  } else if (meal == "dinner") {
    $("#titleHeader").html("更換晚餐");
    mealRename = "main";
  }

  getData();
});

function getData() {
  $.ajax({
    type: "POST",
    cache: false,
    url: `http://localhost:8080/ezfit/MealBox.do`,
    data: { plane: plane, modifyMeal: modifyMeal },
    dataType: "json",
    success: function(data) {
      $("#chooseRow")
        .children()
        .remove();
      mealboxRow = [];
      mealData = "";
      // 依據類別取出餐點
      data.forEach(element => {
        if (element.meal == mealRename) {
          mealboxRow.push(element);
        }
      });
      // 動態新增
      for (let index = 0; index < days; index++) {
        // 產生title
        mealData += `<div class="col-12 day my-2"><h5>第${index +
          1}天</h5></div>
          <div class="col-12 px-3">
          <div class="multiple-items text-center color-838383">`;

        // 產生餐點列表
        mealboxRow.forEach(element => {
          fileName = element.fileName.split(",");
          // 判斷哪個餐點先前被選擇
          if (element.id == mealArray[index]) {
            mealData += `
            <div class=" cardItem selected m-5" data-id="${element.id}" style=" width: 185px;">`;
          } else {
            mealData += `
          <div class=" cardItem m-5" data-id="${element.id}" style=" width: 185px;">`;
          }
          // 餐點資訊
          mealData += `
        <img class="productImg" src="productImage/${
          fileName[0]
        }" alt="" style="width:100%" />
        <h5 class="my-1">${element.name}</h5>
        <p class="my-1 cardBody">
        ${element.introduction.substr(0, 15)}....
        </p>
        <div class="my-1 cardDetail" data-id="${element.id}">詳細資訊</div>
      </div>`;
        });
        // 水平線
        mealData += `
        </div>
        </div>
        <div class="col-12 my-2"><hr class="mealHr" /></div>`;
      }
      $("#chooseRow").append(mealData);
    },
    complete: function(xhr) {
      //   輪播功能
      $(".multiple-items").slick({
        // variableWidth : true,
        infinite: false,
        slidesToShow: 4,
        slidesToScroll: 1,
        prevArrow: `<i class='far fa-arrow-alt-circle-left prevArrowBtn' style="font-size:2rem;"></i>`,
        nextArrow: `<i class="far fa-arrow-alt-circle-right nextArrowBtn" style="font-size:2rem;"></i>`
      });

      //   箭頭效果
      $(".slick-arrow").hover(
        function() {
          console.log(123);
          $(this).css("cursor", "pointer");
          $(this).css("color", "#439A23");
        },
        function() {
          $(this).css("cursor", "auto");
          $(this).css("color", "#C9E6BF");
        }
      );

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
        e.stopPropagation();
        id = $(this).data("id");
        console.log(this);
        console.log(id);
        getSingleMealBox(id);
      });

      // card 容器
      $(".cardItem").hover(
        function() {
          $(this).css("cursor", "pointer");
        },
        function() {
          $(this).css("cursor", "auto");
        }
      );

      $(".cardItem").click(function(e) {
        e.preventDefault();
        $(this).addClass("selected");
        $(this)
          .siblings()
          .removeClass("selected");
      });

      updateId = [];
      // 送出更新後的餐點
      $("#submitChange").click(function(e) {
        Array.from($(".selected")).forEach(element => {
          updateId.push($(element).data("id"));
        });
        // update data to localStorage
        if (meal == "lunch") {
          localStorage.removeItem("saveLunch");
          localStorage.setItem("saveLunch", JSON.stringify(updateId));
        } else if (meal == "dinner") {
          localStorage.removeItem("saveDinner");
          localStorage.setItem("saveDinner", JSON.stringify(updateId));
        } else if (meal == "breakfast") {
          localStorage.removeItem("saveBreakfast");
          localStorage.setItem("saveBreakfast", JSON.stringify(updateId));
        }
        href = `http://localhost:8080/ezfit/work-out-plane.jsp?plane=${plane}`;
        window.location.href = href;
      });

      // 取消更改餐點
      $("#cancleChange").click(function(e) {
        href = `http://localhost:8080/ezfit/work-out-plane.jsp?plane=${plane}`;
        window.location.href = href;
      });

      imgWidth = $(".productImg").css("width");
      $(".productImg").css("height", imgWidth);
    }
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
      console.log(data);
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
            <div id="tab2" class="tab_content p-2">
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
