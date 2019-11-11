var crid = "";
var uid = "";
var crn = "";
var groupAlias = [];
var groupName = [];
var isConnected = false;
var stompClient = null;

$(document).ready(function() {
  uid = getName("name");
  function getName(name) {
    var match = document.cookie.match(new RegExp("(^| )" + name + "=([^;]+)"));
    if (match) return match[2];
  }

  console.log(uid);
  getGroupInfo();
});

function wsconnect() {
  var socket = new SockJS("/ezfit/websocket-cr");
  stompClient = Stomp.over(socket);
  stompClient.connect(
    {
      uid
    },

    function(frame) {
      stompClient.subscribe(`/topic/popup/${crid}`, function(respnose) {
        alert(respnose.body);
      });
    }
  );
}

function getGroupInfo() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/ezfit/api/GroupBuy/query",
    dataType: "json",
    success: function(data) {
      data.reverse().forEach(element => {
        // 判斷是不是主揪
        if (element.role == 1) {
          initiator = false;
          element = element.initiator;
        } else {
          initiator = true;
          element = element;
        }
groupAlias.ad
        console.log(element.groupAlias);
        console.log(element.groupName);
      });
    }
  });
}
