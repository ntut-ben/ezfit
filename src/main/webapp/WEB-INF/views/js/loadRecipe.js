$(document).ready(function () {

    // 從搜尋字串取得recipeId 
    // 如果有帶食譜ID，就載入
    let url = location.href;
    // console.log(url);
    let recipeId = getParameterByName('recipeId', url);
    // alert(recipeId);



    // let recipe = 11;
    if(recipeId != null){
        $.ajax({
            method: "GET",
            url: 'http://localhost:8080/ezfit/recipe/editRecipe',
            data: {
                recipeId: recipeId
            },
            success: function (data) {
    
                // ========暫時========
                arr = [{ detail: null, picSrc: null ,id:0},];
                materal = [{ materalName: '', unit: '' ,materal_Id:0},];
    
                // ====================
    
    
                console.log('收到回傳data:' + data);
                // let dataList = JSON.parse(data);
                let dataList = data;
                let methodLength = dataList[1];
                let materalLength = dataList[2];
                console.log(methodLength);
                console.log(materalLength);
                console.log(dataList);
                // 
                // 處理recipe
                // pk
                let recipeId = document.getElementById('recipePk');
                recipeId.setAttribute('value', dataList[0].recipeId);
                // console.log(recipeId.value);
    
                // recipeName
                let recipeNameInput = document.getElementById('recipeName');
                recipeNameInput.setAttribute('value', dataList[0].recipeName);
                // console.log(recipeNameInput.value);
    
                // recipeImg
                let coverImg = document.getElementById('coverImg');
                if(dataList[0].fileName== null || dataList[0].fileName==''){
                    coverImg.setAttribute('src', "img/publish_recipe/add_photo_small.svg");
                }else{
                	let str = (dataList[0].fileName).split('.');
                    coverImg.setAttribute('src', '/ezfit/image/recipe/' + dataList[0].fileName+'/'+str[str.length-1]);
                }
    
                // spendTime & servings
                let spendTime = document.getElementById('spendTime');
                // spendTime.setAttribute('value', data[0].spendTime);
                let spendTimeList = spendTime.getElementsByTagName('option');
                if (dataList[0].spendTime == null) {
                    spendTimeList[0].selected = true;
                } else if (dataList[0].spendTime == 5) {
                    spendTimeList[1].selected = true;
                } else if (dataList[0].spendTime == 10) {
                    // alert('11');
                    spendTimeList[2].selected = true;
                } else if (dataList[0].spendTime == 15) {
                    spendTimeList[3].selected = true;
                } else if (dataList[0].spendTime == 20) {
                    spendTimeList[4].selected = true;
                } else if (dataList[0].spendTime == 30) {
                    spendTimeList[5].selected = true;
                } else if (dataList[0].spendTime == 45) {
                    spendTimeList[6].selected = true;
                } else if (dataList[0].spendTime == 60) {
                    spendTimeList[7].selected = true;
                } else if (dataList[0].spendTime == 90) {
                    spendTimeList[8].selected = true;
                } else if (dataList[0].spendTime == 120) {
                    spendTimeList[9].selected = true;
                } else if (dataList[0].spendTime == '180+') {
                    spendTimeList[10].selected = true;
                }
                let servings = document.getElementById('servings');
                let servingsList = servings.getElementsByTagName('option');
                if (dataList[0].servings == null) {
                    servingsList[0].selected = true;
                } else if (dataList[0].servings == 2) {
                    servingsList[1].selected = true;
                } else if (dataList[0].servings == 4) {
                    servingsList[2].selected = true;
                } else if (dataList[0].servings == 6) {
                    servingsList[3].selected = true;
                } else if (dataList[0].servings == 8) {
                    servingsList[4].selected = true;
                } else if (dataList[0].servings == 10) {
                    servingsList[5].selected = true;
                } else if (dataList[0].servings == 12) {
                    servingsList[6].selected = true;
                }
    
                // 簡介
                // let brief = document.getElementById('brief');
                $("#brief").val(dataList[0].introduction);
                // $('#brief').val() = 'helloworld';
                // brief.innerHTML = dataList[0].introduction;
    
    
                // 步驟
                //dataList[3]     length = dataList[1]
                // 清空arr
                arr = [];
                // console.log((dataList[3][1]));
                // console.log((dataList[3][1].id));
                for (let i = 0; i < dataList[1]; ++i) {
                    arr.push(dataList[3][i]);
    
                }
                console.log('arr = ');
                console.log(arr);
                createEle();
    
    
    
    
                // 食材
                // dataList[4]   length = dataList[2]
                materal = [];
                for (let i = 0; i < dataList[2]; ++i) {
                    materal.push(dataList[4][i]);
    
                }
                console.log('materal = ');
                console.log(materal);
                createMateral();
    
    
    
    
                // 
                // 
    
    
                // ========暫時========
    
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
                        methodPk.setAttribute('value', arr[i].id);
                        const labelPic = document.createElement('label');
                        labelPic.setAttribute('id', 'uploadStepImgLabel' + i);
                        const inputPic = document.createElement('input');
                        inputPic.setAttribute('type', 'file');
                        inputPic.setAttribute('id', 'uploadStepImg' + i);
                        inputPic.setAttribute('accept', "image/*");
                        inputPic.setAttribute('name', "methodPic" + i);
    
                        const stepImg = document.createElement('img');
                        stepImg.setAttribute('id', 'stepImg' + i);
                        if ((arr[i].fileName !== null && (arr[i].fileName !== undefined))) {
                            console.log('111'+arr[i].fileName);
                            // alert('qqq');   
                            let str = (arr[i].fileName).split('.');
                            stepImg.setAttribute('src', '/ezfit/image/method/' + arr[i].fileName+'/'+str[str.length-1]);
                        }
                        else if (arr[i].picSrc == null || arr[i].picSrc == '') {
                            stepImg.setAttribute('src', "img/publish_recipe/add_photo_small.svg");
                            // console.log('fine');
                        }
                        else {
                            stepImg.setAttribute('src', arr[i].picSrc);
                            console.log('QAQ');
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
                        // directionText.innerHTML = arr[i].detail;
                        directionText.value = arr[i].detail;
                        // $('#directionText'+i).val(arr[i].detail);
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
                        materalPk.setAttribute('value', materal[i].materal_Id);
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
    
    
                // ====================
            }
        });
    }
    
})


function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
  }