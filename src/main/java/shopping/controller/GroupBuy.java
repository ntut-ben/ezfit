package shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import createAccount.model.MemberBean;
import login.service.LoginServiceImpl;

/**
 * Servlet implementation class CheckShopCart
 */
@WebServlet("/GroupBuy.do")
public class GroupBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);

		LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
		String account = null, pwd = null, action = null, groupName = null, initiatorName = null, initiatorPhone = null,
				county = null, district = null, zipcode = null, address = null, deadLine = null;

		HttpSession session = request.getSession(false);

		// Session 取出Member Bean物件
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		account = mb.getEmail();
		pwd = mb.getPassword();

		// 確認該Member Object帳密都正確
		if ((account != null && pwd != null)) {
			mb = loginServiceImpl.checkIDPassword(account, pwd);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

	}

}
