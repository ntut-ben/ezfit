var crid = "";
var uid = "";

var isConnected = false;
var stompClient = null;

$(document).ready(function() {
  getOrderData();
  getGroupBuyData();
  $("#btnSend").on("click", sendCrMessage);

  $("#messageBox").keypress(function(event) {
    if (event.keyCode == 13) {
      $("#btnSend").trigger("click");
    }
  });

  $("#pills-home-tab").click(function(e) {
    e.preventDefault();
    getOrderData();
  });

  $("#pills-profile-tab").click(function(e) {
    e.preventDefault();
    getGroupBuyData();
  });
});

function getGroupBuyData() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/ezfit/api/GroupBuy/query",
    dataType: "json",
    success: function(data) {
      count = 0;
      $("#accordionExample")
        .children()
        .remove();
      groupBuyData = "";
      data.reverse().forEach(element => {
        // 判斷是不是主揪
        if (element.role == 1) {
          initiator = false;
          element = element.initiator;
        } else {
          initiator = true;
          element = element;
        }

        createTime = parseDate(element.createTime);
        deadLine = parseDeadLine(element.deadLine);
        createTimeWeek = parseWeek(createTime);
        deadLineWeek = parseWeek(deadLine);

        groupBuyData += ` <div class="card">
        <div class="card-header" id="headingOne">
          <h2 class="mb-0">
            <button
              class="btn btn-link collapsed text-left"
              type="button"
              data-toggle="collapse"
              data-target="#collapse${count}"
              aria-expanded="false"
              aria-controls="collapseOne"
            >
              <p>
                <span
                  >日期：<span id="group-buy-time"
                    >${createTimeWeek}</span
                  >
                  &nbsp;|&nbsp;
                </span>
                <span
                  >揪團編號：<span id="group-buy-no">000${element.id}</span>
                  &nbsp;|&nbsp;
                </span>
`;

        $.ajax({
          type: "GET",
          async: false,
          url: `http://localhost:8080/ezfit/api/orders/query/order/groupBuy/${element.id}`,
          dataType: "json",
          success: function(order) {
            if (order.id == undefined) {
              orderStatus = false;
              groupBuyData += `<span
              >訂單編號：<span id="group-buy-order-no"
                >尚未訂購</span
              >
              &nbsp;|&nbsp;
            </span>`;
            } else {
              orderStatus = true;
              groupBuyData += `<span
            >訂單編號：<span id="group-buy-order-no"
              >${order.id}</span
            >
            &nbsp;|&nbsp;
          </span>`;
            }
          },
          complete: function(xhr) {}
        });

        groupBuyData += `  <span
                  >截止時間：<span id="group-buy-shipping-time"
                    >${deadLineWeek}</span
                  ></span
                >
                `;

        if (element.status == "0") {
          groupBuyData += `<span class="badge badge-pill badge-success">
          進行中
        </span>`;
        }

        groupBuyData += `</p>
              <p>
                <span
                  >主揪人：<span id="group-buy-hostname"
                    >${element.initiatorName}</span
                  >
                  &nbsp;|&nbsp;
                </span>
                <span
                  >收貨地址：<span id="group-buy-addr"
                    >${element.shippingCity}${element.shippingDistrict}${element.shippingAddress}</span
                  ></span
                >
              </p>
            </button>
          </h2>
        </div>
        
        <div
          id="collapse${count}"
          class="collapse"
          aria-labelledby="headingOne"
          data-parent="#accordionExample"
        >
          <div class="card-body p-3">
            <div class="action-btns">
              <div class="group-list pt-2">揪團名單</div>
              <div>`;

        if (orderStatus != true && element.status == 0) {
          groupBuyData += `<button
                 data-join="http://localhost:8080/ezfit/api/GroupBuy/join/${element.groupAlias}"
                type="button"
                class="btn-order btn btn-outline-success btn-sm action-btn"
              >
                <img
                  class="btn-icons-g"
                  src="img/orders/ic_local_grocery_store_24px.svg"
                  alt=""
                />
                <img
                  class="btn-icons-w"
                  src="img/orders/ic_local_grocery_store_24px_w.svg"
                  alt=""
                />
                加入點餐
              </button>`;
        }

        if (orderStatus != true && element.status == 0) {
          groupBuyData += `<button
                data-alias="${element.groupAlias}"
                type="button"
                class="btn-chat btn btn-outline-success btn-sm action-btn"
              >
                <img
                  class="btn-icons-g"
                  src="img/orders/ic_local_grocery_store_24px.svg"
                  alt=""
                />
                <img
                  class="btn-icons-w"
                  src="img/orders/ic_local_grocery_store_24px_w.svg"
                  alt=""
                />
                聊聊
              </button>`;
        }

        if (element.status == 0) {
          groupBuyData += `<button
          type="button"
          class="copyLink btn btn-outline-success btn-sm action-btn"
        >
          <img
            class="btn-icons-g"
            src="img/orders/ic_content_copy_24px.svg"
            alt=""
          />
          <img
            class="btn-icons-w"
            src="img/orders/ic_content_copy_24px_w.svg"
            alt=""
          />
          複製分享連結
        </button>
        <input
        style = "position: absolute; left: -1000px; top: -1000px;";
        class="w-100 link"
        type="text"
        value="http://localhost:8080/ezfit/api/GroupBuy/join/${element.groupAlias}"
        readonly
      />
        `;
        }

        if (orderStatus != true && element.status == 0) {
          groupBuyData += `<button
                onclick="location.href='cartList?group=${element.groupAlias}'"
                type="button"
                class=" btn btn-outline-success btn-sm action-btn"
              >
                <img
                  class="btn-icons-g"
                  src="img/orders/ic_local_grocery_store_24px.svg"
                  alt=""
                />
                <img
                  class="btn-icons-w"
                  src="img/orders/ic_local_grocery_store_24px_w.svg"
                  alt=""
                />
                查看購物車
              </button>`;
        }

        if (initiator == true && element.status == 0) {
          groupBuyData += ` <button
          data-id=${element.id};
                  type="button"
                  class="btn-checkout btn btn-outline-success btn-sm action-btn"
                >
                  <img
                    class="btn-icons-g"
                    src="img/orders/ic_assignment_turned_in_24px.svg"
                    alt=""
                  />
                  <img
                    class="btn-icons-w"
                    src="img/orders/ic_assignment_turned_in_24px_w.svg"
                    alt=""
                  />
                  收單
                </button>`;
        }

        groupBuyData += `</div></div>`;

        element.orderBeans.forEach(orderBean => {
          total = 0;
          groupBuyData += `<div class="group-buy-member-name pt-3">
              <img src="img/orders/ic_face_24px.svg" alt="" />
              ${orderBean.memberBean.name}
            </div>`;

          orderBean.orderItemBeans.forEach(orderItemBean => {
            fileName = orderItemBean.product.fileName.split(",");
            groupBuyData += `<div class="group-buy-order-items pb-3">
          <div class="group-buy-product mt-2">
            <img
              src="productImage/${fileName[0]}"
              alt=""
            />
            <div class="ml-3">
              <p id="product-name">${orderItemBean.product.name}</p>
              <p>數量：<span id="qty">${orderItemBean.qty}</span></p>
            </div>
          </div>
          <div class="unit-price">$${orderItemBean.subTotal}</div>
        </div>`;
            total += orderItemBean.subTotal;
          });
          groupBuyData += `<div class="total-cost text-right py-3">
          總計：<span id="">${total}</span> 元
        </div>
`;
        });
        groupBuyData += `</div></div></div>`;
        count += 1;
        console.log(count);
      });

      $("#accordionExample").append(groupBuyData);
    },
    complete: function() {
      $(".btn-checkout").click(function(e) {
        e.preventDefault();

        $.ajax({
          type: "GET",
          url: `http://localhost:8080/ezfit/api/GroupBuy/checkout/${$(
            this
          ).data("id")}`,
          dataType: "json",
          success: function(response) {
            getGroupBuyData();
          }
        });
      });

      $(".btn-order").click(function(e) {
        e.preventDefault();
        href = $(this).data("join");

        window.location.href = href;
      });

      $(".copyLink").click(function(e) {
        e.preventDefault();

        $(this)
          .parents(".action-btns")
          .find(".link")
          .select();

        document.execCommand("copy");
      });

      $(".btn-chat").click(function(e) {
        e.preventDefault();
        chat(this);
      });
    }
  });
}

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

