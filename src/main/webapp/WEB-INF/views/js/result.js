// 從搜尋字串取得recipeId 
// 如果有帶食譜ID，就載入
let url = location.href;
const search = getParameterByName('search', url);
// console.log('search = ' + search);
var page = getParameterByName('page', url);
var totalPage = 0;
$(document).ready(function () {



    // console.log(detail);
    $.ajax({
        method: "GET",
        url: 'http://localhost:8080/ezfit/recipe/search.do',
        data: {
            name: search,
        },
        success: function (data) {
            let list = data;
            console.log('搜尋結果=' + list);
            var target = splitArray(list);
            let arrayLength = list.length;
            let perRecipe = 10;
            totalPage = Math.floor((arrayLength) / perRecipe) + 1;


            // 處理 找到筆數  &  關鍵字
            let searchTotal = document.getElementById('searchTotal');
            let result = document.getElementById('result');
            searchTotal.innerHTML = list.length;
            result.innerHTML = search;


            // 動態新增
            // parentCard parent
            // console.log('page = '+page);
            // console.log('targetlength='+target[page-1].length);
             for (let i = 0; i < target[page-1].length; ++i) {
//                  for (let i = 0; i < list.length; ++i) {

                 let apendString = ` <div class="search-result-card my-3">
             <div class="recipe-cover-img"> 
                 <a href="http://localhost:8080/ezfit/recipe_page?recipeId=${target[page - 1][i].recipeId}"><img src="https://picsum.photos/200/200" alt="" id=recipePic${i}></a>
                 </div><div class="browse-recipe-preview m-3 ml-3">
                 <a href="http://localhost:8080/ezfit/recipe_page?recipeId=${target[page - 1][i].recipeId}"><p class="browse-recipe-name">${target[page - 1][i].recipeName}</p></a><a href="http://localhost:8080/ezfit/my_page?ownerId=${target[page - 1][i].member.pkey}&page=1&year=2019"><p class="result-username-by">${target[page - 1][i].member.name}</p></a> <p class="browse-recipe-content-description">${target[page - 1][i].introduction}</p>
                 <ul class="browse-recipe-meta p-0">
                     <li class="browse-recipe-meta-fav"><img src="img/search_result/icon-like.svg" alt=""> ${target[page - 1][i].save}</li> <li class="browse-recipe-meta-comment">
                     <img src="img/search_result/icon-comment.svg" alt=""> ${target[page - 1][i].chat}
                             </li>
                         </ul>
                     </div>
                 </div>`;

            //     // 動態新增
                 $('#parentCard').append(apendString);
            //     // 要處理圖片

                 let pic = document.getElementById('recipePic' + i);

                // console.log('target[' + i + '].fileName=' + list[i].fileName);

                 if (target[page - 1][i].fileName === '' || target[page - 1][i].fileName === undefined) {
                     console.log('bingo');
                     pic.src = "img/publish_recipe/add_photo_small.svg"
                 }
                 else {
                     let str = (target[page - 1][i].fileName).split('.');
                     // console.log('bingo' + target[i].fileName);
                     pic.src = "/ezfit/image/recipe/" + target[page - 1][i].fileName + '/' + str[str.length - 1];
                 }


             }

            let add = '';
            for (let i = 1; i <= totalPage; ++i) {
                add += `<li class="page-item"><a class="page-link" href="http://localhost:8080/ezfit/search_result?search=${search}&page=${i}">${i}</a></li>`

                let addPage = ` 
                    <li class="page-item" id="leftBtn">
                        <a class="page-link" href="http://localhost:8080/ezfit/search_result?search=${search}&page=${Number(page) - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>`+ add + `
                    <li class="page-item" id="rightBtn">
                        <a class="page-link" href="http://localhost:8080/ezfit/search_result?search=${search}&page=${Number(page) + 1} "aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>`;

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




            // 熱門關鍵字
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
                        // var hotRecipe(i+1) = recipe[i]; 動態命名變數
                        eval('var hotSearch' + (i + 1) + '=hotSearchList[' + i + '].keyWord;');
                        // console.log(eval('hotSearch' + (i + 1)));
                        let a = document.createElement("a");
                        a.setAttribute("class", "badge badge-success");
                        a.setAttribute("href", `http://localhost:8080/ezfit/search_result?search=${hotSearchList[i].keyWord}&page=1`);

                        a.innerHTML = eval('hotSearch' + (i + 1));
                        let parent = document.getElementById('hotSearch');
                        parent.appendChild(a);

                    }






                }


            })





        }
    });




    // 寫食譜
    $('#writeRecipe').click(writeRecipe);






});


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

function writeRecipe() {
    window.location.href = 'http://localhost:8080/ezfit/publish_recipe';
}


// 將資料案頁數分裝到陣列中
function splitArray(array) {
    let arrayLength = array.length;
    let perRecipe = 10;
    let page = Math.floor((arrayLength) / perRecipe) + 1;
    console.log('splice list.length = '+page);
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