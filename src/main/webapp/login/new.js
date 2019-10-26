
    $(document).ready(function(){
        let account;
        let password;
        $('#account').blur(function(){
            account = $('#account').val();
            if(account == ''){
                $('#errorAccount').text('請輸入帳號');
                $(this).css("border-color","red");
            }else{
                $('#account').css("border-color","");
                $('#errorAccount').text("");
            }
        });
        $('#password').blur(function(){
            password = $('#password').val();
            if(password == ''){
                $('#errorPassword').text('請輸入密碼');
                $(this).css("border-color","red");
            }else{
                $('#password').css("border-color","");
                $('#errorPassword').text("");

            }
        });






        $("#submit").click(function(){
            account = $('#account').val();
            password = $('#password').val();
            if(account == '' && password == ''){
                $('#errorAccount').text('請輸入帳號');
                $('#account').css("border-color","red");
                $('#errorPassword').text('請輸入密碼');
                $('#password').css("border-color","red");
                }else if(account == '' ){
                    $('#errorAccount').text('請輸入帳號');
                }else if(password == ''){
                    $('#errorPassword').text('請輸入密碼');
                }else{
                    document.form.submit();
                    alert('123');
                }
            });

    });

   

