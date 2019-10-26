package createAccount.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import createAccount.SendEmail;
import createAccount.VeriCode;
import createAccount.model.CodeBean;
import createAccount.service.CodeServiceImpl;

@WebServlet("/createAccount/emailServlet.do")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 產生驗證碼
	VeriCode createCode = new VeriCode();
	String code;
	String email;
	String name;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 與client連線＆取得輸入的email
		email = request.getParameter("memberEmail");
		name = request.getParameter("memberName");

		CodeServiceImpl service = new CodeServiceImpl();

//		 檢查email是否已有驗證碼, 不重複產生驗證碼
		if (service.emailExists(email)) {
			code = service.queryCode(email);
		} else {
			code = createCode.verifyCode();
			// 存入資料庫
			CodeBean cb = new CodeBean(null, email, code);
			service.saveCode(cb);
		}

		// 發送email
		if (name == null || name.trim().length() == 0) {
			name = "New Member";
		} else {
			name = request.getParameter("memberName");
		}

		SendEmail send = new SendEmail();
		send.sendEmail(email, name, code);

		PrintWriter out = response.getWriter();
		out.print(code);
		out.flush();
		out.close();

	}

}