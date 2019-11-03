$(document).ready(function() {
  getOrderData();
});

function getOrderData() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/ezfit/api/orders/query/order",
    dataType: "json",
    success: function(data) {
      orderData = "";
      $("#orderTbody")
        .find("tr")
        .remove();

      data.forEach(element => {
        date = parseDate(element.createTime);

        orderData += `<tr>
<td>${date}</td>
<td>#${element.id}</td>
<td>${element.subscriberName}</td>
<td><a href="http://localhost:8080/ezfit/orderDetail?orderId=${element.id}">點此查看明細</a></td>
<td>${element.totalAmount} 元</td>
</tr>`;
      });

      $("#orderTbody").append(orderData);
    }
  });
}

function parseDate(date) {
  createTime = date.split(",");
  year = createTime[1].trim();
  month = createTime[0].split(" ")[0].trim();
  date = createTime[0].split(" ")[1].trim();

  // 月份字串轉數字
  switch (month) {
    case "Jan":
      month = "1";
      break;
    case "Feb":
      month = "2";
      break;
    case "Mar":
      month = "3";
      break;
    case "Apr":
      month = "4";
      break;
    case "May":
      month = "5";
      break;
    case "Jun":
      month = "6";
      break;
    case "Jul":
      month = "7";
      break;
    case "Aug":
      month = "8";
      break;
    case "Sep":
      month = "9";
      break;
    case "Oct":
      month = "10";
      break;
    case "Nov":
      month = "11";
      break;
    case "Dec":
      month = "12";
      break;
    default:
      break;
  }

  return `${year}-${month}-${date} ${createTime[2]}`;
}
