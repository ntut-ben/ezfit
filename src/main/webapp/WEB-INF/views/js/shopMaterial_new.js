groupAlias = "";
$(document).ready(function() {
  // if (category == false) {
  // window.location.replace("http://stackoverflow.com");
  // }

  getData();
  $(".serarchBarBtn").click(function(e) {
    e.preventDefault();
    search($(this).parents("#searchForm"));
  });
  // Ajax 取得所有商品
  function getData() {
    // 取出Parameter
    $.urlParam = function(name) {
      var results = new RegExp("[?&]" + name + "=([^&#]*)").exec(
        window.location.search
      );

      return results !== null ? results[1] || 0 : false;
    };
    category = $.urlParam("category"); // 取出分類
    page = $.urlParam("page");
    group = $.urlParam("group");
    searchString = $.urlParam("search");
    var pages = 0;
    // 清空頁面
    $("#listProduct").empty();
    // 頁面初始值
    if (category == false) {
      category = "all";
    }
    if (page == false) {
      page = "1";
    }
    setQueryStringParameter("category", category);
    setQueryStringParameter("page", page);

    if ((group != false) & (group != null)) {
      $.ajax({
        type: "GET",
        url: `http://localhost:8080/ezfit/api/GroupBuy/query/${group}`,
        dataType: "json",
        success: function(data) {
          if (data.valid == "false") {
          } else {
            deadLine = data.deadLine;
            deadLine = deadLine.replace("月", ",");
            deadLine = deadLine.split(",");
            deadLine = `${deadLine[2].trim()}-${deadLine[0].trim()}-${deadLine[1].trim()}`;

            $("#groupContainer").css("visibility", "unset");
            $("#groupName").html(data.groupName);
            $("#groupDeadLine").html(`團購結單日期 : ${deadLine}`);
            groupAlias = group;

            $("#groupShareBtn").click(function(e) {
              e.preventDefault();
              window.location.href = `groupBuying?group=${group}`;
            });
          }
        }
      });
    }

    $.ajax({
      type: "get",
      url: `http://localhost:8080/ezfit/api/ingredients/category/${category}`,

      dataType: "json",
      success: function(data) {
        // 計算頁碼
        let numbers = 12;
        pages = Math.floor(data.length / numbers);
        if (data.length % numbers != 0) {
          pages += 1;
        }
        $("#page").html("");

        pageData = `<li id="pagePrevious" class="page-item">
        <a class="page-link" href="" aria-label="Previous">
          <span aria-hidden="true">&laquo;</span>
          <span class="sr-only">Previous</span>
        </a>
      </li>`;

        // 產生頁碼
        for (let index = 1; index <= pages; index++) {
          if (index == parseInt(page)) {
            pageData += `<li class="page-item active disabled">`;
          } else {
            pageData += `<li class="page-item ">`;
          }
          pageData += `<a  class="page-link pageNumber" href="" data-page=${index}>${index}</a>`;
          pageData += `</li>`;
        }

        pageData += ` <li id="pageNext" class="page-item disabled">
        <a class="page-link" href="" aria-label="Next">
          <span aria-hidden="true">&raquo;</span>
          <span class="sr-only">Next</span>
        </a>
      </li>`;

        $("#page").append(pageData);

        // 根據頁碼產生商品資料
        let lastItem = page * numbers;
        let firstItem = page * numbers - numbers;
        if (lastItem > data.length) {
          lastItem = data.length;
        }

        // 產生分類標題
        typeName = categoryName(category);
        if (typeName == 404) {
        } else {
          headerData = `<div class="col-12 my-3"><span class="border-bottom-success font-size-2rem letter-spacing-015rem">${typeName}</span></div>`;
          $("#listProduct").append(headerData);
        }

        for (let index = firstItem; index < lastItem; index++) {
          fileName = data[index].fileName.split(",");
          let apiData = ` <div class="col-3 my-3">`;
          apiData += `<div data-itemId=${data[index].id} class="card" style="width: 100%;">`;
          apiData += `<div class="popupCard">`;
          apiData += `<span style="display: none;">${data[index].id}</span>`;
          apiData += `<img src="productImage/${
            fileName[0]
          }" class="card-img-top" alt=""/>`;
          apiData += ` </div>`;
          apiData += ` <div class="card-body p-0">`;
          apiData += ` <div class="popupCard" >`;
          apiData += `<span style="display: none;">${data[index].id}</span>`;
          apiData += `<h6 class="card-text m-0 m-2">${data[index].name}</h6>`;
          apiData += `</div>`;
          apiData += `<p class="card-text font-size-08rem m-0  m-2">${data[index].unit}</p>`;
          apiData += `<p class="card-text m-0  m-2">$${data[index].price}</p>`;
          apiData += `</div>`;
          apiData += `<form action=http://localhost:8080/ezfit/api/shopCart/ class="text-right" method="POST">

          <input type="text" name="category" value="IngredientProduct" style="display: none;"/>
          <input type="text" name="itemId" value="${data[index].id}" style="display: none;"/>
          <input type="number" id="quantity" name="quantity" min="1" max="${data[index].stock}" value="1" class="text-center w-25 m-1 font-size-08rem"/>
               <button
                 class="text-white font-size-08rem m-1 btn btn-success letter-spacing-015rem p-1 cartAdd"
             type="button">
                 <i class="fas fa-shopping-cart"></i>加入購物車
                 </button> 
                 </form>`;

          apiData += `</div>`;
          apiData += `</div>`;

          $("#listProduct").append(apiData);
        }
      },
      complete: function(xhr) {
        // Page 點擊顯示
        if (xhr.status == 200) {
          imgWidth = $(".card-img-top").css("width");
          $(".card-img-top").css("height", imgWidth);

          // 判斷當前頁碼，決定是否開啟上一頁、下一頁
          if (page == 1) {
            $("#pagePrevious").addClass("disabled");
          } else {
            $("#pagePrevious").removeClass("disabled");
          }

          if (page == pages) {
            $("#pageNext").addClass("disabled");
          } else {
            $("#pageNext").removeClass("disabled");
          }
          // 上一頁
          $("#pagePrevious").click(function(e) {
            e.preventDefault();
            if (page != 1) {
              page -= 1;
              setQueryStringParameter("page", page);
              getData();
            }
          });
          // 下一頁
          $("#pageNext").click(function(e) {
            e.preventDefault();
            if (page != pages) {
              page = parseInt(page) + 1;
              setQueryStringParameter("page", page);
              getData();
            }
          });
          // 點擊頁碼
          $(".pageNumber").click(function(e) {
            e.preventDefault();
            page = $(this).data("page");
            setQueryStringParameter("page", page);
            getData();
          });
          // 滑鼠 move to card
          $(".popupCard").hover(
            function() {
              $(this).css("cursor", "pointer");
            },
            function() {
              $(this).css("cursor", "auto");
            }
          );
          // 滑鼠 click card
          $(".popupCard").click(function(e) {
            e.preventDefault();
            itemId = $(this)
              .children("span:first")
              .text();

            $.ajax({
              type: "GET",
              url: `http://localhost:8080/ezfit/api/ingredients/productDetail/${itemId}`,
              dataType: "json",
              success: function(response) {
                // modal itemDetail
                fileName = response.fileName.split(",");

                $("#itemDetail").html("");

                renderIntro = response.introduction.replace(
                  /(\r?\\n)/g,
                  "<br>"
                );

                console.log(renderIntro);
                itemData = ` <div class="container">
<div class="row">
  <div class="col-12">
    <button
      type="button"
      class="close"
      data-dismiss="modal"
      aria-label="Close"
    >
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="col-4 mx-auto">
    <img
      src="productImage/${fileName[0]}"
      alt=""
      style="width: 100%;"
    />
  </div>
  <div class="col-8 mx-auto">
    <h4 class="cardFontColor">${response.name}</h4>
    <p class="m-0 cardFontColor font-size-08rem">
      容量包裝：${response.unit}
    </p>
    <p class="m-0 cardFontColor font-size-08rem">
      安全等級：${response.certification}
    </p>
    <p class="cardFontColor font-size-08rem">來源：${response.source}</p>
    <p class="cardFontColor font-size-08rem">
    ${renderIntro}
    </p>
    <p class="cardFontColor font-size-08rem">
      建議保存方式： ${response.storage}
    </p>
    <form action="http://localhost:8080/ezfit/api/shopCart/" method="POST" class="text-right ">
    <input type="text" name="action" value="add" style="display: none;"/>
    <input type="text" name="category" value="IngredientProduct" style="display: none;"/>
    <input type="text" name="itemId" value="${
      response.id
    }" style="display: none;"/>
      數量 : <input
        type="number"
        id="quantity"
        name="quantity"
        min="1"
        max=${response.stock}
        value="1"
        class="text-center m-1 font-size-08rem"
        style="width: 50px;"
      />
      <button
        class="text-white font-size-08rem m-1 btn btn-success letter-spacing-015rem p-1 cartAdd"
      type="button">
        <i class="fas fa-shopping-cart"></i>加入購物車
      </button>
    </form>
  </div>

  <div class="col-12 text-center my-3">食譜推薦</div>
  <div class="col-12">
    <div class="container">
      <div class="row">
        <div class="col-3 mx-auto">食譜-1</div>
        <div class="col-3 mx-auto">食譜-2</div>
        <div class="col-3 mx-auto">食譜-3</div>
      </div>
    </div>
  </div>
</div>
</div>`;

                $("#itemDetail").append(itemData);
                $("#itemModel").modal("show");
              },
              // modal cart function
              complete: function(params) {
                $(".cartAdd").click(function(e) {
                  e.preventDefault();
                  cart(this);
                });
              }
            });
          });
        }
        // productlist cart function
        $(".cartAdd").click(function(e) {
          e.preventDefault();
          cart(this);
        });

        $(".card-img-top").css("height", $(".popupCard").css("width"));
      },
      error: function(xhr) {
        $("#listProduct").html("<h1>404 page not found</h1>");
      }
    });
  }

  function categoryName(typeName) {
    switch (typeName) {
      case "fruit":
        return "水果";
        break;

      case "vegetable":
        return "蔬菜";
        break;

      case "brute":
        return "紅肉";
        break;

      case "poultry":
        return "家禽";
        break;

      case "seafood":
        return "海鮮";
        break;

      case "rice":
        return "米麵雜糧";
        break;

      case "egg":
        return "蛋奶豆製品";
        break;

      case "all":
        return "健康食材";
        break;

      default:
        $("#listProduct").html("<h1>404 page not found</h1>");
        return "404";
        break;
    }
  }

  // 點擊分類
  $(".selectCate").click(function(e) {
    e.preventDefault();
    page = $(this).data("page");
    category = $(this).data("category");
    setQueryStringParameter("page", page);
    setQueryStringParameter("category", category);
    getData();
  });
});

