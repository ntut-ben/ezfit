package createAccount.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import createAccount.model.GlobalService;
import createAccount.model.MemberBean;
import createAccount.service.CodeServiceImpl;
import createAccount.service.MemberServiceImpl;

@WebServlet("/createAccount/memberServlet.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		// 錯誤訊息
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 註冊成功
		Map<String, String> msgOK = new HashMap<String, String>();

		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息

		String name = request.getParameter("memberName");
		String email = request.getParameter("memberEmail");
		String password = request.getParameter("memberPassword");

		String code = request.getParameter("veriCode");
		CodeServiceImpl csi = new CodeServiceImpl();
		MemberServiceImpl msi = new MemberServiceImpl();
		String correctCode = csi.queryCode(email);

		// 檢查驗證碼
		if (code.equals(correctCode)) {
			System.out.println("驗證碼正確");

			// 密碼加密
			password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			MemberBean mb = new MemberBean(null, email, password, name, null, null, null, null, null, null, null, ts);
			msi.saveMember(mb);

			RequestDispatcher rd = request.getRequestDispatcher("/login/login.jsp");
			rd.forward(request, response);
			return;
		} else if (msi.emailExists(email)) {
			errorMsg.put("errorEmail", "email重複申請");

		} else {
			errorMsg.put("errorCode", "驗證碼錯誤");

		}

		// 如果 errorMsgMap 不是空的，表示有錯誤
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("/createAccount/createAccount.jsp");
			rd.forward(request, response);
			return;
		}

	}
}