function parseDeadLine(deadLine) {
  deadLine = deadLine.split(" ");
  year = deadLine[2];
  month = deadLine[0].substring(0, deadLine[0].length - 1);
  date = deadLine[1].substring(0, deadLine[1].length - 1);

  return `${year}-${month}-${date}`;
}

function parseWeek(date) {
  ship = date.split("-");

  shipYear = parseInt(ship[0]);
  shipDate = parseInt(ship[2]);
  shipMonth = parseInt(ship[1]);
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
  while (day > 6) {
    day -= 7;
  }
  var week = "";
  switch (day) {
    case 0:
      week = "(日)";
      break;
    case 1:
      week = "(ㄧ)";
      break;
    case 2:
      week = "(二)";
      break;
    case 3:
      week = "(三)";
      break;
    case 4:
      week = "(四)";
      break;
    case 5:
      week = "(五)";
      break;
    case 6:
      week = "(六)";
      break;
    default:
      break;
  }

  return `${shipYear}-${shipMonth}-${shipDate}  ${week}  `;
}

function chat(btn) {
  crid = $(btn).data("alias");
  if (stompClient != null) {
    stompClient.disconnect();
  }

  $("#messagelist")
    .find("li")
    .remove();

  $(".chatBox").animate({ left: "76%" }, "slow", "easeInOutQuad");
  $(".close").click(function(e) {
    e.preventDefault();
    $(".chatBox").animate({ left: "100%" }, "slow", "easeInOutQuad");
  });

  uid = getName("name");
  function getName(name) {
    var match = document.cookie.match(new RegExp("(^| )" + name + "=([^;]+)"));
    if (match) return match[2];
  }

  isConnected = !isConnected;
  if (isConnected) {
    var socket = new SockJS("/ezfit/websocket-cr");
    stompClient = Stomp.over(socket);
    stompClient.connect(
      {
        uid
      },
      function(frame) {
        // subscribe 3 topics
        stompClient.subscribe(`/topic/room/${crid}`, function(respnose) {
          displayAppendCrMessage(JSON.parse(respnose.body));
        });
        connectWs(crid);
      }
    );
  }
  console.log("connectWebSocket:" + isConnected);
}

