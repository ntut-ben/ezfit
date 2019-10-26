package shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00.utils.ToJson;
import createAccount.model.MemberBean;
import login.service.LoginServiceImpl;
import shopping.model.OrderBean;
import shopping.service.impl.OrderServiceImpl;

@WebServlet("/OrderProcess.do")
public class OrderProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		OrderBean orderBean = null;
		ToJson<OrderBean> toJson = new ToJson<OrderBean>();
		HttpSession session = request.getSession(false);
		MemberBean mb = null;
		String account = null, pwd = null, json = null;
		// Session 取出Member Bean物件
		mb = (MemberBean) session.getAttribute("LoginOK");
		account = mb.getEmail();
		pwd = mb.getPassword();

		// 確認該Member Object帳密都正確
		if ((account != null && pwd != null)) {
			mb = loginServiceImpl.checkIDPassword(account, pwd);
		}

		// 如果有該會員
		if (mb != null) {
			orderBean = orderServiceImpl.query(mb);
			response.setContentType("application/json");
			json = toJson.getJson(orderBean);
			System.out.println(json);
			PrintWriter out = response.getWriter();
			out.print(json);
			out.close();

		}

	}

}
