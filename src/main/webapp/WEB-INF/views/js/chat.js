var materal = [];
let login = null;

$(document).ready(function () {

    data = [{ id: '會員名稱', detail: '留言內容。作者可直接回覆留言 or not ？', time: '2天前 （或是直接顯示日期＋時間？）' },];

    // 從搜尋字串取得recipeId 
    // 如果有帶食譜ID，就載入
    let url = location.href;
    // console.log(url);
    const recipeId = getParameterByName('recipeId', url);
    // alert(recipeId);


    // 判斷是否有登入，並布置留言區預設頭像

    $.ajax({
        method: "POST",
        url: 'http://localhost:8080/ezfit/getMember',

        success: function (data) {
            login = data;
            console.log('success???'+login);
            // 偷布置留言區的使用者頭像
            let head2 = document.getElementById('head');
            if (login.memberImage !== undefined) {
                head2.setAttribute('src', '/ezfit/image/memberHead/' + login.memberImage + '/jpg');
            } else {
                // 預設有點醜，考慮要不要換
                head2.setAttribute('src', 'index/img/nav/logo-g.svg');
            }

        }
    })









    // 處理收藏食譜按鈕
    let btn = document.getElementById('followBtn');

    // 未收藏
    let unsave = '<img src="img/recipe_page/icon-heart-solid.svg" class="pb-1 pr-1" alt="" >' + ' 收 藏 ';
    // 已收藏
    let saved = '<img src="img/recipe_page/icon-heart-solid.svg" class="pb-1 pr-1" alt="" >' + ' 已 收 藏 ';

    // 確認是否追蹤該食譜
        $.ajax({
            method: "GET",
            url: 'http://localhost:8080/ezfit/checkedFollowed',
            data: {
                recipeId: recipeId,
            },
            success: function (data) {
                // let ans = JSON.parse(data);
                let ans = data
                // console.log(ans);

                if (ans === 'notFound') {
                    btn.innerHTML = unsave;
                    btn.dataset.followId = ans;
                    btn.className = "btn btn-success"
                } else {
                    btn.innerHTML = saved;
                    btn.dataset.followId = ans;
                    btn.className = "btn btn-secondary";
                }
            }
        });
    

    // 按鈕觸發事件
    // if (btn.innerHTML = unsave) {
    btn.addEventListener('click', function () {
        // console.log('要追蹤食譜');
        // console.log('追蹤相關');
        $.ajax({
            method: "POST",
            url: 'http://localhost:8080/ezfit/aboutSave',
            data: {
                recipeId: recipeId,
                followId: btn.dataset.followId,
            },
            success: function (data) {
                // console.log('QQQQQ' + data);
                if (data.length > 0) {
                    // let back = JSON.parse(data);
                    let back = data;
                    btn.dataset.followId = back.pk;
                    // 重新確認食譜蒐藏狀態
                    $.ajax({
                        method: "GET",
                        url: 'http://localhost:8080/ezfit/checkedFollowed',
                        data: {
                            recipeId: recipeId,
                        },
                        success: function (data) {
                            // let ans = JSON.parse(data);
                            let ans = data;
                            // console.log(ans);

                            if (ans === 'notFound') {
                                btn.innerHTML = unsave;
                                btn.dataset.followId = ans;
                                btn.className = "btn btn-success"
                            } else {
                                btn.innerHTML = saved;
                                btn.dataset.followId = ans;
                                btn.className = "btn btn-secondary";
                            }
                        }
                    });

                    // 佈置食譜資訊區，已更新收藏筆數
                    $.ajax({
                        method: "GET",
                        url: 'http://localhost:8080/ezfit/recipe/editRecipe',
                        data: {
                            recipeId: recipeId,
                        },
                        success: function (data) {
                            // list = JSON.parse(data);
                            list = data;
                            // console.log(list);
                            // 佈置食譜相關資訊
                            let recipeName = document.getElementById('recipeName');
                            recipeName.innerHTML = list[0].recipeName;
                            let save = document.getElementById('save');
                            save.innerHTML = list[0].save;
                            let chat = document.getElementById('chat');
                            chat.innerHTML = list[0].chat;
                            let recipePic = document.getElementById('recipePic');

                            // recipePic.setAttribute('src', '/ezfit/image/recipe/' + list[0].fileName);
                            // let introduction = document.getElementById('introduction');
                            // introduction.innerHTML = list[0].introduction;
                            // let servings = document.getElementById('servings');
                            // servings.innerHTML = '<img src="img/recipe_page/icon-servings.svg" alt="" >份量：' + list[0].servings + '人份';
                            // let spendTime = document.getElementById('spendTime');
                            // spendTime.innerHTML = '<img src="img/recipe_page/icon-cookingtime.svg" alt="" id="spendTime">時間：' + list[0].spendTime + '分鐘';
                        }
                    });
                } else {
                    btn.dataset.followId = 'unfollowed';
                    // 重新確認食譜蒐藏狀態
                    $.ajax({
                        method: "GET",
                        url: 'http://localhost:8080/ezfit/checkedFollowed',
                        data: {
                            recipeId: recipeId,
                        },
                        success: function (data) {
                            // let ans = JSON.parse(data);
                            let ans = data;

                            if (ans === 'notFound') {
                                btn.innerHTML = unsave;
                                btn.dataset.followId = ans;
                                btn.className = "btn btn-success"
                            } else {
                                btn.innerHTML = saved;
                                btn.dataset.followId = ans;
                                btn.className = "btn btn-secondary";
                            }
                        }
                    });

                    // 佈置食譜資訊區，已更新收藏筆數
                    $.ajax({
                        method: "GET",
                        url: 'http://localhost:8080/ezfit/recipe/editRecipe',
                        data: {
                            recipeId: recipeId,
                        },
                        success: function (data) {
                            // list = JSON.parse(data);
                            list = data;
                            // console.log(list);
                            // 佈置食譜相關資訊
                            // let recipeName = document.getElementById('recipeName');
                            // recipeName.innerHTML = list[0].recipeName;
                            let save = document.getElementById('save');
                            save.innerHTML = list[0].save;
                            let chat = document.getElementById('chat');
                            chat.innerHTML = list[0].chat;
                            // let recipePic = document.getElementById('recipePic');
                            // if ((list[0].fileName !== null && (list[0].fileName !== undefined))) {
                            //     recipePic.setAttribute('src', '/ezfit/image/recipe/' + list[0].fileName);
                            // } else {
                            //     recipePic.setAttribute('src', 'img/publish_recipe/add_photo_small.svg')
                            // }
                            // let introduction = document.getElementById('introduction');
                            // introduction.innerHTML = list[0].introduction;
                            // let servings = document.getElementById('servings');
                            // servings.innerHTML = '<img src="img/recipe_page/icon-servings.svg" alt="" >份量：' + list[0].servings + '人份';
                            // let spendTime = document.getElementById('spendTime');
                            // spendTime.innerHTML = '<img src="img/recipe_page/icon-cookingtime.svg" alt="" id="spendTime">時間：' + list[0].spendTime + '分鐘';



                        }
                    });

                }
            }
        })



    })









    // 設定按鈕
    $('#writeRecipe').click(toWriteRecipe);

    // 載入畫面時，佈置食譜
    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/ezfit/recipe/editRecipe',
        data: {
            recipeId: recipeId,
        },
        success: function (data) {
            // list = JSON.parse(data);
            list = data;
            console.log(list);



            // 佈置食譜相關資訊
            let recipeName = document.getElementById('recipeName');
            recipeName.innerHTML = list[0].recipeName;
            let save = document.getElementById('save');
            save.innerHTML = list[0].save;
            let chat = document.getElementById('chat');
            chat.innerHTML = list[0].chat;
            let recipePic = document.getElementById('recipePic');
            console.log('recipePic.fileName = ' + list[0].fileName);
            let head = document.getElementById('memberHeadPic');
            console.log('memberPic=' + list[0].member.memberImage);
            if (list[0].member.memberImage !== undefined) {
                head.setAttribute('src', '/ezfit/image/memberHead/' + list[0].member.memberImage + '/jpg');
            } else {
                // 預設有點醜，考慮要不要換
                head.setAttribute('src', 'index/img/nav/logo-g.svg');
            }
            if ((list[0].fileName !== "" && (list[0].fileName !== undefined))) {
                console.log('testtest' + list[0].fileName);
                let str = (list[0].fileName).split('.');
                recipePic.setAttribute('src', '/ezfit/image/recipe/' + list[0].fileName + '/' + str[str.length - 1]);
            } else {
                console.log('NONONONONO' + list[0].fileName);

                recipePic.setAttribute('src', 'img/publish_recipe/add_photo_small.svg')
            }
            let introduction = document.getElementById('introduction');
            introduction.innerHTML = list[0].introduction;
            let servings = document.getElementById('servings');
            servings.innerHTML = '<img src="img/recipe_page/icon-servings.svg" alt="" >份量：' + list[0].servings + '人份';
            let spendTime = document.getElementById('spendTime');
            spendTime.innerHTML = '<img src="img/recipe_page/icon-cookingtime.svg" alt="" id="spendTime">時間：' + list[0].spendTime + '分鐘';

            // 佈置食材
            // 動態新增
            let materalList = list[4];
            for (let i = 0; i < materalList.length; ++i) {
                let ingredient = document.createElement('div');
                ingredient.setAttribute('class', 'ingredient');
                let materalName = document.createElement('div');
                materalName.setAttribute('class', 'ingredient-name');
                // materalName.innerHTML = materalList[i].materalName;
                let linkMateral = document.createElement('a');
                linkMateral.setAttribute('href', '#');
                linkMateral.innerHTML = materalList[i].materalName;

                let materalUnit = document.createElement('div');
                materalUnit.setAttribute('class', 'ingredient-unit');
                // materalUnit.innerHTML = materalList[i].materalName;
                let linkUnit = document.createElement('a');
                linkUnit.setAttribute('href', '#');
                linkUnit.innerHTML = materalList[i].unit;


                // parentNode
                let ingredients = document.getElementById('ingredients');

                // 佈置頁面
                materalName.appendChild(linkMateral);
                ingredient.appendChild(materalName);
                ingredient.appendChild(linkUnit);
                ingredients.appendChild(ingredient);


                // 將食材名裝進array中
                materal.push(materalList[i].materalName);
            }

            // 作法佈置
            let methodList = list[3];
            for (let i = 0; i < methodList.length; ++i) {
                // parentNode
                let methodSec = document.getElementById('methodSec');

                // 動態新增
                let instructionStep = document.createElement('div');
                instructionStep.setAttribute('class', 'instruction-step');

                // 圖片區
                let stepImg = document.createElement('div');
                stepImg.setAttribute('class', "step-img");
                let methodPic = document.createElement('img');
                if ((methodList[i].fileName !== null && (methodList[i].fileName !== undefined))) {
                    let str = (methodList[i].fileName).split('.');
                    methodPic.setAttribute('src', '/ezfit/image/method/' + methodList[i].fileName + '/' + str[str.length - 1]);
                } else {
                    methodPic.setAttribute('src', 'img/publish_recipe/add_photo_small.svg')
                }

                // 內容區
                let step = document.createElement('div');
                step.setAttribute('class', 'step');
                let stepNumber = document.createElement('div');
                stepNumber.setAttribute('class', "step-number");
                stepNumber.innerHTML = i + 1;
                let stepInstruction = document.createElement('div');
                stepInstruction.setAttribute('class', "step-instruction");
                stepInstruction.innerHTML = methodList[i].detail;


                // 佈置頁面


                stepImg.appendChild(methodPic);
                step.appendChild(stepNumber);
                step.appendChild(stepInstruction);
                instructionStep.appendChild(stepImg);
                instructionStep.appendChild(step);
                methodSec.appendChild(instructionStep);
            }


            // 作者相關
            let memberLink = document.getElementById('memberLink');
            let ownerName = document.getElementById('ownerName');
            ownerName.setAttribute('class', "m-0");
            ownerName.innerHTML = list[0].member.name;
            // ========需要再補資料============

            //放人物連結
            // console.log('pk=' + list[0].member.pkey);
            // document.getElementById('memberLink1').href = `/WEB-INF/views/my_page.jsp?ownerId=${list[0].member.pkey}&page=1&year=2019`;
            // document.getElementById('memberLink2').href = `/WEB-INF/views/my_page.jsp?ownerId=${list[0].member.pkey}&page=1&year=2019`;

            document.getElementById('memberLink1').href = `http://localhost:8080/ezfit/my_page?ownerId=${list[0].member.pkey}&page=1&year=2019`;
            document.getElementById('memberLink2').href = `http://localhost:8080/ezfit/my_page?ownerId=${list[0].member.pkey}&page=1&year=2019`;

            // ===============================
            // 拿作者發佈數
            $.ajax({
                method: "POST",
                url: 'http://localhost:8080/ezfit/getTotal',
                data: {
                    memberId: list[0].member.pkey,
                },
                success: function (data) {
                    // let list = JSON.parse(data);
                    let list = data;
                    // console.log(list);
                    let num = list.length;
                    let mem = document.getElementById('num');
                    mem.innerHTML = num;
                }
            });


        }
    });






    // 載入畫面時，佈置留言區域
    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/ezfit/board/addNewChat',
        data: {
            recipeId: recipeId,
        },
        success: function (data) {
            // let list = JSON.parse(data);
            let list = data;
            // console.log('chat = '+data);
            // console.log(list[0][1]);
            if (list.length !== 0) {
                createChatSec(list);
            }


            // 留言筆數
            let chatLength = list.length;
            // 更新筆數
            let chatTotal = document.getElementById('chatTotal');
            chatTotal.innerHTML = '共 ' + chatLength + ' 則';

        }
    });



    // 送出留言
    let submitBtn = document.getElementById('submitBtn');
    submitBtn.addEventListener('click', function () {
        let msg = '您確定要送出留言?'
        let detail = $('#comment').val();
        if (detail.length == 0) {
            alert('請輸入您的留言');
        } else
            if (confirm(msg) == true) {
                // console.log(detail);
                $.ajax({
                    method: "POST",
                    url: 'http://localhost:8080/ezfit/board/addNewChat',
                    data: {
                        recipeId: recipeId,
                        detail: detail,

                    },
                    success: function (data) {
                        // let list = JSON.parse(data);
                        let list = data;
                        // console.log(list);
                        $('#comment').val('');

                        if (list.length !== 0) {
                            createChatSec(list);
                        }

                        // 留言筆數
                        let chatLength = list.length;
                        // 更新筆數
                        let chatTotal = document.getElementById('chatTotal');
                        chatTotal.innerHTML = '共 ' + chatLength + ' 則';
                        // 更新食譜資訊區已更新留言筆數
                        $.ajax({
                            method: "GET",
                            url: 'http://localhost:8080/ezfit/recipe/editRecipe',
                            data: {
                                recipeId: recipeId,
                            },
                            success: function (data) {
                                // list = JSON.parse(data);
                                list = data;
                                // console.log(list);
                                // 佈置食譜相關資訊
                                let recipeName = document.getElementById('recipeName');
                                recipeName.innerHTML = list[0].recipeName;
                                let save = document.getElementById('save');
                                save.innerHTML = list[0].save;
                                let chat = document.getElementById('chat');
                                chat.innerHTML = list[0].chat;
                                let recipePic = document.getElementById('recipePic');

                                console.log('recipePic.fileName = ' + list[0].fileName);
                                if ((list[0].fileName !== null && (list[0].fileName !== undefined))) {
                                    let str = (list[0].fileName).split('.');
                                    recipePic.setAttribute('src', '/ezfit/image/member/' + list[0].fileName + '/' + str[str.length - 1]);
                                } else {
                                    recipePic.setAttribute('src', 'img/publish_recipe/add_photo_small.svg')
                                }

                                // recipePic.setAttribute('src', '/data/recipePic/' + list[0].fileName);
                                let introduction = document.getElementById('introduction');
                                introduction.innerHTML = list[0].introduction;
                                let servings = document.getElementById('servings');
                                servings.innerHTML = '<img src="img/recipe_page/icon-servings.svg" alt="" >份量：' + list[0].servings + '人份';
                                let spendTime = document.getElementById('spendTime');
                                spendTime.innerHTML = '<img src="img/recipe_page/icon-cookingtime.svg" alt="" id="spendTime">時間：' + list[0].spendTime + '分鐘';
                            }
                        });

                    }
                });


            }
    });

    // 打包食材
    let packageBtn = document.getElementById('package');
    packageBtn.addEventListener('click', function () {
        let msg = '會將有販賣的食材放進您的購物車中，確定要將食材打包至購物車?'
        if (confirm(msg) === true) {
            let materalList = JSON.stringify(materal);
            console.log('materal=' + materal);
            console.log(materalList);

            // 需要將url放入
            // $.ajax({
            //     method: "POST",
            //     url: '',
            //     data: materal
            // });

        }
    })










});










