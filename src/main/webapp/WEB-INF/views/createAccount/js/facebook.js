// $.validator.setDefaults({
//     submitHandler: function() {
//         alert("submitted!");
//     }
// });

function statusChangeCallback(response) { // Called with the results from
	// FB.getLoginStatus().
	console.log('statusChangeCallback');
	console.log(response); // The current login status of the person.
	console.log(response.authResponse.accessToken);
	if (response.status === 'connected') { // Logged into your webpage and
		// Facebook.
		window.location.href = '../index.jsp?access_token='

		$.ajax({
			type : "get",
			url : "http://localhost:8080/ezfit/api/createAccount/facebook",
			data : {
				access_token : response.authResponse.accessToken,
			},
			dataType : "text",
			success : function(response) {
				window.location.href = 'index.jsp';
				location.reload(force)
			},
		});
		window.location.href = '../index.jsp'
		window.location.reload();
		// window.location.href = 'index.jsp?access_token='
		// + response.authResponse.accessToken;
	} else { // Not logged into your webpage or we are unable to tell.
		window.location.href = '../index.jsp';
	}
}

function checkLoginState() { // Called when a person is finished with the
	// Login Button.
	FB.getLoginStatus(function(response) { // See the onlogin handler
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function() {
	FB.init({
		appId : '505880339962412',
		cookie : true, // Enable cookies to allow the server to access the
		// session.
		xfbml : true, // Parse social plugins on this webpage.
		version : 'v5.0' // Use this Graph API version for this call.
	});

	FB.getLoginStatus(function(response) { // Called after the JS SDK has been
		// initialized.
		statusChangeCallback(response); // Returns the login status.
	});
};

(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id)) {
		return;
	}
	js = d.createElement(s);
	js.id = id;
	js.src = "https://connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
//
// function testAPI() { // Testing Graph API after login. See
// // statusChangeCallback() for when this call is made.
// console.log('Welcome! Fetching your information.... ');

// FB.api('/me', function(response) {
// console.log('Successful login for: ' + response.name);
// document.getElementById('status').innerHTML = 'Thanks for logging in, '
// + response.name + response.email + '!';
// });
// }

