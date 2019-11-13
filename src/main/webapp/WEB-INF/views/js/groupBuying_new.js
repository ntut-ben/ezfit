$(document).ready(function() {
  generateView();

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
  $(".twzipcode")
    .find("select")
    .addClass("main-Font-Color rounded");
});

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

    $("#deadLine").append(dateData);
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

function generateView() {
  $.urlParam = function(name) {
    var results = new RegExp("[?&]" + name + "=([^&#]*)").exec(
      window.location.search
    );
    return results !== null ? results[1] || 0 : false;
  };
  group = $.urlParam("group");
  mainBody = $("#mainBody");
  if (group == false || group == null) {
    $("#mainContainer").css("height", "145vh");

    viewData = `  <div class="text-center">
<img src="/ezfit/images/step1.svg" alt="" class="w-75" />
</div>
<!-- 小內框 -->
<div class="container-fulid m-3">
<form class="w-100" action="/ezfit/api/GroupBuy/create" method="POST">
  <div class="row p-2 bg-white">
    <div class="col-12 m-2 text-center main-Font-Color bg-white h2">
      建立揪團資料
    </div>
    <div class="col-12 m-1 main-Font-Color">
      <hr class="hrStyle" />
    </div>
    <!-- 群組名稱 -->
    <div class="col-12 m-2 main-Font-Color">
      <div class="m-1 h6">群組名稱 <span class="text-danger"> ${errorGroupName}</span></div>
      <div class="m-1">
        <input
          type="text"
          value="create"
          name="action"
          class="d-none"
        />
        <input
          name="groupName"
          class="p-2 input-Height w-100 rounded"
          type="text"
          placeholder="請輸入群組名稱"
        />
      </div>
    </div>
    <!-- 主揪姓名 -->
    <div class="col-12 m-2 main-Font-Color">
      <div class="m-1 h6">主揪姓名 <span class="text-danger"> ${errorName}</span></div>
      <div class="m-1">
        <input
          name="initiatorName"
          class="p-2 input-Height w-100 rounded"
          type="text"
          placeholder="請輸入主揪姓名"
        />
      </div>
    </div>
    <!-- email -->
    <div class="col-12 m-2 main-Font-Color">
      <div class="m-1 h6">手機 <span class="text-danger"> ${errorPhone}</span></div>
      <div class="m-1">
        <input
          name="initiatorPhone"
          class="p-2 input-Height w-100 rounded"
          type="tel"
          placeholder="請輸入手機號碼"
        />
      </div>
    </div>
    <!-- 收件人地址 -->
    <div class="col-12 m-2 main-Font-Color">
      <div class="m-1 h6">收件地址 <span class="text-danger"> ${errorAddress}</span></div>
      <div class="m-1">
        <span
          id="twzipcode"
          class="twzipcode main-Font-Color rounded"
        ></span>
      </div>
      <div class="m-1">
        <input
          type="text"
          name="address"
          id="address"
          placeholder="請輸入收件人地址"
          class="p-2 input-Height w-100 rounded"
        />
      </div>
    </div>

    <div class="col-12 m-2 main-Font-Color">
      <div class="m-1 h6">截止時間</div>
      <div class="m-1">
        <select
          name="deadLine"
          id="deadLine"
          class="main-Font-Color rounded"
        ></select>
      </div>
    </div>

    <div class="col-12 text-center main-Font-Color">
      <button
        class="btn btn-success text-white text-center w-25"
        type="submit"
      >
        完成開團
      </button>
    </div>
  </div>
</form>
</div>
<div class="text-center">
如已建立揪團資料，請至「訂單管理」查看明細 <br />
<a href="">前往訂單管理</a>
</div>`;
    $(mainBody).append(viewData);
    getDate();
  } else {
    $("#mainContainer").css("height", "115vh");

    viewData = `  <!-- 狀態圖 -->
    <div class="text-center">
      <img src="images/step2.svg" alt="" class="w-75" />
    </div>
    <div class="container-fulid m-3">
      <div class="row p-2 bg-white">
        <div class="col-3 mx-auto">
          <img src="images/share_to_friends.svg" alt="" class="w-100" />
        </div>
        <div class="col-12 m-2 text-center main-Font-Color bg-white h2">
          揪朋友來訂購
        </div>
        <div class="col-12 m-2 text-center main-Font-Color bg-white">
          擔心當主揪，還要找朋友收款嗎？ <br />
          EZfit Eat 幫你合併配送、各自付費，便利又省時
        </div>
        <div class="col-12 m-2 mt-3 text-center main-Font-Color bg-white">
          <button id="copyLink" type="button" class="btn btn-link">
            分享此訂購連結
          </button>
          <br />
          <input
            id="link"
            class="w-100"
            type="text"
            value="http://localhost:8080/ezfit/api/GroupBuy/join/${group}"
            readonly
          />
        </div>
        <div class="col-12 m-2 text-center main-Font-Color bg-white">
          <button class="btn btn-success text-white w-25">
            <a class="text-white h5 " href="/ezfit/api/GroupBuy/join/${group}">點餐去</a>
          </button>
        </div>
      </div>
    </div>
    <div class="text-center">
      如已建立揪團資料，請至「訂單管理」查看明細 <br />
      <a href="orders">前往訂單管理</a>
    </div>`;
    $(mainBody).append(viewData);
    // 複製參加連結
    $("#copyLink").click(function(e) {
      e.preventDefault();
      $("#link").select();
      document.execCommand("copy");

      $("#itemModel").modal("show");
      setTimeout(() => {
        $("#itemModel").modal("hide");
      }, 800);
    });
  }
}
