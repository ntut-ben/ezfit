<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>EZfit Eat - 常見問題</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/nav.css" />
    <link rel="stylesheet" href="css/faqs.css" />

</head>

<body>

    <!-- Navigation Bar ========================================================================== -->
   	<jsp:include page="header.jsp"></jsp:include>
    <!-- ========================================================================= -->

    <div class="faqs-main">

        <div class="faqs-body-and-bg">
            <div class="faqs-title mb-2">常見問答</div>
            <p></p>
            <div class="faqs-contents">
                <div class="accordion" id="accordionExample">
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h2 class="mb-0">
                                <button class="btn btn-link" type="button" data-toggle="collapse"
                                    data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                    註冊問題
                                </button>
                            </h2>
                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne"
                            data-parent="#accordionExample">
                            <div class="card-body faqs-texts py-4 px-3">
                                <h5>1. 註冊需要提供什麼資料？</h5>
                                <p>請於註冊帳號頁面輸入您的email信箱，設定密碼，填寫姓名，並至您的email信箱收取一組驗證碼填入系統，即可完成註冊流程。</p>
                                <br>
                                <h5>2. 為什麼我收不到驗證信？</h5>
                                <p>請確認輸入的 email 正確。檢查您的垃圾信件，有時可能會被收信系統自動分類到垃圾信件中。</p>
                                <br>
                                <h5>3. 忘記密碼？</h5>
                                <p>在登入頁面點選忘記密碼 > 輸入忘記密碼的信箱 > 到信箱收信，點擊 “變更我的密碼” > 在變更密碼的頁面輸入兩次新的密碼 > 按下 “確認變更密碼”
                                    按鈕之後即變更密碼完成。</p>
                                <br>
                                <h5>4. 無法登入</h5>
                                <p>若無法登入，請提供Email或FB用戶名並附上無法登入時出現的錯誤訊息/畫面截圖Mail至： hi@ezfit.tw 我們將儘快為您查詢。</p>

                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header" id="headingTwo">
                            <h2 class="mb-0">
                                <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                    data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    餐點相關
                                </button>
                            </h2>
                        </div>
                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
                            data-parent="#accordionExample">
                            <div class="card-body faqs-texts py-4 px-3">
                                <h5>1. 菜單多久更換一次？</h5>
                                <p>EZfit Eat每週調整一次菜單、且每月定期推出新菜色，堅持提供減油、減鹽的美味餐點，每日由中央廚房精選當季時令蔬菜、食材製作，豐富您的每一天。</p>
                                <br>
                                <h5>2. 如何得知餐點履歷來源？</h5>
                                <p>在EZfit Eat的官網上，每一個道餐點內容皆標示配菜、安心食材及營養標示，內容呈現營養成份及卡路里，讓您吃的安心。</p>
                                <br>
                                <h5>3. 有素食嗎？</h5>
                                <p>目前沒有素食的口味，若是單純不想吃肉的話，有蔬食類的餐點可以選擇哦！配菜部分會有一道蛋料理，也可能會使用蔥蒜調味，訂購前請您預先評估。</p>
                                <br>
                                <h5>4. 餐盒材質？</h5>
                                <p>餐盒製造為 PP5 耐熱材質，請放心享用餐點。</p>
                                <br>
                                <h5>5. 吃得飽嗎？</h5>
                                <p>EZfit Eat餐盒每個重量約淨重 550g 正負 5% ，市售便當重量約 450g~550g 間，EZfit Eat的餐盒份量吃完後會有飽足感喔！</p>
                                <br>
                                <h5>6. 可以自己挑菜色嗎？</h5>
                                <p>EZfit Eat將推出多種主餐可以選擇，主廚會依照季節搭配每日配菜，菜色內容、營養成份、皆可透過，但無提供自己挑選配菜組合。</p>
                                <br>
                                <h5>7. 可以加飯嗎？</h5>
                                <p>每一個餐盒的菜飯皆為固定比例，尚無提供加飯服務。</p>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header" id="headingThree">
                            <h2 class="mb-0">
                                <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                    data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                    配送問題
                                </button>
                            </h2>
                        </div>
                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree"
                            data-parent="#accordionExample">
                            <div class="card-body faqs-texts py-4 px-3">
                                <h5>1. 為什麼無法配送到我的位置？</h5>
                                <p>EZfit Eat目前僅在「台北市部分區域」營運，正努力擴大服務範圍中。</p>
                                <br>
                                <h5>2. 請問訂餐有訂購金額門檻限制嗎？</h5>
                                <p>在EZfit Eat的服務範圍內，如達指定門檻可免運配送，若未達門檻可支付運費配送。</p>
                                <br>
                                <h5>3. 訂單送出後，可以再變更配送時間、地址嗎？</h5>
                                <p>訂單送出後不得再變更時間、地址，如務必要變更，請將原訂單先「申請退費」並再次購買。</p>
                                <br>
                                <h5>4. 訂購完成後，如何得知餐點送達時間？</h5>
                                <p>EZfit Eat餐點每日由中央廚房採購上選食材現做，每個餐期會依照訂單數量即時分配，您可在訂購時選擇您所期望的送餐時間。</p>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-header" id="heading4">
                            <h2 class="mb-0">
                                <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                    data-target="#collapse4" aria-expanded="false" aria-controls="collapse4">
                                    揪團訂購問題
                                </button>
                            </h2>
                        </div>
                        <div id="collapse4" class="collapse" aria-labelledby="heading4" data-parent="#accordionExample">
                            <div class="card-body faqs-texts py-4 px-3">
                                <h5>1. 何謂揪團？</h5>
                                <p>揪團意指主揪建立群組名稱、送貨時間，將資訊分享給朋友，朋友選購食材及付費完成後即加入此筆揪團。「揪團訂購」是個方便同事間和朋友間訂購的功能，不但可一同訂購、合併配送，最方便的是加入揪團後，每個人可於購買後自行結帳，節省主揪收款找零之繁複手續。
                                </p>
                                <br>
                                <h5>2. 如何建立揪團？</h5>
                                <p>step1. 請務必先加入會員，開啟「揪團訂購」即可建立資料
                                    step2. 建立完成後，可加入選購、分享朋友
                                    step3. 當此揪團已達配送門檻，主揪可以提前收單。
                                    如主揪未提前收單，系統將於「出貨起始時間的前 60 分鐘」自動收單，若達門檻則進行配送；未達門檻則自動退費</p>
                                <br>
                                <h5>3. 揪團有人數限制嗎？</h5>
                                <p>揪團沒有人數限制喔！如未達門檻則會將此揪團整筆退費並取消配送。</p>
                                <br>
                                <h5>4. 如何加入朋友建立的揪團？</h5>
                                <p>主揪將揪團建立完成後可分享連結給好友加入，如您收到連結，請務必透過此連結進入選購得以加入揪團。
                                    貼心提醒：出貨時間及收件地址由主揪所建立，若您為被揪者，則不得變更此筆揪團資訊。</p>
                            </div>
                        </div>
                    </div>


                </div>

            </div>

            <div class="container-fluid contact-us-main">
                <div class="row contact-us">
                    <div class="col-6 contact-us-card">
                        <div class="contact-card-bg">
                            <div class="live-chat-time">
                                <h5>Live Chat - 線上即時客服</h5>
                                <p class="pt-4">找不到答案嗎？歡迎使用LIVE CHAT-線上即時客服。提供全面高品質的客戶服務、線上協助任何疑問!!貼心簡單快遞諮詢、立即交談人性化便捷服務。
                                </p>
                                <p class="pt-5">網站線上專員服務為: <p>週一至週五(國定例假日除外)</p>
                                    <p>09:00~12:00、13:00~16:30</p>
                                </p>
                            </div>

                        </div>

                    </div>
                    <div class="col-6 contact-us-card">
                        <div class="contact-card-bg">
                            <div class="live-chat-time">
                                <h5>撥打客服專線</h5>
                                <p class="pt-4">任何服務建議及疑問，都歡迎您與我們聯繫！客服專線進電後即以秒計算、長途市話不另計收長途費用。<br>客服電話：02-01234567</p>
                                <p class="pt-5">客服專線服務時間: <p>週一至週五(國定例假日除外)</p>
                                    <p>09:00~12:00、13:00~16:30</p>
                                </p>
                            </div>

                        </div>

                    </div>
                </div>

                <div class="row contact-us py-5">

                    <div class="col-6 send-email-text">
                        <h5>客服信箱</h5>
                        <p class="pt-2">對於EZfit Eat在服務方面有任何問題或意見反應，請提供您寶貴的意見和評語，我們將會盡快給您答覆。</p>
                        <a href="#" class="pt-2 service-mail">service@ezfiteat.com</a>
                    </div>
                    <div class="col-6 send-email-form">
                        <form action="">
                            <div><input type="text" name="name" id="name" value="" placeholder="姓名"></div>
                            <div><input type="text" name="email" id="email" value="" placeholder="email"></div>
                            <div><input type="text" name="subjet" id="subjet" value="" placeholder="主旨"></div>
                            <div><textarea name="" id="" cols="30" rows="5" placeholder="請輸入內容"></textarea></div>
                            <div><button type="submit" class="btn btn-success">送 出</button></div>

                        </form>

                    </div>

                </div>


            </div>





        </div>

        <!-- 回到頂部＝＝＝＝＝＝＝ -->
        <div id="return-to-top">
            <img src="img/faqs/back-to-top.svg">
        </div>

      

        <!-- Footer ======================================================================= -->
       <jsp:include page="footer.jsp"></jsp:include>


        <script src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
        <script src="js/nav.js"></script>
        <script src="js/faqs.js"></script>
        <!-- Live Chat Widget powered by https://keyreply.com/chat/ -->
<!-- Advanced options: -->
<!-- data-align="left" -->
<!-- data-overlay="true" -->
<script data-align="right" data-overlay="false" id="keyreply-script" src="https://keyreply.com/chat/widget.js" data-color="#FF9800" data-apps="JTdCJTIyZmFjZWJvb2slMjI6JTIyMTAwMDQzNDI0ODUzNDczJTIyJTdE"></script>
</body>

</html>