function search(form) {
  $.ajax({
    type: "POST",
    url: `http://localhost:8080/ezfit/api/search`,
    data: $(form).serialize(),
    dataType: "json",
    success: function(data) {
      console.log(data);
      $("#listProduct").empty();
      page = 1;
      console.log(data);
      if (data != null) {
        // 計算頁碼
        let numbers = 12;
        pages = Math.floor(data.length / numbers);
        if (data.length % numbers != 0) {
          pages += 1;
        }
        $("#page").html("");

        pageData = `<li id="pagePrevious" class="page-item disabled">
      <a class="page-link" href="" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a>
    </li>`;

        // 產生頁碼
        for (let index = 1; index <= pages; index++) {
          if (index == parseInt(page)) {
            pageData += `<li class="page-item active disabled">`;
          } else {
            pageData += `<li class="page-item ">`;
          }
          pageData += `<a  class="page-link pageNumber" href="" data-page=${index}>${index}</a>`;
          pageData += `</li>`;
        }

        pageData += ` <li id="pageNext" class="page-item disabled">
      <a class="page-link" href="" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Next</span>
      </a>
    </li>`;

        $("#page").append(pageData);

        // 根據頁碼產生商品資料
        let lastItem = page * numbers;
        let firstItem = page * numbers - numbers;
        if (lastItem > data.length) {
          lastItem = data.length;
        }

        // 產生分類標題
        typeName = "健康食材";
        if (typeName == 404) {
        } else {
          headerData = `<div class="col-12 my-3"><span class="border-bottom-success font-size-2rem letter-spacing-015rem">${typeName}</span></div>`;
          $("#listProduct").append(headerData);
        }

        for (let index = firstItem; index < lastItem; index++) {
          fileName = data[index].fileName.split(",");
          let apiData = ` <div class="col-3 my-3">`;
          apiData += `<div data-itemId=${data[index].id} class="card" style="width: 100%;">`;
          apiData += `<div class="popupCard">`;
          apiData += `<span style="display: none;">${data[index].id}</span>`;
          apiData += `<img src="productImage/${
            fileName[0]
          }" class="card-img-top" alt=""/>`;
          apiData += ` </div>`;
          apiData += ` <div class="card-body p-0">`;
          apiData += ` <div class="popupCard" >`;
          apiData += `<span style="display: none;">${data[index].id}</span>`;
          apiData += `<h6 class="card-text m-0 m-2">${data[index].name}</h6>`;
          apiData += `</div>`;
          apiData += `<p class="card-text font-size-08rem m-0  m-2">${data[index].unit}</p>`;
          apiData += `<p class="card-text m-0  m-2">$${data[index].price}</p>`;
          apiData += `</div>`;
          apiData += `<form action=http://localhost:8080/ezfit/api/shopCart/ class="text-right" method="POST">

        <input type="text" name="category" value="IngredientProduct" style="display: none;"/>
        <input type="text" name="itemId" value="${data[index].id}" style="display: none;"/>
        <input type="number" id="quantity" name="quantity" min="1" max="${data[index].stock}" value="1" class="text-center w-25 m-1 font-size-08rem"/>
             <button
               class="text-white font-size-08rem m-1 btn btn-success letter-spacing-015rem p-1 cartAdd"
           type="button">
               <i class="fas fa-shopping-cart"></i>加入購物車
               </button> 
               </form>`;

          apiData += `</div>`;
          apiData += `</div>`;

          $("#listProduct").append(apiData);

          setQueryStringParameter("category", category);
          setQueryStringParameter("page", page);
          setQueryStringParameter(
            "search",
            $(form)
              .serialize()
              .split("=")[1]
          );
        }
      } else {
        apiData = `<h1> Sorry ， 沒有相關結果 </h1>`;
        $("#listProduct").append(apiData);
        $("#page").empty();
      }
    },
    complete: function(xhr) {
      if (xhr.status == 200) {
        imgWidth = $(".card-img-top").css("width");
        $(".card-img-top").css("height", imgWidth);

        // 滑鼠 move to card
        $(".popupCard").hover(
          function() {
            $(this).css("cursor", "pointer");
          },
          function() {
            $(this).css("cursor", "auto");
          }
        );
        // 滑鼠 click card
        $(".popupCard").click(function(e) {
          e.preventDefault();
          itemId = $(this)
            .children("span:first")
            .text();

          $.ajax({
            type: "GET",
            url: `http://localhost:8080/ezfit/api/ingredients/productDetail/${itemId}`,
            dataType: "json",
            success: function(response) {
              // modal itemDetail
              fileName = response.fileName.split(",");

              $("#itemDetail").html("");

              renderIntro = response.introduction.replace(/(\r?\\n)/g, "<br>");

              console.log(renderIntro);
              itemData = ` <div class="container">
<div class="row">
  <div class="col-12">
    <button
      type="button"
      class="close"
      data-dismiss="modal"
      aria-label="Close"
    >
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="col-4 mx-auto">
    <img
      src="productImage/${fileName[0]}"
      alt=""
      style="width: 100%;"
    />
  </div>
  <div class="col-8 mx-auto">
    <h4 class="cardFontColor">${response.name}</h4>
    <p class="m-0 cardFontColor font-size-08rem">
      容量包裝：${response.unit}
    </p>
    <p class="m-0 cardFontColor font-size-08rem">
      安全等級：${response.certification}
    </p>
    <p class="cardFontColor font-size-08rem">來源：${response.source}</p>
    <p class="cardFontColor font-size-08rem">
    ${renderIntro}
    </p>
    <p class="cardFontColor font-size-08rem">
      建議保存方式： ${response.storage}
    </p>
    <form action="http://localhost:8080/ezfit/api/shopCart/" method="POST" class="text-right ">
    <input type="text" name="action" value="add" style="display: none;"/>
    <input type="text" name="category" value="IngredientProduct" style="display: none;"/>
    <input type="text" name="itemId" value="${
      response.id
    }" style="display: none;"/>
      數量 : <input
        type="number"
        id="quantity"
        name="quantity"
        min="1"
        max=${response.stock}
        value="1"
        class="text-center m-1 font-size-08rem"
        style="width: 50px;"
      />
      <button
        class="text-white font-size-08rem m-1 btn btn-success letter-spacing-015rem p-1 cartAdd"
      type="button">
        <i class="fas fa-shopping-cart"></i>加入購物車
      </button>
    </form>
  </div>

  <div class="col-12 text-center my-3">食譜推薦</div>
  <div class="col-12">
    <div class="container">
      <div class="row">
        <div class="col-3 mx-auto">食譜-1</div>
        <div class="col-3 mx-auto">食譜-2</div>
        <div class="col-3 mx-auto">食譜-3</div>
      </div>
    </div>
  </div>
</div>
</div>`;

              $("#itemDetail").append(itemData);
              $("#itemModel").modal("show");
            },
            // modal cart function
            complete: function(params) {
              $(".cartAdd").click(function(e) {
                e.preventDefault();
                cart(this);
              });
            }
          });
        });
      }
      // productlist cart function
      $(".cartAdd").click(function(e) {
        e.preventDefault();
        cart(this);
      });

      $(".card-img-top").css("height", $(".popupCard").css("width"));
    }
  });
}

