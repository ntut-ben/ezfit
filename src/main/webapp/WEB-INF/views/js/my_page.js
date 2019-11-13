$(document).ready(function () {
function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function(e) {
        $("#profileCover").attr("src", e.target.result);
      };

      reader.readAsDataURL(input.files[0]);
    }
  }

  $("#uploadCoverImg").change(function() {
    readURL(this);
  });

//   編輯簡介字數統計

$(function(){ 
    $(document).keyup(function(){ 
      var text=$("#editDescriptionText").val(); 
      var counter= 200-(text.length); 
      $("#textCountResult").text(counter); 
    })
  }) 

});