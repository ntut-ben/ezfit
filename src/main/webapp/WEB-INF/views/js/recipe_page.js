$(document).ready(function () {
$(function(){ 
    $(document).keyup(function(){ 
      var text=$("#comment").val(); 
      var counter= 400-(text.length); 
      $("#commentTextCount").text(counter); 
    })
  }) 
});