var arr = [
  {
    detail: null,
    picSrc: null,
    file: null
  },
];

$(document).ready(function () {
  var publishBT = document.getElementById('publishBT');
  var saveBt = document.getElementById('saveBT');
  var published = document.getElementById('published');
  var form = document.getElementById('recipe');
  var delBT = document.getElementById('deleteBT');
  var cancelBT = document.getElementById('cancelBT');


  // 之前忘記把第一個method input file加監聽器 這邊補加
  let firstMethod = document.getElementById('uploadStepImg0');
  firstMethod.addEventListener('change', (e) => {
    arr[0].file = (e.target.files[0]);
  });


  publishBT.addEventListener('click', function () {
    published.value = 'published';
    // alert(published.value);
    form.action = "http://localhost:8080/ezfit/recipe/writeRecipe";
    // methodLength.value = arr.length;
    // materalLength.value = materal.length;
    // alert('methodLength= ' + arr.length +
    //   'III  materalLength= ' + materal.length
    // )
    let msg = "確認發佈?"
    if (confirm(msg) === true) {
      form.submit();
    }
  })


  saveBt.addEventListener('click', saved);
  delBT.addEventListener('click', deleted);
  cancelBT.addEventListener('click', cancel);



  // 新增步驟
  let addBtn = document.getElementById('rrr');


  addBtn.addEventListener('click', addEle);
  addBtn.addEventListener('click', createEle);
  // addBtn.addEventListener('click', function () {
  // console.log(arr);
  // });

  // 新增食材
  materal = [{ materalName: '', unit: '' },];

  let addMateralBtn = document.getElementById('addMateral');
  addMateralBtn.addEventListener('click', addMateral);
  addMateralBtn.addEventListener('click', createMateral);






})




function saved() {
  let form = document.getElementById('recipe');
  // let published = document.getElementById('published');
  published.value = 'saved';
  let msg = "儲存將不會發佈您的食譜，您是否要儲存？"
  if (confirm(msg) === true) {
    form.action = "http://localhost:8080/ezfit/recipe/writeRecipe";
    form.submit();
  }
}

function deleted() {
  let msg = "您真的要刪除嗎？"
  var delRecipe = $('#recipePk').val();
  if (confirm(msg) === true) {
    $.ajax({
      method: "POST",
      url: ' http://localhost:8080/ezfit/recipe/delete',
      data: {
        published: 'delete',
        pk: delRecipe
      },
      success: function (data) {
        // if (data === 'done')
          window.location.href = `http://localhost:8080/ezfit/recipe_main`
      }
    });
  }
}

function cancel() {
  let ans = "檔案不會儲存，您確定要離開嗎？"
  if (confirm(ans) === true) {
    window.location.href = `http://localhost:8080/ezfit/recipe_main`;
  }
}