// 設定URL Parameter
function setQueryStringParameter(name, value) {
  const params = new URLSearchParams(location.search);
  params.set(name, value);
  window.history.pushState(
    {},
    "",
    decodeURIComponent(`${location.pathname}?${params}`)
  );
}

// 購物車功能
function cart(cartBtn) {
  parentCart = $(cartBtn).parent();
  var targetUrl = $(parentCart).attr("action");
  var data = $(parentCart).serialize();

  $(parentCart)
    .find("#quantity")
    .val("1");

  if (groupAlias.trim() == "") {
    data = data;
    targetUrl += "add";
    cartAjax(data, targetUrl);
  } else if (groupAlias.trim() != "") {
    data = data;
    targetUrl += `groupAdd/${groupAlias.trim()}`;
    cartAjax(data, targetUrl);
  }
}

function cartAjax(data, targetUrl) {
  $.ajax({
    type: "post",
    url: targetUrl,
    cache: false,
    data: data,
    dataType: "json",
    success: function(data) {
      if (data.status == "false") {
        $(".toast-header").empty();
        $(".toast-body").empty();
        $(".toast-header").html("購物失敗");
        $(".toast-body").html("該商品庫存不足");
        $(".toast").toast({
          delay: 3000
        });
        $(".toast").toast("show");
      } else {
        $(".toast-header").empty();
        $(".toast-body").empty();
        $(".toast-header").html("購物成功");
        $(".toast-body").html("商品已被加入購物車");
        $(".toast").toast({
          delay: 3000
        });
        $(".toast").toast("show");
      }
    },
    error: function(data) {
      $(".toast-header").empty();
      $(".toast-body").empty();
      $(".toast-header").html("購物失敗");
      $(".toast-body").html("請求失敗");
      $(".toast").toast({
        delay: 3000
      });
      $(".toast").toast("show");
    },
    complete: function(xhr) {
      // getData();
    }
  });
}
