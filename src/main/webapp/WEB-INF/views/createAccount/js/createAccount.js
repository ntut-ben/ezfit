// $.validator.setDefaults({
//     submitHandler: function() {
//         alert("submitted!");
//     }
// });

$().ready(function() {
	// validate signup form on keyup and submit
	$('#theForm').validate({
		errorPlacement : function(error, element) {
			if (element.attr("name") == "serviceRule") {
				error.insertAfter(document.getElementsByName("service"));
			} else if (element.attr("name") == "veriCode") {
				error.insertAfter(document.getElementsByName("code"));
			} else {
				error.insertAfter(element);
			}

		},

		errorElement : "div",

		rules : {

			memberName : {
				required : true,
			},

			memberEmail : {
				required : true,
				email : true
			},

			memberPassword : {
				minlength : 8,
				maxlength : 12,
				upperandlowercase : true,
				required : true,
			},

			memberPasswordCheck : {
				required : true,
				equalTo : "#memberPassword"
			},

			veriCode : {
				required : true,
			},

			serviceRule : "required"
		},
		messages : {
			memberName : {
				required : "請輸入姓名",
			},

			memberEmail : "請輸入正確Email",

			memberPassword : {
				required : "請輸入密碼",
				minlength : $.validator.format("最少 {0} 個字元"),
				maxlength : $.validator.format("最多 {0} 個字元"),
			},

			memberPasswordCheck : {
				required : "請確認密碼",
				equalTo : "密碼不相符"
			},

			veriCode : {
				required : "請輸入驗證碼",
			},

			serviceRule : "請同意服務條款",
		}
	});

});