function createEle() {
  // 清空底下所有元件
  $('#directions').html('');

  for (let i = 0; i < arr.length; ++i) {
    // for (let i = 1; i < 5; ++i) {
    // //共同父節點(最外面)
    let directions = document.getElementById('directions');
    // 一個method block
    const methodBox = document.createElement('div');
    methodBox.setAttribute('class', 'row m-0 p-2 step');

    // 左邊
    const picSec = document.createElement('div');
    picSec.setAttribute('class', "col-4 m-0 p-0");
    const methodPk = document.createElement('input');
    methodPk.setAttribute('type', 'hidden');
    methodPk.setAttribute('name', 'mdPk' + i);
    methodPk.setAttribute('value', '0');
    const labelPic = document.createElement('label');
    labelPic.setAttribute('id', 'uploadStepImgLabel' + i);
    const inputPic = document.createElement('input');
    inputPic.setAttribute('type', 'file');
    inputPic.setAttribute('id', 'uploadStepImg' + i);
    inputPic.setAttribute('accept', "image/*");
    inputPic.setAttribute('name', "methodPic" + i);
    // 
    // 
    // 
    inputPic.addEventListener('change', (e) => {
      arr[i].file = (e.target.files[0]);
    });
    // 
    // 
    // 
    // 
    // 
    // 
    const stepImg = document.createElement('img');
    stepImg.setAttribute('id', 'stepImg' + i);
    if ((arr[i].fileName !== null && (arr[i].fileName !== undefined))) {
      // alert('qqq');  
      let str =  (arr[i].fileName).split('.');
      stepImg.setAttribute('src', '/ezfit/image/method/' + arr[i].fileName+'/'+str[str.length-1]);
    }
    else if (arr[i].picSrc == null || arr[i].picSrc == '') {
      stepImg.setAttribute('src', "img/publish_recipe/add_photo_small.svg");
    }
    else {
      stepImg.setAttribute('src', arr[i].picSrc);
    }


    // 右邊
    const stepSec = document.createElement('div');
    stepSec.setAttribute('class', "col-8 step-controller m-0 p-0");

    const stepNum = document.createElement('div');
    stepNum.setAttribute('class', "step-num");
    const stepIcons = document.createElement('div');
    stepIcons.setAttribute('class', "step-icons");
    //      +++++   handle拿到就不可以拖曳
    const addIcon = document.createElement('img');
    addIcon.setAttribute('src', "img/publish_recipe/icon_add.svg");
    addIcon.dataset.index = i;
    // addIcon.setAttribute('class',"handle");
    addIcon.addEventListener('click', function () {
      for (let i = 0; i < arr.length; ++i) {
        arr[i].detail = $('#directionText' + i).val();
        arr[i].picSrc = $('#stepImg' + i).attr('src');
      }
      // 轉型
      let num = Number(addIcon.dataset.index);
      arr.splice(1 + num, 0, {
        detail: null,
        picSrc: null,
      });
      console.log(arr);
      console.log('新增的位置在：' + (1 + num));
    })
    addIcon.addEventListener('click', createEle);
    const delIcon = document.createElement('img');
    delIcon.setAttribute('src', "img/publish_recipe/icon_delete.svg");
    // 只有一個步驟時，隱藏刪除鍵
    if (arr.length == 1) {
      delIcon.style.display = 'none';
    }
    // delIcon.setAttribute('class',"handle");
    delIcon.addEventListener('click', function () {
      for (let i = 0; i < arr.length; ++i) {
        arr[i].detail = $('#directionText' + i).val();
      }
      arr.splice(addIcon.dataset.index, 1);
      console.log(arr);
    });
    delIcon.addEventListener('click', createEle);
    // const dragIcon = document.createElement('img');
    // dragIcon.setAttribute('src', "img/publish_recipe/icon_drag.svg");
    // dragIcon.setAttribute('class',"handle");
    // dragIcon.addEventListener('click',function(){
    // })

    const stepDetail = document.createElement('div');
    stepDetail.setAttribute('class', "direction-text");

    const directionText = document.createElement('textarea');
    directionText.setAttribute('id', "directionText" + i);
    directionText.setAttribute('name', 'method' + i);
    directionText.setAttribute('cols', '30');
    directionText.setAttribute('rows', '10');
    directionText.setAttribute('maxlength', '150');
    directionText.innerHTML = arr[i].detail;
    let count = `<p class="mb-0 text-right direction-text-length">剩餘<span id="directionTextCount` + i + `">150</span>個字`;


    //// 左側元件 
    labelPic.appendChild(inputPic);

    picSec.appendChild(methodPk);
    picSec.appendChild(labelPic);
    picSec.appendChild(stepImg);
    // 右側元件
    stepIcons.appendChild(addIcon);
    stepIcons.appendChild(delIcon);
    // stepIcons.appendChild(dragIcon);
    stepSec.appendChild(stepNum);
    stepSec.appendChild(stepIcons);
    stepDetail.appendChild(directionText);
    stepSec.appendChild(stepDetail);
    $(stepDetail).append(count);
    // 合併
    methodBox.appendChild(picSec);
    methodBox.appendChild(stepSec);
    directions.appendChild(methodBox);

    //    動態新增JS(字數統計)  
    $('#directions').append(`<script>
        $(function(){ 
            $(document).keyup(function(){ 
              var text=$("#directionText`+ i + `").val(); 
              var counter= 150-(text.length); 
              $("#directionTextCount`+ i + `").text(counter); 
            })
          })
          $(function() {
            $( "#directions" ).sortable({ handle: '.handle', opacity:0.8, scroll:true});
          } );
          $(function() {
            $( "#ingredientItems`+ i + `" ).sortable({ handle: '.handle', opacity:0.8, scroll:true});
          } );
        
          $('#uploadStepImg`+ i + `').change(function () {
            var file = $('#uploadStepImg`+ i + `')[0].files[0];
            var reader = new FileReader;
            reader.onload = function (e) {
              $('#stepImg`+ i + `').attr('src', e.target.result);
            };
            reader.readAsDataURL(file);
          });
          


        </script>`);

    //  動態新增CSS
    $('#directions').append(`<style>
        #uploadStepImg`+ i + ` {
            font-size: 10px;
            color: white;
            opacity: 0;
            /* border: 1px solid red; */
          }
          #uploadStepImgLabel`+ i + ` {
            width: 100%;
            height: 195px;
            position: absolute;
            cursor: pointer;
            z-index: 1;
            /* border: 1px solid red; */
          }
          
          #uploadStepImgLabel`+ i + `:hover  {
            background: rgba(0, 0, 0, 0.1);
          }
          
          #stepImg`+ i + ` {
            object-fit: cover;
            overflow: hidden;
            position: relative;
            width: 100%;
            height: 195px;
          }     
          
          #directionText`+ i + ` {
            width: 100%;
            height: 130px;
            resize: none;
            border: 1px solid rgb(200, 200, 200);
          }
          
          .direction-text-length,
          #directionTextCount`+ i + ` {
            font-size: 12px;
            font-weight: 300;
            color: #3b3b3b;
          }
        </style>`)
  }
}

