$(document).ready(function() {
  // load file into header and footer

  $(".twzipcode").twzipcode({
    countySel: "臺北市", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
    zipcodeIntoDistrict: true,
    hideCounty: [
      "縣市",
      "基隆市",
      "新北市",
      "宜蘭縣",
      "新竹市",
      "新竹縣",
      "桃園市",
      "苗栗縣",
      "臺中市",
      "彰化縣",
      "南投縣",
      "嘉義市",
      "嘉義縣",
      "雲林縣",
      "臺南市",
      "高雄市",
      "屏東縣",
      "臺東縣",
      "花蓮縣",
      "金門縣",
      "連江縣",
      "澎湖縣"
    ]
  });
  $(".twzipcode option:first-child").css("display", "none");
  $("#subscriberTwzipcode select:nth-child(1)").attr("name", "subscribercity");
  $("#subscriberTwzipcode select:nth-child(2)").attr(
    "name",
    "subscriberdistrict"
  );
  $("#subscriberTwzipcode")
    .find("input")
    .attr("name", "subscriberzipCode");

  getData();
});

function inheritInfo() {
  subscriberName = $("#subscriberName").val();
  subscriberPhone = $("#subscriberPhone").val();
  subscriberdistrict = $(
    "#subscriberTwzipcode select:nth-child(2) option:selected"
  ).val();
  subscriberAddress = $("#subscriberAddress").val();
  $("#name").val(subscriberName);
  $("#phone").val(subscriberPhone);
  $("#twzipcode select:nth-child(2)").val(subscriberdistrict);
  $("#address").val(subscriberAddress);
}

function inheritInfoGroup() {
  subscriberName = $("#subscriberName").val();
  subscriberPhone = $("#subscriberPhone").val();

  $("#name").val(subscriberName);
  $("#phone").val(subscriberPhone);
}

function getData() {
  $.urlParam = function(name) {
    var results = new RegExp("[?&]" + name + "=([^&#]*)").exec(
      window.location.search
    );
    return results !== null ? results[1] || 0 : false;
  };
  group = $.urlParam("group");

  if (group != false && group != null) {
    apiCall = `http://localhost:8080/ezfit/api/CheckShopCart/${group}`;
    $("#cartForm").attr("action", "api/shopCart/groupBill");
  } else {
    apiCall = `http://localhost:8080/ezfit/api/CheckShopCart`;
    $("#cartForm").attr("action", `api/shopCart/bill`);
  }
  $("#cartListRow")
    .children(".cartCard")
    .remove();
  $.ajax({
    type: "GET",
    url: apiCall,
    dataType: "json",
    success: function(data) {
      if (data.groupBuy == undefined) {
        $("#clickBtn").click(function(e) {
          e.preventDefault();
          inheritInfo();
        });
        data = data;
      } else {
        // 團購複製聯繫人按鈕
        $("#clickBtn").click(function(e) {
          e.preventDefault();
          inheritInfoGroup();
        });
        // 增加group alias
        groupData = `<input
                      type="text"
                      name="group"
                      value="${data.groupBuy.groupAlias}"
                      style="display: none;"
                      />`;

        $("#cartListAction").append(groupData);
        $("#cartListAction").val("groupBill");
        // 收件地址不能更改
        addressSelect = $("#twzipcode")
          .find("select")
          .attr("disabled", "true");
        $(addressSelect[0]).val(data.groupBuy.shippingCity);
        $(addressSelect[1]).val(data.groupBuy.shippingDistrict);
        $("#address").val(data.groupBuy.shippingAddress);
        $("#address").attr("readonly", "true");
        // 送出時，資料要解封
        $("#btn-submit").click(function(e) {
          Array.from(addressSelect).forEach(element => {
            $(element).removeAttr("disabled");
          });
        });
        data = data.cartItems;
      }

      total = 0;
      cartData = "";
      for (let i = 0; i < data.length; i++) {
        if (data[i].planeItems.length == 0) {
          fileName = data[i].product.fileName.split(",");
          cartData += ` <div class="col-12 my-2 cartCard">
       <div class="container">
         <div class="row customBottomBorder pb-3">
           <div class="col-7">
             <div class="container-fuild">
               <div class="row">
                 <div class="col-2">
                   <img
                     src="productImage/${fileName[0]}"
                     alt=""
                     style="width: 100%;"
                   />
                 </div>
                 <div class="col-4">
                 ${data[i].product.name} <br />
                 ${data[i].product.unit}
                 </div>
               </div>
             </div>
           </div>
           <div class="col-5">
             <div class="container-fuild">
               <div class="row text-right">
                 <div class="col-4 ">
                   <input
                   class="text-center qtyModify"
                     min="1"
                     data-cartId="${data[i].id}"
                     data-id="${data[i].product.id}"
                     data-action="modify"
                     type="number"
                     name="qty"
                     value="${data[i].qty}"
                     style="max-width: 40%;"
                   />
                   <button class="text-danger mx-1 deleteBtn" type="button" style="border: 0; background: inherit;"  data-action="delete"  data-id="${
                    data[i].id
                   }"=>刪除</button>
                 </div>
                 <div class="col-4">NT$ ${data[i].product.price}</div>
                 <div class="col-4 subTotal" data-subTotal="${
                   data[i].subTotal
                 }">NT$ ${data[i].subTotal}</div>
               </div>
             </div>
           </div>
         </div>
       </div>
     </div>`;
          total += data[i].subTotal;
        } else {
          shipFeed = getDate(data[i].shipDate, data[i].product.id);
          cartData += ` <div class="col-12 my-2 cartCard" >
     <div class="container">
       <div class="row customBottomBorder pb-3">
         <div class="col-7">
           <div class="container-fuild">
             <div class="row">
               <div class="col-5">
               <a href="planeItemDetail?cartItemId=${data[i].id}"> ${data[i].product.alias}</a><br /> <br />
              運費，配送時間${shipFeed}
               </div>
               <div class="col-1">
               </div>
             </div>
           </div>
         </div>
         <div class="col-5">
           <div class="container-fuild">
             <div class="row text-right">
               <div class="col-4 ">
                 <input
                 disabled
                 class="text-center qtyModify"
                   min="1"
                   data-cartId="${data[i].id}"
                   data-id="${data[i].product.id}"
                   data-action="modify"
                   type="number"
                   name="qty"
                   value="${data[i].qty}"
                   style="max-width: 40%;"
                 />
                 <button class="text-danger mx-1 deleteBtn" type="button" style="border: 0; background: inherit;"  data-action="delete"  data-id="${data[i].id}"=>刪除</button>
               </div>
               <div class="col-4">NT$ ${data[i].product.price} 
               <br /> <br /> NT$90</div>
               <div class="col-4 subTotal" data-subTotal="${data[i].subTotal}">NT$ ${data[i].subTotal}  <br /> <br /> NT$90</div>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>`;
          total += data[i].subTotal + 90;
        }
      }
      cartData += `<div class="col-12 my-2 cartCard">
      <div class="container">
        <div class="row">
          <div class="col-7">總計 :</div>
          <div class="col-5">
            <div class="container-fuild">
              <div class="row text-right">
                <div class="col-4"></div>
                <div class="col-4"></div>
                <div class="col-4 total">NT$ ${total}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-12 text-right my-4 cartCard"><a href="shopMaterial.html"><u>回商城繼續購物</u></a></div>
    `;

      $("#cartListRow").append(cartData);
    },
    complete: function(xhr) {
      if (xhr.status == 200) {
        $(".qtyModify").mouseenter(function() {
          value = $(this).val();
        });

        $(".qtyModify").on("keyup change click", function() {
          if (this.value !== value) {
            value = this.value;
            actionToDo = $(this).data("action");
            id = $(this).data("cartid");
            // $(`${parentList} .subTotal`).data(subTotal);
            inputClicked = this;
            $.ajax({
              type: "POST",
              cache: false,
              url: "http://localhost:8080/ezfit/api/mealBox/modifyCount",
              data: { cartId: id, qty: value },
              dataType: "json",
              success: function(data) {
                // 更新顯示
                $(inputClicked)
                  .parent()
                  .parent()
                  .find(".subTotal")
                  .html(`NT$ ${data.subTotal}`);
                // 更新data subTotal
                $(inputClicked)
                  .parent()
                  .parent()
                  .find(".subTotal")
                  .data("subtotal", data.subTotal);

                subTotalArray = $("#cartListRow").find(".subTotal");
                console.log(subTotalArray);
                total = 0;
                // 算出總金額度
                for (let index = 0; index < subTotalArray.length; index++) {
                  console.log($(subTotalArray[index]).data("subtotal"));
                  total += parseInt($(subTotalArray[index]).data("subtotal"));
                }
                // 更新總金額
                $("#cartListRow .total").html(`NT$ ${total}`);
              }
            });
          }
        });

        $(".deleteBtn").click(function(e) {
          e.preventDefault();
          id = $(this).data("id");
          deleteItem(id);
        });
      }
    }
  });
}

