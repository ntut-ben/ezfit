$(document).ready(function() {
  getData();

  $(".btn-Back").click(function(e) {
    e.preventDefault();
    history.back();
  });
});

function getData() {
  $.urlParam = function(name) {
    var results = new RegExp("[?&]" + name + "=([^&#]*)").exec(
      window.location.search
    );
    return results !== null ? results[1] || 0 : false;
  };
  orderId = $.urlParam("orderId");
  if (orderId != undefined && orderId != null && orderId != false) {
    apiURL = `http://localhost:8080/ezfit/api/orders/query/order/${orderId}`;
  } else {
    apiURL = `http://localhost:8080/ezfit/api/orderDetail`;
  }

  $.ajax({
    type: "GET",
    cache: false,
    url: apiURL,
    dataType: "json",
    success: function(data) {
      total = 0;
      cartData = "";

      for (let i = 0; i < data.orderItemBeans.length; i++) {
        if (data.orderItemBeans[i].planeItems.length == 0) {
          fileName = data.orderItemBeans[i].product.fileName.split(",");
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
               ${data.orderItemBeans[i].product.name} <br />
               ${data.orderItemBeans[i].product.unit}
               </div>
             </div>
           </div>
         </div>
         <div class="col-5">
           <div class="container-fuild">
             <div class="row text-right">
               <div class="col-4 ">
                  ${data.orderItemBeans[i].qty}
               </div>
               <div class="col-4">NT$ ${
                 data.orderItemBeans[i].product.price
               }</div>
               <div class="col-4 subTotal" data-subTotal="${
                 data.orderItemBeans[i].subTotal
               }">NT$ ${data.orderItemBeans[i].subTotal}</div>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>`;
          total += data.orderItemBeans[i].subTotal;
        } else {
          shipFeed = getDate(
            data.orderItemBeans[i].shipDate,
            data.orderItemBeans[i].product.id
          );
          cartData += ` <div class="col-12 my-2 cartCard">
     <div class="container">
       <div class="row customBottomBorder pb-3">
         <div class="col-7">
           <div class="container-fuild">
             <div class="row">
               <div class="col-5">
               <a href="planeItemDetail?orderItemId=${data.orderItemBeans[i].id}"> ${data.orderItemBeans[i].product.alias}</a><br /> <br />
               運費，配送時間${shipFeed}
               </div>
               <div class="col-2">
               </div>
             </div>
           </div>
         </div>
         <div class="col-5">
           <div class="container-fuild">
             <div class="row text-right">
               <div class="col-4 ">
                  ${data.orderItemBeans[i].qty}  <br /> <br /> 1
               </div>
               <div class="col-4">NT$ ${data.orderItemBeans[i].product.price}  <br /> <br /> NT$90 </div>
               <div class="col-4 subTotal" data-subTotal="${data.orderItemBeans[i].subTotal}">NT$ ${data.orderItemBeans[i].subTotal}  <br /> <br /> NT$90 </div>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>`;
          total += 90;
          total += data.orderItemBeans[i].subTotal;
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
    `;

      $("#cartListRow").append(cartData);
      x = document.referrer;
      console.log(x);
      if (orderId != undefined && orderId != null && orderId != false) {
        $("#orderId").html(`訂單編號: #${data.id}`);
        $("#orderTime").html(`時間: ${data.createTime}`);
      } else if (x.includes("/ezfit/cartList")) {
        $("#orderInfo").html(
          `<h3>${data.subscriberName}，訂購成功!您的訂單編號為#${data.id}</h3>`
        );
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