function addEle() {
  for (let i = 0; i < arr.length; ++i) {
    arr[i].detail = $('#directionText' + i).val();
    arr[i].picSrc = $('#stepImg' + i).attr('src');
  }
  arr.push({
    detail: null,
    picSrc: null,
  });
  console.log(arr);
}


function createMateral() {
  let parentMateral = document.getElementById('ingredientItems');
  $('#ingredientItems').html('');

  for (let i = 0; i < materal.length; ++i) {
    // for (let i = 0; i < 1; ++i) {
    const materalbox = document.createElement('div');
    materalbox.setAttribute('class', "ingredient-items");
    const materalPk = document.createElement('input');
    materalPk.setAttribute('type', 'hidden');
    materalPk.setAttribute('name', 'mlPk' + i);
    materalPk.setAttribute('value', '0');
    const materalName = document.createElement('input');
    const materalQty = document.createElement('input');
    materalName.setAttribute('type', 'text');
    materalName.setAttribute('class', "ingredient-name");
    materalName.setAttribute('placeholder', "請輸入食材名稱");
    materalName.setAttribute('name', "materalName" + i);
    materalName.setAttribute('id', "materalName" + i);
    materalName.setAttribute('value', materal[i].materalName);



    materalQty.setAttribute('type', 'text');
    materalQty.setAttribute('class', "ingredient-qty");
    materalQty.setAttribute('placeholder', "份量");
    materalQty.setAttribute('name', "unit" + i);
    materalQty.setAttribute('id', 'materalQty' + i);
    materalQty.setAttribute('value', materal[i].unit);



    const materalIcon = document.createElement('div');
    materalIcon.setAttribute('class', "ingredient-icons");
    const delIcon = document.createElement('img');
    delIcon.setAttribute('src', "img/publish_recipe/icon_delete.svg");
    delIcon.dataset.index = i;
    delIcon.addEventListener('click', function () {
      for (let i = 0; i < materal.length; ++i) {
        materal[i].materalName = $('#materalName' + i).val();
        materal[i].unit = $('#materalQty' + i).val();
      }
      materal.splice(delIcon.dataset.index, 1);
      console.log(materal);
    })
    delIcon.addEventListener('click', createMateral);
    // delIcon.setAttribute('class', "handle");
    const dragIcon = document.createElement('img');
    // dragIcon.setAttribute('src',"img/publish_recipe/icon_drag.svg" );
    // dragIcon.setAttribute('class',"handle");

    materalIcon.appendChild(dragIcon);
    materalIcon.appendChild(delIcon);
    materalbox.appendChild(materalPk);
    materalbox.appendChild(materalIcon);
    materalbox.appendChild(materalName);
    materalbox.appendChild(materalQty);
    parentMateral.appendChild(materalbox);



  }
}


function addMateral() {
  for (let i = 0; i < materal.length; ++i) {
    materal[i].materalName = $('#materalName' + i).val();
    materal[i].unit = $('#materalQty' + i).val();
  }
  materal.push({
    materalName: "",
    unit: '',
  });
  console.log(materal);

}

function getParameterByName(name, url) {
  if (!url) url = window.location.href;
  name = name.replace(/[\[\]]/g, '\\$&');
  var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
    results = regex.exec(url);
  if (!results) return null;
  if (!results[2]) return '';
  return decodeURIComponent(results[2].replace(/\+/g, ' '));
}