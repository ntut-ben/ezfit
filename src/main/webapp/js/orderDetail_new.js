$(document).ready(function() {
  // load file into header and footer
  $("#header").load("header.html");
  $("#footer").load("footer.html");

  getData();
});

function getData() {
  $.ajax({
    type: "GET",
    cache: false,
    url: `http://localhost:8080/ezfit/OrderProcess.do`,
    dataType: "json",
    success: function(data) {
      console.log(123);
      total = 0;
      cartData = "";
      for (let i = 0; i < data.orderItemBeans.length; i++) {
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

      $("#orderInfo").html(
        `<h3>${data.subscriberName}，訂購成功!您的訂單編號為#${data.id}</h3>`
      );
      $("#orderId").html(`訂單編號: #${data.id}`);
      $("#orderTime").html(`時間: ${data.createTime}`);
    }
  });
}
