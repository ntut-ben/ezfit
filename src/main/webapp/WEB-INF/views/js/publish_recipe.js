$(document).ready(function () {
  //   食譜標題字數計算
  $(function () {
    $(document).keyup(function () {
      var text = $(".enter-recipe-title").val();
      var counter = 20 - (text.length);
      $("#recipeTitleTextCount").text(counter);
    })
  })

  //   簡介字數計算
  $(function () {
    $(document).keyup(function () {
      var text = $("#brief").val();
      var counter = 180 - (text.length);
      $("#briefTextCount").text(counter);
    })
  })

  //  //   步驟字數計算

  // $(function(){ 
  //   $(document).keyup(function(){ 
  //     var text=$("#directionText").val(); 
  //     var counter= 150-(text.length); 
  //     $("#directionTextCount").text(counter); 
  //   })
  // }) 

  // //步驟拖放

  // $(function() {
  //   $( "#directions" ).sortable({ handle: '.handle', opacity:0.8, scroll:true});

  // } );

  // $(function() {
  //   $( "#ingredientItems" ).sortable({ handle: '.handle', opacity:0.8, scroll:true});

  // } );


  //食譜步驟圖片

  // function readURL(input) {
  //   if (input.files && input.files[0]) {
  //     var reader = new FileReader();

  //     reader.onload = function(e) {
  //       $("#stepImg0").attr("src", e.target.result);
  //     };

  //     reader.readAsDataURL(input.files[0]);
  //   }
  // }

  // $("#uploadStepImg0").change(function() {
  //   readURL(this);
  // });


  $('#uploadStepImg0').change(function () {
    var file = $('#uploadStepImg0')[0].files[0];
    var reader = new FileReader;
    reader.onload = function (e) {
      $('#stepImg0').attr('src', e.target.result);
    };
    reader.readAsDataURL(file);
  });



});

// ===== Scroll to Top ==== 
$(window).scroll(function () {
  if ($(this).scrollTop() >= 50) {        // If page is scrolled more than 50px
    $('#return-to-top').fadeIn(200);    // Fade in the arrow
  } else {
    $('#return-to-top').fadeOut(200);   // Else fade out the arrow
  }
});
$('#return-to-top').click(function () {      // When arrow is clicked
  $('body,html').animate({
    scrollTop: 0                       // Scroll to top of body
  }, 500);
});


//食譜封面圖片

function doFirst(){
    //先跟HTML畫面產生關連，再建事件聆聽的功能
   document.getElementById('uploadCoverImg').onchange = fileChange;

}
function fileChange(){
    let file = document.getElementById('uploadCoverImg').files[0];


    //讀取檔案內容
    let readFile = new FileReader();
    readFile.readAsDataURL(file);
    readFile.addEventListener('load',function(e){
        // document.getElementById('image').src = e.target.result;
        let image = document.getElementById('coverImg');

        image.src = this.result;

    });
}
window.addEventListener('load',doFirst);

