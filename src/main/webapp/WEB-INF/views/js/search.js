$(document).ready(function () {
    var searchString;
    $('#searchRecipe').bind('input propertychange', function () {
        searchString = $('#searchRecipe').val();
        console.log(searchString);
        if(searchString !== null){
            $.ajax({
                method: "GET",
                url: 'http://localhost:8080/ezfit/recipe/search.do',
                data:{
                    name: searchString
                },
                success:function(data){
                    console.log('收到回傳data:'+data);
                }
        });
        }




    });



})
