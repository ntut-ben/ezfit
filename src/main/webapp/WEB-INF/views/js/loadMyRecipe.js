var url = location.href;
var ownerId = getParameterByName('ownerId', url);
var page = getParameterByName('page', url);
var year = getParameterByName('year', url);
var totalPage = 0;
$(document).ready(function start() {
    console.log('page=' + page);




    // 動態新增準備
    let recipeParent = document.getElementById('recipeParent');

  // 設定按鈕
  $('#writeRecipe').click(toWriteRecipe);



    // 取得該頁面會員資訊

    $.ajax({
        method: "POST",
        url: 'http://localhost:8080/ezfit/whoAreYou',
        data: {
            ownerId: ownerId
        },
        success: function (data) {
            // let list = JSON.parse(data);
            let list = data;
            console.log('data=' + list);
            console.log("aboutmember=" + list[1]);
            if (list[0].pkey === ownerId) {
                document.getElementById('whosRecipe').innerHTML = "我的食譜";
                // document.getElementById('memberIntro').innerHTML = list[0].introduction;
                document.getElementById('memberName').innerHTML = list[0].name;


            }
            else if (list[0].name != undefined && list[0].pkey !== ownerId) {
                document.getElementById('whosRecipe').innerHTML = String(list[0].name) + "的食譜";
                document.getElementById('memberIntro').innerHTML = list[0].introduction;
                document.getElementById('memberName').innerHTML = list[0].name;

                // // 放年份對應食譜數 & link
                // document.getElementById('year2019').innerHTML = list[1];
                // document.getElementById('year2018').innerHTML = list[2];
                // document.getElementById('year2017').innerHTML = list[3];
                // document.getElementById('year2016').innerHTML = list[4];
                // document.getElementById('link2019').href = `/my_page_v1/my_page.jsp?ownerId=${ownerId}&page=1&year=2019`
                // document.getElementById('link2018').href = `/my_page_v1/my_page.jsp?ownerId=${ownerId}&page=1&year=2018`
                // document.getElementById('link2017').href = `/my_page_v1/my_page.jsp?ownerId=${ownerId}&page=1&year=2017`
                // document.getElementById('link2016').href = `/my_page_v1/my_page.jsp?ownerId=${ownerId}&page=1&year=2016`

            } else {
                alert('查無此人');
                window.location.href = 'http://localhost:8080/ezfit/recipe_main';
            }

            if (list[0].name != undefined) {
                // 放年份對應食譜數 & link
                document.getElementById('year2019').innerHTML = list[1];
                document.getElementById('year2018').innerHTML = list[2];
                document.getElementById('year2017').innerHTML = list[3];
                document.getElementById('year2016').innerHTML = list[4];

                document.getElementById('link2019').href = `http://localhost:8080/ezfit/my_page?ownerId=${ownerId}&page=1&year=2019`
                document.getElementById('link2018').href = `http://localhost:8080/ezfit/my_page?ownerId=${ownerId}&page=1&year=2018`
                document.getElementById('link2017').href = `http://localhost:8080/ezfit/my_page?ownerId=${ownerId}&page=1&year=2017`
                document.getElementById('link2016').href = `http://localhost:8080/ezfit/my_page?ownerId=${ownerId}&page=1&year=2016`
                if(list[0].coverImg!= undefined){
                    console.log('profileImg = '+list[0].coverImg);
                    document.getElementById('profileCover').src = `/ezfit/data/memberPic/${list[0].coverImg}`;
                }


            }

        }
    });




    // 處理中間食譜區域
    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/ezfit/myRecpie.do',
        data: {
            ownerId: ownerId,
            year: year,
        },
        success: function (data) {
            // let list = JSON.parse(data);
            let list = data;
            console.log(list);
            var publish = list[list.length - 1];
            console.log('是否是自己的食譜：' + list[list.length - 1]);
            var target = splitArray(list);
            // console.log('轉檔後=' + target);
            // console.log(target.length);
            // console.log(target[0]);


            let arrayLength = list.length;
            let perRecipe = 9;
            totalPage = Math.floor((arrayLength - 1) / perRecipe) + 1;
            // console.log('totalPage=' + totalPage);


            // console.log('target[1] length=' + target[1].length);
            for (let i = 0; i < target[page - 1].length; ++i) {



                let addStr = `
            <div class="col-4 p-2 m-0">
                <div class="recipe-item-cards">
                    <a href="http://localhost:8080/ezfit/recipe_page?recipeId=`+ target[page - 1][i].recipeId + `">
                        <div class="recipe-cover-img">
                            <img src="https://picsum.photos/255/255"alt="" id="recipePic`+ i + `">
                        </div>
                        <div class="recipe-info p-2">
                            <div class="recipe-publish-time">`+
                    target[page - 1][i].recipeCreateTime
                    + `</div>
                            <div class="recipe-title mt-2">`+
                    target[page - 1][i].recipeName
                    + `</div>
                            <div class="recipe-description mt-2">`+
                    target[page - 1][i].introduction
                    + `</div>
                        </div>
                    </a>
                    <div class="like-comment-icons mt-3 p-2">
                        <div class="comments">
                            <img src="img/my_page/icons/icon-comment.svg" alt="">
                            <span>`+ target[page - 1][i].chat + `</span>
                        </div>
                        <div class="likes">
                            <img src="img/my_page/icons/icon-like.svg" alt=""> <span>`+ target[page - 1][i].good + `</span>
                        </div>
                        <button class="canEdit" onclick="editBtn(`+ target[page - 1][i].recipeId + `)">編輯</button>
                        <button class="canEdit" onclick="delBtn(`+ target[page - 1][i].recipeId + `)">刪除</button>
                    </div>
                </div>
            </div>`;

                // 動態新增食譜
                $('#recipeParent').append(addStr);

                // 修改食譜圖片
                let recipePic = document.getElementById('recipePic' + i);
                if (target[page - 1][i].fileName !== undefined && target[page - 1][i].fileName.length > 0) {
                    recipePic.src = "/ezfit/data/recipePic/" + target[page - 1][i].fileName;
                } else {
                    recipePic.src = "img/publish_recipe/add_photo_small.svg";
                }
            }

            let canEdit = document.getElementsByClassName('canEdit');

            // console.log('1104:'+publish);
            if (publish == false) {
                // console.log('testtesttset'+list[list.length - 1]);
                document.getElementById('dropdownMenuButton').style.display = 'none';
                for (let j = 0; j < canEdit.length; ++j) {
                    canEdit[j].style.display = 'none';
                }

            }


            let add = '';
            for (let i = 1; i <= totalPage; ++i) {
                add += `<li class="page-item"><a class="page-link" href="http://localhost:8080/ezfit/my_page?ownerId=${ownerId}&page=${i}&year=${year}">${i}</a></li>`
            }
            // let str = `test${page}etstests`

            let addPage = ` 
            <li class="page-item" id="leftBtn">
                <a class="page-link" href="http://localhost:8080/ezfit/my_page?ownerId=${ownerId}&page=${Number(page) - 1}&year=${year}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>`+ add + `
            <li class="page-item" id="rightBtn">
                <a class="page-link" href="http://localhost:8080/ezfit/my_page?ownerId=${ownerId}&page=${Number(page) + 1} &year=${year}"aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>`;
            // console.log('add='+String(add));
            $('#pageNumber').html('');
            $('#pageNumber').append(addPage);
            console.log('leng===' + list.length);
            if (page == 1) {
                document.getElementById('leftBtn').style.display = "none";
            }
            if (list.length < 1) {
                document.getElementById('rightBtn').style.display = "none";
            }
            if (page == totalPage) {
                document.getElementById('rightBtn').style.display = "none";
            }

        }



    });

    // 處理編輯食譜
    // 修改自介
    $('#introBtn').click(function () {
        let introduction = $('#editDescriptionText').val();
        // console.log('introduction=='+introduction);
        // console.log('要更改了喔');
        document.getElementById('memberIntro').innerHTML = introduction;

        $.ajax({
            method: "POST",
            url: 'http://localhost:8080/ezfit/editMemberIntro',
            data: {
                ownerId: ownerId,
                introduction: introduction,
            },
            success: function (data) {
                console.log('editIntro=='+data);
                console.log('complete!!!');
                window.location.href = `http://localhost:8080/ezfit/my_page?ownerId=${ownerId}&page=${page}&year=${year}`;
            }
        });

    })

    // 修改圖片
    let uploadPic = document.getElementById('uploadCoverImg');
    let file;
    uploadPic.addEventListener('change', (e) => {
        file = (e.target.files[0]);
        console.log('file = ' + file);
        console.log('name = ' + file.name);
        console.log('size = ' + file.size);
        console.log('type = ' + file.type);
        // use FormData
        let form = new FormData();
        form.append("test", file);
        // ===================================================
        // let pic = JSON.stringify(form);


        // jQuery
        $.ajax({
            method: "POST",
            url: 'http://localhost:8080/ezfit/changeMemberPic',
            contentType: false,//當form以multipart/form-data方式上傳檔案時，需要設定為false
            processData: false,
            data: form,
            success: function () {
                console.log('test Form Data!!!');
            },
        });








    })








});

