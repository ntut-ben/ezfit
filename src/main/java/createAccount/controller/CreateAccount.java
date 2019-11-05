package createAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import createAccount.SendEmail;
import createAccount.VeriCode;
import createAccount.model.CodeBean;
import createAccount.service.CodeServiceImpl;

@Controller
public class CreateAccount {
	@Autowired
	CodeServiceImpl codeServiceImpl;
	VeriCode createCode = new VeriCode();
	String code = null;
	String email = null;
	String name = null;

	@RequestMapping(value = "/createAccount/emailServlet.do", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String createAccount(@RequestParam("memberEmail") String memberEmail,
			@RequestParam("memberName") String memberName) {
		// 與client連線＆取得輸入的email
		email = memberEmail;
		name = memberName;

//				 檢查email是否已有驗證碼, 不重複產生驗證碼
		if (codeServiceImpl.emailExists(email)) {
			code = codeServiceImpl.queryCode(email);
		} else {
			code = createCode.verifyCode();
			// 存入資料庫
			CodeBean cb = new CodeBean(null, email, code);
			codeServiceImpl.saveCode(cb);
		}

		// 發送email
		if (name == null || name.trim().length() == 0) {
			name = "New Member";
		} else {
			name = memberName;
		}

		SendEmail send = new SendEmail();
		send.sendEmail(email, name, code);

		return code;

	}
}