function getDate(date, id) {
  ship = date.split("月");
  shipMonth = parseInt(ship[0]);
  shipDate = parseInt(ship[1].split(",")[0]);
  shipYear = parseInt(ship[1].split(",")[1]);
  month = new Date().getMonth() + 1;
  var day = 0;
  if (shipMonth > month) {
    switch (month) {
      case 1:
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      case 12:
        shipDate += 31;
        date = shipDate - new Date().getDate();
        day = date + new Date().getDay();
        shipDate -= 31;
        break;
      case 2:
        shipDate += 28;
        date = shipDate - new Date().getDate();
        day = date + new Date().getDay();
        shipDate -= 28;
        break;
      case 4:
      case 6:
      case 9:
      case 11:
        shipDate += 30;
        date = shipDate - new Date().getDate();
        day = date + new Date().getDay();
        shipDate -= 30;
        break;

      default:
        break;
    }
  } else {
    date = shipDate - new Date().getDate();
    day = date + new Date().getDay();
  }

  if (day >= 7) {
    day -= 7;
  } else if (day < 2) {
    deleteItem(id, "delete");
  }

  var week = "";
  switch (day) {
    case 0:
      week = "星期日";
      break;
    case 1:
      week = "星期一";
      break;
    case 2:
      week = "星期二";
      break;
    case 3:
      week = "星期二";
      break;
    case 4:
      week = "星期三";
      break;
    case 5:
      week = "星期四";
      break;
    case 6:
      week = "星期五";
      break;
    default:
      break;
  }

  return `${shipMonth}/${shipDate}${week}`;
}

function deleteItem(id) {
  $.ajax({
    type: "POST",
    cache: false,
    url: "http://localhost:8080/ezfit/api/shopCart/delete",
    data: { itemId: id },
    dataType: "text",
    success: function(response) {
      getData();
    }
  });
}
