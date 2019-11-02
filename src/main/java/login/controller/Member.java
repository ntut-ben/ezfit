package login.controller;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import createAccount.model.GlobalService;
import createAccount.model.MemberBean;
import createAccount.service.CodeServiceImpl;
import createAccount.service.MemberService;
import createAccount.service.MemberServiceImpl;
import login.service.LoginService;
import login.service.LoginServiceImpl;

@Controller
public class Member {
	@Autowired
	MemberService msi;
	@Autowired
	LoginService loginService;

	@RequestMapping(value = "/login/login", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String login() {

		return "login/login";
	}

	@RequestMapping(value = "/createAccount/createAccount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String createAccount() {

		return "createAccount/createAccount";
	}

	@RequestMapping(value = "/api/createAccount/memberServlet", method = RequestMethod.POST)
	public String createAccountAPI(Model model, HttpServletRequest request) {
		// 錯誤訊息
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 註冊成功
		Map<String, String> msgOK = new HashMap<String, String>();

		HttpSession session = request.getSession();
		model.addAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息

		String name = request.getParameter("memberName");
		String email = request.getParameter("memberEmail");
		String password = request.getParameter("memberPassword");

		String code = request.getParameter("veriCode");
		CodeServiceImpl csi = new CodeServiceImpl();

		String correctCode = csi.queryCode(email);

		// 檢查驗證碼
		if (code.equals(correctCode)) {
			System.out.println("驗證碼正確");

			// 密碼加密
			password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			MemberBean mb = new MemberBean(null, email, password, name, null, null, null, null, null, null, null, ts);
			msi.saveMember(mb);
			return "/login/login";
		} else if (msi.emailExists(email)) {
			errorMsg.put("errorEmail", "email重複申請");
		} else {
			errorMsg.put("errorCode", "驗證碼錯誤");
		}

		// 如果 errorMsgMap 不是空的，表示有錯誤

		return "/createAccount/createAccount";
	}

	@RequestMapping(value = "/api/login/loginServlet", method = RequestMethod.POST)
	public String loginAPI(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 定義存放錯誤訊息的Map物件
		Map<String, String> errorMsgMap = new HashMap<String, String>();

		// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		// 1. 讀取使用者輸入資料
		String email = request.getParameter("memberEmail");
		String password = request.getParameter("memberPassword");
		String rm = request.getParameter("rememberMe");
		String requestURI = (String) session.getAttribute("requestURI");
		// 2. 進行必要的資料轉換
		// 無
		// 3. 檢查使用者輸入資料
		// 如果 userId 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (email == null || email.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}

		// 如果 errorMsgMap 不是空的，表示有錯誤，交棒給login.jsp
		if (!errorMsgMap.isEmpty()) {
			return "/login/login";
		}

		// **********Remember Me****************************
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		// rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
		if (rm != null) {
			cookieUser = new Cookie("email", email);
			cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
		} else { // 使用者沒有對 RememberMe 打勾
			cookieUser = new Cookie("email", email);
			cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(0);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
		// ********************************************

		// 4. 進行 Business Logic 運算
		// 將LoginServiceImpl類別new為物件，存放物件參考的變數為 loginService

		// 將密碼加密兩次，以便與存放在表格內的密碼比對
		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		MemberBean mb = null;
		try {
			// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
			mb = loginService.checkIDPassword(email, password);
			if (mb != null) {
				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
				session.setAttribute("LoginOK", mb);
				MemberBean mb1 = (MemberBean) session.getAttribute("LoginOK");
			} else {
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
				errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
			}
		} catch (RuntimeException ex) {
			errorMsgMap.put("LoginError", ex.getMessage());
		}

		// 5.依照 Business Logic 運算結果來挑選適當的畫面
		// 如果 errorMsgMap 是空的，表示沒有任何錯誤，交棒給下一棒
		if (errorMsgMap.isEmpty()) {
			if (requestURI != null) {
				requestURI = (requestURI.length() == 0 ? request.getContextPath() : requestURI);
				try {
					response.sendRedirect(requestURI);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				response.sendRedirect(response.encodeRedirectURL(requestURI));
			} else {
				try {
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			// 如果errorMsgMap不是空的，表示有錯誤，交棒給login.jsp

			return "/login/login";
		}
		return "/login/login";
	}
}
