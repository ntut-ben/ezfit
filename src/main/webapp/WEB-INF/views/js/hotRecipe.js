$(document).ready(function () {
    //  熱門食譜
    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/ezfit/recipe/hotRecipe.do',

        success: function (data) {
            // console.log('收到回傳data:' + data);
            // let recipe = JSON.parse(data);
            let recipe = data;
            // alert(recipe[0].recipeName);

            for (let i = 0; i < 4; ++i) {
                // var hotRecipe(i+1) = recipe[i];   動態命名變數
                eval('var hotRecipe' + (i + 1) + '=recipe[' + i + '];');
            }
            // 用動態新增會跑掉，所以不換了
            // ========top1Recipe========
            let recipe1 = document.getElementById('topOneRecipe');
            // <h5>食譜名
            recipe1.childNodes[1].innerHTML = hotRecipe1.recipeName;
            // <p>作者
            recipe1.childNodes[3].innerHTML = 'by ' + hotRecipe1.member.name;

            // <p>簡介
            recipe1.childNodes[7].innerHTML = hotRecipe1.introduction;

            // <span> 收藏  記得把<a> <img>中間的空白刪掉，不然DOM蒐尋器會找錯

            let likesComments1 = document.getElementById('likesComments1');

            likesComments1.childNodes[1].childNodes[2].innerHTML = hotRecipe1.save;

            likesComments1.childNodes[3].childNodes[2].innerHTML = hotRecipe1.chat;

            // 設定A標籤連結去食譜
            let totalLink = document.getElementsByClassName('link');
            // console.log(totalLink.length);
            for (let i = 0; i < 4; ++i) {
                // totalLink[i].href = '/WEB-INF/views/recipe_page.jsp?recipeId=' + recipe[0].recipeId;
                totalLink[i].href = 'http://localhost:8080/ezfit/recipe_page?recipeId=' + recipe[0].recipeId;
            }
            for (let i = 4; i < 8; ++i) {
                // totalLink[i].href = '/WEB-INF/views/recipe_page.jsp?recipeId=' + recipe[1].recipeId;
                totalLink[i].href = 'http://localhost:8080/ezfit/recipe_page?recipeId=' + recipe[1].recipeId;
            }
            for (let i = 8; i < 12; ++i) {
                // totalLink[i].href = '/WEB-INF/views/recipe_page.jsp?recipeId=' + recipe[2].recipeId;
                totalLink[i].href = 'http://localhost:8080/ezfit/recipe_page?recipeId=' + recipe[2].recipeId;
            }
            for (let i = 12; i < 16; ++i) {
                // totalLink[i].href = '/WEB-INF/views/recipe_page.jsp?recipeId=' + recipe[3].recipeId;
                totalLink[i].href = 'http://localhost:8080/ezfit/recipe_page?recipeId=' + recipe[3].recipeId;
            }

            // 抓食譜圖片
            let totalRecipePic = document.getElementsByClassName('recipePic');
            // console.log(totalRecipePic.length);
            for (let i = 0; i < totalRecipePic.length; ++i) {
                if (!recipe[i].fileName == undefined || !recipe[i].fileName == "") {
                    // console.log('ttttttt='+recipe[i].fileName);
                    let str = (recipe[i].fileName).split('.');
                    totalRecipePic[i].src = "/ezfit/image/recipe/" + recipe[i].fileName + '/' + str[str.lrngth - 1];
                } else {
                    totalRecipePic[i].src = "img/publish_recipe/add_photo_small.svg"
                }
            }



            // ========top2Recipe========
            let recipe2 = document.getElementById('topTwoRecipe');
            // <h5>食譜名
            recipe2.childNodes[1].innerHTML = hotRecipe2.recipeName;

            // <p>作者
            recipe2.childNodes[3].innerHTML = 'by ' + hotRecipe2.member.name;

            // <p>簡介
            recipe2.childNodes[5].innerHTML = hotRecipe2.introduction;
            // alert(recipe2.childNodes[5].nodeName);
            // <span> 收藏  記得把<a> <img>中間的空白刪掉，不然DOM蒐尋器會找錯

            let likesComments2 = document.getElementById('likesComments2');

            likesComments2.childNodes[1].childNodes[2].innerHTML = hotRecipe2.save;

            likesComments2.childNodes[3].childNodes[2].innerHTML = hotRecipe2.chat;

            // alert(likesComments2.childNodes[3].nodeName);


            // ========top3Recipe========

            let recipe3 = document.getElementById('topThreeRecipe');
            // <h5>食譜名
            recipe3.childNodes[1].innerHTML = hotRecipe3.recipeName;

            // <p>作者
            recipe3.childNodes[3].innerHTML = 'by ' + hotRecipe3.member.name;

            // <p>簡介
            recipe3.childNodes[5].innerHTML = hotRecipe3.introduction;
            // alert(recipe2.childNodes[5].nodeName);
            // <span> 收藏  記得把<a> <img>中間的空白刪掉，不然DOM蒐尋器會找錯

            let likesComments3 = document.getElementById('likesComments3');


            likesComments3.childNodes[1].childNodes[2].innerHTML = hotRecipe3.save;


            likesComments3.childNodes[3].childNodes[2].innerHTML = hotRecipe3.chat;


            // ========top4Recipe========

            let recipe4 = document.getElementById('top4Recipe');
            // <h5>食譜名
            recipe4.childNodes[1].innerHTML = hotRecipe4.recipeName;

            // <p>作者
            recipe4.childNodes[3].innerHTML = 'by ' + hotRecipe4.member.name;

            // <p>簡介
            recipe4.childNodes[5].innerHTML = hotRecipe4.introduction;
            // alert(recipe2.childNodes[5].nodeName);
            // <span> 收藏  記得把<a> <img>中間的空白刪掉，不然DOM蒐尋器會找錯

            let likesComments4 = document.getElementById('likesComments4');


            likesComments4.childNodes[1].childNodes[2].innerHTML = hotRecipe4.save;


            likesComments4.childNodes[3].childNodes[2].innerHTML = hotRecipe4.chat;










        }
    });

    //  熱門關鍵字
    $.ajax({
        method: "POST",
        url: ' http://localhost:8080/ezfit/keyword/submit.do',
        data: {
            name: 'hothothot'
        },
        success: function (data) {
            // console.log('收到回傳data:' + data);
            // let hotSearchList = JSON.parse(data);
            let hotSearchList = data;
            let listSize = hotSearchList.length;
            // alert(listSize);

            for (let i = 0; i < listSize; ++i) {
                // var hotRecipe(i+1) = recipe[i];   動態命名變數
                eval('var hotSearch' + (i + 1) + '=hotSearchList[' + i + '].keyWord;');
                // console.log(eval('hotSearch' + (i + 1)));
                let a = document.createElement("a");
                a.setAttribute("class", "badge badge-success");
                // URI還沒設
                // a.setAttribute("href",  eval(URI+'hotSearch' + (i + 1)));

                a.innerHTML = eval('hotSearch' + (i + 1));
                let parent = document.getElementById('hotSearch');
                parent.appendChild(a);

            }






        }


    })

    $('#writeRecipe').click(writeRecipe);

    // 本週新食譜
    $.ajax({
        method: "POST",
        url: 'http://localhost:8080/ezfit/recipe/weekRecipe',

        success: function (data) {
            // let list = JSON.parse(data);
            let list = data;
            // console.log(list);

            //動態新增
            // let  weekRecipeParent = document.getElementById('weekRecipeParent');
            for (let i = 0; i < list.length; ++i) {
                let weekRecipe = `<div class="col-3 m-0 p-2 new-recipe-item">
                <div class="new-recipes-img">
                    <a href="http://localhost:8080/ezfit/recipe_page?recipeId=`+ list[i].recipeId + `"><img src="http://placekitten.com/186/132" alt="..." id="weekPic` + i + `"></a>
                </div>
                <div class="new-recipe-item-text">
                    <h5><a href="http://localhost:8080/ezfit/recipe_page?recipeId=`+ list[i].recipeId + `">` + list[i].recipeName + `</a></h5>
                    <p><a href="http://localhost:8080/ezfit/recipe_page?recipeId=`+ list[i].recipeId + `">` + list[i].member.name + `</a></p>
                </div>
            </div>`;
                $('#weekRecipeParent').append(weekRecipe);

                // 設定食譜圖片
                if (list[i].fileName !== undefined && list[i].fileName.length > 0) {
                    // console.log(i+'fileName = '+list[i].fileName.length);
                    let weekPic = document.getElementById('weekPic' + i);
                    let str = (list[i].fileName).split('.');
                    weekPic.src = "/ezfit/image/recipe/" + list[i].fileName + '/' + str[str.length - 1];
                    console.log('檔案名' + str[str.length - 1]);
                } else {
                    let weekPic = document.getElementById('weekPic' + i);
                    weekPic.src = "img/publish_recipe/add_photo_small.svg";
                }

            }


        }


    });

});



function writeRecipe() {
    window.location.href = 'http://localhost:8080/ezfit/publish_recipe';
};
