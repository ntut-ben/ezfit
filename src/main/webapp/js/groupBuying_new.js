$(document).ready(function() {
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

  getDate();
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