function appendCrMessage(messagelist, msg) {
  var msgItem = $("<li/>")
    .addClass("list-group-item")
    .appendTo(messagelist);
  if (msg.sender == uid) {
    msgItem.addClass("list-group-item-success text-right");
    msgItem.text("me:" + msg.message + " at " + msg.sendTime);
  } else if (msg.sender == "sysinfo") {
    msgItem.addClass("list-group-item-info text-center");
    msgItem.text("sys:" + msg.message + " at " + msg.sendTime);
  } else if (msg.sender == "sysdanger") {
    msgItem.addClass("list-group-item-danger text-center");
    msgItem.text("sys:" + msg.message + " at " + msg.sendTime);
  } else {
    msgItem.text(msg.sender + ":" + msg.message + " at " + msg.sendTime);
  }
  // console.log((msg.sender == getUid()) + "-" + (msg.sender == "sysinfo") +
  // "-" + (msg.sender == "sysdanger"));
}

function createCrMessage(message) {
  var crMessage = {
    status: "",
    crid: crid,
    sender: uid,
    sendTime: "",
    message: message
  };
  return crMessage;
}

function sendCrMessage() {
  // console.log("sendCrMessage...");

  var messageBox = $("#messageBox");
  var message = messageBox.val();
  messageBox.val("");
  console.log(uid + ":" + message);
  var crMessage = createCrMessage(message);
  // 如果直接傳到被subscript的topic的話，會收到一個JSON object
  // 但如果分成一個送、一個收的話，後端還可以處理message然後再透過@SendTo，
  // 這種方式有可能做到把訊息彙總後一次publish多筆message到topic
  stompClient.send(`/ws/v1/room/${crid}`, {}, JSON.stringify(crMessage));
  // stompClient.send("/topic/crmessage", {}, JSON.stringify(crMessage));
}

function displayAppendCrMessage(crMessages) {
  var messagelist = $("#messagelist");
  if (crMessages) {
    if (Array.isArray(crMessages)) {
      crMessages.forEach(function(msg) {
        appendCrMessage(messagelist, msg);
      });
    } else {
      appendCrMessage(messagelist, crMessages);
    }
  }
  // let scroll to last one
  $("ul#messagelist li:last")
    .get(0)
    .scrollIntoView();
}

function connectWs(crid) {
  console.log("connectWs...");
  var crMessage = createCrMessage();
  stompClient.send(`/ws/v1/connect/${crid}`, {}, JSON.stringify(crMessage));
}
