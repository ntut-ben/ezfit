$(document).ready(function () {
    let btn = document.getElementById('mySaveBtn');
    btn.addEventListener('click', function () {
        let ownerId;
        $.ajax({
            method: "POST",
            url: 'http://localhost:8080/ezfit/getMember',
            data: {
            },
            success: function (data) {
//                let member = JSON.parse(data);
                let member = data;
                if(member.pkey!== 'undefine'){
                	console.log(member);
                    console.log('about save...pk=' + member.pkey);
                    ownerId = member.pkey;
                    console.log(ownerId);
                    window.location.href = `http://localhost:8080/ezfit/my_page?ownerId=${ownerId}&page=1&year=2019`;
                }else{
                	window.location.href =`http://localhost:8080/ezfit/login/login`;
                }
                

            }
        });



        
    });

})