// 要放JS object Array進去
function createChatSec(data) {

    for (let i = 0; i < data.length; ++i) {
        // 動態新增
        // 最外層
        let chatContainer = document.createElement('div');
        chatContainer.setAttribute('class', 'members-comments mt-4 pb-2');

        // 會員圖片
        let memberDiv = document.createElement('div');
        memberDiv.setAttribute('class', 'member-profile-img');

        let linkPic = document.createElement('a');
        let memberPic = document.createElement('img');
        //   =====================要更新=============================
        // memberPic.setAttribute('src', 'http://placekitten.com/92/92');

        linkPic.setAttribute('href', '');

        if (data[i].member.memberImage !== undefined) {
            console.log('chatmember=' + data[i].member.memberImage);
            memberPic.setAttribute('src', '/ezfit/image/memberHead/' + data[i].member.memberImage + '/jpg');
        } else {
            // 預設有點醜，考慮要不要換
            memberPic.setAttribute('src', 'index/img/nav/logo-g.svg');
        }
        //   ========================================================

        // 會員姓名
        let memberNameContainer = document.createElement('div');
        memberNameContainer.setAttribute('class', 'comment-box ml-4');

        let memberName = document.createElement('div');
        memberName.setAttribute('class', 'member-name p-0');
        let linkMemberName = document.createElement('a');



        //   =====================要更新=============================
        linkMemberName.setAttribute('href', '');
        linkMemberName.innerHTML = data[i].member.name;
        //   =========================================================

        // 留言內容
        let boardId = document.createElement('input');
        boardId.setAttribute('type', 'hidden');
        let chat = document.createElement('div');
        chat.setAttribute('class', 'comment-text pt-3');

        let time = document.createElement('div');
        time.setAttribute('class', 'comment-time pt-4');
        //   =====================要更新=============================
        boardId.setAttribute('value', data[i].boardId);
        boardId.setAttribute('id', data[i].boardId);
        chat.innerHTML = data[i].detail;
        time.innerHTML = data[i].boardCreateTime;
        //   =========================================================




        // 合併圖片
        linkPic.appendChild(memberPic);
        memberDiv.appendChild(linkPic);

        // 合併會員名
        memberName.appendChild(linkMemberName);
        memberNameContainer.appendChild(memberName);
        memberNameContainer.appendChild(chat);
        memberNameContainer.appendChild(time);
        // 全部合體
        chatContainer.appendChild(boardId);
        chatContainer.appendChild(memberDiv);
        chatContainer.appendChild(memberNameContainer);

        // chatContainer.appendChild(chat);
        // chatContainer.appendChild(time);
        // 父節點
        let chatParent = document.getElementById('chatParent');
        chatParent.appendChild(chatContainer);
    }


}




// 觸發click事件
function submitChat() {
    let msg = '您確定要送出留言?'
    let detail = $('#comment').val();
    if (confirm(msg) == true) {
        // console.log(detail);
        // $('#comment').val('');


        $.ajax({
            method: "POST",
            url: 'http://localhost:8080/ezfit/board/addChat',
            data: {
                recipeId: recipeId,
                detail: detail,

            },
            success: function (data) {

                $('#comment').val('');
            }
        });
    }





}

// 取得搜尋字串
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}

function toWriteRecipe() {
    // window.location.href = '/WEB-INF/views/publish_recipe';
    window.location.href = 'http://localhost:8080/ezfit/publish_recipe';
}