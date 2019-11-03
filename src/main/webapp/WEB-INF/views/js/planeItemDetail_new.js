$(document).ready(function() {
  imgCircleWidth = $(".nutritionCircle").css("width");
  $(".nutritionCircle").css("height", imgCircleWidth);

  getFoodData();
  console.log(123);
  $(".btn-Back").click(function(e) {
    e.preventDefault();
    history.back();
  });
});

function getFoodData() {
  $.urlParam = function(name) {
    var results = new RegExp("[?&]" + name + "=([^&#]*)").exec(
      window.location.search
    );
    return results !== null ? results[1] || 0 : false;
  };

  cartItemId = $.urlParam("cartItemId");
  orderItemId = $.urlParam("orderItemId");

  if (orderItemId != false) {
    targetURL = `http://localhost:8080/ezfit/api/planeDetail/order/${orderItemId}`;
  } else if (cartItemId != false) {
    targetURL = `http://localhost:8080/ezfit/api/planeDetail/cart/${cartItemId}`;
  }

  $.ajax({
    type: "GET",
    url: targetURL,
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

      console.log(data.planeItems[0].cuisineProduct.productCategory);
      plane = data.planeItems[0].cuisineProduct.productCategory.category;

      if (plane == "muscle") {
        $("#planeBody").addClass("planeBody-muscle");
        $("#titlePic").attr("src", "images/nutrition_workout.svg");
        $("#title").text("健身強體計畫");
        $("#col").html("卡路里 <br />450-650");
        $("#protien").html("蛋白質 <br />55g");
        $("#fat").html("脂肪 <br />15g");
        $("#carb").html("碳水化合物 <br />30g");
      } else if (plane == "fit") {
        $("#planeBody").addClass("planeBody-fit");
        $("#titlePic").attr("src", "images/nutrition_slim.svg");
        $("#title").text("輕盈窈窕計畫");
        $("#col").html("卡路里 <br />300-400");
        $("#protien").html("蛋白質 <br />30g");
        $("#fat").html("脂肪 <br />7g");
        $("#carb").html("碳水化合物 <br />17g");
      } else if (plane == "keep") {
        $("#planeBody").addClass("planeBody-keep");
        $("#titlePic").attr("src", "images/nutrition_well_bing.svg");
        $("#title").text("健康營養計畫");
        $("#col").html("卡路里 <br />350-650");
        $("#protien").html("蛋白質 <br />45g");
        $("#fat").html("脂肪 <br />10g");
        $("#carb").html("碳水化合物 <br />25g");
      }

      lunchArray = [];
      dinnerArray = [];
      breakfastArray = [];
      splitPlane = data.product.shorthandOfPlane.split("-");
      workoutPlaneMeals = "";
      if (splitPlane.length > 2) {
        workoutPlaneMeals = splitPlane[1] + splitPlane[2];
      } else if (splitPlane.length == 2) {
        workoutPlaneMeals = splitPlane[1];
      }

      productMeals = data.product.meals;
      productDays = data.product.days;

      if (workoutPlaneMeals == "1b2a") {
        $("#breakfastRow").css("display", "");
        $("#dinnerRow").css("display", "");
        $("#lunchtRow").css("display", "");

        for (let meals = 1; meals <= productMeals; meals++) {
          for (
            let index = (meals - 1) * productDays;
            index < meals * productDays;
            index++
          ) {
            switch (meals) {
              case 1:
                breakfastArray.push(data.planeItems[index]);
                break;
              case 2:
                lunchArray.push(data.planeItems[index]);
                break;
              case 3:
                dinnerArray.push(data.planeItems[index]);
                break;
              default:
                break;
            }
          }
        }
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
        for (let meals = 1; meals <= productMeals; meals++) {
          for (
            let index = (meals - 1) * productDays;
            index < meals * productDays;
            index++
          ) {
            switch (meals) {
              case 1:
                breakfastArray.push(data.planeItems[index]);
                break;
              case 2:
                lunchArray.push(data.planeItems[index]);
                break;
              default:
                break;
            }
          }
        }
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
        // 將餐點放進對應的陣列
        for (let meals = 1; meals <= productMeals; meals++) {
          for (
            let index = (meals - 1) * productDays;
            index < meals * productDays;
            index++
          ) {
            switch (meals) {
              case 1:
                lunchArray.push(data.planeItems[index]);
                break;
              case 2:
                dinnerArray.push(data.planeItems[index]);
                break;
              default:
                break;
            }
          }
        }
        // append lucnh
        appData = createProductElement(lunchArray);
        $("#lunchRow div:last-child").before(appData);
        // append dinner
        appData = createProductElement(dinnerArray);
        $("#dinnerRow div:last-child").before(appData);
      }
    },
    complete: function(xhr) {
      imgWidth = $(".imgSrc").css("width");
      $(".imgSrc").css("height", imgWidth);

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
    }
  });
}

function createProductElement(array) {
  appData = "";
  countDay = 0;
  for (let index = 0; index < array.length; index++) {
    fileName = array[index].cuisineProduct.fileName.split(",");
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
<div class="cardTitle my-1">${array[index].cuisineProduct.name}</div>
<div class="cardBody my-1">
${array[index].cuisineProduct.introduction.substr(0, 15)}....
</div>
<div class="cardDetail my-1" data-id="${
      array[index].cuisineProduct.id
    }">詳細資訊</div>
</div>
</div>
</div>`;
  }

  return appData;
}

function getSingleMealBox(id) {
  $.ajax({
    type: "POST",
    cache: false,
    url: `http://localhost:8080/ezfit/api/mealBox/${id}/single`,
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
