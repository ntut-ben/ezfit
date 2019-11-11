package login.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import _00.utils.ToJson;
import createAccount.SendEmail;
import createAccount.VeriCode;
import createAccount.model.CodeBean;
import createAccount.model.EncrypAES;
import createAccount.model.MemberBean;
import createAccount.service.CodeService;
import createAccount.service.MemberService;
import login.service.LoginService;

@Controller
public class Member {
	@Autowired
	MemberService msi;
	@Autowired
	LoginService lsi;
	@Autowired
	CodeService csi;

	@RequestMapping(value = "/login/login", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String login() {

		return "login/login";
	}

	@RequestMapping(value = "/login/logout", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String logout() {

		return "login/logout";
	}

	@RequestMapping(value = "/createAccount/createAccount", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String createAccount() {

		return "createAccount/createAccount";
	}

	@RequestMapping(value = "/memberPage/memberPage", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String memberPage() {

		return "/memberPage/memberPage";
	}

	// 註冊會員
	@RequestMapping(value = "/api/createAccount/memberServlet", method = RequestMethod.POST)
	public String createAccountAPI(Model model, HttpServletRequest request, HttpServletRequest response) {
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
		String correctCode = csi.queryCode(email);

		// 檢查驗證碼
		if (!code.equals(correctCode)) {
			errorMsg.put("errorCode", "驗證碼錯誤");
			return "/createAccount/createAccount";

		} else if (msi.emailExists(email)) {
			errorMsg.put("errorEmail", "email重複申請");
			return "/createAccount/createAccount";
		} else {
			// 密碼加密
			password = EncrypAES.getMD5Endocing(EncrypAES.encryptString(password));
			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());

			MemberBean mb = new MemberBean(null, email, password, name, null, null, null, null, null, null, null, ts);
			msi.saveMember(mb);

			return "/login/login";
			// 如果 errorMsgMap 不是空的，表示有錯誤

		}
	}

//	ＦＢ登入
	@RequestMapping(value = "/api/createAccount/facebook", method = RequestMethod.GET)
	public String facebookAPI(Model model, HttpServletRequest request, HttpServletRequest response) throws IOException {
		HttpSession session = request.getSession();
		String access_token = request.getParameter("access_token");

		// 和Graph API連線，用access_tokenf取得基本資料
		String url = "https://graph.facebook.com/v5.0/me?fields=id,name,picture,email&access_token=" + access_token;
		URL obj = null;

		obj = new URL(url);

		HttpURLConnection con = null;

		con = (HttpURLConnection) obj.openConnection();

		// optional default is GET

		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = 0;

		responseCode = con.getResponseCode();

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = null;

		in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		StringBuffer stringBuffer = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			stringBuffer.append(inputLine);
		}

		in.close();

		System.out.println(stringBuffer);
		MemberBean mb = new MemberBean();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		try {
			JSONObject myResponse = new JSONObject(stringBuffer.toString());
			mb.setName(myResponse.getString("name"));
			mb.setEmail(myResponse.getString("email"));
			mb.setRegisterTime(ts);
			JSONObject picture_reponse = myResponse.getJSONObject("picture");
			JSONObject data_response = picture_reponse.getJSONObject("data");
			System.out.println("URL : " + data_response.getString("url"));
			mb.setMemberImage(data_response.getString("url"));

		} catch (JSONException e) {
			e.printStackTrace();
		}
		msi.updateMemInfo(mb);
//		msi.saveMember(mb);
		session.setAttribute("FB", access_token);
		session.setAttribute("LoginOK", mb);

		return "index";
	}

//	一般登入
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

			String encodePassword = EncrypAES.getMD5Endocing(EncrypAES.encryptString(password));
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

			String encodePassword = EncrypAES.getMD5Endocing(EncrypAES.encryptString(password));
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
		password = EncrypAES.getMD5Endocing(EncrypAES.encryptString(password));
		MemberBean mb = null;
		try {
			// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
			mb = lsi.checkIDPassword(email, password);
			if (mb != null) {
				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
				session.setAttribute("LoginOK", mb);
				// **********for chat use****************************
				Cookie cookieName = null;
				cookieName = new Cookie("name", mb.getName());
				cookieName.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
				cookieName.setPath(request.getContextPath());
				response.addCookie(cookieName);
//				MemberBean mb1 = (MemberBean) session.getAttribute("LoginOK");
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

//	手機登入
	@RequestMapping(value = "/api/mobile/login/loginServlet", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String loginAPI(@RequestParam("memberEmail") String email,
			@RequestParam("memberPassword") String password, HttpServletRequest request, HttpServletResponse response) {

		// 定義存訊息的Map物件
		Map<String, String> status = new HashMap<String, String>();

		// 將密碼加密兩次，以便與存放在表格內的密碼比對
		password = EncrypAES.getMD5Endocing(EncrypAES.encryptString(password));
		MemberBean mb = null;
		try {
			// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
			mb = lsi.checkIDPassword(email, password);
			System.out.println(mb);
			if (mb != null) {
				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
				status.put("status", "LoginOk");
//				MemberBean mb1 = (MemberBean) session.getAttribute("LoginOK");

			} else {
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
				status.put("status", "LoginFail");
			}
		} catch (RuntimeException ex) {
			status.put("status", "LoginFail");
		}
		ToJson<Map<String, String>> toJson = new ToJson<Map<String, String>>();
		String json = toJson.getJson(status);
		System.out.println(json);
		return json;
	}

//	忘記密碼(寄新密碼)
	@RequestMapping(value = "/api/login/passwordServlet", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String renewPasswordAPI(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String email;
		String encodedPassword;
		// 與client連線＆取得輸入的email
		email = request.getParameter("Email1forPwd");

		MemberBean mb = null;

//				 檢查email是否已存在
		if (msi.emailExists(email)) {
			encodedPassword = lsi.queryPassword(email);
			mb = lsi.queryMB(email);
			String newPassword = RandomStringUtils.randomAlphanumeric(8, 12);

			SendEmail send = new SendEmail();
			send.sendPassword(email, newPassword, encodedPassword);

			PrintWriter out = response.getWriter();
			out.print("密碼已發送至: " + email);
			out.flush();
			out.close();

			HttpSession session = request.getSession();
			mb.setPassword(EncrypAES.getMD5Endocing(EncrypAES.encryptString(newPassword)));
			msi.updateMemInfo(mb);
			session.setAttribute("LoginOK", mb);
			System.out.println("密碼已更新至DB");

		} else {
			PrintWriter out = response.getWriter();
			out.print("Email不存在");
			out.flush();
			out.close();
		}
		return "login/login";
	}

//	寄驗證碼
	@RequestMapping(value = "/api/createAccount/emailServlet", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String sendCodeAPI(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 產生驗證碼
		VeriCode createCode = new VeriCode();
		String code;
		String email;
		String name;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// 與client連線＆取得輸入的email
		email = request.getParameter("memberEmail");
		name = request.getParameter("memberName");

		// 檢查Email是否為空值
		if (email != null && email.trim().length() != 0) {
//				 檢查email是否已有驗證碼, 不重複產生驗證碼
			if (csi.emailExists(email)) {
				code = csi.queryCode(email);
			} else {
				// Email沒有舊的驗證碼，產生新的＆存入資料庫
				code = createCode.verifyCode();
				CodeBean cb = new CodeBean(null, email, code);
				csi.saveCode(cb);
			}

			// 沒有輸入姓名，用New Member取代
			if (name == null || name.trim().length() == 0) {
				name = "New Member";
			} else {
				name = request.getParameter("memberName");
			}

			// 發送email
			SendEmail send = new SendEmail();
			send.sendEmail(email, name, code);

			// 發送response回前端
			PrintWriter out = response.getWriter();
			out.print("已傳送驗證碼至:" + email);
			out.flush();
			out.close();
		} else {
			System.out.println("empty");
			PrintWriter out = response.getWriter();
			out.print("Email不可空白");
			out.flush();
			out.close();
		}
		return null;
	}

//	更新會員資料
	@RequestMapping(value = "/api/memberPage/updateInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String updateMemInfoAPI(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 先取出session物件
		HttpSession session = request.getSession(false);

		// 如果session物件不存在
		if (session == null) {
			// 請使用者登入
//			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login/login.jsp"));
			return "redirect:/login/login";
		}
		// 登入成功後，Session範圍內才會有LoginOK對應的MemberBean物件
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
//		Integer id = mb.getPkey();
//		String email = mb.getEmail();
		String name = mb.getName();
//		String password = mb.getPassword();
		String gender = mb.getGender();
		Double height = mb.getHeight();
		Double weight = mb.getWeight();
		java.sql.Date birthday = mb.getBirthday();
		String address = mb.getAddress();
		String tel = mb.getTel();
//		String img = mb.getMemberImage();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());

		name = request.getParameter("memberName");
		gender = request.getParameter("memberSex");

		if (request.getParameter("memberHeight") != null && request.getParameter("memberHeight").trim() != "") {
			height = Double.parseDouble(request.getParameter("memberHeight"));
		}

		if (request.getParameter("memberWeight") != null && request.getParameter("memberWeight").trim() != "") {
			weight = Double.parseDouble(request.getParameter("memberWeight"));
		}

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
//				birthday = request.getParameter("memberBir");
		java.util.Date parsed;
		try {
			parsed = (Date) format.parse(request.getParameter("memberBir"));
			birthday = new java.sql.Date(parsed.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tel = request.getParameter("memberTel");
		address = request.getParameter("zipcode") + request.getParameter("county") + request.getParameter("district")
				+ request.getParameter("memberAddr");

		mb.setName(name);
		mb.setGender(gender);
		mb.setHeight(height);
		mb.setWeight(weight);
		mb.setBirthday(birthday);
		mb.setTel(tel);
		mb.setAddress(address);
		mb.setRegisterTime(ts);

		msi.updateMemInfo(mb);

		String[] bir = birthday.toString().split("-");
		System.out.println(bir[1] + "/" + bir[2] + "/" + bir[0]);
		session.setAttribute("birthday", bir[1] + "/" + bir[2] + "/" + bir[0]);

		return "/memberPage/memberPage";
	}

	// 更新大頭照
	@RequestMapping(value = "/api/memberPage/updatePic", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@SuppressWarnings("rawtypes")
	public String updatePicAPI(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String filename = null;
		HttpSession session = request.getSession(false);

		// 如果session物件不存在
		if (session == null) {
			// 請使用者登入
//			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login/login"));
			return "redirect:/login/login";
		}
		// 登入成功後，Session範圍內才會有LoginOK對應的MemberBean物件
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		// 取出使用者的memberId，後面的Cookie會用到
		Integer id = mb.getPkey();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		factory.setSizeThreshold(1024 * 1024);
		List items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (!item.isFormField()) {
				// 根據時間戳建立頭像檔案
				filename = id.toString() + ".jpg";
				File f = new File(request.getServletContext().getRealPath("MemberImage"));
//				File f = new File("D://image");
//				File f = new File("C:\\_JSP\\workspaceJDBC\\ezfit\\src\\main\\webapp\\WEB-INF\\views\\MemberImage");
				if (!f.exists()) {
					f.mkdir();
				}
//				(瀚文新增)複製一個到server外的資料夾，抓取方法在recipeController
				File f2 = new File("C:/ezfitData/memberHeadPic");
//				File f = new File("D://image");
//				File f = new File("C:\\_JSP\\workspaceJDBC\\ezfit\\src\\main\\webapp\\WEB-INF\\views\\MemberImage");
				if (!f2.exists()) {
					f2.mkdir();
				}
				String imgsrc = f + "\\" + filename;
				System.out.println(imgsrc);
//				(瀚文新增)
				String imgsrc2 = f2 + "\\" + filename;
				System.out.println(imgsrc2);
				// 複製檔案
				InputStream is = item.getInputStream();
				FileOutputStream fos = new FileOutputStream(imgsrc);
				byte b[] = new byte[1024 * 1024];
				int length = 0;
				while (-1 != (length = is.read(b))) {
					fos.write(b, 0, length);
				}

				// (瀚文新增)複製檔案
//				InputStream is2 = item.getInputStream();
				is = item.getInputStream();
				FileOutputStream fos2 = new FileOutputStream(imgsrc2);
//				byte b[] = new byte[1024 * 1024];
				length = 0;
				while (-1 != (length = is.read(b))) {
					fos2.write(b, 0, length);
				}

				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());

				mb.setMemberImage(filename);
				mb.setRegisterTime(ts);
				msi.updateMemInfo(mb);
//				session.setAttribute("mempic", imgsrc);

				fos.flush();
				fos.close();
			} else {
				System.out.println(item.getFieldName());
				String value = item.getString();
				value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
				System.out.println(value);
			}
		}
		model.addAttribute("imgSrc", filename);
		return "/memberPage/memberPage";
	}

	@RequestMapping(value = "/api/memberPage/updatePwd", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String updatePwdAPI(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// 先取出session物件
		HttpSession session = request.getSession(false);

		// 如果session物件不存在
		if (session == null) {
			// 請使用者登入
//			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login/login.jsp"));
			return "redirect:/login/login";
		}
		// 登入成功後，Session範圍內才會有LoginOK對應的MemberBean物件
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		// 取出使用者的memberId，後面的Cookie會用到

		String email = mb.getEmail();

		String password = request.getParameter("oldPwd");
		String newPassword = request.getParameter("newPwd");
		String newPasswordCheck = request.getParameter("newPwdCheck");

		password = EncrypAES.getMD5Endocing(EncrypAES.encryptString(password));

		Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,12})");
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		Matcher matcher = null;

		// 定義存放錯誤訊息的Map物件
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		model.addAttribute("ErrorMsgKey", errorMsgMap);
		// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"

		// 舊密碼錯誤
		if (!password.equals(lsi.queryPassword(email))) {
			errorMsgMap.put("ErrorOldPwd", "密碼錯誤");
		}

		// 新密碼格式錯誤
		matcher = pattern.matcher(newPassword);
		if (!matcher.matches()) {
			errorMsgMap.put("ErrorFormat", "格式錯誤");

		}

		// 新密碼＆確認不符
		if (!newPassword.equals(newPasswordCheck)) {
			errorMsgMap.put("ErrorNewPwd", "密碼不相符");
		}

		if (errorMsgMap.isEmpty()) {
			// update
			newPassword = EncrypAES.getMD5Endocing(EncrypAES.encryptString(newPassword));
			mb.setPassword(newPassword);
			mb.setRegisterTime(ts);
			msi.updateMemInfo(mb);

//					response.sendRedirect("memberPage.jsp");
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Password updated successfully!');");
			out.println("location=" + "'http://localhost:8080" + request.getContextPath() + "/memberPage/memberPage';");
			out.println("</script>");
			out.flush();
			out.close();
			return "/memberPage/memberPage";

		} else {
			return "/memberPage/memberPage";
		}

	}

}
