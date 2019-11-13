var crid = "";
var uid = "";
var crn = "";
var groupAlias = [];
var groupName = [];
var isConnected = false;
var stompClient = null;
var responseMsg = "";
var responseCrn = "";

$(document).ready(function() {
  uid = getName("name");
  function getName(name) {
    var match = document.cookie.match(new RegExp("(^| )" + name + "=([^;]+)"));
    if (match) return match[2];
  }

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
      groupAlias.forEach(element => {
        stompClient.subscribe(`/topic/popup/${element}`, function(respnose) {
          respnose = JSON.parse(respnose.body);
          responseMsg = respnose.message;
          responseCrn = respnose.crn;
          $(".toast-header").empty();
          $(".toast-body").empty();
          $(".toast-header").html(responseCrn);
          $(".toast-body").html(responseMsg);
          $(".toast").toast({
            delay: 3000
          });
          $(".toast").toast("show");
        });
      });
    }
  );
}

function getGroupInfo() {
  $.ajax({
    type: "GET",
    url: "/ezfit/api/GroupBuy/query",
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
        groupAlias.push(element.groupAlias);
        groupName.push(element.groupName);
      });
    },
    complete: function() {
      wsconnect();
    }
  });
}
