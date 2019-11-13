$(document).ready(function () {


    $('#searchButom').click(function () {

        let searchString = $('#searchRecipe').val();
        if (searchString !== null) {
        	console.log('name='+searchString);
            $.ajax({
                method: "GET",
                url: 'http://localhost:8080/ezfit/keyword/submit.do',
                
                data: {
                    name: searchString
                },
                success: function () {
//                    console.log('收到回傳data:' + data);
//                     alert('success = '+searchString);
                    window.location.href = `http://localhost:8080/ezfit/search_result?search=${searchString}&page=1`;
                }
            });
        };
    });




});