// 抓URL Parameter
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}


// 編輯Btn
function editBtn(data) {
    window.location.href = 'http://localhost:8080/ezfit/publish_recipe?recipeId=' + data;
}


// 刪除Btn
function delBtn(data) {
    let msg = "您真的要刪除嗎？"
    var delRecipe = data;
    if (confirm(msg) === true) {
        $.ajax({
            method: "POST",
            url: ' http://localhost:8080//ezfit//recipe//delete',
            data: {
                published: 'delete',
                pk: delRecipe
            },
            success: function (data) {
                if (data === 'done')
                    console.log('done');
                window.location.href = 'http://localhost:8080/ezfit/my_page?ownerId=' + ownerId;
            }
        });
    }
}






// 將資料案頁數分裝到陣列中
function splitArray(array) {
    array.splice((array.length - 1), 1);
    let arrayLength = array.length;
    let perRecipe = 9;
    let page = Math.floor((arrayLength) / perRecipe) + 1;
    var target = [];
    for (let n = 0; n < page; ++n) {
        let arr = [];
        for (let i = (n * perRecipe); i < (n + 1) * perRecipe; ++i) {
            if (array[i] !== undefined) {
                arr.push(array[i]);
            }

        }
        target.push(arr);
        arr = [];
    }


    return target;


}
function toWriteRecipe() {
    window.location.href = 'http://localhost:8080/ezfit/publish_recipe';

}