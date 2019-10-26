$(document).ready(function() {
  // load file into header and footer
  $("#header").load("header.html");
  $("#footer").load("footer.html");
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

  $("#clickBtn").click(function(e) {
    e.preventDefault();
    inheritInfo();
  });

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

function getData() {
  $("#cartListRow")
    .children(".cartCard")
    .remove();
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/ezfit/CheckShopCart.do`,
    dataType: "json",
    success: function(data) {
      total = 0;
      cartData = "";
      for (let i = 0; i < data.length; i++) {
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
                   data-id="${data[i].product.id}"
                   data-action="modify"
                   type="number"
                   name="qty"
                   value="${data[i].qty}"
                   style="max-width: 40%;"
                 />
                 <button class="text-danger mx-1 deleteBtn" type="button" style="border: 0; background: inherit;"  data-action="delete"  data-id="${
                   data[i].product.id
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
            id = $(this).data("id");
            // $(`${parentList} .subTotal`).data(subTotal);
            inputClicked = this;
            $.ajax({
              type: "POST",
              cache: false,
              url: "http://localhost:8080/ezfit/ShopCart.do",
              data: { itemId: id, action: actionToDo, qty: value },
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
          actionToDo = $(this).data("action");
          id = $(this).data("id");

          $.ajax({
            type: "POST",
            cache: false,
            url: "http://localhost:8080/ezfit/ShopCart.do",
            data: { itemId: id, action: actionToDo },
            dataType: "text",
            success: function(response) {
              getData();
            }
          });
        });
      }
    }
  });
}
