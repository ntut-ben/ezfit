package shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00.utils.ToJson;
import createAccount.model.MemberBean;
import login.service.LoginServiceImpl;
import shopping.model.CartItem;
import shopping.service.impl.CartItemServiceImpl;

/**
 * Servlet implementation class CheckShopCart
 */
@WebServlet("/CheckShopCart.do")
public class CheckShopCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		CartItemServiceImpl cartItemServiceImpl = new CartItemServiceImpl();
		LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
		String account = null, pwd = null, json = null;
		ToJson<CartItem> toJson = new ToJson<CartItem>();
		List<CartItem> cartItems = null;
		HttpSession session = request.getSession(false);

//		Session 取出Member Bean物件
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		account = mb.getEmail();
		pwd = mb.getPassword();

		// 確認該Member Object帳密都正確
		if ((account != null && pwd != null)) {
			mb = loginServiceImpl.checkIDPassword(account, pwd);
		}

		// 取出該mb的購物車
		if (mb != null) {
			cartItems = cartItemServiceImpl.checkAllItems(mb);
			if (cartItems.size() > 0) {
				json = toJson.getArrayJson(cartItems);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
			}
			
		}

	}

}
