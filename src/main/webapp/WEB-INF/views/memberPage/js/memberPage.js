$(document).ready(function() {
  $("#twzipcode").twzipcode({
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

  $("#datepicker").datepicker({
    changeMonth: true,
    changeYear: true
  });
  // 設定年份
  $("#datepicker").datepicker({
    yearRange: "1919:2001"
  });
  var yearRange = $(".selector").datepicker("option", "yearRange");
  $("#datepicker").datepicker("option", "yearRange", "1919:2001");
});
