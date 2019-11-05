<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta charset="UTF-8" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/createAccount/css/createAccount.css"
    />
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/createAccount/js/createAccount.js"></script>
    <script src="${pageContext.request.contextPath}/createAccount/js/jquery.validate.js"></script>
    <script src="${pageContext.request.contextPath}/createAccount/js/additional-methods.js"></script>
    <script src="${pageContext.request.contextPath}/createAccount/js/getEmail.js"></script>
    <!-- <script src="js/facebook.js"></script> -->
    <!-- <script src="js/googleLogin"></script> -->
    <title>加入會員</title>
  </head>
  <body>
    <script
      async
      defer
      crossorigin="anonymous"
      src="https://connect.facebook.net/zh_TW/sdk.js#xfbml=1&version=v4.0&appId=505880339962412&autoLogAppEvents=1"
    ></script>
    <jsp:include page="../header.jsp"></jsp:include>
    <div class="container-fuild pb-5" style="padding-top: 25vh;">
      <table class="mx-auto" id="login" valign="top">
        <tr>
          <td id="loginLeft">
            加入會員
            <div class="greenLine"></div>
            <form
              action="${pageContext.request.contextPath}/api/createAccount/memberServlet"
              method="post"
              id="theForm"
            >
              <input
                type="text"
                class="accountInfo"
                placeholder="姓名"
                id="memberName"
                name="memberName"
                value="${requestScope.name}${param.memberName}"
              />
              <input
                type="email"
                class="accountInfo"
                placeholder="Email"
                id="memberEmail"
                name="memberEmail"
                value="${requestScope.email}${param.memberEmail}"
              />
              <div class="error">${MsgMap.errorEmail}</div>
              <input
                type="password"
                class="accountInfo"
                placeholder="密碼 (8~12位元, 需包含英文大小寫及數字)"
                id="memberPassword"
                name="memberPassword"
                value="${requestScope.password}${param.memberPassword}"
              />
              <input
                type="password"
                class="accountInfo"
                placeholder="確認密碼"
                id="memberPasswordCheck"
                name="memberPasswordCheck"
              />
              <input
                type="text"
                class="accountInfo"
                placeholder="請按右方取得驗證碼 (發送至上方Email)"
                name="veriCode"
                id="veriCode"
              />
              <button type="button" id="veriCodeButton" name="code">
                取得驗證碼
              </button>
              <div id="checkCode" name="checkCode" class="error">
                ${MsgMap.errorCode}
              </div>
              <p>
                <input
                  type="checkbox"
                  id="serviceRule"
                  name="serviceRule"
                />我已經閱讀並同意
                <a href="https://www.facebook.com/legal/terms" name="service"
                  >服務條款</a
                >
              </p>
              <input class="submit" type="submit" value="加入會員" />
            </form>
            <img
              src="${pageContext.request.contextPath}/createAccount/pic/OR.png"
              id="OR"
            />
            <div
              class="fb-login-button"
              data-width="380"
              data-size="medium"
              data-button-type="login_with"
              data-auto-logout-link="false"
              data-use-continue-as="false"
            >
              Facebook登入
            </div>

            <button id="googleButton" class="mb-5">
              <img
                src="${pageContext.request.contextPath}/createAccount/pic/google.png"
                class="w-100"
              />
            </button>
            <br />
          </td>
          <td id="loginRight">
            <span id="span1">已經有帳號了？</span><br />
            <div></div>
            <span id="span2">請點選下方連結登入</span><br />
            <p></p>
            <input
              type="button"
              id="loginButton"
              value="會員登入"
              onclick="window.location.href = '../login/login'"
            />
          </td>
        </tr>
      </table>
    </div>
    <jsp:include page="../footer.jsp"></jsp:include>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  </body>
</html>
